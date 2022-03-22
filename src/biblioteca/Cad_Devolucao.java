package biblioteca;
import java.awt.event.*;
import java.sql.*;
import java.util.Calendar;
import javax.swing.table.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
public class Cad_Devolucao extends JFrame {
    private JLabel lbl_Tombo;
    private JTextField txt_Tombo;
    private JButton bt_Concluir;
    public Connection connection;
    public PreparedStatement statement;
    public ResultSet resultSet;
    public static String DRIVER = "com.mysql.jdbc.Driver";
    public static String con_usuario, con_senha, URL, con_porta, con_bd, con_pc;
    public void Cad_Devolucao(){
        InicializarCad_Devolucao();
        DefinirEventos();
    }
     private void InicializarCad_Devolucao(){
        setBounds(0,0,200,200);
        setLayout(null);
        setTitle("NSB - Devolucao de livros");
        setVisible(true);
        getContentPane().setBackground(Color.getHSBColor(0.650f,0.158f,0.750f));
        lbl_Tombo = new JLabel("Tombo ou código:");
        txt_Tombo = new JTextField();
        bt_Concluir = new JButton("Concluir");
        lbl_Tombo.setBounds(10, 10, 150, 25);
        txt_Tombo.setBounds(10,40, 150, 25);
        bt_Concluir.setBounds(25, 70, 100, 25);
        add(bt_Concluir);
        add(lbl_Tombo);
        add(txt_Tombo);
    }
    private void DefinirEventos(){
           bt_Concluir.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
           String str_Tombo = txt_Tombo.getText();
           if(str_Tombo.equals("")) { JOptionPane.showMessageDialog(null, "Digite o tombo ou o código"); return; }
            //conecta BD
            Conexao conecta= new Conexao();
            URL = conecta.URL();
            con_usuario=conecta.con_usuario();
            con_senha= conecta.con_senha();
            try{
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, con_usuario, con_senha);
                System.out.println("Conectou");
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String sql = "select id_livro from livro where tombo = '"+str_Tombo+"' or NTOMBO = '"+str_Tombo+"' or id_livro = '"+str_Tombo+"'";
                System.out.println(sql);
                resultSet=statement.executeQuery(sql);
                resultSet.last();
                int num_linha = resultSet.getRow();
                resultSet.first();
                if (num_linha > 0){
                    String id_livro  = resultSet.getString("id_livro");
                    resultSet = statement.executeQuery("select id_reserva from reserva where id_livro = "+id_livro+"  and concluida = 'não'");
                    resultSet.last();
                    String reserva = "";
                    if (resultSet.getRow() > 0){
                         reserva = "Reservado";
                    }
                    else{
                         reserva = "Disponível";
                    }
                    sql = "select para, id_emprestimo from emprestimo where id_livro = "+id_livro+" and devolvido = 'não'";
                    System.out.println(sql);
                    resultSet = statement.executeQuery(sql);
                    resultSet.last();
                    num_linha = resultSet.getRow();
                    resultSet.first();
                    if(num_linha > 0){
                        String id_emprestimo = resultSet.getString("id_emprestimo");
                        String[] paraa = resultSet.getString("para").split("-");
                        Calendar calendario = Calendar.getInstance();
                        int int_mes = calendario.get(Calendar.MONTH);
                        int int_dia = calendario.get(Calendar.DAY_OF_MONTH);
                        int int_ano = calendario.get(Calendar.YEAR);
                        int int_hora = calendario.get(Calendar.HOUR_OF_DAY);
                        int int_min = calendario.get(Calendar.MINUTE);
                        int int_sec = calendario.get(Calendar.SECOND);
                        String devolucao = String.valueOf(int_ano)+"-"+String.valueOf(int_mes)+"-"+String.valueOf(int_dia)+" "+String.valueOf(int_hora)+":"+String.valueOf(int_min)+":"+String.valueOf(int_sec);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
                        float diferenca=0;
                            try {     
                                java.util.Date date1 = sdf.parse(paraa[2]+"/"+paraa[1]+"/"+paraa[0]);    
                                int_mes=int_mes+1;
                                java.util.Date date2 = sdf.parse(int_dia+"/"+int_mes+"/"+int_ano);   
                                long differenceMilliSeconds = date2.getTime() - date1.getTime();
                                diferenca=differenceMilliSeconds/1000/60/60/24;
                            } 
                            catch (Exception ef){     
                                ef.printStackTrace();     
                            }   
                        System.out.println(diferenca);
                        if (diferenca<=0){ //ok se return true o livro foi devolvido dentro do prazo                      
                            sql = "update emprestimo set devolucao = '"+devolucao+"', devolvido = 'sim' where id_emprestimo = '"+id_emprestimo+"'";
                            statement.executeUpdate(sql);
                            System.out.println(sql);
                            sql = "update livro set disponivel = '"+reserva+"' where id_livro = '"+id_livro+"'";
                            System.out.println(sql);
                            statement.executeUpdate(sql);
                            JOptionPane.showMessageDialog(null, "Livro devolvido dentro do prazo");
                            Cad_Devolucao.this.dispose();
                        }
                        else{ //o livro não foi devolvido dentro do prazo, eia o problema
                            Calendar dia1 = Calendar.getInstance();
                            Calendar dia2 = Calendar.getInstance();
                            dia1.set(int_ano, int_mes, int_dia);
                            dia2.set(Integer.parseInt(paraa[0]),Integer.parseInt(paraa[1]),Integer.parseInt(paraa[2]));
                            JOptionPane.showMessageDialog(null, "Você teve um atraso de: "+diferenca+" dia(s)");
                            String vd = JOptionPane.showInputDialog("Qual o valor diario da multa?", "1,00");            
                            double total_multa = diferenca*1;
                            JOptionPane.showMessageDialog(null, total_multa);
                            if(JOptionPane.showConfirmDialog(null, "A multa foi paga?", "Pagamento", JOptionPane.YES_NO_OPTION)==0){
                                // ja que a multa foi paga
                                sql = "insert into multa (valor, id_emprestimo, pago) values ('"+total_multa+"', "+id_emprestimo+", 1)";
                                System.out.println(sql);
                                statement.executeUpdate(sql);
                                sql = "update emprestimo set devolucao = '"+devolucao+"', devolvido = 'sim' where id_emprestimo = '"+id_emprestimo+"'";
                                statement.executeUpdate(sql);
                                sql = "update livro set disponivel = '"+reserva+"' where id_livro = '"+id_livro+"'";
                                statement.executeUpdate(sql);
                                System.out.println(sql);
                                JOptionPane.showMessageDialog(null, "livro devolvido e multa paga\n Obrigado");
                            }
                            else{
                                sql = "insert into multa (valor, id_emprestimo, pago) values ('"+total_multa+"', "+id_emprestimo+", 0)";
                                System.out.println(sql);
                                statement.executeUpdate(sql);
                                sql = "update emprestimo set devolucao = '"+devolucao+"', devolvido = 'sim' where id_emprestimo = '"+id_emprestimo+"'";
                                statement.executeUpdate(sql);
                                sql = "update livro set disponivel = '"+reserva+"' where id_livro = '"+id_livro+"'";
                                statement.executeUpdate(sql);
                                System.out.println(sql);
                                JOptionPane.showMessageDialog(null, "A multa ainda não foi paga, para posterior pagamento,\n utilize  a consulta de multas não pagas");
                            }
                            Cad_Devolucao.this.dispose();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Não há livro algum para ser devolvido com esse código");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "RM ou código do livro digitado errado");
                }
            }
            catch (Exception erro){
                JOptionPane.showMessageDialog(null, erro);
            }
      }
    });
   }
}


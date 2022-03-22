package biblioteca;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.util.GregorianCalendar;
import javax.swing.table.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.util.Calendar;
public class Cons_Reserva extends JFrame {
        private static Cons_Reserva frameco;
        private JTextField txt_Consulta;
        private JButton bt_Consultar;
        private javax.swing.JTable tabela;
        private javax.swing.JScrollPane st;
        public Connection connection;
        public PreparedStatement statement;
        public ResultSet resultSet;
        public static String DRIVER = "com.mysql.jdbc.Driver";
        public static String con_usuario, con_senha, URL, con_porta, con_bd, con_pc;
        public JButton bt_Emprestar, bt_Cancelar;
        private JLabel lbl_Tombo, lbl_RM, lbl_Para, lbl_Usuario, lbl_Senha;
        private JTextField txt_Tombo, txt_RM, txt_Usuario;
        private JPasswordField txt_Senha;
        private MaskFormatter ms_Para;
        private JFormattedTextField txt_Para;

    Cons_Reserva()
    {
        inicializarComponentes();
        definirEventos();

    }
    private void inicializarComponentes()
    {
        setBounds(0,0,800,600);
        setLayout(null);
        setTitle("NSB - Consulta de Reserva");
        getContentPane().setBackground(Color.getHSBColor(0.650f,0.158f,0.750f));
        txt_Consulta = new JTextField();
        bt_Consultar = new JButton("Consultar");
        txt_Consulta.setBounds(20, 10,300, 25);
        add(txt_Consulta);
        bt_Consultar.setBounds(350,10,100, 25);
        add(bt_Consultar);
        st=new JScrollPane();
        st.setBounds(18,40,800,200);
        add(st);
        bt_Emprestar = new JButton("Emprestar");
        bt_Emprestar.setBounds(20, 250, 110, 25);
        add(bt_Emprestar);
        bt_Cancelar =new JButton("Cancelar Reserva");
        bt_Cancelar.setBounds(140, 250, 150, 25);
        add(bt_Cancelar);
        try
        {
            ms_Para = new MaskFormatter("##/##/####");
        } 
        catch (ParseException ex)
        {
            Logger.getLogger(Cons_Reserva.class.getName()).log(Level.SEVERE, null, ex);
        }
            lbl_Tombo = new JLabel("Tombo:"); lbl_RM = new JLabel("RM:"); lbl_Para = new JLabel("Para:");
            lbl_Usuario = new JLabel("Usuario:"); lbl_Senha = new JLabel("Senha:");
            txt_Tombo = new JTextField(); txt_RM = new JTextField(); txt_Para =  new JFormattedTextField(ms_Para);
            txt_Usuario = new JTextField(); txt_Senha = new JPasswordField();
            Calendar calendario = Calendar.getInstance();
                        int mes = calendario.get(Calendar.MONTH);
                        int dia = calendario.get(Calendar.DAY_OF_MONTH);
                        int ano = calendario.get(Calendar.YEAR);
            if(dia<10)
            {
                if(mes < 10)
                {
                    txt_Para.setText("0"+dia+"0"+mes+""+ano);
                }
                else
                {
                    txt_Para.setText("0"+dia+""+mes+""+ano);
                }
            }
            else
            {
                if(mes < 10)
                {
                    txt_Para.setText(dia+"0"+mes+""+ano);
                }
                else
                {
                    txt_Para.setText(dia+""+mes+""+ano);
                }
            }
            lbl_Tombo.setBounds(18, 350, 80, 25); add(lbl_Tombo);
            txt_Tombo.setBounds(108,350, 80, 25); add(txt_Tombo); txt_Tombo.setEditable(false);
            lbl_RM.setBounds(18, 380, 80, 25); add(lbl_RM); txt_RM.setEditable(false);
            txt_RM.setBounds(108, 380, 80, 25); add(txt_RM);
            lbl_Para.setBounds(18, 410, 80, 25); add(lbl_Para);
            txt_Para.setBounds(108, 410, 80, 25); add(txt_Para);
            lbl_Usuario.setBounds(18, 440, 80, 25); add(lbl_Usuario);
            txt_Usuario.setBounds(108, 440, 80, 25); add(txt_Usuario);
            lbl_Senha.setBounds(18, 470, 80, 25); add(lbl_Senha);
            txt_Senha.setBounds(108, 470, 80, 25); add(txt_Senha);
    }
    private void definirEventos()
    {
       bt_Consultar.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
       consultar();
       }
       });
       bt_Emprestar.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
       emprestar();
       consultar();
       }
       });
       bt_Cancelar.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
           if(JOptionPane.showConfirmDialog(null, "Você tem certeza?", "Deletar Reserva", JOptionPane.YES_NO_OPTION)==1)
           {
               return;
           }
           //if(0==0) return;
        String id_reserva = tabela.getValueAt(tabela.getSelectedRow(), 0).toString();
        String sql = "delete from reserva where id_reserva ="+id_reserva;
        try
        {
          //conecta BD
          Conexao conecta= new Conexao();
          URL = conecta.URL();
          con_usuario=conecta.con_usuario();
          con_senha= conecta.con_senha();
          String consulta = txt_Consulta.getText();
          Class.forName(DRIVER);
          connection = DriverManager.getConnection(URL, con_usuario, con_senha);
          Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
          statement.executeUpdate(sql);
          JOptionPane.showMessageDialog(null, "Reserva Cancelada");
          consultar();
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, erro);
        }
       }
       });
    }
    public void consultar()
    {
        try
        {
          //conecta BD
        Conexao conecta= new Conexao();
        URL = conecta.URL();
        con_usuario=conecta.con_usuario();
        con_senha= conecta.con_senha();
            String consulta = txt_Consulta.getText();
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, con_usuario, con_senha);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);   
            connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String Consulta = txt_Consulta.getText();
           String sql = "SELECT r.id_reserva, l.tombo, l.disponivel, l.titulo, r.de AS  'do dia:', r.para AS  'para o dia:', u.nome AS  'Reservado para:', u.rm  FROM livro l, reserva r, usuario u WHERE l.id_livro = r.id_livro AND r.id_usuario = u.id_usuario and l.disponivel = 'Reservado' and r.concluida = 'não'  and (u.rm like '%"+consulta+"%' or u.nome like '%"+consulta+"%' ) ORDER BY de";
           // String sql="Select * from reserva";
            System.out.println(sql);
            resultSet=statement.executeQuery(sql);
            resultSet.last();
                if(resultSet.getRow() <= 0)
                {
                    JOptionPane.showMessageDialog(null, "A pesquisa não retornou resultado");
                    Cons_Reserva.this.dispose();
                    return;
                }
            resultSet.beforeFirst();
            DefaultTableModel tableMode1=new DefaultTableModel(new String[]{},0){};
            int qc=resultSet.getMetaData().getColumnCount();
            tableMode1.addColumn("Código");
            tableMode1.addColumn("Tombo");
            tableMode1.addColumn("Disponíbilidade");
            tableMode1.addColumn("Titulo");
            tableMode1.addColumn("do dia:");
            tableMode1.addColumn("Para o dia:");
            tableMode1.addColumn("Reservado Para:");
            tableMode1.addColumn("RM");
            //for(int i=1;i<=qc;i++)
            //{
  //              tableMode1.addColumn(resultSet.getMetaData().getColumnName(i));
//            }
            
            tabela = new JTable(tableMode1);
            DefaultTableModel dtm=(DefaultTableModel)tabela.getModel();
            System.out.println("foi x");
            while(resultSet.next())
            {
                try
                {
                    String[] dados= new String[qc];
                    for(int i=1;i<=qc;i++)
                    {

                        if(i==0)
                        {
                            dados[i-1]=resultSet.getString(i);
                        }
                        else if(i==1)
                        {
                            dados[i-1]=resultSet.getString(i);
                        }
                        else if(i==2)
                        {
                            dados[i-1]=resultSet.getString(i);
                        }
                        else if(i==3)
                        {
                            dados[i-1]=resultSet.getString(i);
                        }
                        else if (i==4)
                        {
                            dados[i-1]=resultSet.getString(i);
                        }
                        else if(i==5)
                        {
                            //do dia tal
                            String[] doDia = resultSet.getString(i).split("-");
                            dados[i-1]= doDia[2]+"/"+doDia[1]+"/"+doDia[0];
                        }
                        else if(i==6)
                        {
                            String[] doDia = resultSet.getString(i).split("-");
                            dados[i-1]= doDia[2]+"/"+doDia[1]+"/"+doDia[0];
                        }
                        else if(i==7)
                        {
                            dados[i-1]=resultSet.getString(i);
                        }
                        else if(i==8)
                        {
                            dados[i-1]=resultSet.getString(i);
                        }
                    }
                    dtm.addRow(dados);
                    System.out.println("um grande vazio");
                }
                catch(SQLException erro)
                {
                    JOptionPane.showMessageDialog(null, "erro1 "+erro);
                }
                System.out.println("um grande vazio 2");
                st.setViewportView(tabela);
                System.out.println("um grande vazio 3");
                tabela.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt)
                    {
                        tabelaMouseClicked(evt);
                    }
                });
            }
            System.out.println("foi y");
            resultSet.close();
            statement.close();
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null,"erro: "+ex);
            }
    }
    public void Carregacr()
    {
         SwingUtilities.invokeLater(new Runnable() {
           public void run()
            {
               frameco = new Cons_Reserva();
               frameco.setSize(1000,800);
               //frameco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               //frameco.setLocation(110, 110);
               //frameco.setBounds(0,0,700,500);
               //frameco.add(lbl_Nome);
               frameco.setVisible(true);

            }
        });
    }
    private void tabelaMouseClicked(java.awt.event.MouseEvent evt)
    {
        int x = tabela.getSelectedRow();
        String tombo = tabela.getValueAt(x, 1).toString();
        String rm = tabela.getValueAt(x,7).toString();
        txt_Tombo.setText(tombo);
        String para = tabela.getValueAt(x, 4).toString();
        txt_Para.setText(para);
        txt_RM.setText(rm);
    }
 public void emprestar()
    {
           String id_reserva = tabela.getValueAt(tabela.getSelectedRow(), 0).toString();
           //JOptionPane.showMessageDialog(null, txt_Para.getText());
           String tombo, rm, para, usuario, senha;
           if(txt_Tombo.getText().equals("")) { JOptionPane.showMessageDialog(null, "Digite o tombo"); txt_Tombo.requestFocus(); return; }
           if(txt_RM.getText().equals("")) { JOptionPane.showMessageDialog(null, "Digite o rm"); txt_RM.requestFocus(); return; }
           if(txt_Para.getText().equals("")) { JOptionPane.showMessageDialog(null, "Digite a data de evolução"); txt_Para.requestFocus(); return; }
           if(txt_Usuario.getText().equals("")) { JOptionPane.showMessageDialog(null, "Digite o seu usuario e login"); txt_Usuario.requestFocus(); return; }
           if(txt_Senha.getPassword().toString().equals("")) { JOptionPane.showMessageDialog(null, "Digite a su senha"); txt_Senha.requestFocus(); return; }
           int x = tabela.getSelectedRow();
           String str_Tombo = tabela.getValueAt(x, 1).toString();
           tombo = str_Tombo;
           rm = txt_RM.getText();
           para = txt_Para.getText();
           usuario = txt_Usuario.getText();
           senha = txt_Senha.getText();
           String sql = "select id_funcionario from funcionario where login = '"+usuario+"' and senha= '"+senha+"'";
           System.out.println(sql);
           try
           {
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                resultSet.last();
                int num_linha = resultSet.getRow();
                resultSet.first();
                if(num_linha > 0)
                {
                    String id_funcionario = resultSet.getString("id_funcionario");
                    String sql_busca_id_livro = "select id_livro from livro where tombo = '"+tombo+"' and disponivel = 'Reservado'";
                    System.out.println(sql_busca_id_livro);
                    resultSet = statement.executeQuery(sql_busca_id_livro);
                    resultSet.last();
                    num_linha = resultSet.getRow();
                    resultSet.first();
                    String id_usuario, id_livro;
                    if(num_linha > 0)
                    {
                        id_livro = resultSet.getString("id_livro");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "ou o livro não existe, ou o tombo está digitado incorretamente");
                        JOptionPane.showMessageDialog(null, tombo);
                        return;
                    }
                    String sql_busca_id_usuario = "select * from usuario where rm = '"+rm+"'";
                    System.out.println(sql_busca_id_usuario);
                    resultSet = statement.executeQuery(sql_busca_id_usuario);
                    resultSet.last();
                    num_linha = resultSet.getRow();
                    resultSet.first();
                    String nome = "";
                    if(num_linha > 0)
                    {
                        id_usuario = resultSet.getString("id_usuario");
                         nome = resultSet.getString("nome");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "ou o RM digitado não existe, ou o RM foi digitado errado");
                        return;
                    }
                    sql = "select * from reserva where concluida = 'não' and id_livro = '"+id_livro+"' order by de limit 0,1";
                    System.out.println(sql);
                    resultSet = statement.executeQuery(sql);
                    resultSet.last();
                    num_linha = resultSet.getRow();
                    resultSet.first();
                    if(num_linha > 0)
                    {
                        if (!resultSet.getString("id_usuario").equals(id_usuario))
                        {
                            JOptionPane.showMessageDialog(null, "O usuario selecionado não é o próximo da fila das reservas do livro");
                            return;
                        }
                    }
                    //if (0==0) return;
                    String sql_Verifica = "select * from emprestimo where id_usuario = "+id_usuario+" and devolvido = 'não'";
                    resultSet = statement.executeQuery(sql_Verifica);
                    resultSet.last();
                    num_linha = resultSet.getRow();
                    resultSet.first();
                    if(num_linha >= 3)
                    {
                        JOptionPane.showMessageDialog(null, "Este usuario Ja possui 3 livros emprestados!!!\nDevolva pelo menos um livro para poder pegar outro");
                        return;
                    }
                    int id_emprestimo = (int) (Math.random() * 1000000000);
                    String sql_checa_id_emprestimo ="select id_emprestimo from emprestimo where id_emprestimo = "+id_emprestimo;
                    resultSet = statement.executeQuery(sql_checa_id_emprestimo);
                    resultSet.last();
                    num_linha = resultSet.getRow();
                    resultSet.first();
                    while(num_linha > 0)
                    {
                        id_emprestimo = (int) (Math.random() * 1000000000);
                        sql_checa_id_emprestimo = "select id_emprestimo from emprestimo where id_emprestimo = "+id_emprestimo;
                        resultSet = statement.executeQuery(sql_checa_id_emprestimo);
                        resultSet.last();
                        num_linha = resultSet.getRow();
                        resultSet.first();
                    }
                    String[] str_Data = para.split("/");
                    String data_para = str_Data[2]+"-"+str_Data[1]+"-"+str_Data[0];
                    //continuar sql paara cadastro do emprestimo;
                    GregorianCalendar calendar = new GregorianCalendar();
                    int dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);
                    int mes = calendar.get(GregorianCalendar.MONTH);
                    int ano = calendar.get(GregorianCalendar.YEAR);
                    int int_hora = calendar.get(GregorianCalendar.HOUR_OF_DAY);
                    int int_min = calendar.get(GregorianCalendar.MINUTE);
                    int int_sec = calendar.get(GregorianCalendar.SECOND);
                    String data_de_hoje = ano+"-"+mes+"-"+dia+" "+int_hora+":"+int_min+":"+int_sec;

                    String sql2 = "insert into emprestimo (id_emprestimo, id_livro, id_usuario, id_funcionario, de, para) values("+id_emprestimo+","+id_livro+", "+id_usuario
                            + ","+id_funcionario+", '"+data_de_hoje+"', '"+data_para+"' )";
                    System.out.println(sql2);
                    statement.executeUpdate(sql2);
                    String sql3 = "update livro set disponivel = 'Não Disponível' where id_livro = "+id_livro;
                    statement.executeUpdate(sql3);
                    sql3 = "update reserva set concluida = 'sim' where id_reserva = "+id_reserva;
                    statement.executeUpdate(sql3);
                    //JOptionPane.showMessageDialog(null, "Livro emprestado com sucesso");
                    JOptionPane.showMessageDialog(null, "livro emprestado para: "+nome+"\n RM : "+rm);
                     //gravar
                    try
                    {
                        PrintWriter out = new PrintWriter("Imprimir.html");
                        out.println("<html><head><title>Imprimir</title></head><body><center>");
                        out.println("Livro tombo :"+ tombo+"<br>");
                        out.println("RM :"+ txt_RM.getText()+"<br>");
                        out.println("Aluno :"+ nome+"<br>");
                        out.println("Devolver dia:" +txt_Para.getText()+"<br>");
                        out.println("Funcionario responsavel:"+ txt_Usuario.getText()+"<br>");
                        out.println("Assintura do Aluno:______________________"+"<br>");
                        out.println("Assintura do Funcionario:______________________"+"<br>");
                        out.println("------------------------------------------------"+"<br>");
                        out.println("Em caso de atraso da devolução será <br> cobrado multa de R$1,00 por dia de atraso.");
                        out.println("</center></body></html>");
                        out.close();
                        JOptionPane.showMessageDialog(null, "Arquivo gravado com sucesso!\n e pronto para imprimir");
                        }
                        catch(IOException erro)
                        {
                            JOptionPane.showMessageDialog(null, "Erro ao gravar no arquivo");
                        }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Usuario e/ou senha incorreto(s)");
                }

           }
           catch(Exception erro)
           {
               JOptionPane.showMessageDialog(null, erro);
           }
    }
}

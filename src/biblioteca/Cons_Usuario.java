package biblioteca;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
public class Cons_Usuario extends JFrame {
        public static Cons_Usuario frameco;
        private JTextField txt_Consulta;
        public String rm;
        private JButton bt_Consultar;
        public javax.swing.JTable tabela;
        private JButton bt_Novo, bt_Ver, bt_Editar, bt_Deletar;
        private javax.swing.JScrollPane st;
        public Connection connection;
        public PreparedStatement statement;
        public ResultSet resultSet;
        public static String DRIVER = "com.mysql.jdbc.Driver";
        public static String con_usuario, con_senha, URL, con_porta, con_bd, con_pc;
    public void Cons_Usuario(){
        inicializarComponentes();
        definirEventos();
    }
    private void inicializarComponentes(){
        setBounds(0,0,890,360);
        setLayout(null);
        setTitle("NSB - Consulta de usuario");
        setVisible(true);
        getContentPane().setBackground(Color.getHSBColor(0.650f,0.158f,0.750f));
        // parte da consulta
        txt_Consulta = new JTextField();
        txt_Consulta.setBounds(20, 10,300, 25);
        add(txt_Consulta);
        bt_Consultar = new JButton("Consultar");
        bt_Consultar.setBounds(350,10,100, 25);
        add(bt_Consultar);
        st=new JScrollPane();
        st.setBounds(18,40,800,200);
        add(st);
        // fim parte da consulta
        //botões
        bt_Novo = new JButton("Novo Usuario");
        bt_Novo.setBounds(20, 260, 150, 25);
        add(bt_Novo);
        bt_Ver = new JButton("Consultar");
        bt_Ver.setBounds(180, 260, 100, 25);
        bt_Editar = new JButton("Editar");
        bt_Editar.setBounds(290, 260, 100, 25);
        add(bt_Editar);
        bt_Editar.setEnabled(false);
        bt_Deletar = new JButton("Deletar");
        bt_Deletar.setBounds(400, 260, 100, 25);
        add(bt_Deletar);
        bt_Deletar.setEnabled(false);
        // fim botões
        //conexao banco de dados
         //conecta BD
        Conexao conecta= new Conexao();
        URL = conecta.URL();
        con_usuario=conecta.con_usuario();
        con_senha= conecta.con_senha();
            try{
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, con_usuario, con_senha);
                System.out.println("Conectou");
            }
            catch (ClassNotFoundException ex){
                Logger.getLogger(Cad_Obra.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (SQLException ex){
                Logger.getLogger(Cad_Obra.class.getName()).log(Level.SEVERE, null, ex);
            }
        // fim conexaobd
    }
    private void definirEventos(){
        bt_Deletar.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
             if(JOptionPane.showConfirmDialog(null, "Você realmente deseja deletar esse Usuario?", "**** deletando ****", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0){
                 int x = tabela.getSelectedRow();
                 int y = 0;
                 rm = tabela.getValueAt(tabela.getSelectedRow(), 0).toString();
                 String sql = "delete from usuario where RM = '"+rm+"'";
                 System.out.println(sql);
                 try{
                    Class.forName(DRIVER);
                    Connection connection = DriverManager.getConnection(URL, con_usuario, con_senha);
                    Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);                
                    statement.execute(sql);
                 }
                 catch(Exception ef){
                     JOptionPane.showMessageDialog(null,ef);
                 }
             }
        }
        });
        bt_Editar.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            Editar_usuario edit= new Editar_usuario();
            edit.Editar_usuario();
            int x = tabela.getSelectedRow();
            int y = 0;           
            }
        });
        bt_Ver.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            int x = tabela.getSelectedRow();
            int y = 0;
            Object obj = tabela.getValueAt(tabela.getSelectedRow(), 0);
            String rm = tabela.getValueAt(tabela.getSelectedRow(), 0).toString();
            }
        });
        bt_Consultar.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          consultarLivro();
          }
       });
       bt_Novo.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
          Cad_Usuario cadastro = new Cad_Usuario();
          cadastro.Cad_Usuario();
          }
       });
    }
    public void tabelaMouseClicked(java.awt.event.MouseEvent evt){
        try{
            int x = tabela.getSelectedRow();
            String obj = tabela.getValueAt(x, 0).toString();
            String sql = "select id_usuario from usuario where rm = '"+obj+"'";
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            resultSet.first();
            String id_usuario = resultSet.getString("id_usuario");
            sql = "select id_usuario from emprestimo where id_usuario = '"+id_usuario+"' and devolvido = 'não' ";
            resultSet = statement.executeQuery(sql);
            resultSet.last();
            int num_linhas = resultSet.getRow();
            resultSet.first();
            sql = "select id_usuario from reserva where id_usuario = '"+id_usuario+"' and concluida = 'não'";
            resultSet = statement.executeQuery(sql);
            resultSet.last();
            int num_linhas2 = resultSet.getRow();
            resultSet.first();
            if(num_linhas < 0 && num_linhas2 < 0){
                bt_Editar.setEnabled(true);
                bt_Ver.setEnabled(true);
                bt_Deletar.setEnabled(true);
            }
            else{
                bt_Ver.setEnabled(true);
                bt_Editar.setEnabled(true);
                bt_Deletar.setEnabled(true);
            }
        }
        catch(Exception erro){
            JOptionPane.showMessageDialog(null, erro);
        }
    }
    public void consultarLivro(){
     try{
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String Consulta = txt_Consulta.getText();
                String sql = "select RM, nome, rg, periodo, serie from usuario where nome like '%"+Consulta+"%' or rg like '%"+Consulta+"%' or curso like '%"+Consulta+"%' or periodo like '%"+Consulta+"%' or  serie  like '%"+Consulta+"%' or  rm like '%"+Consulta+"%' or  data_nasc like '%"+Consulta+"%' or  data_insc like '%"+Consulta+"%' or  numero like '%"+Consulta+"%' or  complemento like '%"+Consulta+"%' or  bairro like '%"+Consulta+"%'  or cidade like '%"+Consulta+"%'  or telefone like '%"+Consulta+"%'  or celular like '%"+Consulta+"%'  or email like '%"+Consulta+"%'  or rua like '%"+Consulta+"%' ";
                System.out.println(sql);
                resultSet=statement.executeQuery(sql);
                DefaultTableModel tableMode1=new DefaultTableModel(new String[]{},0){};
                int qc=resultSet.getMetaData().getColumnCount();
                for(int i=1;i<=qc;i++){
                    tableMode1.addColumn(resultSet.getMetaData().getColumnName(i));
                }
                tabela = new JTable(tableMode1);
                DefaultTableModel dtm=(DefaultTableModel)tabela.getModel();
                while(resultSet.next()){
                try{
                  String[] dados= new String[qc];
                  for(int i=1;i<=qc;i++){
                    dados[i-1]=resultSet.getString(i);
                  }
                  dtm.addRow(dados);
                  System.out.println();
                }
                catch(SQLException erro){
                    JOptionPane.showMessageDialog(null,erro);
                }
                st.setViewportView(tabela);
                }
           } 
            catch (Exception ex){
               JOptionPane.showMessageDialog(null,"erro"+ex);
           }
     tabela.addMouseListener(new java.awt.event.MouseAdapter() {
     public void mouseClicked(java.awt.event.MouseEvent evt){
             tabelaMouseClicked(evt);
            }
        });
    }
}
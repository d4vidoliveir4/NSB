package biblioteca;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
public class Cons_Multa extends JFrame {
        public static Cons_Multa frameco;
        private JTextField txt_Consulta;
        public String rm;
        private JButton bt_Consultar;
        public javax.swing.JTable tabela;
        private JButton bt_Novo;
        private javax.swing.JScrollPane st;
        public Connection connection;
        public PreparedStatement statement;
        public ResultSet resultSet;
        public static String DRIVER = "com.mysql.jdbc.Driver";
        public static String con_usuario, con_senha, URL, con_porta, con_bd, con_pc;
    public void Cons_Multa(){
        inicializarComponentes();
        definirEventos();
    }
    private void inicializarComponentes(){
        setBounds(0,0,800,600);
        setLayout(null);
        setTitle("NSB - Consulta de Multas");
        setSize(890,360);
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
        bt_Novo =new JButton("Pago");
        bt_Novo.setBounds(100,250,100,25);
        add(bt_Novo);
        // fim botões
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
    bt_Consultar.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
     try{
        String consulta=txt_Consulta.getText();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery("SELECT m.id_multa,m.valor, u.rm from multa m, emprestimo e, usuario u where m.id_emprestimo=e.id_emprestimo and e.id_usuario=u.id_usuario and m.pago=0 and u.rm='"+consulta+"'"); 
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
                  st.enableInputMethods(false);
            }
              resultSet.close();
              statement.close();
           } 
           catch (Exception ex){
               JOptionPane.showMessageDialog(null,ex);
           }
        }
    });
      bt_Novo.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
            int x = tabela.getSelectedRow();
            String obj = tabela.getValueAt(x, 0).toString();
            try{
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);                       
             String sql = "UPDATE multa set pago= 1 WHERE id_multa='"+obj+"'";  
             System.out.println(sql); 
             statement.execute(sql);
            }
            catch(Exception er){
                JOptionPane.showMessageDialog(null,"Erro: "+er);
            }
        }
    });
}
}

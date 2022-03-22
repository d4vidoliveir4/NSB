package biblioteca;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
public class relatorio extends JFrame {
        private JButton bt_Consultar;
        private javax.swing.JTable tabela;
        private javax.swing.JScrollPane st;
        public Connection connection;
        public PreparedStatement statement;
        public ResultSet resultSet;
        JComboBox combo_tipo;
        JTextField txt_comeco, txt_final;
        JLabel lbl_combo, lbl_comeco, lbl_final,lbl_dica;
        public static String DRIVER = "com.mysql.jdbc.Driver";
        public static String con_usuario, con_senha, URL, con_porta, con_bd, con_pc;
        public void relatorio(){
        inicializarComponentes();
        def();
    }
    public void inicializarComponentes(){
        setBounds(0,0,890,360);
        setLayout(null);
        setTitle("NSB - Relatórios");
        setVisible(true);
        getContentPane().setBackground(Color.getHSBColor(0.650f,0.158f,0.750f));
        bt_Consultar = new JButton("Gerar");
        st=new JScrollPane();
        st.setBounds(18,100,800,200);
        bt_Consultar.setBounds(600,40,100, 25);
        lbl_dica=new JLabel("Escolha o tipo de relatorio e o periodo que deverá ser usado.");
        lbl_dica.setBounds(10,10,800,25); add(lbl_dica);
        add(bt_Consultar);
        add(st);
        combo_tipo = new JComboBox();
        combo_tipo.setBounds(40, 40, 150, 25);
        add(combo_tipo);
        lbl_comeco = new JLabel("Começo");
        lbl_comeco.setBounds(200, 40, 80, 25);
        add(lbl_comeco);
        txt_comeco = new JTextField();
        txt_comeco.setBounds(290, 40, 80, 25);
        add(txt_comeco);
        lbl_final = new JLabel("Fim");
        lbl_final.setBounds(380, 40, 80, 25);
        add(lbl_final);
        txt_final = new JTextField();
        txt_final.setBounds(480, 40, 80, 25);
        add(txt_final);
        combo_tipo.addItem("Multa");
        combo_tipo.addItem("Emprestimo");
        combo_tipo.addItem("Cadastros");
        combo_tipo.addItem("Leitor nota 10");
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
    }
    public void def(){
    bt_Consultar.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
          consultarLivro();
        }
    });
    }
    public void consultarLivro(){
      try{
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sql="";
        String str_inicio=txt_comeco.getText();
        String str_final = txt_final.getText();
        String [] inicio = str_inicio.split("/");
        String [] fim = str_final.split("/");
        str_inicio = inicio[2]+"-"+inicio[1]+"-"+inicio[0];
        str_final = fim[2]+"-"+fim[1]+"-"+fim[0];
        if(combo_tipo.getSelectedItem().equals("Multa")){
            sql = "select sum(valor) as total ,m.valor, e.devolucao from multa m, emprestimo e where m.id_emprestimo = e.id_emprestimo between '"+str_inicio+"' and '"+str_final+"'";
            System.out.println(sql);
        }
        if(combo_tipo.getSelectedItem().equals("Emprestimo")){
            sql = "SELECT COUNT( * ) as total FROM emprestimo WHERE de between '"+str_inicio+"' and '"+str_final+"'";
        }
        if(combo_tipo.getSelectedItem().equals("Cadastros")){
            sql = "SELECT COUNT( * ) as total FROM livro WHERE data_cadastro between '"+str_inicio+"' and '"+str_final+"'";
        }
        if(combo_tipo.getSelectedItem().equals("Leitor nota 10")){
            sql =  sql = "select COUNT(u.id_usuario) as total, e.id_usuario, u.nome from usuario u, emprestimo e where e.id_usuario = u.id_usuario and e.de between '"+str_inicio+"' and '"+str_final+"' group by u.id_usuario";
        }
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
          resultSet.close();
          statement.close();
          }
          catch (Exception ex){
               JOptionPane.showMessageDialog(null,ex);
          }
          tabela.addMouseListener(new java.awt.event.MouseAdapter() {
          public void mouseClicked(java.awt.event.MouseEvent evt){
             tabelaMouseClicked(evt);
            }
         });
       }
       private void tabelaMouseClicked(java.awt.event.MouseEvent evt){
           int x = tabela.getSelectedRow();
           String obj = tabela.getValueAt(x, 2).toString();
        }
}

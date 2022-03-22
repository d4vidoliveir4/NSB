
package biblioteca;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.io.File;
public class Main extends JFrame {
    public String user, pass;
    public int a;
    private JTextField txt_Usuario;
    private JPasswordField txt_Senha;
    private JLabel lbl_Usuario, lbl_Senha;
    private JButton bt_Logar,bt_first;
    private static Main framem;
    public Connection connection;
    public PreparedStatement statement;
    public ResultSet resultSet;
    public static String DRIVER = "com.mysql.jdbc.Driver";
    public static String con_usuario, con_senha, URL, con_porta, con_bd, con_pc;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                framem = new Main();
                framem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                framem.setBounds(300,300,200,200);
                framem.setVisible(true);
            }
        });
    }
    public  Main()
    {
        inicializarComponentes();
        Backup_Obrigatorio backup = new Backup_Obrigatorio();
        backup.gerar_backup();
    }
    public void inicializarComponentes()
    {
        setTitle("NSB - Login");
        getContentPane().setBackground(Color.getHSBColor(0.650f,0.158f,0.750f));
        setLayout(null);
        setResizable(false);
        setBounds(300,300,200,200);
        txt_Usuario = new JTextField();
        txt_Senha = new JPasswordField();
        lbl_Usuario = new JLabel("Usuario:");      
        lbl_Senha = new JLabel("Senha:");
        bt_Logar = new JButton("Logar");
        bt_first = new JButton("Novo?");
        lbl_Usuario.setBounds(10, 10, 120, 25);
        lbl_Senha.setBounds(10,50,120,25);
        txt_Usuario.setBounds(80,10,100,25);
        txt_Senha.setBounds(80,50,100,25);
        bt_Logar.setBounds(10,85,170,30);
        bt_first.setBounds(10,120,170,30);
        add(lbl_Usuario);
        add(lbl_Senha);
        add(txt_Usuario);
        add(txt_Senha);
        add(bt_Logar);
        add(bt_first);
        setVisible(true);
         //conecta BD
        Conexao conecta= new Conexao();
        URL = conecta.URL();
        con_usuario=conecta.con_usuario();
        con_senha= conecta.con_senha();
            try
            {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, con_usuario, con_senha);
                System.out.println("Conectou");
                
            }
            catch (ClassNotFoundException ex)
            {
                Logger.getLogger(Cad_Obra.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (SQLException ex)
            {
                Logger.getLogger(Cad_Obra.class.getName()).log(Level.SEVERE, null, ex);
            }
        // fim conexaobd
         definireventos();
    }
    private void definireventos(){
    txt_Usuario.addMouseListener(new MouseAdapter() {  
      public void mouseClicked(MouseEvent me) {  
          //Verificando se o botão direito do mouse foi clicado  
          if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {  
              JPopupMenu menu = new JPopupMenu();  
              add(menu);  
              
              JMenuItem item = new JMenuItem("Copiar");  
              menu.add(item); 
                item.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            JOptionPane.showMessageDialog(null,"Foi");
                        }
                    });
              JMenuItem item2 = new JMenuItem("Recortar");  
              menu.add(item2); 
              JMenuItem item3 = new JMenuItem("Colar");  
              menu.add(item3);  
              menu.show(menu, me.getX(), me.getY());  
  
          }  
      }  
  });
     bt_Logar.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
      try{
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sql = "Select * from funcionario where login= '"+txt_Usuario.getText()+"' and senha= '"+txt_Senha.getText()+"'";
        System.out.println(sql);
        resultSet=statement.executeQuery(sql);
        resultSet.first();
        pass=resultSet.getString("senha");
        user=resultSet.getString("login");
        if(txt_Senha.getText().equals(pass) && txt_Usuario.getText().equals(user)){
          
              MainScreen ms = new MainScreen();        
              ms.MainScreen();
              Main.this.dispose();        
        }
        else{
            JOptionPane.showMessageDialog(null,"Usuario ou Senha incorretos!");
            txt_Usuario.setText("");
            txt_Senha.setText("");
        }
        }
        catch(Exception er){
            JOptionPane.showMessageDialog(null, "Verifique as configurações!");
        }
        }
      });
        bt_first.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
        Cad_Opcoes opcoes = new Cad_Opcoes();
        if((JOptionPane.showInputDialog("Informe a senha de primeiro acesso!")).equals("firstacessjava")){
           JOptionPane.showMessageDialog(null,"Parabens por adquirir o Biblio's System 2.0!\nPara começar insira as configurações do banco de dados\ne o seu primeiro funcionario!");
           opcoes.Cad_Opcoes();
           Main.this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null,"Desculpe mas você não tem permisão para fazer essas configurações!");
        }
      }
      });
    }
}

package biblioteca;
import java.awt.event.*;
import java.sql.*;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.util.Calendar;
public class Cad_Funcionario extends JFrame {
        private JLabel lbl_Nome,lbl_RG, lbl_DN, lbl_Endereco, lbl_Numero,lbl_Complemento, lbl_Bairro, lbl_Cidade;
        private JLabel lbl_Telefone,lbl_Celular, lbl_Email;
        private JTextField txt_Nome,txt_RG, txt_DN, txt_Endereco, txt_Numero, txt_Complemento, txt_Bairro, txt_Cidade;
        private JTextField txt_Telefone, txt_Celular, txt_Email;
        private JButton bt_Cadastrar, bt_Cancelar;
        private JLabel lbl_Login, lbl_Senha;
        private JTextField txt_Login;
        private JCheckBox bibliotecario;
        private JPasswordField ps_Senha;
        private static String  con_usuario, con_senha, URL;
        private static String DRIVER = "com.mysql.jdbc.Driver";
    public void Cad_Funcionario(){
        inicializarComponentes();
        definirEventos();
    }
    private void inicializarComponentes()
    {
         //conecta BD
        Conexao conecta= new Conexao();
        URL = conecta.URL();
        con_usuario=conecta.con_usuario();
        con_senha= conecta.con_senha();
        setBounds(0,0,800,600);
        setLayout(null);
        setTitle("NSB - Cadastro de Funcionarios");
        setVisible(true);
        getContentPane().setBackground(Color.getHSBColor(0.650f,0.158f,0.750f));
        lbl_Nome = new JLabel("Nome:");
        lbl_RG = new JLabel("RG:");
        lbl_Endereco = new JLabel("Endereco:");
        lbl_Numero = new JLabel("Número:");
        lbl_Complemento = new JLabel("Complemento:");
        lbl_Bairro = new JLabel("Bairro:");
        lbl_Cidade = new JLabel("Cidade");
        lbl_Telefone = new JLabel("Telefone:");
        lbl_Celular = new JLabel("Celular:");
        lbl_Email = new JLabel("Email:");
        lbl_DN = new JLabel("Nascimento:");
        txt_Nome = new JTextField();
        txt_RG = new JTextField(); 
        txt_Endereco = new JTextField();
        txt_Numero = new JTextField();
        txt_Complemento = new JTextField();
        txt_Bairro = new JTextField();
        txt_Cidade = new JTextField();
        txt_Telefone = new JTextField();
        txt_Celular = new JTextField();
        txt_Email = new JTextField();
        txt_DN = new JTextField();
        bt_Cadastrar = new JButton("Cadastrar");
        bt_Cancelar = new JButton("Cancelar");
        lbl_Login = new JLabel("Usuario:");
        lbl_Senha = new JLabel("Senha:");
        txt_Login = new JTextField();
        ps_Senha = new JPasswordField();
        bibliotecario = new JCheckBox("Pode fazer emprestimos.");
        bibliotecario.setBackground(Color.getHSBColor(0.650f,0.158f,0.750f));
        lbl_Nome.setBounds(18,40, 80,25);
        add(lbl_Nome);
        txt_Nome.setBounds(108, 40, 500, 25);
        add(txt_Nome);
        lbl_RG.setBounds(18,80, 80,25);
        add(lbl_RG);
        txt_RG.setBounds(108,80, 200, 25);
        add(txt_RG);
        lbl_DN.setBounds(320, 80,110,25);
        add(lbl_DN);
        txt_DN.setBounds(410,80,197,25);
        add(txt_DN);
        lbl_Endereco.setBounds(18,150,80,25);
        add(lbl_Endereco);
        txt_Endereco.setBounds(108,150,500,25);
        add(txt_Endereco);
        lbl_Numero.setBounds(18,190,80,25);
        add(lbl_Numero);
        txt_Numero.setBounds(108,190,100,25);
        add(txt_Numero);
        lbl_Complemento.setBounds(220,190,110,25);
        add(lbl_Complemento);
        txt_Complemento.setBounds(330,190,277,25);
        add(txt_Complemento);
        lbl_Bairro.setBounds(18,230,200,25);
        add(lbl_Bairro);
        txt_Bairro.setBounds(108,230,500,25);
        add(txt_Bairro);
        lbl_Cidade.setBounds(18,270,200,25);
        add(lbl_Cidade);
        txt_Cidade.setBounds(108,270,500,25);
        add(txt_Cidade);
	lbl_Telefone.setBounds(18,340,100,25);
	add(lbl_Telefone);
	txt_Telefone.setBounds(108,340,180,25);
	add(txt_Telefone);
	lbl_Celular.setBounds(320,340,100,25);
	add(lbl_Celular);
	txt_Celular.setBounds(410,340,200,25);
	add(txt_Celular);
	lbl_Email.setBounds(18,380,100,25);
	add(lbl_Email);
	txt_Email.setBounds(108,380,500,25);
	add(txt_Email);
        lbl_Login.setBounds(18, 450,80, 25);
        add(lbl_Login);
        txt_Login.setBounds(108, 450,180,25);
        add(txt_Login);
        lbl_Senha.setBounds(340, 450, 80, 25);
        add(lbl_Senha);
        ps_Senha.setBounds(410, 450, 200, 25);
        add(ps_Senha);
        bibliotecario.setBounds(18,490,500,25);
        add(bibliotecario);
	bt_Cadastrar.setBounds(620,350,150,40);
	add(bt_Cadastrar);
        bt_Cancelar.setBounds(620, 400, 150, 40);
        add(bt_Cancelar);
    }
    private void definirEventos()
    {
             this.addMouseListener(new MouseAdapter() {  
  
      public void mouseClicked(MouseEvent me) {  
          //Verificando se o botão direito do mouse foi clicado  
          if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {  
              JPopupMenu menu = new JPopupMenu();  
              add(menu);  
              JMenuItem item = new JMenuItem("Copiar");  
              menu.add(item); 
              JMenuItem item2 = new JMenuItem("Recortar");  
              menu.add(item2); 
              JMenuItem item3 = new JMenuItem("Colar");  
              menu.add(item3);  
              menu.show(menu, me.getX(), me.getY());  
  
          }  
      }  
  });
        bt_Cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                Cad_Funcionario.this.dispose();
            }
        });
         bt_Cadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                int empresta;
                if(bibliotecario.isSelected()==true)
                    empresta=1;
                else
                    empresta=0;
                
                try
                {
                String str_Nome, str_RG, str_Nascimento, str_Celular, str_Email, str_Login, str_Senha;
                String str_Endereco, str_Numero, str_Complemento, str_Bairro, str_Cidade, str_Telefone;
                if (txt_Nome.getText().equals("")) { return; }
                if (txt_RG.getText().equals("")) { return; }
                if (txt_DN.getText().equals("")) { return; }
                if (txt_Email.getText().equals("")) { return; }
                if (txt_Endereco.getText().equals("")) { return; }
                if (txt_Numero.getText().equals("")) { return; }
                if (txt_Bairro.getText().equals("")) { return; }
                if (txt_Cidade.getText().equals("")) { return; }
                if (txt_Login.getText().equals("")) { return; }
                if (ps_Senha.getText().equals("")) { return; }
                String SQL_CHECA_CADASTRO = "select RG from funcionario where RG = '"+txt_RG.getText()+"' or login = '"+txt_Login.getText()+"'";
                Class.forName(DRIVER);
                Connection connection = DriverManager.getConnection(URL, con_usuario, con_senha);
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet resultSet = statement.executeQuery(SQL_CHECA_CADASTRO);
                resultSet.last();
                int num_linha = resultSet.getRow();
                resultSet.first();
                if (num_linha > 0 )
                {
                    JOptionPane.showMessageDialog(null, "Esse Usuario já existe!");
                    return;
                }
                str_Nome = txt_Nome.getText();
                str_RG = txt_RG.getText();
                str_Nascimento = txt_DN.getText();
                str_Celular = txt_Celular.getText();
                str_Email = txt_Email.getText();
                str_Endereco = txt_Endereco.getText();
                str_Numero = txt_Numero.getText();
                str_Complemento =txt_Complemento.getText();
                str_Bairro = txt_Bairro.getText();
                str_Cidade = txt_Cidade.getText();
                str_Telefone = txt_Telefone.getText();
                str_Login = txt_Login.getText();
                str_Senha = ps_Senha.getText();
                int id_funcionario = (int) (Math.random() * 1000000000);
                Calendar calendario = Calendar.getInstance();
                        int mes = calendario.get(Calendar.MONTH);
                        int dia = calendario.get(Calendar.DAY_OF_MONTH);
                        int ano = calendario.get(Calendar.YEAR);
                String data_cadastro = ano+"-"+mes+"-"+dia;
                String SQL_CADASTRA_FUNCIONARIO = "insert into funcionario "
                        + "(id_funcionario, nome, data_nasc, data_cadastro, RG, rua, numero, complemento, bairro, "
                        + "telefone, celular, email, cidade, login, senha,empresta)"
                        + "values ("+id_funcionario+", '"+str_Nome+"', '"+str_Nascimento+"', '"+data_cadastro+"', '"+str_RG+"', '"+str_Endereco+"', '"+str_Numero+"', '"+str_Complemento+"', '"+str_Bairro+"', "
                        + "'"+str_Telefone+"', '"+str_Celular+"', '"+str_Email+"', '"+str_Cidade+"', '"+str_Login+"', '"+str_Senha+"',"+empresta+") ";
                System.out.println(SQL_CADASTRA_FUNCIONARIO);
                    try
                    {
                        statement.executeUpdate(SQL_CADASTRA_FUNCIONARIO);
                        JOptionPane.showMessageDialog(null, "Funcionario Cadastrado com sucesso");
                        txt_Nome.setText("");
                        txt_RG.setText("");
                        txt_DN.setText("");
                        txt_Endereco.setText("");
                        txt_Numero.setText("");
                        txt_Complemento.setText("");
                        txt_Bairro.setText("");
                        txt_Cidade.setText("");
                        txt_Telefone.setText("");
                        txt_Celular.setText("");
                        txt_Email.setText("");
                      }
                    catch (Exception meditado)
                    {
                        JOptionPane.showMessageDialog(null, "erro: "+meditado);
                    }
                }
                catch (Exception erro)
                {
                    JOptionPane.showMessageDialog(null, erro);
                }
            }
        });
    }
}

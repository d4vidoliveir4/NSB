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
public class Editar_Funcionario extends JFrame {
        private JLabel lbl_Nome,lbl_RG, lbl_DN, lbl_Endereco, lbl_Numero,lbl_Complemento, lbl_Bairro, lbl_Cidade;
        private JLabel lbl_Telefone,lbl_Celular, lbl_Email;
        private JTextField txt_Nome,txt_RG, txt_DN, txt_Endereco, txt_Numero, txt_Complemento, txt_Bairro, txt_Cidade;
        private JTextField txt_Telefone, txt_Celular, txt_Email;
        private JButton bt_Cadastrar, bt_Cancelar;
        private JLabel lbl_Login, lbl_Senha;
        private JTextField txt_Login;
        private JPasswordField ps_Senha;
        public PreparedStatement statement;
        public ResultSet resultSet;
        public Connection connection;
        String login,senha;
        private static String  con_usuario, con_senha,  URL;
        private static String DRIVER = "com.mysql.jdbc.Driver";

    public void Editar_Funcionario(){
        inicializarComponentes();
        CarregaFunc();
        definirEventos();
    }
    private void inicializarComponentes()
    {
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
        catch (Exception erro){
            JOptionPane.showMessageDialog(null, erro);
        }
        setBounds(0,0,800,600);
        setLayout(null);
        setTitle("NSB - Editar Funcionarios");
        setVisible(true);
        getContentPane().setBackground(Color.getHSBColor(0.650f,0.158f,0.750f));
        lbl_Nome = new JLabel("Nome:"); lbl_RG = new JLabel("RG:");   lbl_Endereco = new JLabel("Endereco:"); lbl_Numero = new JLabel("Número:");
        lbl_Complemento = new JLabel("Complemento:"); lbl_Bairro = new JLabel("Bairro:"); lbl_Cidade = new JLabel("Cidade"); lbl_Telefone = new JLabel("Telefone:");
        lbl_Celular = new JLabel("Celular:"); lbl_Email = new JLabel("Email:"); lbl_DN = new JLabel("Nascimento:");

        txt_Nome = new JTextField(); txt_RG = new JTextField();   txt_Endereco = new JTextField(); txt_Numero = new JTextField();
        txt_Complemento = new JTextField(); txt_Bairro = new JTextField(); txt_Cidade = new JTextField(); txt_Telefone = new JTextField();
        txt_Celular = new JTextField(); txt_Email = new JTextField(); txt_DN = new JTextField();
        txt_RG.setEnabled(false);
        txt_DN.setEnabled(false);
        
        bt_Cadastrar = new JButton("Cadastrar");
        bt_Cancelar = new JButton("Cancelar");

        lbl_Login = new JLabel("Login:");
        lbl_Senha = new JLabel("Senha:");
        txt_Login = new JTextField();
        ps_Senha = new JPasswordField();
        txt_Login.setEnabled(false);
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
	bt_Cadastrar.setBounds(620,350,150,40);
	add(bt_Cadastrar);
        bt_Cancelar.setBounds(620, 400, 150, 40);
        add(bt_Cancelar);
    }
    private void definirEventos()
    {
        bt_Cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                Editar_Funcionario.this.dispose();
            }
        });
         bt_Cadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                String str_Nome, str_Celular, str_Email;
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
                
                str_Nome = txt_Nome.getText();
                str_Celular = txt_Celular.getText();
                str_Email = txt_Email.getText();
                str_Endereco = txt_Endereco.getText();
                str_Numero = txt_Numero.getText();
                str_Complemento =txt_Complemento.getText();
                str_Bairro = txt_Bairro.getText();
                str_Cidade = txt_Cidade.getText();
                str_Telefone = txt_Telefone.getText();
               if(JOptionPane.showInputDialog("Digite sua senha.")!=senha){
                     JOptionPane.showMessageDialog(null,"Senha incorreta, você não pode fazer essas alterações!");
                     return;
                }
                String SQL_CADASTRA_FUNCIONARIO = "UPDATE `funcionario` SET `nome`='"+str_Nome+"',`rua`='"+str_Endereco+"',`numero`='"+str_Numero+"',`complemento`='"+str_Complemento+"',`bairro`='"+str_Bairro+"',`telefone`='"+str_Telefone+"',`celular`='"+str_Celular+"',`email`='"+str_Email+"',`cidade`='"+str_Cidade+"' WHERE login= '"+login+"'";
                System.out.println(SQL_CADASTRA_FUNCIONARIO);
                    try
                    {
                        statement.executeUpdate(SQL_CADASTRA_FUNCIONARIO);
                        JOptionPane.showMessageDialog(null, "Informações atualizadas com sucesso");
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
    public void CarregaFunc(){
      try{  
        login=JOptionPane.showInputDialog("Digite o usuario a ser alterado");
        String sql = "Select * from funcionario where login= '"+login+"'";
        System.out.println(sql);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery(sql);
        resultSet.first();
        txt_Nome.setText(resultSet.getString("nome"));
        txt_RG.setText(resultSet.getString("RG"));
        txt_DN.setText(resultSet.getString("data_nasc"));
        txt_Endereco.setText(resultSet.getString("rua"));
        txt_Numero.setText(resultSet.getString("numero"));
        txt_Complemento.setText(resultSet.getString("complemento"));
        txt_Bairro.setText(resultSet.getString("bairro"));
        txt_Cidade.setText(resultSet.getString("cidade"));
        txt_Telefone.setText(resultSet.getString("telefone"));
        txt_Celular.setText(resultSet.getString("celular"));
        txt_Email.setText(resultSet.getString("email"));
        txt_Login.setText(resultSet.getString("login"));
        senha=resultSet.getString("senha");
      }
      catch(Exception asd){
          JOptionPane.showMessageDialog(null,asd);
      }
    }
}

package biblioteca;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.*;
public class Editar_usuario extends JFrame {
    private JLabel lbl_Nome,lbl_RG, lbl_RM,lbl_DN,lbl_Curso,lbl_Serie,lbl_Periodo,lbl_Endereco,lbl_Numero,lbl_Complemento,lbl_Bairro,lbl_Cidade,lbl_Telefone,lbl_Celular,lbl_Email;
    private JTextField txt_Nome, txt_RG,txt_RM,txt_DN,txt_Curso,txt_Serie,txt_Periodo,txt_Endereco,txt_Numero, txt_Complemento, txt_Bairro, txt_Cidade,txt_Telefone,txt_Celular,txt_Email;
    private JButton bt_Cadastrar,bt_Cancelar;
    private static String  con_usuario, con_senha, URL;
    private static String DRIVER = "com.mysql.jdbc.Driver";
    public Connection connection;
    private JCheckBox ativo;
    public PreparedStatement statement;
    public ResultSet resultSet;
    public String rm;
    String nome,rg,srm,dn,curso,serie,periodo,rua,numero,compl,bairro,cidade,telefone,celular,email;    
    private void inicializarComponentes(){
         //conecta BD
        Conexao conecta= new Conexao();
        URL = conecta.URL();
        con_usuario=conecta.con_usuario();
        con_senha= conecta.con_senha();
        setBounds(0,0,800,600);
        setLayout(null);
        setTitle("NSB - Editar usuario");
        getContentPane().setBackground(Color.getHSBColor(0.650f,0.158f,0.750f));
        txt_Nome = new JTextField();
        txt_Curso = new JTextField();
        txt_Numero = new JTextField();
        txt_RG = new JTextField();
        txt_Serie = new JTextField();
        txt_Complemento = new JTextField();
        txt_RM = new JTextField();
        txt_Periodo = new JTextField(); 
        txt_Bairro = new JTextField();
        txt_DN = new JTextField();
        txt_Endereco = new JTextField();
        txt_Cidade = new JTextField();
        txt_Telefone = new JTextField();
        txt_Celular = new JTextField();
        txt_Email = new JTextField();
        lbl_Nome = new JLabel("Nome:");
        lbl_Curso = new JLabel("Curso:");
        lbl_Numero = new JLabel("Número:");
        lbl_RG = new JLabel("RG:"); 
        lbl_Serie = new JLabel("Série:");
        lbl_Complemento = new JLabel("Complemento:");
        lbl_RM = new JLabel("RM:");  
        lbl_Periodo = new JLabel("Periodo:");
        lbl_Bairro = new JLabel("Bairro:");
        lbl_DN = new JLabel("DN:"); 
        lbl_Endereco = new JLabel("Endereço:");
        lbl_Cidade = new JLabel("Cidade:");
        lbl_Telefone = new JLabel("Telefone:");
        lbl_Celular = new JLabel("Celular:");
        lbl_Email = new JLabel("Email:");
        bt_Cadastrar = new JButton("Cadastrar");
        bt_Cancelar = new JButton("Cancelar");
        ativo = new JCheckBox("Desativar cadastro.");
        ativo.setBackground(Color.getHSBColor(0.650f,0.158f,0.750f));
        lbl_Nome.setBounds(18, 39, 80, 25);
        add(lbl_Nome);
        txt_Nome.setBounds(108, 39,500,25);
        add(txt_Nome);
        lbl_RG.setBounds(18, 80,250, 25);
        add(lbl_RG);
        txt_RG.setBounds(108, 80, 240,25);
        add(txt_RG);
        lbl_RM.setBounds(375,80, 80, 25);
        add(lbl_RM); 
        txt_RM.setBounds(415,80, 193,25);
        add(txt_RM);
        lbl_DN.setBounds(18,120,80,  25);
        add(lbl_DN); 
        txt_DN.setBounds(108,120,240, 25);
        add(txt_DN); 
        lbl_Curso.setBounds(18, 180, 80,25);
        add(lbl_Curso);
        txt_Curso.setBounds(108, 180,500,25);
        add(txt_Curso);
        lbl_Periodo.setBounds(18,220,80, 25);
        add(lbl_Periodo); 
        txt_Periodo.setBounds(108,220,300,25);
        add(txt_Periodo); 
        lbl_Serie.setBounds(430,220, 80,25);
        add(lbl_Serie); 
        txt_Serie.setBounds(490,220,118,25);
        add(txt_Serie);
        ativo.setBounds(108,250,500,25);
        add(ativo);
        lbl_Endereco.setBounds(18, 290,80, 25);
        add(lbl_Endereco); 
        txt_Endereco.setBounds(108,290,500,25);
        add(txt_Endereco);
        lbl_Numero.setBounds(18,330,80, 25);
        add(lbl_Numero); 
        txt_Numero.setBounds(108,330,100, 25);
        add(txt_Numero);
        lbl_Complemento.setBounds(230 ,330,110, 25);
        add(lbl_Complemento); 
        txt_Complemento.setBounds(345,330,263, 25);
        add(txt_Complemento);
        lbl_Bairro.setBounds(18,370,80, 25);
        add(lbl_Bairro); 
        txt_Bairro.setBounds(108,370,500, 25);
        add(txt_Bairro); 
        lbl_Cidade.setBounds(18,410,80, 25);
        add(lbl_Cidade);
        txt_Cidade.setBounds(108,410,500,25);
        add(txt_Cidade);
        lbl_Telefone.setBounds(18, 470,100,25);
        add(lbl_Telefone); 
        txt_Telefone.setBounds(108,470,200, 25);
        add(txt_Telefone); 
        lbl_Celular.setBounds(340,470,80, 25);
        add(lbl_Celular); 
        txt_Celular.setBounds(410,470,197, 25);
        add(txt_Celular);
        lbl_Email.setBounds(18,510,80, 25);
        add(lbl_Email); 
        txt_Email.setBounds(108,510,500,25);
        add(txt_Email);
        bt_Cadastrar.setBounds(620,400,150,40);
        add(bt_Cadastrar);
        bt_Cancelar.setBounds(620,450,150,40);
        add(bt_Cancelar);
   }
   public void Editar_usuario(){
       inicializarComponentes();
       carregainfo();
       DefinirEventos();
   }
   public void DefinirEventos(){  
       bt_Cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                Editar_usuario.this.dispose();
            }
        });
       bt_Cadastrar.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e){
           int desativado;
                if(ativo.isSelected()==true)
                    desativado=1;
                else
                    desativado=0;
            String str_Nome,str_Curso, str_Serie, str_Periodo, str_Endereco;
            String str_Numero, str_Complemento, str_Bairro, str_Cidade, str_Telefone, str_Celular, str_Email;
            if (txt_Nome.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Digite o nome do usuario");
                txt_Nome.requestFocus();
                return;
            }
            if(txt_RG.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Digite o RG do usuario");
                txt_RG.requestFocus();
                return;
            }
            if(txt_RM.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Digite o RM do usuario");
                txt_RM.requestFocus();
                return;
            }
            if(txt_DN.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Digite a data de nascimento do usuario");
                txt_DN.requestFocus();
                return;
            }
            if(txt_Curso.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Digite o curso do usuario");
                txt_Curso.requestFocus();
                return;
            }
            if(txt_Serie.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Digite a série do usuario");
                txt_Serie.requestFocus();
                return;
            }
            if(txt_Periodo.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Digite o periodo do usuario");
                txt_Periodo.requestFocus();
                return;
            }
            if(txt_Endereco.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Digite o endereço do usuario");
                txt_Endereco.requestFocus();
                return;
            }
            if(txt_Numero.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Digite o Numero do endereço do usuario");
                txt_Numero.requestFocus();
                return;
            }
            if(txt_Complemento.getText().equals("")){
                str_Complemento = "";
            }
            if(txt_Bairro.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Digite o Bairro do usuario");
                txt_Bairro.requestFocus();
                return;
            }
            if(txt_Cidade.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Digite o cidade do usuario");
                txt_Cidade.requestFocus();
                return;
            }
            if(txt_Email.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Digite o Email do usuario");
                txt_Email.requestFocus();
                return;
            }
            str_Nome = txt_Nome.getText();
            str_Curso = txt_Curso.getText();
            str_Serie = txt_Serie.getText();
            str_Periodo = txt_Periodo.getText();
            str_Endereco= txt_Endereco.getText();
            str_Numero = txt_Numero.getText();
            str_Complemento = txt_Complemento.getText();
            str_Bairro = txt_Bairro.getText();
            str_Cidade = txt_Cidade.getText();
            str_Telefone = txt_Telefone.getText();
            str_Celular = txt_Celular.getText();
            str_Email = txt_Email.getText();              
            try{
               Class.forName(DRIVER);
               Connection connection = DriverManager.getConnection(URL, con_usuario, con_senha);
               Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
               connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
               try{
                   String SQL_CADASTRA_USUARIO = "UPDATE `usuario` SET `nome`='"+str_Nome+"',`curso`='"+str_Curso+"',`periodo`='"+str_Periodo+"',`serie`='"+str_Serie+"',`numero`='"+str_Numero+"',`complemento`='"+str_Complemento+"',`bairro`='"+str_Bairro+"',`cidade`='"+str_Cidade+"',`telefone`='"+str_Telefone+"',`celular`='"+str_Celular+"',`email`='"+str_Email+"',`rua`='"+str_Endereco+"', desativado="+desativado+" WHERE rm='"+rm+"'";
                   statement.executeUpdate(SQL_CADASTRA_USUARIO);
                   JOptionPane.showMessageDialog(null, "Informações alteradas com sucesso");
                   txt_Nome.setText("");
                   txt_RG.setText("");
                   txt_RM.setText("");
                   txt_DN.setText("");
                   txt_Curso.setText("");
                   txt_Serie.setText("");
                   txt_Periodo.setText("");
                   txt_Endereco.setText("");
                   txt_Numero.setText("");
                   txt_Complemento.setText("");
                   txt_Bairro.setText("");
                   txt_Cidade.setText("");
                   txt_Telefone.setText("");
                   txt_Celular.setText("");
                   txt_Email.setText("");
               }
               catch (Exception erropremeditado){
                     System.out.println("Erro: "+erropremeditado);
                     JOptionPane.showMessageDialog(null, "");
               }
          }
          catch (Exception errox){
                System.out.println("Erro: "+errox);
          }
      }
  });
  }
    public void carregainfo(){
    try{
       rm=JOptionPane.showInputDialog("Digite o RM a ser Editado");
       Class.forName(DRIVER);
       Connection connection = DriverManager.getConnection(URL, con_usuario, con_senha);                    
       Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
       connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String sql = "select * from usuario where RM='"+rm+"'";
                System.out.println(sql);
                resultSet=statement.executeQuery(sql);
                resultSet.first();
                 nome=resultSet.getString("nome");
                 rg=resultSet.getString("rg");
                 srm=resultSet.getString("rm");
                 dn=resultSet.getString("data_nasc");
                 curso=resultSet.getString("curso");
                 serie=resultSet.getString("serie");
                 periodo=resultSet.getString("periodo");
                 rua=resultSet.getString("rua");
                 numero=resultSet.getString("numero");
                 compl=resultSet.getString("complemento");
                 bairro=resultSet.getString("bairro");
                 cidade=resultSet.getString("cidade");
                 telefone=resultSet.getString("telefone");
                 celular=resultSet.getString("celular");
                 email=resultSet.getString("email");
                 txt_Nome.setText(nome);                 
                 txt_RG.setText(rg);
                 txt_RM.setText(srm);
                 txt_DN.setText(dn);
                 txt_Curso.setText(curso);
                 txt_Serie.setText(serie);
                 txt_Periodo.setText(periodo);
                 txt_Endereco.setText(rua);
                 txt_Numero.setText(numero);
                 txt_Complemento.setText(compl);
                 txt_Bairro.setText(bairro);
                 txt_Cidade.setText(cidade);
                 txt_Telefone.setText(telefone);
                 txt_Celular.setText(celular);
                 txt_Email.setText(email);
                 txt_RM.setEnabled(false);
                 txt_RG.setEnabled(false);
                 txt_DN.setEnabled(false);
                 setVisible(true);
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(null,"erro:"+ e);
       }
    }

}

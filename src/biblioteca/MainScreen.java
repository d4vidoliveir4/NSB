package biblioteca;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JOptionPane;
public class MainScreen extends JFrame {
    public Connection connection;
   public static String DRIVER = "com.mysql.jdbc.Driver";
   public static String con_usuario, con_senha, URL, con_porta, con_bd, con_pc;
    private JDesktopPane contentPane;
    private JMenuBar mnBarra;
    private JMenu mnCadastro,mnEditar,mnSobre;
    private JMenuItem miEditarU,miObra, miUsuario, miFuncionario, miDevolucao, miEmprestimo, miAlterarLivro,miEFunc,miMulta;
    private JMenu mnConsulta;
    private JMenuItem miCObra, miCUsuario,miEtiquetas,miBarra,miOpcoes;
    private JMenuItem miBS, miContato,miLogout,miBackup,miRestaurar;
    private JMenu mnSair, mnRelaorios;
    private JMenuItem miSair,miRelatorios;
    private JButton bt_emprestar,bt_Devolver, bt_NLivro, bt_NUsuario, bt_Reserva,bt_consultar;
    public ResultSet resultSet1;
    public PreparedStatement statement1;
    public JLabel lbl_TL,lbl_tt,lbl_bemvindo,lbl_bemvindo2;
    public JLabel lbl_Total_Livros, lbl_Total_Reservas, lbl_Total_Emprestimo, lbl_Multa;
    public JTextField txt_Total, txt_Total_Reservas, txt_Total_Emprestimo;
    public void MainScreen(){       
        inicializarEventos();
        definirEventos();
        
    }
     private void inicializarEventos(){
        contentPane = new JDesktopPane();
        setContentPane(contentPane);
        setTitle("NSB");
        setBounds(0,0,800,600);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.getHSBColor(0.650f,0.158f,0.750f));
        mnBarra = new JMenuBar();
        mnEditar= new JMenu("Editar");
        mnSobre= new JMenu("Sobre");
        miBS = new JMenuItem("NSB");
        miContato = new JMenuItem("Contato");
        mnCadastro = new JMenu("Novo");
        miEtiquetas=new JMenuItem("Etiquetas");
        miUsuario = new JMenuItem("Usuario");
        miEditarU = new JMenuItem("Editar Usuario");
        miObra = new JMenuItem("Obra");
        miEFunc=new JMenuItem("Editar Funcionario");
        miFuncionario = new JMenuItem("Funcionario");
        miDevolucao = new JMenuItem("Devolução");
        miAlterarLivro = new JMenuItem("Alterar Obra");
        miEmprestimo = new JMenuItem("Emprestimo/Reserva");
        miMulta= new JMenuItem("Multa");
        miOpcoes = new JMenuItem("Opções");
        miLogout = new JMenuItem("Logout");
        miBackup = new JMenuItem("Exportar Backup");
        miRestaurar = new JMenuItem("Importar Backup");
        mnRelaorios = new JMenu("Gerar");
        miRelatorios = new JMenuItem("Relatórios");
        miBarra = new JMenuItem("Código de barras");
        mnConsulta = new JMenu("Consulta");
        miCObra = new JMenuItem("Obra");               
        miCUsuario = new JMenuItem("Usuario");  
        mnSair = new JMenu("Sair");
        miSair = new JMenuItem("Sair");
        bt_consultar = new JButton("Consultar livro");
        bt_emprestar = new JButton("Emprestar");
        bt_Devolver = new JButton("Devolver");
        mnCadastro.add(miObra);
        mnCadastro.add(miUsuario);
        mnEditar.add(miEditarU);
        mnCadastro.add(miFuncionario);
        mnEditar.add(miEFunc);
        mnCadastro.add(miEmprestimo);
        mnCadastro.add(miDevolucao);  
        mnConsulta.add(miCObra);
        mnConsulta.add(miCObra);
        mnConsulta.add(miCUsuario);
        mnConsulta.add(miMulta);
        mnSobre.add(miBS);
        mnSobre.add(miContato);
        mnSobre.add(miOpcoes);
        mnRelaorios.add(miRelatorios);
        mnRelaorios.add(miEtiquetas);
        mnRelaorios.add(miBarra);
        mnRelaorios.add(miBackup);
        mnRelaorios.add(miRestaurar);
        mnSair.add(miLogout);
        mnSair.add(miSair);
        mnBarra.add(mnCadastro);
        mnBarra.add(mnEditar);
        mnBarra.add(mnConsulta);
        mnBarra.add(mnRelaorios);
        mnBarra.add(mnSobre);
        mnBarra.add(mnSair);
        setJMenuBar(mnBarra);
        bt_consultar.setBounds(10,50,150,40); 
        bt_emprestar.setBounds(10,100,150,40);
        bt_Devolver.setBounds(10,150,150,40);
        add(bt_consultar);    
        add(bt_emprestar);      
        add(bt_Devolver);
        bt_NLivro = new JButton("Novo livro");
        bt_NLivro.setBounds(10,200,150,40);
        add(bt_NLivro);
        bt_NUsuario = new JButton("Novo Usuario");
        bt_NUsuario.setBounds(10,250,150,40);
        add(bt_NUsuario);
        bt_Reserva = new JButton("Concluir reserva");
        bt_Reserva.setBounds(10,300,150,40);
        add(bt_Reserva);
        lbl_bemvindo = new JLabel("Bem vindo ao NSB, escolha o que você deseja fazer ");
        lbl_bemvindo.setBounds(210,480,400,25);
        add(lbl_bemvindo);
        lbl_bemvindo2 = new JLabel("clicando nos botões ao lado ou no menu acima.");
        lbl_bemvindo2.setBounds(340,500,350,25);
        add(lbl_bemvindo2);
        lbl_TL= new JLabel(new ImageIcon("C:/NSB/Icon.png"));
        lbl_TL.setBounds(0,0,300,300);
        add(lbl_TL);
        lbl_Total_Livros = new JLabel("Total Livros: ");
        lbl_Total_Livros.setBounds(50,400, 80, 25);
         //conecta BD
        Conexao conecta= new Conexao();
        URL = conecta.URL();
        con_usuario=conecta.con_usuario();
        con_senha= conecta.con_senha();
        String sql = "SELECT COUNT( * ) AS total FROM livro";
        try{
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL, "root", "toor");
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.first();
            String total = resultSet.getString("total");
            lbl_Total_Livros = new JLabel("Livros: ");
            lbl_Total_Livros.setBounds(560,50, 110, 25);
            add(lbl_Total_Livros);
            txt_Total = new JTextField(total);
            txt_Total.setBounds(660, 50 , 60, 25);
            add(txt_Total);
            txt_Total.setEditable(false);
            sql = "SELECT COUNT( * ) AS total FROM emprestimo where devolvido <> 'sim' ";
            resultSet = statement.executeQuery(sql);
            resultSet.first();
            total = resultSet.getString("total");
            lbl_Total_Emprestimo = new JLabel("Emprestimos:");
            lbl_Total_Emprestimo.setBounds(560,110, 130, 25);
            add(lbl_Total_Emprestimo);
            txt_Total_Emprestimo = new JTextField(total);
            txt_Total_Emprestimo.setBounds(660, 110 , 60, 25);
            add(txt_Total_Emprestimo);
            txt_Total_Emprestimo.setEditable(false);
            sql = "SELECT COUNT( * ) AS total FROM reserva where concluida <> 'sim'";
            resultSet = statement.executeQuery(sql);
            resultSet.first();
            total = resultSet.getString("total");
            lbl_Total_Reservas = new JLabel("Reservas:");
            lbl_Total_Reservas.setBounds(560,80, 110, 25);
            add(lbl_Total_Reservas);
            txt_Total_Reservas = new JTextField(total);
            txt_Total_Reservas.setBounds(660, 80 , 60, 25);
            add(txt_Total_Reservas);
            txt_Total_Reservas.setEditable(false);
        }
        catch (Exception erro){
           
        }
    }
    private void definirEventos(){
        miUsuario.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
             Cad_Usuario cadastrarusuario = new Cad_Usuario();
             cadastrarusuario.Cad_Usuario();
            }
        });
        miMulta.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
             Cons_Multa multa= new Cons_Multa();
             multa.Cons_Multa();
            }
        });
        miBackup.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
             Gerar_Backup backup= new Gerar_Backup();
             backup.gerar_backup();
            }
        });
        miRestaurar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
             Restaurar_backup restaurar= new Restaurar_backup();
             restaurar.inicia();
            }
        });
        miFuncionario.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
             Cad_Funcionario cadastrarFuncionario = new Cad_Funcionario();
             cadastrarFuncionario.Cad_Funcionario();
            }
        });
        miEditarU.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
             Editar_usuario edit= new Editar_usuario();
             edit.Editar_usuario();
            }
        });
        miDevolucao.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
             Cad_Devolucao cadastraDevolucao = new Cad_Devolucao();
             cadastraDevolucao.Cad_Devolucao();
            }
        });
        miAlterarLivro.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
            Cons_Obra alterar = new Cons_Obra();
            alterar.Cons_Obra();
            }
        });
        miObra.addActionListener(new ActionListener() {
        public  void actionPerformed(ActionEvent e){
             Cad_Obra cad=new Cad_Obra();
             cad.Cad_Obra();                
            }
        });
        miEFunc.addActionListener(new ActionListener() {
        public  void actionPerformed(ActionEvent e){
             Editar_Funcionario edfunc=new Editar_Funcionario();
             edfunc.Editar_Funcionario();                
            }
        });    
        miLogout.addActionListener(new ActionListener() {
        public  void actionPerformed(ActionEvent e){
            MainScreen.this.dispose();
            Main login = new Main();
            login.inicializarComponentes();
            }
        });    
        miCObra.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
             Cons_Obra consultaObra = new Cons_Obra();
             consultaObra.Cons_Obra();   
            }
        });
        miCUsuario.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
             Cons_Usuario consultaObra = new Cons_Usuario();
             consultaObra.Cons_Usuario();
            }
        });
        miEmprestimo.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
             Cad_Emprestimo1 emprestar = new Cad_Emprestimo1();
             emprestar.Cad_Emprestimo1();
            }
        });
        miOpcoes.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
             Cad_Opcoes opcoes = new Cad_Opcoes();
             opcoes.Cad_Opcoes();
             MainScreen.this.dispose();
            }
        });
        miRelatorios.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
             relatorio re = new relatorio();
             re.relatorio();
            }
        });
        miSair.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
             System.exit(0);
            }
        });
        miEtiquetas.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
             Imprimir_Etiquetas etiquetas= new Imprimir_Etiquetas();
             etiquetas.carregaLivros();
            }
        });
        miBarra.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
             Imprimir_CodigoBarra barra= new Imprimir_CodigoBarra();
             barra.carregaLivros();
            }
        });
        miBS.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null,"NSB 2.0 é um software de controle para biblioteca. Desenvolvido em\n"
                                                 + "Java com banco de dados em MySQL.");
                                                
            }
        });
        miContato.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null,"Para entrar em contato com os desenvolvedores envie um e-mail para:\n"
                                                 + "davidoliveira564@gmail.com.\n"
                                                 + "Seu e-mail sera respondido o mais rapido possivel!");
            }
        });
        bt_consultar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
                Cons_Obra consultaObra = new Cons_Obra();
                consultaObra.Cons_Obra();
            }
        });
        bt_emprestar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
             Cad_Emprestimo1 emprestar = new Cad_Emprestimo1();
             emprestar.Cad_Emprestimo1();
            }
        });
        bt_Devolver.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
             Cad_Devolucao cadastraDevolucao = new Cad_Devolucao();
             cadastraDevolucao.Cad_Devolucao();
            }
        });
        bt_NLivro.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
             Cad_Obra cad=new Cad_Obra();
             cad.Cad_Obra();   
            }
        });
        bt_NUsuario.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            Cad_Usuario cadastrarusuario = new Cad_Usuario();
            cadastrarusuario.Cad_Usuario();
           }
        });
        bt_Reserva.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            Cons_Reserva consultar = new Cons_Reserva();
            consultar.Carregacr();
            }
        });
    }
 }
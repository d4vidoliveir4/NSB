package biblioteca;
import java.io.*;
import java.awt.event.*;
import java.sql.*;
import java.util.GregorianCalendar;
import javax.swing.table.*;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.util.Calendar;
public class Cad_Emprestimo1 extends JFrame {
    //declaração dos objetos
        private MaskFormatter ms_Para;
        String T;
        private JPasswordField txt_Senha, txt_Senha1;
        private JFormattedTextField txt_Para;
        private JTextField txt_Consulta;
        private JButton bt_Consultar;
        public javax.swing.JTable tabela;
        private javax.swing.JScrollPane st;
        public Connection connection;
        public PreparedStatement statement;
        public ResultSet resultSet;
        public static String DRIVER = "com.mysql.jdbc.Driver";
        public static String con_usuario, con_senha, URL, con_porta, con_bd, con_pc;
        private JLabel lbl_Tombo, lbl_RM, lbl_Para, lbl_Usuario, lbl_Senha;
        private JTextField txt_Tombo, txt_RM, txt_Usuario;
        public JButton bt_Fechar, bt_Emprestar, bt_Reservar;
        public JTextField txt_Tombo1, txt_RM1, txt_De1, txt_Para1, txt_Usuario1;
        private JLabel lbl_Tombo1, lbl_RM1,lbl_De1, lbl_Para1, lbl_Usuario1, lbl_Senha1;
        public int usu=1;
        String para,paraf="";
    public void Cad_Emprestimo1(){
        inicializarComponentes();
        definirEventos();
    }
    private void inicializarComponentes(){
        try{
            setLayout(null);
            setTitle("NSB - Cadastro de empréstimos/Reservas");
            setBounds(0,0,1024,700);
            setVisible(true);
            getContentPane().setBackground(Color.getHSBColor(0.650f,0.158f,0.750f));
            //conecta BD
            Conexao conecta= new Conexao();
            URL = conecta.URL();
            con_usuario=conecta.con_usuario();
            con_senha= conecta.con_senha();
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, con_usuario, con_senha);
            System.out.println("Conectou");
            txt_Consulta = new JTextField();
            txt_Consulta.setBounds(20, 10,800, 25);
            add(txt_Consulta);
            bt_Consultar = new JButton("Consultar");
            bt_Consultar.setBounds(830,10,100, 25);
            add(bt_Consultar);
            st=new JScrollPane();
            st.setBounds(18,40,915,300);
            add(st);
            bt_Emprestar = new JButton("Emprestar");
            bt_Emprestar.setBounds(100, 600, 100, 25);
            add(bt_Emprestar);
            bt_Reservar = new JButton("Reservar");
            bt_Reservar.setBounds(210, 600, 100, 25);
            ms_Para = new MaskFormatter("##/##/####");
            lbl_Tombo = new JLabel("Tombo:");
            lbl_RM = new JLabel("RM:"); 
            lbl_Para = new JLabel("Para:");
            lbl_Usuario = new JLabel("Usuario:"); 
            lbl_Senha = new JLabel("Senha:");
            txt_Tombo = new JTextField(); 
            txt_RM = new JTextField();
            txt_Para =  new JFormattedTextField(ms_Para);
            txt_Usuario = new JTextField();
            txt_Senha = new JPasswordField();
            lbl_Tombo.setBounds(18, 350, 80, 25);
            add(lbl_Tombo);
            txt_Tombo.setBounds(108,350, 80, 25);
            add(txt_Tombo);
            lbl_RM.setBounds(18, 380, 80, 25);
            add(lbl_RM);
            txt_RM.setBounds(108, 380, 80, 25);
            add(txt_RM);
            lbl_Para.setBounds(18, 410, 80, 25);
            add(lbl_Para);
            txt_Para.setBounds(108, 410, 80, 25);
            add(txt_Para);
            lbl_Usuario.setBounds(18, 440, 80, 25); 
            add(lbl_Usuario);
            txt_Usuario.setBounds(108, 440, 80, 25); 
            add(txt_Usuario);
            lbl_Senha.setBounds(18, 470, 80, 25);
            add(lbl_Senha);
            txt_Senha.setBounds(108, 470, 80, 25);
            add(txt_Senha);
            add(bt_Reservar);
            /// reserva
            lbl_Tombo1 = new JLabel("Tombo:");
            lbl_RM1 = new JLabel("RM/Login:");
            lbl_De1 = new JLabel("De:");
            lbl_Para1 = new JLabel("Para:");
            lbl_Usuario1 = new JLabel("Usuario:"); 
            lbl_Senha1= new JLabel("Senha:");
            txt_Tombo1 =new JTextField();
            txt_RM1 =new JTextField();
            txt_De1 =new JTextField();
            txt_Para1 =new JTextField();
            txt_Usuario1 =new JTextField();
            txt_Senha1 = new JPasswordField();
            lbl_Tombo1.setBounds(218, 350, 80, 25); 
            add(lbl_Tombo1);
            txt_Tombo1.setBounds(308,350, 80, 25);
            add(txt_Tombo1);
            lbl_RM1.setBounds(218, 380, 80, 25); 
            add(lbl_RM1);
            txt_RM1.setBounds(308, 380, 80, 25);
            add(txt_RM1);
            lbl_De1.setBounds(218, 410, 80, 25); 
            add(lbl_De1);
            txt_De1.setBounds(308, 410, 80, 25); 
            add(txt_De1);
            lbl_Para1.setBounds(218, 440, 80, 25);
            add(lbl_Para1);
            txt_Para1.setBounds(308, 440, 80, 25);
            add(txt_Para1);
            lbl_Usuario1.setBounds(218, 470, 80, 25);
            add(lbl_Usuario1);
            txt_Usuario1.setBounds(308, 470, 80, 25);
            add(txt_Usuario1);
            lbl_Senha1.setBounds(218, 500, 80, 25);
            add(lbl_Senha1);
            txt_Senha1.setBounds(308, 500, 80, 25);
            add(txt_Senha1);
            //deixado tudo invisivel
            txt_Tombo.setVisible(false);
            lbl_Tombo.setVisible(false);
            lbl_RM.setVisible(false);
            txt_RM.setVisible(false);
            lbl_Para.setVisible(false);
            txt_Para.setVisible(false);
            lbl_Usuario.setVisible(false);
            txt_Usuario.setVisible(false);
            lbl_Senha.setVisible(false);
            txt_Senha.setVisible(false);
            txt_Tombo1.setVisible(false);
            lbl_Tombo1.setVisible(false);
            lbl_RM1.setVisible(false);
            txt_RM1.setVisible(false);
            lbl_De1.setVisible(false);
            txt_De1.setVisible(false);
            lbl_Para1.setVisible(false);
            txt_Para1.setVisible(false);
            lbl_Usuario1.setVisible(false);
            txt_Usuario1.setVisible(false);
            lbl_Senha1.setVisible(false);
            txt_Senha1.setVisible(false);
            bt_Emprestar.setVisible(false);
            bt_Reservar.setVisible(false);
            txt_Tombo.setEditable(false);
            txt_Tombo1.setEditable(false);
            }
            catch (Exception erro){
            JOptionPane.showMessageDialog(null, erro);
            }
    }
    private void definirEventos(){
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
    bt_Consultar.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
          consultarLivro();
        }
    });
    bt_Emprestar.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
        try{ 
           String sql_checa_id_emprestimo ="select empresta from funcionario where login = '"+txt_Usuario.getText()+"'";
           resultSet = statement.executeQuery(sql_checa_id_emprestimo);
           resultSet.first();
           if(resultSet.getInt("empresta")==0){
               JOptionPane.showMessageDialog(null,"Esse funcionario não pode emprestar livros.\nFavor procurar um funcionario da biblioteca!");
               return;
           }
        }
        catch(Exception ee){
            
        }
            emprestar();         
       }
    });
    bt_Reservar.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
            reservar();     
        }
    });
    }
    private void tabelaMouseClicked(java.awt.event.MouseEvent evt){
        try{
            int x = tabela.getSelectedRow();
            String str_Disponivel = tabela.getValueAt(x, 4).toString();
            if(str_Disponivel.equals("Extraviado")||str_Disponivel.equals("Danificado")){
                JOptionPane.showMessageDialog(null,"Esse livro não esta mais diponivel!\nNão pode ser Emprestado ou Reservado.");
                return;
            }
            if(str_Disponivel.equals("Disponível")){
                txt_Tombo1.setVisible(false);
                lbl_Tombo1.setVisible(false);
                lbl_RM1.setVisible(false);
                txt_RM1.setVisible(false);
                lbl_De1.setVisible(false);
                txt_De1.setVisible(false);
                lbl_Para1.setVisible(false);
                txt_Para1.setVisible(false);
                lbl_Usuario1.setVisible(false);
                txt_Usuario1.setVisible(false);
                lbl_Senha1.setVisible(false);
                txt_Senha1.setVisible(false);
                txt_Tombo.setVisible(true);
                lbl_Tombo.setVisible(true);
                lbl_RM.setVisible(true);
                txt_RM.setVisible(true);
                lbl_Para.setVisible(true);
                txt_Para.setVisible(true);
                lbl_Usuario.setVisible(true);
                txt_Usuario.setVisible(true);
                lbl_Senha.setVisible(true);
                txt_Senha.setVisible(true);
                bt_Emprestar.setVisible(true);
                bt_Reservar.setVisible(false);
                String str_Tombo = tabela.getValueAt(x, 0).toString();
                txt_Tombo.setText(str_Tombo);
            }
            else{
                txt_Tombo1.setVisible(true);
                lbl_Tombo1.setVisible(true);
                lbl_RM1.setVisible(true);
                txt_RM1.setVisible(true);
                lbl_De1.setVisible(true);
                txt_De1.setVisible(true);
                lbl_Para1.setVisible(true);
                txt_Para1.setVisible(true);
                lbl_Usuario1.setVisible(true);
                txt_Usuario1.setVisible(true);
                lbl_Senha1.setVisible(true);
                txt_Senha1.setVisible(true);
                txt_De1.setEditable(false);
                txt_Tombo.setVisible(false);
                lbl_Tombo.setVisible(false);
                lbl_RM.setVisible(false);
                txt_RM.setVisible(false);
                lbl_Para.setVisible(false);
                txt_Para.setVisible(false);
                lbl_Usuario.setVisible(false);
                txt_Usuario.setVisible(false);
                lbl_Senha.setVisible(false);
                txt_Senha.setVisible(false);
                bt_Emprestar.setVisible(false);
                bt_Reservar.setVisible(true);
                String str_Tombo = tabela.getValueAt(x, 0).toString();
                txt_Tombo.setText(str_Tombo);
                reserva();
            }
        }
        catch(Exception erro){
            JOptionPane.showMessageDialog(null, "erro: "+erro);
        }
    }
    
    public void consultarLivro(){
     try{
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        resultSet=statement.executeQuery("Select l.NTOMBO, l.tombo, l.titulo, l.autor , l.disponivel,  l.cutter from livro l where ( disponivel like '%" + txt_Consulta.getText() +"%' or  titulo like '%" + txt_Consulta.getText() +"%' or autor like '%" + txt_Consulta.getText() +"%' or tombo like'%" + txt_Consulta.getText() +"%') order by titulo, tombo, autor");
        DefaultTableModel tableMode1=new DefaultTableModel(new String[]{},0){};
          int qc=resultSet.getMetaData().getColumnCount();
          for(int i=1;i<=qc;i++){
              tableMode1.addColumn(resultSet.getMetaData().getColumnName(i).toUpperCase());
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
     tabela.addMouseListener(new java.awt.event.MouseAdapter() {
     public void mouseClicked(java.awt.event.MouseEvent evt){
             tabelaMouseClicked(evt);
         }
      });
    }
    public void emprestar(){
           String tombo, rm,usuario, senha;
           if(txt_Tombo.getText().equals("")) { JOptionPane.showMessageDialog(null, "Digite o tombo"); txt_Tombo.requestFocus(); return; }
           if(txt_RM.getText().equals("")) {JOptionPane.showMessageDialog(null, "Digite o tombo"); txt_RM.requestFocus(); return;}
           if(txt_Para.getText().equals("")) { JOptionPane.showMessageDialog(null, "Digite o tombo"); txt_Para.requestFocus(); return; }
           if(txt_Usuario.getText().equals("")) { JOptionPane.showMessageDialog(null, "Digite o tombo"); txt_Usuario.requestFocus(); return; }
           if(txt_Senha.getPassword().toString().equals("")) { JOptionPane.showMessageDialog(null, "Digite o tombo"); txt_Senha.requestFocus(); return; }
           int x = tabela.getSelectedRow();
           String str_Tombo = tabela.getValueAt(x, 0).toString();
           tombo = str_Tombo;
           rm = txt_RM.getText();
           int num_linha;
           String sql;
           try{
              sql="Select * from emprestimo where rm='"+rm+"' and devolvido='não'";
              statement = connection.prepareStatement(sql);
              resultSet = statement.executeQuery();
              resultSet.last();
              num_linha = resultSet.getRow();
              resultSet.last();
              if(num_linha > 2 ){
                    JOptionPane.showMessageDialog(null,"Esse aluno já pegou 3 livros, não pode pegar outro livro ainda!");
                    return;
                }
           }
           catch(Exception ee){
               
           }
           try{
              sql="Select * from emprestimo where rm='"+rm+"' and devolvido='não'and para< NOW()";
              statement = connection.prepareStatement(sql);
              resultSet = statement.executeQuery();
              resultSet.last();
              num_linha = resultSet.getRow();
              resultSet.last();
              if(num_linha > 0){
                    JOptionPane.showMessageDialog(null,"Esse aluno esta devendo um livro, não pode pegar outro livro!");
                    return;
                }
           }
           catch(Exception ee){
               
           }
           usuario = txt_Usuario.getText();
           senha = txt_Senha.getText();
           sql = "select id_funcionario from funcionario where login = '"+usuario+"' and senha= '"+senha+"' and empresta = 1";
           System.out.println(sql);
           try{
              statement = connection.prepareStatement(sql);
              resultSet = statement.executeQuery();
              resultSet.last();
              num_linha = resultSet.getRow();
              resultSet.first();
              if(num_linha > 0){
                    String id_funcionario = resultSet.getString("id_funcionario");
                    String sql_busca_id_livro = "select tombo,id_livro from livro where NTOMBO = '"+tombo+"' and disponivel = 'Disponível'";
                    System.out.println(sql_busca_id_livro);
                    resultSet = statement.executeQuery(sql_busca_id_livro);
                    resultSet.last();
                    num_linha = resultSet.getRow();
                    resultSet.first();
                    String id_usuario, id_livro;
                    if(num_linha > 0){
                         T = resultSet.getString("tombo");
                        id_livro = resultSet.getString("id_livro");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "ou o livro não existe, ou o seu tombo está digitado incorretamente");
                        return;
                    }
                    String sql_busca_id_usuario = "select * from usuario where rm = '"+rm+"' and desativado= 0";
                    System.out.println(sql_busca_id_usuario);
                    resultSet = statement.executeQuery(sql_busca_id_usuario);
                    resultSet.last();
                    num_linha = resultSet.getRow();
                    resultSet.first();
                    String nome = "";
                    if(num_linha > 0){
                        id_usuario = resultSet.getString("id_usuario");
                         nome = resultSet.getString("nome");
                         usu=1;
                    }
                    else{
                    String sql_busca_id_funcionario = "select * from funcionario where login = '"+rm+"'";
                    System.out.println(sql_busca_id_funcionario);
                    resultSet = statement.executeQuery(sql_busca_id_funcionario);
                    resultSet.last();
                    num_linha = resultSet.getRow();
                    resultSet.first();
                    nome = "";
                    if(num_linha > 0){
                        id_usuario = resultSet.getString("id_funcionario");
                         nome = resultSet.getString("nome");
                         usu=2;
                    }
                    else{                        
                        JOptionPane.showMessageDialog(null, "ou o RM/Login digitado não existe, ou o RM foi digitado errado ou o usuario foi desativado.");
                        return;
                        }
                    }
                    carregapara();
                    String sql_Verifica = "select * from emprestimo where id_usuario = "+id_usuario+" and devolvido = 'não'";
                    resultSet = statement.executeQuery(sql_Verifica);
                    resultSet.last();
                    num_linha = resultSet.getRow();
                    resultSet.first();
                    if(num_linha >= 3){
                        JOptionPane.showMessageDialog(null, "Este usuario Ja possui 3 livros emprestados!!!\nDevolva pelo menos um livro para poder pegar outro");
                        return;
                    }
                    int id_emprestimo = (int) (Math.random() * 1000000000);
                    String sql_checa_id_emprestimo ="select id_emprestimo from emprestimo where id_emprestimo = "+id_emprestimo;
                    resultSet = statement.executeQuery(sql_checa_id_emprestimo);
                    resultSet.last();
                    num_linha = resultSet.getRow();
                    resultSet.first();
                    while(num_linha > 0){
                        id_emprestimo = (int) (Math.random() * 1000000000);
                        sql_checa_id_emprestimo = "select id_emprestimo from emprestimo where id_emprestimo = "+id_emprestimo;
                        resultSet = statement.executeQuery(sql_checa_id_emprestimo);
                        resultSet.last();
                        num_linha = resultSet.getRow();
                        resultSet.first();
                    }
                    String[] str_Data = para.split("/");
                    String data_para = paraf;
                    //continuar sql paara cadastro do emprestimo;
                    GregorianCalendar calendar = new GregorianCalendar();
                    int dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);
                    int mes = calendar.get(GregorianCalendar.MONTH);
                    int ano = calendar.get(GregorianCalendar.YEAR);
                    int int_hora = calendar.get(GregorianCalendar.HOUR_OF_DAY);
                    int int_min = calendar.get(GregorianCalendar.MINUTE);
                    int int_sec = calendar.get(GregorianCalendar.SECOND);
                    String data_de_hoje = ano+"-"+mes+"-"+dia+" "+int_hora+":"+int_min+":"+int_sec;
                    String sql2 = "insert into emprestimo (id_emprestimo, id_livro, id_usuario, id_funcionario, de, para,caminho) values("+id_emprestimo+","+id_livro+", "+id_usuario
                                  + ","+id_funcionario+", '"+data_de_hoje+"', '"+data_para+"','C:/NSB/imprimir/"+id_emprestimo+"Imprimir.txt' )";
                    System.out.println(sql2);
                    statement.executeUpdate(sql2);
                    String sql3 = "update livro set disponivel = 'Não Disponível' where id_livro = "+id_livro;
                    statement.executeUpdate(sql3);
                    JOptionPane.showMessageDialog(null, "livro emprestado para: "+nome+"\n RM : "+rm);
                    consultarLivro();
                     //gravar
                    System.out.println(T);
          try{
            PrintWriter out = new PrintWriter("C:/NSB/imprimir/"+id_emprestimo+"Imprimir.txt");
            out.println("      CEET Paula Souza      ");
            out.println("   Biblioteca Jorge Street  ");
            out.println("----------------------------");
            out.println("  Conprovante de Emprestimo ");
            out.println("Livro tombo :"+ T);
            out.println("RM :"+ txt_RM.getText());
            out.println("Aluno :"+ nome);
            out.println("Devolver dia:" +txt_Para.getText());
            out.println("Funcionario responsavel:"+ txt_Usuario.getText());
            out.println("Assintura do Aluno:");
            out.println("______________________");
            out.println("Assintura do Funcionario:");
            out.println("______________________");
            out.println(" ");
            out.println("-------------------------------------");
            out.println("Em caso de atraso na devolução será ");
            out.println("cobrado multa de R$1,00 por dia de atraso.");
            out.close();
            JOptionPane.showMessageDialog(null, "Arquivo gravado com sucesso!\n C:/biblioteca/imprimir/"+id_emprestimo+"Imprimir.txt");
            try{  
                Runtime.getRuntime().exec("cmd.exe /c start notepad.exe C:/NSB/imprimir/"+id_emprestimo+"Imprimir.txt");  
            }  
            catch(IOException iOException){  
                JOptionPane.showMessageDialog(null,iOException); 
                JOptionPane.showMessageDialog(null,"Tente abrir o arquivo manualmente");
               }  
            }   
        catch(IOException erro){
          JOptionPane.showMessageDialog(null, "Erro ao gravar no arquivo");
        }
            }
            else{
                JOptionPane.showMessageDialog(null, "Usuario e/ou senha incorreto(s)");
              }
           }
           catch(Exception erro){
               JOptionPane.showMessageDialog(null, erro);
           }
    }
    public void reserva(){
        //verificaçoes
        int x = tabela.getSelectedRow();
        String str_Tombo = tabela.getValueAt(x, 0).toString();
        String tombo = str_Tombo;
        txt_Tombo1.setText(tombo);
               try{
                  Class.forName(DRIVER);
                  connection = DriverManager.getConnection(URL, con_usuario, con_senha);
                  System.out.println("Conectou");
                  Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                  connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                  String sql = "select id_livro from livro where NTOMBO = '"+tombo+"'";
                  System.out.println(sql);
                  statement = connection.prepareStatement(sql);
                  resultSet = statement.executeQuery(sql);
                  resultSet.first();
                  String id_livro = resultSet.getString("id_livro");
                  sql = "select de, para from reserva where id_livro = "+id_livro+" order by para desc limit 0,1";
                  System.out.println(sql);
                  resultSet = statement.executeQuery(sql);
                  resultSet.last();
                  int num_linha = resultSet.getRow();
                  System.out.println("número de reservas: "+ num_linha);
                  resultSet.first();
                  if(num_linha > 0){
                        String[] data = resultSet.getString("para").split("-");
                        txt_De1.setText(data[2]+"/"+data[1]+"/"+data[0]);
                    }
                    else{
                        sql = "select para from emprestimo where id_livro =  "+id_livro;
                        System.out.println(sql);
                        resultSet = statement.executeQuery(sql);
                        resultSet.first();
                        String[] data = resultSet.getString("para").split("-");
                        txt_De1.setText(data[2]+"/"+data[1]+"/"+data[0]);
                    }
               }
               catch (Exception erro){
                   JOptionPane.showMessageDialog(null, erro);
               }
    }
    public void reservar(){
         String str_Tombo, str_RM, str_De, str_Para, str_Usuario, str_Senha;
                str_Tombo = txt_Tombo1.getText();
                str_RM = txt_RM1.getText();
                str_De = txt_De1.getText();
                str_Para = txt_Para1.getText();
                str_Usuario = txt_Usuario1.getText();
                str_Senha = txt_Senha1.getText();
                String[] de2 = str_De.split("/");
                String de = de2[2]+"-"+de2[1]+"-"+de2[0];
                String[] para2 = str_Para.split("/");
                String para = para2[2]+"-"+para2[1]+"-"+para2[0];
                if(str_Tombo.equals("")||str_RM.equals("")||str_De.equals("")||str_Para.equals("")) return;
                // parei nessa exata linha
                String sql = "select id_funcionario from funcionario where login = '"+str_Usuario+"' and senha= '"+str_Senha+"'";
           System.out.println(sql);
           try{
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, con_usuario, con_senha);
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                resultSet.last();
                int num_linha = resultSet.getRow();
                resultSet.first();
                if(num_linha > 0){
                    String id_funcionario = resultSet.getString("id_funcionario");
                    String sql_busca_id_livro = "select id_livro from livro where NTOMBO = '"+str_Tombo+"' and (disponivel = 'não Disponível' or disponivel = 'reservado')";
                    System.out.println(sql_busca_id_livro);
                    resultSet = statement.executeQuery(sql_busca_id_livro);
                    resultSet.last();
                    num_linha = resultSet.getRow();
                    resultSet.first();
                    String id_usuario, id_livro;
                    if(num_linha > 0){
                        id_livro = resultSet.getString("id_livro");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "ou o livro não existe, ou o seu tombo está digitado incorretamente");
                        return;
                    }
                    String sql_busca_id_usuario = "select * from usuario where rm = '"+str_RM+"'";
                    System.out.println(sql_busca_id_usuario);
                    resultSet = statement.executeQuery(sql_busca_id_usuario);
                    resultSet.last();
                    num_linha = resultSet.getRow();
                    resultSet.first();
                    String nome = "";
                    if(num_linha > 0){
                        id_usuario = resultSet.getString("id_usuario");
                         nome = resultSet.getString("nome");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "ou o RM digitado não existe, ou o RM foi digitado errado");
                        return;
                    }
                    int id_emprestimo = (int) (Math.random() * 1000000000);
                    String sql_checa_id_emprestimo ="select id_emprestimo from emprestimo where id_emprestimo = "+id_emprestimo;
                    resultSet = statement.executeQuery(sql_checa_id_emprestimo);
                    resultSet.last();
                    num_linha = resultSet.getRow();
                    resultSet.first();
                    while(num_linha > 0){
                        id_emprestimo = (int) (Math.random() * 1000000000);
                        sql_checa_id_emprestimo = "select id_emprestimo from emprestimo where id_emprestimo = "+id_emprestimo;
                        resultSet = statement.executeQuery(sql_checa_id_emprestimo);
                        resultSet.last();
                        num_linha = resultSet.getRow();
                        resultSet.first();
                    }
                String sql_Final = "insert into reserva (id_livro, id_usuario, id_funcionario, de, para) values ("+id_livro+", "+id_usuario+","+id_funcionario+",'"+de+"', '"+para+"') ";
                System.out.println(sql_Final);
                statement.executeUpdate(sql_Final);
                JOptionPane.showMessageDialog(null, "Reserva feita: "+nome+"\ndo dia: "+de+"\npara o dia: "+para);
            }
            else{
                JOptionPane.showMessageDialog(null, "Usuario e/ou senha incorretos");
            }
        }
           catch(Exception erro){
               
           }
    }
    public void carregapara(){
            Calendar calendario = Calendar.getInstance();
            int mes = calendario.get(Calendar.MONTH)+1;
            int dia = calendario.get(Calendar.DAY_OF_MONTH);
            int ano = calendario.get(Calendar.YEAR);
            if(usu==1){
                Conta_Dias teste = new Conta_Dias();  
                para=(teste.somaData(ano+"-"+mes+"-"+dia, 7)); 
            }
            else{
                Conta_Dias teste = new Conta_Dias();  
                para=(teste.somaData(ano+"-"+mes+"-"+dia, 15));                 
            }
       txt_Para.setText(para);
       JOptionPane.showMessageDialog(null,"O livro deverá ser devolvido dia: "+para);
       paraf=paraf+para.charAt(6);
       paraf=paraf+para.charAt(7);
       paraf=paraf+para.charAt(8);
       paraf=paraf+para.charAt(9);
       paraf=paraf+"-";
       paraf=paraf+para.charAt(3);
       paraf=paraf+para.charAt(4);
       paraf=paraf+"-";
       paraf=paraf+para.charAt(0);
       paraf=paraf+para.charAt(1);
       System.out.println(paraf);
    }
}
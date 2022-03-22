package biblioteca;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.util.Calendar;
public class Cons_Obra extends JFrame {
    //declaração dos objetos
        String str_valor;
        private JTextField txt_Consulta;
        private JDesktopPane contentPane;
        private JButton bt_Consultar;
        public javax.swing.JTable tabela;
        private javax.swing.JScrollPane st;
        public Connection connection;
        public PreparedStatement statement, sta;
        public ResultSet resultSet;
        public static String DRIVER = "com.mysql.jdbc.Driver";
        public String id_livro,id_usuario,str_Status;
        public static String con_usuario, con_senha, URL, con_porta, con_bd, con_pc;
        public JLabel lbl_Titulo, lbl_Tipo, lbl_Editora, lbl_ISBN, lbl_Publicacao;
        public JLabel lbl_emprestado_para,lbl_TO, lbl_NTOMBO;
        public JLabel lbl_Autor, lbl_Preco, lbl_Ano, lbl_Volume, lbl_Tombo, lbl_Cutter, lbl_Paginas,lbl_serie,lbl_cdd;
        public JLabel lbl_Categoria, lbl_SubCategoria, lbl_OBS, lbl_Cod,lbl_dica,lbl_nota,lbl_order,lbl_colecao,lbl_ex;
        public JTextField txt_Titulo, txt_Tipo, txt_Editora, txt_ISBN, txt_Publicacao, txt_Cod,txt_serie,txt_assuntos,txt_tradutor,txt_edicao;
        public JTextField txt_Autor, txt_Preco, txt_Ano, txt_Volume, txt_Tombo, txt_Cutter, txt_Paginas,txt_cdd,txt_tituloori;
        public JTextField txt_Categoria, txt_SubCategoria, txt_OBS,txt_NTOMBO,txt_nota,txt_ex;
        public JTextField txt_emprestado_para,txt_colecao;
        public JComboBox combo_order,combo_Editora,combo_origem;
        public JButton bt_Fechar,bt_deletar,bt_editar,bt_concluir,bt_E,bt_D,bt_R,bt_novo,bt_cadastrar;
        int NTOMBO, exemplar;
        String str_Titulo="", str_ISBN="";
    public void Cons_Obra(){
        inicializarComponentes();
        definirEventos();

    }
    private void inicializarComponentes(){
        setLayout(null);
        setTitle("NSB - Consulta Obras");
        setBounds(0,0,1024,700);
        setVisible(true);
        getContentPane().setBackground(Color.getHSBColor(0.650f,0.158f,0.750f));
        //instanciaçãoobjetos
        bt_deletar=new JButton();
        txt_Categoria= new JTextField();
        txt_SubCategoria = new JTextField();
        txt_OBS= new JTextField();
        txt_Titulo = new JTextField();
        txt_Tipo = new JTextField();
        txt_Editora = new JTextField();
        txt_ISBN = new JTextField();
        txt_Publicacao = new JTextField();
        txt_Autor = new JTextField();
        txt_Preco = new JTextField();
        txt_Ano = new JTextField(); 
        txt_Volume = new JTextField();
        txt_Tombo = new JTextField();
        txt_Cutter = new JTextField();
        txt_Paginas = new JTextField(); 
        txt_edicao= new JTextField();
        txt_tradutor = new JTextField();
        txt_assuntos = new JTextField();
        txt_Cod = new JTextField();
        txt_serie=new JTextField();
        txt_Consulta = new JTextField();
        txt_emprestado_para = new JTextField();
        txt_NTOMBO=new JTextField();
        txt_tituloori=new JTextField();
        txt_nota = new JTextField();
        txt_cdd= new JTextField();
        txt_colecao=new JTextField();
        txt_ex=new JTextField();
        lbl_order = new JLabel("Ordenar: ");
        bt_deletar.setText("Deletar livro");
        lbl_Titulo = new JLabel("Titulo:");
        lbl_Tipo = new JLabel("Obra:");
        lbl_Editora = new JLabel("Editora:");
        lbl_Autor = new JLabel("Autor:");
        lbl_Publicacao = new JLabel("Local:");
        lbl_Ano = new JLabel("Ano:");
        lbl_ISBN = new JLabel("ISBN:");
        lbl_Preco = new JLabel("Origem/Preço:");
        lbl_Volume = new JLabel("Volume:");
        lbl_Tombo = new JLabel("Tombo A.");
        lbl_Paginas = new JLabel("Paginas");
        lbl_Categoria = new JLabel("Edição: ");
        lbl_SubCategoria = new JLabel("Tradutor:");
        lbl_OBS = new JLabel("Assuntos: ");
        lbl_serie=new JLabel("Serie:");
        lbl_NTOMBO=new JLabel("Tombo: ");
        bt_Consultar = new JButton("Consultar");
        lbl_dica=new JLabel("Você pode pesquisar um livro digitando o titulo, tombo, autor ou assunto.");
        lbl_Cutter = new JLabel("Cutter:"); 
        lbl_Cod = new JLabel("Código:");
        lbl_TO=new JLabel("T.Original: ");
        lbl_emprestado_para=new JLabel("Emprestado: ");
        lbl_nota= new JLabel("Notas:");
        lbl_cdd=new JLabel("CDU: ");
        bt_E= new JButton("E");
        bt_concluir=new JButton("Concluir Edição");
        bt_D= new JButton("D");
        bt_R=new JButton("R");
        bt_editar=new JButton("Editar Livro");
        bt_novo= new JButton("Novo Exemplar");
        bt_cadastrar= new JButton("Cadastrar Exemplar");
        lbl_colecao= new JLabel("Coleção: ");
        lbl_ex = new JLabel("Ex:");
        st=new JScrollPane();
        combo_order= new JComboBox();
        combo_Editora=new JComboBox(); 
        combo_origem= new JComboBox();
        //Fim da Instanciação
        //Inicio de Definição de Propriedades
        txt_Consulta.setBounds(20, 10,400, 25);
        lbl_order.setBounds(440,10,80,25);
        combo_order.setBounds(520,10,110,25);
        bt_Consultar.setBounds(640,10,100, 25);
        st.setBounds(18,40,915,300);
        lbl_dica.setBounds(40,620,800,25);
        lbl_Categoria.setBounds(10,470,80,25);
        lbl_SubCategoria.setBounds(10,500,80,25);
        lbl_TO.setBounds(300,350,80,25);
        txt_tituloori.setBounds(380,350,220,25);
        lbl_serie.setBounds(150,470,80,25);
        txt_serie.setBounds(210,470,390,25);
        txt_edicao.setBounds(80,470,50,25);
        txt_tradutor.setBounds(80,500,520,25);
        lbl_Titulo.setBounds(10,350,80,25);
        txt_Titulo.setBounds(80, 350, 220, 25);
        lbl_Tipo.setBounds(10, 380, 80, 25);
        txt_Tipo.setBounds(80, 380, 100, 25);
        lbl_Editora.setBounds(190,380,80,25);
        txt_Editora.setBounds(250, 380, 140, 25 );
        combo_Editora.setBounds(250,380,140,25);
        lbl_ISBN.setBounds(400 ,380,80,25); 
        txt_ISBN.setBounds(450, 380, 150, 25 );
        lbl_Publicacao.setBounds(10, 410, 80, 25);
        txt_Publicacao.setBounds(80, 410, 100, 25);
        lbl_Autor.setBounds(190, 410, 80, 25);
        txt_Autor.setBounds(250, 410, 350, 25);
        lbl_Preco.setBounds(10, 440, 80, 25);
        txt_Preco.setBounds(80, 440, 100, 25);
        combo_origem.setBounds(80, 440, 100, 25);
        lbl_Ano.setBounds(190, 440, 80, 25);
        txt_Ano.setBounds(250, 440, 80, 25);
        lbl_Volume.setBounds(340, 440,80, 25);
        txt_Volume.setBounds(410, 440, 80, 25);
        lbl_Paginas.setBounds(500, 440, 80, 25);
        txt_Paginas.setBounds(560, 440, 40, 25);
        lbl_OBS.setBounds(10, 530, 80, 25); 
        txt_assuntos.setBounds(80, 530, 520, 25);
        lbl_emprestado_para.setBounds(10,590,80,25);
        txt_emprestado_para.setBounds(80,590,520,25);
        lbl_nota.setBounds(10,560,80,25);
        txt_nota.setBounds(80,560,220,25);
        lbl_colecao.setBounds(300,560,80,25);
        txt_colecao.setBounds(380,560,220,25);
        lbl_cdd.setBounds(650,380,80,25);
        txt_cdd.setBounds(720,380,150,25);
        lbl_Cod.setBounds(650, 410, 80, 25);
        txt_Cod.setBounds(720, 410, 150, 25 );
        lbl_Cutter.setBounds(650, 440, 80, 25);
        txt_Cutter.setBounds(720, 440, 150, 25 );
        lbl_Tombo.setBounds(650, 470, 80, 25);
        txt_Tombo.setBounds(720, 470, 150, 25 );
        lbl_NTOMBO.setBounds(650,500,80,25);
        txt_NTOMBO.setBounds(720,500,80,25);
        lbl_ex.setBounds(800,500,50,25);
        txt_ex.setBounds(820,500,50,25);
        bt_deletar.setBounds(720,600,200,30);
        bt_novo.setBounds(720,530,200,30);
        bt_cadastrar.setBounds(720,530,200,30);
        bt_editar.setBounds(720,565,200,30);
        bt_E.setBounds(885,410,50,30);
        bt_D.setBounds(885,440,50,30);
        bt_R.setBounds(885,470,50,30);
        bt_concluir.setBounds(720,565,200,30);
        txt_tituloori.setEditable(false);
        txt_colecao.setEditable(false);
        txt_ISBN.setEditable(false);
        txt_serie.setEditable(false);
        txt_edicao.setEditable(false);
        bt_novo.setEnabled(false);
        txt_tradutor.setEditable(false);
        txt_Titulo.setEditable(false);
        txt_Tipo.setEditable(false);
        txt_Editora.setEditable(false);
        txt_Publicacao.setEditable(false);
        txt_Autor.setEditable(false);
        txt_Preco.setEditable(false);
        txt_Ano.setEditable(false);
        txt_Volume.setEditable(false);
        txt_Paginas.setEditable(false);
        txt_assuntos.setEditable(false);
        txt_emprestado_para.setEditable(false);
        txt_nota.setEditable(false);
        txt_cdd.setEditable(false);
        txt_Cod.setEditable(false);
        txt_Cutter.setEditable(false);
        txt_Tombo.setEditable(false);
        txt_NTOMBO.setEditable(false);
        bt_deletar.setEnabled(false);
        bt_editar.setEnabled(false);
        bt_E.setVisible(false);
        txt_ex.setEditable(false);
        combo_origem.setVisible(false);
        bt_D.setVisible(false);
        bt_R.setVisible(false);  
        bt_cadastrar.setVisible(false);
        combo_Editora.setVisible(false);
        bt_concluir.setVisible(false);
        bt_D.setBackground(Color.gray);
        bt_E.setBackground(Color.gray);
        bt_R.setBackground(Color.gray);
        combo_order.addItem("titulo");
        combo_order.addItem("tombo");
        combo_order.addItem("autor");
        combo_order.addItem("assuntos");
        combo_order.addItem("cutter");
        combo_order.addItem("CDU");
        combo_origem.addItem("Doação");
        combo_origem.addItem("Aquisição");
        combo_origem.addItem("Permuta");
        //Fim de definição de propriedades
        //Inicio de Adição
        add(txt_Consulta);        
        add(bt_Consultar);        
        add(st);        
        add(lbl_dica);      
        add(lbl_Categoria);        
        add(lbl_SubCategoria);       
        add(lbl_TO);
        add(txt_tituloori);
        add(lbl_serie);
        add(txt_serie);
        add(txt_edicao);        
        add(txt_tradutor);
        add(lbl_Titulo);        
        add(txt_Titulo);
        add(lbl_Tipo);        
        add(txt_Tipo);
        add(lbl_Editora);        
        add(txt_Editora);
        add(lbl_ISBN);         
        add(txt_ISBN);
        add(lbl_Publicacao);        
        add(txt_Publicacao);
        add(lbl_Autor);        
        add(txt_Autor);
        add(lbl_Preco);        
        add(txt_Preco);
        add(lbl_Ano);        
        add(txt_Ano);
        add(lbl_Volume);        
        add(txt_Volume);
        add(lbl_Paginas);
        add(txt_Paginas);
        add(lbl_OBS);        
        add(txt_assuntos);        
        add(lbl_nota);        
        add(txt_nota);
        add(lbl_cdd);
        add(txt_cdd);
        add(lbl_Cod);        
        add(txt_Cod);
        add(lbl_Cutter);        
        add(txt_Cutter);
        add(lbl_Tombo);        
        add(txt_Tombo);        
        add(lbl_NTOMBO);
        add(txt_NTOMBO);
        add(bt_editar);
        add(bt_E);
        add(bt_D);
        add(bt_R);    
        add(bt_concluir);                                            
        add(lbl_emprestado_para);
        add(txt_emprestado_para);
        add(combo_order);
        add(lbl_order);
        add(combo_Editora);
        add(bt_novo);
        add(bt_cadastrar);
        add(combo_origem);
        add(lbl_colecao);
        add(txt_colecao);
        add(lbl_ex);
        add(txt_ex);
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
    }
    private void definirEventos(){
    bt_Consultar.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
          bt_deletar.setEnabled(false);
          bt_editar.setEnabled(false);
          bt_novo.setEnabled(false);
          txt_Preco.setVisible(true);
          combo_origem.setVisible(false);
          consultarLivro();   
        }
    });
     bt_editar.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
             Carrega_Combo_Editora();
             txt_ex.setEditable(true);
             txt_colecao.setEditable(true);
             bt_deletar.setEnabled(false);
             bt_novo.setEnabled(false);
             txt_Titulo.setEditable(true);
             txt_ISBN.setEditable(true);
             txt_Publicacao.setEditable(true);
             txt_serie.setEditable(true);
             txt_assuntos.setEditable(true);
             txt_tradutor.setEditable(true);
             txt_edicao.setEditable(true);
             txt_Autor.setEditable(true);
             txt_Ano.setEditable(true);
             txt_Volume.setEditable(true);
             txt_Tombo.setEditable(true);
             txt_Cutter.setEditable(true);
             txt_Paginas.setEditable(true);
             txt_Categoria.setEditable(true);
             txt_SubCategoria.setEditable(true);
             txt_OBS.setEditable(true);
             txt_cdd.setEditable(true);
             txt_tituloori.setEditable(true);
             txt_nota.setEditable(true);
             bt_editar.setVisible(false);
             bt_concluir.setVisible(true);
             combo_origem.setVisible(true);
             txt_Preco.setVisible(false);
             if(str_Status.equals("Disponível")||str_Status.equals("Não Disponivel")||str_Status.equals("Reservado")){
                bt_D.setBackground(Color.gray);
                bt_E.setBackground(Color.gray);
                bt_R.setBackground(Color.gray);
             }
             else{
                 if(str_Status.equals("Danificado")){
                     bt_D.setBackground(Color.red);
                     bt_E.setBackground(Color.gray);
                     bt_R.setBackground(Color.gray);                     
                 }
                 else{
                      bt_D.setBackground(Color.gray);
                      bt_E.setBackground(Color.red);
                      bt_R.setBackground(Color.gray);
                 }
             }
             bt_E.setVisible(true);
             bt_D.setVisible(true);
             if(str_Status.equals("Danificado")||str_Status.equals("Extraviado")){
                 bt_R.setVisible(true);
             }                
        }
    });
     bt_E.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
           str_Status="Extraviado";
           bt_concluir.requestFocus();
           bt_D.setBackground(Color.gray);
           bt_E.setBackground(Color.red);
           bt_R.setBackground(Color.gray);
        }
    });
    bt_D.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
           str_Status="Danificado";
           bt_concluir.requestFocus();
           bt_D.setBackground(Color.red);
           bt_E.setBackground(Color.gray);
           bt_R.setBackground(Color.gray);
        }
    });
    bt_R.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
           str_Status="Disponível";
           bt_concluir.requestFocus();
           bt_D.setBackground(Color.gray);
           bt_E.setBackground(Color.gray);
           bt_R.setBackground(Color.blue);
        }
    });
    bt_novo.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
           NewTombo();
           txt_Tombo.setText("");
           bt_editar.setEnabled(false);
           bt_deletar.setEnabled(false);
           txt_Tombo.setEditable(true);
           txt_Tombo.requestFocus();
           bt_novo.setVisible(false);
           bt_cadastrar.setVisible(true);
           txt_Preco.setVisible(false);
           combo_origem.setVisible(true);
        }
    });
    bt_cadastrar.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
           NewTombo();
           String  str_Tipo="", str_Editora="", str_Autor="", str_Publicacao="";
            String str_Ano="", str_Preco="", str_Volume="", str_Tombo="";
            String str_Paginas="", str_Cdd="", str_SubCategoria="", str_OBS="", str_Cutter="";
            String str_edicao="",str_assunto="",str_tradutor="",str_serie="",str_colecao="";
            String str_tituloori="",str_notas="",str_origem="";
            if(combo_origem.getSelectedIndex()==1)
                str_Preco =JOptionPane.showInputDialog("Qual o valor do livro?");
            else
                str_Preco="00,00";
            str_Cdd = txt_cdd.getText();
            str_edicao=txt_edicao.getText().toString();
            str_assunto=txt_assuntos.getText().toString();
            str_tradutor=txt_tradutor.getText().toString();
            str_serie=txt_serie.getText().toString();
            str_Editora = txt_Editora.getText();
            str_Tipo = txt_Tipo.getText();
            str_Paginas = txt_Paginas.getText();
            str_Volume = txt_Volume.getText();
            str_OBS = txt_OBS.getText();
            str_Autor = txt_Autor.getText();
            str_Titulo = txt_Titulo.getText();
            str_Publicacao = txt_Publicacao.getText();
            str_Ano = txt_Ano.getText();
            str_ISBN = txt_ISBN.getText();
            str_Cutter = txt_Cutter.getText();
            str_tituloori=txt_tituloori.getText();
            str_notas=txt_nota.getText();
            str_origem=combo_origem.getSelectedItem().toString(); 
            str_colecao=txt_colecao.getText();
            ContExemplar();
            try{
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                str_Tombo = txt_Tombo.getText();
                String SQL_CHECA_EXISTENCIA_TOMBO = "select tombo from livro where tombo = '"+str_Tombo+"'";
                ResultSet resultSet = statement.executeQuery(SQL_CHECA_EXISTENCIA_TOMBO);
                resultSet.last();
                if(resultSet.getRow() > 0){
                    JOptionPane.showMessageDialog(null, "Este Tombo já está cadastrado, digite um tombo diferente!");
                    return;
                }
            }
            catch(Exception e1){
                JOptionPane.showMessageDialog(null, e1);
            }
            Calendar calendario = Calendar.getInstance();
            int int_mes = calendario.get(Calendar.MONTH);
            int int_dia = calendario.get(Calendar.DAY_OF_MONTH);
            int int_ano = calendario.get(Calendar.YEAR);
            try{
                String id_Cutter = "";
                String data =int_ano+"-"+int_mes+"-"+int_dia;
                String id_Editora="", id_Tipo="";
                String SQL_BUSCA_EDITORA = "select * from editora where nome = '"+str_Editora+"'";
                resultSet = statement.executeQuery(SQL_BUSCA_EDITORA);
                System.out.println(SQL_BUSCA_EDITORA);
                while (resultSet.next()){
                    id_Editora = resultSet.getString("id_editora").toString();
                    System.out.println("editora: "+id_Editora);
                }
                String SQL_BUSCA_TIPO = "select * from tipo where tipo = '"+str_Tipo+"'";
                System.out.println(SQL_BUSCA_TIPO);
                resultSet = statement.executeQuery(SQL_BUSCA_TIPO);
                while (resultSet.next()){
                    id_Tipo = resultSet.getString("id_tipo").toString();
                    System.out.println("Tipo: "+id_Tipo);
                }
                int id_Livro = (int) (Math.random() * 1000000000);
                String SQL_CHECA_ID_LIVRO = "SELECT * FROM livro where id_livro = "+id_Livro;
                System.out.println(SQL_CHECA_ID_LIVRO);
                resultSet = statement.executeQuery(SQL_CHECA_ID_LIVRO);
                resultSet.last();
                int num_id_livro = resultSet.getRow();
                resultSet.beforeFirst();
                System.out.println("passou 1");
                while(num_id_livro > 0){
                    id_Livro = (int) (Math.random() * 1000000000);
                    SQL_CHECA_ID_LIVRO = "SELECT * FROM livro where id_livro = "+id_Livro;
                    System.out.println(SQL_CHECA_ID_LIVRO);
                    resultSet = statement.executeQuery(SQL_CHECA_ID_LIVRO);
                    resultSet.last();
                    num_id_livro = resultSet.getRow();
                    resultSet.beforeFirst();
                }
            System.out.println("vai cadastrar a obra");
                //continuar cadastro sql...........................................
                id_Cutter = "0";
                System.out.println(id_Cutter);
                System.out.println("preco: "+str_Preco);
                String SQL_CADASTRA_OBRA = "insert into livro(id_livro, data_cadastro, titulo, id_editora, num_pag,"
                    +"volume, local_publicacao, ano_publicacao, preco,"
                    +"cdu, id_prefixo, id_tipo, isbn, tombo, autor, cutter,tradutor,assuntos,serie,edicao,colecao,titulooriginal,NTOMBO,notas,origem,exemplar) values("
                    +id_Livro+", '"+data+"', '"+str_Titulo+"', "+id_Editora+", "+str_Paginas+","
                    +str_Volume+",'"+str_Publicacao+"' ,'"+str_Ano+"','"+str_Preco+"', '"
                    +str_Cdd+"', '"+id_Cutter+"', "+id_Tipo+", '"+str_ISBN+"', '"+str_Tombo+"', '"+str_Autor+"', '"+str_Cutter+"', '"
                    +str_tradutor+"', '"+str_assunto+"', '"+str_serie+"', '"+str_edicao+"','"+str_colecao+"','"+str_tituloori+"','"+NTOMBO+"','"+str_notas+"','"+str_origem+"',"+exemplar+")";
                System.out.println(SQL_CADASTRA_OBRA);
                try{
                    statement.executeUpdate(SQL_CADASTRA_OBRA);
                    JOptionPane.showMessageDialog(null, "Obra Cadastrada com sucesso!\nExemplar Nº: "+exemplar+"\nCodigo(NTOMBO)= "+NTOMBO);
                    String[] opcao = {"SIM","NÃO"};
                    if(JOptionPane.showOptionDialog(null,"Deseja cadastrar outro livro igual?","APAGAR?",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcao, true )==JOptionPane.YES_OPTION){
                       txt_Tombo.setEditable(true);
                       NewTombo();
                       txt_Tombo.setText("");
                       txt_Tombo.requestFocus();
                    }
                else{
                    txt_Tombo.setEditable(false);
                    bt_novo.setVisible(true);
                    bt_cadastrar.setVisible(false);
                    txt_Preco.setVisible(true);
                    combo_origem.setVisible(false);
                    consultarLivro();
                    }
                }
                catch(Exception ee){
                        JOptionPane.showMessageDialog(null, "erro : "+ee);
                        JOptionPane.showMessageDialog(null, "Erro no Cadastro, verifique os dados e tente denovo mais tarde!!!");
                }
             }
            catch(Exception ef){
            }
       }
    });
    bt_concluir.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){   
           String id_Editora="";
           try{           
                String SQL_BUSCA_EDITORA = "select * from editora where nome = '"+combo_Editora.getSelectedItem().toString()+"'";
                resultSet = statement.executeQuery(SQL_BUSCA_EDITORA);
                System.out.println(SQL_BUSCA_EDITORA);
                while (resultSet.next()){
                    id_Editora = resultSet.getString("id_editora").toString();
                    System.out.println("editora: "+id_Editora);
                }
           }
            catch(Exception erro){
                JOptionPane.showMessageDialog(null, erro);
            }
           if(combo_origem.getSelectedIndex()==1)
                str_valor=JOptionPane.showInputDialog("Digite o valor do livro.");
           else
               str_valor="00,00";
             String sql = "UPDATE livro SET titulo='"+txt_Titulo.getText()+"',num_pag='"+txt_Paginas.getText()+"',volume='"+txt_Volume.getText()+"',local_publicacao='"+txt_Publicacao.getText()+"',`ano_publicacao`='"+txt_Ano.getText()+"',`preco`='"+txt_Preco.getText()+"',`cdu`='"+txt_cdd.getText()+"',isbn='"+txt_ISBN.getText()+"',autor='"+txt_Autor.getText()+"',cutter='"+txt_Cutter.getText()+"',tradutor='"+txt_tradutor.getText()+"',assuntos='"+txt_assuntos.getText()+"',serie='"+txt_serie.getText()+"',edicao='"+txt_edicao.getText()+"',notas='"+txt_nota.getText()+"', tombo= '"+txt_Tombo.getText()+"', cdu='"+txt_cdd.getText()+"', cutter='"+txt_Cutter.getText()+"', disponivel='"+str_Status+"',id_editora='"+id_Editora+"', origem='"+combo_origem.getSelectedItem().toString()+"',preco='"+str_valor+"', colecao='"+txt_colecao.getText()+"',titulooriginal='"+txt_tituloori.getText()+"',exemplar='"+txt_ex.getText()+"'  WHERE NTOMBO='"+txt_NTOMBO.getText()+"'";                
             System.out.println(sql);    
             try{
                    Class.forName(DRIVER);
                    Connection connection = DriverManager.getConnection(URL, con_usuario, con_senha);
                    Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);                
                    statement.execute(sql);
                    txt_colecao.setEditable(false);
                    txt_Titulo.setEditable(false);
                    txt_Tipo.setEditable(false);
                    txt_ISBN.setEditable(false);
                    txt_Publicacao.setEditable(false);
                    txt_Cod.setEditable(false);
                    txt_serie.setEditable(false);
                    txt_assuntos.setEditable(false);
                    txt_tradutor.setEditable(false);
                    txt_edicao.setEditable(false);
                    txt_Autor.setEditable(false);
                    txt_Preco.setEditable(false);
                    txt_Ano.setEditable(false);
                    txt_Volume.setEditable(false);
                    txt_Tombo.setEditable(false);
                    txt_ex.setEditable(false);
                    txt_Cutter.setEditable(false);
                    txt_Paginas.setEditable(false);
                    txt_Categoria.setEditable(false);
                    txt_SubCategoria.setEditable(false);
                    txt_OBS.setEditable(false);
                    txt_cdd.setEditable(false);
                    bt_concluir.setVisible(false);
                    bt_editar.setVisible(true);
                    txt_tituloori.setEditable(false);
                    txt_nota.setEditable(false);
                    bt_E.setVisible(false);
                    bt_D.setVisible(false);
                    bt_R.setVisible(false);
                    bt_editar.setEnabled(false);
                    bt_deletar.setEnabled(false);
                    bt_novo.setEnabled(false);
                    txt_Editora.setVisible(true);
                    combo_Editora.setVisible(false);
                    combo_origem.setVisible(false);
                    txt_Preco.setVisible(true);
                    consultarLivro();
                 }
                 catch(Exception ef){
                     JOptionPane.showMessageDialog(null,ef);   
                 }
             }         
        }); 
    }  
    private void tabelaMouseClicked(java.awt.event.MouseEvent evt){
        txt_Preco.setVisible(true);
        txt_colecao.setEditable(false);
        combo_origem.setVisible(false);
        bt_novo.setEnabled(true);
        bt_novo.setVisible(true);
        txt_ex.setEditable(false);
        bt_cadastrar.setVisible(false);
        txt_Titulo.setEditable(false);
        txt_Tipo.setEditable(false);
        txt_ISBN.setEditable(false);
        txt_Publicacao.setEditable(false);
        txt_Cod.setEditable(false);
        txt_serie.setEditable(false);
        txt_assuntos.setEditable(false);
        txt_tradutor.setEditable(false);
        txt_edicao.setEditable(false);
        txt_Autor.setEditable(false);
        txt_Preco.setEditable(false);
        txt_Ano.setEditable(false);
        txt_Volume.setEditable(false);
        txt_Tombo.setEditable(false);
        txt_Cutter.setEditable(false);
        txt_Paginas.setEditable(false);
        txt_Categoria.setEditable(false);
        txt_SubCategoria.setEditable(false);
        txt_OBS.setEditable(false);
        txt_cdd.setEditable(false);
        bt_concluir.setVisible(false);
        bt_editar.setVisible(true);
        bt_editar.setEnabled(true);
        bt_deletar.setEnabled(true);
        txt_tituloori.setEditable(false);
        txt_nota.setEditable(false);
        bt_E.setVisible(false);
        bt_D.setVisible(false);
        bt_R.setVisible(false);
        txt_Editora.setVisible(true);
        combo_Editora.setVisible(false);
        id_livro="";
        id_usuario="";
        try{
            int x = tabela.getSelectedRow();
            String str_Tombo = tabela.getValueAt(x, 0).toString();
            String sql = "Select l.*, t.*, e.* from livro l,  tipo t, editora e where e.id_editora = l.id_editora and t.id_tipo = l.id_tipo and l.NTOMBO = '"+str_Tombo+"'";
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
            resultSet.first();
            id_livro=resultSet.getString("id_livro");
            txt_cdd.setText(resultSet.getString("cdu"));
            txt_Titulo.setText(resultSet.getString("titulo"));
            txt_Tipo.setText(resultSet.getString("Tipo"));
            txt_Editora.setText(resultSet.getString("nome").toUpperCase());
            txt_ISBN.setText(resultSet.getString("ISBN"));
            txt_Publicacao.setText(resultSet.getString("local_publicacao"));
            txt_Autor.setText(resultSet.getString("autor"));
            txt_Preco.setText(resultSet.getString("origem")+" - "+resultSet.getString("preco"));
            txt_Ano.setText(resultSet.getString("ano_publicacao"));
            txt_Volume.setText(resultSet.getString("volume"));
            txt_Paginas.setText(resultSet.getString("num_pag"));
            txt_assuntos.setText(resultSet.getString("assuntos"));
            txt_Cutter.setText(resultSet.getString("cutter"));
            txt_Tombo.setText(resultSet.getString("tombo"));
            txt_serie.setText(resultSet.getString("serie"));
            txt_tradutor.setText(resultSet.getString("tradutor"));
            txt_assuntos.setText(resultSet.getString("assuntos"));
            txt_edicao.setText(resultSet.getString("edicao"));
            txt_emprestado_para.setText("");
            txt_NTOMBO.setText(resultSet.getString("NTOMBO"));
            txt_tituloori.setText(resultSet.getString("titulooriginal"));
            txt_nota.setText(resultSet.getString("notas"));
            str_Status=resultSet.getString("disponivel");
            txt_colecao.setText(resultSet.getString("colecao"));
            txt_ex.setText(resultSet.getString("exemplar"));
            emprestadopara();
            bt_editar.setEnabled(true);
        }
        catch(Exception erro){
            JOptionPane.showMessageDialog(null, erro);
        }
    }
    public void consultarLivro(){
     try{
        String consulta=txt_Consulta.getText();
        consulta.replaceAll("'","\\'");
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String ordem="";
        if(combo_order.getSelectedIndex()==1)
            ordem="NTOMBO, tombo";
        else
            ordem=combo_order.getSelectedItem().toString();
        PreparedStatement sta =connection.prepareStatement("Select l.NTOMBO, l.tombo, l.titulo, l.autor , l.disponivel, l.cdu, l.cutter, l.assuntos from livro l where( disponivel like '%" + consulta +"%' or  titulo like '%" + consulta +"%' or autor like '%" + consulta +"%'or NTOMBO like '%"+consulta +"%' or tombo like'%" + consulta +"%' or l.id_livro like '%"+consulta+"%' or l.cdu like '%"+consulta+"%' or assuntos like '%"+consulta+"%') order by "+ordem);
        
        resultSet=sta.executeQuery();
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
    public void emprestadopara(){
        try{
            String sql = "Select id_usuario from emprestimo where id_livro= '"+id_livro+"' and devolvido ='não'";
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
            resultSet.first();
            id_usuario=resultSet.getString("id_usuario");
            resultSet.close();
            statement.close();
       }
       catch(Exception es){
           txt_emprestado_para.setText("");
       }
       try{
            String sql = "Select nome, rm from  usuario where id_usuario= '"+id_usuario+"'";
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
            resultSet.first();
            txt_emprestado_para.setText(resultSet.getString("nome")+"|  |RM: "+resultSet.getString("rm"));
       }
       catch(Exception ed){
           try{
                String sql = "Select nome from  funcionario where id_funcionario= '"+id_usuario+"'";
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                resultSet = statement.executeQuery(sql);
                resultSet.first();
                if(resultSet.getString("nome")==null)
                    txt_emprestado_para.setText("");
                else
                    txt_emprestado_para.setText(resultSet.getString("nome")+"|  |Funcionario.");
           }
           catch(Exception tt){
                
           } 
       }
    }
    public void Carrega_Combo_Editora(){
              try{
                    txt_Editora.setVisible(false);
                    combo_Editora.setVisible(true);
                    combo_Editora.removeAllItems();
                    String SQL_TIPO = "SELECT * FROM editora order by nome";
                    statement = connection.prepareStatement(SQL_TIPO);
                    resultSet = statement.executeQuery();
                    while (resultSet.next()){
                        combo_Editora.addItem(resultSet.getString("Nome").toUpperCase());
                    }
                    combo_Editora.setSelectedItem(txt_Editora.getText());
                   }
                   catch(Exception e){
                       JOptionPane.showMessageDialog(rootPane, e);
                   }
    }
    public void NewTombo(){
        NTOMBO=0;
        try{
        String SQL_TIPO = "SELECT count(*) as total from livro";
        statement = connection.prepareStatement(SQL_TIPO);
        resultSet = statement.executeQuery();
        resultSet.last();
            String num1= resultSet.getString("total");
            NTOMBO=Integer.parseInt(num1)+1;
            lbl_NTOMBO.setText("Tombo:  "+ NTOMBO);
        }
        catch(Exception y){
            
        }
    }
        public void ContExemplar(){
        String SQL_TIPO;
        if(txt_ISBN.getText().equals("-")||txt_ISBN.getText().equals("")){
            SQL_TIPO = "SELECT count(*) as ex from livro where titulo = '"+str_Titulo+"' and volume='"+txt_Volume.getText()+"' and colecao='"+txt_colecao.getText()+"' and serie='"+txt_serie.getText()+"'";
        }
        else{
            SQL_TIPO = "SELECT count(*) as ex from livro where isbn = '"+str_ISBN+"'";
        }
            try{
                statement = connection.prepareStatement(SQL_TIPO);
                resultSet = statement.executeQuery();
                resultSet.last();
                exemplar=resultSet.getInt("ex")+1;
            }
            catch(Exception y){
                JOptionPane.showMessageDialog(null,y);
            }
       }
}
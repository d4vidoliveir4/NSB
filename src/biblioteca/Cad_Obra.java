package biblioteca;
import java.awt.event.*;
import java.sql.*;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
public class Cad_Obra extends JFrame{
    public static Cad_Obra frameco;
    public JLabel lbl_Titulo, lbl_Tipo, lbl_Editora, lbl_Autor, lbl_Publicacao, lbl_Ano,lbl_colecao,lbl_VSerie;
    public JLabel lbl_ISBN, lbl_Preco, lbl_Volume, lbl_Tombo, lbl_Paginas, lbl_Categoria, lbl_to;
    public JLabel lbl_SubCategoria, lbl_OBS, lbl_Cutter,lbl_assunto,lbl_edicao,lbl_tradutor, lbl_serie,lbl_Notas;
    public JTextField txt_Titulo, txt_Autor, txt_Publicacao, txt_Ano, txt_ISBN, txt_Volume,txt_serie,txt_colecao, txt_tituloori,txt_NTOMBO;
    public JTextField txt_Tombo, txt_Paginas, txt_OBS, txt_Preco, txt_Cutter,txt_cdd,txt_assuntos,txt_tradutor,txt_edicao,txt_Nota,txt_VSerie;
    public JComboBox combo_Editora, combo_Categoria,  combo_Tipo, combo_SubCategoria,combo_origem;
    public JButton bt_Cadastrar, bt_Cancelar;
    public JMenuBar mnbBarra;
    public JMenu mnAdicionar, mnDeletar;
    public JMenuItem miSubCategoria, miEditora, miTipo, miSubCategoria2, miEditora2, miTipo2;
    public static String DRIVER = "com.mysql.jdbc.Driver";
    public static String con_usuario, con_senha, URL, con_porta, con_bd, con_pc;
    public Connection connection;
    public PreparedStatement statement;
    public ResultSet resultSet;
    public Connection connection2;
    public PreparedStatement statement2;
    public ResultSet resultSet2;
    int NTOMBO;
    int exemplar;
    String str_Titulo="", str_ISBN="";
    public boolean d = true;
    public void CarregarBd(){
        //conecta BD
        Conexao conecta= new Conexao();
        URL = conecta.URL();
        con_usuario=conecta.con_usuario();
        con_senha= conecta.con_senha();
        try{
           Class.forName(DRIVER);
           connection = DriverManager.getConnection(URL, con_usuario, con_senha);
       }
       catch (ClassNotFoundException ex){
            Logger.getLogger(Cad_Obra.class.getName()).log(Level.SEVERE, null, ex);
       }
       catch (SQLException ex){
            Logger.getLogger(Cad_Obra.class.getName()).log(Level.SEVERE, null, ex);
       }
       System.out.println("Conectou");
    }
    public void Cad_Obra(){
        InicializarCad_Obra();
        DefinirEventos();
    }
     private void InicializarCad_Obra(){
        CarregarBd();
        NewTombo();
        setBounds(0,0,800,600);
        setLayout(null);
        setTitle("NSB - Cadastro de Obras");
        setVisible(true);
        getContentPane().setBackground(Color.getHSBColor(0.650f,0.158f,0.750f));
        mnbBarra = new JMenuBar();
        lbl_VSerie = new JLabel("V.:");
        txt_VSerie = new JTextField();
        mnAdicionar = new JMenu("Adicionar");
        miSubCategoria = new JMenuItem("SubCategoria");
        miEditora = new JMenuItem("Editora");
        miTipo = new JMenuItem("Tipo");
        mnDeletar = new JMenu("Deletar");
        miTipo2 = new JMenuItem("Tipo");
        miEditora2 = new JMenuItem("Editora");
        miSubCategoria2 = new JMenuItem("Subcategoria");
        mnAdicionar.add(miEditora);
        mnAdicionar.add(miTipo);
        mnbBarra.add(mnAdicionar);
        mnDeletar.add(miSubCategoria2);
        mnDeletar.add(miEditora2);
        mnDeletar.add(miTipo2);
        mnbBarra.add(mnDeletar);
        setJMenuBar(mnbBarra);
        lbl_Titulo = new JLabel("Titulo:");
        lbl_Tipo = new JLabel("Tipo:");
        lbl_Editora = new JLabel("Editora:");
        lbl_Autor = new JLabel("Autores:");
        lbl_Publicacao = new JLabel("Local de Publicação:");
        lbl_Ano = new JLabel("Ano:");
        lbl_ISBN = new JLabel("ISBN:");
        lbl_Preco = new JLabel("Origem:"); 
        lbl_Volume = new JLabel("Volume:");
        lbl_Tombo = new JLabel("Tombo:");
        lbl_Paginas = new JLabel("Paginas:");
        lbl_Categoria = new JLabel("CDU:");
        lbl_SubCategoria = new JLabel("Subcategoria:");
        lbl_OBS = new JLabel("OBS:");
        lbl_Cutter = new JLabel("Cutter:");
        txt_Titulo = new JTextField();
        txt_Autor = new JTextField();
        txt_Publicacao = new JTextField();
        txt_Ano = new JTextField();
        txt_ISBN = new JTextField(); 
        txt_Volume = new JTextField();
        txt_Tombo = new JTextField();
        txt_Paginas = new JTextField();
        txt_tradutor = new JTextField();
        txt_serie = new JTextField();
        txt_edicao = new JTextField();
        txt_assuntos = new JTextField();
        txt_OBS = new JTextField();
        txt_Preco = new JTextField("00,00");
        txt_Cutter = new JTextField();
        lbl_assunto=new JLabel("Assuntos:");
        lbl_edicao=new JLabel("Edição:");
        lbl_tradutor=new JLabel("Tradutor:");
        lbl_serie=new JLabel("Série:");
        lbl_colecao= new JLabel("Coleção:");
        txt_colecao=new JTextField();
        combo_origem= new JComboBox();
        combo_Editora = new JComboBox();
        combo_Categoria = new JComboBox();
        combo_SubCategoria = new JComboBox();
        combo_Tipo = new JComboBox();
        txt_cdd=new JTextField();
        add(txt_cdd);
        bt_Cadastrar = new JButton("Cadastrar");
        txt_tituloori= new JTextField();
        lbl_to=new JLabel("T. Original: ");
        bt_Cancelar = new JButton("Cancelar");
        lbl_Notas=new JLabel("Notas: ");
        txt_Nota=new JTextField();
        txt_NTOMBO=new JTextField();
        combo_origem.addItem("Doação");
        combo_origem.addItem("Aquisição");
        combo_origem.addItem("Permuta");
        lbl_Titulo.setBounds(18, 10, 80,  25);
        add(lbl_Titulo);
        txt_Titulo.setBounds(108, 10,500, 25);
        add(txt_Titulo);
        lbl_to.setBounds(18,45,80,25);
        add(lbl_to);
        txt_tituloori.setBounds(108,45,500,25);
        add(txt_tituloori);
        lbl_Autor.setBounds(18,80, 80,  25);
        add(lbl_Autor);
        txt_Autor.setBounds(108,80, 500,25);
        add(txt_Autor);
        lbl_tradutor.setBounds(18,115,80,25);
        add(lbl_tradutor);
        txt_tradutor.setBounds(108,115,500,25);
        add(txt_tradutor);
        lbl_Editora.setBounds(18, 150,100,  25);
        add(lbl_Editora);
        lbl_Tipo.setBounds(450, 150, 80,  25);
        add(lbl_Tipo);
        lbl_edicao.setBounds(18,200,80,25);
        add(lbl_edicao);
        txt_edicao.setBounds(108,200,50,25);
        add(txt_edicao);
        lbl_Volume.setBounds(170, 200, 80, 25);
        add(lbl_Volume);
        txt_Volume.setBounds(230, 200, 50, 25);
        add(txt_Volume);
        lbl_colecao.setBounds(290,200,80,25);
        add(lbl_colecao);
        txt_colecao.setBounds(360,200,250,25);
        add(txt_colecao);
         lbl_serie.setBounds(18,240,80,25);
        add(lbl_serie);
        txt_serie.setBounds(108,240,200,25);
        add(txt_serie);
        lbl_VSerie.setBounds(320,240,80,25);
        add(lbl_VSerie);
        txt_VSerie.setBounds(350,240,40,25);
        add(txt_VSerie);
        lbl_Paginas.setBounds(400, 240, 80, 25);
        add(lbl_Paginas);
        txt_Paginas.setBounds(470, 240, 140, 25); 
        add(txt_Paginas);
        lbl_assunto.setBounds(18,275,80,25);
        add(lbl_assunto);
        txt_assuntos.setBounds(108,275,500,25);
        add(txt_assuntos);
        lbl_Publicacao.setBounds(18,325,150,25);
        add(lbl_Publicacao);
        txt_Publicacao.setBounds(170,325,300,25);
        add(txt_Publicacao);
        lbl_Ano.setBounds(490, 325, 80, 25);
        add(lbl_Ano);
        txt_Ano.setBounds(530, 325, 80, 25);
        add(txt_Ano);
        lbl_ISBN.setBounds(18, 360, 80, 25);
        add(lbl_ISBN);
        txt_ISBN.setBounds(108, 360, 150, 25);
        add(txt_ISBN);
        lbl_Preco.setBounds(280, 360, 80, 25);
        add (lbl_Preco);
        combo_origem.setBounds(350,360,150,25);
        add(combo_origem);
        lbl_Categoria.setBounds(18, 410, 80, 25);
        add(lbl_Categoria);
        txt_cdd.setBounds(108, 410, 150, 25);
        lbl_Cutter.setBounds(280, 410, 80, 25);
        add(lbl_Cutter);
        txt_Cutter.setBounds(340, 410, 100, 25);
        add(txt_Cutter);
        lbl_Tombo.setBounds(465, 410, 80, 25);
        add(lbl_Tombo);
        txt_Tombo.setBounds(530, 410, 80, 25);
        add(txt_Tombo);
        lbl_Notas.setBounds(18,460,80,25);
        add(lbl_Notas);
        txt_Nota.setBounds(108,460,500,25);
        add(txt_Nota);
        bt_Cadastrar.setBounds(620, 390, 150, 40);
        add(bt_Cadastrar);
        bt_Cancelar.setBounds(620 , 445, 150, 40);
        add(bt_Cancelar);
        Carrega_Combo_Tipo();
        Carrega_Combo_Editora();
    }
    private void DefinirEventos(){  
     //Botão direito
     txt_Titulo.addMouseListener(new MouseAdapter() {  
        public void mouseClicked(MouseEvent me) {  
          //Verificando se o botão direito do mouse foi clicado  
          if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {  
              JPopupMenu menu = new JPopupMenu();  
              add(menu);  
              JMenuItem item = new JMenuItem("Copiar");  
              menu.add(item); 
                item.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            JOptionPane.showMessageDialog(null,"saporracopio");
                        }
                    });
              JMenuItem item2 = new JMenuItem("Recortar");  
              menu.add(item2); 
                item2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                });
              JMenuItem item3 = new JMenuItem("Colar");  
              menu.add(item3);
                item3.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){ 

                        }
                });
              menu.show(menu, me.getX(), me.getY());  
  
          }  
      }  
  });  
        txt_Autor.addMouseListener(new MouseAdapter() {  
        public void mouseClicked(MouseEvent me) {  
          //Verificando se o botão direito do mouse foi clicado  
          if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {  
              JPopupMenu menu = new JPopupMenu();  
              add(menu);  
              JMenuItem item = new JMenuItem("Copiar");  
              menu.add(item); 
                item.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                    });
              JMenuItem item2 = new JMenuItem("Recortar");  
              menu.add(item2); 
                item2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                });
              JMenuItem item3 = new JMenuItem("Colar");  
              menu.add(item3);
                item3.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){ 

                        }
                });
              menu.show(menu, me.getX(), me.getY());  
  
          }  
      }  
  });  
   txt_Publicacao.addMouseListener(new MouseAdapter() {  
        public void mouseClicked(MouseEvent me) {  
          //Verificando se o botão direito do mouse foi clicado  
          if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {  
              JPopupMenu menu = new JPopupMenu();  
              add(menu);  
              JMenuItem item = new JMenuItem("Copiar");  
              menu.add(item); 
                item.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                    });
              JMenuItem item2 = new JMenuItem("Recortar");  
              menu.add(item2); 
                item2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                });
              JMenuItem item3 = new JMenuItem("Colar");  
              menu.add(item3);
                item3.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){ 

                        }
                });
              menu.show(menu, me.getX(), me.getY());  
  
          }  
      }  
  });   
   txt_Ano.addMouseListener(new MouseAdapter() {  
        public void mouseClicked(MouseEvent me) {  
          //Verificando se o botão direito do mouse foi clicado  
          if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {  
              JPopupMenu menu = new JPopupMenu();  
              add(menu);  
              JMenuItem item = new JMenuItem("Copiar");  
              menu.add(item); 
                item.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                    });
              JMenuItem item2 = new JMenuItem("Recortar");  
              menu.add(item2); 
                item2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                });
              JMenuItem item3 = new JMenuItem("Colar");  
              menu.add(item3);
                item3.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){ 

                        }
                });
              menu.show(menu, me.getX(), me.getY());  
  
          }  
      }  
  });  
  txt_ISBN.addMouseListener(new MouseAdapter() {  
        public void mouseClicked(MouseEvent me) {  
          //Verificando se o botão direito do mouse foi clicado  
          if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {  
              JPopupMenu menu = new JPopupMenu();  
              add(menu);  
              JMenuItem item = new JMenuItem("Copiar");  
              menu.add(item); 
                item.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                    });
              JMenuItem item2 = new JMenuItem("Recortar");  
              menu.add(item2); 
                item2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                });
              JMenuItem item3 = new JMenuItem("Colar");  
              menu.add(item3);
                item3.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){ 

                        }
                });
              menu.show(menu, me.getX(), me.getY());  
  
          }  
      }  
  });   
 txt_Volume.addMouseListener(new MouseAdapter() {  
        public void mouseClicked(MouseEvent me) {  
          //Verificando se o botão direito do mouse foi clicado  
          if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {  
              JPopupMenu menu = new JPopupMenu();  
              add(menu);  
              JMenuItem item = new JMenuItem("Copiar");  
              menu.add(item); 
                item.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                    });
              JMenuItem item2 = new JMenuItem("Recortar");  
              menu.add(item2); 
                item2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                });
              JMenuItem item3 = new JMenuItem("Colar");  
              menu.add(item3);
                item3.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){ 

                        }
                });
              menu.show(menu, me.getX(), me.getY());  
  
          }  
      }  
  }); 
 txt_serie.addMouseListener(new MouseAdapter() {  
        public void mouseClicked(MouseEvent me) {  
          //Verificando se o botão direito do mouse foi clicado  
          if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {  
              JPopupMenu menu = new JPopupMenu();  
              add(menu);  
              JMenuItem item = new JMenuItem("Copiar");  
              menu.add(item); 
                item.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                    });
              JMenuItem item2 = new JMenuItem("Recortar");  
              menu.add(item2); 
                item2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                });
              JMenuItem item3 = new JMenuItem("Colar");  
              menu.add(item3);
                item3.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){ 

                        }
                });
              menu.show(menu, me.getX(), me.getY());  
  
          }  
      }  
  });  
 txt_colecao.addMouseListener(new MouseAdapter() {  
        public void mouseClicked(MouseEvent me) {  
          //Verificando se o botão direito do mouse foi clicado  
          if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {  
              JPopupMenu menu = new JPopupMenu();  
              add(menu);  
              JMenuItem item = new JMenuItem("Copiar");  
              menu.add(item); 
                item.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                    });
              JMenuItem item2 = new JMenuItem("Recortar");  
              menu.add(item2); 
                item2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                });
              JMenuItem item3 = new JMenuItem("Colar");  
              menu.add(item3);
                item3.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){ 

                        }
                });
              menu.show(menu, me.getX(), me.getY());  
  
          }  
      }  
  });  
  txt_tituloori.addMouseListener(new MouseAdapter() {  
        public void mouseClicked(MouseEvent me) {  
          //Verificando se o botão direito do mouse foi clicado  
          if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {  
              JPopupMenu menu = new JPopupMenu();  
              add(menu);  
              JMenuItem item = new JMenuItem("Copiar");  
              menu.add(item); 
                item.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                    });
              JMenuItem item2 = new JMenuItem("Recortar");  
              menu.add(item2); 
                item2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                });
              JMenuItem item3 = new JMenuItem("Colar");  
              menu.add(item3);
                item3.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){ 

                        }
                });
              menu.show(menu, me.getX(), me.getY());  
  
          }  
      }  
  });  
  txt_NTOMBO.addMouseListener(new MouseAdapter() {  
        public void mouseClicked(MouseEvent me) {  
          //Verificando se o botão direito do mouse foi clicado  
          if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {  
              JPopupMenu menu = new JPopupMenu();  
              add(menu);  
              JMenuItem item = new JMenuItem("Copiar");  
              menu.add(item); 
                item.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                    });
              JMenuItem item2 = new JMenuItem("Recortar");  
              menu.add(item2); 
                item2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                });
              JMenuItem item3 = new JMenuItem("Colar");  
              menu.add(item3);
                item3.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){ 

                        }
                });
              menu.show(menu, me.getX(), me.getY());  
  
          }  
      }  
  });   
  txt_Tombo.addMouseListener(new MouseAdapter() {  
        public void mouseClicked(MouseEvent me) {  
          //Verificando se o botão direito do mouse foi clicado  
          if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {  
              JPopupMenu menu = new JPopupMenu();  
              add(menu);  
              JMenuItem item = new JMenuItem("Copiar");  
              menu.add(item); 
                item.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                    });
              JMenuItem item2 = new JMenuItem("Recortar");  
              menu.add(item2); 
                item2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                });
              JMenuItem item3 = new JMenuItem("Colar");  
              menu.add(item3);
                item3.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){ 

                        }
                });
              menu.show(menu, me.getX(), me.getY());  
  
          }  
      }  
  });  
  txt_Paginas.addMouseListener(new MouseAdapter() {  
        public void mouseClicked(MouseEvent me) {  
          //Verificando se o botão direito do mouse foi clicado  
          if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {  
              JPopupMenu menu = new JPopupMenu();  
              add(menu);  
              JMenuItem item = new JMenuItem("Copiar");  
              menu.add(item); 
                item.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                    });
              JMenuItem item2 = new JMenuItem("Recortar");  
              menu.add(item2); 
                item2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                });
              JMenuItem item3 = new JMenuItem("Colar");  
              menu.add(item3);
                item3.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){ 

                        }
                });
              menu.show(menu, me.getX(), me.getY());  
  
          }  
      }  
  }); 
  txt_OBS.addMouseListener(new MouseAdapter() {  
        public void mouseClicked(MouseEvent me) {  
          //Verificando se o botão direito do mouse foi clicado  
          if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {  
              JPopupMenu menu = new JPopupMenu();  
              add(menu);  
              JMenuItem item = new JMenuItem("Copiar");  
              menu.add(item); 
                item.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                    });
              JMenuItem item2 = new JMenuItem("Recortar");  
              menu.add(item2); 
                item2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                });
              JMenuItem item3 = new JMenuItem("Colar");  
              menu.add(item3);
                item3.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){ 

                        }
                });
              menu.show(menu, me.getX(), me.getY());  
  
          }  
      }  
  });   
  txt_Preco.addMouseListener(new MouseAdapter() {  
        public void mouseClicked(MouseEvent me) {  
          //Verificando se o botão direito do mouse foi clicado  
          if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {  
              JPopupMenu menu = new JPopupMenu();  
              add(menu);  
              JMenuItem item = new JMenuItem("Copiar");  
              menu.add(item); 
                item.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                    });
              JMenuItem item2 = new JMenuItem("Recortar");  
              menu.add(item2); 
                item2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                });
              JMenuItem item3 = new JMenuItem("Colar");  
              menu.add(item3);
                item3.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){ 

                        }
                });
              menu.show(menu, me.getX(), me.getY());  
  
          }  
      }  
  });  
  txt_Cutter.addMouseListener(new MouseAdapter() {  
        public void mouseClicked(MouseEvent me) {  
          //Verificando se o botão direito do mouse foi clicado  
          if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {  
              JPopupMenu menu = new JPopupMenu();  
              add(menu);  
              JMenuItem item = new JMenuItem("Copiar");  
              menu.add(item); 
                item.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                    });
              JMenuItem item2 = new JMenuItem("Recortar");  
              menu.add(item2); 
                item2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                });
              JMenuItem item3 = new JMenuItem("Colar");  
              menu.add(item3);
                item3.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){ 

                        }
                });
              menu.show(menu, me.getX(), me.getY());  
  
          }  
      }  
  });  
  txt_cdd.addMouseListener(new MouseAdapter() {  
        public void mouseClicked(MouseEvent me) {  
          //Verificando se o botão direito do mouse foi clicado  
          if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {  
              JPopupMenu menu = new JPopupMenu();  
              add(menu);  
              JMenuItem item = new JMenuItem("Copiar");  
              menu.add(item); 
                item.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                    });
              JMenuItem item2 = new JMenuItem("Recortar");  
              menu.add(item2); 
                item2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                });
              JMenuItem item3 = new JMenuItem("Colar");  
              menu.add(item3);
                item3.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){ 

                        }
                });
              menu.show(menu, me.getX(), me.getY());  
  
          }  
      }  
  });  
  txt_assuntos.addMouseListener(new MouseAdapter() {  
        public void mouseClicked(MouseEvent me) {  
          //Verificando se o botão direito do mouse foi clicado  
          if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {  
              JPopupMenu menu = new JPopupMenu();  
              add(menu);  
              JMenuItem item = new JMenuItem("Copiar");  
              menu.add(item); 
                item.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                    });
              JMenuItem item2 = new JMenuItem("Recortar");  
              menu.add(item2); 
                item2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                });
              JMenuItem item3 = new JMenuItem("Colar");  
              menu.add(item3);
                item3.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){ 

                        }
                });
              menu.show(menu, me.getX(), me.getY());  
  
          }  
      }  
  });  
 
  txt_tradutor.addMouseListener(new MouseAdapter() {  
        public void mouseClicked(MouseEvent me) {  
          //Verificando se o botão direito do mouse foi clicado  
          if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {  
              JPopupMenu menu = new JPopupMenu();  
              add(menu);  
              JMenuItem item = new JMenuItem("Copiar");  
              menu.add(item); 
                item.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                    });
              JMenuItem item2 = new JMenuItem("Recortar");  
              menu.add(item2); 
                item2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                });
              JMenuItem item3 = new JMenuItem("Colar");  
              menu.add(item3);
                item3.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){ 

                        }
                });
              menu.show(menu, me.getX(), me.getY());  
  
          }  
      }  
  });  
  txt_edicao.addMouseListener(new MouseAdapter() {  
        public void mouseClicked(MouseEvent me) {  
          //Verificando se o botão direito do mouse foi clicado  
          if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {  
              JPopupMenu menu = new JPopupMenu();  
              add(menu);  
              JMenuItem item = new JMenuItem("Copiar");  
              menu.add(item); 
                item.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                    });
              JMenuItem item2 = new JMenuItem("Recortar");  
              menu.add(item2); 
                item2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                });
              JMenuItem item3 = new JMenuItem("Colar");  
              menu.add(item3);
                item3.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){ 

                        }
                });
              menu.show(menu, me.getX(), me.getY());  
  
          }  
      }  
  }); 
  txt_Nota.addMouseListener(new MouseAdapter() {  
        public void mouseClicked(MouseEvent me) {  
          //Verificando se o botão direito do mouse foi clicado  
          if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {  
              JPopupMenu menu = new JPopupMenu();  
              add(menu);  
              JMenuItem item = new JMenuItem("Copiar");  
              menu.add(item); 
                item.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                    });
              JMenuItem item2 = new JMenuItem("Recortar");  
              menu.add(item2); 
                item2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                });
              JMenuItem item3 = new JMenuItem("Colar");  
              menu.add(item3);
                item3.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){ 

                        }
                });
              menu.show(menu, me.getX(), me.getY());  
  
          }  
      }  
  });  
  txt_VSerie.addMouseListener(new MouseAdapter() {  
        public void mouseClicked(MouseEvent me) {  
          //Verificando se o botão direito do mouse foi clicado  
          if ((me.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {  
              JPopupMenu menu = new JPopupMenu();  
              add(menu);  
              JMenuItem item = new JMenuItem("Copiar");  
              menu.add(item); 
                item.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                    });
              JMenuItem item2 = new JMenuItem("Recortar");  
              menu.add(item2); 
                item2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){

                        }
                });
              JMenuItem item3 = new JMenuItem("Colar");  
              menu.add(item3);
                item3.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){ 

                        }
                });
              menu.show(menu, me.getX(), me.getY());  
  
          }  
      }  
  });  
   //fim botão direito 
        miEditora.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            try{
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String str_Nova_Editora = JOptionPane.showInputDialog("Digite o nome da Editora:");
                String SQL_CHECA_EXISTENCIA_EDITORA = "select * from editora where nome = '"+str_Nova_Editora+"'";
                ResultSet resultSet = statement.executeQuery(SQL_CHECA_EXISTENCIA_EDITORA);
                resultSet.last();
                int num_linhas = resultSet.getRow();
                resultSet.beforeFirst();
                if(num_linhas > 0){
                    JOptionPane.showMessageDialog(null, "Esta Editora ja está cadastrada");
                    return;
                }
                else{
                    int id_Editora = (int)(Math.random() * 1000000000);
                    String SQL_CADASTRA_EDITORA = "insert into editora (id_Editora, nome) values("+id_Editora+", '"+str_Nova_Editora+"')";
                    System.out.println(SQL_CADASTRA_EDITORA);
                    try{
                        statement.executeUpdate(SQL_CADASTRA_EDITORA);
                    }
                    catch(Exception e4){
                        System.out.println("erro 4: "+e4);
                        JOptionPane.showMessageDialog(null, "erro 4 : "+e4);
                    }
                }
                combo_Editora.removeAllItems();
                String SQL_RECARREGA_EDITORA = "SELECT * FROM editora ORDER BY nome";
                resultSet = statement.executeQuery(SQL_RECARREGA_EDITORA);
                System.out.println("Recarregar Editora");
                resultSet.beforeFirst();
                while(resultSet.next()){
                combo_Editora.addItem(resultSet.getString("nome"));
                }
                combo_Editora.setSelectedItem(str_Nova_Editora);
             }
            catch(Exception e2){
                System.out.println("Erro 2: "+ e2);
            }
          }
        });
        miTipo.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            try{
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String str_Novo_Tipo = JOptionPane.showInputDialog("Digite o Tipo da Obra:");
                String SQL_CHECA_EXISTENCIA_TIPO = "select * from tipo where tipo = '"+str_Novo_Tipo+"'";
                ResultSet resultSet = statement.executeQuery(SQL_CHECA_EXISTENCIA_TIPO);
                resultSet.last();
                int num_linhas = resultSet.getRow();
                resultSet.beforeFirst();
                if(num_linhas > 0){
                    JOptionPane.showMessageDialog(null, "Este Tipo de Obra Já está cadastrada");
                    return;
                }
                else{
                    int id_Tipo = (int)(Math.random() * 1000000000);
                    String SQL_CADASTRA_TIPO = "insert into tipo (id_tipo, tipo) values("+id_Tipo+", '"+str_Novo_Tipo+"')";
                    System.out.println(SQL_CADASTRA_TIPO);
                    try{
                    statement.executeQuery(SQL_CADASTRA_TIPO);
                    }
                    catch(Exception e4){
                        System.out.println(e4);
                        JOptionPane.showMessageDialog(null, "erro: "+e4);
                    }
                }
                combo_Tipo.removeAllItems();
                String SQL_RECARREGA_TOMBO = "SELECT * FROM tipo ORDER BY tipo";
                resultSet = statement.executeQuery(SQL_RECARREGA_TOMBO);
                resultSet.beforeFirst();
                while(resultSet.next()){
                combo_Tipo.addItem(resultSet.getString("tipo"));
                }
                combo_Tipo.setSelectedItem(str_Novo_Tipo);
             }
            catch(Exception e2){
                System.out.println("Erro 2: "+ e2);
            }
          }
        });
        miTipo2.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            String str_Deleta_Tipo = JOptionPane.showInputDialog("Digite o Tipo a ser deletado");
            }
        });
        bt_Cancelar.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
             Cad_Obra.this.dispose();
            }
        });
        combo_Editora.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
               String id_Editora="";
               
               try{
                    Statement statement2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    String SQL_BUSCA_EDITORA = "select * from editora where nome = '"+combo_Editora.getSelectedItem()+"'";
                    resultSet2 = statement2.executeQuery(SQL_BUSCA_EDITORA);
                    System.out.println(SQL_BUSCA_EDITORA);
                    while (resultSet2.next()){
                        id_Editora = resultSet2.getString("id_editora").toString();
                        System.out.println("editora: "+id_Editora);
                    }
                    SQL_BUSCA_EDITORA = "select local_publicacao from livro where id_editora = '"+id_Editora+"'";
                    resultSet2 = statement2.executeQuery(SQL_BUSCA_EDITORA);
                    System.out.println(SQL_BUSCA_EDITORA);
                    resultSet2.first();
                    txt_Publicacao.setText(resultSet2.getString("local_publicacao"));
               }
               catch(Exception eee){
                    
               }
            }        
        });
        bt_Cadastrar.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            NewTombo();
            String  str_Tipo="", str_Editora="", str_Autor="", str_Publicacao="";
            String str_Ano="", str_Preco="", str_Volume="", str_Tombo="";
            String str_Paginas="", str_Categoria="", str_SubCategoria="", str_OBS="", str_Cutter="";
            String str_edicao="",str_assunto="",str_tradutor="",str_serie="",str_colecao="";
            String str_tituloori="",str_notas="",str_origem="",vs="";
            if(combo_origem.getSelectedIndex()==1)
                str_Preco = JOptionPane.showInputDialog("Qual o valor do livro?");
            str_Categoria = txt_cdd.getText();
            str_edicao=txt_edicao.getText().toString();
            str_assunto=txt_assuntos.getText().toString();
            str_tradutor=txt_tradutor.getText().toString();
            str_serie=txt_serie.getText().toString();
            str_Editora = combo_Editora.getSelectedItem().toString();
            str_Tipo = combo_Tipo.getSelectedItem().toString();
            str_Paginas = txt_Paginas.getText();
            str_Volume = txt_Volume.getText();
            str_OBS = txt_OBS.getText();
            str_Autor = txt_Autor.getText();
            str_Titulo = txt_Titulo.getText();
            str_Publicacao = txt_Publicacao.getText();
            str_Ano = txt_Ano.getText();
            str_ISBN = txt_ISBN.getText();
            str_Cutter = txt_Cutter.getText();
            str_colecao=txt_colecao.getText();
            str_tituloori=txt_tituloori.getText();
            str_notas=txt_Nota.getText();
            vs=txt_VSerie.getText();
            str_origem=combo_origem.getSelectedItem().toString(); 
            
            if(txt_Cutter.getText().equals("")) { JOptionPane.showMessageDialog(null, "Digite o cutter da Obra"); return; }
            if(txt_Paginas.getText().equals("")) { str_Paginas = "0"; }
            if(txt_Volume.getText().equals("")) { str_Volume = "0"; }
            if(txt_Preco.getText().replace(" ", "").equals("")) { str_Preco = "não cadastrado"; }
            if(txt_ISBN.getText().equals("")) { str_ISBN = ""; }
            if(txt_Publicacao.getText().equals("")) { str_Publicacao = "Não Cadastrado"; }
            if(txt_Ano.getText().equals("")) { str_Publicacao = "0"; }
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
                int cont=1;
                while (cont>0){
                try{
                     String SQL_TIPO = "SELECT count(NTOMBO)as total from livro where NTOMBO='"+NTOMBO+"'";
                     statement = connection.prepareStatement(SQL_TIPO);
                     resultSet = statement.executeQuery();
                     resultSet.last();
                     if(resultSet.getInt("total")>0)
                         NTOMBO++;
                     else
                         cont=0;
                }
                catch(Exception y){
                    JOptionPane.showMessageDialog(null, y);
                }
                }
                System.out.println("vai cadastrar a obra");
                //continuar cadastro sql...........................................
                id_Cutter = "0";
                System.out.println(id_Cutter);
                System.out.println("preco: "+str_Preco);
                String SQL_CADASTRA_OBRA = "insert into livro(id_livro, data_cadastro, titulo, id_editora, num_pag,"
                    +"volume, local_publicacao, ano_publicacao, preco,"
                    +"cdu, id_prefixo, id_tipo, isbn, tombo, autor, cutter,tradutor,assuntos,serie,edicao,colecao,titulooriginal,NTOMBO,notas,origem,exemplar,vs) values("
                    +id_Livro+", '"+data+"', '"+str_Titulo+"', "+id_Editora+", "+str_Paginas+","
                    +str_Volume+",'"+str_Publicacao+"' ,'"+str_Ano+"','"+str_Preco+"', '"
                    +str_Categoria+"', '"+id_Cutter+"', "+id_Tipo+", '"+str_ISBN+"', '"+str_Tombo+"', '"+str_Autor+"', '"+str_Cutter+"', '"
                    +str_tradutor+"', '"+str_assunto+"', '"+str_serie+"', '"+str_edicao+"','"+str_colecao+"','"+str_tituloori+"','"+NTOMBO+"','"+str_notas+"','"+str_origem+"',"+exemplar+",'"+vs+"')";
                System.out.println(SQL_CADASTRA_OBRA);
                try{
                    statement.executeUpdate(SQL_CADASTRA_OBRA);
                    JOptionPane.showMessageDialog(null, "Obra Cadastrada com sucesso!\nExemplar Nº:"+exemplar+"\n Código(NTOMBO)= "+NTOMBO);
                    ReCarrega_Combo_Tipo(str_Tipo);
                    ReCarrega_Combo_Editora(str_Editora);
                    String[] opcao = {"SIM","NÃO"};
                    if(JOptionPane.showOptionDialog(null,"Deseja cadastrar outro livro igual?","APAGAR?",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcao, true )==JOptionPane.YES_OPTION){
                       txt_Tombo.setText("");
                       NewTombo();
                       ContExemplar();
                       txt_Tombo.requestFocus();
                    }
                    else{
                        txt_VSerie.setText("");
                        txt_Titulo.setText("");
                        txt_Autor.setText("");
                        //txt_Publicacao.setText("");
                        txt_Ano.setText("");
                        txt_ISBN.setText("");
                        txt_Volume.setText("");
                        txt_Tombo.setText("");
                        txt_Paginas.setText("");
                        txt_OBS.setText("");
                        txt_Preco.setText("00,00");
                        txt_Cutter.setText("");
                        txt_cdd.setText("");
                        txt_assuntos.setText("");
                        txt_tradutor.setText("");
                        txt_edicao.setText("");
                        txt_serie.setText("");
                        txt_tituloori.setText("");
                        txt_Nota.setText("");
                        txt_colecao.setText("");
                        combo_origem.setSelectedIndex(0);
                        NewTombo();
                        txt_Titulo.requestFocus();
                    }
                }
                catch(Exception ee){
                        JOptionPane.showMessageDialog(null, "erro : "+ee);
                        JOptionPane.showMessageDialog(null, "Erro no Cadastro, Tente novamente mais tarde.");
                }
             }
            catch(Exception erro){
              JOptionPane.showMessageDialog(null, "erro: "+erro);
            }
         }
        });
    }
    public void Carrega_Combo_Tipo(){
        add(combo_Tipo);
        try{
            combo_Tipo.setBounds(519, 150,90,  25);
            String SQL_TIPO = "SELECT * FROM tipo order by id_tipo asc";
            statement = connection.prepareStatement(SQL_TIPO);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                  combo_Tipo.addItem(resultSet.getString("tipo"));
            }  
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    public void ReCarrega_Combo_Tipo(String str_Tipo){
        try{
            combo_Tipo.removeAllItems();
            String SQL_TIPO = "SELECT * FROM tipo";
            statement = connection.prepareStatement(SQL_TIPO);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                 combo_Tipo.addItem(resultSet.getString("tipo"));
            }
            combo_Tipo.setSelectedItem(str_Tipo);
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    public void Carrega_Combo_Editora(){
        add(combo_Editora);
        try{
           combo_Editora.setBounds(108,150,320,25);
           String SQL_TIPO = "SELECT * FROM editora order by nome";
           statement = connection.prepareStatement(SQL_TIPO);
           resultSet = statement.executeQuery();
           while (resultSet.next()){
                 combo_Editora.addItem(resultSet.getString("nome").toUpperCase());
            }
            add(combo_Editora);
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(rootPane, e+"--1");
        }
    }
    public void ReCarrega_Combo_Editora(String str_Editora){
        try{
           combo_Editora.removeAllItems();
           String SQL_Editora = "SELECT * FROM editora order by nome";
           statement = connection.prepareStatement(SQL_Editora);
           resultSet = statement.executeQuery();
           while (resultSet.next()){
                combo_Editora.addItem(resultSet.getString("nome").toUpperCase());
           }
           combo_Editora.setSelectedItem(str_Editora);
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(rootPane, e+"--2");
        }
    }
    public void NewTombo(){
      
        try{
            String SQL_TIPO = "SELECT count(NTOMBO)as total from livro ";
            statement = connection.prepareStatement(SQL_TIPO);
            resultSet = statement.executeQuery();
            resultSet.last();
            String num1= resultSet.getString("total");
            NTOMBO=Integer.parseInt(num1)+1;
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

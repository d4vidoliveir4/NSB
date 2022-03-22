package biblioteca;
import java.io.*;
import java.sql.*;
import javax.swing.JOptionPane;
public class Gerar_Backup{
    String NTOMBO,tombo,cutter,cdd,exemplar="",volume="",edicao="";
    public Connection connection;
    public PreparedStatement statement;
    public ResultSet resultSet;
    String nome;
    public String etiquetas="";
    int cont,fin,op;
    public static String DRIVER = "com.mysql.jdbc.Driver";
    public static String con_usuario, con_senha, URL, con_porta, con_bd, con_pc;

public void gerar_backup(){
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
    catch(Exception err){
        JOptionPane.showMessageDialog(null,err);
    }
    String[] opcao = {"Livros","Tudo"};
    if(JOptionPane.showOptionDialog(null,"Gerar backup de?","Gerar?",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcao, true )==JOptionPane.YES_OPTION){
     livros();
     JOptionPane.showMessageDialog(null,"Copia de segurança finalizada.\n Verifique a pasta 'C:/NSB/backup' e copie todos os arquivos dela.");
    }
    else{
        editora();
        emprestimo();
        funcionario();
        livros();
        multa();
        reserva();
        tipo();
        usuario(); 
        JOptionPane.showMessageDialog(null,"Copia de segurança finalizada.\n Verifique a pasta 'C:/NSB/backup' e copie todos os arquivos dela.");
    }
}
public void editora(){
        try{
            String sql = "Select * from editora";
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
            PrintWriter out = new PrintWriter("C:/NSB/backup/editora.txt"); 
            while(resultSet.next()){
                out.println("('"+resultSet.getString("id_editora")+"','"+resultSet.getString("nome")+"')");
            }
            out.close();
        }
        catch(Exception eee){
            JOptionPane.showMessageDialog(null, eee);
        }
}
public void emprestimo(){
        try{
            String sql = "Select * from emprestimo";
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
            PrintWriter out = new PrintWriter("C:/NSB/backup/emprestimo.txt"); 
            while(resultSet.next()){
                out.println("('"+resultSet.getString("id_emprestimo")+"','"+resultSet.getString("id_livro")+"','"+resultSet.getString("id_usuario")+"','"+resultSet.getString("id_funcionario")+"','"+resultSet.getString("de")+"','"+resultSet.getString("para")+"','"+resultSet.getString("devolucao")+"','"+resultSet.getString("devolvido")+"','"+resultSet.getString("caminho")+"')");
            }
            out.close();
        }
        catch(Exception eee){
            JOptionPane.showMessageDialog(null, eee);
        }
}
public void funcionario(){
        try{
            String sql = "Select * from funcionario";
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
            PrintWriter out = new PrintWriter("C:/NSB/backup/funcionario.txt"); 
            while(resultSet.next()){
                out.println("('"+resultSet.getString("id_funcionario")+"','"+resultSet.getString("nome")+"','"+resultSet.getString("data_nasc")+"','"+resultSet.getString("data_cadastro")+"','"+resultSet.getString("RG")+"','"+resultSet.getString("rua")+"','"+resultSet.getString("numero")+"','"+resultSet.getString("complemento")+"','"+resultSet.getString("bairro")+"','"+resultSet.getString("telefone")+"','"+resultSet.getString("celular")+"','"+resultSet.getString("email")+"','"+resultSet.getString("cidade")+"','"+resultSet.getString("login")+"','"+resultSet.getString("senha")+"','"+resultSet.getString("empresta")+"')");
            }
            out.close();
        }
        catch(Exception eee){
            JOptionPane.showMessageDialog(null, eee);
        }
}

    public void livros(){
        try{
            String sql = "Select * from livro";
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
            PrintWriter out = new PrintWriter("C:/NSB/backup/livros.txt"); 
            while(resultSet.next()){
                out.println("('"+resultSet.getString("id_livro")+"','"+resultSet.getString("data_cadastro")+"','"+resultSet.getString("titulo")+"','"+resultSet.getString("id_editora")+"','"+resultSet.getString("num_pag")+"','"+resultSet.getString("volume")+"','"+resultSet.getString("local_publicacao")+"','"+resultSet.getString("ano_publicacao")+"','"+resultSet.getString("preco")+"','"+resultSet.getString("obs")+"','"+resultSet.getString("cdu")+"','"+resultSet.getString("id_prefixo")+"','"+resultSet.getString("id_tipo")+"','"+resultSet.getString("isbn")+"','"+resultSet.getString("tombo")+"','"+resultSet.getString("autor")+"','"+resultSet.getString("cutter")+"','"+resultSet.getString("disponivel")+"','"+resultSet.getString("tradutor")+"','"+resultSet.getString("assuntos")+"','"+resultSet.getString("serie")+"','"+resultSet.getString("edicao")+"','"+resultSet.getString("colecao")+"','"+resultSet.getString("titulooriginal")+"','"+resultSet.getString("NTOMBO")+"','"+resultSet.getString("notas")+"','"+resultSet.getString("origem")+"','"+resultSet.getString("exemplar")+"','"+resultSet.getString("vs")+"')");
            }
            out.close();
        }
        catch(Exception eee){
            JOptionPane.showMessageDialog(null, eee);
        }
    }
    public void multa(){
        try{
            String sql = "Select * from multa";
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
            PrintWriter out = new PrintWriter("C:/NSB/backup/multa.txt"); 
            while(resultSet.next()){
                out.println("('"+resultSet.getString("id_multa")+"','"+resultSet.getString("valor")+"','"+resultSet.getString("id_emprestimo")+"','"+resultSet.getString("pago")+"')");
            }
            out.close();
        }
        catch(Exception eee){
            JOptionPane.showMessageDialog(null, eee);
        }
}
    public void reserva(){
        try{
            String sql = "Select * from reserva";
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
            PrintWriter out = new PrintWriter("C:/NSB/backup/reserva.txt"); 
            while(resultSet.next()){
                out.println("('"+resultSet.getString("id_reserva")+"','"+resultSet.getString("id_livro")+"','"+resultSet.getString("id_usuario")+"','"+resultSet.getString("id_funcionario")+"','"+resultSet.getString("de")+"','"+resultSet.getString("para")+"','"+resultSet.getString("concluida")+"')");
            }
            out.close();
        }
        catch(Exception eee){
            JOptionPane.showMessageDialog(null, eee);
        }
}
    public void tipo(){
        try{
            String sql = "Select * from tipo";
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
            PrintWriter out = new PrintWriter("C:/NSB/backup/tipo.txt"); 
            while(resultSet.next()){
                out.println("('"+resultSet.getString("id_tipo")+"','"+resultSet.getString("tipo")+"')");
            }
            out.close();
        }
        catch(Exception eee){
            JOptionPane.showMessageDialog(null, eee);
        }
}
    public void usuario(){
        try{
            String sql = "Select * from usuario";
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
            PrintWriter out = new PrintWriter("C:/NSB/backup/usuario.txt"); 
            while(resultSet.next()){
                out.println("('"+resultSet.getString("id_usuario")+"','"+resultSet.getString("nome")+"','"+resultSet.getString("rg")+"','"+resultSet.getString("curso")+"','"+resultSet.getString("periodo")+"','"+resultSet.getString("serie")+"','"+resultSet.getString("RM")+"','"+resultSet.getString("data_nasc")+"','"+resultSet.getString("data_insc")+"','"+resultSet.getString("numero")+"','"+resultSet.getString("complemento")+"','"+resultSet.getString("bairro")+"','"+resultSet.getString("cidade")+"','"+resultSet.getString("telefone")+"','"+resultSet.getString("celular")+"','"+resultSet.getString("email")+"','"+resultSet.getString("rua")+"','"+resultSet.getString("desativado")+"')");
            }
            out.close();
        }
        catch(Exception eee){
            JOptionPane.showMessageDialog(null, eee);
        }
    }
    
}
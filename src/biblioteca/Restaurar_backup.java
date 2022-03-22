package biblioteca;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Restaurar_backup {
   public Connection connection;
   public static String DRIVER = "com.mysql.jdbc.Driver";
   public static String con_usuario, con_senha, URL;
   String values,sql;
   public void inicia(){
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
        String[] opcao = {"Continuar","Cancelar"};
        if(JOptionPane.showOptionDialog(null,"Esse processo ira apagar todas as informações atuais\ne substituir pelas salvas no backup!\nDeseja continuar?","ATENÇÂO!",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcao, true )==JOptionPane.YES_OPTION){
        restaurareditora();
        restauraremprestimo();
        restaurarfuncionario();
        restaurarlivro();
        restaurarmulta();
        restaurarreserva();
        restaurartipo();
        restaurarusuario();
        JOptionPane.showMessageDialog(null,"Processo finalizado!\nVerifique as informações e se necessario repita o processo.");
        }
   }
   public void restaurareditora(){
       try{         
            InputStream is = new FileInputStream("C:/NSB/backup/editora.txt"); 
            InputStreamReader isr = new InputStreamReader(is); 
            BufferedReader br = new BufferedReader(isr); 
            values=br.readLine();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "DELETE FROM editora";
            System.out.println(sql);
            statement.execute(sql);
            while(values!=""||values!=null){
                sql = "INSERT INTO editora (id_editora, nome) VALUES"+values;
                System.out.println(sql);
                statement.executeUpdate(sql);
                values=br.readLine();
            }
       }   catch(Exception ee){
          
       }
   }
   public void restauraremprestimo(){
       try{         
            InputStream is = new FileInputStream("C:/NSB/backup/emprestimo.txt"); 
            InputStreamReader isr = new InputStreamReader(is); 
            BufferedReader br = new BufferedReader(isr); 
            values=br.readLine();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "DELETE FROM emprestimo";
            System.out.println(sql);
            statement.execute(sql);
            while(values!=""||values!=null){
                sql = "INSERT INTO emprestimo (id_emprestimo, id_livro, id_usuario, id_funcionario, de, para, devolucao, devolvido, caminho) VALUES "+values;
                System.out.println(sql);
                statement.executeUpdate(sql);
                values=br.readLine();
            }
            is.close();
            isr.close();
            br.close();
       }   catch(Exception ee){
          
       }
   }
   public void restaurarfuncionario(){
       try{         
            InputStream is = new FileInputStream("C:/NSB/backup/funcionario.txt"); 
            InputStreamReader isr = new InputStreamReader(is); 
            BufferedReader br = new BufferedReader(isr); 
            values=br.readLine();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "DELETE FROM funcionario";
            System.out.println(sql);
            statement.execute(sql);
            while(values!=""||values!=null){
                sql = "INSERT INTO funcionario (id_funcionario, nome, data_nasc, data_cadastro, RG, rua, numero, complemento, bairro, telefone, celular, email, cidade, login, senha, empresta) VALUES"+values;
                System.out.println(sql);
                statement.executeUpdate(sql);
                values=br.readLine();
            }
            is.close();
            isr.close();
            br.close();
       }   catch(Exception ee){
          
       }
   }
      public void restaurarlivro(){
       try{         
            InputStream is = new FileInputStream("C:/NSB/backup/livros.txt");
            InputStreamReader isr = new InputStreamReader(is); 
            BufferedReader br = new BufferedReader(isr); 
            values=br.readLine();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "DELETE FROM livro";
            System.out.println(sql);
            statement.execute(sql);
            while(values!=""||values!=null){
                sql = "INSERT INTO livro (id_livro, data_cadastro, titulo, id_editora, num_pag, volume, local_publicacao, ano_publicacao, preco, obs, cdu, id_prefixo, id_tipo, isbn, tombo, autor, cutter, disponivel, tradutor, assuntos, serie, edicao, colecao, titulooriginal, NTOMBO, notas, origem, exemplar, vs) VALUES "+values;
                System.out.println(sql);
                statement.executeUpdate(sql);
                values=br.readLine();
            }
            is.close();
            isr.close();
            br.close();
       }   catch(Exception ee){
          JOptionPane.showMessageDialog(null, ee);
       }
   }
   public void restaurarmulta(){
       try{         
            InputStream is = new FileInputStream("C:/NSB/backup/multa.txt"); 
            InputStreamReader isr = new InputStreamReader(is); 
            BufferedReader br = new BufferedReader(isr); 
            values=br.readLine();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "DELETE FROM multa";
            System.out.println(sql);
            statement.execute(sql);
            while(values!=""||values!=null){
                sql = "INSERT INTO multa (id_multa, valor, id_emprestimo, pago) VALUES "+values;
                System.out.println(sql);
                statement.executeUpdate(sql);
                values=br.readLine();
            }
            is.close();
            isr.close();
            br.close();
       }   catch(Exception ee){
          
       }
   }
   public void restaurarreserva(){
       try{         
            InputStream is = new FileInputStream("C:/NSB/backup/reserva.txt"); 
            InputStreamReader isr = new InputStreamReader(is); 
            BufferedReader br = new BufferedReader(isr); 
            values=br.readLine();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "DELETE FROM reserva";
            System.out.println(sql);
            statement.execute(sql);
            while(values!=""||values!=null){
                sql = "INSERT INTO reserva (id_reserva, id_livro, id_usuario, id_funcionario, de, para, concluida) VALUES"+values;
                System.out.println(sql);
                statement.executeUpdate(sql);
                values=br.readLine();
            }
            is.close();
            isr.close();
            br.close();
       }   catch(Exception ee){
          
       }
   }
      public void restaurartipo(){
       try{         
            InputStream is = new FileInputStream("C:/NSB/backup/tipo.txt"); 
            InputStreamReader isr = new InputStreamReader(is); 
            BufferedReader br = new BufferedReader(isr); 
            values=br.readLine();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "DELETE FROM tipo";
            System.out.println(sql);
            statement.execute(sql);
            while(values!=""||values!=null){
                sql = "INSERT INTO tipo (id_tipo, tipo) VALUES"+values;
                System.out.println(sql);
                statement.executeUpdate(sql);
                values=br.readLine();
            }
            is.close();
            isr.close();
            br.close();
       }   catch(Exception ee){
          
       }
   }
     public void restaurarusuario(){
       try{         
            InputStream is = new FileInputStream("C:/NSB/backup/usuario.txt"); 
            InputStreamReader isr = new InputStreamReader(is); 
            BufferedReader br = new BufferedReader(isr); 
            values=br.readLine();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "DELETE FROM usuario";
            System.out.println(sql);
            statement.execute(sql);
            while(values!=""||values!=null){
                sql = "INSERT INTO usuario(id_usuario, nome, rg, curso, periodo, serie, RM, data_nasc, data_insc, numero, complemento, bairro, cidade, telefone, celular, email, rua, desativado) VALUES"+values;
                System.out.println(sql);
                statement.executeUpdate(sql);
                values=br.readLine();
            }
            is.close();
            isr.close();
            br.close();
       }   catch(Exception ee){
       JOptionPane.showMessageDialog(null, ee);
       }
   }
}

package biblioteca;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class Conexao {
   public Connection connection;
   public static String DRIVER = "com.mysql.jdbc.Driver";
   public static String con_usuario="root", con_senha="toor", URL, con_porta, con_bd, con_pc;
   public String URL(){
       try{ 
            // 
             // is representa um fluxo de entrada a partir  de um arquivo 
            InputStream is = new FileInputStream("C:/NSB/op.conf"); 
           
            // 
             //InputStreamReader é uma classe para converter os bytes em char 
            InputStreamReader isr = new InputStreamReader(is); 
            // 
             //BufferedReader é uma classe para armazenar os chars em memoria 
            BufferedReader br = new BufferedReader(isr); 
            con_pc=br.readLine();
            con_bd=br.readLine();
            con_porta=br.readLine();
            con_usuario=br.readLine();
            con_senha=br.readLine(); 
       }   catch(Exception ee){
           
       }
       String con_inicio = "jdbc:mysql://";
       URL = con_inicio + con_pc + ":" + con_porta + "/" + con_bd;
       return URL;
   }
   public String con_usuario(){
      return con_usuario;
   }
   public String con_senha(){
       return con_senha;
   }
}

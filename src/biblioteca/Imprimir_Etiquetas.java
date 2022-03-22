package biblioteca;
import java.io.*;
import java.sql.*;
import javax.swing.JOptionPane;
public class Imprimir_Etiquetas{
    String NTOMBO,tombo,cutter,cdd,exemplar="",edicao="";
    double volume=0;
    public Connection connection;
    public PreparedStatement statement;
    public ResultSet resultSet;
    String nome;
    public String etiquetas="";
    int cont,fin,op;
    public static String DRIVER = "com.mysql.jdbc.Driver";
    public static String con_usuario, con_senha, URL, con_porta, con_bd, con_pc;

public void carregaLivros(){
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
    String[] opcao = {"UM","VARIOS"};
    if(JOptionPane.showOptionDialog(null,"Deseja criar etiquetas para um unico livro ou para varios?","Gerar?",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcao, true )==JOptionPane.YES_OPTION){
     cont=Integer.parseInt(JOptionPane.showInputDialog("Qual o tombo?"));
     op=1;
    }
    else{
     cont=Integer.parseInt(JOptionPane.showInputDialog("Você quer etiquetas a partir de qual tombo?"));
     op=0; 
    }
    if(op==0){
  try{
    cont=Integer.parseInt(JOptionPane.showInputDialog("Você quer etiquetas a partir de qual tombo?"));
    String sql = "Select NTOMBO from livro order by NTOMBO ";
    Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    resultSet = statement.executeQuery(sql);
    resultSet.last();
    fin=resultSet.getInt("NTOMBO");
    resultSet.close();
    statement.close();
    while(cont<=fin){
    try{
    sql = "Select cdu,cutter,edicao,tombo,exemplar,volume from livro where NTOMBO='"+cont+"'";
    statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    resultSet = statement.executeQuery(sql);
    resultSet.first();        
        cdd=resultSet.getString("cdu");
        cutter=resultSet.getString("cutter");
        tombo=resultSet.getString("tombo");
        exemplar=resultSet.getString("exemplar");
        volume=resultSet.getDouble("volume");
        edicao=resultSet.getString("edicao");
        etiquetas= etiquetas+"<br>"+cdd+"<br>"+cutter;
        if(edicao!="")
            etiquetas=etiquetas+"<br>ed."+edicao;
        if(volume!=0)
            etiquetas=etiquetas+"<br>v."+volume+"/";
        
        etiquetas=etiquetas+"ex."+exemplar+"<br><b>"+tombo+"</b><br>";
        resultSet.close();
        statement.close();
        cont++;
    }
    catch(Exception eee){
      JOptionPane.showMessageDialog(null,eee);
    }
  }
  
}
catch(Exception ert){
        JOptionPane.showMessageDialog(null,ert);
    }
  }
  else{
      try{
        String sql = "Select cdu,cutter,edicao,tombo,exemplar,volume from livro where NTOMBO='"+cont+"'";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery(sql);
        resultSet.first();        
        cdd=resultSet.getString("cdu");
        cutter=resultSet.getString("cutter");
        tombo=resultSet.getString("tombo");
        exemplar=resultSet.getString("exemplar");
        volume=resultSet.getDouble("volume");
        edicao=resultSet.getString("edicao");
        etiquetas= etiquetas+"<br>"+cdd+"<br>"+cutter;
        if(edicao!="")
            etiquetas=etiquetas+"<br>ed."+edicao;
        if(volume!=0)
            etiquetas=etiquetas+"<br>v."+volume+"/";
        etiquetas=etiquetas+"ex."+exemplar+"<br><b>"+tombo+"</b><br>";
        resultSet.close();
        statement.close();
        cont++;
    }
    catch(Exception eee){
      JOptionPane.showMessageDialog(null,eee);
    }  
    }
  gerar();
}    
public void gerar(){
 try{
       PrintWriter out = new PrintWriter("C:/NSB/imprimir/etiquetas.html");
       out.println("<html><body><center>");
       out.println(etiquetas);
       out.println("</center></body></html>");
       out.close();
       JOptionPane.showMessageDialog(null, "Arquivo gravado com sucesso!\n C:/NSB/imprimir/etiquetas.html");
     }
     catch(IOException erro){
        JOptionPane.showMessageDialog(null, "Erro ao gravar no arquivo");
        return;
     }
 try{  
         Runtime.getRuntime().exec("cmd.exe /c start firefox.exe file:///C:/NSB/imprimir/etiquetas.html");  
      }  
      catch(IOException iOException){  
         JOptionPane.showMessageDialog(null,iOException); 
         JOptionPane.showMessageDialog(null,"Tente abrir o arquivo manualmente");
      }  
  }
}
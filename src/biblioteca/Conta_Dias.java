package biblioteca;
import java.util.Date;  
import java.text.ParseException;  
import java.text.SimpleDateFormat;  
public class Conta_Dias{  
   public String converteDataBrasil(String data){     
      String dataConvertida = "";        
      try{  
          SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy/MM/dd");  
          SimpleDateFormat ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");  
          dataConvertida = ddMMyyyy.format(yyyyMMdd.parse(verificaMascaraData(data)));                  
      }  
      catch(Exception e){}    
      return dataConvertida;        
   }  
   /*
   Se a data vier separada por "-" 
   Será trocado por "/" 
   */  
   public String verificaMascaraData(String data){  
      for (int i = 0; i < data.length(); i++){  
         char c = data.charAt(i);  
         if (c == '-')  
            data = data.replace ('-', '/');  
      }           
      return data;        
   }
   public String somaData(String data, int somaDias){  
      Date dt = new Date(verificaMascaraData(data));  
      String formato = "dd/MM/yyyy";  
      dt.setDate(dt.getDate() + somaDias);        
      SimpleDateFormat dataFormatada = new SimpleDateFormat(formato);        
      return dataFormatada.format(dt);  
   }  
 public static int intervaloDias (Date d1, Date d2){   
 int result = (int)((d1.getTime() - d2.getTime()) / 86400000L);   
 return result<0?result*-1:result;   
    }   
}
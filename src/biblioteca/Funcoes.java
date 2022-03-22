package biblioteca;
import javax.swing.JOptionPane;
public class Funcoes {
    public void CalculaCutter()
    {
        
    }
    public void SQL_Cadastra_Funcionario(String str_Nome , String str_RG, String str_DN, String str_Endereco, String str_Numero, String str_Complemento, String str_Bairro, String str_Cidade, String str_Telefone, String str_Celular, String str_Email)
    {
           String Data_De_Hoje="", id_Funcionario="";
           String SQL = "insert into funcionario (id_funcionario, nome, data_nasc, data_cadastro, rg, rua, numero, complemento, bairro, cidade, fone, celular, email)"
        + "VALUES (" + id_Funcionario + ", '" + str_Nome + "', '" + str_DN + "', '" + Data_De_Hoje + "', '" + str_RG + "', '" + str_Endereco + "', '" + str_Numero + "',"
        + "'" + str_Complemento + "', '" + str_Bairro + "', '" + str_Cidade + "', '" + str_Telefone + "', '" + str_Celular + "', '" + str_Email + "')";
        try
        {
            JOptionPane.showMessageDialog(null,"Usuario Cadastrado com sucesso");
        }
        catch(Exception erro)
        {
           JOptionPane.showMessageDialog(null, erro);
        }

    }
}

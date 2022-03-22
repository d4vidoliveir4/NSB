/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
/**
 *
 * @author david
 */
public class Cad_Opcoes extends JFrame{
    public JTextField txt_con_pc,txt_porta,txt_usuario,txt_senha,txt_banco;
    public JLabel lb_con_pc, lb_porta, lb_usuario, lb_senha,lb_banco;
    public JButton bt_salvar,bt_cancelar;
    public void Cad_Opcoes(){
        inicializarcomponentes();
        definireventos();
    }
    private void inicializarcomponentes(){
        setBounds(0,0,800,600);
        setLayout(null);
        setTitle("NSB - Configurações");
        setVisible(true);
        getContentPane().setBackground(Color.getHSBColor(0.650f,0.158f,0.750f));
        txt_con_pc = new JTextField();
        txt_porta= new JTextField("3306");
        txt_usuario = new JTextField("root");
        txt_senha= new JTextField();
        lb_con_pc = new JLabel("Nome ou IP do Computador onde esta o Banco de Dados:");
        lb_porta=new JLabel("Porta de Comunicação Banco de Dados:");
        lb_banco= new JLabel("Versão do Banco de Dados:");
        lb_usuario = new JLabel("Usuario para acesso ao Banco de Dados:");
        lb_senha = new JLabel("Senha para acesso ao Banco de Dados:");
        bt_salvar = new JButton("Salvar");
        bt_cancelar = new JButton("Cancelar");
        txt_banco = new JTextField("biblioteca");
        lb_con_pc. setBounds(10,10,500,25);
        txt_con_pc.setBounds(10,30,500,25);
        lb_banco.setBounds(10,70,500,25);
        txt_banco.setBounds(10,100,500,25);
        lb_porta.setBounds(10,140,500,25);
        txt_porta.setBounds(10,170,500,25);
        lb_usuario.setBounds(10,210,500,25);
        txt_usuario.setBounds(10,240,500,25);
        lb_senha.setBounds(10,270,500,25);
        txt_senha.setBounds(10,300,500,25);
        bt_salvar. setBounds(620,400,150,40);
        bt_cancelar.setBounds(620,450,150,40);
        add(lb_con_pc);
        add(txt_con_pc);
        add(lb_porta);
        add(lb_banco);
        add(txt_banco);
        add(txt_porta);
        add(lb_usuario);
        add(txt_usuario);
        add(lb_senha);
        add(txt_senha);
        add(bt_salvar);
        add(bt_cancelar);
    }
    private void definireventos(){
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
        bt_cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                Cad_Opcoes.this.dispose();
            }
        });
        bt_salvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
               try{
            PrintWriter out = new PrintWriter("C:/NSB/op.conf");
            out.println(txt_con_pc.getText());
            out.println(txt_banco.getText());
            out.println(txt_porta.getText());
            out.println(txt_usuario.getText());
            out.println(txt_senha.getText());
            out.close();
            JOptionPane.showMessageDialog(null, "Gravado com sucesso!\nAs alterações serão aplicadas na proxima inicialização.");
            MainScreen main = new MainScreen();
            main.MainScreen();
            Cad_Opcoes.this.dispose();
            }   
            catch(IOException erro){
            JOptionPane.showMessageDialog(null, "Erro ao salvar as informações!");
            } 
          }
      });
    }
 }

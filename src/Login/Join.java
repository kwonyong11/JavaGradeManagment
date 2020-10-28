// �����, ���� : �̰���
// ȸ������ â
package Login;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import Data.*;
import Admin.*;
import Student.*;
public class Join {
   JLabel la_Id, la_Pw, la_RePw, la_Name,  l_title ;
   JTextField txt_ID, txt_Pw, txt_RePw, txt_Name;
   JButton btnJoin, idCheck, pwCheck ;
   
   Scanner sc = new Scanner(System.in);
   //â ������ ����
   final int WIDTH=600;
   final int HEIGH=600;
   
   DataIO dio = new DataIO();
   
   int idch = -1; // ID �ߺ� Ȯ�� ����, 0 : ���� / 1: ��밡�� ID / 2 : ID �ߺ�	 / -1 : �ʱⰪ
   boolean passch = false;	// true : ���Ȯ�� �� ��� / false : ���Ȯ�� �� ��� x

   public Join(){            
	
      JFrame joinView = new JFrame("ȸ������");

      
      /*******************  Ÿ��Ʋ ����     *******************/
      l_title = new JLabel("[ ȸ�� ���� ]"); 
      l_title.setBounds(220,50,WIDTH,30);
      l_title.setFont(new Font(null,Font.BOLD,30));


      // ���̵� �Է�ĭ

      la_Id = new JLabel("�� �� �� ");             // ���̵� ���
      la_Id.setBounds(WIDTH/4, 160, WIDTH/2, 33);

      txt_ID = new JTextField(10);   // ���̵� �Է�  
      txt_ID.setBounds(WIDTH/4, 190, 230, 33);

      idCheck = new JButton("Ȯ��");        //  ���̵� �ߺ� Ȯ��
      idCheck.setBounds(380, 190, 70, 33);

      // ��й�ȣ �Է�ĭ

      la_Pw = new JLabel("�� �� �� ȣ ");   // ��й�ȣ ���
      la_Pw.setBounds(WIDTH/4, 230, WIDTH/2, 33);

      txt_Pw = new JPasswordField(10);  // ��й�ȣ �Է� ĭ ����
      txt_Pw.setBounds(WIDTH/4, 260, WIDTH/2, 33);
      
      // ��й�ȣ ���Է� ĭ

      la_RePw = new JLabel("��й�ȣ ��Ȯ��");   // ��й�ȣ ���Է� 
      la_RePw.setBounds(WIDTH/4, 300, WIDTH/2, 33);

      txt_RePw = new JPasswordField(10);  // ��й�ȣ ���Է� ĭ ����
      txt_RePw.setBounds(WIDTH/4, 330, WIDTH/2, 33);

      // �̸� �Է� ĭ 

      la_Name = new JLabel("�� ��");  
      la_Name.setBounds(WIDTH/4, 370, WIDTH/2, 33);   

      txt_Name = new JTextField(10);  // ��й�ȣ ���Է� ĭ ����
      txt_Name.setBounds(WIDTH/4, 400, WIDTH/2, 33);

      // ȸ������ ��ư

      btnJoin = new JButton("ȸ�� ����"); 
      btnJoin.setBounds(WIDTH/4, 460, WIDTH/2, 35);        

      
      joinView.add(l_title);
      
      
      joinView.add(la_Id);
      joinView.add(txt_ID);
      joinView.add(idCheck);

      joinView.add(la_Pw);
      joinView.add(txt_Pw);      

      joinView.add(la_RePw);
      joinView.add(txt_RePw);

      joinView.add(la_Name);
      joinView.add(txt_Name);

      joinView.add(btnJoin);
            
      joinView.setLayout(new BorderLayout());
      
      joinView.addWindowListener(new WindowAdapter() {
    	  public void windowClosing(WindowEvent e) { 
    		  new Login_Main();    		 
    	  }    	
      });


     
      joinView.setSize(WIDTH, HEIGH); // ���θ� 600, ���θ� 600�ȼ��� ����
      joinView.setVisible(true);      
      

      idCheck.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {        	 
        	 idch = dio.idCheck(txt_ID.getText());
        	 System.out.println(idch);
        	 switch(idch) {
        	 case 0:
        		 JOptionPane.showMessageDialog( null , " ������ �߻��߽��ϴ�. ");
        		 break;
        	 case 1:
        		 JOptionPane.showMessageDialog( null , " ��� ������ ID �Դϴ�. ");
        		 txt_ID.setEnabled(false);
        		 break;
        	 case 2:
        		 JOptionPane.showMessageDialog( null , " �̹� ������� ID �Դϴ�. ");
        		 break;
        		 
        	 }
        }
      });     
      // �̰���
      btnJoin.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             try {
                // ������ �ְų�
                // ��й�ȣ Ȯ���̶� �ٸ��ų�
                // �ִ� ȸ���̴���                

                if (idch == -1) {
                   JOptionPane.showMessageDialog(null, "ID �ߺ�üũ�� �ϼ��� ");
                } else if (txt_ID.getText().equals("") || txt_Name.getText().equals("")
                      || txt_Pw.getText().equals("") || txt_RePw.getText().equals("")) {
                   JOptionPane.showMessageDialog(null, " ������ �ֽ��ϴ� ");

                } else if (!(txt_Pw.getText().equals(txt_RePw.getText()))) {
                   JOptionPane.showMessageDialog(null, " ��й�ȣ�� �ٸ��ϴ�. ");
                } else if (idch == 2) {
                   JOptionPane.showMessageDialog(null, " �̹� ������� ID �Դϴ�. ");
                   idch = -1;
                } else {
                   JOptionPane.showMessageDialog(null, " ȸ������ ���� ");
                   dio.joinFileWrite(txt_Name.getText(), txt_Pw.getText(), txt_ID.getText());
                   idch = -1;
                   txt_ID.setText("");
                   txt_Pw.setText("");
                   txt_RePw.setText("");
                   txt_Name.setText("");
                   passch = false;
                }

             } catch (Exception e1) {
                // TODO: handle exception
             }

          }
       });

   }


}
// �ǿ�
// ������ ��й�ȣ ����
package Admin;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Data.*;

import Login.Login_Main;


public class MPwCh{

  // private ArrayList<Student> stulist = new ArrayList<Student>();

   DataIO dio=new DataIO();
   private static final int NOMAL = 0;
   final int WIDTH=600;
   final int HEIGH=600;

   JLabel l_title, l_orginal, l_modify;
   JPasswordField t_orginal, t_modify ;
   JButton btn_ok;
   
   DataSet d = new DataSet();
   
   public MPwCh(){
      JFrame modifyview = new JFrame("�л���� - ��й�ȣ����");

      /*******************  Ÿ��Ʋ ����     *******************/
      
      l_title = new JLabel("< �� ��й�ȣ ���� >"); 
      l_title.setBounds(160,170,WIDTH,30);
      l_title.setFont(new Font(null,Font.BOLD,30));

      /*******************  ��й�ȣ ����     *******************/

      l_orginal = new JLabel("���� ��й�ȣ");
      l_orginal.setBounds(WIDTH/4, 220, WIDTH/2, 33);

      t_orginal = new JPasswordField(10);   //���� ��й�ȣ
      t_orginal.setBounds(WIDTH/4, 250, WIDTH/2, 33);

      l_modify = new JLabel("���� ��й�ȣ");
      l_modify.setBounds(WIDTH/4, 300, WIDTH/2, 33);
      l_modify.setFont(new Font(null,Font.BOLD,13));

      t_modify = new JPasswordField(10);    //���� ��й�ȣ
      t_modify.setBounds(WIDTH/4, 330, WIDTH/2, 33);

      /*******************  ��ư    *******************/

      btn_ok = new JButton("��� ��ȣ ����");
      btn_ok.setBounds(330,400,120,30);

      /*******************  ȭ�鿡 �߰�    *******************/
      
      modifyview.add(l_title);
      modifyview.add(l_orginal);
      modifyview.add(t_orginal);
      modifyview.add(l_modify);
      modifyview.add(t_modify);
      modifyview.add(btn_ok);

      /*******************  modifyview_ȭ�� ���� ����    *******************/
      
      modifyview.setLayout(new BorderLayout());
      modifyview.setSize(WIDTH, HEIGH); // ���θ� 600, ���θ� 600�ȼ��� ����
      modifyview.setVisible(true);      
      
      
      /****************************  ����  �ڵ�     ******************************/

     
      
      System.out.println(d.getStuindex());  
      btn_ok.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {    
            
            if(dio.getAdminPw().equals(t_orginal.getText()))
           {
            	if((t_orginal.getText()).equals(t_modify.getText()))
            	{
            		JOptionPane.showMessageDialog( null , "������ ��й�ȣ!!!");                      
            	}
            	else {
                    d.getStudent().get(d.getStuindex()).setStupw(t_modify.getText());
                    dio.adminPwModify(t_modify.getText());            
                    JOptionPane.showMessageDialog( null , "��й�ȣ ���� �Ϸ�!");
                    t_orginal.setText("");
                    t_modify.setText("");
            	}            
           }
           else
           {
              JOptionPane.showMessageDialog( null , "��й�ȣ�� �߸� �Է��ϼ̽��ϴ�.");         
              
           }
         }
      });    
   }


}
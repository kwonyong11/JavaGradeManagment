// �����
// �л� ��й�ȣ ã��

package Login;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Data.DataSet;
import Student.Student;

public class Student_FindPW{
   
   DataSet ds = new DataSet();
    ArrayList<Student> stu = ds.getStudent();

   final int WIDTH=40;

   JLabel lal_FindTitle, lal_FindPW ;
   JTextField tex_FindPW;
   JButton btn_Ok;

   public Student_FindPW(){
      JFrame findPWview = new JFrame("�л�������α׷� - ��й�ȣã��");

      /*******************  Ÿ��Ʋ ����     *******************/
      
      lal_FindTitle = new JLabel("< PW ã�� >"); 
      lal_FindTitle.setBounds(WIDTH,60,300,30);
      lal_FindTitle.setFont(new Font(null,Font.BOLD,20));

      /*******************  ���̵� �Է�â     *******************/

      lal_FindPW = new JLabel("����� ID �Է�");
      lal_FindPW.setBounds(WIDTH, 110, 300, 33);

      tex_FindPW = new JTextField(10);   
      tex_FindPW.setBounds(WIDTH, 140, 300, 33);

      /*******************  ��ư    *******************/

      btn_Ok = new JButton("PW ã��");
      btn_Ok.setBounds(220,200,120,30);

      /*******************  ȭ�鿡 �߰�    *******************/
      
      findPWview.add(lal_FindTitle);
      findPWview.add(lal_FindPW);
      findPWview.add(tex_FindPW);
      findPWview.add(btn_Ok);

      /*******************  modifyview_ȭ�� ���� ����    *******************/
      
      findPWview.setLocationRelativeTo(null);
      findPWview.setLayout(new BorderLayout());
      findPWview.setSize(380, 330);
      findPWview.setVisible(true);

      /****************************  ����  �ڵ�     ******************************/
            
     btn_Ok.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           ds = new DataSet();
           stu = ds.getStudent();
           boolean ChangeCh = false;

           if(!(tex_FindPW.getText().equals(""))) {
              for(int i=0; i<stu.size(); i++) {
                 if (stu.get(i).getStuid().equals(tex_FindPW.getText()) ) {
                    JOptionPane.showMessageDialog(null, stu.get(i).getStuid()+ " ����  ��й�ȣ��  "
                 + stu.get(i).getStupw() + " �Դϴ�." );
                    ChangeCh = false;
                    break;
                 }
                 else {
                    ChangeCh = true;
                 }
              }
              if (ChangeCh == true) {
                 JOptionPane.showMessageDialog( null , " ���Ե��� ���� ���̵� �Դϴ�. ");
              }
           }
           else {
              JOptionPane.showMessageDialog(null, " ������ �ֽ��ϴ�. ");
           }
        }
     });     


   }


}
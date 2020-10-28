// �����
// �л� ID ã��( �������ο� ���� ó�� �̱��� )
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

public class Student_FindID{
   
   DataSet ds = new DataSet();
    ArrayList<Student> stu = ds.getStudent();


   final int WIDTH=40;

   JLabel lal_FindTitle, lal_FindId ;
   JTextField tex_FindName ;
   JButton btn_Ok;

   public Student_FindID(){
      JFrame findIDview = new JFrame("�л�������α׷� - ���̵�ã��");

      /*******************  Ÿ��Ʋ ����     *******************/
      
      lal_FindTitle = new JLabel("< ID ã�� >"); 
      lal_FindTitle.setBounds(WIDTH,60,300,30);
      lal_FindTitle.setFont(new Font(null,Font.BOLD,20));

      /*******************  �̸� �Է�ĭ     *******************/

      lal_FindId = new JLabel("����� �̸� �Է�");
      lal_FindId.setBounds(WIDTH, 110, 300, 33);

      tex_FindName = new JTextField(10);   //���� ��й�ȣ
      tex_FindName.setBounds(WIDTH, 140, 300, 33);

      /*******************  ��ư    *******************/

      btn_Ok = new JButton("ID ã��");
      btn_Ok.setBounds(220,200,120,30);

      /*******************  ȭ�鿡 �߰�    *******************/
      
      findIDview.add(lal_FindTitle);
      findIDview.add(lal_FindId);
      findIDview.add(tex_FindName);
      findIDview.add(btn_Ok);

      /*******************  modifyview_ȭ�� ���� ����    *******************/
      
      findIDview.setLocationRelativeTo(null);
      findIDview.setLayout(new BorderLayout());
      findIDview.setSize(380, 330);
      findIDview.setVisible(true);
 
      /****************************  ����  �ڵ�     ******************************/
           
      
     btn_Ok.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           ds = new DataSet();
           stu = ds.getStudent();
           boolean ChangeCh = false;

           if(!(tex_FindName.getText().equals(""))) {
              for(int i=0; i<stu.size(); i++) {
                 if (stu.get(i).getStuname().equals(tex_FindName.getText()) ) {
                    JOptionPane.showMessageDialog(null, stu.get(i).getStuname() + "���� ���̵��  " + stu.get(i).getStuid() + " �Դϴ�." );
                    ChangeCh = false;
                    break;
                 }
                 else {
                    ChangeCh = true;
                 }
              }
              if (ChangeCh == true) {
                 JOptionPane.showMessageDialog( null , " ���Ե��� ���� �̸� �Դϴ�. ");
              }
           }
           else {
              JOptionPane.showMessageDialog(null, "������ �ֽ��ϴ�. ");
           }
        }
     });     

   }


}
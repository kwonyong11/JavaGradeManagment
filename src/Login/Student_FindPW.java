// 김건중
// 학생 비밀번호 찾기

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
      JFrame findPWview = new JFrame("학사관리프로그램 - 비밀번호찾기");

      /*******************  타이틀 제목     *******************/
      
      lal_FindTitle = new JLabel("< PW 찾기 >"); 
      lal_FindTitle.setBounds(WIDTH,60,300,30);
      lal_FindTitle.setFont(new Font(null,Font.BOLD,20));

      /*******************  아이디 입력창     *******************/

      lal_FindPW = new JLabel("사용자 ID 입력");
      lal_FindPW.setBounds(WIDTH, 110, 300, 33);

      tex_FindPW = new JTextField(10);   
      tex_FindPW.setBounds(WIDTH, 140, 300, 33);

      /*******************  버튼    *******************/

      btn_Ok = new JButton("PW 찾기");
      btn_Ok.setBounds(220,200,120,30);

      /*******************  화면에 추가    *******************/
      
      findPWview.add(lal_FindTitle);
      findPWview.add(lal_FindPW);
      findPWview.add(tex_FindPW);
      findPWview.add(btn_Ok);

      /*******************  modifyview_화면 각종 설정    *******************/
      
      findPWview.setLocationRelativeTo(null);
      findPWview.setLayout(new BorderLayout());
      findPWview.setSize(380, 330);
      findPWview.setVisible(true);

      /****************************  실행  코드     ******************************/
            
     btn_Ok.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           ds = new DataSet();
           stu = ds.getStudent();
           boolean ChangeCh = false;

           if(!(tex_FindPW.getText().equals(""))) {
              for(int i=0; i<stu.size(); i++) {
                 if (stu.get(i).getStuid().equals(tex_FindPW.getText()) ) {
                    JOptionPane.showMessageDialog(null, stu.get(i).getStuid()+ " 님의  비밀번호는  "
                 + stu.get(i).getStupw() + " 입니다." );
                    ChangeCh = false;
                    break;
                 }
                 else {
                    ChangeCh = true;
                 }
              }
              if (ChangeCh == true) {
                 JOptionPane.showMessageDialog( null , " 가입되지 않은 아이디 입니다. ");
              }
           }
           else {
              JOptionPane.showMessageDialog(null, " 공백이 있습니다. ");
           }
        }
     });     


   }


}
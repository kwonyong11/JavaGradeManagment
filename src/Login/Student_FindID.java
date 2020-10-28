// 김건중
// 학생 ID 찾기( 동명이인에 대한 처리 미구현 )
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
      JFrame findIDview = new JFrame("학사관리프로그램 - 아이디찾기");

      /*******************  타이틀 제목     *******************/
      
      lal_FindTitle = new JLabel("< ID 찾기 >"); 
      lal_FindTitle.setBounds(WIDTH,60,300,30);
      lal_FindTitle.setFont(new Font(null,Font.BOLD,20));

      /*******************  이름 입력칸     *******************/

      lal_FindId = new JLabel("사용자 이름 입력");
      lal_FindId.setBounds(WIDTH, 110, 300, 33);

      tex_FindName = new JTextField(10);   //현재 비밀번호
      tex_FindName.setBounds(WIDTH, 140, 300, 33);

      /*******************  버튼    *******************/

      btn_Ok = new JButton("ID 찾기");
      btn_Ok.setBounds(220,200,120,30);

      /*******************  화면에 추가    *******************/
      
      findIDview.add(lal_FindTitle);
      findIDview.add(lal_FindId);
      findIDview.add(tex_FindName);
      findIDview.add(btn_Ok);

      /*******************  modifyview_화면 각종 설정    *******************/
      
      findIDview.setLocationRelativeTo(null);
      findIDview.setLayout(new BorderLayout());
      findIDview.setSize(380, 330);
      findIDview.setVisible(true);
 
      /****************************  실행  코드     ******************************/
           
      
     btn_Ok.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           ds = new DataSet();
           stu = ds.getStudent();
           boolean ChangeCh = false;

           if(!(tex_FindName.getText().equals(""))) {
              for(int i=0; i<stu.size(); i++) {
                 if (stu.get(i).getStuname().equals(tex_FindName.getText()) ) {
                    JOptionPane.showMessageDialog(null, stu.get(i).getStuname() + "님의 아이디는  " + stu.get(i).getStuid() + " 입니다." );
                    ChangeCh = false;
                    break;
                 }
                 else {
                    ChangeCh = true;
                 }
              }
              if (ChangeCh == true) {
                 JOptionPane.showMessageDialog( null , " 가입되지 않은 이름 입니다. ");
              }
           }
           else {
              JOptionPane.showMessageDialog(null, "공백이 있습니다. ");
           }
        }
     });     

   }


}
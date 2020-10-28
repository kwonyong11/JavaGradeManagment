// 김건중, 도움 : 이경현
// 회원가입 창
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
   //창 사이즈 변수
   final int WIDTH=600;
   final int HEIGH=600;
   
   DataIO dio = new DataIO();
   
   int idch = -1; // ID 중복 확인 변수, 0 : 에러 / 1: 사용가능 ID / 2 : ID 중복	 / -1 : 초기값
   boolean passch = false;	// true : 비번확인 시 통과 / false : 비번확인 시 통과 x

   public Join(){            
	
      JFrame joinView = new JFrame("회원가입");

      
      /*******************  타이틀 제목     *******************/
      l_title = new JLabel("[ 회원 가입 ]"); 
      l_title.setBounds(220,50,WIDTH,30);
      l_title.setFont(new Font(null,Font.BOLD,30));


      // 아이디 입력칸

      la_Id = new JLabel("아 이 디 ");             // 아이디 출력
      la_Id.setBounds(WIDTH/4, 160, WIDTH/2, 33);

      txt_ID = new JTextField(10);   // 아이디 입력  
      txt_ID.setBounds(WIDTH/4, 190, 230, 33);

      idCheck = new JButton("확인");        //  아이디 중복 확인
      idCheck.setBounds(380, 190, 70, 33);

      // 비밀번호 입력칸

      la_Pw = new JLabel("비 밀 번 호 ");   // 비밀번호 출력
      la_Pw.setBounds(WIDTH/4, 230, WIDTH/2, 33);

      txt_Pw = new JPasswordField(10);  // 비밀번호 입력 칸 지정
      txt_Pw.setBounds(WIDTH/4, 260, WIDTH/2, 33);
      
      // 비밀번호 재입력 칸

      la_RePw = new JLabel("비밀번호 재확인");   // 비밀번호 재입력 
      la_RePw.setBounds(WIDTH/4, 300, WIDTH/2, 33);

      txt_RePw = new JPasswordField(10);  // 비밀번호 재입력 칸 지정
      txt_RePw.setBounds(WIDTH/4, 330, WIDTH/2, 33);

      // 이름 입력 칸 

      la_Name = new JLabel("이 름");  
      la_Name.setBounds(WIDTH/4, 370, WIDTH/2, 33);   

      txt_Name = new JTextField(10);  // 비밀번호 재입력 칸 지정
      txt_Name.setBounds(WIDTH/4, 400, WIDTH/2, 33);

      // 회원가입 버튼

      btnJoin = new JButton("회원 가입"); 
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


     
      joinView.setSize(WIDTH, HEIGH); // 가로를 600, 세로를 600픽셀로 지정
      joinView.setVisible(true);      
      

      idCheck.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {        	 
        	 idch = dio.idCheck(txt_ID.getText());
        	 System.out.println(idch);
        	 switch(idch) {
        	 case 0:
        		 JOptionPane.showMessageDialog( null , " 에러가 발생했습니다. ");
        		 break;
        	 case 1:
        		 JOptionPane.showMessageDialog( null , " 사용 가능한 ID 입니다. ");
        		 txt_ID.setEnabled(false);
        		 break;
        	 case 2:
        		 JOptionPane.showMessageDialog( null , " 이미 사용중인 ID 입니다. ");
        		 break;
        		 
        	 }
        }
      });     
      // 이경현
      btnJoin.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             try {
                // 공백이 있거나
                // 비밀번호 확인이랑 다르거나
                // 있는 회원이던가                

                if (idch == -1) {
                   JOptionPane.showMessageDialog(null, "ID 중복체크를 하세요 ");
                } else if (txt_ID.getText().equals("") || txt_Name.getText().equals("")
                      || txt_Pw.getText().equals("") || txt_RePw.getText().equals("")) {
                   JOptionPane.showMessageDialog(null, " 공백이 있습니다 ");

                } else if (!(txt_Pw.getText().equals(txt_RePw.getText()))) {
                   JOptionPane.showMessageDialog(null, " 비밀번호가 다릅니다. ");
                } else if (idch == 2) {
                   JOptionPane.showMessageDialog(null, " 이미 사용중인 ID 입니다. ");
                   idch = -1;
                } else {
                   JOptionPane.showMessageDialog(null, " 회원가입 성공 ");
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
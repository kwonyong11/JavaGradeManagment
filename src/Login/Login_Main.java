// 디자인 : 이강은, 도움 : 김종희
// 로그인창 
package Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Data.*;
import Student.*;
import Admin.*;
import Login.*;

public class Login_Main extends JFrame {

	   private JPanel loginView = new JPanel();
	   private JLabel idLabel = new JLabel("아이디 : ");   // 아이디 라벨
	   private JLabel pwLavel = new JLabel("비밀번호 : ");   // 비밀번호 라벨
	   private JTextField txtID = new JTextField(10);   // 아이디 입력 칸 지정
	   private JPasswordField txtPW = new JPasswordField(10);  // 비밀번호 입력 칸 지정
	   private JButton btnLogin = new JButton("로그인");         // 로그인 버튼
	   private JButton btnJoin = new JButton("회원가입");            // 회원가입 버튼
	   ImageIcon img = new ImageIcon("images/logpic.jpg"); //이미지 로딩
	   private JLabel image = new JLabel(img); // 레이블 생성
	   private JButton btnFindID = new JButton("아이디 찾기");            // 아이디 찾기 버튼
	   private JButton btnFindPW = new JButton("비밀번호 찾기");            // 비밀번호 찾기 버튼

	   ManagerMenu pre;	   
	   DataSet ds = new DataSet();
	   ArrayList<Student> stu = ds.getStudent();
	   DataIO dio = new DataIO();
	   public Login_Main() {
		   super("학사 관리 프로그램 로그인");         
	         this.setContentPane(loginView);

	         
	         //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

	         loginView.setLayout(null);
	         idLabel.setBounds(470, 120, 80, 25);
	         pwLavel.setBounds(470, 160, 80, 25);

	         txtID.setBounds(550, 120, 180, 25);
	         txtPW.setBounds(550, 160, 180, 25);

	         btnLogin.setBounds(740, 120, 82, 65);
	         btnJoin.setBounds(740, 200, 82, 25);
	         btnFindID.setBounds(480, 200, 120, 25);
	         btnFindPW.setBounds(610, 200, 120, 25);

	         image.setBounds(0, 0, 900, 532);

	         loginView.add(idLabel); 
	         loginView.add(txtID);
	         loginView.add(pwLavel); // 비밀번호 화면 안에 넣어주기
	         loginView.add(txtPW);  
	         loginView.add(btnLogin); 
	         loginView.add(btnJoin);
	         loginView.add(btnFindID);
	         loginView.add(btnFindPW);
	         loginView.add(image);

	         idLabel.setHorizontalAlignment(NORMAL);      // 가운데 정렬   
	         pwLavel.setHorizontalAlignment(NORMAL);
	         setSize(900 , 550);
	         setLocationRelativeTo(null);
	         this.setVisible(true);
	         // 김종희
	         btnLogin.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	stu = dio.refreshStudentdata();
	                boolean loginch=false;
	                boolean stuloginch = false;
	                for (int i = 0; i <stu.size(); i++) {
	                     if (stu.get(i).getStuid().equals(txtID.getText()) && stu.get(i).getStupw().equals(txtPW.getText())) {
	                    	 ds.setStuindex(i);
	                    	 new StudentMenu();
	                    	 stuloginch = true;
	                    	 loginch = true;
	                    	 dispose();
	                    	 break;
	                     }
	                     else {
	                    	 stuloginch = false;
	                     }
	                }
	                if(!stuloginch) {
	                	if(dio.adminDataCheck(txtID.getText(), txtPW.getText())){
	                		new ManagerMenu();
	                		loginch = true;
	                		dispose();
	                	}
	                	else {
	                		loginch = false;
	                	}
	                }
	                if (!loginch) {
	                     JOptionPane.showMessageDialog( null , " 로그인 실패! ");
	                  }

	               }
	         });

	      btnJoin.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {	           
	            //회원가입 창 이동
	            new Join();	            
	         }

	      });
	      
	      btnFindID.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	new Student_FindID();
	            	
	            }

	         }); 
	         
	      btnFindPW.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	new Student_FindPW();
	            	
	            }

	         }); 
	   }	   
	   public static void main(String[] args) { 
	      new Login_Main();
	   }
	  
	   

	}
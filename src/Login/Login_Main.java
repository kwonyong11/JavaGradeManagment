// ������ : �̰���, ���� : ������
// �α���â 
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
	   private JLabel idLabel = new JLabel("���̵� : ");   // ���̵� ��
	   private JLabel pwLavel = new JLabel("��й�ȣ : ");   // ��й�ȣ ��
	   private JTextField txtID = new JTextField(10);   // ���̵� �Է� ĭ ����
	   private JPasswordField txtPW = new JPasswordField(10);  // ��й�ȣ �Է� ĭ ����
	   private JButton btnLogin = new JButton("�α���");         // �α��� ��ư
	   private JButton btnJoin = new JButton("ȸ������");            // ȸ������ ��ư
	   ImageIcon img = new ImageIcon("images/logpic.jpg"); //�̹��� �ε�
	   private JLabel image = new JLabel(img); // ���̺� ����
	   private JButton btnFindID = new JButton("���̵� ã��");            // ���̵� ã�� ��ư
	   private JButton btnFindPW = new JButton("��й�ȣ ã��");            // ��й�ȣ ã�� ��ư

	   ManagerMenu pre;	   
	   DataSet ds = new DataSet();
	   ArrayList<Student> stu = ds.getStudent();
	   DataIO dio = new DataIO();
	   public Login_Main() {
		   super("�л� ���� ���α׷� �α���");         
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
	         loginView.add(pwLavel); // ��й�ȣ ȭ�� �ȿ� �־��ֱ�
	         loginView.add(txtPW);  
	         loginView.add(btnLogin); 
	         loginView.add(btnJoin);
	         loginView.add(btnFindID);
	         loginView.add(btnFindPW);
	         loginView.add(image);

	         idLabel.setHorizontalAlignment(NORMAL);      // ��� ����   
	         pwLavel.setHorizontalAlignment(NORMAL);
	         setSize(900 , 550);
	         setLocationRelativeTo(null);
	         this.setVisible(true);
	         // ������
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
	                     JOptionPane.showMessageDialog( null , " �α��� ����! ");
	                  }

	               }
	         });

	      btnJoin.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {	           
	            //ȸ������ â �̵�
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
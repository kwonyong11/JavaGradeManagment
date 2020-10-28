//이강은
//학생 메뉴 - 성적조회, 수강신청, 비밀번호 변경

package Student;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import Login.Login_Main;

import java.io.FileNotFoundException;

public class StudentMenu {
	JButton btnJoin;

	//창 사이즈 변수
	final int WIDTH=600;
	final int HEIGH=600;

	ArrayList<Student> stulist = new ArrayList<Student>();
	boolean idch = false;
	boolean passch = false;

	public StudentMenu(){	   
		DataSet da = new DataSet();
		DataIO dio = new DataIO();
		JFrame stuMenuView = new JFrame("학생 메뉴");

		Font f = new Font("맑은고딕", Font.BOLD, 20);//버튼 폰트 설정


		JLabel Stme = new JLabel("학생 메뉴");
		Stme.setBounds(195, 50, WIDTH/2, 45);
		Stme.setFont(new Font("맑은고딕", Font.BOLD, 50));//레이블 폰트 지정



		JButton btnInfo = new JButton("내 성적 조회"); 
		btnInfo.setBounds(WIDTH/4, 150, WIDTH/2, 50);

		JButton btnJoin = new JButton("수강신청"); 
		btnJoin.setBounds(WIDTH/4, 250, WIDTH/2, 50);     

		JButton btnStuPw = new JButton("비밀번호 변경");
		btnStuPw.setBounds(WIDTH/4, 350, WIDTH/2, 50);
		
		JButton btnStudDelete = new JButton("회원탈퇴");
		btnStudDelete.setBounds(WIDTH/4, 450, WIDTH/2, 50);


		// 폰트설정
		btnInfo.setFont(f); 
		btnJoin.setFont(f);
		btnStuPw.setFont(f);
		btnStudDelete.setFont(f);
		stuMenuView.add(btnJoin);
		stuMenuView.add(btnInfo);
		stuMenuView.add(btnStuPw);
		stuMenuView.add(Stme);
		stuMenuView.add(btnStudDelete);

		stuMenuView.setLayout(new BorderLayout());
		stuMenuView.setSize(WIDTH, HEIGH); // 가로를 600, 세로를 600픽셀로 지정
		stuMenuView.setVisible(true);
		
		stuMenuView.addWindowListener(new WindowAdapter() {
	    	  public void windowClosing(WindowEvent e) { 
	    		  new Login_Main();    		 
	    	  }    	
	      });
		btnStudDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str =  JOptionPane.showInputDialog("비밀번호 입력해주세요.");
				try {
					if(str.equals(da.getStudent().get(da.getStuindex()).getStupw())){					
						if(JOptionPane.showConfirmDialog(null, "정말 탈퇴하시겠습니까?", "탈퇴확인", JOptionPane.YES_NO_OPTION) == 0) {
							JOptionPane.showMessageDialog( null , " 회원탈퇴 성공! ");
							da.getStudent().remove(da.getStuindex());
							dio.saveStudentData(da.getStudent());						
							new Login_Main();
							stuMenuView.dispose();
						}
						else {
							JOptionPane.showMessageDialog( null , " 회원탈퇴 취소! ");
						}
					}
					else {
						JOptionPane.showMessageDialog( null , " 회원탈퇴 실패! ");
					}
				}
				catch(NullPointerException e1) {
					JOptionPane.showMessageDialog( null , " 회원탈퇴 실패! ");
				}
				
				
					
				
			}
		});
		// 수강신청으로 이동
		btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Students_Join();
				
			}
		});
		// 비밀번호 변경
		btnStuPw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Students_PWModify();
				
			}
		});
		// 학생 성적 조회창 이동
		btnInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Student_Info();
				
			}
		});

	}
	
}
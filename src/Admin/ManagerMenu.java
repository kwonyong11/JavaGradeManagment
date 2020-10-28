//이강은

//관리자 메뉴- 성적 입력, 과목별 학생조회, 비밀번호 변경
package Admin;

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

import java.io.FileNotFoundException;
import Login.*;
import Data.*;
import Student.*;
public class ManagerMenu {
	JButton btnJoin;

	//창 사이즈 변수
	final int WIDTH=600;
	final int HEIGH=600;


	public ManagerMenu(){
		JFrame managerMenuView = new JFrame("관리자 메뉴");
		Font f = new Font("맑은고딕", Font.BOLD, 20);//버튼 폰트 설정

		JLabel Stme = new JLabel("관리자 메뉴");
		Stme.setBounds(165, 100, WIDTH/2, 45);
		Stme.setFont(new Font("맑은고딕", Font.BOLD, 50));

		JButton btn_ScoreInput = new JButton("성적 입력"); 
		btn_ScoreInput.setBounds(WIDTH/4, 200, WIDTH/2, 50);


		JButton btn_info = new JButton("과목별 학생조회"); 
		btn_info.setBounds(WIDTH/4, 300, WIDTH/2, 50);


		JButton btn_PwModify = new JButton("비밀번호 변경");
		btn_PwModify.setBounds(WIDTH/4, 400, WIDTH/2, 50);

		//폰트 설정
		btn_ScoreInput.setFont(f);
		btn_info.setFont(f);
		btn_PwModify.setFont(f);           

		managerMenuView.add(btn_info);
		managerMenuView.add(btn_ScoreInput);
		managerMenuView.add(btn_PwModify);
		managerMenuView.add(Stme);

		managerMenuView.setLayout(new BorderLayout());
		managerMenuView.setSize(WIDTH, HEIGH); // 가로를 600, 세로를 600픽셀로 지정
		managerMenuView.setVisible(true);
		
		managerMenuView.addWindowListener(new WindowAdapter() {
	    	  public void windowClosing(WindowEvent e) { 
	    		  new Login_Main();    		 
	    	  }    	
	      });
		
		btn_info.addActionListener(new ActionListener() {// 과목별 학생정보
			@Override
			public void actionPerformed(ActionEvent e) {
				new Lookup(); 
			}
		});


		btn_PwModify.addActionListener(new ActionListener() {// 비밀번호 변경으로 이동
			@Override
			public void actionPerformed(ActionEvent e) {
				new MPwCh();
			}
		});


		btn_ScoreInput.addActionListener(new ActionListener() {//성적입력
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new SubScore();
			}
		});

	}
	
}
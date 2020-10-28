//�̰���

//������ �޴�- ���� �Է�, ���� �л���ȸ, ��й�ȣ ����
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

	//â ������ ����
	final int WIDTH=600;
	final int HEIGH=600;


	public ManagerMenu(){
		JFrame managerMenuView = new JFrame("������ �޴�");
		Font f = new Font("�������", Font.BOLD, 20);//��ư ��Ʈ ����

		JLabel Stme = new JLabel("������ �޴�");
		Stme.setBounds(165, 100, WIDTH/2, 45);
		Stme.setFont(new Font("�������", Font.BOLD, 50));

		JButton btn_ScoreInput = new JButton("���� �Է�"); 
		btn_ScoreInput.setBounds(WIDTH/4, 200, WIDTH/2, 50);


		JButton btn_info = new JButton("���� �л���ȸ"); 
		btn_info.setBounds(WIDTH/4, 300, WIDTH/2, 50);


		JButton btn_PwModify = new JButton("��й�ȣ ����");
		btn_PwModify.setBounds(WIDTH/4, 400, WIDTH/2, 50);

		//��Ʈ ����
		btn_ScoreInput.setFont(f);
		btn_info.setFont(f);
		btn_PwModify.setFont(f);           

		managerMenuView.add(btn_info);
		managerMenuView.add(btn_ScoreInput);
		managerMenuView.add(btn_PwModify);
		managerMenuView.add(Stme);

		managerMenuView.setLayout(new BorderLayout());
		managerMenuView.setSize(WIDTH, HEIGH); // ���θ� 600, ���θ� 600�ȼ��� ����
		managerMenuView.setVisible(true);
		
		managerMenuView.addWindowListener(new WindowAdapter() {
	    	  public void windowClosing(WindowEvent e) { 
	    		  new Login_Main();    		 
	    	  }    	
	      });
		
		btn_info.addActionListener(new ActionListener() {// ���� �л�����
			@Override
			public void actionPerformed(ActionEvent e) {
				new Lookup(); 
			}
		});


		btn_PwModify.addActionListener(new ActionListener() {// ��й�ȣ �������� �̵�
			@Override
			public void actionPerformed(ActionEvent e) {
				new MPwCh();
			}
		});


		btn_ScoreInput.addActionListener(new ActionListener() {//�����Է�
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new SubScore();
			}
		});

	}
	
}
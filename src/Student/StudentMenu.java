//�̰���
//�л� �޴� - ������ȸ, ������û, ��й�ȣ ����

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

	//â ������ ����
	final int WIDTH=600;
	final int HEIGH=600;

	ArrayList<Student> stulist = new ArrayList<Student>();
	boolean idch = false;
	boolean passch = false;

	public StudentMenu(){	   
		DataSet da = new DataSet();
		DataIO dio = new DataIO();
		JFrame stuMenuView = new JFrame("�л� �޴�");

		Font f = new Font("�������", Font.BOLD, 20);//��ư ��Ʈ ����


		JLabel Stme = new JLabel("�л� �޴�");
		Stme.setBounds(195, 50, WIDTH/2, 45);
		Stme.setFont(new Font("�������", Font.BOLD, 50));//���̺� ��Ʈ ����



		JButton btnInfo = new JButton("�� ���� ��ȸ"); 
		btnInfo.setBounds(WIDTH/4, 150, WIDTH/2, 50);

		JButton btnJoin = new JButton("������û"); 
		btnJoin.setBounds(WIDTH/4, 250, WIDTH/2, 50);     

		JButton btnStuPw = new JButton("��й�ȣ ����");
		btnStuPw.setBounds(WIDTH/4, 350, WIDTH/2, 50);
		
		JButton btnStudDelete = new JButton("ȸ��Ż��");
		btnStudDelete.setBounds(WIDTH/4, 450, WIDTH/2, 50);


		// ��Ʈ����
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
		stuMenuView.setSize(WIDTH, HEIGH); // ���θ� 600, ���θ� 600�ȼ��� ����
		stuMenuView.setVisible(true);
		
		stuMenuView.addWindowListener(new WindowAdapter() {
	    	  public void windowClosing(WindowEvent e) { 
	    		  new Login_Main();    		 
	    	  }    	
	      });
		btnStudDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str =  JOptionPane.showInputDialog("��й�ȣ �Է����ּ���.");
				try {
					if(str.equals(da.getStudent().get(da.getStuindex()).getStupw())){					
						if(JOptionPane.showConfirmDialog(null, "���� Ż���Ͻðڽ��ϱ�?", "Ż��Ȯ��", JOptionPane.YES_NO_OPTION) == 0) {
							JOptionPane.showMessageDialog( null , " ȸ��Ż�� ����! ");
							da.getStudent().remove(da.getStuindex());
							dio.saveStudentData(da.getStudent());						
							new Login_Main();
							stuMenuView.dispose();
						}
						else {
							JOptionPane.showMessageDialog( null , " ȸ��Ż�� ���! ");
						}
					}
					else {
						JOptionPane.showMessageDialog( null , " ȸ��Ż�� ����! ");
					}
				}
				catch(NullPointerException e1) {
					JOptionPane.showMessageDialog( null , " ȸ��Ż�� ����! ");
				}
				
				
					
				
			}
		});
		// ������û���� �̵�
		btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Students_Join();
				
			}
		});
		// ��й�ȣ ����
		btnStuPw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Students_PWModify();
				
			}
		});
		// �л� ���� ��ȸâ �̵�
		btnInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Student_Info();
				
			}
		});

	}
	
}
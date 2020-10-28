// �Ѽ���
// �л� ��� ���� ���� ��ȸ
package Student;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Data.DataSet;
import Login.AlJoSubject;

public class Student_Info {

   DataSet da = new DataSet();
   JLabel label_title;
   JTable table;
   JButton btnBack;
   JScrollPane scrollpane;

   final int WIDTH=600;
   final int HEIGH=600;

  
   public Student_Info(){
      JFrame stuInfoView = new JFrame("�л���� - �� ���� ��ȸ");       

      /*******************  Ÿ��Ʋ ����     *******************/
      label_title = new JLabel("< "+da.getStudent().get(da.getStuindex()).getStuname()+" �л����� >"); 
      label_title.setBounds(48,75,WIDTH,30);
      label_title.setFont(new Font(null,Font.BOLD,25));


      /*******************  ǥ ����     *******************/
      String header[]= {"�����","����","����"};    
 
      Object contents[][]= new String[da.getStudent().get(da.getStuindex()).sub.size()][3];
      
      ArrayList<String> grade = new ArrayList<String>();
      // ������ ���� ����            
      for(int i=0; i<da.getStudent().get(da.getStuindex()).sub.size(); i++)
      {
         if(da.getStudent().get(da.getStuindex()).sub.get(i).getScore()>95)
         {
            grade.add("A+");
            
         }
        else if(da.getStudent().get(da.getStuindex()).sub.get(i).getScore()>90)
         {
            grade.add("A");
            
         }
         else if(da.getStudent().get(da.getStuindex()).sub.get(i).getScore()>80)
         {
            grade.add("B+");
            
         }
         else if(da.getStudent().get(da.getStuindex()).sub.get(i).getScore()>50)
         {
            grade.add("B");
            
         }
         else if(da.getStudent().get(da.getStuindex()).sub.get(i).getScore()>30)
         {
            grade.add("C+");
            
         }
         else if(da.getStudent().get(da.getStuindex()).sub.get(i).getScore()>20)
         {
            grade.add("C");
            
         }
         else if(da.getStudent().get(da.getStuindex()).sub.get(i).getScore() == 0) {
        	 grade.add("���� �Է� �ȵ�");
         }
         else
         {
            grade.add("F");
           
         }
         
      }
      
      //���̺� ���� ���� ���� ����
       for(int i=0; i<da.getStudent().get(da.getStuindex()).sub.size(); i++)
      {
 
      contents[i][0] =  da.getStudent().get(da.getStuindex()).getAlJoSubject().get(i).getSubject(); //�����
      contents[i][1] = Integer.toString(da.getStudent().get(da.getStuindex()).getAlJoSubject().get(i).getScore());//����
      contents[i][2] = grade.get(i);//����
      }      
      
      table = new JTable(contents,header);
      scrollpane = new JScrollPane(table);
    
      scrollpane.setBounds(40, 120, 500, 350);      
        
      btnBack = new JButton("�� ��");//  ������û Ȯ�� ��ư
      btnBack.setBounds(450,500,90,30);
      
      btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stuInfoView.dispose();
				
			}
		});

      /*******************  â �߰�      *******************/

      stuInfoView.add(btnBack);
      stuInfoView.add(label_title);
      stuInfoView.add(scrollpane);
     
      /*******************  â ����     *******************/
      stuInfoView.setLayout(new BorderLayout());
      stuInfoView.setSize(WIDTH, HEIGH); // ���θ� 600, ���θ� 600�ȼ��� ����
      stuInfoView.setVisible(true);

   }
   
}


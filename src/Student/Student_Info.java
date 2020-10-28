// 한수정
// 학생 모든 과목 정보 조회
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
      JFrame stuInfoView = new JFrame("학사관리 - 내 정보 조회");       

      /*******************  타이틀 제목     *******************/
      label_title = new JLabel("< "+da.getStudent().get(da.getStuindex()).getStuname()+" 학생정보 >"); 
      label_title.setBounds(48,75,WIDTH,30);
      label_title.setFont(new Font(null,Font.BOLD,25));


      /*******************  표 내용     *******************/
      String header[]= {"과목명","점수","성적"};    
 
      Object contents[][]= new String[da.getStudent().get(da.getStuindex()).sub.size()][3];
      
      ArrayList<String> grade = new ArrayList<String>();
      // 점수별 성적 구분            
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
        	 grade.add("성적 입력 안됨");
         }
         else
         {
            grade.add("F");
           
         }
         
      }
      
      //테이블에 띄우기 위해 내용 저장
       for(int i=0; i<da.getStudent().get(da.getStuindex()).sub.size(); i++)
      {
 
      contents[i][0] =  da.getStudent().get(da.getStuindex()).getAlJoSubject().get(i).getSubject(); //과목명
      contents[i][1] = Integer.toString(da.getStudent().get(da.getStuindex()).getAlJoSubject().get(i).getScore());//점수
      contents[i][2] = grade.get(i);//성적
      }      
      
      table = new JTable(contents,header);
      scrollpane = new JScrollPane(table);
    
      scrollpane.setBounds(40, 120, 500, 350);      
        
      btnBack = new JButton("종 료");//  수강신청 확인 버튼
      btnBack.setBounds(450,500,90,30);
      
      btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stuInfoView.dispose();
				
			}
		});

      /*******************  창 추가      *******************/

      stuInfoView.add(btnBack);
      stuInfoView.add(label_title);
      stuInfoView.add(scrollpane);
     
      /*******************  창 설정     *******************/
      stuInfoView.setLayout(new BorderLayout());
      stuInfoView.setSize(WIDTH, HEIGH); // 가로를 600, 세로를 600픽셀로 지정
      stuInfoView.setVisible(true);

   }
   
}


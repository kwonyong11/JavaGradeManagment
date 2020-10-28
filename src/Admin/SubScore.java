//  조인태
// 학생 성적 입력

package Admin;

import Login.*;
import Student.*;
import Data.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField; 
import javax.swing.table.DefaultTableModel;

public class SubScore extends JFrame{
   
   SubScore() {
      super("성적 입력");
      
      String[] subject= {"java","C","Py","Cpp","MFC","Jsp","JavaScript","Kotlin"}; //과목이름 배열로 저장
      JPanel[] panel= {new stPanel("java"),new stPanel("C"),new stPanel("Py"),new stPanel("Cpp"),
            new stPanel("MFC"),new stPanel("Jsp"),new stPanel("JavaScript"),new stPanel("Kotlin")};
      JTabbedPane jtab = new JTabbedPane();
      
      for(int i =0; i<8;i++)
         jtab.addTab(subject[i],panel[i]);

      this.add(jtab);      //frame에 jtap 붙임.
      this.setSize(600,600);
      this.setVisible(true);
   }
}
class stPanel extends JPanel {
   int i =0;
   DataSet data1 = new DataSet();    //DataSet 생성 ->변수를 참조하기 위함
   SubToStu func = new SubToStu();   //데이터 추출 탐색 메소드를 담고있는 클래스 인스턴스화
   stPanel(String subject) {
      JButton btn_submit = new JButton("성적 저장"); //제출 버튼 생성
      JPanel tPanel = new JPanel();
      JPanel btnPanel = new JPanel();

      String sTsName[] = func.sTs(data1,subject);     // sTs함수 호출 주석은 SubToStu에 있음.
      String sTsID[] = func.sTsID(data1,subject);
      int Size=0;
      for(int i=0;i<sTsName.length;i++) {
    	//이름이 null값이면 size에 포함되지 않아도 되므로 data에 null값이 아니면 size를 증가시켜 table의 사이즈를 지정해준다.
         if(sTsName[i]!=null) {
            Size++;
         }
      }
      String data[][]= new String[Size][2];    //이름을 받는 배열
      String dataID[][]= new String[Size][2];  //ID받는 배열

      for(int i=0;i<Size;i++) {

         dataID[i][0]= sTsID[i];        //테이블의 형태에 맞게 정렬
         dataID[i][1]="";
      }
      for(int i=0;i<Size;i++) {
         data[i][0]= sTsName[i];        //테이블의 형태에 맞게 정렬
         data[i][1]="";
      }


      String head[]= {"이름", "성적"};    //테이블의 헤드데이터

      btnPanel.add(btn_submit);

      btnPanel.setLayout(new BoxLayout(btnPanel,BoxLayout.Y_AXIS));
      tPanel.add(btnPanel);
      this.add(tPanel);

      DefaultTableModel model = new DefaultTableModel(data, head);     //테이블생성
      JTable table = new JTable(model);
      JScrollPane scroll = new JScrollPane(table);                     //스크롤가능하게 붙임

      this.add(scroll);
      this.setVisible(true);

      btn_submit.addActionListener(new ActionListener() {             //버튼 클릭 이벤트

         @Override
         public void actionPerformed(ActionEvent e) {            
            func.dataTrans(dataID, subject, table);                   //dataTrans 메소드 실행
         }
      });

   }
}
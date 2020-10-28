// 권용
// 가입한 학생들의 과목별 성적 조회
package Admin;

import Login.*;
import Data.*;
import Student.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
class JP extends JPanel{
   DataSet data=new DataSet();
   SubToStu SubToStu1=new SubToStu();
   JP(String sub){//탭에 과목 명을 가져옴

      setLayout(null);
      String sTsName[]=SubToStu1.sTs(data, sub);//해당 과목을 수강하는 데이타 셋의 이름을 배열로 저장
      int score[]=SubToStu1.scoresTs(data, sub);//해당 과목을 수강하는 학생의 점수를 배열에 저장
      int Size=0;// 해당 과목에 학생이 없으면 테이블을 만들지 않아도 되므로 테이블의 사이즈를 0으로 지정
      for(int i=0;i<sTsName.length;i++) { // 이름배열과 점수 배열의 길이가 같으므로 길이의 기준은 이름배열의 길이로 함.
         if(sTsName[i]!=null) { 
            Size++; // 학생 한 명당 size를 1씩 증가시킴.
         }
      }
      if(Size!=0) { // size가 0이면 테이블을 생성하지 않아도 되기 때문에 0이 아니면 구문 실행
         String[][] contents=new String[Size][3]; // 테이블에는 이름, 성적, 등수 총 3개의 열을 지정함
         for(int i=0;i<Size;i++) { // 학생 수만큼 반복
            contents[i][0]=sTsName[i]; // 1열에는 학생의 이름을 저장함
            contents[i][1]=Integer.toString(score[i]); // 2열에는 학생의 점수를 저장함.
         }
         
         for(int i=0;i<Size-1;i++) {
            String temp="";
            for(int j=0;j<(Size-1-i);j++) {
               if(Integer.parseInt(contents[j][1])<Integer.parseInt(contents[j+1][1])) { // 성적 순으로 내림차 순을 함, 
                  temp=contents[j][0];
                  contents[j][0]=contents[j+1][0];
                  contents[j+1][0]=temp; // 이름 열을 바꿔중
                  
                  temp=contents[j][1];
                  contents[j][1]=contents[j+1][1];
                  contents[j+1][1]=temp; // 성적 열을 바꿔줌
               }
            
            }
            
         }
         contents[0][2]="1";
         int k=0;
         for(int i=1;i<Size;i++) {// 성적 열은 이미 내림차 순으로 정렬을 해 주어서 등수 열은 학생 수만큼 올라감
               
            if(Integer.parseInt(contents[i-1][1])==Integer.parseInt(contents[i][1])) { // 점수를 비교하여 같으면
               contents[i][2]=contents[i-1][2]; // 등수를 같게 함.
            }
            else {
               
               contents[i][2]=Integer.toString(i+1); //점수가 다르면 그대로 등수를 지정함
            }
         }
         String header[]= {"이름","성적","등수"};
         JTable Table=new JTable(contents,header);
           JScrollPane scrollPane = new JScrollPane(Table); //테이블을 스크롤바로 설정
           add(scrollPane,BorderLayout.CENTER);
           scrollPane.setBounds(0, 20, 580, 510);
      }
      else { // 해당 과목에 학생이 없으면 테이블에 아무 것도 안씀
         
         String header[]= {"이름","성적","등수"};
         String contents[][]= {{"","",""}};
         JTable Table=new JTable(contents,header);
           JScrollPane scrollPane = new JScrollPane(Table); 
           add(scrollPane,BorderLayout.CENTER);
           scrollPane.setBounds(0, 20, 580, 510);
      }
        //scrollPane.setVisible(true);
   }
}
public class Lookup extends JFrame{
   Lookup() {
      super("학생 조회");
      
      JTabbedPane jtab=new JTabbedPane();
      
      String[] subject= {"java","C","Py","Cpp","MFC","Jsp","JavaScript","Kotlin"};
      Component[] panel= {new JP("java"),new JP("C"),new JP("Py"),new JP("Cpp"),new JP("MFC"),new JP("Jsp"),new JP("JavaScript"),new JP("Kotlin")};
      for(int i=0;i<subject.length;i++) {
         jtab.addTab(subject[i], panel[i]); // 과목별로 테이블을 탭으로 설정하여 반복시킴
      }
      
      add(jtab);
      setSize(600,600);
      setVisible(true);
   }

}
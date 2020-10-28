// 주 : 조인태
// 학생 리스트의 과목 탐색( 학생리스트에 있는 학생들의  과목을 관리자가 과묙별로 볼 수 있게 변환 )

package Admin;
import Login.*;
import Data.*;
import Student.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
public class SubToStu {
   DataIO dio = new DataIO();
   public String[] sTs(DataSet a, String str) { // (str)이라는 과목을 수강하는 학생을 추출하는 메소드
      int k =0;
      String subName[] = new String[a.getStudent().size()-1]; //학생수의 크기만큼 배열 선언
      for(int i =0; i<a.getStudent().size(); i++) {      //학생 수의 크기만큼
         for(int j = 0; j<a.getStudent().get(i).getAlJoSubject().size(); j++)   //학생이 듣는 과목수의 수만큼
            if(a.getStudent().get(i).getAlJoSubject().get(j).getSubject().equals(str)) {  //학생이 듣는 과목중 str이라는 과목 존재 시
               subName[k] = a.getStudent().get(i).getStuname();     //subName에 저장
               k++;
            }
      }
      return subName; //예를 들어 java를 듣는 수강생들이 subName에 저장된다.
   }
   public String[] sTsID(DataSet a, String str) {  //위와 동일한 메소드인데 ID값을 추출
      int k =0;
      String subName[] = new String[a.getStudent().size()-1];
      for(int i =0; i<a.getStudent().size(); i++) {
         for(int j = 0; j<a.getStudent().get(i).getAlJoSubject().size(); j++)
            if(a.getStudent().get(i).getAlJoSubject().get(j).getSubject().equals(str)) {
               subName[k] = a.getStudent().get(i).getStuid();
               k++;
            }
      }
      return subName;
   }

   public int[] scoresTs(DataSet a, String str) { //성적을 추출
      int k =0;
      int subName[] = new int[a.getStudent().size()-1];
      for(int i =0; i<a.getStudent().size(); i++) {
         for(int j = 0; j<a.getStudent().get(i).getAlJoSubject().size(); j++)
            if(a.getStudent().get(i).getAlJoSubject().get(j).getSubject().equals(str)) {
               subName[k] = a.getStudent().get(i).getAlJoSubject().get(j).getScore();
               k++;
            }
      }
      return subName;
   }

   public void findID(String[][] a,String str) {    //SubScore에서 입력된 점수를 학생이름과 과목을 탐색하여 저장한다.

      DataSet B = new DataSet();
      int num;

      for (int i = 0; i < a.length; i++) { 
         for(int j = 0; j < B.getStudent().size(); j++) {   //학생의 수만큼
            if(B.getStudent().get(j).getStuid().equals(a[i][0])) {   //학생의 ID와 테이블에 저장된 ID 같은 ID를 찾음
               for(int k=0; k <B.getStudent().get(j).getAlJoSubject().size();k++) // 학생이 수강하는 과목수만큼
               {
                  if(B.getStudent().get(j).getAlJoSubject().get(k).getSubject().equals(str)) //학생이 수강하는 과목과 넘어온 str의 과목이 같으면
                  {   
                	// num 변수에 테이블에 저장된 변수 저장 
                     num = Integer.parseInt(a[i][1]); 
                  // DataSec에 num 을저장
                     B.getStudent().get(j).getAlJoSubject().get(k).setScore(num);            
                  }
               }
            }
         }
      }
      dio.saveStudentData(B.getStudent()); // csv파일에 입력
      JOptionPane.showMessageDialog(null, "데이터가 입력되었습니다.");
   }
   public void dataTrans(String[][] dataID, String str, JTable table)
   {
      try {
         for(int i=0; i<dataID.length; i++) {
            dataID[i][1] = (String)table.getValueAt(i, 1); //테이블의 행과 열의 값을 받아 data 배열에 저장한다.
         }
         findID(dataID, str); //저장된 data배열의 값을 DataSet에 저장
      } catch (Exception e2) {
         // TODO: handle exception
         JOptionPane.showMessageDialog(null, "모든 값을 입력해주세요");
      }
   }
}
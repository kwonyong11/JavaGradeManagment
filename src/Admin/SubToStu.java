// �� : ������
// �л� ����Ʈ�� ���� Ž��( �л�����Ʈ�� �ִ� �л�����  ������ �����ڰ� ���򺰷� �� �� �ְ� ��ȯ )

package Admin;
import Login.*;
import Data.*;
import Student.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
public class SubToStu {
   DataIO dio = new DataIO();
   public String[] sTs(DataSet a, String str) { // (str)�̶�� ������ �����ϴ� �л��� �����ϴ� �޼ҵ�
      int k =0;
      String subName[] = new String[a.getStudent().size()-1]; //�л����� ũ�⸸ŭ �迭 ����
      for(int i =0; i<a.getStudent().size(); i++) {      //�л� ���� ũ�⸸ŭ
         for(int j = 0; j<a.getStudent().get(i).getAlJoSubject().size(); j++)   //�л��� ��� ������� ����ŭ
            if(a.getStudent().get(i).getAlJoSubject().get(j).getSubject().equals(str)) {  //�л��� ��� ������ str�̶�� ���� ���� ��
               subName[k] = a.getStudent().get(i).getStuname();     //subName�� ����
               k++;
            }
      }
      return subName; //���� ��� java�� ��� ���������� subName�� ����ȴ�.
   }
   public String[] sTsID(DataSet a, String str) {  //���� ������ �޼ҵ��ε� ID���� ����
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

   public int[] scoresTs(DataSet a, String str) { //������ ����
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

   public void findID(String[][] a,String str) {    //SubScore���� �Էµ� ������ �л��̸��� ������ Ž���Ͽ� �����Ѵ�.

      DataSet B = new DataSet();
      int num;

      for (int i = 0; i < a.length; i++) { 
         for(int j = 0; j < B.getStudent().size(); j++) {   //�л��� ����ŭ
            if(B.getStudent().get(j).getStuid().equals(a[i][0])) {   //�л��� ID�� ���̺� ����� ID ���� ID�� ã��
               for(int k=0; k <B.getStudent().get(j).getAlJoSubject().size();k++) // �л��� �����ϴ� �������ŭ
               {
                  if(B.getStudent().get(j).getAlJoSubject().get(k).getSubject().equals(str)) //�л��� �����ϴ� ����� �Ѿ�� str�� ������ ������
                  {   
                	// num ������ ���̺� ����� ���� ���� 
                     num = Integer.parseInt(a[i][1]); 
                  // DataSec�� num ������
                     B.getStudent().get(j).getAlJoSubject().get(k).setScore(num);            
                  }
               }
            }
         }
      }
      dio.saveStudentData(B.getStudent()); // csv���Ͽ� �Է�
      JOptionPane.showMessageDialog(null, "�����Ͱ� �ԷµǾ����ϴ�.");
   }
   public void dataTrans(String[][] dataID, String str, JTable table)
   {
      try {
         for(int i=0; i<dataID.length; i++) {
            dataID[i][1] = (String)table.getValueAt(i, 1); //���̺��� ��� ���� ���� �޾� data �迭�� �����Ѵ�.
         }
         findID(dataID, str); //����� data�迭�� ���� DataSet�� ����
      } catch (Exception e2) {
         // TODO: handle exception
         JOptionPane.showMessageDialog(null, "��� ���� �Է����ּ���");
      }
   }
}
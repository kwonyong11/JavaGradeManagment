// �ǿ�
// ������ �л����� ���� ���� ��ȸ
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
   JP(String sub){//�ǿ� ���� ���� ������

      setLayout(null);
      String sTsName[]=SubToStu1.sTs(data, sub);//�ش� ������ �����ϴ� ����Ÿ ���� �̸��� �迭�� ����
      int score[]=SubToStu1.scoresTs(data, sub);//�ش� ������ �����ϴ� �л��� ������ �迭�� ����
      int Size=0;// �ش� ���� �л��� ������ ���̺��� ������ �ʾƵ� �ǹǷ� ���̺��� ����� 0���� ����
      for(int i=0;i<sTsName.length;i++) { // �̸��迭�� ���� �迭�� ���̰� �����Ƿ� ������ ������ �̸��迭�� ���̷� ��.
         if(sTsName[i]!=null) { 
            Size++; // �л� �� ��� size�� 1�� ������Ŵ.
         }
      }
      if(Size!=0) { // size�� 0�̸� ���̺��� �������� �ʾƵ� �Ǳ� ������ 0�� �ƴϸ� ���� ����
         String[][] contents=new String[Size][3]; // ���̺��� �̸�, ����, ��� �� 3���� ���� ������
         for(int i=0;i<Size;i++) { // �л� ����ŭ �ݺ�
            contents[i][0]=sTsName[i]; // 1������ �л��� �̸��� ������
            contents[i][1]=Integer.toString(score[i]); // 2������ �л��� ������ ������.
         }
         
         for(int i=0;i<Size-1;i++) {
            String temp="";
            for(int j=0;j<(Size-1-i);j++) {
               if(Integer.parseInt(contents[j][1])<Integer.parseInt(contents[j+1][1])) { // ���� ������ ������ ���� ��, 
                  temp=contents[j][0];
                  contents[j][0]=contents[j+1][0];
                  contents[j+1][0]=temp; // �̸� ���� �ٲ���
                  
                  temp=contents[j][1];
                  contents[j][1]=contents[j+1][1];
                  contents[j+1][1]=temp; // ���� ���� �ٲ���
               }
            
            }
            
         }
         contents[0][2]="1";
         int k=0;
         for(int i=1;i<Size;i++) {// ���� ���� �̹� ������ ������ ������ �� �־ ��� ���� �л� ����ŭ �ö�
               
            if(Integer.parseInt(contents[i-1][1])==Integer.parseInt(contents[i][1])) { // ������ ���Ͽ� ������
               contents[i][2]=contents[i-1][2]; // ����� ���� ��.
            }
            else {
               
               contents[i][2]=Integer.toString(i+1); //������ �ٸ��� �״�� ����� ������
            }
         }
         String header[]= {"�̸�","����","���"};
         JTable Table=new JTable(contents,header);
           JScrollPane scrollPane = new JScrollPane(Table); //���̺��� ��ũ�ѹٷ� ����
           add(scrollPane,BorderLayout.CENTER);
           scrollPane.setBounds(0, 20, 580, 510);
      }
      else { // �ش� ���� �л��� ������ ���̺� �ƹ� �͵� �Ⱦ�
         
         String header[]= {"�̸�","����","���"};
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
      super("�л� ��ȸ");
      
      JTabbedPane jtab=new JTabbedPane();
      
      String[] subject= {"java","C","Py","Cpp","MFC","Jsp","JavaScript","Kotlin"};
      Component[] panel= {new JP("java"),new JP("C"),new JP("Py"),new JP("Cpp"),new JP("MFC"),new JP("Jsp"),new JP("JavaScript"),new JP("Kotlin")};
      for(int i=0;i<subject.length;i++) {
         jtab.addTab(subject[i], panel[i]); // ���񺰷� ���̺��� ������ �����Ͽ� �ݺ���Ŵ
      }
      
      add(jtab);
      setSize(600,600);
      setVisible(true);
   }

}
//  ������
// �л� ���� �Է�

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
      super("���� �Է�");
      
      String[] subject= {"java","C","Py","Cpp","MFC","Jsp","JavaScript","Kotlin"}; //�����̸� �迭�� ����
      JPanel[] panel= {new stPanel("java"),new stPanel("C"),new stPanel("Py"),new stPanel("Cpp"),
            new stPanel("MFC"),new stPanel("Jsp"),new stPanel("JavaScript"),new stPanel("Kotlin")};
      JTabbedPane jtab = new JTabbedPane();
      
      for(int i =0; i<8;i++)
         jtab.addTab(subject[i],panel[i]);

      this.add(jtab);      //frame�� jtap ����.
      this.setSize(600,600);
      this.setVisible(true);
   }
}
class stPanel extends JPanel {
   int i =0;
   DataSet data1 = new DataSet();    //DataSet ���� ->������ �����ϱ� ����
   SubToStu func = new SubToStu();   //������ ���� Ž�� �޼ҵ带 ����ִ� Ŭ���� �ν��Ͻ�ȭ
   stPanel(String subject) {
      JButton btn_submit = new JButton("���� ����"); //���� ��ư ����
      JPanel tPanel = new JPanel();
      JPanel btnPanel = new JPanel();

      String sTsName[] = func.sTs(data1,subject);     // sTs�Լ� ȣ�� �ּ��� SubToStu�� ����.
      String sTsID[] = func.sTsID(data1,subject);
      int Size=0;
      for(int i=0;i<sTsName.length;i++) {
    	//�̸��� null���̸� size�� ���Ե��� �ʾƵ� �ǹǷ� data�� null���� �ƴϸ� size�� �������� table�� ����� �������ش�.
         if(sTsName[i]!=null) {
            Size++;
         }
      }
      String data[][]= new String[Size][2];    //�̸��� �޴� �迭
      String dataID[][]= new String[Size][2];  //ID�޴� �迭

      for(int i=0;i<Size;i++) {

         dataID[i][0]= sTsID[i];        //���̺��� ���¿� �°� ����
         dataID[i][1]="";
      }
      for(int i=0;i<Size;i++) {
         data[i][0]= sTsName[i];        //���̺��� ���¿� �°� ����
         data[i][1]="";
      }


      String head[]= {"�̸�", "����"};    //���̺��� ��嵥����

      btnPanel.add(btn_submit);

      btnPanel.setLayout(new BoxLayout(btnPanel,BoxLayout.Y_AXIS));
      tPanel.add(btnPanel);
      this.add(tPanel);

      DefaultTableModel model = new DefaultTableModel(data, head);     //���̺����
      JTable table = new JTable(model);
      JScrollPane scroll = new JScrollPane(table);                     //��ũ�Ѱ����ϰ� ����

      this.add(scroll);
      this.setVisible(true);

      btn_submit.addActionListener(new ActionListener() {             //��ư Ŭ�� �̺�Ʈ

         @Override
         public void actionPerformed(ActionEvent e) {            
            func.dataTrans(dataID, subject, table);                   //dataTrans �޼ҵ� ����
         }
      });

   }
}
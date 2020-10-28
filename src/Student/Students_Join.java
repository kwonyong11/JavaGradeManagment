// 이경현
 //학생 수강신청 기능

package Student;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Data.*;
import Login.AlJoSubject;

public class Students_Join extends Frame {

   DataSet da = new DataSet();
   DataIO dio = new DataIO();
   final int WIDTH = 600;
   final int HEIGH = 600;

   JTable t_sub; // 수강신청 목록 테이블
   JTable t_subjoin;// 수강신청 신청 테이블

   JFrame frm; // 메인 프레임

   DefaultTableModel model_sub; // 수강신청 목록
   DefaultTableModel model_subjoin; // 수강신청 신청 목록

   int boxcre = 0; 
   

   /////////////////////////////////////////

   public Students_Join() {

      String category1[] = { "과목명", "학점", "교수님 명", "남은 인원수" };
      String category2[] = { "과목명", "학점", "교수님 명", "남은 인원수" };
      ArrayList<String> overlap = new ArrayList<String>();
      ArrayList<Integer> overlap2 = new ArrayList<Integer>();
      
      for (int j = 0; j < da.getSub().length; j++) overlap.add(da.getSub()[j]);
      
   
      for (int i = 0; i < da.getStudent().get(da.getStuindex()).sub.size(); i++)
         for (int j = 0; j < da.getSub().length; j++) 
          {
            if (da.getStudent().get(da.getStuindex()).sub.get(i).getSubject().equals(da.getSub()[j]))
            {//저장된 과목(i) 랑 원래 과목(j)
               overlap.remove(da.getSub()[j]);               
               overlap2.add(j);
            }
         }
      Object contents2[][] = new String[da.getSub().length-overlap.size()][4]; // String header[];
      
      for (int j = 0; j < overlap2.size(); j++) 
      {
         contents2[j][0] = da.getSub()[overlap2.get(j)]; // 과목명
         contents2[j][1] = Integer.toString(da.getScore()[overlap2.get(j)]); // 학점
         contents2[j][2] = da.getPro()[overlap2.get(j)]; // 교수님명
         contents2[j][3] = Integer.toString(da.getPerson()[overlap2.get(j)]); // 남은 인원
      }
      
      
      Object contents1[][] = new String[overlap.size()][4]; // String header[];
      
      
      int cnt=0;
      for (int j = 0; j < overlap.size(); j++) 
         for (int i = 0; i < da.getSub().length; i++) 
         {
            if(overlap.get(j).equals(da.getSub()[i]))
            {
               contents1[cnt][0] = da.getSub()[i]; // 과목명
               contents1[cnt][1] = Integer.toString(da.getScore()[i]); // 학점
               contents1[cnt][2] = da.getPro()[i]; // 교수님명
               contents1[cnt][3] = Integer.toString(da.getPerson()[i]); // 남은 인원
               cnt++;
            }

         }
      
      
      JFrame frm = new JFrame("수강신청");

      frm.setSize(WIDTH, HEIGH);
      frm.setResizable(false);

      frm.setLocationRelativeTo(null);
      frm.getContentPane().setLayout(null);

      // ****************************************************************
      // 수강신청 제목
      JLabel l_title = new JLabel("수강신청");
      l_title.setBounds(2, 30, WIDTH, 30); // 위치
      l_title.setFont(new Font(null, Font.BOLD, 30));
      l_title.setHorizontalAlignment(JLabel.CENTER);
      frm.getContentPane().add(l_title);

      // ****************************************************************
      // 수강신청 목록
      JPanel p_sub = new JPanel(); // 수강신청 목록 뷰
      p_sub.setBounds(2, 100, 600, 280);
      frm.getContentPane().add(p_sub);

      JLabel l_sub = new JLabel("< 수강신청 목록 >");
      l_sub.setBounds(70, 80, WIDTH, 20);
      l_sub.setFont(l_sub.getFont().deriveFont(15.0f));
      frm.getContentPane().add(l_sub);

   

       model_sub = new DefaultTableModel(contents1, category1);
         t_sub = new JTable(model_sub);
         JScrollPane scrollPane = new JScrollPane(t_sub);
         p_sub.add(scrollPane);

         scrollPane.setPreferredSize(new Dimension(450,150));
         p_sub.setBounds(50, 100, 500, 170);

         // *****************************************************
         // 나의 신청 목록
         JLabel l_subjoin = new JLabel("< 나의 신청 목록 >");
         l_subjoin.setBounds(70, 280, WIDTH, 20);
         l_subjoin.setFont(l_subjoin.getFont().deriveFont(15.0f));
         frm.getContentPane().add(l_subjoin);

         JPanel p_subjoin = new JPanel(); // 수강신청 목록 뷰
         p_subjoin.setBounds(2, 300, 600, 130);
         frm.getContentPane().add(p_subjoin);
         model_subjoin = new DefaultTableModel(contents2, category2);

         JTable t_subjoin = new JTable(model_subjoin);
         JScrollPane scrollPane2 = new JScrollPane(t_subjoin);
         scrollPane2.setPreferredSize(new Dimension(450,100));
         p_subjoin.add(scrollPane2);
         

      // ****************************************************************
      // 신청 확인 단추
      JButton btn_ok = new JButton("신 청");// 수강신청 확인 버튼
      btn_ok.setBounds(360, 465, 80, 25);
      frm.getContentPane().add(btn_ok);

      JButton btn_cancel = new JButton("취 소");// 수강신청 확인 버튼
      btn_cancel.setBounds(450, 465, 80, 25);
      frm.getContentPane().add(btn_cancel);

      frm.getContentPane().add(l_sub);
      frm.getContentPane().add(l_subjoin);

      frm.setVisible(true);
  

      // 수강신청 버튼
      btn_ok.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            int tableIndex = t_sub.getSelectedRow();
            int dataIndex = 0;

            if (tableIndex == -1)// 테이블 선택 안했을 때
               JOptionPane.showMessageDialog(null, "과목을 선택해 주세요");
            else {

               boxcre = da.getcre() + da.getScore()[dataIndex];

               String[] selData = new String[4];
               

               for (int i = 0; i < da.getSub().length; i++) {
                  if (da.getSub()[i].equals(model_sub.getValueAt(tableIndex, 0)))
                     dataIndex = i;
               }
               if (boxcre > 19) {
                  JOptionPane.showMessageDialog(null, "신청 학점을 초과하였습니다.");
                  
               } else {
                  da.setcre(da.getcre() + da.getScore()[dataIndex]);   
                  da.getPerson()[dataIndex] = da.getPerson()[dataIndex] - 1;

                  selData[0] = da.getSub()[dataIndex]; // 과목명
                  selData[1] = Integer.toString(da.getScore()[dataIndex]); // 학점
                  selData[2] = da.getPro()[dataIndex]; // 교수님명
                  selData[3] = Integer.toString(da.getPerson()[dataIndex]); // 남은 인원
                  // 서브젝트 어레이에 저장
                  da.getStudent().get(da.getStuindex()).setAlJoSubject(new AlJoSubject(da.getSub()[dataIndex]));// 과목                                                                                       
                  

                  

                  contents1[tableIndex][3] = selData[3];

                  model_subjoin.addRow(selData);
                  model_sub.removeRow(tableIndex);
               }

            }
         //  학생데이터 파일에 저장   
            dio.saveStudentData(da.getStudent());

         }

      });

      // 수강신청 취소버튼
      btn_cancel.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            int tableIndex = t_subjoin.getSelectedRow();
            int dataIndex = 0;

            if (tableIndex == -1)
               JOptionPane.showMessageDialog(null, "취소할 과목을 선택해 주세요");
            else {
               String[] selData = new String[4];

               for (int i = 0; i < da.getSub().length; i++) {

                  if (da.getSub()[i].equals(model_subjoin.getValueAt(tableIndex, 0)))
                     dataIndex = i;

               }
               da.getPerson()[dataIndex] = da.getPerson()[dataIndex] + 1;
               da.setcre(da.getcre() - da.getScore()[dataIndex]);
            
               selData[0] = da.getSub()[dataIndex]; // 과목명
               selData[1] = Integer.toString(da.getScore()[dataIndex]); // 학점
               selData[2] = da.getPro()[dataIndex]; // 교수님명
               selData[3] = Integer.toString(da.getPerson()[dataIndex]); // 남은 인원

               // 학생클레스안에

               contents2[tableIndex][3] = selData[3];

               for (int i = 0; i < da.getStudent().get(da.getStuindex()).sub.size(); i++) {
                  if (da.getStudent().get(da.getStuindex()).sub.get(i).getSubject()
                        .equals(model_subjoin.getValueAt(tableIndex, 0)))
                     da.getStudent().get(da.getStuindex()).sub.remove(i);

               }
               model_sub.addRow(selData);
               model_subjoin.removeRow(tableIndex);
            }
            dio.saveStudentData(da.getStudent());

         }
      });
   }


}

//이거 마지막
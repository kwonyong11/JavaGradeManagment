// �̰���
 //�л� ������û ���

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

   JTable t_sub; // ������û ��� ���̺�
   JTable t_subjoin;// ������û ��û ���̺�

   JFrame frm; // ���� ������

   DefaultTableModel model_sub; // ������û ���
   DefaultTableModel model_subjoin; // ������û ��û ���

   int boxcre = 0; 
   

   /////////////////////////////////////////

   public Students_Join() {

      String category1[] = { "�����", "����", "������ ��", "���� �ο���" };
      String category2[] = { "�����", "����", "������ ��", "���� �ο���" };
      ArrayList<String> overlap = new ArrayList<String>();
      ArrayList<Integer> overlap2 = new ArrayList<Integer>();
      
      for (int j = 0; j < da.getSub().length; j++) overlap.add(da.getSub()[j]);
      
   
      for (int i = 0; i < da.getStudent().get(da.getStuindex()).sub.size(); i++)
         for (int j = 0; j < da.getSub().length; j++) 
          {
            if (da.getStudent().get(da.getStuindex()).sub.get(i).getSubject().equals(da.getSub()[j]))
            {//����� ����(i) �� ���� ����(j)
               overlap.remove(da.getSub()[j]);               
               overlap2.add(j);
            }
         }
      Object contents2[][] = new String[da.getSub().length-overlap.size()][4]; // String header[];
      
      for (int j = 0; j < overlap2.size(); j++) 
      {
         contents2[j][0] = da.getSub()[overlap2.get(j)]; // �����
         contents2[j][1] = Integer.toString(da.getScore()[overlap2.get(j)]); // ����
         contents2[j][2] = da.getPro()[overlap2.get(j)]; // �����Ը�
         contents2[j][3] = Integer.toString(da.getPerson()[overlap2.get(j)]); // ���� �ο�
      }
      
      
      Object contents1[][] = new String[overlap.size()][4]; // String header[];
      
      
      int cnt=0;
      for (int j = 0; j < overlap.size(); j++) 
         for (int i = 0; i < da.getSub().length; i++) 
         {
            if(overlap.get(j).equals(da.getSub()[i]))
            {
               contents1[cnt][0] = da.getSub()[i]; // �����
               contents1[cnt][1] = Integer.toString(da.getScore()[i]); // ����
               contents1[cnt][2] = da.getPro()[i]; // �����Ը�
               contents1[cnt][3] = Integer.toString(da.getPerson()[i]); // ���� �ο�
               cnt++;
            }

         }
      
      
      JFrame frm = new JFrame("������û");

      frm.setSize(WIDTH, HEIGH);
      frm.setResizable(false);

      frm.setLocationRelativeTo(null);
      frm.getContentPane().setLayout(null);

      // ****************************************************************
      // ������û ����
      JLabel l_title = new JLabel("������û");
      l_title.setBounds(2, 30, WIDTH, 30); // ��ġ
      l_title.setFont(new Font(null, Font.BOLD, 30));
      l_title.setHorizontalAlignment(JLabel.CENTER);
      frm.getContentPane().add(l_title);

      // ****************************************************************
      // ������û ���
      JPanel p_sub = new JPanel(); // ������û ��� ��
      p_sub.setBounds(2, 100, 600, 280);
      frm.getContentPane().add(p_sub);

      JLabel l_sub = new JLabel("< ������û ��� >");
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
         // ���� ��û ���
         JLabel l_subjoin = new JLabel("< ���� ��û ��� >");
         l_subjoin.setBounds(70, 280, WIDTH, 20);
         l_subjoin.setFont(l_subjoin.getFont().deriveFont(15.0f));
         frm.getContentPane().add(l_subjoin);

         JPanel p_subjoin = new JPanel(); // ������û ��� ��
         p_subjoin.setBounds(2, 300, 600, 130);
         frm.getContentPane().add(p_subjoin);
         model_subjoin = new DefaultTableModel(contents2, category2);

         JTable t_subjoin = new JTable(model_subjoin);
         JScrollPane scrollPane2 = new JScrollPane(t_subjoin);
         scrollPane2.setPreferredSize(new Dimension(450,100));
         p_subjoin.add(scrollPane2);
         

      // ****************************************************************
      // ��û Ȯ�� ����
      JButton btn_ok = new JButton("�� û");// ������û Ȯ�� ��ư
      btn_ok.setBounds(360, 465, 80, 25);
      frm.getContentPane().add(btn_ok);

      JButton btn_cancel = new JButton("�� ��");// ������û Ȯ�� ��ư
      btn_cancel.setBounds(450, 465, 80, 25);
      frm.getContentPane().add(btn_cancel);

      frm.getContentPane().add(l_sub);
      frm.getContentPane().add(l_subjoin);

      frm.setVisible(true);
  

      // ������û ��ư
      btn_ok.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            int tableIndex = t_sub.getSelectedRow();
            int dataIndex = 0;

            if (tableIndex == -1)// ���̺� ���� ������ ��
               JOptionPane.showMessageDialog(null, "������ ������ �ּ���");
            else {

               boxcre = da.getcre() + da.getScore()[dataIndex];

               String[] selData = new String[4];
               

               for (int i = 0; i < da.getSub().length; i++) {
                  if (da.getSub()[i].equals(model_sub.getValueAt(tableIndex, 0)))
                     dataIndex = i;
               }
               if (boxcre > 19) {
                  JOptionPane.showMessageDialog(null, "��û ������ �ʰ��Ͽ����ϴ�.");
                  
               } else {
                  da.setcre(da.getcre() + da.getScore()[dataIndex]);   
                  da.getPerson()[dataIndex] = da.getPerson()[dataIndex] - 1;

                  selData[0] = da.getSub()[dataIndex]; // �����
                  selData[1] = Integer.toString(da.getScore()[dataIndex]); // ����
                  selData[2] = da.getPro()[dataIndex]; // �����Ը�
                  selData[3] = Integer.toString(da.getPerson()[dataIndex]); // ���� �ο�
                  // ������Ʈ ��̿� ����
                  da.getStudent().get(da.getStuindex()).setAlJoSubject(new AlJoSubject(da.getSub()[dataIndex]));// ����                                                                                       
                  

                  

                  contents1[tableIndex][3] = selData[3];

                  model_subjoin.addRow(selData);
                  model_sub.removeRow(tableIndex);
               }

            }
         //  �л������� ���Ͽ� ����   
            dio.saveStudentData(da.getStudent());

         }

      });

      // ������û ��ҹ�ư
      btn_cancel.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            int tableIndex = t_subjoin.getSelectedRow();
            int dataIndex = 0;

            if (tableIndex == -1)
               JOptionPane.showMessageDialog(null, "����� ������ ������ �ּ���");
            else {
               String[] selData = new String[4];

               for (int i = 0; i < da.getSub().length; i++) {

                  if (da.getSub()[i].equals(model_subjoin.getValueAt(tableIndex, 0)))
                     dataIndex = i;

               }
               da.getPerson()[dataIndex] = da.getPerson()[dataIndex] + 1;
               da.setcre(da.getcre() - da.getScore()[dataIndex]);
            
               selData[0] = da.getSub()[dataIndex]; // �����
               selData[1] = Integer.toString(da.getScore()[dataIndex]); // ����
               selData[2] = da.getPro()[dataIndex]; // �����Ը�
               selData[3] = Integer.toString(da.getPerson()[dataIndex]); // ���� �ο�

               // �л�Ŭ�����ȿ�

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

//�̰� ������
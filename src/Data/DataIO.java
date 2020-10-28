// ������
// ������ �б�, ���� ��� Ŭ����(�л�, ������)

package Data;
import Student.*;
import Login.*;
import java.io.*;

import java.util.ArrayList;

public class DataIO {	
	private String filepath = "data\\LoginData_IDPW.csv";
	private String filepath2 = "data\\LoginData_IDNAME.csv";
	private String testfilepath = "data\\testLoginData_IDPW.csv";
	private String testfilepath2 = "data\\testLoginData_IDNAME.csv";
	
	private String adminfilepath = "data\\adminLoginData_IDPW.csv";
	ArrayList<Student> stu = new ArrayList<Student>();
	
	// �л������� ���ΰ�ħ
	public ArrayList<Student> refreshStudentdata(){
		stu = getStudentdata();
		return stu;
	}
	
	//ID �ߺ�Ȯ�� 
	public int idCheck(String id) {		
        int returnValue = 0; // ���� �� ����, 0 : ���� / 1: ��밡�� ID / 2 : ID �ߺ�		
        try {            
            File csv = new File(testfilepath);
            //File csv = new File(filepath);
            BufferedReader br = new BufferedReader(new FileReader(csv));
            String line = "";          
            String[] token = new String[10];
            while ((line = br.readLine()) != null) {
                // -1 �ɼ��� ������ "," ���� �� ���鵵 �б� ���� �ɼ�
                token = line.split(",", -1);                
                if(token[0].equals(id) || "".equals(id)) {
                	returnValue = 2;
               		break;
               	}
               	else {
               		returnValue = 1;
               	}                             
            }                      
            br.close();             
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
            returnValue=0;
        } 
        catch (IOException e) {
            e.printStackTrace();
            returnValue=0;
        }
        return returnValue;
	}
	// �л�����Ʈ ��ȯ �޼ҵ�(�л����� �ҷ�����)
	public ArrayList<Student> getStudentdata(){		
		try {    
			// �л� ����, �̸� ���� �б�, �׽�Ʈ����
            File csv = new File(testfilepath);          
            //File csv = new File(filepath); 
            BufferedReader br = new BufferedReader(new FileReader(csv));
            String line = "";          
            String[] idpass = new String[3];
            String[] idname = new String[15];
            // �л� id, pass �߰�
            while ((line = br.readLine()) != null) {
                // -1 �ɼ��� ������ "," ���� �� ���鵵 �б� ���� �ɼ�
            	idpass = line.split(",", -1);
            	Student tmps = new Student("�⺻�̸�",idpass[1], idpass[0]);            	
            	stu.add(tmps);
            }                      
            br.close();
            
            // �л� ����, �̸� ���� �б�  , �׽�Ʈ����          
            csv = new File(testfilepath2);   
            //csv = new File(filepath2); 
            br = new BufferedReader(new FileReader(csv));
            int count = 0; // �л� ��̸���Ʈ �ε���            
            while ((line = br.readLine()) != null) {
                // -1 �ɼ��� ������ "," ���� �� ���鵵 �б� ���� �ɼ�
            	idname = line.split(",", -1);
            	stu.get(count).setStuname(idname[1]);	// �л�����Ʈ�� �л��̸� �߰�
            	int index = Integer.parseInt(idname[2]);
            	int subindex = 3; // �л� ���� ���� ���� �ε���
            	// ������ ������ �����߰�
            	if(index > 0) {
            		AlJoSubject tmpsub;
            		// �л�����Ʈ�� �����߰�
            		for(int i=1;i<=index;i++) {
            			tmpsub = new AlJoSubject(idname[subindex],Integer.parseInt(idname[subindex+1]));
            			subindex+=2;
            			stu.get(count).setAlJoSubject(tmpsub);
            		}            		
            	}
            	count++;
            }                      
            br.close();            
        } 
        catch (FileNotFoundException e) {
            System.out.println("���� ������ ã�� ���߽��ϴ�.");
        }
        catch (IOException e) {
        	System.out.println(" �����б� ����� ���� ");          
        }
		return stu;
	}
	// �л� ���� ����(id, �̸�, ������)
	public void saveStudentData(ArrayList<Student> stu) {
		try {
			// id,pw ���� ����			
			for(int i =0; i<stu.size();i++) {
				BufferedWriter bw;
				
				bw = makeBufferWriter(testfilepath, !(i==0) );
				//bw = makeBufferWriter(filepath, !(i==0) );
				bw.write(stu.get(i).getStuid()+","+stu.get(i).getStupw()+"\n");
				bw.flush();
				bw.close();				
			
				bw = makeBufferWriter(testfilepath2, !(i==0) );
				//bw = makeBufferWriter(filepath2, !(i==0) );
				// ���� �����ϱ����� ������ ���ڿ��� ��ȯ
				String str =stu.get(i).getStuid() + ","+ stu.get(i).getStuname()+","+stu.get(i).getAlJoSubject().size();
				for(int j = 0; j<stu.get(i).getAlJoSubject().size();j++) {
					str += "," + stu.get(i).getAlJoSubject().get(j).getSubject()+","
							+stu.get(i).getAlJoSubject().get(j).getScore();
				}
				str += "\n";
				//���Ͽ� ������ �ۼ�
				bw.write(str);
				bw.flush();
				bw.close();	
			}	
					
		}
		catch (FileNotFoundException e) {			
            System.out.println("�ۼ� ���� ��ã��");            
        } 
        catch (IOException e) {
        	 System.out.println("����� ����");
        }	
	}
	//���� ��ȯ �޼ҵ�
	BufferedWriter makeBufferWriter(String filepath, boolean append) {
			try {
				return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath, append), "MS949"));
				
			} catch (UnsupportedEncodingException | FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			return null;			
	}
	
	// ȸ�����Խ� ���Ͽ� ������ ����
	public void joinFileWrite(String name, String pw, String id) {
		try {
			// id,pw ���� ����
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(testfilepath, true), "MS949"));
			//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath, true), "MS949"));
			bw.write(id+","+pw+"\n");
			bw.flush();
			bw.close();
			// �̸�, id ���� ����
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(testfilepath2, true), "MS949"));
			//bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath2, true), "MS949"));
			bw.write(id+","+name+","+0+"\n");
			bw.flush();
			bw.close();			
		}
		catch (FileNotFoundException e) {
			
            System.out.println("�ۼ� ���� ��ã��");
            
        } 
        catch (IOException e) {
        	 System.out.println("����� ����");
        }		
	}
	// ������ �н����� ����
	public void adminPwModify(String pass) {
		try {
			//adminfilepath ����� ���� (����������) �б����� �������
			File csv = new File(adminfilepath);     
			// ������ ���� ������ �д� ���� ����
            BufferedReader br = new BufferedReader(new FileReader(csv));
            String line = "";          
            String[] idpass = new String[3];
            line = br.readLine();
            idpass = line.split(",", -1);
            br.close();           
            idpass[1] = pass;
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(adminfilepath), "MS949"));
            bw.write(idpass[0]+","+idpass[1]+"\n");
            bw.flush();
            bw.close();            
		}
		catch (FileNotFoundException e) {
            System.out.println("������ ID �н����� ������ ��ã�ҽ��ϴ�.");
        }
        catch (IOException e) {
        	System.out.println("������ ID �н����� ���� ����� �����Դϴ�.");          
        }
	}
	// ������ �н����� ��������
	public String getAdminPw() {
		String returnValue=null;
		try {
			//adminfilepath ����� ���� (����������) �б����� �������
			File csv = new File(adminfilepath);     
			// ������ ���� ������ �д� ���� ����
            BufferedReader br = new BufferedReader(new FileReader(csv));
            String line = "";          
            String[] idpass = new String[3];
            line = br.readLine();
            idpass = line.split(",", -1);
            br.close();           
            returnValue= idpass[1];                      
		}
		catch (FileNotFoundException e) {
            System.out.println("������ ID �н����� ������ ��ã�ҽ��ϴ�.");
        }
        catch (IOException e) {
        	System.out.println("������ ID �н����� ���� ����� �����Դϴ�.");          
        }
		return returnValue;
	}
	// ������ �α��� Ȯ�� �޼ҵ�
	public boolean adminDataCheck(String id , String pw) {
		boolean returnValue = false;	// true : ������ ID, PW�� ����, false : �ٸ�
		try {
			//adminfilepath ����� ���� (����������) �б����� �������
			File csv = new File(adminfilepath);      
			// ������ ���� ������ �д� ���� ����
            BufferedReader br = new BufferedReader(new FileReader(csv));
            String line = "";          
            String[] idpass = new String[3];
            line = br.readLine();	// �����б�
            idpass = line.split(",", -1);
            // ������ ������ ID, PW�� ��ġ�ϴ��� Ȯ��
            if(idpass[0].equals(id) && idpass[1].equals(pw)) {
            	returnValue = true;
            }            	
            else {
            	returnValue = false;
            }
		}
		catch (FileNotFoundException e) {
            System.out.println("������ ID �н����� ������ ��ã�ҽ��ϴ�.");
        }
        catch (IOException e) {
        	System.out.println("������ ID �н����� ���� ����� �����Դϴ�.");          
        }
		
		return returnValue;
	}
	public static void main(String[] args) {
		DataIO i = new DataIO();			
	}
}

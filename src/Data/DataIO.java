// 김종희
// 데이터 읽기, 쓰기 담당 클래스(학생, 관리자)

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
	
	// 학생데이터 새로고침
	public ArrayList<Student> refreshStudentdata(){
		stu = getStudentdata();
		return stu;
	}
	
	//ID 중복확인 
	public int idCheck(String id) {		
        int returnValue = 0; // 리턴 값 변수, 0 : 에러 / 1: 사용가능 ID / 2 : ID 중복		
        try {            
            File csv = new File(testfilepath);
            //File csv = new File(filepath);
            BufferedReader br = new BufferedReader(new FileReader(csv));
            String line = "";          
            String[] token = new String[10];
            while ((line = br.readLine()) != null) {
                // -1 옵션은 마지막 "," 이후 빈 공백도 읽기 위한 옵션
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
	// 학생리스트 반환 메소드(학생정보 불러오기)
	public ArrayList<Student> getStudentdata(){		
		try {    
			// 학생 성적, 이름 정보 읽기, 테스트파일
            File csv = new File(testfilepath);          
            //File csv = new File(filepath); 
            BufferedReader br = new BufferedReader(new FileReader(csv));
            String line = "";          
            String[] idpass = new String[3];
            String[] idname = new String[15];
            // 학생 id, pass 추가
            while ((line = br.readLine()) != null) {
                // -1 옵션은 마지막 "," 이후 빈 공백도 읽기 위한 옵션
            	idpass = line.split(",", -1);
            	Student tmps = new Student("기본이름",idpass[1], idpass[0]);            	
            	stu.add(tmps);
            }                      
            br.close();
            
            // 학생 성적, 이름 정보 읽기  , 테스트파일          
            csv = new File(testfilepath2);   
            //csv = new File(filepath2); 
            br = new BufferedReader(new FileReader(csv));
            int count = 0; // 학생 어레이리스트 인덱스            
            while ((line = br.readLine()) != null) {
                // -1 옵션은 마지막 "," 이후 빈 공백도 읽기 위한 옵션
            	idname = line.split(",", -1);
            	stu.get(count).setStuname(idname[1]);	// 학생리스트에 학생이름 추가
            	int index = Integer.parseInt(idname[2]);
            	int subindex = 3; // 학생 성적 정보 시작 인덱스
            	// 과목이 있으면 과목추가
            	if(index > 0) {
            		AlJoSubject tmpsub;
            		// 학생리스트에 과목추가
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
            System.out.println("읽을 파일을 찾지 못했습니다.");
        }
        catch (IOException e) {
        	System.out.println(" 파일읽기 입출력 오류 ");          
        }
		return stu;
	}
	// 학생 정보 저장(id, 이름, 성적들)
	public void saveStudentData(ArrayList<Student> stu) {
		try {
			// id,pw 정보 저장			
			for(int i =0; i<stu.size();i++) {
				BufferedWriter bw;
				
				bw = makeBufferWriter(testfilepath, !(i==0) );
				//bw = makeBufferWriter(filepath, !(i==0) );
				bw.write(stu.get(i).getStuid()+","+stu.get(i).getStupw()+"\n");
				bw.flush();
				bw.close();				
			
				bw = makeBufferWriter(testfilepath2, !(i==0) );
				//bw = makeBufferWriter(filepath2, !(i==0) );
				// 파일 저장하기위해 데이터 문자열로 변환
				String str =stu.get(i).getStuid() + ","+ stu.get(i).getStuname()+","+stu.get(i).getAlJoSubject().size();
				for(int j = 0; j<stu.get(i).getAlJoSubject().size();j++) {
					str += "," + stu.get(i).getAlJoSubject().get(j).getSubject()+","
							+stu.get(i).getAlJoSubject().get(j).getScore();
				}
				str += "\n";
				//파일에 데이터 작성
				bw.write(str);
				bw.flush();
				bw.close();	
			}	
					
		}
		catch (FileNotFoundException e) {			
            System.out.println("작성 파일 못찾음");            
        } 
        catch (IOException e) {
        	 System.out.println("입출력 오류");
        }	
	}
	//버퍼 반환 메소드
	BufferedWriter makeBufferWriter(String filepath, boolean append) {
			try {
				return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath, append), "MS949"));
				
			} catch (UnsupportedEncodingException | FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			return null;			
	}
	
	// 회원가입시 파일에 데이터 저장
	public void joinFileWrite(String name, String pw, String id) {
		try {
			// id,pw 정보 저장
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(testfilepath, true), "MS949"));
			//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath, true), "MS949"));
			bw.write(id+","+pw+"\n");
			bw.flush();
			bw.close();
			// 이름, id 정보 저장
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(testfilepath2, true), "MS949"));
			//bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath2, true), "MS949"));
			bw.write(id+","+name+","+0+"\n");
			bw.flush();
			bw.close();			
		}
		catch (FileNotFoundException e) {
			
            System.out.println("작성 파일 못찾음");
            
        } 
        catch (IOException e) {
        	 System.out.println("입출력 오류");
        }		
	}
	// 관리자 패스워드 변경
	public void adminPwModify(String pass) {
		try {
			//adminfilepath 경로의 파일 (관리자정보) 읽기위해 경로지정
			File csv = new File(adminfilepath);     
			// 관리자 정보 파일을 읽는 버퍼 설정
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
            System.out.println("관리자 ID 패스워드 파일을 못찾았습니다.");
        }
        catch (IOException e) {
        	System.out.println("관리자 ID 패스워드 파일 입출력 오류입니다.");          
        }
	}
	// 관리자 패스워드 가져오기
	public String getAdminPw() {
		String returnValue=null;
		try {
			//adminfilepath 경로의 파일 (관리자정보) 읽기위해 경로지정
			File csv = new File(adminfilepath);     
			// 관리자 정보 파일을 읽는 버퍼 설정
            BufferedReader br = new BufferedReader(new FileReader(csv));
            String line = "";          
            String[] idpass = new String[3];
            line = br.readLine();
            idpass = line.split(",", -1);
            br.close();           
            returnValue= idpass[1];                      
		}
		catch (FileNotFoundException e) {
            System.out.println("관리자 ID 패스워드 파일을 못찾았습니다.");
        }
        catch (IOException e) {
        	System.out.println("관리자 ID 패스워드 파일 입출력 오류입니다.");          
        }
		return returnValue;
	}
	// 관리자 로그인 확인 메소드
	public boolean adminDataCheck(String id , String pw) {
		boolean returnValue = false;	// true : 관리자 ID, PW와 동일, false : 다름
		try {
			//adminfilepath 경로의 파일 (관리자정보) 읽기위해 경로지정
			File csv = new File(adminfilepath);      
			// 관리자 정보 파일을 읽는 버퍼 설정
            BufferedReader br = new BufferedReader(new FileReader(csv));
            String line = "";          
            String[] idpass = new String[3];
            line = br.readLine();	// 파일읽기
            idpass = line.split(",", -1);
            // 관리자 파일의 ID, PW와 일치하는지 확인
            if(idpass[0].equals(id) && idpass[1].equals(pw)) {
            	returnValue = true;
            }            	
            else {
            	returnValue = false;
            }
		}
		catch (FileNotFoundException e) {
            System.out.println("관리자 ID 패스워드 파일을 못찾았습니다.");
        }
        catch (IOException e) {
        	System.out.println("관리자 ID 패스워드 파일 입출력 오류입니다.");          
        }
		
		return returnValue;
	}
	public static void main(String[] args) {
		DataIO i = new DataIO();			
	}
}

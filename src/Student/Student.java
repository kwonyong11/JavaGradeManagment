// 이경현
// 학생 정보 클래스

package Student;
import java.util.ArrayList;

import Login.*;


public class Student {
	String name;
	String pw;
	String id;
	ArrayList<AlJoSubject> sub = new ArrayList<AlJoSubject>();

	public Student(){
		this("기본이름", "기본패스워드", "기본ID");
	}

	public Student(String name, String pw, String id){
		this.name = name;
		this.pw =pw;
		this.id = id;
	}
	public String getStuname()
	{
		return name;
	}
	public void setStuname(String name)
	{
		this.name = name;
	}

	public String getStupw()
	{
		return pw;
	}
	public void setStupw(String pw)
	{
		this.pw = pw;
	}
	public String getStuid()
	{
		return id;
	}
	public void setStuid(String id)
	{
		this.id = id;
	}
	public void setAlJoSubject (AlJoSubject ss)
	{
		this.sub.add(ss);
	}
	public ArrayList<AlJoSubject> getAlJoSubject(){
		return sub;
	}

}
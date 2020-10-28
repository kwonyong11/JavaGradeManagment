// ±èÁ¾Èñ, ÀÌ°æÇö
package Data;
import Student.*;
import Login.*;
import java.util.ArrayList;

public class DataSet {
	   private DataIO dio = new DataIO();
	   ArrayList<Student> stu  = dio.getStudentdata();
	   String []sub = {"java","C","Py","Cpp","MFC","Jsp","JavaScript","Kotlin"};
	   int []score = {3,3,3,2,2,2,1,1};
	   String []pro= {"±è","ÀÌ","±Ç","Á¶","ÇÑ","È«","Á¾","Ãß"};
	   int []person= {20,10,15,25,30,15,15,15};
	   static int index;
	   static int sumCre=0;
	   
	   public DataSet(){	     
	      
	   }
	   public String [] getSub()
		{
			return sub;
		}
		public void setSub(String []sub)
		{
			this.score= score;
		}	
		
		public int [] getScore()
		{
			return score;
		}
		public void setScore(int []score)
		{
			this.score = score;
		}
		
		public String [] getPro()
		{
			return pro;
		}
		public void setPro(String []pro)
		{
			this.pro= pro;
		}
		public int [] getPerson()
		{
			return person;
		}
		public void setPerson(int []person)
		{
			this.person= person;
		}
		public int getcre()
		{
			return sumCre;
		}
		public void setcre(int sumCre)
		{
			this.sumCre = sumCre;
		}
	   public ArrayList<Student> getStudent(){
		   return stu;
	   }
	   public void setStudent(ArrayList<Student> stu) {
		   this.stu = stu;
	   }
	   public int getStuindex() {
		   return index;
	   }
	   public void setStuindex(int index) {
		   this.index = index;
	   }
	   
	}

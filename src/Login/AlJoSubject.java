// �̰���
// ���� ���� Ŭ����
package Login;

public class AlJoSubject  {	
	String subject;
	int score;
	public AlJoSubject() {
		this("����");
	}
	public AlJoSubject(String subject) {
		this(subject, 0);
	}
	public AlJoSubject(String subject, int score) {
		this.subject = subject;	
		this.score = score;
	}
	public String getSubject() {
		return subject;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getScore() {
		return score;
	}

}
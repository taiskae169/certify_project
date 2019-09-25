package certify.cond.method;

public class methodVO {
	private boolean possible;
	private String mess;
	
	// 응시자격 자가진단에 사용되는 참조클래스로,
	// 별도의 유효성검사가 필요하지 않습니다.
	
	public boolean isPossible() {
		return possible;
	}
	public void setPossible(boolean possible) {
		this.possible = possible;
	}
	public String getMess() {
		return mess;
	}
	public void setMess(String mess) {
		this.mess = mess;
	}	
}

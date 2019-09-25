package user.vo;

public class user_info_qual_value {
	private int num;
	private String value;
	
	// 별도의 유효성검사가 필요하지 않은 코드입니다. (by. 조지훈)
	// 활용처 : 회원 개인정보에서 활용
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}

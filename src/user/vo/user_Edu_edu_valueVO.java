package user.vo;

public class user_Edu_edu_valueVO {
	private int num;
	private String value;
	
	// 별도의 유효성검사가 필요하지 않은 코드입니다. (By. 조지훈)
	// DTO활용처 : 회원 학력사항에서 edu 항목의 value 참조
	
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

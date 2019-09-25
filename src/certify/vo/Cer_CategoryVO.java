package certify.vo;

public class Cer_CategoryVO {
	//자격증 카테고리 VO 클래스
	private int num;		//고유번호
	private String name;	//카테고리명
	private int certi_num;	//카테고리 번호
	
	/*
	 * DB에 저장된 국가기술직무표준 코드번호로, 단순 데이터로 구성되어있음
	 * 본 클래스를 다른 클래스에서 참조하여 사용합니다.
	 */
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		if(num>=1&&num<=53) {
			this.num = num;
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCerti_num() {
		return certi_num;
	}
	public void setCerti_num(int certi_num) {
		if(certi_num>=0&&certi_num<=1000) {
			this.certi_num = certi_num;
		}
	}
	
	
}

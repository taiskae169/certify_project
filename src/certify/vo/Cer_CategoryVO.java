package certify.vo;

public class Cer_CategoryVO {
	//자격증 카테고리 VO 클래스
	private int num;		//고유번호
	private String name;	//카테고리명
	private int certi_num;	//카테고리 번호
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
		this.certi_num = certi_num;
	}
	
	
}

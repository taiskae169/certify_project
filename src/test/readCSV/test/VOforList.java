package test.readCSV.test;

public class VOforList {
	private String univ;
	private String univType;
	private String univName;
	private String majorName;
	private String greatType;
	private String middleType;
	private String smallType;
	private String years;
	private String eduType;
	
	// csv파일을 리스트로 불러올때 참조하는 클래스로,
	// 별도의 유효성검사가 필요하지 않습니다.
	
	public String getUniv() {
		return univ;
	}
	public void setUniv(String univ) {
		this.univ = univ;
	}
	public String getUnivType() {
		return univType;
	}
	public void setUnivType(String univType) {
		this.univType = univType;
	}
	public String getUnivName() {
		return univName;
	}
	public void setUnivName(String univName) {
		this.univName = univName;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public String getGreatType() {
		return greatType;
	}
	public void setGreatType(String greatType) {
		this.greatType = greatType;
	}
	public String getMiddleType() {
		return middleType;
	}
	public void setMiddleType(String middleType) {
		this.middleType = middleType;
	}
	public String getSmallType() {
		return smallType;
	}
	public void setSmallType(String smallType) {
		this.smallType = smallType;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getEduType() {
		return eduType;
	}
	public void setEduType(String eduType) {
		this.eduType = eduType;
	}
}

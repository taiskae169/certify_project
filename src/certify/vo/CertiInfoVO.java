package certify.vo;


//자격증 검색 시 자격증 정보를 위한 VO입니다.
public class CertiInfoVO {
	private String cerName;		//자격증명
	private String gen;			//개요
	private String history;		//변천과정
	private String job;			//수행직무
	private String siteLink;	//사이트 주소
	private String site;		//기관명
	private String future;		//진로 및 전망
	private String testInfo;	//출제경향
	private String how;			//취득방법
	
	
	public String getCerName() {
		return cerName;
	}
	public void setCerName(String cerName) {
		this.cerName = cerName;
	}
	public String getGen() {
		return gen;
	}
	public void setGen(String gen) {
		this.gen = gen;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getSiteLink() {
		return siteLink;
	}
	public void setSiteLink(String siteLink) {
		this.siteLink = siteLink;
	}	
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}

	public String getFuture() {
		return future;
	}
	public void setFuture(String future) {
		this.future = future;
	}
	public String getTestInfo() {
		return testInfo;
	}
	public void setTestInfo(String testInfo) {
		this.testInfo = testInfo;
	}
	public String getHow() {
		return how;
	}
	public void setHow(String how) {
		this.how = how;
	}
	
	
	
	
}

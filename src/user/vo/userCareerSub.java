package user.vo;

public class userCareerSub {
	
	// 회원 경력정보 활용을 위해 DAO에서 임시적으로 사용하는 sub클래스입니다.
	
	public int user_car_cate;
	public long user_sub_workdays;
	
	
	public int getUser_car_cate() {
		return user_car_cate;
	}
	public void setUser_car_cate(int user_car_cate) {
		this.user_car_cate = user_car_cate;
	}
	public long getUser_sub_workdays() {
		return user_sub_workdays;
	}
	public void setUser_sub_workdays(long user_sub_workdays) {
		this.user_sub_workdays = user_sub_workdays;
	}
	
	
}

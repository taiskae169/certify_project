package certify.user.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMethod {
	
	@Autowired
	private SqlSessionTemplate sql =null;
	
	public int logincheck(String id, String pw) {
		int result =0;
		Map<String, String> idpw = new HashMap<String, String>();
		idpw.put("id", id);
		idpw.put("pw", pw);
		
		int test = sql.selectOne("user.logincheck", idpw);
		System.out.println(test);
		
		
		return result;
		
	}

}

package certify.user.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import certify.vo.Cer_CategoryVO;
import certify.vo.CertifyVO;

public class CertifyMethod {
	
	@Autowired
	private SqlSessionTemplate sql =null;
	
	// 전체 자격증 리스트를 리턴
	public List<CertifyVO> getAllCertify(){
		List<CertifyVO> certify = sql.selectList("certi.getAllCertify");
		return certify;
	}
	
	// 전체 자격증을 게시판 리스트 형태로 리턴
	public List<CertifyVO> getCertify_Board(HashMap<Object, Object> parameters){
		List<CertifyVO> certify = sql.selectList("certi.getAllCertify", parameters);
		return certify;
	}
	
	// 특정검색어의 자격증을 게시판 리스트 형태로 리턴
	public List<CertifyVO> getCertify_Board_Spec(HashMap<Object, Object> parameters){
		List<CertifyVO> certify = sql.selectList("certi.getCertify_Board_Spec", parameters);
		return certify;
	}
	
	// 카테고리 리스트를 리턴
	public List<Cer_CategoryVO> getCerti_Category(){
		List<Cer_CategoryVO> category = sql.selectList("certi.cer_category");
		return category;
	}
	
	// 특정 자격증 리스트를 리턴
	public CertifyVO getSpecCertify(int num){
		CertifyVO certi = sql.selectOne("certi.getSpecCertify", num);
		return certi;
	}
	
	// 전체 자격증 리스트를 오름차순 정렬하여 리턴
	public List<CertifyVO> getAllCertifyOrder(){
		List<CertifyVO> certify = sql.selectList("certi.getAllCertifyOrder");
		return certify;
	}
	
	// 전체 국가기술 자격증 개수를 리턴
	public int getCount_gukki(){
		int count = sql.selectOne("certi.getCount_gukki");
		return count;
	}
	
	
}

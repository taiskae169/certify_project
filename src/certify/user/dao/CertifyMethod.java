package certify.user.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import certify.vo.Cer_CategoryVO;
import certify.vo.CertiInfoVO;
import certify.vo.CertifyVO;
import test.readCSV.test.VOforCerti;


public class CertifyMethod {
	
	@Autowired
	private SqlSessionTemplate sql =null;
	
	
	// 전체 자격증 리스트를 리턴
	public List<CertifyVO> getAllCertify(){
		List<CertifyVO> certify = sql.selectList("certi.getAllCertify");
		return certify;
	}
	
	// 전체 자격증을 게시판 리스트 형태로 리턴
	public List<CertifyVO> getCertify_Board1(HashMap<String, Object> parameters){
		List<CertifyVO> certify = sql.selectList("certi.getCertify_Board1", parameters);
		return certify;
	}
	
	// 전체 자격증을 게시판 리스트 형태로 리턴
	public List<CertifyVO> getCertify_Board2(HashMap<String, Object> parameters){
		List<CertifyVO> certify = sql.selectList("certi.getCertify_Board2", parameters);
		return certify;
	}

	// 특정검색어의 자격증을 게시판 리스트 형태로 리턴
	public List<CertifyVO> getCertify_Board_Spec1(HashMap<String, Object> parameters){
		List<CertifyVO> certify = sql.selectList("certi.getCertify_Board_Spec1", parameters);
		return certify;
	}
	
	// 특정검색어의 자격증을 게시판 리스트 형태로 리턴
		public List<CertifyVO> getCertify_Board_Spec2(HashMap<String, Object> parameters){
			List<CertifyVO> certify = sql.selectList("certi.getCertify_Board_Spec2", parameters);
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
	
	public void updateCertiCategory0(CertiInfoVO vo) {
//		System.out.println(vo.getCerName());
//		System.out.println(vo.getFuture());
//		System.out.println(vo.getGen());
//		System.out.println(vo.getHistory());
//		System.out.println(vo.getHow());
//		System.out.println(vo.getJob());
//		System.out.println(vo.getPref());
//		System.out.println(vo.getSite());
//		System.out.println(vo.getSiteLink());
//		System.out.println(vo.getTestInfo());
//		System.out.println(sql);
		sql.insert("certi.updateCetiCategory0",vo);
	}//자격증 리스트 업데이트를 위한 메소드
	
	public void deleteCertiCategory(int i) {
		if(i==0) {
			sql.delete("certi.deleteCertiCategory0");
		}
	}//자격증 리스트 삭제를 위한 메소드
	
	// 전체 자격증 개수를 리턴
	public int getCount(){
		int count = sql.selectOne("certi.getCount");
		return count;
	}
	
	// 국가기술 자격증 개수를 리턴
	public int getCount_type1(){
		int count = sql.selectOne("certi.getCount_type1");
		return count;
	}
	
	// 국가기술 자격증 개수를 리턴
	public int getCount_typeSpec1(String certi_name){
		int count = sql.selectOne("certi.getCount_typeSpec1", certi_name);
		return count;
	}
		
	// 전체 국가전문 자격증 개수를 리턴
	public int getCount_type2(){
		int count = sql.selectOne("certi.getCount_type2");
		return count;
	}
	
	// 전체 국가전문 자격증 개수를 리턴
	public int getCount_typeSpec2(String certi_name){
		int count = sql.selectOne("certi.getCount_typeSpec2", certi_name);
		return count;
	}
	
	public List<CertifyVO> getCertiSpecList(String certify) {
		List<CertifyVO> list = sql.selectList("getCertifySearch", certify);
		return list;
	}
	
	
}

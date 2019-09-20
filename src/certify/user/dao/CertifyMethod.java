package certify.user.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import certify.vo.Cer_CategoryVO;
import certify.vo.CertiInfoVO;
import certify.vo.CertifyVO;


public class CertifyMethod {
	
	@Autowired
	private SqlSessionTemplate sql =null;
	
	
	// 전체 자격증 리스트를 리턴
	public List<CertifyVO> getAllCertify(){
		List<CertifyVO> certify = sql.selectList("certi.getAllCertify");
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
		System.out.println(vo.getCerName());
		System.out.println(vo.getFuture());
		System.out.println(vo.getGen());
		System.out.println(vo.getHistory());
		System.out.println(vo.getHow());
		System.out.println(vo.getJob());
		System.out.println(vo.getPref());
		System.out.println(vo.getSite());
		System.out.println(vo.getSiteLink());
		System.out.println(vo.getTestInfo());
		System.out.println(sql);
		sql.insert("certi.updateCetiCategory0",vo);
	}
	
	
}

package certi.Rjava;

import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import certify.vo.CertiInfoVO;


public class CertiInfoR {
	
	public void updateCertiInfo(int num) {
		//num은 자격종류을 분류하기 위한 분휴번호이다.
		//0일경우 기능사, 1일경우 산업기사의 형식
		RConnection conn = null;
		CertiInfoVO certiInfo = new CertiInfoVO();
		try {
			
			conn = new RConnection();
			conn.setStringEncoding("utf8");
			conn.eval("library(KoNLP)");
			
			conn.eval("list<-read.csv('E:/R/certify.csv',stringsAsFactors=F,na.strings='')");
			
			System.out.println(conn.eval("list[1,1]").asString());
			
			System.out.println(conn.eval("list[,1]").length());
			int lenth = conn.eval("list[,1]").length();//해당 리스트에 몇개나 있는지 체크
			
			
			conn.eval("");

			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			conn.close();
		}
		
	}

}

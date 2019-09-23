package certi.Rjava;

import java.util.ArrayList;
import java.util.List;

import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.stereotype.Service;

import certify.user.dao.CertifyMethod;
import certify.vo.CertiInfoVO;

@Service
public class CertiInfoR {
	
	
	
	public List<CertiInfoVO> updateCertiInfo(int num) {
		//num은 자격종류을 분류하기 위한 분휴번호이다.
		//0일경우 기능사, 1일경우 산업기사의 형식
		RConnection conn = null;
		CertiInfoVO certiInfo = null;
		List<CertiInfoVO> result = new ArrayList<CertiInfoVO>();
		//CertifyMethod certi = new CertifyMethod();
		try {
			
			conn = new RConnection();
			conn.setStringEncoding("utf8");
			conn.eval("library(KoNLP)");
			
			conn.eval("list<-read.csv('E:/R/certify.csv',stringsAsFactors=F,na.strings='')");
			
			System.out.println(conn.eval("list[1,1]").asString());
			
			System.out.println(conn.eval("list[,1]").length());
			//int lenth = conn.eval("list[,1]").length();//해당 리스트에 몇개나 있는지 체크
			
			conn.eval("lenth<-length(list[,1])");
			
			conn.eval("cername<-list[1,1]");
			conn.eval("gen<-''");
			conn.eval("history<-''");
			conn.eval("job<-''");
			conn.eval("sitelink<-''");
			conn.eval("site<-''");
			conn.eval("pref<-''");
			conn.eval("future<-''");
			conn.eval("testinfo<-''");
			conn.eval("how<-''");
			//기본 초기화 작업
			
			conn.eval("if(list[1,2]=='개요'){"
					+ "	site<-'';"
					+ "}");
			
			conn.eval("for(i in 1:lenth){"
					+ "	tmp<-list[i,];"
					+ "	tmpname<-tmp[,1];"
					+ "	if(cername==tmpname){"
					+ "		if(tmp[,2]=='개요'){"
					+ "			gen<-tmp[,3];"
					+ "		};"
					+ "		if(tmp[,2]=='변천과정'){"
					+ "			history<-tmp[,3];"
					+ "		};"
					+ "		if(tmp[,2]=='수행직무'){"
					+ "			job<-tmp[,3];"
					+ "		};"
					+ "		if(tmp[,2]=='실시기관 홈페이지'){"
					+ "			sitelink<-tmp[,3];"
					+ "		};"
					+ "		if(tmp[,2]=='실시기관명'){"
					+ "			site<-tmp[,3];"
					+ "		};"
					+ "		if(tmp[,2]=='위 자격취득자에 대한 법령상 우대현황'){"
					+ "			pref<-tmp[,3];"
					+ "		};"
					+ "		if(tmp[,2]=='진로 및 전망'){"
					+ "			future<-tmp[,3];"
					+ "		};"
					+ "		if(tmp[,2]=='출제경향'){"
					+ "			testinfo<-tmp[,3];"
					+ "		};"
					+ "		if(tmp[,2]=='취득방법'){"
					+ "			how <- tmp[,3];"
					+ "		};"
					+ "		if(i==lenth){"
					+ "			certiinfo<-rbind(certiinfo,data.frame(cername,gen,history,job,sitelink,site,pref,future,testinfo,how,stringsAsFactors=FALSE))"
					+ "		};"
					+ "	}else{"
					+ "		if(cername==list[1,1]){"
					+ "			certiinfo<-data.frame(cername,gen,history,job,sitelink,site,pref,future,testinfo,how,stringsAsFactors=FALSE);"
					+ "		}else{"
					+ "			certiinfo<-rbind(certiinfo,data.frame(cername,gen,history,job,sitelink,site,pref,future,testinfo,how,stringsAsFactors=FALSE));"
					+ "		};"
					+ "		cername<-tmpname;"
					+ "		history<-'';"
					+ "		job<-'';"
					+ "		sitelink<-'';"
					+ "		site<-'';"
					+ "		pref<-'';"
					+ "		future<-'';"
					+ "		testinfo<-'';"
					+ "		how<-'';"
					+ "	}"
					+ "}");
			int count = conn.eval("length(certiinfo[,1])").asInteger();
//			int testnum1 =1;
//			int testnum2 =2;
//			String testname = conn.eval("certiinfo["+testnum1+","+testnum2+"]").asString();
//			System.out.println("count : " + count);
//			System.out.println("testname : " + testname);
//			System.out.println("testtesttest1 : "+conn.eval("certiinfo[2,1]").asString());
//			System.out.println("testtesttest2 : "+conn.eval("certiinfo[2,2]").asString());
//			System.out.println("testtesttest3 : "+conn.eval("certiinfo[2,3]").asString());
			//System.out.println("connnnn : "+conn.eval("length(certiinfo[1,]").asString());
			for(int y=1;y<=count;y++) {
				for(int x=1;x<=10;x++) {
					String tmp = conn.eval("certiinfo["+y+","+x+"]").asString();
					 certiInfo = new CertiInfoVO();
					//System.out.println("tmp"+x+" : " +tmp);
					switch(x) {
						case 1: certiInfo.setCerName(tmp);
								//System.out.println(x+ " : "+certiInfo.getCerName());
								break;
						case 2: certiInfo.setGen(tmp);
//								System.out.println(x+ " : "+certiInfo.getGen());
								break;
						case 3: certiInfo.setHistory(tmp);
//								System.out.println(x+ " : "+certiInfo.getHistory());
								break;
						case 4: certiInfo.setJob(tmp);
//								System.out.println(x+ " : "+certiInfo.getJob());
								break;
						case 5: certiInfo.setSiteLink(tmp);
//								System.out.println(x+ " : "+certiInfo.getSiteLink());
								break;
						case 6: certiInfo.setSite(tmp);
//								System.out.println(x+ " : "+certiInfo.getSite());
								break;
						case 7: //certiInfo.setPref(tmp);
//								System.out.println(x+ " : "+certiInfo.getPref());
								break;
						case 8: certiInfo.setFuture(tmp);
//								System.out.println(x+ " : "+certiInfo.getFuture());
								break;
						case 9: certiInfo.setTestInfo(tmp);
//								System.out.println(x+ " : "+certiInfo.getTestInfo());
								break;
						case 10: certiInfo.setHow(tmp);
//								System.out.println(x+ " : "+certiInfo.getHow());
								//certi.updateCertiCategory0(certiInfo);
								result.add(certiInfo);
								break;
					}
					
				}
			}

			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			conn.close();
		}
		System.out.println(result.get(1).getCerName());
		return result;
	}

}

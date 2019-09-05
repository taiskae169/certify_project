package test.readCSV.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.opencsv.CSVReader;

public class ReadCSVFile_saveToHashMap {
	
	static HashMap<String,Set> univercity = new HashMap();
	
	public HashMap reader(String filePath) throws IOException {
		 
        @SuppressWarnings("resource")
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath), "EUC-KR")); // CSV파일 한글로 읽어들이기
        String[] nextLine;
        int i = 0;
        long start = System.currentTimeMillis();
        String save = null;
        Set<String> s = null;
        while ((nextLine = reader.readNext()) != null) {
            if(save==null) {
            	s = new HashSet<String>();
            	save=nextLine[5].trim();
            	s.add(nextLine[8].trim());
            	univercity.put(save, s);
            }else if(save!=null && save.equals(nextLine[5].trim())) {
            	s.add(nextLine[8].trim());
            	univercity.put(save, s);
            }else if(save!=null && !save.equals(nextLine[5].trim())) {
            	save = nextLine[5].trim();
            	s = new HashSet<String>();
            	s.add(nextLine[8].trim());
            	univercity.put(save, s);
            }  
            i++;
        }   
        
        long end = System.currentTimeMillis();
        long belong = end - start;
        System.out.println("전체학과수 : "+i);
        System.out.println("걸린 시간: " + belong + " 밀리초");
        return univercity;
    }

}	

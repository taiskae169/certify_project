package test.readCSV.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import com.opencsv.CSVReader;

public class CSVtoMap {
	public HashMap<String, Set<String>> reader(String filePath) throws IOException {
		HashMap<String, Set<String>> univercity = new HashMap<String, Set<String>>();	 
        @SuppressWarnings("resource")
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath), "EUC-KR")); // CSV파일 한글로 읽어들이기
        String[] nextLine;
        int i = 0;
        long start = System.currentTimeMillis();
        String save = null;
        Set<String> s = null;
        while ((nextLine = reader.readNext()) != null) {
        	System.out.print("학교 : " + nextLine[2].trim()+" / ");
            System.out.print("과명 : " + nextLine[3].trim()+" / ");	// 가로행
            System.out.println();
            if(save==null) {
            	s = new HashSet<String>();
            	save=nextLine[2].trim();
            	s.add(nextLine[3].trim());
            	univercity.put(save, s);
            }else if(save!=null && save.equals(nextLine[2].trim())) {
            	s.add(nextLine[3].trim());
            	univercity.put(save, s);
            }else if(save!=null && !save.equals(nextLine[2].trim())) {
            	save = nextLine[2].trim();
            	s = new HashSet<String>();
            	s.add(nextLine[3].trim());
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

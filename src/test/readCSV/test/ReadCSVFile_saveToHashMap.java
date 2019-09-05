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
	
	private void reader(String filePath) throws IOException {
		 
        @SuppressWarnings("resource")
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath), "EUC-KR")); // CSV파일 한글로 읽어들이기
        String[] nextLine;
        int i = 0;
        long start = System.currentTimeMillis();
        String save = null;
        Set<String> s = null;
        while ((nextLine = reader.readNext()) != null) {
        	System.out.print("학교 : " + nextLine[5].trim()+" / ");
            System.out.print("과명 : " + nextLine[8].trim()+" / ");	// 가로행
            System.out.print("대계열 : " + nextLine[12]+" / ");
            System.out.print("중계열 : " + nextLine[13]+" / ");
            System.out.print("학년제 : " + nextLine[15]);
            System.out.println();
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
       
        System.out.println(univercity.keySet().size());
        System.out.println(univercity.values());
    }
	
	public static void main(String[] args) {
		ReadCSVFile_saveToHashMap csvRead = new ReadCSVFile_saveToHashMap();
		 
        try {
            csvRead.reader("C:/Users/DELL/Documents/major.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
     
        
    }
}	

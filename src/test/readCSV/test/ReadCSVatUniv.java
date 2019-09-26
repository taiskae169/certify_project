package test.readCSV.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.opencsv.CSVReader;

public class ReadCSVatUniv {
	
	public HashMap<String, Set<String>> csvToMap(String filePath) throws IOException {
		HashMap<String, Set<String>> univercity = new HashMap<String, Set<String>>();	 
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
       
        return univercity;
    }
	
	public List<VOforList> csvToList(String filePath) throws IOException {
		List<VOforList> univercity = new ArrayList<VOforList>();	 
        @SuppressWarnings("resource")
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath), "EUC-KR")); // CSV파일 한글로 읽어들이기
        String[] nextLine;
        int i = 0;
        long start = System.currentTimeMillis();
        String save = null;
        Set<String> s = null;
        while ((nextLine = reader.readNext()) != null) {
        	VOforList voli = new VOforList();
        	voli.setUniv(nextLine[0]);
        	voli.setUnivType(nextLine[1]);
            voli.setUnivName(nextLine[2]);
            voli.setMajorName(nextLine[3]);
            voli.setGreatType(nextLine[4]);
            voli.setMiddleType(nextLine[5]);
            voli.setSmallType(nextLine[6]);
            voli.setYears(nextLine[7]);
            voli.setEduType(nextLine[8]);
            univercity.add(voli);
        }   
        return univercity;
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap<String, Map<String, String>> certiCSVtoMap(String filePath) throws IOException {
		HashMap<String, Map<String, String>> univercity = new HashMap<String, Map<String, String>>();	 
        @SuppressWarnings("resource")
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath), "EUC-KR")); // CSV파일 한글로 읽어들이기
        String[] nextLine;
        String save = null;
        Map mm = null;
        while ((nextLine = reader.readNext()) != null) {
            if(save==null) {
            	mm = new HashMap<String, String>();
            	save=nextLine[0].trim();
            	mm.put(nextLine[1].trim(), nextLine[2].trim());
            	univercity.put(save, mm);
            }else if(save!=null && save.equals(nextLine[0].trim())) {
            	mm.put(nextLine[1].trim(), nextLine[2].trim());
            	univercity.put(save, mm);
            }else if(save!=null && !save.equals(nextLine[0].trim())) {
            	save = nextLine[0].trim();
            	mm = new HashMap<String, String>();
            	mm.put(nextLine[1].trim(), nextLine[2].trim());
            	univercity.put(save, mm);
            }  
        }
       
        return univercity;
    }
	
	
}

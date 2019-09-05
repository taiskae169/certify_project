package test.readCSV.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opencsv.CSVReader;

public class ReadCSVFile_saveToHashSet {
	
	public List reader(String filePath) throws IOException {
		 
        @SuppressWarnings("resource")
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath), "EUC-KR")); // CSV파일 한글로 읽어들이기
        String[] nextLine;
        int i = 0;
        long start = System.currentTimeMillis();
        String save = null;
        Set<String> uni_name = new HashSet<String>();
        Set<String> major_name = new HashSet<String>();
        while ((nextLine = reader.readNext()) != null) {
            uni_name.add(nextLine[2].trim());
            major_name.add(nextLine[3]);
            i++;
        }   
        
        long end = System.currentTimeMillis();
        long belong = end - start;
        System.out.println("전체학과수 : "+i);
        System.out.println("걸린 시간: " + belong + " 밀리초");

        System.out.println("uni_name=="+uni_name.size());
        System.out.println("major_name=="+major_name.size());
        
        List setList = new ArrayList();
        setList.add(uni_name);
        setList.add(major_name);
        
        return setList;
    }
}	

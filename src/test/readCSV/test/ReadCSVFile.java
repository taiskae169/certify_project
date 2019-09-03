package test.readCSV.test;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.opencsv.CSVReader;

public class ReadCSVFile {
	
	public static List<School> univercity = new ArrayList();
	
	private void reader(String filePath) throws IOException {
		 
        @SuppressWarnings("resource") //안쓰면 reader 안닫았다고 밑에 밑줄쳐진다.
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath), "EUC-KR")); // CSV파일 한글로 읽어들이기
        
        String[] nextLine;
        int i = 0;
        while ((nextLine = reader.readNext()) != null) {
        	School sc = new School();
            // nextLine[] is an array of values from the line
        	// nextLine[]은 csv파일에서 한줄을 의미 
            System.out.print("번호 : " + nextLine[0]+" / ");	// 가로행
            System.out.print("학교 : " + nextLine[1]+" / ");
            System.out.print("학종1 : " + nextLine[2]+" / ");
            System.out.print("학종2 : " + nextLine[3]+" / ");
            System.out.print("학년제 : " + nextLine[4]);
            System.out.println();
            sc.setName(nextLine[1]);
            sc.setYears(nextLine[4]);
            univercity.add(sc);
            i++;
        }    
    }
	
	public static void main(String[] args) {
		ReadCSVFile csvRead = new ReadCSVFile();
		 
        try {
            csvRead.reader("C:/Users/DELL/Documents/KoreaUnivercityList.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
     
        
    }
}	

class School {
	private String name;
	private String years;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
}



package test.readCSV.test;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.opencsv.CSVReader;

public class ReadCSVFile {
	
	private static ReadCSVFile instance = new ReadCSVFile();
	public static ReadCSVFile getInstance() {
		return instance;
	}
	private ReadCSVFile() {}
	
	private Connection conn=null;
	private PreparedStatement pstmt= null;
	private ResultSet rs = null;
	
	private Connection getConnection() {
		try {
			Context ctx = new InitialContext();
			Context env = (Context)ctx.lookup("java:comp/env");
			DataSource ds = (DataSource)env.lookup("jdbc/xe");
			conn = ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	private void reader(String filePath) throws IOException, SQLException {
       
		@SuppressWarnings("resource") //안쓰면 reader 안닫았다고 밑에 밑줄쳐진다.
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath), "EUC-KR")); // CSV파일 한글로 읽어들이기
        
		String sql = "";
		conn=getConnection();
		sql="insert into CERTIFY(num,cre_name) values(?,?)";
		pstmt=conn.prepareStatement(sql);
		 pstmt.setInt(1,1);
         pstmt.setString(2, "bong");
         pstmt.executeUpdate();
		
        String[] nextLine;
        int i = 0;
        while ((nextLine = reader.readNext()) != null) {
        	School sc = new School();
            // nextLine[] is an array of values from the line
        	// nextLine[]은 csv파일에서 한줄을 의미 
            /*sql="insert into CERTIFY(num,cre_name) values(?,?)";
			pstmt=conn.prepareStatement(sql);
			System.out.println(nextLine[0]);
			sc.setNum(Integer.parseInt(nextLine[0]));
            pstmt.setInt(1,sc.getNum());
            pstmt.setString(2, nextLine[1]);
            pstmt.executeUpdate();*/
 
        }    
    }
	
	public static void main(String[] args) throws SQLException {
		ReadCSVFile csvRead = new ReadCSVFile();
		 
        try {
            csvRead.reader("/Users/mac/certification.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
     
        
    }
}	

class School {
	private int num;
	private String name;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}



package gendata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import database.DatabaseManager;

public class GenPerson {
	private final int DATA_NAME = 6000;
	private final int DATA_DESC = 40;
	private final int DATA_JOB = 80;
	
	String[] personName = new String[DATA_NAME+5];
	String[] personDesc = new String[DATA_DESC+5];
	String[] personJob = new String[DATA_JOB+5];
	
	int nameAmount = 0;
	int descAmount = 0;
	int jobAmount = 0;
	public void genPerson(int amount) {
		
        // Lấy dữ liệu tên người
		BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("/media/ductrong/DATA/Study/Kì 5 - BK/OOP/Workspace/BTL-OOP/src/main/java/rawdata/personName.txt"));  
            String textInALine;
            // Đọc dữ liệu
            while ((textInALine = br.readLine()) != null) {
            	personName[++nameAmount] = textInALine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		
        // Lấy dữ liệu mô tả người
        try {
            br = new BufferedReader(new FileReader("/media/ductrong/DATA/Study/Kì 5 - BK/OOP/Workspace/BTL-OOP/src/main/java/rawdata/personDescription.txt"));       
            String textInALine;
            // Đọc dữ liệu
            while ((textInALine = br.readLine()) != null) {
            	personDesc[++descAmount] = textInALine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        // Lấy dữ liệu công việc
        try {
            br = new BufferedReader(new FileReader("/media/ductrong/DATA/Study/Kì 5 - BK/OOP/Workspace/BTL-OOP/src/main/java/rawdata/job.txt"));       
            String textInALine;
            // Đọc dữ liệu
            while ((textInALine = br.readLine()) != null) {
            	personJob[++jobAmount] = textInALine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        // Đóng br
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Ghi dữ liệu ra file 
        Random rd = new Random();
        
        BufferedWriter bw = null;
        try {
        	bw = new BufferedWriter(new FileWriter("/media/ductrong/DATA/Study/Kì 5 - BK/OOP/Workspace/BTL-OOP/src/main/java/generateddata/person.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        // Giả lập dữ liệu
		for(int i=1;i<=amount;i++) {
			String name = personName[rd.nextInt(DATA_NAME)];
			String desc = personDesc[rd.nextInt(DATA_DESC)];
			String job = personJob[rd.nextInt(DATA_JOB)];
			int temp = (amount/DATA_NAME)+100;
			int ranID = rd.nextInt(temp);
			try {
				String tempString = name + " " + ranID + "\n" + desc + "\n" + job + "\n";
				bw.write(tempString);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public GenPerson() {
	}
}

package gendata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import database.DatabaseManager;

public class GenCountry {
	private final int DATA_NAME = 200;
	private final int DATA_DESC = 65;
	
	String[] countryName = new String[DATA_NAME+5];
	String[] countryDesc = new String[DATA_DESC+5];
	
	int nameAmount = 0;
	int descAmount = 0;
	public void genCountry(int amount) {
		
        // Lấy dữ liệu tên quốc gia
		BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("/media/ductrong/DATA/Study/Kì 5 - BK/OOP/Workspace/BTL-OOP/src/main/java/rawdata/countryName.txt"));  
            String textInALine;
            // Đọc dữ liệu
            while ((textInALine = br.readLine()) != null) {
            	countryName[++nameAmount] = textInALine;
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
		
        // Lấy dữ liệu mô tả quốc gia
        try {
            br = new BufferedReader(new FileReader("/media/ductrong/DATA/Study/Kì 5 - BK/OOP/Workspace/BTL-OOP/src/main/java/rawdata/countryDescription.txt"));       
            String textInALine;
            // Đọc dữ liệu
            while ((textInALine = br.readLine()) != null) {
            	countryDesc[++descAmount] = textInALine;
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
        	bw = new BufferedWriter(new FileWriter("/media/ductrong/DATA/Study/Kì 5 - BK/OOP/Workspace/BTL-OOP/src/main/java/generateddata/country.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        // Giả lập dữ liệu
		for(int i=1;i<=amount;i++) {
			String name = countryName[rd.nextInt(DATA_NAME)];
			String desc = countryDesc[rd.nextInt(DATA_DESC)];
			int temp = (amount/DATA_NAME)+100;
			int ranID = rd.nextInt(temp);
			try {
				String tempString = name + " " + ranID + "\n" + desc + "\n";
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
	public GenCountry() {
	}
}

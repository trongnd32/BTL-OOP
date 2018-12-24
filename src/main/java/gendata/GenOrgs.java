package gendata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import database.DatabaseManager;

public class GenOrgs {
	private final int DATA_NAME = 2350;
	private final int DATA_DESC = 200;
	private final int DATA_HEADQUATER = 200;
	
	String[] orgName = new String[DATA_NAME+5];
	String[] orgDesc = new String[DATA_DESC+5];
	String[] orgHeadquater = new String[DATA_HEADQUATER+5];
	
	int nameAmount = 0;
	int descAmount = 0;
	int headquaterAmount = 0;
	public void genOrg(int amount) {
		
        // Lấy dữ liệu tên tổ chức
		BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("/media/ductrong/DATA/Study/Kì 5 - BK/OOP/Workspace/BTL-OOP/src/main/java/rawdata/orgsName.txt"));  
            String textInALine;
            // Đọc dữ liệu
            while ((textInALine = br.readLine()) != null) {
            	orgName[++nameAmount] = textInALine;
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
		
        // Lấy dữ liệu mô tả tổ chức 
        try {
            br = new BufferedReader(new FileReader("/media/ductrong/DATA/Study/Kì 5 - BK/OOP/Workspace/BTL-OOP/src/main/java/rawdata/orgsDescription.txt"));       
            String textInALine;
            // Đọc dữ liệu
            while ((textInALine = br.readLine()) != null) {
            	orgDesc[++descAmount] = textInALine;
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
        
        // Lấy dữ liệu trụ sở
        try {
            br = new BufferedReader(new FileReader("/media/ductrong/DATA/Study/Kì 5 - BK/OOP/Workspace/BTL-OOP/src/main/java/rawdata/headquater.txt"));       
            String textInALine;
            // Đọc dữ liệu
            while ((textInALine = br.readLine()) != null) {
            	orgHeadquater[++headquaterAmount] = textInALine;
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
        
        System.out.println("Completed get orgs data");
        
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
        	bw = new BufferedWriter(new FileWriter("/media/ductrong/DATA/Study/Kì 5 - BK/OOP/Workspace/BTL-OOP/src/main/java/generateddata/orgs.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        // Giả lập dữ liệu
		for(int i=1;i<=amount;i++) {
			String name = orgName[rd.nextInt(DATA_NAME)];
			String desc = orgDesc[rd.nextInt(DATA_DESC)];
			String headquater = orgHeadquater[rd.nextInt(DATA_HEADQUATER)];
			int temp = (amount/DATA_NAME)+100;
			int ranID = rd.nextInt(temp);
			try {
				String tempString = name + " " + ranID + "\n" + desc + "\n" + headquater + "\n";
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
		System.out.println("Finished generate orgs");
		
	}
	public GenOrgs() {
	}
}

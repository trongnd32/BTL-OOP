package gendata;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenTime {
	public void genTime(int amount) {
		
		BufferedWriter bw = null;
        try {
        	bw = new BufferedWriter(new FileWriter("/media/ductrong/DATA/Study/Kì 5 - BK/OOP/Workspace/BTL-OOP/src/main/java/generateddata/time.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        } 
		Random rd = new Random();
		for(int i = 1; i <= amount; i++) {
			
			// Lấy ngẫu nhiên ngày tháng năm
	        int y = rd.nextInt() % 2018 + 1;
	        int m = rd.nextInt() % 12 + 1;
	        int d = 1;
	        switch( m ){
	            case 1:
	            case 3:
	            case 5:
	            case 7:
	            case 8:
	            case 10:
	            case 12: {
	                d = rd.nextInt() % 31 + 1;
	                break;
	            }
	            case 4:
	            case 6:
	            case 9:
	            case 11: {
	                d = rd.nextInt() % 30 + 1;
	                break;
	            }
	            default: d = rd.nextInt() % 28 + 1;
	        }
	        
	        // Ghép ngày tháng năm thành xâu
	        StringBuffer temp = new StringBuffer("");
	        int d1 = d / 10;
	        int d2 = d % 10;
	        temp.append(d1 + d2 + "/");
	        int m1 = m / 10;
	        int m2 = m % 10;
	        temp.append(m1 + m2 + "/");
	        temp.append(y + "\n ");
	        
	        // Ghi vào file
	        try {
				bw.write(temp.toString());
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
}

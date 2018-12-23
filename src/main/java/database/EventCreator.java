package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.neo4j.graphdb.Result;

public class EventCreator {
	
	/**
	* @param filepath:
	* 	Đường dẫn tới file
	*/  
	public void addEvent(String filepath) {
		//s là chuỗi thêm 10 000 location vào database 
		StringBuffer s = new StringBuffer("CREATE ");
		
		// biến count đếm số lượng person trong chuỗi s. Reset khi biến count đạt 10 000
        int count = 0;
        
		// đọc dữ liệu từ file input
		BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filepath));       
            String textInALine;
            // Đọc dữ liệu
            while ((textInALine = br.readLine()) != null) {
            	DatabaseManager.totalEntity++;
            	s.append("(:EVENT {");
            	// Thêm thuộc tính id
            	s.append("id : \"");
                s.append(DatabaseManager.totalEntity);
                s.append("\", ");
                
            	// Thêm thuộc tính name
            	s.append("name : \"");
                s.append(textInALine);
                s.append("\", ");
                
                // Thêm thuộc tính description
                textInALine = br.readLine();
                s.append("description : \"");
                s.append(textInALine);
                s.append("\"}), ");
                
                count++;
                DatabaseManager.eventAmount++;
                // Biến count = 10 000, thêm dữ liệu vào neo4j
                if(count == 10000) {
                	int len = s.length();
                	s.delete(len-2, len);
                	DatabaseManager.graphDb.execute(s.toString());
                	count = 0;
                	s.delete(0, len);
                	s.append("CREATE ");
                }
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
        
        // Thêm dữ liệu vào neo4j nếu chưa thêm
        if(count!=0) {
        	int len = s.length();
    		s.delete(len-2, len);
    		DatabaseManager.graphDb.execute(s.toString());
    		s.delete(0, len);
        }
//        System.out.println(s);
	}
	
	// Constructor 
	public EventCreator() {
	}
	
}

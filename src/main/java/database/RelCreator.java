package database;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class RelCreator {
	
	// Bảng quan hệ
	String[][][] relTable = new String[][][] {
		{},
		{{}, {"meet","love","hate"}, {"live","visit"}, {"live","visit"},{"join", "founded"}, {"held", "join"}, {"was_born", "died"} },
		{{}, {"protect"}, {"deal_with","war_with"}, {"has"},{"has"}, {"held"}, {"established", "fallen"} },
		{{}, {"protect"}, {"belong_to"}, {"overtake"},{"deal"}, {"held"}, {"established_on"} },
		{{}, {"established_by"}, {"established_in"}, {"established_in"}, {"deal_with", "compete_with" }, {"held", "join"}, {"established_on"} },
		{{}, {"was_held_by"}, {"was_held_in"}, {"was_held_in"},{"was_held_by"}, {"unite"}, {"was_held_on"} },
		{{}, {}, {}, {},{}, {}, {} },
	};
	
	int per = DatabaseManager.personAmount;
	int country = per + DatabaseManager.countryAmount;
	int loca = country + DatabaseManager.locaAmount;
	int orgs = loca + DatabaseManager.orgsAmount;
	int event = orgs + DatabaseManager.eventAmount;
	int time = event + DatabaseManager.timeAmount;
	
	// trả về loại của thực thể tương ứng với id
	public String labelOf(int id) {
		if (id <= per) {
			return "PERSON";
		} else if (id <= country) {
			return "COUNTRY";
		} else if (id <= loca) {
			return "LOCATION";
		} else if (id <= orgs) {
			return "ORGANIZATION";
		} else if (id <= event) {
			return "EVENT";
		} else if (id <= time) {
			return "TIME";
		}
		return null;
	}
	
	// Trả về type của thực thể
	public int typeOf(String label) {
		if(label == "PERSON") return 1;
		if(label == "COUNTRY") return 2;
		if(label == "LOCATION") return 3;
		if(label == "ORGANIZATION") return 4;
		if(label == "EVENT") return 5;
		if(label == "TIME") return 6;
		return 0;
	}
	
	// Thêm quan hệ
	public void addRel(int amount) {
		
		// s1: MATCH entities
		// s2: CREAT relationship
		StringBuffer s1 = new StringBuffer("");
		StringBuffer s2 = new StringBuffer("CREATE ");
		Random rd = new Random();
		int count = 0;
		int[] en = new int[21005];
		for(int i=1;i<=amount;i++) {
			// Lấy ngẫu nhiên 2 thực thể khác nhau
			int id1 = rd.nextInt(event) + 1;
			int id2 = rd.nextInt(DatabaseManager.totalEntity) + 1;
			while(true) {
				if(labelOf(id1) == "TIME") continue;
				if(id1 != id2 && labelOf(id1) != null && labelOf(id2) != null) break;
				id1 = rd.nextInt(DatabaseManager.totalEntity) + 1;
				id2 = rd.nextInt(DatabaseManager.totalEntity) + 1;
			}
			
			count++;
			System.out.println("relationship " + count);
			// Lấy nhãn và loại của thực thể
			String labelOfId1 = labelOf(id1);
			String labelOfId2 = labelOf(id2);
			int type1 = typeOf(labelOfId1);
			int type2 = typeOf(labelOfId2);
			
			// Lấy ngẫu nhiên relationship
			int rel = rd.nextInt(relTable[type1][type2].length);
			String relation = relTable[type1][type2][rel];
			
			en[count*2] = id1;
			en[count*2-1] = id2;	
			// Thêm quan hệ vào truy vấn
			s2.append("(a" + id1 + ") -[:" +  relation + "]-> (a" + id2 + "), " );
			
			// Khi biến count = 10 000, thực hiện thêm quan hệ vào database, xóa chuỗi s1, s2, mảng en
			if(count == 5000) {
				// Sắp xếp mảng thực thể để tránh MATCH trùng lặp
				Arrays.sort(en,1,count*2);
				s1.append("MATCH (a" + en[1] + ":" + labelOf(en[1]) + "{ id :'" + en[1] + "'}) ");
				for(int j=2;j<=count*2;j++) {
					if(en[j] != en[j-1]) {
						s1.append("MATCH (a" + en[j] + ":" + labelOf(en[j]) + "{ id :'" + en[j] + "'}) ");
					}
				}
				Arrays.fill(en, 0);
				
				// Ghép s1 và s2, xóa 2 xâu
				int len1 = s1.length();
				int len2 = s2.length();
				s2.delete(len2 - 2, len2);
				s1.append(s2);
	    		DatabaseManager.graphDb.execute(s1.toString());
	    		s1.delete(0, len1 + len2 - 2);
	    		s2.delete(0, len2 - 2);
	    		s2.append("CREATE ");
	    		count = 0;
			}
		}
		// Nếu count chưa đạt đến 10 000 mà đã ngắt vòng lặp
		if(count != 0) {
			System.out.println("1");
			Arrays.sort(en,1,count*2);
			System.out.println("2");
			s1.append("MATCH (a" + en[1] + ":" + labelOf(en[1]) + " { id: '" + en[1] + "'}) ");
			System.out.println("3");
			for(int j=2;j<=count*2;j++) {
				if(en[j] != en[j-1]) {
					s1.append("MATCH (a" + en[j] + ":" + labelOf(en[j]) + " { id: '" + en[j] + "'}) ");
				}
			}
			System.out.println("4");
			Arrays.fill(en, 0);
			System.out.println("5");
			int len1 = s1.length();
			int len2 = s2.length();
			s2.delete(len2 - 2, len2);
			s1.append(s2);
//			System.out.println(s1);
			System.out.println("6");
    		DatabaseManager.graphDb.execute(s1.toString());
    		System.err.println("added");
    		s1.delete(0, len1 + len2 - 2);
    		s2.delete(0, len2 - 2);
    		s2.append("CREATE ");
		}
		
		System.out.println("Success add relationship to database");
	}
	
	// Constructor
	public RelCreator() {
	}
}

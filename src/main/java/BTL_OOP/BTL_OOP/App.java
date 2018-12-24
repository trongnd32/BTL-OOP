package BTL_OOP.BTL_OOP;

import org.neo4j.commandline.arguments.common.Database;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.impl.storageengine.impl.recordstorage.RelationshipCreator;

import database.CountryCreator;
import database.DatabaseManager;
import database.EventCreator;
import database.LocaCreator;
import database.OrgsCreator;
import database.PersonCreator;
import database.RelCreator;
import database.TimeCreator;
import gendata.GenCountry;
import gendata.GenEvent;
import gendata.GenLoca;
import gendata.GenOrgs;
import gendata.GenPerson;
import gendata.GenTime;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
    	
    	// Tạo dữ liệu
    	GenPerson genPerson = new GenPerson();
    	genPerson.genPerson(12000);
    	
    	GenCountry genCountry = new GenCountry();
    	genCountry.genCountry(6000);
    	
    	GenLoca genLoca = new GenLoca();
    	genLoca.genLoca(6000);
    	
    	GenOrgs genOrgs = new GenOrgs();
    	genOrgs.genOrg(12000);
    	
    	GenEvent genEvent = new GenEvent();
    	genEvent.genEvent(12000);
    	
    	GenTime genTime = new GenTime();
    	genTime.genTime(12000);
    
    	System.out.println("Finished generate data");
    	
    	// Đưa dữ liệu vào database
    	try (Transaction tx = DatabaseManager.graphDb.beginTx()) {
    		PersonCreator per = new PersonCreator();
        	per.addPerson("/media/ductrong/DATA/Study/Kì 5 - BK/OOP/Workspace/BTL-OOP/src/main/java/generateddata/person.txt");
        	
        	CountryCreator country = new CountryCreator();
        	country.addCountry("/media/ductrong/DATA/Study/Kì 5 - BK/OOP/Workspace/BTL-OOP/src/main/java/generateddata/country.txt");
        	
        	LocaCreator loca = new LocaCreator();
        	loca.addLoca("/media/ductrong/DATA/Study/Kì 5 - BK/OOP/Workspace/BTL-OOP/src/main/java/generateddata/location.txt");
        	
        	OrgsCreator orgs = new OrgsCreator();
        	orgs.addOrgs("/media/ductrong/DATA/Study/Kì 5 - BK/OOP/Workspace/BTL-OOP/src/main/java/generateddata/orgs.txt");
        	
        	EventCreator event = new EventCreator();
        	event.addEvent("/media/ductrong/DATA/Study/Kì 5 - BK/OOP/Workspace/BTL-OOP/src/main/java/generateddata/event.txt");
        	
        	TimeCreator time = new TimeCreator();
        	time.addTime("/media/ductrong/DATA/Study/Kì 5 - BK/OOP/Workspace/BTL-OOP/src/main/java/generateddata/time.txt");
        	
        	RelCreator rel = new RelCreator();
        	rel.addRel(80000);
        	tx.success();
    	}
    	DatabaseManager.graphDb.shutdown();
        System.out.println( "Hello World!" );
    }
}

package BTL_OOP.BTL_OOP;

import org.neo4j.commandline.arguments.common.Database;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.impl.storageengine.impl.recordstorage.RelationshipCreator;

import database.DatabaseManager;
import database.PersonCreator;
import database.RelCreator;
import gendata.GenPerson;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
    	
//    	try (Transaction tx = DatabaseManager.graphDb.beginTx()) {
//    		PersonCreator per = new PersonCreator();
//        	per.addPerson("/media/ductrong/DATA/Study/Kì 5 - BK/OOP/Workspace/BTL-OOP/src/main/java/rawdata/person.txt");
//        	RelCreator rel = new RelCreator();
//        	rel.addRel(10);
//        	tx.success();
//    	}
//    	DatabaseManager.graphDb.shutdown();
        
    	GenPerson genPerson = new GenPerson();
    	genPerson.genPerson(5000);
    	
        System.out.println( "Hello World!" );
    }
}

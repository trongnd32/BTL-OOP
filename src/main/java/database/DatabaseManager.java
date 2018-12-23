package database;

import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import java.io.File;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.Label;

public class DatabaseManager {
	public static int totalEntity = 0;
	public static int personAmount = 0;
	public static int countryAmount = 0;
	public static int locaAmount = 0;
	public static int orgsAmount = 0;
	public static int timeAmount = 0;
	public static int eventAmount = 0;
	
	public static GraphDatabaseFactory graphDbFactory = new GraphDatabaseFactory();
	public static GraphDatabaseService graphDb = graphDbFactory.newEmbeddedDatabase(new File("/home/ductrong/Downloads/neo4j-community-3.5.1/data/databases/entities"));
//	public static GraphDatabaseService graphDb = graphDbFactory.newEmbeddedDatabase("/home/ductrong/Downloads/neo4j-community-3.5.1/data"));
}

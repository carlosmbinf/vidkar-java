package vidkar.controlador;

import static com.mongodb.client.model.Filters.eq;

import java.util.LinkedList;

import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

public class Connection {
	// Replace the uri string with your MongoDB deployment's connection string
	String uri = "mongodb://root:lastunas123@152.206.177.186:27017/";
	MongoClient mongoClient = MongoClients.create(uri);
	MongoDatabase database = mongoClient.getDatabase("meteor");

	public Document findFirstElement() {

		try {
			MongoCollection<Document> collection = database.getCollection("users");
			Bson projectionFields = Projections.fields(
//					Projections.include("type", "version")
//					, Projections.excludeId()
			);
			Document doc = collection.find(
//					eq("title", "The Room")
			).projection(projectionFields)
//					.sort(Sorts.descending("imdb.rating"))
					.first();
			if (doc == null) {
				return null;
			} else {
				return doc;
			}
		} catch (Error e) {
			e.printStackTrace();
		}
		return null;
	}

	public LinkedList<Document> findAllElement() {
		LinkedList<Document> list = new LinkedList<Document>();
		try {

			MongoCollection<Document> collection = database.getCollection("users");
			Bson projectionFields = Projections.fields(
                    Projections.include("megasGastadosinBytes", "profile", "username", "vpnMbGastados", "movil")
//                    Projections.excludeId()
			);
			MongoCursor<Document> cursor = collection.find(
//            		lt("runtime", 15)
			).projection(projectionFields)
                    .sort(Sorts.descending("megasGastadosinBytes","vpnMbGastados"))
					.iterator();
			try {
				while (cursor.hasNext()) {
					list.add(cursor.next());
				}
			} finally {
				cursor.close();
			}
			
		} catch (Error e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Document findUserById(String id) {
		try {

			MongoCollection<Document> collection = database.getCollection("users");
			Bson projectionFields = Projections.fields(
                    Projections.include("megasGastadosinBytes", "profile", "username", "emails")
//                    Projections.excludeId()
			);
			Document doc = collection.find(
            		new Document("_id",id)
			).projection(projectionFields)
//                    .sort(Sorts.descending("megasGastadosinBytes"))
					.first();
			return doc;
		} catch (Error e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {

		Connection vidkarDB = new Connection();
		Object result = vidkarDB.findFirstElement();
		if (result != null) {
			System.out.println(((Document) result).toJson());
		}

		for (Document doc : vidkarDB.findAllElement()) {
			System.out.println(doc.toJson());
		}
//		vidkarDB.findAllElement().;
	}

}

package persistence;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class Dao {

	private MongoClient client;

	public MongoClient getCliente() {
		client = new MongoClient();
		return client;
	}

	public MongoDatabase getDB() throws Exception {
		return getCliente().getDatabase("mongoMVC");
	}

	public void close() throws Exception {
		client.close();
	}

}

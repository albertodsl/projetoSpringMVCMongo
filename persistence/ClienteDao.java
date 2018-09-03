package persistence;

//eq
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import adapter.ClienteAdapter;
import entity.Cliente;

public class ClienteDao extends Dao {

	public static final String CLIENTE = "cliente";

	public void save(Cliente c) throws Exception {

		try {
			MongoCollection<Document> coll = getDB().getCollection(CLIENTE);
			coll.insertOne(ClienteAdapter.toDBObject(c));

		} finally {
			close();
		}
	}

	public List<Cliente> findAll() throws Exception {
		MongoCursor<Document> doc = null;

		try {

			MongoCollection<Document> coll = getDB().getCollection(CLIENTE);
			doc = coll.find().iterator();
			List<Cliente> lista = new ArrayList<>();
			lista.clear();
			while (doc.hasNext()) {
				lista.add(ClienteAdapter.getFromClienteDoc(doc.next()));
			}

			return lista;

		} finally {
			doc.close();
			close();
		}
	}
	
	public Cliente findByCode(Double id) throws Exception {
		MongoCursor<Document> doc = null;
		Cliente c = null;
		
		
		try {

			MongoCollection<Document> coll = getDB().getCollection(CLIENTE);
			doc = coll.find(eq("_id", id)).iterator();
			
			if(doc.hasNext()) {
				c = ClienteAdapter.getFromClienteDoc(doc.next());
			}
			
			return c;
			
		} finally {
			doc.close();
			close();
		}
	}

	public void edit(Cliente c) throws Exception {

		try {
			MongoCollection<Document> coll = getDB().getCollection(CLIENTE);
			coll.updateOne(eq("_id", c.getId()),
					new Document("$set", ClienteAdapter.toDBObject(c)));

		} finally {
			close();
		}
	}
	
	public void delete(Cliente c) throws Exception{
		
		try {
			
			MongoCollection<Document> coll = getDB().getCollection(CLIENTE);
			coll.findOneAndDelete(eq("_id", c.getId()));
			
		} finally {
			close();
		}
		
	}
	
}

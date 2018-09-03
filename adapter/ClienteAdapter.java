package adapter;

import org.bson.Document;

import entity.Cliente;

public class ClienteAdapter {

	//Função de conversão de Classe(Obj) para Doc(Obj Mongo)
	public static Document toDBObject(Cliente c) {

		Document doc = new Document();
		doc.append("_id", c.getId());
		doc.append("nome", c.getNome());
		doc.append("email", c.getEmail());

		return doc;
	}

	//Função de conversão de Doc(Obj Mongo) para Classe(Obj)
	public static Cliente getFromClienteDoc(Document doc) {

		Cliente cli = new Cliente(doc.getDouble("_id"), doc.getString("nome"), doc.getString("email"));

		return cli;
	}

}

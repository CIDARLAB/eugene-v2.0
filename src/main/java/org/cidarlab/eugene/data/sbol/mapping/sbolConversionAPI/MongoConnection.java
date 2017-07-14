package org.cidarlab.eugene.data.sbol.mapping.sbolConversionAPI;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author Shamseen Rahman  <shamseen at bu.edu>
 */
public class MongoConnection {

    private MongoClient client;
    private MongoDatabase db;

    public MongoConnection() {
    }

    // TO DO: ERROR HANDLING ------------------------------------------------------
    public MongoCollection getCollection(String colName) {
        MongoCollection<Document> collection = db.getCollection(colName);
        return collection;
    }

    public void login(String user, String pw) throws Exception {
        if (user == null || pw == null) {
        }

        try {

            // To connect to mongodb server
            String uri = String.format("mongodb://%s:%slcp@ds153412.mlab.com:53412/lookuptables", user, pw);
            MongoClientURI connectionString = new MongoClientURI(uri);
            client = new MongoClient(connectionString);
            db = client.getDatabase("lookuptables");
            System.out.println("Connect to database successfully");
            //boolean auth = db.authenticate(myUserName, myPassword);
            //System.out.println("Authentication: "+auth);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void logout() {
        client.close();
    }
}

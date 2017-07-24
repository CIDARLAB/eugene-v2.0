package org.cidarlab.eugene.data.sbol.mapping.sbolConversionAPI;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
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

    public MongoIterable<String> getCollectionNames() {
//        Document retVal = db.runCommand(new Document("listCollections", 1));
        MongoIterable<String> retVal = db.listCollectionNames();

        return retVal;
    }

    public void login(String user, String pw) throws Exception {
        if (user == null || pw == null) {
        }
        // To connect to mongodb server
        String uri = String.format("mongodb://%s:%s@ds153412.mlab.com:53412/lookuptables", user, pw);

        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("admin", "admin",
          "admin123".toCharArray());
        MongoClientURI connectionString = new MongoClientURI(uri);
        client = new MongoClient(connectionString);
        db = client.getDatabase("lookuptables");
        System.out.println("Connected to database successfully!");
        //boolean auth = db.authenticate(myUserName, myPassword);
        //System.out.println("Authentication: "+auth);

    }

    public void logout() {
        client.close();
    }
}

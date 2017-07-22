/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.eugene.data.sbol.mapping.sbolConversionAPI;

// MongoDB imports
import org.junit.After;
import org.junit.Before;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;

// Java imports
import java.util.ArrayList;
import java.util.Arrays;

/**
 * SBOLConversionDataLayer connects to the LookupTables database (MongoDB) at
 * https://mlab.com/databases/lookuptables .
 *
 * Performs two-way data exchange with the SBOL2Conversion collection.
 *
 * @author Shamseen Rahman <shamseen at bu.edu>
 */
public class SBOLConversionDataLayer {

    private MongoConnection _instance;
    private MongoCollection collection;
    private Document doc;

    public SBOLConversionDataLayer() {
        setUp();
    }

    public ArrayList<String> getEugenePartNames(String so) {
        ArrayList<String> names = new ArrayList<>();

        if (findDocument(so)) {
            names = (ArrayList) doc.get("eugenePartNames");
        }

        return names;
    }

    public void UpsertEugenePartName(String so, String name) {
        if (findDocument(so)) {
            updateSOTerm(so, name, true);
            return;
        }

        Document d = new Document("sequenceOntologyNumber", so)
          .append("eugenePartNames", Arrays.asList(name));

        collection.insertOne(d);
    }

    public boolean removeEugenePartName(String so, String name) {
        boolean retVal = true;

        if (findDocument(so)) {
            updateSOTerm(so, name, false);
        }

        return retVal;
    }

    private void updateSOTerm(String so, String name, boolean addName) {
        ArrayList<String> names = (ArrayList) doc.get("eugenePartNames");

        if (addName == names.contains(name)) {
            return;
        }

        if (addName) {
            names.add(name);
        } else {
            names.remove(name);
        }

        collection.updateOne(eq("sequenceOntologyNumber", so),
          new Document("$set", new Document("eugenePartNames", names)));
    }

    private boolean findDocument(String so) {

        doc = (Document) collection.find(eq("sequenceOntologyNumber", so)).first();

        boolean retVal = doc != null;

        return retVal;
    }

    @Before
    private void setUp() {
        _instance = new MongoConnection();

        try {
            _instance.login("shamseen", "lcp");
            collection = _instance.getCollection("SBOL2Conversions");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    private void tearDown() {
        _instance.logout();
    }
}

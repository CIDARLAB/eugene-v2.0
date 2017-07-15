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
    }

    public ArrayList<String> getEugenePartNames(String so) {
        ArrayList<String> names = new ArrayList<>();

        if (findDocument(so)) {
            names = (ArrayList) doc.get("eugenePartNames");
        }

        return names;
    }

    public boolean insertEugenePartName(String so, String name) {

        Document d = new Document("sequenceOntologyNumber", so)
          .append("eugenePartNames", Arrays.asList(name));

        collection.insertOne(d);

        return true;
    }

    public boolean removeEugenePartName(String so, String name) {
        boolean retVal = true;

        return retVal;
    }

    public boolean updateSOTerm(String so, String name) {
        ArrayList<String> names = (ArrayList) doc.get("eugenePartNames");

        names.add(name);

        doc.put("eugenePartNames", names);

        // TO DO: Don't replace whole doc, just update it.
        collection.updateOne(
          eq("sequenceOntologyNumber", so),
          doc);
        return true;
    }

    private boolean findDocument(String so) {
        boolean retVal = true;

        doc = (Document) collection.find(eq("sequenceOntologyNumber", so)).first();

        //if doc something
        return retVal;
    }

    @Before
    private void setUp() {
        _instance = new MongoConnection();

        try {
            _instance.login("shamseen@bu.edu", "lcp");
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.eugene.data.sbol.mapping.sbolConversionAPI;

//MongoDB
import org.junit.After;
import org.junit.Before;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 * SBOLConversionDataLayer connects to the LookupTables database (MongoDB) at
 * https://mlab.com/databases/lookuptables .
 *
 * Performs two-way data exchange with the SBOL2Conversion collection.
 *
 * @author Shamseen Rahman <shamseen at bu.edu>
 */
public class SBOLConversionDataLayer {

    private MongoConnection instance;
    private MongoCollection collection;
    private Document doc;

    public SBOLConversionDataLayer() {
    }

    public List<String> getEugenePartNames(String so) {
        List<String> names = new ArrayList<>();

        if (findDocument(so)) {
            names = (List) doc.get("eugenePartNames");
        }

        return names;
    }

    public boolean insertEugenePartName(String so, String name) {
        boolean retVal = true;
        if (!findDocument(so)) {

        } else {

        }

        return retVal;
    }

    public boolean removeEugenePartName(String so, String name) {
        boolean retVal = true;
        if (!findDocument(so)) {

        } else {

        }

        return retVal;
    }

    private boolean findDocument(String so) {
        boolean retVal = true;

        doc = (Document) collection.find(eq("sequenceOntologyNumber", so)).first();

        //if doc something
        return retVal;
    }

    @Before
    private void setUp() {
        instance = new MongoConnection();

        try {
            instance.login("shamseen@bu.edu", "lcp");
            collection = instance.getCollection("SBOL2Conversions");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    private void tearDown() {
        instance.logout();
    }
}

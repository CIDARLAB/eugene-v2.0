/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.eugene.data.sbol.mapping.sbolConversionAPI;

import java.net.URI;
import java.util.List;
import org.sbolstandard.core.util.SequenceOntology;

/**
 *
 * @author Shamseen Rahman <shamseen at bu.edu>
 */
public class SBOLConversionLogicLayer {

    private SBOLConversionDataLayer _dataLayer;
    private List<String> names;
    private int uriIndex;
    private String _so;

    // TO DO: Error handling
    public SBOLConversionLogicLayer() {
        _dataLayer = new SBOLConversionDataLayer();
        String uri = SequenceOntology.NAMESPACE.toString() + "SO_";
        uriIndex = uri.length();
    }

    public void AddOrUpdateSO(String so, String name) {
        takeSONumberFromURI(so);
        insertOrUpdate(name);
    }

    public boolean DeleteEugenePartName(String so, String name) {
        takeSONumberFromURI(so);
        return deleteEugenePartName(name);
    }

    public List<String> GetEugeneNames(String so) {
        takeSONumberFromURI(so);
        getEugeneNames();
        return names;
    }

    private boolean deleteEugenePartName(String name) {
        boolean retVal = _dataLayer.removeEugenePartName(_so, name);
        return retVal;
    }

    private void getEugeneNames() {
        names = _dataLayer.getEugenePartNames(_so);
    }

    private void insertOrUpdate(String name) {
        _dataLayer.UpsertEugenePartName(_so, name);
    }

    private void takeSONumberFromURI(String so) {
        if (so.length() > uriIndex) {
            so = so.substring(uriIndex);
        }

        _so = so;
    }

}

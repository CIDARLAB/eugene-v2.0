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

    // TO DO: Error handling
    public SBOLConversionLogicLayer() {
        _dataLayer = new SBOLConversionDataLayer();
        uriIndex = SequenceOntology.NAMESPACE.toString().length();
    }

    public void AddOrUpdateSO(URI so, String name) {
        insertOrUpdate(so, name);
    }

    public boolean DeleteEugenePartName(String so, String name) {
        return deleteEugenePartName(so, name);
    }

    public List<String> GetEugeneNames(String so) {
        getEugeneNames(so);
        return names;
    }

    private boolean deleteEugenePartName(String so, String name) {
        boolean retVal = _dataLayer.removeEugenePartName(so, name);
        return retVal;
    }

    private void getEugeneNames(String so) {
        names = _dataLayer.getEugenePartNames(so);
    }

    private void insertOrUpdate(URI _so, String name) {
        String so = _so.toString().substring(uriIndex);

        getEugeneNames(so);
        if (names.isEmpty()) {
            _dataLayer.insertEugenePartName(so, name);
        }
    }

}

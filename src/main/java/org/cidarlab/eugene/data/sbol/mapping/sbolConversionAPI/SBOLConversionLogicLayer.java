/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.eugene.data.sbol.mapping.sbolConversionAPI;

import org.junit.After;
import org.junit.Before;

/**
 *
 * @author Shamseen Rahman <shamseen at bu.edu>
 */
public class SBOLConversionLogicLayer {

    private SynBioHubAdaptor instance; //connection to symbiohub db

    @Before
    private void setUp() {
        instance = new SynBioHubAdaptor("https://synbiohub.programmingbiology.org/");
        try {
            instance.login("shamseen@bu.edu", "cidarlab");
        } catch (SynBioHubException e) {
            e.printStackTrace();
        }
    }

    @After
    private void tearDown() {
        instance.logout();
    }
}

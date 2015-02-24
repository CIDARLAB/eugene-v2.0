/* Copyright (c) 2015, Boston University
 * 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this list 
 * of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list 
 * of conditions and the following disclaimer in the documentation and/or other materials 
 * provided with the distribution.
 * 
 * 3. Neither the name of the copyright holder nor the names of its contributors may be 
 * used to endorse or promote products derived from this software without specific prior 
 * written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL 
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE 
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND 
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */

package org.cidarlab.sparrow.test;

import java.util.Set;

import org.cidarlab.eugene.data.sbol.SBOLImporter;
import org.cidarlab.eugene.dom.Component;
import org.cidarlab.sparrow.Sparrow;
import org.cidarlab.sparrow.exception.SparrowException;

/**
 * Here, we import some SBOL files (see ./test_data/), 
 * insert the data into the Sparrow LMS,
 * and compare the number of inserted facts with the number 
 * of expected facts.
 * 
 * @author Ernst Oberortner
 */
public class LocalDataImportTest {

	private Sparrow sparrow;
	
	public LocalDataImportTest() 
			throws SparrowException {
		try {
			this.sparrow = new Sparrow();
		} catch(SparrowException se) {
			throw new SparrowException("Something went wrong while initializing Sparrow! "+se.getMessage());
		}
	}
	
	public void test() 
			throws SparrowException {
		
		if(!this.importSBOL("./src/test/data/K780001.rdf")) {
			throw new SparrowException("K780001.rdf FAILED!");
		}
		if(!this.importSBOL("./src/test/data/K780002.rdf")) {
			throw new SparrowException("K780002.rdf FAILED!");
		}
		if(!this.importSBOL("./src/test/data/K780000.rdf")) {
			throw new SparrowException("K780000.rdf FAILED!");
		}
		if(!this.importSBOL("./src/test/data/partsLibrary.sbol.xml")) {
			throw new SparrowException("partsLibrary.sbol.xml FAILED!");
		}
		if(!this.importSBOL("./src/test/data/RBSs.sbol.xml")) {
			throw new SparrowException("RBSs.sbol.xml FAILED!");
		}
		if(!this.importSBOL("./src/test/data/Terminators.sbol.xml")) {
			throw new SparrowException("Terminators.sbol.xml FAILED!");
		}
		
		// finally, we dump the WM
		if(this.sparrow.getFactCount() != 114) {
			throw new SparrowException("Wrong number of facts! LocalDataImportTest FAILED!");
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean importSBOL(String file) {
		
		try {
			Object o = SBOLImporter.importSBOL(file);
			if(null != o) {
				if(o instanceof Component) {                 // Part or Device
					sparrow.insertFact((Component)o);
				} else if(o instanceof java.util.HashSet) {  // Collection
					/*
					 * here, we iterate over the collection
					 * inserting each element 
					 */
					Set<Component> components = (java.util.Set<Component>)o;
					if(!components.isEmpty()) {
						for(Component c : components) {
							sparrow.insertFact(c);
						}
					}
					
				}
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}	
	
	public static void main(String[] args) 
			throws Exception {
		
		new LocalDataImportTest().test();
	}
}

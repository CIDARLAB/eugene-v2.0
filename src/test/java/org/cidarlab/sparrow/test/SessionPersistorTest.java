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

import java.net.URL;
import java.util.Set;

import org.cidarlab.eugene.dom.Component;
import org.cidarlab.sparrow.Sparrow;
import org.cidarlab.sparrow.exception.SparrowException;
import org.cidarlab.sparrow.importers.SBOLImporter;

/**
 * 
 * @author Ernst Oberortner
 */
public class SessionPersistorTest
		implements SparrowTest {

	private Sparrow sparrow;
	private static final String URL_PREFIX = "http://convert.sbols.org/biobrick/";
	
	public SessionPersistorTest() 
			throws SparrowException {
		
		try {
			this.sparrow = new Sparrow();
		} catch(SparrowException se) {
			throw new SparrowException(
					"Something went wrong while initializing Sparrow! "+se.getMessage());
		}
	}
	
	public String getSessionId() {
		return this.sparrow.getSessionID();
	}
	
	public long getFactCount() {
		return this.sparrow.getFactCount();
	}
	
	public void test() 
			throws SparrowException {
		
		// first, we populate the session with ``real'' data
		// imported from the iGEM partsregistry
		this.doBulkImport();
		this.persist();
	}
	
	private void doBulkImport() {
		// importing the JCA constitutive promoters
		for(int i=0; i<=10; i++) {
			String part = "BBa_J231";
			if(i<10) {
				part += "0"+i;
			} else {
				part += i;
			}
			this.importSBOL(part);
		}
		
//		// import the USTC logic promoters
//		// BBa_I732200 ... BBa_I732452
//		for(int i=200; i<=452; i++) {
//			String part = "BBa_I732"+i;
//			this.importSBOL(part);
//		}
		
		// finally, we dump the WM
		//this.printComponents();
	}
	
	private void persist() {
		try {
			this.sparrow.persist();
		} catch(SparrowException se) {
			se.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private boolean importSBOL(String part) {
		
		try {
			Object o = SBOLImporter.importSBOL(new URL(URL_PREFIX+part));
			if(null != o) {
				if(o instanceof Component) {                 // Part or Device
					this.insert((Component)o);
				} else if(o instanceof java.util.HashSet) {  // Collection
					/*
					 * here, we iterate over the collection
					 * inserting each element 
					 */
					Set<Component> components = (java.util.Set<Component>)o;
					if(!components.isEmpty()) {
						for(Component c : components) {
							this.insert(c);
						}
					}
					
				}
				return true;
			}
		} catch(Exception e) {
			//e.printStackTrace();
		}
		return false;
	}
	
	private void insert(Component c) 
			throws SparrowException {
		this.sparrow.insertFact(c);
	}
	
}

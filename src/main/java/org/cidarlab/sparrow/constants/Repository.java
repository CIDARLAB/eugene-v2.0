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

package org.cidarlab.sparrow.constants;

import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.sparrow.exception.SparrowException;
import org.cidarlab.sparrow.importers.RegistryImporter;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * we use ``polymorphic method dispatch''
 * 
 * @author Ernst Oberortner
 */
public enum Repository {
	iGEM {
		@Override
		public NamedElement importData(StatefulKnowledgeSession session, String name) 
				throws SparrowException {
			try {
				return new RegistryImporter(session).importData(name);
			} catch(Exception e) {
				throw new SparrowException(e.getMessage());
			}
		}
	},
	Clotho3 {   // WHEN???
		@Override
		public NamedElement importData(StatefulKnowledgeSession session, String name) 
			throws SparrowException {
			throw new UnsupportedOperationException("NOT YET SUPPORTED!");
		}
	};

	/**
	 * 
	 * @param name ... 
	 * 
	 * @throws SparrowException
	 */
	public abstract NamedElement importData(StatefulKnowledgeSession session, String name) 
			throws SparrowException;
}


/**** BACKUP ****

	JBEI_ICE {
		@Override
		public void importData(StatefulKnowledgeSession session, String name) 
			throws SparrowException {
			throw new UnsupportedOperationException("NOT YET SUPPORTED!");
		}
	}
	
	
	SynBERC {
		@Override
		public void importData(StatefulKnowledgeSession session, String name) 
			throws SparrowException {
			throw new UnsupportedOperationException("NOT YET SUPPORTED!");
		}
	}


	virtualparts {
		@Override
		public NamedElement importData(StatefulKnowledgeSession session, String name) 
			throws SparrowException {
			throw new UnsupportedOperationException("NOT YET SUPPORTED!");
		}
	}
	
	SBPKb {
		@Override
		public void importData(StatefulKnowledgeSession session, String name) 
			throws SparrowException {
			throw new UnsupportedOperationException("NOT YET SUPPORTED!");
		}
 **********/
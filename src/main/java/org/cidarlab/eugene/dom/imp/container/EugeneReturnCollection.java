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

package org.cidarlab.eugene.dom.imp.container;

import java.util.Map;
import java.util.Collection;
import java.net.URI;

/**
 * The EugeneReturnCollection contains all relevant 
 * information regarding the execution of a Eugene script. 
 * That is:
 * -- the enumerated designs
 * -- the visualized images
 * -- statistics (TODO)
 * 
 * @author Ernst Oberortner
 *
 */

public class EugeneReturnCollection 
	extends EugeneCollection {

	/*
	 * a map of the visualized images
	 */
	private Map<String, Collection<URI>> images;
	
	public EugeneReturnCollection(String name) {
		super(name);
	}

	
	public void setImages(Map<String, Collection<URI>> images) {
		this.images = images;
	}
	
	public Map<String, Collection<URI>> getImages() {
		return this.images;		
	}
	private static final long serialVersionUID = 1695242717572898462L;
}

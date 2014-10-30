/**
 * 
 */
package org.cidarlab.sparrow.importers;

import org.cidarlab.eugene.dom.NamedElement;
import org.cidarlab.sparrow.exception.ImportException;

/**
 * @author Ernst Oberortner
 */
public interface Importer {

	/**
	 * 
	 * @param pattern  ... a regular expression that specifies the name 
	 *                     of the data object(s) to be imported
	 * @throws ImportException
	 */
	public NamedElement importData(String pattern) 
		throws ImportException;
}

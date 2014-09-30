/**
 * 
 */
package org.cidarlab.sparrow.importers;

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
	public void importData(String pattern) 
		throws ImportException;
}

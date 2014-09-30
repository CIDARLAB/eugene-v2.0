package org.cidarlab.sparrow.test;

import java.util.HashSet;
import java.util.Set;

import org.cidarlab.sparrow.Sparrow;
import org.cidarlab.sparrow.exception.SparrowException;


public class SessionIDTest 
		implements SparrowTest {

	private Set<String> ids;
	private int NR_OF_SESSIONS;
	
	public SessionIDTest(int NR_OF_SESSIONS) 
			throws SparrowException {
		
		ids = new HashSet<String>();
		this.NR_OF_SESSIONS = NR_OF_SESSIONS;
	}

	
	class SessionCreator
		implements Runnable {
		
		private Sparrow sp;
		
		public SessionCreator() 
				throws SparrowException {
			this.sp = new Sparrow();		
		}
		
		public void run() {
			
			try {
				synchronized(ids) {
					ids.add(sp.getSessionID());
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}


	/**
	 * 
	 */
	public void test() 
			throws SparrowException {
		
		for(int i=1; i<=NR_OF_SESSIONS; i++) {
			new Thread(new SessionCreator()).start();
		}

		/*
		 * TODO: the threads should notify the parent (ie. this) thread
		 */
		try {
			Thread.sleep(2000);
		} catch(Exception e) {}
		
		/*
		 * now, the number of sessions ids must
		 * be equal to the NR_OF_SESSIONS
		 */
		if(ids.size() != NR_OF_SESSIONS) {
			throw new SparrowException(
					"Session ID Test FAILED! => "+ids.size()+"!="+NR_OF_SESSIONS);
		}
	}
}

package tests.API;

import cucumber.api.java.Before;

public class GlobalHooks {
	
    
    public GlobalHooks() {
    }
    
    /*
	 * this thread is used to quit the driver before the jvm shuts down
	 */
	private static final Thread shutdownThread = new Thread() {
		@Override
		public void run() {
		}
	};
	
    @Before
    public void beforeAll() {
    }

	private void initializeDriver() {
	}
}

package com.stickify.app.util.logging;

/**
 * Log4J are installed in a low-level class loader that does not have direct access to the Spring Framework classes needed to observe transaction status. 
 * Spring classes are accessible at runtime from the 'context class loader' attached to each thread. 
 * Uses the context class loader and reflection to invoke methods on TransactionSynchronizationManager to determine transaction status for the current thread.
 * @author michael le
 *
 */
public class TransactionIndicatingUtil {
    private final static String TSM_CLASSNAME = "org.springframework.transaction.support.TransactionSynchronizationManager";
    
    /**
     * Returns a string indication if the logged message is running inside a transaction
     * [+] Transaction is active
     * [-] Transaction is inactive
     * @param verbose
     * @return transaction status
     */
    public static String getTransactionStatus(boolean verbose) 
    {
        String status = null;

        try {
        	ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if (contextClassLoader != null) {
            	Class tsmClass = contextClassLoader.loadClass(TSM_CLASSNAME);
                Boolean isActive = (Boolean) tsmClass.getMethod("isActualTransactionActive", null).invoke(null, null);
                if (!verbose) {
                    status = (isActive) ? "[+] " : "[-] ";
                } 
                else {
                    String transactionName = (String) tsmClass.getMethod("getCurrentTransactionName", null).invoke(null, null);
                    status = (isActive) ? "[" + transactionName + "] " : "[no transaction] ";
                }
            } else {
        	 status = (verbose) ? "[ccl unavailable] " : "[x ]";
            } 
        } catch (Exception e) {
            status = (verbose) ? "[spring unavailable] " : "[x ]";
        }
        return status;
    }
        
}

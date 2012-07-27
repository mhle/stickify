package com.stickify.app.util.logging;

import static com.stickify.app.util.logging.TransactionIndicatingUtil.getTransactionStatus;

import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Log4j filter to aid with transaction logging.
 * Filters intercept logging messages before they reach an Appender in Log4J (i.e., ConsoleAppender, FileAppender). 
 * We can create a filter that adds information to the MappedDiagnosticContext, or MDC, of the logging event.
 * The MDC is essentially a Map of name/value pairs that are available to an Appender for output. 
 * Adds a 'xaName' and 'xaStatus' property to the MDC.
 * 
 * @author michael le
 *
 */
public class TransactionIndicatingFilter extends Filter {
    @Override
    public int decide(LoggingEvent loggingEvent) {
        loggingEvent.setProperty("xaName", getTransactionStatus(true) );
        loggingEvent.setProperty("xaStatus", getTransactionStatus(false) );
        return Filter.NEUTRAL;
    }  
}
package com.gorkem.parentpath;

import org.slf4j.Logger;

import java.util.Timer;
import java.util.TimerTask;

public class LoggerUtils {
    public static void printLogPerSpecificTime(long sec, Logger log) {
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (log.isErrorEnabled()) { log.error("Logger name : {}. ERROR LOG", log.getName()); }
                if (log.isWarnEnabled()) { log.warn("Logger name : {}. WARN LOG", log.getName()); }
                if (log.isInfoEnabled()) { log.info("Logger name : {}. INFO LOG", log.getName()); }
                if (log.isDebugEnabled()) { log.debug("Logger name : {}. DEBUG LOG", log.getName()); }
                if (log.isTraceEnabled()) { log.trace("Logger name : {}. TRACE LOG", log.getName()); }
                System.out.println("******************************************************************");
            }
        };
        timer.scheduleAtFixedRate(task, 0, sec);
    }
}

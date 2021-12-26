package com.gorkem.parentpath.secondchildrenpath;

import com.gorkem.parentpath.LoggerUtils;
import org.slf4j.LoggerFactory;

public class SecondChildrenClass implements Runnable {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger("src.main.java.com.gorkem.parentpath.secondchildrenpath");

    @Override
    public void run() {
        LoggerUtils.printLogPerSpecificTime(8000, log);
    }
}

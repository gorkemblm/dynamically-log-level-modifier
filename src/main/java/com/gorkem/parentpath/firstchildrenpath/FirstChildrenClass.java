package com.gorkem.parentpath.firstchildrenpath;

import com.gorkem.parentpath.LoggerUtils;
import org.slf4j.LoggerFactory;

public class FirstChildrenClass implements Runnable {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger("src.main.java.com.gorkem.parentpath.firstchildrenpath");

    @Override
    public void run() {
        LoggerUtils.printLogPerSpecificTime(5000, log);
    }
}

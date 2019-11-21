package com.endata.springboot.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ligang
 * @create 2019-11-21 17:08
 */
public class returnCode {
    Map<String,Float> codeMap  = new HashMap<String,Float>();

    public int trueCode() {
        return 1;
    }
    public int falseCode() {
        return 2;
    }
    public int notLogin(){
        return 0;
    }
}

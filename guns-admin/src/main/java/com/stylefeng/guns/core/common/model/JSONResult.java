package com.stylefeng.guns.core.common.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jingyujie
 * @create 2018-08-20 上午10:00
 **/

public class JSONResult {

    private String code = CODE_SUCCESS;

    private String msg;

    private Object data;

    private Long ts;

    public JSONResult() {
    }

    public JSONResult(Object data) {
        this(CODE_SUCCESS, null, data);
    }

    public JSONResult(String msg) {
        this(CODE_SUCCESS, msg, null);
    }

    public JSONResult(String code, String msg) {
        this(code, msg, null);
    }

    public JSONResult(String key, Object value) {
        this.ts = System.currentTimeMillis();
        this.code = CODE_SUCCESS;
        Map<String, Object> m = new HashMap<String, Object>(1);
        m.put(key, value);
        data = m;
    }

    public JSONResult(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        ts = System.currentTimeMillis();
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public Long getTs() {
        return ts;
    }

    public static final String CODE_SUCCESS = "200";
    public static final String CODE_FAIL = "900";


}

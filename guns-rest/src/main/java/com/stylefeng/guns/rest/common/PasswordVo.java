package com.stylefeng.guns.rest.common;

/**
 * @author jingyujie
 * @create 2018-08-19 下午9:37
 **/

public class PasswordVo{
    private String data;

    private String object;

    private String sign;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

package com.hangzhou.mygsontest.beans;

import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;

/**
 * @Since 和 @Until 两个注解基于版本对字段进行过滤
 * Since 的意思是“自……开始”，Until 的意思是“到……为止”
 * 当版本( GsonBuilder 设置的版本) 大于或等于 Since 属性值或
 * 小于 Until 属性值时字段会进行序列化和反序列化操作，
 * 而没有声明注解的字段都会加入序列化和反序列操作
 * @author 29794
 */
public class User3 {
    @Since(1.4)
    private String a;

    @Since(1.6)
    private String b;

    @Since(1.8)
    private String c;

    @Until(1.6)
    private String d;

    @Until(2.0)
    private String e;

    private String f;

    public User3(String a, String b, String c, String d, String e, String f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    @Override
    public String toString() {
        return "User{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", e='" + e + '\'' +
                ", f='" + f + '\'' +
                '}';
    }

}

package com.hangzhou.mygsontest.beans;

import com.google.gson.annotations.Expose;

/**
 * 基于@Expose注解字段过滤
 * @author 29794
 *
 */
public class User2 {
    /**
     * @Expose(serialize = true, deserialize = true)   //序列化和反序列化都生效
     * @Expose(serialize = false, deserialize = true)  //序列化时不生效，反序列化时生效
     * @Expose(serialize = true, deserialize = false)  //序列化时生效，反序列化时不生效
     * @Expose(serialize = false, deserialize = false) //序列化和反序列化都不生效，和不写注解一样
     * */
    @Expose(serialize = true, deserialize = true)   //序列化和反序列化都生效
    private String a;

    @Expose(serialize = false, deserialize = true)  //序列化时不生效，反序列化时生效
    private String b;

    @Expose(serialize = true, deserialize = false)  //序列化时生效，反序列化时不生效
    private String c;

    @Expose(serialize = false, deserialize = false) //序列化和反序列化都不生效，和不写注解一样
    private String d;

    private String e;

    public User2(String a, String b, String c, String d, String e) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }

    @Override
    public String toString() {
        return "User{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", e='" + e + '\'' +
                '}';
    }

}

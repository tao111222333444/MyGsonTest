package com.hangzhou.mygsontest.beans;

import com.google.gson.annotations.SerializedName;

/**
 * @author 29794
 */
public class User1 {
    /**
     * 设置了SerializedName这个注解name在反序列化时就不管用了
     * 属性重命名为  userName*/
    @SerializedName("userName")
    private String name;

    /** 设置了SerializedName这个注解age在反序列化时就不管用了
     * alternate不能单独设置  必须要设置value  才能设置 alternate
     * alternate备选属性名
     * */
    @SerializedName(value = "user_age",alternate = {"userAge","Age"})
    private int age;

    private boolean sex;

    public User1(String name, int age, boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}

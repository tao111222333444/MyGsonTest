package com.hangzhou.mygsontest.beans;

/**
 * GsonBuilder 对象的 excludeFieldsWithModifiers方法接收一个 int 类型可变参数，
 * 指定不进行序列化和反序列化操作的访问修饰符字段
 * 测试数据类
 * @author 29794
 */
public class ModifierSample {
    public String publicField = "public";

    protected String protectedField = "protected";

    private String privateField = "private";

    String defaultField = "default";

    final String finalField = "final";

    static String staticField = "static";

}

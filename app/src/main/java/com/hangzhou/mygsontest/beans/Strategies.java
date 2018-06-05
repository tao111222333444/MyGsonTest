package com.hangzhou.mygsontest.beans;

/**
 * 基于策略方法，用于直接排除指定字段名或者指定字段类型
 * @author 29794
 */
public class Strategies {
    private String stringField;

    private int intField;

    private double doubleField;

    public Strategies(String stringField, int intField, double doubleField) {
        this.stringField = stringField;
        this.intField = intField;
        this.doubleField = doubleField;
    }

    @Override
    public String toString() {
        return "Strategies{" +
                "stringField='" + stringField + '\'' +
                ", intField=" + intField +
                ", doubleField=" + doubleField +
                '}';
    }

}

package com.hangzhou.mygsontest;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.hangzhou.mygsontest.beans.User;

import java.io.IOException;

/**
 *  定义 TypeAdapter 的子类 UserTypeAdapter 来接管 User 类的序列化和反序列化过程
    这里设定当 User 类序列化时 Json 中的Key值都是大写字母开头，
    反序列化时支持“name”和“Name”两种不同的 Json 风格
 * */
public class UserTypeAdapter extends TypeAdapter<User>{

    @Override
    public void write(JsonWriter out, User value) throws IOException {
        //流式序列化成对象开始
        out.beginObject();
        out.name("Name").value(value.getName());
        out.name("Age").value(value.getAge());
        out.name("Sex").value(value.isSex());
        //流式序列化结束
        out.endObject();
    }

    @Override
    public User read(JsonReader in) throws IOException {
        User user = new User();
        ////流式反序列化开始
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()){
                case "Name":
                case "name":
                    user.setName(in.nextString());
                    break;
                case "age":
                    user.setAge(in.nextInt());
                    break;
                case "sex":
                    user.setSex(in.nextBoolean());
                    break;
                    default:
            }

        }
        in.endObject();
        return user;
    }
}

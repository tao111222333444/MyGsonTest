package com.hangzhou.mygsontest;

import android.view.View;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.hangzhou.mygsontest.beans.ModifierSample;
import com.hangzhou.mygsontest.beans.Strategies;
import com.hangzhou.mygsontest.beans.Strategies1;
import com.hangzhou.mygsontest.beans.User;
import com.hangzhou.mygsontest.beans.User1;
import com.hangzhou.mygsontest.beans.User2;
import com.hangzhou.mygsontest.beans.User3;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

public class GsonTest {
    private void getGson(){
        //通过构造函数来获取  Gson
        Gson gson = new Gson();
        //通过 GsonBuilder 来获取，可以进行多项特殊配置
        Gson gson1 =new GsonBuilder().create();
    }
    /**生成Json*/
    private static void createJson(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("String","gson");
        jsonObject.addProperty("number_int",12);
        jsonObject.addProperty("number_double",12.2);
        jsonObject.addProperty("boolean",true);
        jsonObject.addProperty("char","h");

        JsonObject jsonElement = new JsonObject();
        jsonElement.addProperty("boolean",false);
        jsonElement.addProperty("number_Double",16.6);
        jsonElement.addProperty("char","c");
        jsonObject.add("jsonElement",jsonElement);
        System.out.println();
        System.out.println(jsonObject);
    }


    private  static void stringToJson(){
        //通过构造函数来获取  Gson
        Gson gson = new Gson();
        //Json数组 转为 字符串数组
        String jsonArray = "[\"string\",\"boolean\",\"java\",\"kotlin\",\"git\"]";
        String[] strings = gson.fromJson(jsonArray,String[].class);
        System.out.println("\nJson数组 转为 字符串数据：");
        for (String string : strings){
            System.out.println(string);
        }
        jsonArray = "";
        //字符串数组  转为  json数组
        jsonArray = gson.toJson(strings,String[].class);
        System.out.println("\n字符串数据  转为 json数据");
        System.out.println(jsonArray);
    }

    private static void listToJson(){
        Gson gson = new Gson();
        //Json数组转为List
        String jsonArray = "[\"string\",\"boolean\",\"java\",\"kotlin\",\"git\"]";
        List<String> stringList = gson.fromJson(jsonArray,new TypeToken<List<String>>(){
            }.getType());
        System.out.println("\njson数组  转为 List：");
        for (String string: stringList){
            System.out.println(string);
        }

        //List 转为 json数组
        jsonArray = "";
        jsonArray = gson.toJson(stringList,new TypeToken<List<String>>(){
            }.getType());
        System.out.println("\nList 转为 Json数组：");
        System.out.println(jsonArray);
    }

    private static void gsonSerialize(){
        //序列化
        User user = new User("hugo",23,true);
        Gson gson = new Gson();
        System.out.println();
        System.out.println(gson.toJson(user));
    }

    private static void gsonDeserialize(){
        //反序列化
        String userJson = "{\"name\":\"hugo\",\"age\":23,\"sex\":true}";
        Gson gson = new Gson();
        User user = gson.fromJson(userJson,User.class);
        System.out.println();
        System.out.println(user);
    }

    private static void gsonSerialize1(){
        //序列化
        User1 user = new User1("hugo",23,true);
        Gson gson = new Gson();
        System.out.println();
        System.out.println(gson.toJson(user));
    }
    /**用于 重命名的demo*/
    private static void gsonDeserialize1(){
        //反序列化
        String userJson = "{\"userName\":\"hugo\",\"age\":23,\"sex\":true}";
        Gson gson = new Gson();
        User1 user = gson.fromJson(userJson,User1.class);
        System.out.println();
        System.out.println(user);
    }

    /**用于 检测备选属性名效果的demo*/
    private static void gsonDeserialize2(){
        //反序列化
        Gson gson = new Gson();
        String userJson = "{\"userName\":\"hugo\",\"age\":23,\"sex\":true}";
        User1 user = gson.fromJson(userJson,User1.class);
        System.out.println("因为  在SerializedName注解里没有设置age  所以age是解析不到值的");
        System.out.println(user);

        userJson = "{\"userName\":\"hugo\",\"Age\":26,\"sex\":true}";
        user = gson.fromJson(userJson,User1.class);
        System.out.println();
        System.out.println(user);

        userJson = "{\"userName\":\"hugo\",\"userAge\":24,\"sex\":true}";
        user = gson.fromJson(userJson,User1.class);
        System.out.println();
        System.out.println(user);

        userJson = "{\"userName\":\"hugo\",\"user_age\":25,\"sex\":true}";
        user = gson.fromJson(userJson,User1.class);
        System.out.println();
        System.out.println(user);
    }

    /**用于测试  Expose注解  字段过滤功能*/
    private static void jsonExpose(){
        //设置了这个excludeFieldsWithoutExposeAnnotation() 方法的时候  只有Expose注解的属性才能序列化和反序列化
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        User2 user = new User2("A", "B", "C", "D", "E");
        System.out.println();
        System.out.println(gson.toJson(user));

        String json = "{\"a\":\"A\",\"b\":\"B\",\"c\":\"C\",\"d\":\"D\",\"e\":\"E\"}";
        user = gson.fromJson(json,User2.class);
        System.out.println();
        System.out.println(user);
    }

    /**用于测试  Since和Until注解  版本过滤功能*/
    private static void jsonSinceAndUntil(){
        //在创建Gson时  设置版本
        Gson gson = new GsonBuilder().setVersion(1.6).create();
        //序列化
        User3 user3 =  new User3("A", "B", "C", "D", "E","F");
        System.out.println();
        System.out.println(gson.toJson(user3));

        //反序列化
        String jsonString = "{\"a\":\"A\",\"b\":\"B\",\"c\":\"C\",\"d\":\"D\",\"e\":\"E\",\"f\":\"F\"}";
        user3 = gson.fromJson(jsonString,User3.class);
        System.out.println();
        System.out.println(user3);
    }

    /**
     * 访问修饰符由 java.lang.reflect.Modifier 提供 int 类型的定义，
     * 而 GsonBuilder 对象的 excludeFieldsWithModifiers方法接收一个 int 类型可变参数，
     * 指定不进行序列化和反序列化操作的访问修饰符字段
     *
     * 指定不进行序列化和反序列化操作的访问修饰符字段*/
    private static void gsonModifier(){
        //excludeFieldsWithModifiers()  设置那些修饰符类型  不序列化和反序列化
        Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.PRIVATE,Modifier.STATIC).create();
        ModifierSample modifierSample = new ModifierSample();
        System.out.println();
        System.out.println(gson.toJson(modifierSample));
    }

    /**
     * GsonBuilder 类包含 setExclusionStrategies(ExclusionStrategy... strategies)
     * 方法用于传入不定长参数的策略方法，用于直接排除指定字段名或者指定字段类型
     * */
    private static void gsonStrategies(){
        //setExclusionStrategies 方法在序列化和反序列化时都会生效，
        // 如果只是想指定其中一种情况下的排除策略或分别指定排除策略，
        // 可以改为使用以下两个方法
        //只在序列化时生效
        //addSerializationExclusionStrategy(ExclusionStrategy strategy);
        //只在反序列化时生效
        //addDeserializationExclusionStrategy(ExclusionStrategy strategy);

        Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                //排除指定字段名
                return "intField".equals(f.getName());
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                //排除指定字段类型  double 和Double  在这是不一样
                return clazz.getName().equals(double.class.getName());
            }
        }).create();

        Strategies strategies = new Strategies("stringField",11,12.66);
        System.out.println();
        System.out.println(gson.toJson(strategies));

        String json = "{\"stringField\":\"stringField\",\"intField\":111,\"doubleField\":11.22}";
        strategies = gson.fromJson(json,Strategies.class);
        System.out.println();
        System.out.println(strategies);
    }

    /**对于 Gson 而言，在序列化时如果某个属性值为 null 的话，
     * 那么在序列化时该字段不会参与进来，
     * 如果想要显示输出该字段的话，
     * 可以通过 GsonBuilder 进行配置serializeNulls()//输出null
     * */
    private static void serializeNull(){
        Gson gson = new GsonBuilder()
                .serializeNulls()//输出null
                .create();
        Strategies strategies = new Strategies(null,10,16.66);
        System.out.println();
        System.out.println(gson.toJson(strategies));

    }

    /**
     * 默认的序列化的json字符串是没有换行的   我们可以用setPrettyPrinting  格式化输出
     * */
    private static void prettyPrinting(){
        Gson gson = new GsonBuilder()
                .serializeNulls()//输出null
                .setPrettyPrinting()//格式化输出
                .create();

        Strategies strategies = new Strategies(null,10,16.66);
        System.out.println();
        System.out.println(gson.toJson(strategies));
    }

    /**
     *  用Gson setDateFormat格式化时间
     * */
    public static void dateFormat(){
        Gson gson  =  new GsonBuilder()
                .setPrettyPrinting()
                //格式化时间
                .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
                .create();

        Date date = new Date();
        Strategies1 strategies1 = new Strategies1(date,new Date(date.getTime()+1000000));
        System.out.println();
        System.out.println(gson.toJson(strategies1));

        String json = "{\n" +
                "  \"date\": \"2018-06-05 11:15:42:739\",\n" +
                "  \"date2\": \"2018-06-05 11:32:22:739\"\n" +
                "}";
        System.out.println();
        System.out.println(gson.fromJson(json,Strategies1.class));
    }

    /**
     * 设置 某种类型的对应序列化和反序列化
     * 用继承TypeAdapter的类的
     * write方法来序列化
     * read方法来反序列化
     * */
    private static void typeAdapter(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(User.class,new UserTypeAdapter())
                .create();
        User user = new User("hugo",5,true);
        System.out.println();
        System.out.println(gson.toJson(user));

        String json = "{\"Name\":\"hugo\",\"age\":24,\"sex\":true}";
        System.out.println();
        System.out.println(gson.fromJson(json,User.class));

    }

    /**
     * TypeAdapter 将序列化和反序列操作都接管了过来，
     * 其实 Gson 还提供了只接管序列化过程的接口，
     * 即 JsonSerializer
     *
     */
    private static void jsonSerializer(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(User.class,new JsonSerializer<User>(){
                    @Override
                    public JsonElement serialize(User src, Type typeOfSrc, JsonSerializationContext context) {
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("UserName",src.getName());
                        jsonObject.addProperty("Age",src.getAge());
                        jsonObject.addProperty("sex",src.isSex());

                        return jsonObject;
                    }
                })
                .create();
        User user = new User("hugo",24,true);
        System.out.println();
        System.out.println(gson.toJson(user));
    }

    /**
     * 相对应的，JsonDeserializer 接口提供了反序列化的接口
     *
     * Gosn 还提供了另一个注解 @JsonAdapter 用于进行简单的声明类似于这样，
     * 声明了 User 类的序列化或反序列化操作由 UserTypeAdapter 完成，
     * 注解的优先级高于 registerTypeAdapter 方法
     * */
    private static void jsonDeserializer(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(User.class,new  JsonDeserializer<User>(){

                    @Override
                    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        User user = new User();
                        JsonObject jsonObject = json.getAsJsonObject();
                        //同时支持 userName 和 name 两种情况
                        if(jsonObject.has("name")){
                            user.setName(jsonObject.get("name").getAsString());
                        }else if(jsonObject.has("userName")){
                            user.setName(jsonObject.get("userName").getAsString());
                        }
                        user.setSex(jsonObject.get("sex").getAsBoolean());
                        user.setAge(jsonObject.get("age").getAsInt());
                        return user;
                    }
                })
                .create();

        String json = "{\"userName\":\"hugo\",\"sex\":true,\"age\":24}";
        User user = gson.fromJson(json, User.class);
        System.out.println();
        System.out.println(user);

        json = "{\"name\":\"hugo\",\"sex\":true,\"age\":24}";
        user = gson.fromJson(json, User.class);
        System.out.println();
        System.out.println(user);



    }

    /**
     *TypeAdapterFactory 是用于创建 TypeAdapter 的工厂类，
     * 通过参数 TypeToken 来查找确定对应的 TypeAdapter，
     * 如果没有就返回 null 并由 Gson 默认的处理方法来进行序列化和反序列化操作，
     * 否则就由用户预定义的 TypeAdapter 来进行处理
     * */
    private static void typeAdapterFactory(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new TypeAdapterFactory() {
            @Override
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
                //如果 Gson 需要与 User 类相关的 TypeAdapter ，则返回我们自己定义的 TypeAdapter 对象
                if (User.class.isAssignableFrom(type.getRawType())) {
                    return (TypeAdapter<T>) new UserTypeAdapter();
                }
                //找不到则返回null
                return null;
            }
        }).create();

        User user = new User("hugo",5,true);
        System.out.println();
        System.out.println(gson.toJson(user));

        String json = "{\"Name\":\"hugo\",\"age\":24,\"sex\":true}";
        System.out.println();
        System.out.println(gson.fromJson(json,User.class));

        Strategies strategies = new Strategies("stringField",10,16.66);
        System.out.println();
        System.out.println(gson.toJson(strategies));
    }


    public static  void main(String[] arg){
        typeAdapterFactory();
    }
}

package app.leiyu.com.weather.utill;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.commons.io.IOUtils;


import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * Created by leiyu on 17/3/16.
 */

public class GetData {

    private List<String>datas=new ArrayList<>();
    public  String get_data() {
        String data="";
        try{
            URL url = new URL("http://mpianatra.com/Courses/files/data.json");
            InputStream in=url.openStream();
            data=IOUtils.toString(in);
            in.close();
        }catch(Exception e){data="error";}

        return data;
    }

    public  List deal_data(String data){
            try {
                JsonObject obj = new JsonParser().parse(data).getAsJsonObject();
                JsonArray array = obj.getAsJsonArray("allNews");
                for (JsonElement ele : array) {
                    String str1 = ele.getAsJsonObject().get("link").getAsString();
                    String str2 = ele.getAsJsonObject().get("title").getAsString();
                    datas.add(str1 + "\n" + str2);
                }

            }catch (Exception e){datas.add("error");}
            return datas;

    }

    public  static  void main(String[] args){
        GetData get=new GetData();
        System.out.println(get.deal_data(get.get_data()));
    }

}

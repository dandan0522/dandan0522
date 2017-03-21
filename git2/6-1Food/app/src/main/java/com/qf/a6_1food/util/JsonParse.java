package com.qf.a6_1food.util;

import com.qf.a6_1food.bean.Classify;
import com.qf.a6_1food.bean.DetailFood;
import com.qf.a6_1food.bean.Food;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王松 on 2017/2/27.
 */

public class JsonParse {
    public static List<Classify> parseJson2Classify(String jsonStr) {
        if (jsonStr == null || jsonStr.equals("")) {
            return null;
        }
        List<Classify> list = new ArrayList<>();
        try {
            JSONObject jo = new JSONObject(jsonStr);
            JSONArray tngou = jo.getJSONArray("tngou");
            for (int i = 0; i < tngou.length(); i++) {
                JSONObject data = tngou.getJSONObject(i);
                String name = data.getString("name");
                int id = data.getInt("id");
                Classify e = new Classify();
                e.setName(name);
                e.setId(id);
                list.add(e);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Food> parseJson2Food(String jsonStr) {
        if (jsonStr == null || jsonStr.equals("")) {
            return null;
        }
        List<Food> foods = new ArrayList<>();
        try {
            JSONObject jo = new JSONObject(jsonStr);
            JSONArray tngou = jo.getJSONArray("tngou");
            for (int i = 0; i < tngou.length(); i++) {
                JSONObject data = tngou.getJSONObject(i);
                String description = data.getString("description");
                String keywords = data.getString("keywords");
                String img = data.getString("img");
                int id = data.getInt("id");
                Food e = new Food();
                e.setId(id);
                e.setDescription(description);
                e.setImg("http://tnfs.tngou.net/image" + img);
                e.setKeywords(keywords);
                foods.add(e);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return foods;
    }

    public static DetailFood parseJson2DetailFood(String jsonStr) {
        if (jsonStr == null || jsonStr.equals("")) {
            return null;
        }
        DetailFood food = new DetailFood();
        try {
            JSONObject jo = new JSONObject(jsonStr);
            food.setMessage(jo.getString("message"));
            food.setImg("http://tnfs.tngou.net/image" + jo.getString("img"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return food;
    }
}

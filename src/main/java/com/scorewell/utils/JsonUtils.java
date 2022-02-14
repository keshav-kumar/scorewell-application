package com.scorewell.utils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtils {

	private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	
	public static JsonObject createErrorResponse(String error) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("success", false);
		jsonObject.addProperty("error", error);
		return jsonObject;
	}

	public static JsonObject createSuccessResponse(String data) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("success", true);
		jsonObject.addProperty("data", data);
		return jsonObject;
	}
	
	public static JsonObject createSuccessResponse(JsonArray data) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("success", true);
		jsonObject.add("data", data);
		return jsonObject;
	}

	public static JsonObject createPaginatedResponse(JsonArray data, int total, int pos) {
		JsonObject resObj = new JsonObject();
		resObj.addProperty("total", total);
		if (data == null) {
			data = new JsonArray();
		}
		resObj.addProperty("n", data.size());
		resObj.addProperty("pos", pos);
		resObj.add("data", data);
		return resObj;
	}

	public static JsonObject getJson(Map<String, ?> map) {
		JsonObject jsonObj = new JsonObject();
		if (map == null) {
			return jsonObj;
		}
		for (Entry<String, ?> en : map.entrySet()) {
			String key = en.getKey();
			Object value = en.getValue();
			jsonObj.add(key, new Gson().toJsonTree(value));
		}
		return jsonObj;
	}

	public static <T> T getMerged(T oldT, T newT) {
		String oldStr=new Gson().toJson(oldT, oldT.getClass());
		JsonObject oldObj = (new JsonParser()).parse(oldStr).getAsJsonObject();
		String newStr=new Gson().toJson(newT, newT.getClass());
		JsonObject newObj = (new JsonParser()).parse(newStr).getAsJsonObject();
			for (Map.Entry<String, JsonElement> entry : newObj.entrySet()) {
				String key = entry.getKey();
				JsonElement val = entry.getValue();
				if(oldObj.isJsonObject()){
					setMergedValue(oldObj, val, key);
				}
			}
			oldT=(T) new Gson().fromJson(oldObj, oldT.getClass());
			return oldT;
	}

	private static void setMergedValue(JsonObject obj, JsonElement val, String key) {
		if (val.isJsonArray()) {
			obj.add(key, val.getAsJsonArray());
		} else if (val.isJsonObject()) {
			obj.add(key, val.getAsJsonObject());
		} else if (val.isJsonNull()) {
			obj.add(key, val.getAsJsonNull());
		} else if (val.isJsonPrimitive()) {
			obj.add(key, val.getAsJsonPrimitive());
		} else {
			obj.add(key, val);
		}
	}

	public static <T> JsonArray getField(T t) {
		JsonArray arr = new JsonArray();
		for (Field f : t.getClass().getDeclaredFields()) {
			arr.add(f.getName());
		}
		return arr;
	}

	public static double getDoubleValue(JsonObject jsonObj, String key) {
		if(jsonObj==null){
			return 0;
		}
		JsonElement valObj = jsonObj.get(key);
		if(valObj==null){
			return 0;
		}
		try{
			double asDouble = valObj.getAsDouble();
			return asDouble;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
		
		return 0;
	}
	
	public static int getIntValue(JsonObject jsonObj, String key) {
		if(jsonObj==null){
			return 0;
		}
		JsonElement valObj = jsonObj.get(key);
		if(valObj==null){
			return 0;
		}
		try{
			int parseInt = valObj.getAsInt();
			return parseInt;
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
		
		return 0;
	}
	
	public static String getStringValue(JsonObject jsonObj, String key){
		if(jsonObj==null){
			return null;
		}
		JsonElement valObj = jsonObj.get(key);
		if(valObj==null){
			return null;
		}
		return valObj.getAsString();
	}
}

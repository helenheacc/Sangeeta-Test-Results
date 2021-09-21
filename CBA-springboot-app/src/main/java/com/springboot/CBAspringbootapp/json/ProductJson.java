package com.springboot.CBAspringbootapp.json;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.springboot.CBAspringbootapp.bean.Product;

public class ProductJson {
	
	public static String generateProductJson(Product product) {
		Gson gson = new Gson();
		String jsonString = gson.toJson(product);	
		return jsonString;
		
	}
	public static String generateProductListJson(List<Product> products) {
		JsonArray ja = new JsonArray();
		JsonObject jo = null;
		for(Product product : products) {
			jo = new JsonObject();
			jo.addProperty("id", product.getId());
			jo.addProperty("brand", product.getBrand());
			jo.addProperty("model", product.getModel());
			//jo.addProperty("price", product.getPrice());
			ja.add(jo);	
		}
		jo = new JsonObject();
		jo.add("products", ja);
		return jo.toString();
		
	}
	
	public static String generateVersionJson(String version) {
		JsonObject jo = new JsonObject();
		jo.addProperty("version", version);
		return jo.toString();
	}
	
	public static String generateNotFoundJson() {
		JsonObject jo = new JsonObject();
		jo.addProperty("message", "Not Found.");
		return jo.toString();		
	}

	public static String generateErrorJson(String error) {
		JsonObject jo = new JsonObject();
		jo.addProperty("error", error);
		return jo.toString();	
	}
}

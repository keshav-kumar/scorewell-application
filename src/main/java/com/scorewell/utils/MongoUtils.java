package com.scorewell.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class MongoUtils {

	public static List<Map> getTimeRangeAndQuery(String field,long startTime, long endTime){
		List<Map> andList = new ArrayList<>();
		andList.add(Collections.singletonMap(field, Collections.singletonMap("$gte", startTime)));
		andList.add(Collections.singletonMap(field, Collections.singletonMap("$lte", endTime)));
		return andList;
	}
	
	public static Map<String,Object> getNameDetailSearchQuery(String name){
		Map<String,Object> queryMap = new HashMap<>();
		if(name==null||name.trim().equals("")){
			name = "";
		}
		name = name.trim();
		String firstName = name;
		String lastName = name;
		List<Map<String, Object>> orList = new ArrayList<>();
		if(name.indexOf(' ')>0){
			firstName = name.substring(0, name.indexOf(' '));
			lastName = name.substring(name.lastIndexOf(' ')+1);
			orList.add(Collections.singletonMap("nameDetail.name", Pattern.compile(Pattern.quote(name), Pattern.CASE_INSENSITIVE)));
			Map<String,Object> nameMap = new HashMap<>();
			nameMap.put("nameDetail.firstName", Pattern.compile(Pattern.quote(firstName), Pattern.CASE_INSENSITIVE));
			nameMap.put("nameDetail.lastName", Pattern.compile(Pattern.quote(lastName), Pattern.CASE_INSENSITIVE));
			orList.add(nameMap);
		}else{
			orList.add(Collections.singletonMap("nameDetail.name", Pattern.compile(Pattern.quote(name), Pattern.CASE_INSENSITIVE)));
			orList.add(Collections.singletonMap("nameDetail.firstName", Pattern.compile(Pattern.quote(firstName), Pattern.CASE_INSENSITIVE)));
			orList.add(Collections.singletonMap("nameDetail.lastName", Pattern.compile(Pattern.quote(lastName), Pattern.CASE_INSENSITIVE)));
		}
		queryMap.put("$or", orList);
		return queryMap;
	}
	
	public static Map<String, Object> getFacilityNameSearchQuery(String query) {
		Map<String, Object> appSearchQuery = new HashMap<>();
		List<Map<String, Object>> orList = new ArrayList<>();
		orList.add(Collections.singletonMap("insuredInfo.nameDetail.name",
				Pattern.compile(query, Pattern.CASE_INSENSITIVE)));
		orList.add(Collections.singletonMap("facilitiesApplications.facilityInfo.facilityName",
				Pattern.compile(query, Pattern.CASE_INSENSITIVE)));
		appSearchQuery.put("$or", orList);
		return appSearchQuery;
	}

	public static Map<String, Object> getSortMap(String sort) {
		if(StringUtils.isEmpty(sort)){
			return null;
		}
		Map<String, Object> map = new HashMap<>();
		int ascendingOrder = 1;
		if(sort.startsWith("-")){
			ascendingOrder = -1;
			sort = sort.substring(1);
		}
		if(sort.startsWith("+")){
			sort = sort.substring(1);
		}
		map.put(sort, ascendingOrder);
		return map;
	}
	
}

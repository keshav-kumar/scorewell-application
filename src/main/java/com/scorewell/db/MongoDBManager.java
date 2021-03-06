package com.scorewell.db;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;

/**
 *
 */
public class MongoDBManager {

	private static final Logger logger = LoggerFactory.getLogger(MongoDBManager.class);

	private MongoClient mongoClient;
	private MongoDatabase mongoDB;


	
	public MongoDBManager(String host, String dbName) {
		init(host, dbName);
	}

	private void init(String host, String dbName) {
		try {
			MongoClientOptions mongoOptions = MongoClientOptions.builder()
					.connectionsPerHost(50).connectTimeout(10000)
					.maxWaitTime(10000)
					.threadsAllowedToBlockForConnectionMultiplier(50).build();

			mongoClient = new MongoClient(host, mongoOptions);
			mongoClient.setWriteConcern(WriteConcern.SAFE);
			mongoDB = mongoClient.getDatabase(dbName);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	MongoDatabase getDB() {
		return mongoDB;
	}

	public String addObject(String collectionName, String id, String jsonData) {
		MongoCollection<Document> collection = getDB().getCollection(
				collectionName);
		Document doc = Document.parse(jsonData);
		if (id != null && !id.trim().equals("")) {
			collection.updateOne(new Document("_id", new ObjectId(id)), new Document(
					"$set", new Document(doc)));
			return id;
		} else {
			doc.remove("_id");
			collection.insertOne(doc);
			Object objectId =  doc.get("_id");
			return objectId==null?null:objectId.toString();
		}
	}
	
	public String updateObject(String collectionName, Map<String, Object> queryParam, String jsonData){
		MongoCollection<Document> collection = getDB().getCollection(
				collectionName);
		Document doc = Document.parse(jsonData);
		doc.remove("_id");
		Document document = collection.findOneAndUpdate(new Document(queryParam), new Document(
				"$set", new Document(doc)));
		if(document==null){
			collection.insertOne(doc);
			Object objectId =  doc.get("_id");
			return objectId==null?null:objectId.toString();
		}
		Object objectId =  document.get("_id");
		return objectId==null?null:objectId.toString();
	}

	public String addObject(String collectionName, String jsonData) {
		return addObject(collectionName, null, jsonData);
	}

	public Document getObject(String collectionName,
			Map<String, Object> queryParams) {
		MongoCollection<Document> collection = getDB().getCollection(
				collectionName);
		Document doc = new Document(queryParams);
		Document result = collection.find(doc).first();
		return result;
	}
	
	public Document getObject(String collectionName,
			Map<String, Object> queryParams, Map<String, Object> sortingKey) {
		MongoCollection<Document> collection = getDB().getCollection(
				collectionName);
		Document doc = new Document(queryParams);
		FindIterable<Document> find = collection.find(doc);
		if(sortingKey!=null && sortingKey.size()>0){
			find  =find.sort(new Document(sortingKey));
		}
		Document result = find.first();
		return result;
	}

	public List<Document> getObjects(String collectionName,
			Map<String, Object> queryParams) {
		return getObjects(collectionName, 0, -1, queryParams, null);
	}

	// give negative value of n to get all results
	public List<Document> getObjects(String collectionName, int startPos,
			int n, Map<String, Object> queryParams,
			Map<String, Object> sortingKey) {

		return getObjects(collectionName, startPos, n, queryParams, null,
				sortingKey);
	}

	public List<Document> getObjects(String collectionName, int startPos,
			int n, Map<String, Object> queryParams, List<String> fields,
			Map<String, Object> sortingKey) {
		List<Document> objList = new ArrayList<Document>();
		if (startPos < 0) {
			return objList;
		}
		MongoCollection<Document> collection = getDB().getCollection(
				collectionName);
		if (collection == null) {
			return objList;
		}
		if(queryParams==null){
			queryParams = new HashMap<>();
		}
		FindIterable<Document> find = collection
				.find(new Document(queryParams));
		if (sortingKey != null && sortingKey.size() > 0) {
			find = find.sort(new Document(sortingKey));
		}
		if (fields != null && fields.size() > 0) {
			Map<String, Object> fieldMap = fields.stream().collect(
					Collectors.toMap(s -> s, s -> 1));
			find = find.projection(new Document(fieldMap));
		}
		if (startPos > 0) {
			find = find.skip(startPos);
		}
		if (n >= 0) {
			find = find.limit(n);
		}
		find.forEach(new Block<Document>() {

			@Override
			public void apply(Document paramT) {
				objList.add(paramT);
			}
		});
		return objList;
	}

	public static ObjectId getObjectId(String oid) {
		byte[] byts = new BigInteger(oid, 16).toByteArray();
		ObjectId mongoId = new ObjectId(byts);
		return mongoId;
	}

	public boolean deleteObject(String collectionName, String objectId) {
		MongoCollection<Document> collection = getDB().getCollection(
				collectionName);
		try {
			Document findOneAndDelete = collection
					.findOneAndDelete(new Document("_id",
							new ObjectId(objectId)));
			return findOneAndDelete != null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

	public void deleteObjects(String collectionName,
			Map<String, Object> queryParams) {
		MongoCollection<Document> collection = getDB().getCollection(
				collectionName);
		collection.deleteMany(new Document(queryParams));
	}

	public long getCount(String collectionName, Map<String, Object> queryParams) {
		MongoCollection<Document> collection = getDB().getCollection(
				collectionName);
		if (collection == null) {
			return 0;
		}
		long count = collection.count(new Document(queryParams));
		return count;
	}

	public boolean setField(String collectionName,
			Map<String, Object> queryParams, Map<String, Object> fieldValueMap) {
		return setField(collectionName, queryParams, fieldValueMap, true, false);
	}

	public boolean setField(String collectionName,
			Map<String, Object> queryParams, Map<String, Object> fieldValueMap,
			boolean upsert, boolean multi) {
		MongoCollection<Document> collection = getDB().getCollection(
				collectionName);
		try {
			if (multi) {
				collection.updateMany(new Document(queryParams), new Document(
						"$set", new Document(fieldValueMap)),
						new UpdateOptions().upsert(upsert));
			} else {
				collection.updateOne(new Document(queryParams), new Document(
						"$set", new Document(fieldValueMap)),
						new UpdateOptions().upsert(upsert));
			}
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}

	}
	
	public List<String> getDistinctValues(String collectionName,String fieldName,Map<String, Object> queryParams){
		List<String> objList = new ArrayList<>();
		MongoCollection<Document> collection = getDB().getCollection(
				collectionName);
		if (collection == null) {
			return objList;
		}
		if(queryParams==null){
			queryParams = new HashMap<>();
		}
		DistinctIterable<String> distinctIterable = collection.distinct(fieldName, new Document(queryParams), String.class);
		distinctIterable.forEach(new Block<String>() {

			@Override
			public void apply(String val) {
				objList.add(val);
			}
		});
		return objList;
	}
	
	public void addToFields(String collectionName,Map<String, Object> queryParams,Map<String,Integer> numbersToAdd, boolean multi){
		MongoCollection<Document> collection = getDB().getCollection(
				collectionName);
		if (collection == null) {
			return;
		}
		if(queryParams==null){
			queryParams = new HashMap<>();
		}
		Map<String,Object> numObj = new HashMap<>();
		numObj.putAll(numbersToAdd);
		if(multi){
			collection.updateMany(new Document(queryParams), new Document("$inc", new Document(numObj)));
		}else{
			collection.updateOne(new Document(queryParams), new Document("$inc", new Document(numObj)));
		}
	}
	
//	public static void main(String[] args) {
//		MongoDBManager mdm = new MongoDBManager("localhost", "pixeldb");
//		Map<String, Object> queryParams = new HashMap<>();
//		queryParams.put("accountId", "test1");
//		Map<String, Integer> numbersToAdd = new HashMap<>();
//		numbersToAdd.put("cnt", 10);
//		mdm.addToFields("customer_account", queryParams, numbersToAdd, true);
//	}

}

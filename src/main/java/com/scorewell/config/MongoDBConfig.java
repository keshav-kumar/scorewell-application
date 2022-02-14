package com.scorewell.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDBConfig {

    private String  mongodbHost;
    private int     mongodbPort;
    private String  mongoDBName;
    private String  mongoDBPrefix;
    private String  mongodbHostSlave;
    private int     mongodbPortSlave;
    private String  mongodbHostArbiter;
    private int     mongodbPortArbiter;
    private boolean readPrimary;

    public String getMongodbHost() {
        return mongodbHost;
    }

    public void setMongodbHost(String mongodbHost) {
        this.mongodbHost = mongodbHost;
    }

    public int getMongodbPort() {
        return mongodbPort;
    }

    public void setMongodbPort(int mongodbPort) {
        this.mongodbPort = mongodbPort;
    }

    public String getMongoDBPrefix() {
        return mongoDBPrefix;
    }

    public void setMongoDBPrefix(String mongoDBPrefix) {
        this.mongoDBPrefix = mongoDBPrefix;
    }

    public String getMongoDBName() {
        return mongoDBName;
    }

    public void setMongoDBName(String mongoDBName) {
        this.mongoDBName = mongoDBName;
    }

    public String getMongodbHostSlave() {
        return mongodbHostSlave;
    }

    public void setMongodbHostSlave(String mongodbHostSlave) {
        this.mongodbHostSlave = mongodbHostSlave;
    }

    public int getMongodbPortSlave() {
        return mongodbPortSlave;
    }

    public void setMongodbPortSlave(int mongodbPortSlave) {
        this.mongodbPortSlave = mongodbPortSlave;
    }

    public String getMongodbHostArbiter() {
        return mongodbHostArbiter;
    }

    public void setMongodbHostArbiter(String mongodbHostArbiter) {
        this.mongodbHostArbiter = mongodbHostArbiter;
    }

    public int getMongodbPortArbiter() {
        return mongodbPortArbiter;
    }

    public void setMongodbPortArbiter(int mongodbPortArbiter) {
        this.mongodbPortArbiter = mongodbPortArbiter;
    }

    public boolean isReadPrimary() {
        return readPrimary;
    }

    public void setReadPrimary(boolean readPrimary) {
        this.readPrimary = readPrimary;
    }


}

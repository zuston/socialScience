package me.zuston.bean;

import org.bson.BSON;
import org.bson.types.ObjectId;

/**
 * Created by zuston on 17/1/18.
 */
public class CorruptBean {
    public ObjectId objectId;
    public String resourceUrl;
    public String title;
    public int level;
    public String content;
    public String time;
    public String resource;
    public int type;

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ObjectId getObjectId() {

        return objectId;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public String getTitle() {
        return title;
    }

    public int getLevel() {
        return level;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public String getResource() {
        return resource;
    }

    public int getType() {
        return type;
    }

    public CorruptBean(String resourceUrl, String title, int level, String content, String time, String resource, int type) {

        this.resourceUrl = resourceUrl;
        this.title = title;
        this.level = level;
        this.content = content;
        this.time = time;
        this.resource = resource;
        this.type = type;
    }
}

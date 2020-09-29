package com.bezkoder.spring.files.upload.db.message;

public class ResponseFile {
    private String id;
    private String name;
    private String url;
    private String type;
    private String parent;
    private Boolean isFolder;
    private long size;

    public ResponseFile() {
    }

    public ResponseFile(String id,String name, String url, String type, String parent, Boolean isFolder, long size) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.type = type;
        this.parent = parent;
        this.isFolder = isFolder;
        this.size = size;
    }

    public ResponseFile(String name, String url, String type, String parent, Boolean isFolder, long size) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.parent = parent;
        this.isFolder = isFolder;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Boolean getIsFolder() {
        return isFolder;
    }

    public void setIsFolder(Boolean folder) {
        isFolder = folder;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

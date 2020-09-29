package com.bezkoder.spring.files.upload.db.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "file")
public class File {
    @ManyToOne
    Folder folder;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String type;
    private String parent;
    private Boolean isFolder;
    @Lob
    private byte[] data;

    public File() {
    }

    public File(String name, String type, String parent, Boolean isFolder, byte[] data) {
        this.name = name;
        this.type = type;
        this.parent = parent;
        this.isFolder = isFolder;
        this.data = data;
    }

    public File(String name, String type, String parent, Boolean isFolder, byte[] data, Folder folder) {
        this.name = name;
        this.type = type;
        this.parent = parent;
        this.isFolder = isFolder;
        this.data = data;
        this.folder = folder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}

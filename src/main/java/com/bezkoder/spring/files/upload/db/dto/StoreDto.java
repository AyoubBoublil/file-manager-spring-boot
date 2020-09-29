package com.bezkoder.spring.files.upload.db.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class StoreDto {
    MultipartFile file;
    String id;
    String parent;
    Boolean isFolder;
    String folderName;
    String newName;
    String moveToNewElementId;

}

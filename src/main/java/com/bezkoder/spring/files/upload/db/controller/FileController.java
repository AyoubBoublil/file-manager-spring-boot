package com.bezkoder.spring.files.upload.db.controller;

import com.bezkoder.spring.files.upload.db.dto.StoreDto;
import com.bezkoder.spring.files.upload.db.message.ResponseFile;
import com.bezkoder.spring.files.upload.db.model.File;
import com.bezkoder.spring.files.upload.db.model.Folder;
import com.bezkoder.spring.files.upload.db.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin("http://localhost:4200")
public class FileController {

    @Autowired
    private FileStorageService storageService;

    @PostMapping("/saveFolder")
    public ResponseEntity<?> saveFolder(@RequestBody StoreDto storeDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                storageService.saveFolder(storeDto.getFolderName(), storeDto.getParent(), storeDto.getIsFolder())
        );
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,
                                        @RequestParam("parent") String parent,
                                        @RequestParam("isFolder") Boolean isFolder) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(
                storageService.addFile(file, parent, isFolder)
        );
    }

    @PutMapping("/renameElement")
    public ResponseEntity<?> renameElement(@RequestBody StoreDto storeDto) {
        if (storeDto.getIsFolder()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    storageService.updateFolder(storeDto.getId(), storeDto.getNewName())
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    storageService.updateFile(storeDto.getId(), storeDto.getNewName())
            );
        }
    }

    @PutMapping("/deleteElement")
    public ResponseEntity<?> deleteElement(@RequestBody StoreDto storeDto) {
        if (storeDto.getIsFolder()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    storageService.deleteFolder(storeDto.getId())
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    storageService.deleteFile(storeDto.getId())
            );
        }
    }

    @PutMapping("/moveToAnotherElement")
    public ResponseEntity<?> moveToAnotherElement(@RequestBody StoreDto storeDto) {
        if (storeDto.getIsFolder()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    storageService.moveFolderToAnotherFolder(storeDto.getId(), storeDto.getMoveToNewElementId())
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    storageService.moveFileToAnotherFolder(storeDto.getId(), storeDto.getMoveToNewElementId())
            );
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().stream().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getId(),
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getParent(),
                    dbFile.getIsFolder(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        File File = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + File.getName() + "\"")
                .body(File.getData());
    }

    @GetMapping("/folders")
    public ResponseEntity<List<Folder>> getListFolders() {
        System.out.println(storageService.getAllFolders());
        List<Folder> folders = storageService.getAllFolders();

        return ResponseEntity.status(HttpStatus.OK).body(folders);
    }
}

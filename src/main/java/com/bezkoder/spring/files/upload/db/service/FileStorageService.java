package com.bezkoder.spring.files.upload.db.service;

import com.bezkoder.spring.files.upload.db.model.File;
import com.bezkoder.spring.files.upload.db.model.Folder;
import com.bezkoder.spring.files.upload.db.repository.FileRepository;
import com.bezkoder.spring.files.upload.db.repository.FolderRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileStorageService {


    private final FileRepository fileRepository;
    private final FolderRepository folderRepository;

    public FileStorageService(FileRepository fileRepository, FolderRepository folderRepository) {
        this.fileRepository = fileRepository;
        this.folderRepository = folderRepository;
    }

    public Folder saveFolder(String name, String parent, Boolean isFolder) {
        Folder folder = new Folder(name, parent, isFolder);
        return folderRepository.save(folder);
    }

    public File addFile(MultipartFile file, String parent, Boolean isFolder) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File File;
        if(!parent.equals("root")){
            Folder folder = folderRepository.findById(parent).get();
            File = new File(fileName, file.getContentType(), parent, isFolder, file.getBytes(), folder);
        } else {
            File = new File(fileName, file.getContentType(), parent, isFolder, file.getBytes());
        }

        return fileRepository.save(File);
    }

    public Folder updateFolder(String id, String newName) {
        Folder folder = folderRepository.findById(id).get();
        folder.setName(newName);
        return folderRepository.save(folder);
    }

    public Folder moveFolderToAnotherFolder(String currentElementId, String moveToNewElementId) {
        Folder folder = folderRepository.findById(currentElementId).get();
        folder.setParent(moveToNewElementId);
        return folderRepository.save(folder);
    }

    public File moveFileToAnotherFolder(String currentElementId, String moveToNewElementId) {
        File file = fileRepository.findById(currentElementId).get();
        file.setParent(moveToNewElementId);
        file.setFolder(folderRepository.findById(moveToNewElementId).get());
        return fileRepository.save(file);
    }


    public File updateFile(String id, String newName) {
        File file = fileRepository.findById(id).get();
        file.setName(newName);
        return fileRepository.save(file);
    }

    public Boolean deleteFolder(String id) {
        Folder folder = folderRepository.findById(id).get();
        folderRepository.delete(folder);
        return true;
    }


    public Boolean deleteFile(String id) {
        File file = fileRepository.findById(id).get();
        fileRepository.delete(file);
        return true;
    }

    public File getFile(String id) {
        return fileRepository.findById(id).get();
    }

    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    public List<Folder> getAllFolders() {
        return folderRepository.findAll();
    }
}

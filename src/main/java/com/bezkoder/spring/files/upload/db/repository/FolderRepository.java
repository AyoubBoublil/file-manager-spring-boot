package com.bezkoder.spring.files.upload.db.repository;

import com.bezkoder.spring.files.upload.db.model.File;
import com.bezkoder.spring.files.upload.db.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<Folder, String> {

}

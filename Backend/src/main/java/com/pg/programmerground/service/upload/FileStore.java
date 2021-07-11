package com.pg.programmerground.service.upload;

import com.pg.programmerground.dto.UploadImg;
import com.pg.programmerground.exception.FileExtractException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

/**
 * File Upload 구현체지만 각 이미지마다 저장 경로가 다르기 떄문에 상속받아 경로를 설정
 */
public abstract class FileStore {
    private static final String[] IMG_CONTENT_TYPE_LIST = {"image/png", "image/jpeg"};

    @Value("${file.dir}")
    public String fileDir;

    /**
     * 이미지 업로드 안했을 경우 default 이미지 설정
     */
    public abstract UploadImg defaultStoreFile();

    /**
     * 저장할 파일 경로
     */
    public abstract String getFullPath(String fileName);

    /**
     * 파일 저장
     */
    public UploadImg storeFile(MultipartFile imgFile) throws IOException {
        if(imgFile == null || imgFile.isEmpty()) {
            return this.defaultStoreFile();
        }
        this.checkExtract(imgFile.getContentType());
        String fileName = imgFile.getOriginalFilename();
        String storeFileName = this.createStoreFileName(fileName);
        imgFile.transferTo(new File(this.getFullPath(storeFileName)));
        return new UploadImg(fileName, storeFileName);
    }

    /**
     * 서버에 저장될 이미지 이름
     */
    public String createStoreFileName(String originalFileName) {
        String uuid = UUID.randomUUID().toString();
        String ext = this.extractExt(originalFileName);

        return uuid + "." + ext;
    }

    /**
     * 확장자 추출
     */
    public String extractExt(String originalName) {
        int pos = originalName.lastIndexOf(".");
        return originalName.substring(pos + 1);
    }

    /**
     * 확장자 체크
     */
   private void checkExtract(String contentType) {
       if(!Arrays.asList(IMG_CONTENT_TYPE_LIST).contains(contentType)) {
           throw new FileExtractException("파일 확장자는 jpeg, png만 업로드 가능");
       }
   }
}

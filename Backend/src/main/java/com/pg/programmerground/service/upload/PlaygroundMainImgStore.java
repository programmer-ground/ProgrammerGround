package com.pg.programmerground.service.upload;

import com.pg.programmerground.dto.UploadImg;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PlaygroundMainImgStore extends FileStore{
    @Value("${file.playground-main-img}")
    private String playgroundImgDir;

    @Override
    public UploadImg defaultStoreFile() {
        return new UploadImg("playground-default.png",  "playground-default.png");
    }

    /**
     * 이미지
     */
    @Override
    public String getFullPath(String fileName) {
        return this.fileDir + playgroundImgDir + fileName;
    }
}

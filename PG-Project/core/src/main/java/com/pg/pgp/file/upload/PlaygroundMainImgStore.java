package com.pg.pgp.file.upload;

import org.springframework.stereotype.Component;

@Component
public class PlaygroundMainImgStore extends FileStore{
    /*@Value("${file.playground-main-img}")*/
    private String playgroundImgDir = "test";

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

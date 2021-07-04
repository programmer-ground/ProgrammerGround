package com.pg.programmerground.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UploadImg {
    //업로드한 이미지 원본명
    private final String originalFileName;
    //서버에 저장될 이미지명
    private final String storeFileName;
}

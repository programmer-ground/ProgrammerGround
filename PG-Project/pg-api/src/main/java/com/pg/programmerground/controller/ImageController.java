package com.pg.programmerground.controller;

import com.pg.pgp.file.upload.PlaygroundMainImgStore;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {
    private final PlaygroundMainImgStore playgroundMainImgStore;

    @ApiOperation(value = "Playground Main Img File", notes = "Playground Main Img File 요청")
    @ResponseBody
    @GetMapping(value="/pgmainimg/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public Resource getMainImg(@PathVariable String fileName) throws MalformedURLException {
        return new UrlResource("file:" + playgroundMainImgStore.getFullPath(fileName));
    }

    /**
     * 추후 상세 설명에 이미지로드
     */
    @ResponseBody
    @GetMapping(value="/pgdesimg/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public Resource getDesImg(@PathVariable String fileName) {
        return null;
    }
}

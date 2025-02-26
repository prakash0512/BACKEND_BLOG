package com.prakash.blog.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest {

    @InjectMocks
    private FileServiceImpl fileService;

    @Mock
    private MultipartFile multipartFile;

    private String path = "some/path";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void uploadImage() throws IOException {
        when(multipartFile.getOriginalFilename()).thenReturn("image.jpg");

       String fileName = fileService.uploadImage(path, multipartFile);

        assertNotNull(fileName);
        assertTrue(fileName.contains(".jpg"));
    }
}

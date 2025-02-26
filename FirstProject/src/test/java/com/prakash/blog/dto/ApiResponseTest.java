package com.prakash.blog.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiResponseTest {

    @Test
    void testApiResponseConstructorAndGettersSetters() {
        ApiResponse apiResponse = new ApiResponse("Success", true);
        
        assertEquals("Success", apiResponse.getMessage());
        assertTrue(apiResponse.isSuccess());
        
        apiResponse.setMessage("Failure");
        apiResponse.setSuccess(false);
        
        assertEquals("Failure", apiResponse.getMessage());
        assertFalse(apiResponse.isSuccess());
    }
}


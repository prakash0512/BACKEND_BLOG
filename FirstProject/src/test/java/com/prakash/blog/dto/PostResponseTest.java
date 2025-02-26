package com.prakash.blog.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostResponseTest {

    @Test
    void testPostResponse() {
        PostResponse postResponse = new PostResponse();
        postResponse.setPageNumber(1);
        postResponse.setPageSize(10);
        postResponse.setTotalElements(100);
        postResponse.setTotalPages(10);
        postResponse.setLastPage(true);

        assertEquals(1, postResponse.getPageNumber());
        assertEquals(10, postResponse.getPageSize());
        assertEquals(100, postResponse.getTotalElements());
        assertTrue(postResponse.isLastPage());
    }
}

package com.tommymei.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class ColorQueueImplFullQueueTest {

    ColorQueueImpl classUnderTest;

    @Before
    public void setUp() {
        classUnderTest = new ColorQueueImpl();
        assertEquals(100, classUnderTest.size());
    }
    
    @Test
    public void testFetchOnUnusedQueue() {
        FetchableColor fetch = classUnderTest.fetch();
        assertNotNull(fetch);
        assertNotNull(fetch.getLastFetched());
        assertEquals(100, classUnderTest.size());
    }

    @Test
    public void testFetchOnUsedQueue() throws Exception {
        for (int i = 0; i < 100; i++) {
            FetchableColor fetch = classUnderTest.fetch();
            assertNotNull(fetch);
        }
        
        FetchableColor fetch = classUnderTest.fetch();
        assertNotNull(fetch);
        assertNotNull(fetch.getLastFetched());
        // 1 is generated bc all are in use
        assertEquals(101, classUnderTest.size());
    }

}

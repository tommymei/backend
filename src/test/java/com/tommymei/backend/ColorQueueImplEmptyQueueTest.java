package com.tommymei.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class ColorQueueImplEmptyQueueTest {

    ColorQueueImpl classUnderTest;

    @Before
    public void setUp() {
        classUnderTest = new ColorQueueImpl() {
            void init() {
                // intentional left blank;
            }
        };
        assertEquals(0, classUnderTest.size());
    }
    
    @Test
    public void testFetchOnEmptyQueue() {
        FetchableColor fetch = classUnderTest.fetch();
        assertNotNull(fetch);
        assertNotNull(fetch.getLastFetched());
        assertEquals(1, classUnderTest.size());
    }

    @Test
    public void testSetAfterFetchOnEmptyQueue() {
        FetchableColor fetch = classUnderTest.fetch();
        assertNotNull(fetch);
        assertEquals(1, classUnderTest.size());
        
        classUnderTest.set(fetch.getId());
        assertEquals(0, classUnderTest.size());
    }

}

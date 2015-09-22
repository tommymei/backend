package com.tommymei.backend;

import static org.apache.commons.lang.math.RandomUtils.nextInt;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class ColorQueueImplExpiredTest {

    ColorQueueImpl classUnderTest;

    @Before
    public void setUp() {
        classUnderTest = new ColorQueueImpl() {

            // setup expired entries
            void init() {
                this.colorQueue.add(new FetchableColor(UUID.randomUUID(),
                        new Color(new Rgb(nextInt(256)), new Rgb(nextInt(256)),
                                new Rgb(nextInt(256))), Instant.MIN));
            }
        };
        assertEquals(1, classUnderTest.size());
    }

    @Test
    public void testExpireEntriesAreReuse() {
        FetchableColor fetch = classUnderTest.fetch();
        assertNotNull(fetch);
        assertTrue(fetch.getLastFetched() != Instant.MIN);
        assertEquals(1, classUnderTest.size());
    }
}

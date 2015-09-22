package com.tommymei.backend;

import java.util.UUID;

public interface ColorQueue {

    /**
     * Fetch and mark as fetched
     * 
     * @return
     */
    FetchableColor fetch();
    
    /**
     * Mark that the fetched color was set.
     * 
     * RuntimeException is thrown if id is not found in Queue
     * 
     * @param id
     */
    void set(UUID id);
}

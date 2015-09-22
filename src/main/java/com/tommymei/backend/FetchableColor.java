package com.tommymei.backend;

import static com.google.common.base.Preconditions.checkNotNull;

import java.time.Instant;
import java.util.UUID;

/**
 * Random generated color than can be fetchable by the client
 * 
 * @author tommymei
 *
 */
public class FetchableColor {

    private final UUID id;
    private final Color color;
    private final Instant lastFetched;

    public FetchableColor(UUID id, Color color, Instant lastFetched) {
        checkNotNull(id, "Undefined id");
        checkNotNull(color, "Undefined color");
        this.id = id;
        this.color = color;
        this.lastFetched = lastFetched;
    }

    public UUID getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }
    
    public Instant getLastFetched() {
        return lastFetched;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        FetchableColor other = (FetchableColor) obj;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;
        return true;
    }

}

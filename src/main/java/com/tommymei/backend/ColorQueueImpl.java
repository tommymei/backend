package com.tommymei.backend;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.time.Instant.now;
import static org.apache.commons.lang.math.RandomUtils.nextInt;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.google.common.annotations.VisibleForTesting;

/**
 * This impl uses a locking concurrency model. If parallelization is key, I would try to do a non-locking solution.
 * 
 * 
 * @author tommymei
 *
 */
public class ColorQueueImpl implements ColorQueue {

    protected final ConcurrentLinkedQueue<FetchableColor> colorQueue = new ConcurrentLinkedQueue<>();

    // init the queue
    ColorQueueImpl() {
        super();
        init();
    }

    @Override
    public synchronized FetchableColor fetch() {
        FetchableColor candidate = colorQueue.peek();

        // queue is empty
        if (candidate == null) {
            return generateAndMark();
        }

        // next candidate is being used, meaning the rest after it is being used as well,
        // since this is the oldest
        Instant lastFetched = candidate.getLastFetched();
        if (lastFetched != null) {
            boolean inUse = lastFetched.plusSeconds(15).isAfter(now());
            if (inUse) {
                return generateAndMark();
            }
        }

        // candidate is good, update the last fetched
        // -- remove it first
        colorQueue.remove(candidate);
        FetchableColor updated = new FetchableColor(candidate.getId(),
                candidate.getColor(), now());
        this.colorQueue.add(updated);
        return updated;
    }

    @Override
    public synchronized void set(UUID id) {
        checkNotNull(id, "Undefined id");
        // identity is just base on id, other values do not matter
        FetchableColor fc = new FetchableColor(id, new Color(new Rgb(0),
                new Rgb(0), new Rgb(0)), null);

        // may not be optimal on a large queue since it has to search through
        // the whole queue to remove it.
        // If performance is key, I would spend some time thinking of a better data
        // structure for this
        boolean result = this.colorQueue.remove(fc);
        if (result == false) {
            throw new RuntimeException(String.format("id not found %s", id));
        }
    }

    private FetchableColor generateAndMark() {
        FetchableColor rand = generate();
        FetchableColor candidate = new FetchableColor(rand.getId(),
                rand.getColor(), now());
        this.colorQueue.add(candidate);
        return candidate;

    }

    private FetchableColor generate() {
        return new FetchableColor(UUID.randomUUID(), new Color(new Rgb(
                nextInt(256)), new Rgb(nextInt(256)), new Rgb(nextInt(256))),
                null);
    }

    @VisibleForTesting
    void init() {
        for (int i = 0; i < 100; i++) {
            this.colorQueue.add(generate());
        }
    }

    @VisibleForTesting
    int size() {
        return this.colorQueue.size();
    }

}

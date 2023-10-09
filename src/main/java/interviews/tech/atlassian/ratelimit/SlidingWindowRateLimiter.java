package interviews.tech.atlassian.ratelimit;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class SlidingWindowRateLimiter implements RateLimiter {
    private final RateLimiterConfig config;
    private final Queue<Long> slidingWindow;

//    private ReentrantReadWriteLock readWriteLock;
//    ReentrantReadWriteLock.ReadLock readLock;
//    ReentrantReadWriteLock.WriteLock writeLock;

    public SlidingWindowRateLimiter(RateLimiterConfig config){
        this.config = config;
        slidingWindow = new ConcurrentLinkedQueue<>();
//        readWriteLock = new ReentrantReadWriteLock(true);
//        this.readLock = readWriteLock.readLock();
//        this.writeLock = readWriteLock.writeLock();
    }

    @Override
    public synchronized boolean allow() {
        Long currentTime = System.currentTimeMillis();

//        writeLock.lock();

        updateSlidingWindow(currentTime);


        if (slidingWindow.size() < config.getMaxCallsAllowed()) {
            slidingWindow.offer(currentTime);
//            writeLock.unlock();
            return true;
        }

//        writeLock.unlock();

        return false;
    }

    private void updateSlidingWindow(Long currentTimeInMillis){


        //Slide Window based on current time
        while (
                slidingWindow.size() > 0
                        && (currentTimeInMillis - slidingWindow.peek()) / 1000 > config.getWindowSizeInSeconds()
        ) {
            slidingWindow.poll();
        }


    }
}

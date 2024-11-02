package interviews.tech.companies.atlassian.ratelimit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GenericBucket<T> {
    Map<T, RateLimiter> bucket;

    public GenericBucket(T key, RateLimiterConfig config) {
        bucket = new ConcurrentHashMap<>();
        bucket.put(key, new SlidingWindowRateLimiter(config));
    }

    public void tryPerformAction(T key) {
        if (bucket.get(key).allow()) {
            System.out.println(Thread.currentThread().getName() + " -> able to access the application");
        } else {
            System.out.println(Thread.currentThread().getName() + " -> Too many request, Please try after some time");
        }
    }

//    public void tryPerformAction(Supplier<String> callback, T key) {
//        if (bucket.get(key).allow()) {
//             callback.get();
//
//        } else {
//            System.out.println(Thread.currentThread().getName() + " -> Too many request, Please try after some time");
//        }
//    }
}

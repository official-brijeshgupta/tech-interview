package interviews.tech.atlassian.ratelimit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RateLimiterExample {
    public static void execute(String[] args) {
        RateLimiterConfig config = new RateLimiterConfig(10, 1);
        UserBucket userBucket = new UserBucket(1L, config);

        ExecutorService executorService = Executors.newFixedThreadPool(12);

        for (int i = 0; i < 12; i++) {
            executorService.execute(() -> userBucket.tryPerformAction(1L));
        }

        executorService.shutdown();

    }

    private static void work(String str){
        System.out.println(Thread.currentThread().getName() + " -> able to access the application");
    }
}

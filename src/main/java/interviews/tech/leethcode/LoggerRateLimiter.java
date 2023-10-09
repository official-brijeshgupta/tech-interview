package interviews.tech.leethcode;

import org.springframework.data.util.Pair;

import java.util.*;

/**
 * 359. Logger Rate Limiter
 * Design a logger system that receives a stream of messages along with their timestamps. Each unique message should only be printed at most every 10 seconds (i.e. a message printed at timestamp t will prevent other identical messages from being printed until timestamp t + 10).
 *
 * All messages will come in chronological order. Several messages may arrive at the same timestamp.
 *
 * Implement the Logger class:
 *
 * Logger() Initializes the logger object.
 * bool shouldPrintMessage(int timestamp, string message) Returns true if the message should be printed in the given timestamp, otherwise returns false.
 *
 *
 * Example 1:
 *
 * Input
 * ["Logger", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage"]
 * [[], [1, "foo"], [2, "bar"], [3, "foo"], [8, "bar"], [10, "foo"], [11, "foo"]]
 * Output
 * [null, true, true, false, false, false, true]
 *
 * Explanation
 * Logger logger = new Logger();
 * logger.shouldPrintMessage(1, "foo");  // return true, next allowed timestamp for "foo" is 1 + 10 = 11
 * logger.shouldPrintMessage(2, "bar");  // return true, next allowed timestamp for "bar" is 2 + 10 = 12
 * logger.shouldPrintMessage(3, "foo");  // 3 < 11, return false
 * logger.shouldPrintMessage(8, "bar");  // 8 < 12, return false
 * logger.shouldPrintMessage(10, "foo"); // 10 < 11, return false
 * logger.shouldPrintMessage(11, "foo"); // 11 >= 11, return true, next allowed timestamp for "foo" is 11 + 10 = 21
 */
public class LoggerRateLimiter {
    public static void main(String[] arg) {
        Logger logger = new Logger();



        System.out.println(logger.shouldPrintMessage(1, "foo"));
        System.out.println(logger.shouldPrintMessage(2, "bar"));
        System.out.println(logger.shouldPrintMessage(3, "foo"));
        System.out.println(logger.shouldPrintMessage(8, "bar"));
        System.out.println(logger.shouldPrintMessage(10, "foo"));
        System.out.println(logger.shouldPrintMessage(11, "foo"));

    }


}

class Logger {

    private final Set<String> uniqueMessages;
    private final Deque<Pair<Integer,String>> messageQueue;

    public Logger() {
        uniqueMessages = new HashSet<>();
        messageQueue = new LinkedList<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        cleanExpiredMessages(timestamp);

        if(!uniqueMessages.contains(message)) {
            messageQueue.offer(Pair.of(timestamp, message));
            uniqueMessages.add(message);
            return true;
        }

        return false;
    }

    private void cleanExpiredMessages(int timestamp){

        Pair<Integer, String> curr = messageQueue.peek();
        while(curr != null && (timestamp - curr.getFirst()) >= 10) {
            Pair<Integer, String> log = messageQueue.poll();
            uniqueMessages.remove(log.getSecond());
            curr = messageQueue.peek();
        }

    }
}

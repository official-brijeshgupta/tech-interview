package interviews.tech.karat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Suppose we have an unsorted log file of accesses to web resources. Each log entry consists of an access time, the ID of the user making the access, and the resource ID.
 * <p>
 * The access time is represented as seconds since 00:00:00, and all times are assumed to be in the same day.
 * <p>
 * Example:
 * logs1 = [
 * ["58523", "user_1", "resource_1"],
 * ["62314", "user_2", "resource_2"],
 * ["54001", "user_1", "resource_3"],
 * ["200", "user_6", "resource_5"],
 * ["215", "user_6", "resource_4"],
 * ["54060", "user_2", "resource_3"],
 * ["53760", "user_3", "resource_3"],
 * ["58522", "user_22", "resource_1"],
 * ["53651", "user_5", "resource_3"],
 * ["2", "user_6", "resource_1"],
 * ["100", "user_6", "resource_6"],
 * ["400", "user_7", "resource_2"],
 * ["100", "user_8", "resource_6"],
 * ["54359", "user_1", "resource_3"],
 * ]
 * <p>
 * Example 2:
 * logs2 = [
 * ["300", "user_1", "resource_3"],
 * ["599", "user_1", "resource_3"],
 * ["900", "user_1", "resource_3"],
 * ["1199", "user_1", "resource_3"],
 * ["1200", "user_1", "resource_3"],
 * ["1201", "user_1", "resource_3"],
 * ["1202", "user_1", "resource_3"]
 * ]
 * <p>
 * Example 3:
 * logs3 = [
 * ["300", "user_10", "resource_5"]
 * ]
 * <p>
 * Write a function that takes the logs and returns the resource with the highest number of accesses in any 5 minute window, together with how many accesses it saw.
 * <p>
 * Expected Output:
 * most_requested_resource(logs1) # => ('resource_3', 3) [resource_3 is accessed at 53760, 54001, and 54060]
 * most_requested_resource(logs2) # => ('resource_3', 4) [resource_3 is accessed at 1199, 1200, 1201, and 1202]
 * most_requested_resource(logs3) # => ('resource_5', 1) [resource_5 is accessed at 300]
 * <p>
 * Complexity analysis variables:
 * <p>
 * n: number of logs in the input
 **/

public class FindResourceUsageFromLogs {
    public static void execute() {

        String[][] logs1 = {
                {"58523", "user_1", "resource_1"},
                {"62314", "user_2", "resource_2"},
                {"54001", "user_1", "resource_3"},
                {"200", "user_6", "resource_5"},
                {"215", "user_6", "resource_4"},
                {"54060", "user_2", "resource_3"},
                {"53760", "user_3", "resource_3"},
                {"58522", "user_22", "resource_1"},
                {"53651", "user_5", "resource_3"},
                {"2", "user_6", "resource_1"},
                {"100", "user_6", "resource_6"},
                {"400", "user_7", "resource_2"},
                {"100", "user_8", "resource_6"},
                {"54359", "user_1", "resource_3"},
        };

        String[][] logs2 = {
                {"300", "user_1", "resource_3"},
                {"599", "user_1", "resource_3"},
                {"900", "user_1", "resource_3"},
                {"1199", "user_1", "resource_3"},
                {"1200", "user_1", "resource_3"},
                {"1201", "user_1", "resource_3"},
                {"1202", "user_1", "resource_3"}
        };

        String[][] logs3 = {{"300", "user_10", "resource_5"}};

        UsageRecord result = FindMostUsedResourceWithinATimeFrame(logs1);
        UsageRecord result2 = FindMostUsedResourceWithinATimeFrame(logs2);
        UsageRecord result3 = FindMostUsedResourceWithinATimeFrame(logs3);

        if (result != null) {
            System.out.println(result.resource + "->" + result.count);
        }

        if (result2 != null) {
            System.out.println(result2.resource + "->" + result2.count);
        }

        if (result3 != null) {
            System.out.println(result3.resource + "->" + result3.count);
        }

    }


    private static UsageRecord FindMostUsedResourceWithinATimeFrame(String[][] logs) {

        //Sort based on time
        Arrays.sort(logs, (a, b) -> Integer.compare(getTime(a), getTime(b)));

        int secondsInFiveMinutes = 60 * 5;

        UsageRecord globalMax = null;
        for (int i = 0; i < logs.length; i++) {
            UsageRecord currentMax = findMaxUsageInGivenInterval(logs, i, secondsInFiveMinutes);
            if (currentMax != null) {
                if (globalMax == null || globalMax.count < currentMax.count) {
                    globalMax = currentMax;
                }
            }
        }
        return globalMax;
    }

    private static UsageRecord findMaxUsageInGivenInterval(String[][] logs, int startIndex, int timeInterval) {
        int startTime = getTime(logs[startIndex]);
        int endTime = startTime + timeInterval;
        int currentTime = 0;
        HashMap<String, Integer> frequency = new HashMap<>();
        for (int i = startIndex; i < logs.length && currentTime <= endTime; i++) {
            String[] log = logs[i];
            String resource = log[2];
            currentTime = getTime(log);

            if (frequency.containsKey(resource)) {
                frequency.put(resource, frequency.get(resource) + 1);
            } else {
                frequency.put(resource, 1);
            }
        }

        UsageRecord result = null;


        for (Map.Entry<String, Integer> f : frequency.entrySet()) {
            if (result == null) {
                result = new UsageRecord(f.getKey(), f.getValue());
            } else if (result.count < f.getValue()) {
                result = new UsageRecord(f.getKey(), f.getValue());
            }
        }

        return result;
    }

    private static int getTime(String[] log) {
        return Integer.parseInt((log[0]));
    }

}

class UsageRecord {
    public int count;
    public String resource;

    public UsageRecord(String resource, int count) {
        this.count = count;
        this.resource = resource;
    }
}

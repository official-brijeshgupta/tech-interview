package interviews.tech.leethcode;

import org.springframework.data.util.Pair;

import java.util.*;

/**
 * You are given a stream of records about a particular stock. Each record contains a timestamp and the corresponding price of the stock at that timestamp.
 *
 * Unfortunately due to the volatile nature of the stock market, the records do not come in order. Even worse, some records may be incorrect. Another record with the same timestamp may appear later in the stream correcting the price of the previous wrong record.
 *
 * Design an algorithm that:
 *
 * Updates the price of the stock at a particular timestamp, correcting the price from any previous records at the timestamp.
 * Finds the latest price of the stock based on the current records. The latest price is the price at the latest timestamp recorded.
 * Finds the maximum price the stock has been based on the current records.
 * Finds the minimum price the stock has been based on the current records.
 * Implement the StockPrice class:
 *
 * StockPrice() Initializes the object with no price records.
 * void update(int timestamp, int price) Updates the price of the stock at the given timestamp.
 * int current() Returns the latest price of the stock.
 * int maximum() Returns the maximum price of the stock.
 * int minimum() Returns the minimum price of the stock.
 *
 *
 * Example 1:
 *
 * Input
 * ["StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update", "minimum"]
 * [[], [1, 10], [2, 5], [], [], [1, 3], [], [4, 2], []]
 * Output
 * [null, null, null, 5, 10, null, 5, null, 2]
 *
 * Explanation
 * StockPrice stockPrice = new StockPrice();
 * stockPrice.update(1, 10); // Timestamps are [1] with corresponding prices [10].
 * stockPrice.update(2, 5);  // Timestamps are [1,2] with corresponding prices [10,5].
 * stockPrice.current();     // return 5, the latest timestamp is 2 with the price being 5.
 * stockPrice.maximum();     // return 10, the maximum price is 10 at timestamp 1.
 * stockPrice.update(1, 3);  // The previous timestamp 1 had the wrong price, so it is updated to 3.
 *                           // Timestamps are [1,2] with corresponding prices [3,5].
 * stockPrice.maximum();     // return 5, the maximum price is 5 after the correction.
 * stockPrice.update(4, 2);  // Timestamps are [1,2,4] with corresponding prices [3,5,2].
 * stockPrice.minimum();     // return 2, the minimum price is 2 at timestamp 4.
 *
 *
 * Constraints:
 *
 * 1 <= timestamp, price <= 109
 * At most 105 calls will be made in total to update, current, maximum, and minimum.
 * current, maximum, and minimum will be called only after update has been called at least once.
 */
public class StockPriceFluctuation {
    public static void main(String[] args) {
//        StockPrice stockPrice = new StockPrice();
//        stockPrice.update(1, 1);
//        stockPrice.update(2, 2);
//        stockPrice.update(3, 3);
//        stockPrice.update(4, 4);
//        stockPrice.update(2, 10);
//        System.out.println("Max " + stockPrice.maximum());
//        System.out.println("Min " + stockPrice.minimum());
//        System.out.println("Curr " + stockPrice.current());
//        stockPrice.update(2, 11);
//        System.out.println("Curr " + stockPrice.maximum());

        StockPrice2 stockPrice2 = new StockPrice2();
        stockPrice2.update(1, 1);
        stockPrice2.update(2, 2);
        stockPrice2.update(3, 3);
        stockPrice2.update(4, 4);
        stockPrice2.update(2, 10);
        System.out.println("Max " + stockPrice2.maximum());
        System.out.println("Min " + stockPrice2.minimum());
        System.out.println("Curr " + stockPrice2.current());
        stockPrice2.update(2, 11);
        System.out.println("Curr " + stockPrice2.maximum());
    }

}

//HashMap with SortedMap
class StockPrice {
    private int latestTime;
    private final Map<Integer, Integer> timeToValueMap;
    private final TreeMap<Integer, Integer> valueToFrequencyMap;

    public StockPrice() {
        latestTime = 0;
        timeToValueMap = new HashMap<>();
        valueToFrequencyMap = new TreeMap<>();
    }

    public void update(int timestamp, int price) {
        //Update LatestTime
        latestTime = Math.max(latestTime, timestamp);

        //Remove existing record
        if(timeToValueMap.containsKey(timestamp)){
            //Get old entry
            int oldValue = timeToValueMap.get(timestamp);

            //reduce frequency based on old entry
            valueToFrequencyMap.put(oldValue, valueToFrequencyMap.get(oldValue) - 1);

            //Remove if frequency of old entry is zero
            if(valueToFrequencyMap.get(oldValue)==0){
                valueToFrequencyMap.remove(oldValue);
            }
        }

        //Add new time entry
        timeToValueMap.put(timestamp, price);

        //increase frequency of the already existed or new value.
        valueToFrequencyMap.put(price, valueToFrequencyMap.getOrDefault(price, 0) + 1);
    }

    public int current() {
        return timeToValueMap.get(latestTime);
    }

    public int maximum() {
        return valueToFrequencyMap.lastKey();
    }

    public int minimum() {
        return valueToFrequencyMap.firstKey();
    }
}

//HashMap with Min and Max Heap
class StockPrice2 {

    private int latestTime;
    private final Map<Integer, Integer> timeToValueMap;
    private final PriorityQueue<Pair<Integer, Integer>> min;
    private final PriorityQueue<Pair<Integer, Integer>> max;

    public StockPrice2() {
        latestTime = 0;
        timeToValueMap = new HashMap<>();
        min = new PriorityQueue<>((a, b) -> a.getFirst() - b.getFirst());
        max = new PriorityQueue<>((a, b) -> b.getFirst() - a.getFirst());

    }


    public void update(int timestamp, int price) {
        latestTime = Math.max(latestTime, timestamp);

        //Add/Update the new timeStamp value
        timeToValueMap.put(timestamp, price);

        //O(Log n) - heapify function
        min.add(Pair.of(price, timestamp));
        max.add(Pair.of(price, timestamp));
    }

    public int current() {
        return timeToValueMap.get(latestTime);
    }

    public int maximum() {
        if(max.size()==0) return 0;

        Pair<Integer, Integer> valueTimePair = max.peek();
        while(!Objects.equals(timeToValueMap.get(valueTimePair.getSecond()), valueTimePair.getFirst())){
            max.poll();
            valueTimePair = max.peek();
        }

        return valueTimePair.getFirst();
    }

    public int minimum() {
        if(min.size()==0) return 0;

        Pair<Integer, Integer> valueTimePair = min.peek();
        while(!Objects.equals(timeToValueMap.get(valueTimePair.getSecond()), valueTimePair.getFirst())){
            min.poll();
            valueTimePair = min.peek();
        }

        return valueTimePair.getFirst();
    }
}

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */

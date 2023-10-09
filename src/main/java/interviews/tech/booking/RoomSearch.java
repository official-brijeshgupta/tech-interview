package interviews.tech.booking;

import java.util.*;
import java.util.stream.Collectors;

/**
 * {
 *
 * 	176 : 	[
 *                                {
 * 					"price" : 120,
 * 					"features" : ["breakfast", "refundable"],
 * 					"availbility" : 5
 *                }
 * 			],
 * 	177 : 	[
 *                {
 * 					"price" : 130,
 * 					"features" : ["breakfast"],
 * 					"availbility" : 1
 *                },
 *                {
 * 					"price" : 140,
 * 					"features" : ["breakfast", "refundable", "wifi"],
 * 					"availbility" : 3
 *                }
 * 			],
 * 	178 : 	[
 *                {
 * 					"price" : 130,
 * 					"features" : ["breakfast"],
 * 					"availbility" : 2
 *                },
 *                {
 * 					"price" : 140,
 * 					"features" : ["breakfast", "refundable", "wifi"],
 * 					"availbility" : 10
 *                }
 * 			]
 * }
 *
 * I/P =>
 *
 * {
 * 	"checkin" : 176,
 * 	"checkout" : 178,
 * 	"features" : ["breakfast"]
 * 	"rooms"    : 1
 *
 * }
 *
 * O/P
 * [
 *        {
 * 		"price" : 250,
 * 		"features" : ["breakfast"],
 * 		"availbility" : 1
 *    },
 *    {
 * 		"price" : 260,
 * 		"features" : ["breakfast", "refundable"],
 * 		"availbility" : 3
 *    }
 * ]
 */
public class RoomSearch {

    public static void main(String[] args) {

        test1();
    }

    private static void test1() {


        Map<Integer, List<Room>> inventory = new HashMap<>();

        inventory.put(176,
                List.of(
                        new Room(120, 5, new String[]{"breakfast", "refundable"})
                )
        );

        inventory.put(177,
                List.of(
                        new Room(130, 1, new String[]{"breakfast"}),
                        new Room(140, 3, new String[]{"breakfast", "refundable", "wifi"})
                )
        );

        inventory.put(178,
                List.of(
                        new Room(130, 2, new String[]{"breakfast"}),
                        new Room(140, 10, new String[]{"breakfast", "refundable", "wifi"})
                )
        );

        SearchCriteria searchCriteria = new SearchCriteria(176, 178, new String[]{"breakfast"}, 1);

        List<Room> result = search(inventory, searchCriteria);

        print(result);
    }

    private static void print(List<Room> list){
        for (Room room:list){
            System.out.println(room.toString());
        }
    }

    private static List<Room> search(Map<Integer, List<Room>> inventory, SearchCriteria searchCriteria) {
        int totalNights = searchCriteria.checkOut - searchCriteria.checkIn;

        List<Room> result = new ArrayList<>();
        for (int i = 0; i < totalNights; i++) {
            List<Room> availableRooms = inventory
                    //find rooms available within travel date i.e. check in date to 1 days less than checkout date
                    .getOrDefault(searchCriteria.checkIn + i, new ArrayList<>())
                    .stream()
                    //filter with required availability
                    .filter(x -> availabilityFilter(x, searchCriteria))
                    // filter with required features
                    .filter(x -> featureFilter(x, searchCriteria))
                    .collect(Collectors.toList());

            if(availableRooms.isEmpty()) return List.of();

            result = merge(availableRooms, result);
        }

        return result;
    }

    private static boolean availabilityFilter(Room room, SearchCriteria searchCriteria) {
        return room.availability >= searchCriteria.rooms;
    }

    private static boolean featureFilter(Room room, SearchCriteria searchCriteria) {
        return Arrays.asList(room.features).containsAll(Arrays.asList(searchCriteria.features));
    }

    private static List<Room> merge(List<Room> list1, List<Room> list2) {
        if(list1.size()==0) return list2;
        if(list2.size()==0) return list1;

        List<Room> result = new ArrayList<>();
        for (Room room1 : list1) {
            for (Room room2 : list2) {
                result.add(new Room(
                        room1.price + room2.price,
                        Math.min(room1.availability, room2.availability),
                        findCommonFeatures(room1.features, room2.features)
                ));
            }
        }

        return result;
    }

    private static String[] findCommonFeatures(String[] arr1, String[] arr2){
        Set<String> set1 = Arrays.stream(arr1).collect(Collectors.toSet());
        return Arrays.stream(arr2)
                .filter(x -> set1.contains(x))
                .toArray(String[]::new);
    }
}

class Room {
    int price;
    int availability;
    String[] features;

    public Room(int price, int availability, String[] features) {
        this.price = price;
        this.availability = availability;
        this.features = features;
    }

    @Override
    public String toString() {
        return "Room{" +
                "price=" + price +
                ", features=" + Arrays.toString(features) +
                ", availability=" + availability +
                '}';
    }
}

class SearchCriteria {
    int checkIn;
    int checkOut;
    String[] features;
    int rooms;

    public SearchCriteria(int checkIn, int checkOut, String[] features, int rooms) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.features = features;
        this.rooms = rooms;
    }
}

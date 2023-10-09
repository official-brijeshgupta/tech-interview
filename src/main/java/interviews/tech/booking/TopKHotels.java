//package interviews.tech.booking;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.stream.Collectors;
//
///**
// * {
// *
// * 	176 : 	[
// *                                {
// * 					"price" : 120,
// * 					"features" : ["breakfast", "refundable"],
// * 					"availbility" : 5
// *                }
// * 			],
// * 	177 : 	[
// *                {
// * 					"price" : 130,
// * 					"features" : ["breakfast"],
// * 					"availbility" : 1
// *                },
// *                {
// * 					"price" : 140,
// * 					"features" : ["breakfast", "refundable", "wifi"],
// * 					"availbility" : 3
// *                }
// * 			],
// * 	178 : 	[
// *                {
// * 					"price" : 130,
// * 					"features" : ["breakfast"],
// * 					"availbility" : 2
// *                },
// *                {
// * 					"price" : 140,
// * 					"features" : ["breakfast", "refundable", "wifi"],
// * 					"availbility" : 10
// *                }
// * 			]
// * }
// *
// * I/P =>
// *
// * {
// * 	"checkin" : 176,
// * 	"checkout" : 178,
// * 	"features" : ["breakfast"]
// * 	"rooms"    : 1
// *
// * }
// *
// * O/P
// * [
// *        {
// * 		"price" : 250,
// * 		"features" : ["breakfast"],
// * 		"availbility" : 1
// *    },
// *    {
// * 		"price" : 260,
// * 		"features" : ["breakfast", "refundable"],
// * 		"availbility" : 3
// *    }
// * ]
// */
//
//public class TopKHotels {
//
//    public static void main(String[] args) {
//
//    }
//
//    private static void test1(){
//        Map<Integer, List<Room2>> inventory = new HashMap<>();
//
//        inventory.put(176,
//                List.of(
//                        new Room2(120d, 5, Set.of("breakfast", "refundable"))
//                )
//        );
//
//        inventory.put(177,
//                List.of(
//                        new Room2(130d, 1, Set.of("breakfast")),
//                        new Room2(140d, 3, Set.of("breakfast", "refundable", "wifi"))
//                )
//        );
//
//        inventory.put(178,
//                List.of(
//                        new Room2(130d, 2,Set.of("breakfast")),
//                        new Room2(140d, 10, Set.of("breakfast", "refundable", "wifi"))
//                )
//        );
//
//        SearchCriteria2 searchCriteria = new SearchCriteria2(176, 178, 1, Set.of("breakfast"));
//
//    }
//
//    private static List<Room2> search(Map<Integer, List<Room2>> inventory, SearchCriteria2 searchCriteria){
//        int totalNights = searchCriteria.getCheckOutDate() - searchCriteria.getCheckInDate();
//        for (int i=0; i<totalNights; i++) {
//            List<Room2> matchingRoom = inventory
//                    .getOrDefault(searchCriteria.getCheckInDate() + i, List.of())
//                    .stream()
//                    .filter(x -> filterByAvailability(x, searchCriteria.getNumberOfRooms()))
//                    .filter(x -> filterByFeatures(x, searchCriteria.getFeatures()))
//                    .collect(Collectors.toList());
//
//            if(matchingRoom.isEmpty()) return List.of();
//        }
//    }
//
//    private static boolean filterByAvailability(Room2 room, int numberOfRooms){
//        return room.getAvailability() >= numberOfRooms;
//    }
//
//    private static boolean filterByFeatures(Room2 room, Set<String> features){
//        return room.getFeatures().containsAll(features);
//    }
//
//
//
//
//
//}
//
//class Room2{
//    private final int availability;
//    private final Set<String> features;
//    private final double price;
//
//    public Room2(double price, int availability, Set<String> features){
//        this.availability = availability;
//        this.price = price;
//        this.features = features;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public int getAvailability() {
//        return availability;
//    }
//
//    public Set<String> getFeatures() {
//        return features;
//    }
//}
//
//class SearchCriteria2{
//    private final int checkInDate;
//    private final int checkOutDate;
//    private final int numberOfRooms;
//    private final Set<String> features;
//
//    public SearchCriteria2(int checkInDate, int checkOutDate,int numberOfRooms, Set<String> features){
//        this.checkInDate = checkInDate;
//        this.checkOutDate = checkOutDate;
//        this.numberOfRooms = numberOfRooms;
//        this.features = features;
//    }
//
//    public int getCheckInDate() {
//        return checkInDate;
//    }
//
//    public int getCheckOutDate() {
//        return checkOutDate;
//    }
//
//    public Set<String> getFeatures() {
//        return features;
//    }
//
//    public int getNumberOfRooms() {
//        return numberOfRooms;
//    }
//}

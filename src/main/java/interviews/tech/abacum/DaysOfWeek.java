package interviews.tech.abacum;

import org.springframework.data.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Days fo the week are represented as three-letter string
 * {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"}
 * Write a function that, given a day of the Week S, and an integer K,
 * return days of the week that is K days later from S
 */
public class DaysOfWeek {
    public static void main(String[] args){

        List<Pair<Pair<String, Integer>, String>> testCases = List.of(
            Pair.of(Pair.of("Mon", 4), "Fri"),
            Pair.of(Pair.of("Sun", 21), "Sun")
        );

        for (Pair<Pair<String, Integer>, String> testCase: testCases){
            Pair<String, Integer> input = testCase.getFirst();
            String expectedOutput = testCase.getSecond();
            String actualOutput = solution(input.getFirst(), input.getSecond());
            if (actualOutput.equals(expectedOutput)) {
                System.out.println("Passed");
            }else{
                System.out.printf("Failed. %s != %s%n", actualOutput, expectedOutput);
            }
        }



    }

    private static String solution(String s, int k) {
        if (k <= 0) return "";

        Map<Integer, String> dayNumberToDayNameMap = new HashMap<>(
                Map.of(
                        1, "Mon",
                        2, "Tue",
                        3, "Wed",
                        4, "Thu",
                        5, "Fri",
                        6, "Sat",
                        7, "Sun"
                )
        );
        Map<String, Integer> dayNameToDayNumberMap = new HashMap<>(
                Map.of(
                        "Mon", 1,
                        "Tue", 2,
                        "Wed", 3,
                        "Thu", 4,
                        "Fri", 5,
                        "Sat", 6,
                        "Sun", 7
                )
        );


        int dayIndexOfGivenDay = dayNameToDayNumberMap.get(s);

        int indexOfRequiredDay = k + dayIndexOfGivenDay;
        int optimizedIndexOfRequiredDay = indexOfRequiredDay % 7;

        if (optimizedIndexOfRequiredDay == 0) optimizedIndexOfRequiredDay = 7;

        return dayNumberToDayNameMap.get(optimizedIndexOfRequiredDay);
    }


}

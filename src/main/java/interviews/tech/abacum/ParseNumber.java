package interviews.tech.abacum;

import org.springframework.data.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.Set;

public class ParseNumber {
    public static void main(String[] arg) {
//        readLines();
        List<String> inputs = List.of(
                "+0",
                "00",
                "2u1",
                "-0",
                "  +5",
                "++5",
                "   123",
                "   0123",
                "   1 23",
                "1.23",
                "1000000000",
                "1000000001",
                "999999999",
                "+1000000000",
                "1000000001",
                "-999999999",
                "2147483647",
                "FIVE"
        );

//        for (String input: inputs){
//            Pair<Boolean, Integer> output = tryParse(input);
//            if(output.getFirst()){
//                System.out.printf("Valid - %s -> %s%n", input, output.getSecond());
//            }else{
//                System.out.printf("Invalid - %s%n", input);
//            }
//
//        }

        for (String input: inputs){
            String output = parseNumber(input);
            if(output.length()!=0){
                System.out.printf("Valid - %s -> %s%n", input, output);
            }else{
                System.out.printf("Invalid - %s%n", input);
            }

        }
    }

    private static void readLines(Reader reader) {
        // File path is passed as parameter

        File file = new File(
                "/Users/devil/Documents/Codebase/programming/src/main/resources/test.text");

        // Note:  Double backquote is to avoid compiler
        // interpret words
        // like \test as \t (ie. as a escape sequence)

        try {
            // Creating an object of BufferedReader class
            BufferedReader br
                    = new BufferedReader(new FileReader(file));

            // Declaring a string variable
            String st;
            // Condition holds true till
            // there is character in a string
            while ((st = br.readLine()) != null)

                // Print the string
                System.out.println(st);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private static Pair<Boolean, Integer> parse(String s) {

        s = s.trim();

        Set<Character> validPrefix = Set.of('+', '-');
        String validSuffix = ""; // There should not be any suffix after trim

        int maxLengthOfMiddlePart = 10;

        int endIndexOfPrefix = 0;

        StringBuilder sb = new StringBuilder();




        for(int i=0; i< s.length()-1; i++){ // there should be atleast 1 digit

        }

        try{
            int parsedNumber = Integer.parseInt(s);

            if(parsedNumber > 1000000000 || parsedNumber < -1000000000 ){
                return Pair.of(false, 0);
            } else{
                return Pair.of(true, parsedNumber);
            }
        }catch (Exception ex){
            return Pair.of(false, 0);
        }

    }

    //Optimized



    private static Pair<String, Integer> getPrefix(String s){
        if(s.charAt(0) == '-'){
            return Pair.of("-", 1);
        }

        if(s.charAt(0) == '+'){
            return Pair.of("", 1);
        }

        return Pair.of("", 0);
    }

    private static String parseNumber(String s){
        s = s.trim();
        int lengthOfString = s.length();
        Set<String> validDigits = Set.of("1","2","3","4","5","6","7","8","9");

        Pair<String, Integer> prefixResult = getPrefix(s);

        int startIndexOfNumber = prefixResult.getSecond();
        String numberSign = prefixResult.getFirst();
        int remainingLength = lengthOfString - (startIndexOfNumber - 0); // length of prefix

        // No number to parse
        if(remainingLength == 0) return "";

        String numberValue = s.substring(startIndexOfNumber, startIndexOfNumber + remainingLength);

        if(remainingLength == 1){
            return
                    validDigits.contains(numberValue) || numberValue.equals("0")
                            ?  formNumber(numberSign, numberValue)
                            : "";
        } else if(remainingLength == 10){
           return numberValue.equals("1000000000") ?  formNumber(numberSign, numberValue) :"";
        } else if(remainingLength > 10)
            return "";
        else {
            for (int i = startIndexOfNumber; i < remainingLength; i++) {
                if (!validDigits.contains(numberValue.substring(i,i+1))){
                    return "";
                }
            }
        }

        return formNumber(numberSign, numberValue);
    }

    private static String formNumber(String prefix, String number){
        if(number.equals("0")) return number;

        return prefix + number;
    }

    private static Pair<Boolean, Integer> tryParse(String s) {

        s = s.trim();

        try{
            int parsedNumber = Integer.parseInt(s);

            if(parsedNumber > 1000000000 || parsedNumber < -1000000000 ){
                return Pair.of(false, 0);
            } else{
                return Pair.of(true, parsedNumber);
            }
        }catch (Exception ex){
            return Pair.of(false, 0);
        }

    }
}



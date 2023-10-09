package interviews.tech.atlassian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode https://leetcode.com/problems/rank-teams-by-votes/
 * In a special ranking system, each voter gives a rank from highest to lowest to all teams participated
 * in the competition.
 *
 * The ordering of teams is decided by who received the most position-one votes.
 * If two or more teams tie in the first position, we consider the second position to resolve the conflict,
 * if they tie again, we con tinue this process until the ties are resolved. If two or more teams are still tied
 * after considering all positions, we rank them alphabetically based on their team letter.
 *
 * Given an array of strings votes which is the votes of all voters in the ranking systems. Sort all teams according
 * to the ranking system described above.
 *
 * Return a string of all teams sorted by the ranking system.
 * Example 1:
 * Input: votes = ["ABC","ACB","ABC","ACB","ACB"]
 * Output: "ACB"
 * Explanation: Team A was ranked first place by 5 voters.
 * No other team was voted as first place so team A is the first team.
 * Team B was ranked second by 2 voters and was ranked third by 3 voters.
 * Team C was ranked second by 3 voters and was ranked third by 2 voters.
 * As most of the voters ranked C second, team C is the second team and team B is the third.
 *
 */
public class RankTeamByVotes {

    public static void main(String[] args) {

        List<String[]> inputs = new ArrayList<>();
        inputs.add(new String[]{"ABC", "ACB", "ABC", "ACB", "ACB"});
        inputs.add(new String[]{"WXYZ","XYZW"});
        inputs.add(new String[]{"ZMNAGUEDSJYLBOPHRQICWFXTVK"});

        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("ACB");
        expectedOutput.add("XWYZ");
        expectedOutput.add("ZMNAGUEDSJYLBOPHRQICWFXTVK");

        for (int i = 0; i < inputs.size(); i++) {
            String actualOutput = rankTeams(inputs.get(i));
            String expected = expectedOutput.get(i);
            if (actualOutput.equals(expected)) {
                System.out.printf("Test - %s Passed\n", i+1);
            } else {
                System.out.printf("Test - %s Failed. Actual - %s, Expected - %s\n", i+1, actualOutput, expected);
            }
        }
    }

    private static String rankTeams(String[] votes) {

        if (votes.length == 0) return "";

        if (votes.length == 1) return votes[0];

        int totalTeams = votes[0].length();

        //1. Create a matrix with 26 * (n+1)
        //2. Initialize first col with team index
        //3. Put all the votes in the matrix
        //4. Sort the matrix using custom comparator
        //5. Form the string from the first col and return.


        int[][] leaderBoard = new int[26][totalTeams + 1];

        //Fill team codes
        for (int i = 0; i < 26; i++) {
            leaderBoard[i][0] = i;
        }

        //Create a matrix with teams and votes count at respective positions
        for (int i = 0; i < votes.length; i++) {
            String vote = votes[i];
            for (int j = 0; j < vote.length(); j++) {
                char teamName = vote.charAt(j);
                int teamIndex = teamName - 'A';
                leaderBoard[teamIndex][j+1] += 1;
            }
        }

        //Sort based on votes and position voted at
        Arrays.sort(leaderBoard, (a, b) -> compare(a, b));

        String teams = toString(leaderBoard, totalTeams);

        return teams;
    }

    private static String toString(int[][] leaderBoard, int totalTeams){
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<totalTeams; i++){
            int teamIndex = leaderBoard[i][0];
            char teamName = (char)(teamIndex + 'A');
            sb.append(teamName);
        }

        return sb.toString();
    }

    private static int compare(int[] a, int[] b){

        for(int i=1; i < a.length; i++){
            int compareResults = Integer.compare(a[i], b[i]);
            if(compareResults !=0){
                return compareResults * -1;
            }
        }

        //If using hashmap, get the team name from Map
        return Integer.compare(a[0], b[0]);
    }


}

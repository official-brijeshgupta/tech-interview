package interviews.tech.atlassian;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *     - another question was sort of leetcode
 *         - elections, every1 votes for 3 candidates, assume a method: def vote(candidate1, c2, c3)
 *         - first candidate gets 5 points, 2nd gets 3 and the third gets 1 point
 *         - find who win the elections
 *         - there might be a lot of candidates
 *         - but every1 votes for 3 candidates
 *         - vote(c1,c2,c3)
 *         - vote(c4, c1,c5)
 *         - vote(c1, c5,c4)
 *         - so it's either find the winer or sort the candidates by their results
 *
 * there is an edge case:
 * vote(c1, c2, c3)
 * vote(c4, c5, c6)
 * vote(c7, c8, c6)
 * vote(c9, c10, c6)
 *
 * vote(c11, c12, c6)
 * vote(c12, c11, c6)
 *
 * c1 gets 5 points, coz i was voted first
 * and also c6 get 5 points, coz it was voted 5 times as 3rd
 *
 * the question is how to sort them
 * if c1 goes first or c6
 *
 * basically you should implement a custom comparator
 */
public class VotingSystem {

    public static void execute() {

        printLeaderBoard();
    }

    public static void printLeader() {

        VotingMachine votingMachine = new VotingMachine();
        votingMachine.vote("c1", "c2", "c3");
        votingMachine.vote("c4", "c5", "c6");
        votingMachine.vote("c7", "c8", "c6");
        votingMachine.vote("c9", "c10", "c6");
        votingMachine.vote("c11", "c12", "c6");
        votingMachine.vote("c13", "c14", "c6");

        String expectedWinner = "c6";
        if (votingMachine.findWinner().equals(expectedWinner)) {
            System.out.println("Passed!! - Winner is " + expectedWinner);
        } else {
            System.out.println("Failed!!, Winner should not be " + votingMachine.findWinner());
        }
    }

    private static void printLeaderBoard(){
        VotingMachine2 votingMachine = new VotingMachine2(15);
        votingMachine.vote(0, 1, 2);
        votingMachine.vote(3, 4, 5);
        votingMachine.vote(6, 7, 5);
        votingMachine.vote(8, 9, 5);
        votingMachine.vote(10, 11, 5);
        votingMachine.vote(12, 14, 5);
        votingMachine.vote(3, 7, 0);

        int expectedWinner = 5;
        int[][] getLeaderBoard = votingMachine.getLeaderBoard();
        if (votingMachine.findWinner() == 5) {
            System.out.println("Passed!! - Winner is " + expectedWinner);
        } else {
            System.out.println("Failed!!, Winner should not be " + votingMachine.findWinner());
        }
    }
}

class VotingMachine {

    private final Map<String, int[]> votes;
    private String leading;

    public VotingMachine(){
        votes = new HashMap<>();
        leading = null;
    }

    public void vote(String c1, String c2, String c3) {
        vote1(c1);
        vote2(c2);
        vote3(c3);
        printLeading();
    }

    public String findWinner() {
        return leading;
    }


    private void vote1(String c){
        updateVote(c, 5);
    }

    private void vote2(String c){
        updateVote(c, 3);
    }

    private void vote3(String c){
        updateVote(c, 1);
    }

    private int[] getCandidateVotes(String c){
        if (votes.containsKey(c)) return  votes.get(c);
        return new int[2];
    }

    private void updateVote(String c, int value){
        int[] candidateVotes = getCandidateVotes(c);
        candidateVotes[0] = candidateVotes[0] + value; // Value
        candidateVotes[1] = candidateVotes[1] + 1; // votes count
        votes.put(c, candidateVotes);

        updateLeading(c, candidateVotes);
    }

    private void updateLeading(String c, int[] candidateVotes) {
        if(leading == null){
            leading = c;
            return;
        }
        int[] votesOfLeadingCandidate = votes.get(leading);
        if ((candidateVotes[0] > votesOfLeadingCandidate[0])
                || (candidateVotes[0] == votesOfLeadingCandidate[0]
                && candidateVotes[1] > votesOfLeadingCandidate[1]
        )

        ) { // compare value and if equal compare votes
            leading = c;
        }
    }

    private void printLeading() {
        if (leading == null) return;

        int[] votesOfLeadingCandidate = votes.get(leading);
        System.out.printf(
                "Leading - Candidate = %s , Value = %s, Votes = %s%n",
                leading,
                votesOfLeadingCandidate[0],
                votesOfLeadingCandidate[1]
        );
    }

}

class VotingMachine2 {

    private final int[][] leaderBoard;
    private int leadingIndex;

    public VotingMachine2(int totalCandidates){
        leaderBoard = new int[totalCandidates][3];
        for (int i=0; i<leaderBoard.length; i++){
           leaderBoard[i][0] = i;
        }
        leadingIndex = 0;
        printLeaderBoard();
    }

    private void printLeaderBoard(){
        for (int i=0; i<leaderBoard.length; i++){
            for (int j=0; j<leaderBoard[0].length; j++){
                System.out.print(leaderBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void vote(int c1, int c2, int c3) {
        vote1(c1);
        vote2(c2);
        vote3(c3);
        printLeading();
    }

    public int findWinner() {
        return leaderBoard[leadingIndex][0];
    }

    public int[][] getLeaderBoard() {
        Arrays.sort(leaderBoard, (x, y) -> compare(x,y));
        printLeaderBoard();
        return leaderBoard;

    }

    private int compare(int[] x, int[] y) {
        return x[1] == y[1] ? (y[2] - x[2] ) : y[1] - x[1];
    }

    private void vote1(int c){
        updateVote(c, 5);
    }

    private void vote2(int c){
        updateVote(c, 3);
    }

    private void vote3(int c){
        updateVote(c, 1);
    }

    private int[] getCandidateVotes(int c){
        return leaderBoard[c];
    }

    private void updateVote(int c, int value){
        int[] candidateVotes = getCandidateVotes(c);
        candidateVotes[1] = candidateVotes[1] + value; // Value
        candidateVotes[2] = candidateVotes[2] + 1; // votes count

        updateLeading(c);
    }

    private void updateLeading(int c) {
        int[] votesOfLeadingCandidate = leaderBoard[leadingIndex];
        int[] candidateVotes = leaderBoard[c];
        if ((candidateVotes[1] > votesOfLeadingCandidate[1])
                || (candidateVotes[1] == votesOfLeadingCandidate[1]
                && candidateVotes[2] >= votesOfLeadingCandidate[2]
        )

        ) { // compare value and if equal compare votes
            leadingIndex = c;
        }
    }

    private void printLeading() {
        int[] votesOfLeadingCandidate = leaderBoard[leadingIndex];
        System.out.printf(
                "Leading - Candidate = %s , Value = %s, Votes = %s%n",
                leadingIndex,
                votesOfLeadingCandidate[1],
                votesOfLeadingCandidate[2]
        );
    }



}

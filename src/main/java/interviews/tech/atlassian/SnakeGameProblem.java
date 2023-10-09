package interviews.tech.atlassian;

import org.springframework.data.util.Pair;

import java.util.*;

/**
 * https://leetcode.com/problems/design-snake-game/
 */
public class SnakeGameProblem {

    private static final String UP = "U";
    private static final String DOWN = "D";
    private static final String LEFT = "L";
    private static final String RIGHT = "R";

    public static void main(String[] args) {
        game1(
                3,
                2,
                new int[][]{
                        new int[]{1, 2},
                        new int[]{0, 1}

                }
        );
//        game2();
    }

    private static int game1(int width, int height, int[][] food){
        SnakeGame snake = new SnakeGame(width, height, food);
        String[] moves = new String[]{RIGHT, DOWN, RIGHT, UP, LEFT, UP};
        int size = 0;

        for (int i=0; i < moves.length; i++){
            size = snake.move(moves[i]);
            System.out.println(size);
            if(size == -1) break;
        }

        if(size==-1){
            System.out.println("Passed");
        }else{
            System.out.println("Failed");
        }

        return size;
    }


}

class SnakeGame {

//    private int score;
//    private int pos_x;
//    private int pos_y;

    private final int width;
    private final int height;

    private final int[][] food;
    private int foodTracker;

    private final Queue<Pair<Integer, Integer>> snake;

    private final Set<Pair<Integer, Integer>> snakeMap;

    public SnakeGame(int width, int height, int[][] food) {

        this.snake = new LinkedList<>();
        this.snake.offer(Pair.of(0,0));
        this.snakeMap = new HashSet<>();
        this.snakeMap.add(Pair.of(0,0)); // intially at [0][0]

        this.width = width;
        this.height = height;

        this.food = food;
        this.foodTracker = 0;

    }

    public int move(String direction) {

        boolean wasAbleToMove = tryMove(direction);

        if(!wasAbleToMove) return -1;

        Boolean wasAbleTpConsume = tryConsumeFood();
        tryRemoveTail(wasAbleTpConsume);
        return this.snake.size() -1;
    }

    private boolean tryMove(String direction){

        if(direction.equals("R")){
            return tryMoveRight();
        }else if(direction.equals("D")){
            return tryMoveDown();
        }else if(direction.equals("L")){
            return tryMoveLeft();
        }else if(direction.equals("U")){
            return tryMoveUp();
        }

        return false;
    }

    private Pair<Integer, Integer> head(){
        return snake.peek();
    }

    private boolean tryMoveDown(){
        return tryAddNewSnakeHead(Pair.of(head().getFirst() + 1, head().getSecond()));
    }

    private boolean tryMoveUp(){
        return tryAddNewSnakeHead(Pair.of(head().getFirst() - 1, head().getSecond()));
    }

    private boolean tryMoveRight(){
        return tryAddNewSnakeHead(Pair.of(head().getFirst() , head().getSecond() + 1));
    }

    private boolean tryMoveLeft(){
        return tryAddNewSnakeHead(Pair.of(head().getFirst(), head().getSecond() - 1));
    }

    private boolean tryConsumeFood() {
        if (this.foodTracker < this.food.length &&
                head().getFirst() == this.food[this.foodTracker][0] &&
                head().getSecond() == this.food[this.foodTracker][1]) {
            this.foodTracker++;
            return true;
        }

        return false;
        // printCurrentPos();
    }

    private void printCurrentPos(){
        System.out.printf("%s,%s \n", head());
        System.out.printf("Food Tracker %s \n", foodTracker);
    }

    private boolean tryAddNewSnakeHead(Pair<Integer, Integer> newHead){
        if(isSafeFromSnakeBite(newHead) || checkForBoundary(newHead)){
            this.snakeMap.add(newHead);
            this.snake.offer(newHead);
            return true;
        }

        return false;

    }

    private void tryRemoveTail(boolean consumedFood){
        if(consumedFood) return;

        snake.poll();
    }

    private boolean isSafeFromSnakeBite(Pair<Integer, Integer> newHead){
       return !snakeMap.contains(newHead);
    }

    private boolean checkForBoundary(Pair<Integer, Integer> newHead) {
        return
                newHead.getFirst() >= 0
                        && newHead.getFirst() < width
                        && newHead.getSecond() >= 0
                        && newHead.getSecond() < height;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */
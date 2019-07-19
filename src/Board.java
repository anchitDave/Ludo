import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.Set;

public class Board {
    private HashMap<Lives,Integer> positions = new HashMap<>();
    private TreeMap<Integer, List<Lives>> board = new TreeMap<>();
    private HashMap<Lives,Integer> finalPositions = new HashMap<>();
    private static Board ludoBoard = null;


    public static Board getInstance(){
        if (ludoBoard == null)
            ludoBoard = new Board();
         return ludoBoard;
    }

    public void start(Lives life){
        if (life.getType() == "Red"){
            addStart(life,0);
         }
        else if (life.getType() == "Green"){
            addStart(life,13);
        }
        else if (life.getType() == "Blue"){
            addStart(life,26);
        }
        else if (life.getType() == "Yellow"){
            addStart(life,39);
        }
        printBoard();
    }

    public void addStart(Lives life, int start){
        positions.put(life,start);
        System.out.println(positions.size());
        List<Lives> temp = new ArrayList<>();
        if (board.containsKey(start)){
            temp = board.get(start);
        }
        temp.add(life);
        board.put(start,temp);
    }

    public List<Lives> getPlayerPostions(Player player){
        List<Lives> list = new ArrayList<>();
        for (Lives life: player.lives){
            if (positions.containsKey(life)){
                System.out.println(life.getType()+" at position "+ positions.get(life));
                list.add(life);
            } else if (finalPositions.containsKey(life)){
                if (finalPositions.get(life) != 56){
                    System.out.println(life.getType()+" is at final position needs "+ (56 - finalPositions.get(life)));
                    list.add(life);
                }
            }
        }
        return list;
    }
    private List<Lives> returnLives(int position){
        if (board.containsKey(position))
            return board.get(position);
        else return null;
    }


    public TreeMap<Integer, List<Lives>> getBoard(){
        return board;
    }

    public void printBoard(){
        Set<Integer> set = board.keySet();
        for (Integer a: set){
            System.out.print("Position "+a+" has ");
            for (Lives b: board.get(a)){
                System.out.print(b.getType()+", ");
            }
            System.out.println();
        }
        System.out.println();

    }

    public void moveLife(Lives life, int noOnDice, Player player){
        if (life.getCount() + noOnDice > 50){
            finalSteps(life,life.getCount()+noOnDice,player);
            return;
        }
        int newPositon = (positions.get(life) + noOnDice) % 53;
        List<Lives> lives = returnLives(newPositon);
        if (lives == null){
            List<Lives> tempList = board.get(positions.get(life));
            tempList.remove(life);
            if (tempList.size() == 0)
                board.remove(positions.get(life));
            tempList = new ArrayList<Lives>();
            tempList.add(life);
            board.put(newPositon, tempList);
            positions.put(life,newPositon);
            life.setCount(noOnDice);
        } else {
            List<Lives> tempList = new ArrayList<>();
            for (Lives a: lives){
                if (a.getType().equals(life.getType())) {
                    tempList.add(a);
                    continue;
                }
                a.setHome();
                positions.remove(a);
            }
            tempList.add(life);
            board.put(newPositon,tempList);
            tempList = board.get(positions.get(life));
            tempList.remove(life);
            if (tempList.size() == 0)
                board.remove(positions.get(life));
            positions.put(life,newPositon);
            life.setCount(noOnDice);
        }
        System.out.println("Positions on board are ");
        printBoard();
    }

    public void finalSteps(Lives life, int newPosition, Player player){
        if (newPosition == 56){
            System.out.print("We have a Winner: "+ player.name);
            if (positions.containsKey(life))
                adjust(life);
            if (finalPositions.containsKey(life))
                finalPositions.remove(life);
            player.setSafeLives(life);
        } else if (newPosition > 56)
            System.out.println("Cannot Move this");
        else {
            if (positions.containsKey(life)) {
                adjust(life);
            }
            life.setCount(newPosition);
            finalPositions.put(life,newPosition);
        }

        printBoard();
    }

    public void adjust(Lives life){
        List<Lives> tempList = board.get(positions.get(life));
        tempList.remove(life);
        if (tempList.size() == 0)
            board.remove(positions.get(life));

        positions.remove(life);
    }
}

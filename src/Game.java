import java.util.*;

public class Game {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean start = false;
        List<Player> list = new ArrayList<>();
        int i =1;
        System.out.println("Assigned Two Players");
        list.add(new Player("Player"+i++));
        list.add(new Player("player"+i++));
        while (i < 5){
            System.out.println("Would you like to assign more players? (Y/N)");
            String temp = in.next();
            if (temp.equals("Y")){
                Player tempPlayer = new Player("Player"+i++);
                if (tempPlayer.getType() == null){
                    start = true;
                }
                else {
                    list.add(tempPlayer);
                }
            } else break;
        }
        Board game = Board.getInstance();
        for(Player a :list){
            game.start(a.lives.get(0));
        }
        game.printBoard();
        Decider decider = new Decider();
        Boolean finish = false;
        while (!finish) {
            for (Player player:list) {
                System.out.println(player.name + "'s turn");
                Random rand = new Random();
                int a = rand.nextInt(6) + 1;
                System.out.println("Number on Dice: " + a);
                decider.optionsDecider(player, a);
                if (decider.statusChecker(player) == true) {
                    finish = true;
                    break;
                }
            }
            if (finish)
                break;
            System.out.println();
        }


    }
}

import java.util.List;
import java.util.Scanner;

public class Decider {

    public void optionsDecider(Player player, int noOnDice){
        Board game = Board.getInstance();
        List<Lives> lives = game.getPlayerPostions(player);
        int count = lives.size();
        Scanner in = new Scanner(System.in);

        if (noOnDice == 6 && count + player.getSafeLives() < 4){
            System.out.println("Start a new Life? Y/N");
            if (in.next().equals("Y")){
                for (Lives life: player.lives) {
                    if (lives.contains(life))
                        continue;
                    game.start(life);
                    return;
                }
                return;
            }
        }
        if (count > 1) {
            System.out.println("Which life would you like to move?");
            int a = in.nextInt();
            game.moveLife(lives.get(a-1), noOnDice, player);
        } else if(count == 1){
            game.moveLife(lives.get(0), noOnDice, player);
        }
    }

    public boolean statusChecker(Player player){
        if (player.getSafeLives() == 4)
            return true;
        return false;
    }
}

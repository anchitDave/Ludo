import java.util.*;

public class Player {


    public String name;
    ArrayList<Lives> lives = new ArrayList<>();
    private int safeLives = 0;

    Player(String name){
        this.name = name;
        for(int i =0; i<4;i++) {
            Lives temp = new Lives();
            if (temp == null)
                break;
            lives.add(temp);
        }
    }

    public String getType(){
        return lives.get(0).getType();
    }

    public void setSafeLives(Lives life){
        lives.remove(life);
        this.safeLives++;
    }

    public int getSafeLives(){
        return safeLives;
    }

}

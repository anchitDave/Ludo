import java.util.*;

public class Type {
    String[] types = {"Red","Red","Red","Red","Blue","Blue","Blue","Blue","Green","Green","Green","Green","Yellow","Yellow","Yellow","Yellow"};
    List<String> allTypes = new LinkedList<>(Arrays.asList(types));
    static Type type = null;
    Type(){
    }

    public static Type getInstance(){
        if (type == null){
            type = new Type();
            return type;
        } else return type;
    }


    public String getType(){
        if (type.allTypes.size() == 0){
            System.out.println("No More Players");
            return null;
        }
        return type.allTypes.remove(0);
    }


}

public class Lives {
    private int count;
    private String type;
    Lives(){
        count = 0;
        Type temp = Type.getInstance();
        type = temp.getType();
    }

    public int getCount(){
        return this.count;
    }

    public String getType()
    {
        return this.type;

    }

    public void setHome(){
        this.count = 0;
    }

    public void setCount(int noOnDice){
        if (noOnDice > 50)
            this.count = noOnDice;
        else this.count = this.count + noOnDice;
    }
}

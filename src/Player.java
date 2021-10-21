import java.util.ArrayList;

public class Player {

    private int money;
    private ArrayList<Property> properties;
    private int id;
    private int position;

    public Player (){
        this.money=1500;
        this.position=0;

    }
    public void setId(int id){
        this.id = id;
    }


}

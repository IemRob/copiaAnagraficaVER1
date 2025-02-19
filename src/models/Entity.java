package models;

public abstract class Entity {
    
    //proprietà
    private int id;

    //costruttore
    public Entity(){}

    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id=" + id;
    }


}

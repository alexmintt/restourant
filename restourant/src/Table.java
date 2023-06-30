public class Table {
    private int id;
    private int max_capacity;
    public Table (int id, int max_capacity){
        this.id = id;
        this.max_capacity = max_capacity;
    }

    public int getId() {
        return id;
    }
    public int getMC(){return max_capacity;}

}
public class Meal {
    private int id;
    private int table_id;
    private String date_;
    private int waiter_id;
    private int client_id;
    private String end_time;
    private String start_time;
    public Meal (int id, int table_id, String date_, int waiter_id, int client_id, String end_time, String start_time){
        this.id = id;
        this.table_id = table_id;
        this.date_ = date_;
        this.client_id = client_id;
        this.waiter_id = waiter_id;
        this.end_time = end_time;
        this.start_time = start_time;
    }

    public int getId() {
        return id;
    }
    public int getTI() {
        return table_id;
    }
    public String getdate(){return date_;}
    public int getWI() {
        return waiter_id;
    }
    public int getCI() {
        return client_id;
    }
    public String getET(){return end_time;}
    public String getST(){return start_time;}


}
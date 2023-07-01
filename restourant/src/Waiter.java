public class Waiter {
    private int id;
    private String name;
    private String address;
    private String phone_number;
    public Waiter (int id, String name, String address, String phone_number){
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
    }

    public int getId() {
        return id;
    }
    public String getName(){return name;}
    public String getAddress(){return address;}
    public String getPN() {
        return phone_number;
    }
}
public class Waiter {
    private int id;
    private String name;
    private String address;
    private int phone_number;
    public Waiter (int id, String name, String address, int phone_number){
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
    public int getPN() {
        return phone_number;
    }
}
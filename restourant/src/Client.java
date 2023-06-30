public class Client {
    private int id;
    private int tax_id;
    private String name;
    private String address;
    public Client (int id, int tax_id, String name, String address){
        this.id = id;
        this.tax_id = tax_id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }
    public int getTI(){return tax_id;}
    public String getName(){return name;}
    public String getAddress(){return address;}
}
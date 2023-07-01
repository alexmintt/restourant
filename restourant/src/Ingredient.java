public class Ingredient {
    private int id;
    private String unit_of_measurement;
    private String name;
    private int quantity_in_stock;
    public Ingredient (int id, String name, String unit_of_measurement, int quantity_in_stock){
        this.id = id;
        this.name = name;
        this.unit_of_measurement = unit_of_measurement;
        this.quantity_in_stock = quantity_in_stock;
    }

    public int getId() {
        return id;
    }
    public String getName(){return name; }

    public String getUOM() {
        return unit_of_measurement;
    }
    public int getQIS(){return quantity_in_stock;}

}
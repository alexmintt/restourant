public class Ingredient {
    private int id;
    private String unit_of_measurement;
    private int quantity_in_stock;
    public Ingredient (int id, String unit_of_measurement, int quantity_in_stock){
        this.id = id;
        this.unit_of_measurement = unit_of_measurement;
        this.quantity_in_stock = quantity_in_stock;
    }

    public int getId() {
        return id;
    }

    public String getUOM() {
        return unit_of_measurement;
    }
    public int getQIS(){return quantity_in_stock;}

}
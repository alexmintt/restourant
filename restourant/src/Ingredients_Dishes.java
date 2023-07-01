public class Ingredients_Dishes {
    private int id;
    private String ingredient_name;
    private String dish_name;
    private int needed_amount;
    public Ingredients_Dishes (int id, String ingredient_name, String dish_name, int needed_amount){
        this.id = id;
        this.ingredient_name = ingredient_name;
        this.dish_name = dish_name;
        this.needed_amount = needed_amount;
    }

    public int getId() {
        return id;
    }
    public String getIN(){return ingredient_name;}
    public String getDN(){return dish_name;}
    public int getNA(){return needed_amount;}
}
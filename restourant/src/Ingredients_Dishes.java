public class Ingredients_Dishes {
    private int id;
    private int ingredient_id;
    private int dish_id;
    private int needed_amount;
    public Ingredients_Dishes (int id, int ingredient_id, int dish_id, int needed_amount){
        this.id = id;
        this.ingredient_id = ingredient_id;
        this.dish_id = dish_id;
        this.needed_amount = needed_amount;
    }

    public int getId() {
        return id;
    }
    public int getII(){return ingredient_id;}
    public int getDI(){return dish_id;}
    public int getNA(){return needed_amount;}
}
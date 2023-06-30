public class Meals_Dishes {
    private int id;
    private int meal_id;
    private int dish_id;
    private int ordered_count;
    public Meals_Dishes (int id, int meal_id, int dish_id, int ordered_count){
        this.id = id;
        this.meal_id = meal_id;
        this.dish_id = dish_id;
        this.ordered_count = ordered_count;
    }

    public int getId() {
        return id;
    }
    public int getMI(){return meal_id;}
    public int getDI(){return dish_id;}
    public int getOC(){return ordered_count;}
}

import java.sql.ResultSet;
import java.util.ArrayList;

public class ContIngredientDish extends ArrayList<Ingredients_Dishes> {
    private int size;

    public ContIngredientDish(Connect getdata, int id) throws Exception {
        ResultSet res = getdata.getIngredientByDishID(id);
        while (res.next()) {
            this.add(new Ingredients_Dishes(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4)));
            size++;
        }
    }

    public String[][] toData(String role) {
        String a[][] = new String[size + 1][4];
        for (int i = 0; i < size; i++) {
            a[i][0] = String.valueOf(get(i).getId());
            a[i][1] = String.valueOf(get(i).getDN());
            a[i][2] = String.valueOf(get(i).getIN());
            a[i][3] = String.valueOf(get(i).getNA());
        }
        a[size][0] = "Итого:";
        a[size][1] = String.valueOf(size);
        a[size][2] = "-";
        a[size][3] = "-";
        return a;
    }
}
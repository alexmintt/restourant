import java.sql.ResultSet;
import java.util.ArrayList;
public class ContIngredient extends ArrayList<Ingredient> {
    private int size;
    public ContIngredient(Connect getdata) throws Exception {
        ResultSet res = getdata.getIngredients();
        while (res.next()) {
            this.add(new Ingredient(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4)));
            size++;
        }
    }
    public String[][] toData(String role) {
        String a[][] = new String[size + 1][4];
        for (int i = 0; i < size; i++) {
            a[i][0] = String.valueOf(get(i).getId());
            a[i][1] = String.valueOf(get(i).getName());
            a[i][2] = String.valueOf(get(i).getUOM());
            a[i][3] = String.valueOf(get(i).getQIS());
        }
        a[size][0] = "Итого:";
        a[size][1] = String.valueOf(size);
        a[size][2] = "-";
        a[size][3] = "-";
        return a;
    }
}
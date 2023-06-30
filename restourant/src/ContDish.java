import java.sql.ResultSet;
import java.util.ArrayList;
public class ContDish extends ArrayList<Dish> {
    private int size;

    public ContDish(Connect getdata) throws Exception {
        ResultSet res = getdata.getDishes();
        while (res.next()) {
            this.add(new Dish(res.getInt(1), res.getString(2)));
            size++;
        }
    }

    public String[][] toData(String role) {
        String a[][] = new String[size + 1][2];
        for (int i = 0; i < size; i++) {
            a[i][0] = String.valueOf(get(i).getId());
            a[i][1] = String.valueOf(get(i).getName());
        }
        a[size][0] = "Итого:";
        a[size][1] = String.valueOf(size);
        return a;
    }
}
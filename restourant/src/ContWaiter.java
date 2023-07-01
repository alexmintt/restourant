import java.sql.ResultSet;
import java.util.ArrayList;
public class ContWaiter extends ArrayList<Waiter> {
    private int size;

    public ContWaiter(Connect getdata) throws Exception {
        ResultSet res = getdata.getWaiters();
        while (res.next()) {
            this.add(new Waiter(res.getInt(1), res.getString(2), res.getString(3), res.getString(4)));
            size++;
        }
    }

    public String[][] toData(String role) {
        if (role.equals("superuser")) {
            String a[][] = new String[size + 1][4];
            for (int i = 0; i < size; i++) {
                a[i][0] = String.valueOf(get(i).getId());
                a[i][1] = String.valueOf(get(i).getName());
                a[i][2] = String.valueOf(get(i).getAddress());
                a[i][3] = String.valueOf(get(i).getPN());
            }
            a[size][0] = "Итого:";
            a[size][1] = String.valueOf(size);
            a[size][2] = "-";
            a[size][3] = "-";
            return a;
        }
        else{
            String a[][] = new String[size + 1][2];
            for (int i = 0; i < size; i++) {
                a[i][0] = String.valueOf(get(i).getName());
                a[i][1] = String.valueOf(get(i).getPN());
            }
            a[size][0] = "Итого:";
            a[size][1] = String.valueOf(size);
            return a;
        }
    }
}
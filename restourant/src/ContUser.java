import java.sql.ResultSet;
import java.util.ArrayList;
public class ContUser extends ArrayList<User> {
    private int size;

    public ContUser(Connect getdata) throws Exception {
        ResultSet res = getdata.getUsers();
        while (res.next()) {
            this.add(new User(res.getInt(1), res.getString(2), res.getString(3), getdata.getRoleByID(res.getInt(1))));
            size++;
        }
    }

    public String[][] toData(String role) {
        String a[][] = new String[size + 1][4];
        for (int i = 0; i < size; i++) {
            a[i][0] = String.valueOf(get(i).getId());
            a[i][1] = String.valueOf(get(i).getLogin());
            a[i][2] = String.valueOf(get(i).getPassword());
            a[i][3] = String.valueOf(get(i).getRole());
        }
        a[size][0] = "Итого:";
        a[size][1] = String.valueOf(size);
        a[size][2] = "-";
        a[size][3] = "-";
        return a;
    }
}
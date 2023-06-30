import java.sql.ResultSet;
import java.util.ArrayList;
public class ContClient extends ArrayList<Client> {
    private int size;

    public ContClient(Connect getdata) throws Exception {
        ResultSet res = getdata.getClients();
        while (res.next()) {
            this.add(new Client(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4)));
            size++;
        }
    }

    public String[][] toData(String role) {
            String a[][] = new String[size + 1][4];
            for (int i = 0; i < size; i++) {
                a[i][0] = String.valueOf(get(i).getId());
                a[i][1] = String.valueOf(get(i).getTI());
                a[i][2] = String.valueOf(get(i).getName());
                a[i][3] = String.valueOf(get(i).getAddress());
            }
            a[size][0] = "Итого:";
            a[size][1] = String.valueOf(size);
            a[size][2] = "-";
            a[size][3] = "-";
            return a;
    }
}
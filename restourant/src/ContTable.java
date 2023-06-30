import java.sql.ResultSet;
import java.util.ArrayList;
public class ContTable extends ArrayList<Table> {
    private int size;
    public ContTable(Connect getdata) throws Exception {
        ResultSet res = getdata.getTables();
        while (res.next()) {
            this.add(new Table(res.getInt(1), res.getInt(2)));
            size++;
        }
    }
    public String[][] toData(String role) {
        String a[][] = new String[size + 1][2];
        for (int i = 0; i < size; i++) {
            a[i][0] = String.valueOf(get(i).getId());
            a[i][1] = String.valueOf(get(i).getMC());
        }
        a[size][0] = "Итого:";
        a[size][1] = String.valueOf(size);
        return a;
    }
}
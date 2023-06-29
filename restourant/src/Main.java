import java.sql.*;

public class Main {
    public static void main(String[] args){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver" );
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2298_project" ,
                    "std_2298_project" , "12345678" );
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Ingredients" ;
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                int id = result.getInt("id" );
                String name = result.getString("name");
                String unit_of_measurement = result.getString("unit_of_measurement" );
                System.out.print(" Vacant post: " );
                System.out.print(" id = " + id);
                System.out.print(" , name = \" " + name + " \" " );
                System.out.print(" , unit_of_measurement = \" " + unit_of_measurement + " \" " );
            }
            connection.close();
            /*Connect connect = new Connect();
            ResultSet a = connect.getUsers();
            System.out.println(a);

             */
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
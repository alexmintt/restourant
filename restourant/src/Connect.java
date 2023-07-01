import java.sql.ResultSet;
import java.sql.*;
public class Connect {
    private Connection connection;

    public Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2298_project",
                    "std_2298_project", "12345678");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addUsers(String log, String pas, int role_id) throws Exception {
        PreparedStatement stat = connection.prepareStatement("INSERT Users(id, login, password, role_id)  VALUES(null, ?, ?, ?);");
        stat.setString(1, log);
        stat.setString(2, String.valueOf(pas.hashCode()));
        stat.setInt(3, role_id);
        stat.executeUpdate();
    }

    public void addClients(int tax_id, String name, String address) throws Exception {
        PreparedStatement stat = connection.prepareStatement("INSERT Clients(id, tax_id, name, address)  VALUES(null, ?, ?, ?);");
        stat.setInt(1, tax_id);
        stat.setString(2, name);
        stat.setString(3, address);
        stat.executeUpdate();
    }

    public void addDishes(String name) throws Exception {
        PreparedStatement stat = connection.prepareStatement("INSERT Dishes(id, name)  VALUES(null, ?);");
        stat.setString(1, name);
        stat.executeUpdate();
    }

    public void addIngredients(String name, String unit_of_measurement, int quantity_in_stock) throws Exception {
        PreparedStatement stat = connection.prepareStatement("INSERT Ingredients(id, name, unit_of_measurement, quantity_in_stock)  VALUES(null, ?, ?, ?);");
        stat.setString(1, name);
        stat.setString(2, unit_of_measurement);
        stat.setInt(3, quantity_in_stock);
        stat.executeUpdate();
    }

    public void addMeals(int table_id, String date_, int waiter_id, int client_id, String end_time, String start_time) throws Exception {
        PreparedStatement stat = connection.prepareStatement("INSERT Meals(id, table_id, date_, waiter_id, client_id, end_time, start_time)  VALUES(null, ?, ?, ?, ?, ?, ?);");
        stat.setInt(1, table_id);
        stat.setString(2, date_);
        stat.setInt(3, waiter_id);
        stat.setInt(4, client_id);
        stat.setString(5, end_time);
        stat.setString(6, start_time);
        stat.executeUpdate();
    }

    public void addRoles(String name) throws Exception {
        PreparedStatement stat = connection.prepareStatement("INSERT Roles(id, name)  VALUES(null, ?);");
        stat.setString(1, name);
        stat.executeUpdate();
    }

    public void addTables(int max_capacity) throws Exception {
        PreparedStatement stat = connection.prepareStatement("INSERT Meals(id, max_capacity)  VALUES(null, ?);");
        stat.setInt(1, max_capacity);
        stat.executeUpdate();
    }

    public void addWaiters(String name, String address, String phone_number) throws Exception {
        PreparedStatement stat = connection.prepareStatement("INSERT Waiters(id, name, address, phone_number)  VALUES(null, ?, ?, ?);");
        stat.setString(1, name);
        stat.setString(2, address);
        stat.setString(3, phone_number);
        stat.executeUpdate();
    }

    public void addIngredients_Dishes(int ingredient_id, int dish_id, int needed_amount) throws Exception {
        PreparedStatement stat = connection.prepareStatement("INSERT Ingredients_Dishes(ingredient_id, dish_id, needed_amount)  VALUES(?, ?, ?);");
        stat.setInt(1, ingredient_id);
        stat.setInt(2, dish_id);
        stat.setInt(3, needed_amount);
        stat.executeUpdate();
    }

    public void addMeals_Dishes(int meal_id, int dish_id, int ordered_count) throws Exception {
        PreparedStatement stat = connection.prepareStatement("INSERT Meals_Dishes(meal_id, dish_id, ordered_count)  VALUES(?, ?, ?);");
        stat.setInt(1, meal_id);
        stat.setInt(2, dish_id);
        stat.setInt(3, ordered_count);
        stat.executeUpdate();
    }

    public ResultSet getUsers() throws Exception {
        PreparedStatement stat = connection.prepareStatement("SELECT id, login, password, role_id FROM Users WHERE 1;");
        ResultSet res = stat.executeQuery();
        return res;
    }

    public void renameUsers(int id, String log, String pas) throws SQLException {
        PreparedStatement stat = connection.prepareStatement("UPDATE user SET login = ?, password = ? WHERE id = ?");
        stat.setString(1, log);
        stat.setString(2, String.valueOf(pas.hashCode()));
        stat.setInt(3, id);
        stat.executeUpdate();
    }

    public ResultSet getClients() throws Exception {
        PreparedStatement stat = connection.prepareStatement("SELECT id, tax_id, name, address FROM Clients WHERE 1;");
        return stat.executeQuery();
    }

    public ResultSet getDishes() throws Exception {
        PreparedStatement stat = connection.prepareStatement("SELECT id, name FROM Dishes WHERE 1;");
        return stat.executeQuery();
    }

    public ResultSet getIngredients() throws Exception {
        PreparedStatement stat = connection.prepareStatement("SELECT id, name, unit_of_measurement, quantity_in_stock FROM Ingredients WHERE 1;");
        return stat.executeQuery();
    }

    public ResultSet getIngredients_Dishes() throws Exception {
        PreparedStatement stat = connection.prepareStatement("SELECT Ingredients_id, dish_id, needed_amount FROM Ingredients_Dishes WHERE 1;");
        return stat.executeQuery();
    }

    public ResultSet getMeals() throws Exception {
        PreparedStatement stat = connection.prepareStatement("SELECT id, table_id, date_, waiter_id, client_id, end_time, start_time FROM Meals WHERE 1;");
        return stat.executeQuery();
    }

    public ResultSet getMeals_Dishes() throws Exception {
        PreparedStatement stat = connection.prepareStatement("SELECT meal_id, dish_id, ordered_count FROM Meals_Dishes WHERE 1;");
        return stat.executeQuery();
    }

    public ResultSet getTables() throws Exception {
        PreparedStatement stat = connection.prepareStatement("SELECT id, max_capacity FROM Tables WHERE 1;");
        return stat.executeQuery();
    }

    public ResultSet getWaiters() throws Exception {
        PreparedStatement stat = connection.prepareStatement("SELECT id, name, address, phone_number FROM Waiters WHERE 1;");
        return stat.executeQuery();
    }

    public int getid(String login) throws Exception {
        PreparedStatement stat = connection.prepareStatement("SELECT id FROM user WHERE login RLike ?");
        stat.setString(1, login);
        ResultSet res = stat.executeQuery();
        res.next();
        return res.getInt(1);
    }

    public boolean isUser(String login, String password) throws Exception {
        PreparedStatement stat = connection.prepareStatement("SELECT COUNT(id) AS a FROM Users WHERE ? = login AND ? = password");
        stat.setString(1, login);
        stat.setString(2, String.valueOf(password.hashCode()));
        ResultSet res = stat.executeQuery();
        res.next();
        return res.getInt("a") == 1;
    }

    public boolean isUser(String login) throws Exception {
        PreparedStatement stat = connection.prepareStatement("SELECT COUNT(id) AS a FROM Users WHERE ? = login");
        stat.setString(1, login);
        ResultSet res = stat.executeQuery();
        res.next();
        return res.getInt("a") == 1;
    }

    public int getRoles(String login) throws Exception {
        PreparedStatement stat = connection.prepareStatement("SELECT role_id FROM Users WHERE login RLike ?");
        stat.setString(1, login);
        ResultSet res = stat.executeQuery();
        res.next();
        return res.getInt(1);
    }


    public void deleteUser(int id) throws Exception {
        PreparedStatement stat = connection.prepareStatement("DELETE FROM user WHERE id = ?");
        stat.setInt(1, id);
        stat.executeUpdate();
    }


    public void changeDish(int id, String name) throws SQLException {
        PreparedStatement stat = connection.prepareStatement("UPDATE Dishes SET name = ? WHERE id = ?");
        stat.setString(1, name);
        stat.setInt(2, id);
        stat.executeUpdate();
    }

    public void changeIngredient(int id, String name, String unit_of_measurement, int quantity_in_stock) throws SQLException {
        PreparedStatement stat = connection.prepareStatement("UPDATE Ingredients SET name = ?, unit_of_measurement = ?, quantity_in_stock = ?  WHERE id = ?");
        stat.setString(1, name);
        stat.setString(2, unit_of_measurement);
        stat.setInt(3, quantity_in_stock);
        stat.setInt(4, id);
        stat.executeUpdate();
    }

    public void changeMeal(int id, int table_id, String date_, int waiter_id, int client_id, String end_time, String start_time) throws SQLException {
        PreparedStatement stat = connection.prepareStatement("UPDATE Meals SET table_id = ?, date_ = ?, waiter_id = ?, client_id = ?,  end_time = ?, start_time = ? WHERE id = ?");
        stat.setInt(1, table_id);
        stat.setString(2, date_);
        stat.setInt(3, waiter_id);
        stat.setInt(4, client_id);
        stat.setString(5, end_time);
        stat.setString(6, start_time);
        stat.setInt(7, id);
        stat.executeUpdate();
    }

    public void changeTable(int id, int max_capacity) throws SQLException {
        PreparedStatement stat = connection.prepareStatement("UPDATE Tables SET max_capacity = ? WHERE id = ?");
        stat.setInt(1, max_capacity);
        stat.setInt(2, id);
        stat.executeUpdate();
    }

    public void changeClient(int id, int tax_id, String name, String address) throws SQLException {
        PreparedStatement stat = connection.prepareStatement("UPDATE Clients SET tax_id = ?, name = ?, address = ? WHERE id = ?");
        stat.setInt(1, tax_id);
        stat.setString(2, name);
        stat.setString(3, address);
        stat.setInt(4, id);
        stat.executeUpdate();
    }

    public void changeWaiter(int id, String name, String address, String phone_number) throws SQLException {
        PreparedStatement stat = connection.prepareStatement("UPDATE Waiters SET name = ?, address = ?, phone_number = ? WHERE id = ?");
        stat.setString(1, name);
        stat.setString(2, address);
        stat.setString(3, phone_number);
        stat.setInt(4, id);
        stat.executeUpdate();
    }


    public ResultSet getDish(int id) throws SQLException {
        PreparedStatement stat = connection.prepareStatement("SELECT name FROM Dishes WHERE id = ?");
        stat.setInt(1, id);
        return stat.executeQuery();
    }

    public ResultSet getIngredient(int id) throws SQLException {
        PreparedStatement stat = connection.prepareStatement("SELECT name, unit_of_measurement, quantity_in_stock FROM Ingredients WHERE id = ?");
        stat.setInt(1, id);
        return stat.executeQuery();
    }

    public ResultSet getMeal(int id) throws SQLException {
        PreparedStatement stat = connection.prepareStatement("SELECT table_id, date_, waiter_id, client_id, end_time, start_time FROM Meals WHERE id = ?");
        stat.setInt(1, id);
        return stat.executeQuery();
    }

    public ResultSet getTable(int id) throws SQLException {
        PreparedStatement stat = connection.prepareStatement("SELECT max_capacity FROM Tables WHERE id = ?");
        stat.setInt(1, id);
        return stat.executeQuery();
    }

    public ResultSet getClient(int id) throws SQLException {
        PreparedStatement stat = connection.prepareStatement("SELECT tax_id, name, address FROM Clients WHERE id = ?");
        stat.setInt(1, id);
        return stat.executeQuery();
    }

    public ResultSet getWaiter(int id) throws SQLException {
        PreparedStatement stat = connection.prepareStatement("SELECT name, address, phone_number FROM Waiters WHERE id = ?");
        stat.setInt(1, id);
        return stat.executeQuery();
    }

    public String getRoleByID(int id) throws SQLException {
        PreparedStatement stat = connection.prepareStatement("SELECT Roles.name FROM Roles JOIN Users WHERE Users.id = ? AND Users.role_id = Roles.id");
        stat.setInt(1, id);
        ResultSet res = stat.executeQuery();
        res.next();
        return res.getString(1);
    }

    public ResultSet getIngredientByDishID(int id) throws SQLException {
        PreparedStatement stat = connection.prepareStatement("SELECT Dishes.id, Ingredients.name, Dishes.name, Ingredients_Dishes.needed_amount FROM Dishes JOIN Ingredients JOIN Ingredients_Dishes WHERE Dishes.id = ? AND Ingredients_Dishes.dish_id = Dishes.id AND Ingredients_Dishes.ingredient_id = Ingredients.id");
        stat.setInt(1, id);
        ResultSet res = stat.executeQuery();
        return res;
    }
}
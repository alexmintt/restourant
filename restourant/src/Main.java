import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.sql.SQLException;

public class Main extends Application {
    private Connect c = new Connect();
    private TextField login = new TextField();
    private PasswordField password = new PasswordField();
    private Button authorize = new Button("Авторизация");
    private Button registration = new Button("Регистрация");
    private Button change = new Button("Изменить данные");
    private Label l1 = new Label("успех");
    private Label l2 = new Label("Введены неверные данные");
    private FlowPane auth = new FlowPane(Orientation.VERTICAL, login,password, authorize, registration);
    private Scene sc_auth = new Scene(auth);
    private String log;
    private String pas;
    private String role;
    private int ID;

    private Button contClient = new Button("Клиенты");
    private Button contDish = new Button("Блюда");
    private Button contIngredient = new Button("Ингредиенты");
    private Button contTable = new Button("Столы");
    private Button contUser = new Button("Пользователи");
    private Button contWaiter = new Button("Официанты");
    private Button settings = new Button("Настройки");
    private Button exit = new Button("Выход");
    private Button backtolog = new Button("Попробовать ещё раз");
    private FlowPane header;
    private FlowPane screen;
    private FlowPane retry = new FlowPane(l2, backtolog);
    private TextField ch_login;
    private TextField ch_password;


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Ресторан");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        contUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ContUser cu = new ContUser(c);
                    String[][] scu = cu.toData(role);
                    GridPane vp = new GridPane();
                    vp.getColumnConstraints().add(new ColumnConstraints(10));
                    vp.getColumnConstraints().add(new ColumnConstraints(50));
                    vp.getColumnConstraints().add(new ColumnConstraints(50));
                    vp.getColumnConstraints().add(new ColumnConstraints(100, 150, 200));
                    vp.getColumnConstraints().add(new ColumnConstraints(100, 150, 200));
                    vp.getColumnConstraints().add(new ColumnConstraints(100, 150, 200));
                    vp.getColumnConstraints().add(new ColumnConstraints(100, 150, 200));
                    vp.add(new Label("id"),1,0);
                    vp.add(new Label("login"),2,0);
                    vp.add(new Label("hashpass"),3,0);
                    vp.add(new Label("role"),4,0);
                    for (int i = 0; i < scu.length; i++){
                        vp.add(new Label(),0,i+1);
                        for (int j = 0; j < scu[i].length; j++){
                            vp.add(new Label(scu[i][j]), j+1, i+1);
                        }
                        if (!scu[i][3].equals("superuser") && !scu[i][3].equals("-")){
                            Button bp = new Button(scu[i][3]);
                            Button bd = new Button("Удалить");
                            bp.setId(scu[i][0]);
                            bd.setId(scu[i][0]);
                            bd.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    try {
                                        c.deleteUser(Integer.parseInt(bd.getId()));
                                    } catch (
                                            Exception e) {
                                        throw new RuntimeException(e);
                                    }

                                }
                            });
                            bp.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    try {
                                        c.changeRole(Integer.parseInt(bp.getId()), bp.getText());
                                    } catch (
                                            Exception e) {
                                        throw new RuntimeException(e);
                                    }

                                }
                            });
                            vp.add(bp, 6, i+1);
                            vp.add(bd,7,i+1);
                        }
                    }
                    FlowPane hd = new FlowPane(Orientation.VERTICAL, header, vp);
                    hd.setHgap(10);
                    Scene scene = new Scene(hd);
                    primaryStage.setScene(scene);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        contWaiter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ContWaiter cw = new ContWaiter(c);
                    String[][] scu = cw.toData(role);
                    GridPane vp = new GridPane();
                    vp.getColumnConstraints().add(new ColumnConstraints(10));
                    vp.getColumnConstraints().add(new ColumnConstraints(100, 150, 200));
                    vp.getColumnConstraints().add(new ColumnConstraints(100, 150, 200));
                    vp.getColumnConstraints().add(new ColumnConstraints(100, 150, 200));
                    vp.getColumnConstraints().add(new ColumnConstraints(100, 150, 200));
                    if (role.equals("superuser")){
                        vp.add(new Label("id"),1,0);
                        vp.add(new Label("name"),2,0);
                        vp.add(new Label("address"),3,0);
                        vp.add(new Label("phone number"),4,0);
                    }
                    else {
                        vp.add(new Label("name"), 1, 0);
                        vp.add(new Label("phone number"), 2, 0);
                    }
                    for (int i = 0; i < scu.length; i++){
                        vp.add(new Label(),0,i+1);
                        int j = 0;
                        for (j = 0; j < scu[i].length; j++){
                            vp.add(new Label(scu[i][j]), j+1, i+1);
                        }
                    }
                    FlowPane hd = new FlowPane(Orientation.VERTICAL, header, vp);
                    hd.setHgap(10);
                    Scene scene = new Scene(hd);
                    primaryStage.setScene(scene);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        contIngredient.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ContIngredient ci = new ContIngredient(c);
                    String[][] scu = ci.toData(role);
                    GridPane vp = new GridPane();
                    vp.getColumnConstraints().add(new ColumnConstraints(10));
                    vp.getColumnConstraints().add(new ColumnConstraints(100, 150, 200));
                    vp.getColumnConstraints().add(new ColumnConstraints(100, 150, 200));
                    vp.getColumnConstraints().add(new ColumnConstraints(100, 150, 200));
                    vp.getColumnConstraints().add(new ColumnConstraints(100, 150, 200));
                    vp.getColumnConstraints().add(new ColumnConstraints(40));
                    vp.getColumnConstraints().add(new ColumnConstraints(40));
                        vp.add(new Label("id"),1,0);
                        vp.add(new Label("name"),2,0);
                        vp.add(new Label("unit of measurement"),3,0);
                        vp.add(new Label("quantity in stock"),4,0);
                    for (int i = 0; i < scu.length; i++){
                        vp.add(new Label(),0,i+1);
                        int j = 0;
                        for (j = 0; j < scu[i].length; j++){
                            vp.add(new Label(scu[i][j]), j+1, i+1);
                        }
                        if (role.equals("superuser") && (!scu[i][3].equals("-"))){
                            Button add = new Button("+");
                            Button minus = new Button("-");
                            add.setId(scu[i][0]);
                            minus.setId(scu[i][0]);
                            add.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    try {
                                        c.plusIngredient(Integer.parseInt(add.getId()));
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            });
                            minus.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    try {
                                        c.minusIngredient(Integer.parseInt(minus.getId()));
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            });
                            vp.add(add,6,i+1);
                            vp.add(minus,7,i+1);
                        }
                    }
                    FlowPane hd = new FlowPane(Orientation.VERTICAL, header, vp);
                    hd.setHgap(10);
                    Scene scene = new Scene(hd);
                    primaryStage.setScene(scene);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        contClient.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ContClient cc = new ContClient(c);
                    String[][] scu = cc.toData(role);
                    GridPane vp = new GridPane();
                    vp.getColumnConstraints().add(new ColumnConstraints(10));
                    vp.getColumnConstraints().add(new ColumnConstraints(50, 100, 200));
                    vp.getColumnConstraints().add(new ColumnConstraints(50, 100, 200));
                    vp.getColumnConstraints().add(new ColumnConstraints(250, 300, 400));
                    vp.getColumnConstraints().add(new ColumnConstraints(100, 150, 200));
                    vp.add(new Label("id"),1,0);
                    vp.add(new Label("tax id"),2,0);
                    vp.add(new Label("name"),3,0);
                    vp.add(new Label("address"),4,0);
                    for (int i = 0; i < scu.length; i++){
                        vp.add(new Label(),0,i+1);
                        int j = 0;
                        for (j = 0; j < scu[i].length; j++){
                            vp.add(new Label(scu[i][j]), j+1, i+1);
                        }
                    }
                    FlowPane hd = new FlowPane(Orientation.VERTICAL, header, vp);
                    hd.setHgap(10);
                    Scene scene = new Scene(hd);
                    primaryStage.setScene(scene);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        contTable.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ContTable ct = new ContTable(c);
                    String[][] scu = ct.toData(role);
                    GridPane vp = new GridPane();
                    vp.getColumnConstraints().add(new ColumnConstraints(10));
                    vp.getColumnConstraints().add(new ColumnConstraints(50, 100, 200));
                    vp.getColumnConstraints().add(new ColumnConstraints(50, 100, 200));
                    vp.add(new Label("id"),1,0);
                    vp.add(new Label("max capacity"),2,0);
                    for (int i = 0; i < scu.length; i++){
                        vp.add(new Label(),0,i+1);
                        int j = 0;
                        for (j = 0; j < scu[i].length; j++){
                            vp.add(new Label(scu[i][j]), j+1, i+1);
                        }
                    }
                    FlowPane hd = new FlowPane(Orientation.VERTICAL, header, vp);
                    hd.setHgap(10);
                    Scene scene = new Scene(hd);
                    primaryStage.setScene(scene);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        contDish.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ContDish cd = new ContDish(c);
                    String[][] scu = cd.toData(role);
                    GridPane vp = new GridPane();
                    vp.getColumnConstraints().add(new ColumnConstraints(10));
                    vp.getColumnConstraints().add(new ColumnConstraints(50, 100, 200));
                    vp.getColumnConstraints().add(new ColumnConstraints(50, 100, 200));
                    vp.getColumnConstraints().add(new ColumnConstraints(50, 100, 200));
                    vp.add(new Label("id"),1,0);
                    vp.add(new Label("name"),2,0);
                    for (int i = 0; i < scu.length; i++){
                        vp.add(new Label(),0,i+1);
                        int j = 0;
                        for (j = 0; j < scu[i].length; j++){
                            vp.add(new Label(scu[i][j]), j+1, i+1);
                        }if (!scu[i][0].equals("Итого")){
                            Button bp = new Button("Состав");
                            bp.setId(scu[i][0]);
                            bp.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    try {
                                        ContIngredientDish cid = new ContIngredientDish(c,Integer.parseInt(bp.getId()));
                                        String[][] scu = cid.toData(role);
                                        GridPane vp = new GridPane();
                                        vp.getColumnConstraints().add(new ColumnConstraints(10));
                                        vp.getColumnConstraints().add(new ColumnConstraints(50, 100, 200));
                                        vp.getColumnConstraints().add(new ColumnConstraints(50, 100, 200));
                                        vp.getColumnConstraints().add(new ColumnConstraints(50, 100, 200));
                                        vp.getColumnConstraints().add(new ColumnConstraints(50, 100, 200));
                                        vp.getColumnConstraints().add(new ColumnConstraints(50, 100, 200));
                                        vp.add(new Label("id"), 1, 0);
                                        vp.add(new Label("dish name"), 2, 0);
                                        vp.add(new Label("ingredient name"), 3, 0);
                                        vp.add(new Label("needed amount"), 4, 0);
                                        for (int i = 0; i < scu.length; i++) {
                                            vp.add(new Label(), 0, i + 1);
                                            int j = 0;
                                            for (j = 0; j < scu[i].length; j++) {
                                                vp.add(new Label(scu[i][j]), j + 1, i + 1);
                                            }
                                        }
                                        FlowPane ccid = new FlowPane(Orientation.VERTICAL,header,vp);
                                        Scene scene = new Scene(ccid);
                                        primaryStage.setScene(scene);
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            });
                            vp.add(bp, 6, i+1);
                        }

                    FlowPane hd = new FlowPane(Orientation.VERTICAL, header, vp);
                    hd.setHgap(10);
                    Scene scene = new Scene(hd);
                    primaryStage.setScene(scene);

                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        change.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    c.renameUsers(ID,ch_login.getText(), ch_password.getText());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        backtolog.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(sc_auth);
            }
        });
        settings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ch_login = new TextField(log);
                ch_password = new TextField(pas);
                FlowPane newpaslog = new FlowPane(ch_login, ch_password, change);
                newpaslog.setAlignment(Pos.CENTER);
                screen = new FlowPane(Orientation.VERTICAL, header, newpaslog);
                Scene sc = new Scene(screen);
                primaryStage.setScene(sc);
            }
        });
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(sc_auth);
            }
        });
        auth.setAlignment(Pos.CENTER);
        registration.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (!c.isUser(login.getText(), password.getText())) {
                        c.addUsers(login.getText(),password.getText(), 2);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        authorize.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (c.isUser(login.getText(), password.getText())){
                        exit.setText("Выход");
                        log = login.getText();
                        pas = password.getText();
                        ID = c.getID(login.getText(), password.getText());
                        System.out.println(ID);
                        role = c.getRoleByID(ID);
                        if (role.equals("superuser")){
                            header = new FlowPane(Orientation.HORIZONTAL, contUser, contClient, contWaiter, contDish, contIngredient, contTable, settings, exit);
                        }
                        else if (role.equals("waiter")){
                            header = new FlowPane(Orientation.HORIZONTAL, contWaiter, contDish, contIngredient, contTable, settings, exit);
                        }
                        else{
                            header = new FlowPane(Orientation.HORIZONTAL, contDish, contTable, settings, exit);
                        }
                        screen = new FlowPane(Orientation.VERTICAL,header);
                        Scene sc = new Scene(screen);
                        primaryStage.setScene(sc);
                    }
                    else{
                        header = new FlowPane();
                        exit.setText("Попробовать ещё раз");
                        screen = new FlowPane(retry);
                        Scene scene = new Scene(screen);
                        primaryStage.setScene(scene);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        primaryStage.setScene(sc_auth);
        primaryStage.show();
    }
}

package Adapter;
import main.*;

public class AdapterDB implements Adapt {
    MainInterface mainIn;

    public AdapterDB (String dbType) {

        if (dbType == "sqlite") {
            mainIn = new Main();
        } else if (dbType == "mysql") {
            mainIn = new MainMysql();
        }

    }

    public void connect() {
        mainIn.connect();
    }

}
package Config;

public class DBHandler {

    public DBConfig getHandler(){
        DBConfig config = new DBConfig();
        try {
            config.readConfig();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return config;
    }

}

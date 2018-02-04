package Strategy;

import Strategy.StrategyDBHandler;

import java.sql.Connection;

public class Singleton {
    public StrategyDBHandler database;
    private static Singleton instance = null;
    protected Singleton(String type) {
        if(type.equals("sqlite")){
            database =  new StartegyDBsqlite();
            database.connect();
        }
        else if(type.equals("postgresql")){
            database =  new StrategyPostgre();
            database.connect();
        }
        else if(type.equals("mysql")){
            database =  new StrategyMysql();
            database.connect();
        }
    }

    public static Singleton getInstance(String type) {
        if(instance == null) {
            instance = new Singleton(type);
        }
        return instance;
    }



}

package main;

import Adapter.Adapt;
import Adapter.AdapterDB;

public class Singleton {
    Main m = null;
    private static Singleton instance = null;
    protected Singleton() {
        m = new Main();
    }

    void connect(){
        m.connect();
    }

    void getAll(){
        m.getAll();
    }
    void getOne(int id){
        m.getOne(id);
    }

    void insert(int id, String name,String surname){
        m.insert( id, name, surname);
    }

    void delete(int id){
        m.delete(id);
    }

    void  update(int id, String name,String surname){
        m.update(id,name,surname);
    }

    public static Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public static void main(String[] args){
        Singleton s;
        AdapterDB a = new AdapterDB("mysql");
        MainMysql mm = new MainMysql();
        a.connect();

        a = new AdapterDB("sqlite");
        Main m = new Main();
        a.connect();

        s = getInstance();
        s.connect();
        s.getAll();
    }
}


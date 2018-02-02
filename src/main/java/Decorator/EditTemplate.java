package Decorator;

import Builder.*;

import java.io.IOException;

public abstract class EditTemplate implements HTMLConfigure {

    protected HTMLConfig conf;
    public EditTemplate(HTMLConfig c){
        this.conf = c;
    }

    public String readConfig(String naem, String parameter) throws IOException {

        String comments = "";
        try {
            comments = conf.readConfig(naem, parameter);
        }
        catch(Exception e){
            System.out.println("There was a problem with accessing the configuration file.");
            e.printStackTrace();
        }
        return comments;

    }
}

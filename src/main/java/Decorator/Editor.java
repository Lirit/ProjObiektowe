package Decorator;

import Builder.HTMLConfig;
import Builder.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Editor extends EditTemplate{

    public Editor(HTMLConfig c){
        super(c);
    }

    public String readConfig(String naem, String parameter) throws IOException {
        StringBuilder tempHTML = new StringBuilder();
        String comments = super.readConfig(naem,parameter);
        String str = "";
        if(comments.equals("remove")){
        String pattern_comment = "<!--(.*?)-->";
        Test t = new Test();
        String html = t.getTemp().returnHTML();
        str = html.replaceFirst(pattern_comment, "");
        }
        return str;
    }
}

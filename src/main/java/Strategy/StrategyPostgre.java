package Strategy;

import main.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class StrategyPostgre implements StrategyDBHandler {
    Connection con = null;

    @Override
    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch(ClassNotFoundException cnf){
            cnf.printStackTrace();
        }
        String data = "jdbc:postgresql://localhost:5432/testdb";
        try {
            String uName = "";
            String uPass = "";
            con = DriverManager.getConnection(data, uName, uPass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(Person p){
        Statement stmt = null;

        String query =
                "INSERT INTO ADRESS_BOOK (ID,NAME,SURNAME, NUMBER,MAIL) VALUES (?,?,?,?,?);";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, p.getId());
            pstmt.setString(2,p.getName());
            pstmt.setString(3,p.getSurname());
            pstmt.setInt(4, p.getTelephone());
            pstmt.setString(5,p.getMail());
            pstmt.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void remove(int id){
        String query = "DELETE FROM ADRESS_BOOK WHERE id = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public Person getbyId(int id){
        Person result = new Person();
        Statement stmt = null;
        String query = "select ID, NAME, SURNAME, NUMBER, MAIL from ADRESS_BOOK WHERE ID = ? " ;
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs  = pstmt.executeQuery();
            while (rs.next()) {
                result.setId(rs.getInt("ID"));
                result.setName(rs.getString("NAME"));
                result.setSurname(rs.getString("SURNAME"));
                result.setTelephone(rs.getInt("NUMBER"));
                result.setMail(rs.getString("MAIL"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public Map<Integer, Person> getAll(){
        Statement stmt = null;
        Map<Integer, Person> result = new HashMap<Integer, Person>();
        String query =
                "select ID, NAME, SURNAME, NUMBER, MAIL " +
                        "from " + "ADRESS_BOOK" ;

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Person tmp = new Person();
                int id = rs.getInt("ID");
                tmp.setId(rs.getInt("ID"));
                tmp.setName( rs.getString("NAME"));
                tmp.setSurname( rs.getString("SURNAME"));
                tmp.setTelephone( rs.getInt("NUMBER"));
                tmp.setMail(rs.getString("MAIL"));
                result.put(id,tmp);
            }
        } catch (SQLException e ) { e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();

                }
                catch(Exception exz){}
            }
        }
        return result;
    }

    public JSONArray toJson(Map<Integer, Person> map){
        JSONArray jsa = new JSONArray();
        for(Map.Entry<Integer, Person> entry : map.entrySet()){
            JSONObject jObj = new JSONObject();
            Person tmp = entry.getValue();
            jObj.put("id",tmp.getId());
            jObj.put("name",tmp.getName());
            jObj.put("surname",tmp.getSurname());
            jObj.put("phone",tmp.getTelephone());
            jObj.put("e-mail",tmp.getMail());
            jsa.add(jObj);
        }
        return jsa;
    }
}

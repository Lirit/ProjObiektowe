import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainMysql implements MainInterface {


    Connection c = null;
    Statement stmt = null;
    static final String MSG = "Exception occurred: ";

    public void connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            c = DriverManager.getConnection("jdbc:mysql://localhost/Baza_1","root","root");
            c.close();
        }catch(Exception e){
            Logger logger = Logger.getAnonymousLogger();

            logger.log(Level.INFO,MSG, e);
        }
        finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
                Logger logger = Logger.getAnonymousLogger();
                logger.log(Level.INFO,MSG, se2);
            }
            try{
                if(c!=null){
                    c.close();
                }
            }catch(SQLException se){
                Logger logger = Logger.getAnonymousLogger();
                logger.log(Level.INFO,MSG, se);
            }
        }
    }

    public List<Person> getAll() {
        List<Person> resultList = new ArrayList<Person>();
        ResultSet rs = null;
        try{
            stmt = c.createStatement();
            String sql;
            sql = "SELECT * FROM People";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                Person tempP = new Person();

                //Retrieve by column name
                int id  = rs.getInt("id");
                String name  = rs.getString("name");
                String surname = rs.getString("surname");

                tempP.setId(id);
                tempP.setName(name);
                tempP.setSurname(surname);
                resultList.add(tempP);
            }
        }
        catch(Exception e){
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.INFO,MSG, e);
        }
        finally{
            try{
                if(rs != null){
                    rs.close();
                }
            }
            catch(SQLException ex){
                Logger logger = Logger.getAnonymousLogger();
                logger.log(Level.INFO,MSG, ex);
            }
            try {
                if (stmt != null)
                    stmt.close();
            }catch (Exception ex){
                Logger logger = Logger.getAnonymousLogger();
                logger.log(Level.INFO,MSG, ex);
            }
        }
        return resultList;
    }


    Person getOne(int id){
        Person resultPerson = new Person();
        ResultSet rs = null;
        PreparedStatement prp = null;
        try{
            stmt = c.createStatement();
            String sql;
            sql = "SELECT * FROM People WHERE id=?";
            prp = c.prepareStatement(sql);
            prp.setInt(1,id);
            rs = prp.executeQuery();
            while(rs.next()) {
                //Retrieve by column name
                int idNum = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");

                //Display values
                resultPerson.setId(idNum);
                resultPerson.setName(name);
                resultPerson.setSurname(surname);
            }
        }
        catch(Exception e){
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.INFO,MSG, e);
        }
        finally{
            try{
                if(prp != null){
                    prp.close();}
                if(rs != null){
                    rs.close();
                }}
            catch(Exception exc){
                Logger logger = Logger.getAnonymousLogger();
                logger.log(Level.INFO,MSG, exc);
            }
            try {
                if (stmt != null)
                {stmt.close();}

            }catch (Exception ex){
                Logger logger = Logger.getAnonymousLogger();
                logger.log(Level.INFO,MSG, ex);
            }
        }
        return resultPerson;
    }


    void insert(int id, String name,String surname) {
        ResultSet rs = null;
        PreparedStatement prp = null;
        if((name != null) && (surname != null)){
            try {
                stmt = c.createStatement();
                String sql;
                sql = "INSERT INTO People VALUES (?,?,?)";
                prp = c.prepareStatement(sql);
                prp.setInt(1,id);
                prp.setString(2,name);
                prp.setString(3,surname);
                rs = prp.executeQuery();
                rs.close();
            } catch (Exception e) {
                Logger logger = Logger.getAnonymousLogger();
                logger.log(Level.INFO,MSG, e);
            } finally {
                try {
                    if(prp != null){
                        prp.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (Exception ex) {
                    Logger logger = Logger.getAnonymousLogger();
                    logger.log(Level.INFO,MSG, ex);
                }
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception ex) {

                    Logger logger = Logger.getAnonymousLogger();
                    logger.log(Level.INFO,MSG, ex);
                }
            }
        }
    }

    void delete(int id){
        try{
            stmt = c.createStatement();
            String sql = "DELETE FROM Registration " +
                    "WHERE id = "+id;
            stmt.executeUpdate(sql);
        }
        catch(Exception e){
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.INFO,MSG, e);
        }

    }
    void  update(int id, String name,String surname){
        try{
            stmt = c.createStatement();
            String sql = "UPDATE Registration " +
                    "SET name ="+name+",surname="+surname+" WHERE id="+id;
            stmt.executeUpdate(sql);
        }
        catch (Exception e){
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.INFO,MSG, e);
        }
    }
}


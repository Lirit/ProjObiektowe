import java.sql.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    Connection c = null;
    Statement stmt = null;

    public void connect(){
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite://localhost/Baza_1","root","root");
            c.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(c!=null){
                    c.close();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    public void getAll() {
        try{
        stmt = c.createStatement();
        String sql;
        sql = "SELECT * FROM People";
        ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    //Retrieve by column name
                    int id  = rs.getInt("id");
                    String name  = rs.getString("name");
                    String surname = rs.getString("surname");

                    //Display values
                    System.out.print("ID: " + id);
                    System.out.print(", Name: " + name);
                    System.out.print(", Surname: " + surname);
                }
        rs.close();
        stmt.close();}
        catch(Exception e){}
        finally{try {
                if (stmt != null)
                    stmt.close();
            }catch (Exception ex){

            }
            }
    }
    void getOne(int id){
    try{
        stmt = c.createStatement();
        String sql;
        sql = "SELECT * FROM People WHERE 'id'="+id;
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            //Retrieve by column name
            int id_1  = rs.getInt("id");
            String name  = rs.getString("name");
            String surname = rs.getString("surname");

            //Display values
            System.out.print("ID: " + id_1);
            System.out.print(", Name: " + name);
            System.out.print(", Surname: " + surname);
        }
        rs.close();
        stmt.close();}
        catch(Exception e){}
        finally{try {
        if (stmt != null)
            stmt.close();
    }catch (Exception ex){

    }
    }}


    void insert(int id, String name,String surname){
        ResultSet rs = null;
        try{
            stmt = c.createStatement();
            String sql;
            sql = "INSERT INTO People VALUES ("+id+","+name+","+surname+")";
            rs = stmt.executeQuery(sql);
            System.out.println("Inserted into database.");
            rs.close();
            stmt.close();
            c.close();
        }
    catch(Exception e){}
    finally{
        try {
        if (stmt != null){
            stmt.close();
        }
    }catch (Exception ex){}
            try {
                if (rs != null){
                    rs.close();
                }
            }catch (Exception ex){}
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
        }

    }
   void  update(int id, String name,String surname){
       try{
            stmt = c.createStatement();
            String sql = "UPDATE Registration " +
               "SET name ="+name+",surname="+surname+" WHERE id="+id;
       stmt.executeUpdate(sql);
}catch (Exception e){

}
    }
}

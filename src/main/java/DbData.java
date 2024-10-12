import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbData {
    //Database Credentials
    private static String dbUrl; // table details
    private static String dbUser;
    private static String dbPass;
    //Database Index
    private static String header;
    private static String context;
    private static Date date;

    private List<String> headers;
    private List<String> contexts;
    private List<Date> dates;
    private List<Integer> ids;
   /* public DbData(String url, String user, String password) {
        url=dbUrl;
        user=dbUser;
        password=dbPass;

        try{
            Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        }catch (SQLException e){
            System.out.println(e);
        }
    }*/

    public DbData(){

        headers = new ArrayList<>();
        contexts = new ArrayList<>();
        dates = new ArrayList<>();
        ids = new ArrayList<>();

        getData();
    }

    public String getHeader(int index){
        return headers.get(index);
    }

    public String getContext(int index){
        return contexts.get(index);
    }
    public Date getDate(int index){
        return dates.get(index);
    }
    public Integer getId(int index){return ids.get(index);}
    public int getHeaderListSize(){
        return headers.size();
    }
    public int getConextListSize(){
        return contexts.size();
    }

    public void getData(){
        dbUrl="jdbc:mysql://avnadmin:AVNS_b1GW5bgz7-zCbovjbiz@approject-dannyzincher-a05d.b.aivencloud.com:28101/defaultdb?ssl-mode=REQUIRED";
        dbUser="avnadmin";
        dbPass="AVNS_b1GW5bgz7-zCbovjbiz";

        try{
            Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            PreparedStatement prestmt = con.prepareStatement("SELECT * FROM blog");
            ResultSet rs = prestmt.executeQuery();
            headers.clear();
            contexts.clear();
            dates.clear();
            ids.clear();
            while(rs.next()){
                headers.add(rs.getString("header"));
                contexts.add (rs.getString("context"));
                dates.add(rs.getDate("date"));
                ids.add(rs.getInt("id"));
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    public void setAllData(String newHeader,String newContext,Date newDate, Integer newId){
        try{
            Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            PreparedStatement prestmt = con.prepareStatement("INSERT INTO `blog`(header, context, date, id) VALUES (?,?,?,?) ");

            prestmt.setString(1,newHeader);
            prestmt.setString(2,newContext);
            prestmt.setDate(3,newDate);
            prestmt.setInt(4,newId);

            prestmt.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void removeAllData(Integer theId){

        try{
            Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            PreparedStatement prestmt = con.prepareStatement("DELETE FROM blog WHERE id=?; ");

            prestmt.setInt(1,theId);

            prestmt.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    public String getAllDataById(Integer theId){

        String header;
        String context;
        Date date;
        Integer id;
        String stringToRemove = null;

        try{
            Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            PreparedStatement prestmt = con.prepareStatement("SELECT * FROM blog WHERE id=?; ");

            prestmt.setInt(1,theId);
            ResultSet rs = prestmt.executeQuery();
            prestmt.executeUpdate();

            while(rs.next()){
                header=rs.getString("header");
                context=rs.getString("context");
                date=rs.getDate("date");
                id=rs.getInt("id");
                stringToRemove = "Header " + ": " + header +"\nDate: "+ date + "\nContext: "+context+"\nID: "+id+"\n\n\n";
            }

            return stringToRemove;

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }




}

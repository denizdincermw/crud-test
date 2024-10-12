import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scnr = new Scanner(System.in);

    static String url = "jdbc:postgresql://localhost:5432/postgres"; // table details
    static String username = "postgres";
    static String password = "dincer1";
    //MySql infos--------------------------------------------------------
    private static String myUrl = "jdbc:mysql://avnadmin:AVNS_b1GW5bgz7-zCbovjbiz@approject-dannyzincher-a05d.b.aivencloud.com:28101/defaultdb?ssl-mode=REQUIRED"; // table details
    private static String myUser = "avnadmin";
    private static String myPass = "AVNS_b1GW5bgz7-zCbovjbiz";
    public static void main(String[] args) {

      MainWindow.start();
      //SetDataWindow.start();
        //RemoveData.start();
    }
    public static void intro(){
        System.out.println("-----------------------\n1)Get all datas\n2)Set a data\n3)Remove a data\n4)Quit\nWhat do u wanna do");
        short choice = scnr.nextShort();

        switch(choice){
            case 1:getData();intro();
                break;
            case 2:setData();intro();
                break;
            case 3:removeData();intro();
                break;
            case 4:break;
        }

    }
    public static void getData(){

        ArrayList<String> results = new ArrayList<String>();
        try{
            // Connection con = DriverManager.getConnection(url, username, password);
            Connection con = DriverManager.getConnection(myUrl, myUser, myPass);

            PreparedStatement prestmt = con.prepareStatement("SELECT * FROM crud_test");
            ResultSet rs = prestmt.executeQuery();

            while(rs.next()){
                //main get columns
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Date birthDate = rs.getDate("birthDate");

                results.add(id+"|"+firstName +" "+ lastName+ "|" + birthDate);
            }

            for(String result:results){
                System.out.println(result);
            }


            // use con here
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void setData(){

        //ask to user
        System.out.print("Enter an ID for the new user: ");
        int id = scnr.nextInt();
        System.out.print("Enter the firstname of the new user: ");
        String firstName= scnr.next();
        System.out.print("Enter the lastname of the new user: ");
        String lastName= scnr.next();
        System.out.println("Enter the birthdate of the new user(YYYY-MM-DD): ");
        String birthDate= scnr.next();

        try{
            Connection con = DriverManager.getConnection(myUrl, myUser, myPass);
            PreparedStatement prestmt = con.prepareStatement("INSERT INTO `crud_test`(id,firstName,lastName,birthDate) VALUES (?,?,?,?) ");

            prestmt.setInt(1,id);
            prestmt.setString(2,firstName);
            prestmt.setString(3,lastName);
            prestmt.setDate(4,Date.valueOf(birthDate));

            int rowsAffected = prestmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("The new user created!");
                System.out.println("-------------------------------------");
            } else {
                System.out.println("Failed to create the new user.");
            }

            getData();
            System.out.println("-------------------------------------");

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void updateData(){
        try{
            Connection con = DriverManager.getConnection(myUrl, myUser, myPass);


        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void removeData(){
        try{
            Connection con = DriverManager.getConnection(myUrl, myUser, myPass);
            PreparedStatement prestmt = con.prepareStatement("DELETE FROM crud_test WHERE id=?");

            System.out.println("Please enter the ID of the user who you want to remove: ");
            int id = scnr.nextInt();
            prestmt.setInt(1,id);

            System.out.println("Do you really wanna remove that data?(Y/N)");
            String realDecision=scnr.next();
            if(realDecision.equalsIgnoreCase("y")){
                int rowsAffected = prestmt.executeUpdate();
                if(rowsAffected>0){
                    System.out.println("The user removed!");
                    getData();
                }else{
                    System.out.println("there is a problem man");
                    getData();
                }
            } else if (realDecision.equalsIgnoreCase("n")) {
                System.out.println("You were so close man");
                getData();
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}

package lab05;


import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Scanner;

public class lab05 {
 // JDBC driver name and database URL
 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
 static final String DB_URL = "jdbc:mysql://localhost:3306/lab5";

 //  Database credentials
 static final String USER = "root";
 static final String PASS = "";
 
 public static void main(String[] args) {
 Connection conn = null;
 Statement stmt = null;
 try{
    //STEP 2: Register JDBC driver
    Class.forName("com.mysql.jdbc.Driver");

    //STEP 3: Open a connection
    System.out.println("Connecting to database...");
    conn = DriverManager.getConnection(DB_URL,USER,PASS);

    //STEP 4: Execute a query
    System.out.println("Creating statement...");
    stmt = conn.createStatement();
    String sql;
    sql = "use `lab5`;";
   String sql2 = "CREATE table IF NOT exists `mytable` ( `locId` smallint,`country` char(50),`region` char(50),`city` char(50),`postalCode` char(50),`latitude` float,`longitude` float,`metroCode` int,`areaCode`  int,  primary key    (`locid`));";
   String sql3 = "CREATE database IF NOT EXISTS `lab5`;";// ResultSet rs = 
   System.out.println("Creating Database..");
   stmt.executeUpdate(sql3);
   
   System.out.println("Selecting Database..");
    stmt.executeUpdate(sql);
    
    System.out.println("Creating table...");
    stmt.executeUpdate(sql2);

  /*  String splitBy = ",";
BufferedReader read = new BufferedReader(new FileReader("C://Users//Muhammad Umar//Desktop//GeoLiteCity-Location.csv"));
		String line = null;
		int mycounter = 0;
		System.out.println("Populating Table from CSV file.. Please wait...");
		while ((line = read.readLine()) != null) {
			if (mycounter > 1){
		String[] items= line.split(",",-1);
                            for(int i = 0;i<9;i++){
                            if(items[i].length() == 0){
                            items[i] = "0";
                            }
                            }

String insertSql = "INSERT INTO mytable Values(" + items[0] + ",'" +items[1]+" ','" +items[2]+"','" +items[3]+"','" +items[4]+"','" +items[5]+"','" +items[6]+"','" +items[7]+"','" +items[7]+"');";

stmt.executeUpdate(insertSql);

			}
			mycounter = mycounter + 1;
 }*/
		System.out.println("File added..!!");
		System.out.println("*******************User interface**********************");
		System.out.println("Choose from the following menu:\n1) Search by city\n2)Search by longitutude/lattitude\n");
		System.out.println("Your Choice");
		Scanner reader = new Scanner(System.in); 
		int choice = reader.nextInt();
		if(choice == 1){
			System.out.println("Enter City name: \n");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String city = br.readLine();
		//System.out.println(city);
		String sql4 ="select latitude,longitude from lab5.mytable where city = '\""+city+"\"';";
		System.out.println(sql4);
		ResultSet rs = stmt.executeQuery(sql4);
		while(rs.next()){
		       //Retrieve by column name
		       double lat  = rs.getDouble("latitude");
		       double longi  = rs.getDouble("longitude");

		       //Display values
		       System.out.println("\nlatitude: " + lat);
		    }
		stmt.close();
		conn.close(); 
		}
 }catch(SQLException se){
    //Handle errors for JDBC
    se.printStackTrace();
 }catch(Exception e){
    //Handle errors for Class.forName
    e.printStackTrace();
 }finally{
    //finally block used to close resources
    try{
       if(stmt!=null)
          stmt.close();
    }catch(SQLException se2){
    }// nothing we can do
    try{
       if(conn!=null)
          conn.close();
    }catch(SQLException se){
       se.printStackTrace();
    }//end finally try
 }//end try
 System.out.println("\nGoodbye!");
}//end main
}//end FirstExample

package ezstock;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
	 String url ;
	    String user ;
	    String password ;
	    Connection dbConn ;
	    public boolean connected;
	    
	   public DBConnection(){  
		   
	   }
	   
	   public DBConnection(String user, String pwd, String url){
		   this.user = user;
		   this.password = pwd;
		   this.url = url;
	   }
	   
	   public Connection openConnection() {
	       try {
	           Class.forName("org.postgresql.Driver");
	           String url = "jdbc:postgresql://localhost:5432/postgres";
	           String userName = "postgres" ;
	           String password = "1234" ;
	           dbConn  = DriverManager.getConnection
	                   (url,userName,password);
	           System.out.println(dbConn.getMetaData());
	           connected = true;
	          
	       }catch (Exception ex) {            
	    	   ex.printStackTrace();
	    	   connected = false;
	       }
	       return dbConn;
	   }  
}


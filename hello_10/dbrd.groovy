import java.sql.*; 
import groovy.sql.Sql 

class Testrd {
   static void main(String[] args) {
      // Creating a connection to the database
      def sql = Sql.newInstance('jdbc:postgresql://localhost:5432/mydb', 
         'test', '1234', 'org.postgresql.Driver')
			
      // Executing the query SELECT VERSION which gets the version of the database
      // Also using the eachROW method to fetch the result from the database
   
      sql.eachRow('SELECT * from brch_qry_dtl'){ row ->
         println row
      }
		
      sql.close()  
   } 
}


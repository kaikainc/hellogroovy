import java.sql.*; 
import groovy.sql.Sql 

class Testwr {
   static void main(String[] args) { 
      // Creating a connection to the database
      def sql = Sql.newInstance('jdbc:postgresql://localhost:5432/mydb', 
         'test', '1234', 'org.postgresql.Driver')
			
      sql.connection.autoCommit = false
		
      def sqlstr = """INSERT INTO brch_qry_dtl(acc,
         tran_date, amt, dr_cr_flag, rpt_sum, timestampl)
         VALUES ('2', cast('2020-03-01' as date), 20, '1', '1','20200301010101')""" 
      try {
         sql.execute(sqlstr);
         sql.commit()
         println("Successfully committed") 
      }catch(Exception ex) {
         sql.rollback()
         println("Transaction rollback") 
      }
		
      sql.close()
   } 
}
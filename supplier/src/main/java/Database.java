import java.sql.*;

/**
 * Created by מחשב on 17/04/2018.
 */
public class Database {
    Connection conn = null ;

    Database(Connection conn){
        this.conn = conn;
    }

    public String add_Supplier(Supplier supplier) {
        String output = "";

        try (Statement stmt  = conn.createStatement()){
            // loop through the result set
            stmt.executeUpdate("INSERT INTO Supplier " + "VALUES (\"" +supplier.supplierId +"\",\"" +
                    supplier.bankAccount +"\",\"" + supplier.payment + "\",\"" + supplier.supplyForm+ ")");
            output = "Add supplier succeeded";
        } catch (SQLException e) {
            output = "Add supplier failed because: " + e.getMessage();
        }
        return output;
    }

    private String updateSupplier(String supplierId ,String filed, String value){

        String sql = "UPDATE Supplier SET " +filed+ "= ?  where supplierId = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(2, supplierId);
        if(value =="bankAccount") {
            pstmt.setInt(1, Integer.parseInt(value));
        }
        else{
            pstmt.setString(1, value);
        }
            pstmt.executeUpdate();
            return "succeed";
        } catch (SQLException e) {
            return "failed";
        }

    }

    public Supplier select_supplier(String supplierId) {
        String sql = "SELECT * FROM Supplier WHERE supplierId \"" + supplierId + "\"";
        Supplier s = new Supplier();
        try (Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {

                s.supplierId = rs.getString("supplierId");
                s.supplyForm = rs.getString("supplyForm");
                s.payment =  rs.getString("payment");
                s.bankAccount = rs.getInt("bankAccount");
            }
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            System.out.println("faild select supplier");

        }
        return s;
    }



}



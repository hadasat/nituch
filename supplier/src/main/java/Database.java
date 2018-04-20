import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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



    ////////////////item:****************

    public String add_item(Item item) {
        String output = "";

        try (Statement stmt  = conn.createStatement()){
            // loop through the result set
            stmt.executeUpdate("INSERT INTO Item " + "VALUES (\"" +item.catalogId+"\",\"" +
                    item.supplierId +"\",\"" + item.price  +")");
            output = "Add item succeeded";
        } catch (SQLException e) {
            output = "Add item failed  ";
        }
        return output;
    }


    private String updateItem(String supplierId ,String CatalogId, String new_price_value){

        String sql = "UPDATE Item SET " +"price"+ "= ?  where supplierId = ? AND CatalogId = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {


            pstmt.setInt(1, Integer.parseInt(new_price_value));
            pstmt.setString(2, supplierId);
            pstmt.setString(3, CatalogId);

            pstmt.executeUpdate();
            return "updateItem succeed";
        } catch (SQLException e) {
            return "updateItem failed";
        }

    }



    public Item select_Item(String supplierId,String catalogId ) {
        String sql = "SELECT * FROM Item WHERE catalogId=" +catalogId+ " AND supplierId =" + supplierId  ;
        Item newItem = new Item();
        try (Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {

                newItem.supplierId = rs.getString("supplierId");
                newItem.catalogId = rs.getString("catalogId");
                newItem.price =  rs.getInt("price");

            }
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            System.out.println("faild select Item");

        }
        return newItem;
    }





    //*********************************end item::


    ////////////////////////////order:

    public String add_order(Order order) {
        String output = "";

        try (Statement stmt  = conn.createStatement()){
            // loop through the result set
            stmt.executeUpdate("INSERT INTO Oredrs " + "VALUES (\"" +order.supplierId+"\",\"" + order.orderId +"\",\""+
                    order.quanttity +"\",\"" + order.orderDate  +"\",\"" +order.recived  +"\",\"" +order.arrivalDate +")");
            output = "Add order succeeded";
        } catch (SQLException e) {
            output = "Add order failed  ";
        }
        return output;
    }



    private String updateOrder(String item ,String filed, String value){

        String sql = "UPDATE Oredrs SET " +filed+ "= ?  where item = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(2, item);
            if(filed =="quanttity") {
                pstmt.setString(1, value);
            }
            else if(filed =="orderDate"||filed =="arrivalDate") {
                pstmt.setDate(1, Date.valueOf(value));
            }
            else if(filed =="recived" ){
                pstmt.setInt(1, Integer.parseInt(value));
            }

            pstmt.executeUpdate();
            return "succeed";
        } catch (SQLException e) {
            return "failed";
        }

    }

    public List<Order> select_Order(String supplierId) {
        String sql = "SELECT * FROM Oredrs WHERE supplierId=" +supplierId  ;


        List<Order> newOrder = new ArrayList<Order>();


        try (Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {

                Order tmpO = new Order();
                tmpO.supplierId = rs.getString("supplierId");
                tmpO.orderId = rs.getString("orderId");
                tmpO.quanttity = rs.getString("quanttity");
                tmpO.arrivalDate = rs.getDate("arrivalDate");
                tmpO.orderDate = rs.getDate("orderDate");
                tmpO.recived=rs.getInt("recived");
                newOrder.add(tmpO);

            }
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            System.out.println("faild select Order");

        }
        return newOrder;
    }





    ///////************************************



}



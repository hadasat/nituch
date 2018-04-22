import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by מחשב on 17/04/2018.
 */
public class Database {
    Connection connection = null ;

    Database()
    {
        String url = "jdbc:sqlite:Suppliers.db";
        try {
            this.connection = DriverManager.getConnection(url);
        } catch (Exception var3) {
            System.out.println(var3);
            System.out.println("not");
        }

    }


    public String add_Supplier(Supplier supplier) {
        String output = "";

        try (Statement stmt  = connection.createStatement()){
            System.out.println("INSERT INTO Supplier VALUES (" +supplier.supplierId +"," +
                    supplier.bankAccount +",\"" + supplier.payment + "\",\"" + supplier.supplyForm+ "\")");
            // loop through the result set
            stmt.executeUpdate("INSERT INTO Supplier VALUES (" +supplier.supplierId +"," +
                    supplier.bankAccount +",\"" + supplier.payment + "\",\"" + supplier.supplyForm+ "\");");
            // stmt.executeUpdate("INSERT INTO Supplier VALUES (123,123,'124','123')");

            System.out.println("Add supplier succeeded");
        } catch (SQLException e) {
            System.out.println("Add supplier failed" );
        }
        return output;
    }


    public String updateSupplier(int supplierId ,String filed, String value){

        String sql = "UPDATE Supplier SET " +filed+ "= ?  where supplierId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(2, supplierId);
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

    public Supplier select_supplier(int supplierId) {
        String sql = "SELECT * FROM Supplier WHERE supplierId =" + supplierId ;
        Supplier s = new Supplier();
        try (Statement stmt  = connection.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {

                s.supplierId = rs.getInt("supplierId");
                s.supplyForm = rs.getString("supplyForm");
                s.payment =  rs.getString("payment");
                s.bankAccount = rs.getInt("bankAccount");
            }
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            System.out.println("faild select supplier"+e);

        }
        return s;
    }


    public  List<Supplier> select_ALL_supplier() {
        List<Supplier> s = new LinkedList<>();
        String sql = "SELECT * FROM Supplier ";

        try (Statement stmt  = connection.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                Supplier sT = new Supplier();

                sT.supplierId = rs.getInt("supplierId");
                sT.supplyForm = rs.getString("supplyForm");
                sT.payment =  rs.getString("payment");
                sT.bankAccount = rs.getInt("bankAccount");
                s.add(sT);
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

        try (Statement stmt  = connection.createStatement()){
            // loop through the result set
            stmt.executeUpdate("INSERT INTO Item  VALUES ( "+item.catalogId+"," +
                    item.supplierId +"," + item.price  +",\"" + item.manufacturer +  "\");");
            output = "Add item succeeded";
        } catch (SQLException e) {
            output = "Add item failed";
        }
        return output;
    }


    public String updateItem(int supplierId ,int CatalogId, int new_price_value){

        String sql = "UPDATE Item SET price= ?  where supplierId = ? AND CatalogId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, new_price_value);
            pstmt.setInt(2, supplierId);
            pstmt.setInt(3, CatalogId);
           // pstmt.setString(3, m);

            pstmt.executeUpdate();
            return "updateItem succeed";
        } catch (SQLException e) {
            return "updateItem failed";
        }

    }



    public List<Item> select_Item(int supplierId, int catalogId ) {
        String sql = "SELECT * FROM Item WHERE catalogId=" +catalogId+ " AND supplierId =" + supplierId;

        List<Item> output = new LinkedList<>();
        try (Statement stmt  = connection.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                Item newItem = new Item();
                newItem.supplierId = rs.getInt("supplierId");
                newItem.catalogId = rs.getInt("catalogId");
                newItem.price =  rs.getInt("price");
                newItem.manufacturer =  rs.getString("manufacturer");
                output.add(newItem);
            }
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            System.out.println("faild select Item");
        }
        return output;
    }


    public List<Item> select_ALL_Item() {
        String sql = "SELECT * FROM Item ";

        List<Item> output = new LinkedList<>();
        try (Statement stmt  = connection.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                Item newItem = new Item();
                newItem.supplierId = rs.getInt("supplierId");
                newItem.catalogId = rs.getInt("catalogId");
                newItem.price =  rs.getInt("price");
                newItem.manufacturer =  rs.getString("manufacturer");
                output.add(newItem);
            }
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            System.out.println("faild select Item");
        }
        return output;
    }





    //*********************************end item::


    ////////////////////////////order:

    public void add_order(Order order) {


        String output = "";

        try {


            Supplier s = select_supplier(order.supplierId);
            output = s.payment;
            if (output.equals("")) {
                output = "Add Order failed no such supplier";

            } else {
                try (Statement stmt = connection.createStatement()) {
                    // loop through the result set
                    stmt.executeUpdate("INSERT INTO Orders VALUES (" + order.supplierId + "," + order.orderId +","+order.catalogId+","+
                            order.quanttity+",\"" + order.orderDate + "\"," + order.recived + ",\"" + order.arrivalDate + "\")");
                    output = "Add Order succeeded";
                } catch (SQLException e) {
                    output = "Add Order failed" ;
                }

            }

        }catch (Exception e){
            output = "Add Order failed" ;
        }
        System.out.println(output);
    }

    public void updateOrder(int catalogId ,String filed, String value){

        String sql = "UPDATE Oredrs SET " +filed+ "= ?  where catalogId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(2, catalogId);
            if(filed =="quanttity"||filed =="recived" ) {
                pstmt.setInt(1,  Integer.parseInt(value));
            }
            else if(filed =="orderDate"||filed =="arrivalDate") {
                pstmt.setString(1,value);
            }

            pstmt.executeUpdate();

            System.out.println("succeed");
        } catch (SQLException e) {

            System.out.println( "failed");
        }

    }

    public List<Order> select_Order(int supplierId) {
        String sql = "SELECT * FROM Oredrs WHERE supplierId=" +supplierId  ;


        List<Order> newOrder = new ArrayList<Order>();


        try (Statement stmt  = connection.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {

                Order tmpO = new Order();
                tmpO.supplierId = rs.getInt("supplierId");
                tmpO.orderId = rs.getInt("orderId");
                tmpO.quanttity = rs.getInt("quanttity");
                tmpO.arrivalDate = rs.getString("arrivalDate");
                tmpO.orderDate = rs.getString("orderDate");
                tmpO.recived=rs.getInt("recived");
                newOrder.add(tmpO);

            }
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            System.out.println("faild select Order");

        }
        return newOrder;
    }



    public List<Order> select_Not_Recived_Orders(int supplierId) {
        String sql = "SELECT * FROM Oredrs WHERE supplierId=" +supplierId +"AND recived = 0"  ;


        List<Order> newOrder = new ArrayList<Order>();


        try (Statement stmt  = connection.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {

                Order tmpO = new Order();
                tmpO.supplierId = rs.getInt("supplierId");
                tmpO.orderId = rs.getInt("orderId");
                tmpO.quanttity = rs.getInt("quanttity");
                tmpO.arrivalDate = rs.getString("arrivalDate");
                tmpO.orderDate = rs.getString("orderDate");
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



    ////discount:_______________________------------



    public void add_Discount(Discount discount) {
        String output = "";

        try (Statement stmt  = connection.createStatement()){
            // loop through the result set
            stmt.executeUpdate("INSERT INTO Discount " + "VALUES (" +discount.catalogId +"," +
                    discount.quanttity +"," + discount.discount  );
            output = "Add supplier succeeded";
        } catch (SQLException e) {
            output = "Add supplier failed" ;
        }
        System.out.println(output);
    }

    public void updateDiscount(int catalogId ,int quanttity ,int newDiscount){

        String sql = "UPDATE Discount SET discount= ?  where catalogId = ? AND quanttity = ? ";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, newDiscount);
            pstmt.setInt(2, catalogId);
            pstmt.setInt(3, quanttity);

            pstmt.executeUpdate();
            System.out.println("succeed");
        } catch (SQLException e) {
            System.out.println( "failed");
        }

    }

    public Discount select_Discount(int catalogId, int quanttity) {
        String sql = "SELECT * FROM Discount WHERE catalogId=" +catalogId+ " AND quanttity =" + quanttity  ;
        Discount d = new Discount();
        try (Statement stmt  = connection.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {

                d.catalogId = rs.getInt("catalogId");
                d.quanttity = rs.getInt("quanttity");
                d.discount = rs.getInt("discount");
            }
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            System.out.println("faild select Discount");

        }
        return d;
    }



    ////////////**********************************

    ////contact:_______________________------------



    public void add_Contact(Contact con) {
        String output = "";

        try {
            Supplier s = select_supplier(con.supplierId);
            output = s.payment;
            if(output.equals("")){
                output = "Add Contact failed no such supplier" ;

            }else{
                try (Statement stmt  = connection.createStatement()){
                    // loop through the result set
                    stmt.executeUpdate("INSERT INTO Contact " + "VALUES (" +con. supplierId+",\"" +
                            con.firstName +"\",\"" + con.lastName +"\",\"" + con.phoneNumber +"\",\"" + con.email   +"\")");
                    output = "Add Contact succeeded";
                } catch (SQLException e) {
                    output = "Add Contact failed"+e ;
                }

            }


        }catch (Exception e){
            output = "Add Contact failed no such supplier" ;
        }
        System.out.println(output);
    }

    //not god!!!!!!!!!!!!!!!!!!!!!!
    public void updateContact(int supplierId ,String filed ,String newVal){

        String sql = "UPDATE Discount SET discount= ?  where catalogId = ? AND quanttity = ? ";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(2, supplierId);

            pstmt.setString(1, newVal);


            pstmt.executeUpdate();
            System.out.println("succeed");
        } catch (SQLException e) {
            System.out.println( "failed");
        }

    }




    public List<Contact> select_All_Contact_of_supplier(int supplierId) {
        String sql = "SELECT * FROM Contact WHERE supplierId=" +supplierId  ;


        List<Contact> newOrder = new ArrayList<Contact>();


        try (Statement stmt  = connection.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {

                Contact tmpC = new Contact();
                tmpC.supplierId = rs.getInt("supplierId");
                tmpC.firstName = rs.getString("firstName");
                tmpC.lastName = rs.getString("lastName");
                tmpC.phoneNumber = rs.getString("phoneNumber");
                tmpC.email = rs.getString("email");

                newOrder.add(tmpC);

            }
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            System.out.println("faild select Order");

        }
        return newOrder;
    }



    ////////////**********************************





}



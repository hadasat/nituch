package DataSection;

import DataSection.Contact;
import DataSection.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by מחשב on 17/04/2018.
 */
public class Database {
    Connection connection = null ;

    public Database()
    {
        String url = "jdbc:sqlite:Suppliers.db";
        try {
            this.connection = DriverManager.getConnection(url);
        } catch (Exception var3) {
            System.out.println(var3);
            System.out.println("not");
        }

    }

    public void closeConnection(){
        try {
            connection.close();
        }
        catch (Exception e){System.out.println("fail load data");}
    }


    public String add_Supplier(Supplier supplier) {
        String output = "";

        try (Statement stmt  = connection.createStatement()){

            // loop through the result set
            stmt.executeUpdate("INSERT INTO Suppliers VALUES (" +supplier.supplierId +"," +
                    supplier.bankAccount +",\"" + supplier.payment + "\",\"" + supplier.supplyForm+ "\");");
            // stmt.executeUpdate("INSERT INTO DataSection.Supplier VALUES (123,123,'124','123')");
            output ="Add supplier succeeded";
        } catch (SQLException e) {
            output ="Add supplier failed";
        }
        return output;
    }


    public String updateSupplier(int supplierId ,String filed, String value){
        String sql = "UPDATE Suppliers SET " +filed+ "= ?  where supplierId = ?";
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

    public List<ItemInOrder> items_in_Order(int orderId){
        String sql = "select * from ItemsInOrders where orderId=" + orderId;
        List<ItemInOrder> items = new LinkedList<>();
        try (Statement stmt  = connection.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                ItemInOrder item = new ItemInOrder();
                item.orderId = rs.getInt("orderId");
                item.catalogId = rs.getInt("catalogId");
                item.quanttity= rs.getInt("quanttity");
                items.add(item);
            }
        } catch (SQLException e) {

        }
        return items;
    }

    public Supplier select_supplier(int supplierId) {
        String sql = "SELECT * FROM Suppliers WHERE supplierId =" + supplierId ;
        Supplier s = new Supplier();
        try (Statement stmt  = connection.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            // loop through the result set

                while (rs.next()) {
                    s.supplierId = rs.getInt("supplierId");
                    s.supplyForm = rs.getString("supplyForm");
                    s.payment = rs.getString("payment");
                    s.bankAccount = rs.getInt("bankAccount");

            }
        } catch (SQLException e) {
            return s;
        }
        return s;
    }


    public  List<Supplier> select_ALL_supplier() {
        List<Supplier> s = new LinkedList<>();
        String sql = "SELECT * FROM Suppliers ";

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
            //System.out.println("faild select supplier");

        }
        return s;
    }



    ////////////////item:****************

    public String add_item(Item item) {
        String output;

        try (Statement stmt  = connection.createStatement()){
            // loop through the result set
            stmt.executeUpdate("INSERT INTO Items  VALUES ( "+item.catalogId+"," +
                    item.supplierId +"," + item.price  +",\"" + item.manufacturer +  "\");");
            output = "Add item succeeded";
        } catch (SQLException e) {
            output = "Add item failed";
        }
        return output;
    }


    public String updateItem(int supplierId ,int CatalogId, int new_price_value){
        String output;
        String sql = "UPDATE Items SET price= ?  where supplierId = ? AND CatalogId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, new_price_value);
            pstmt.setInt(2, supplierId);
            pstmt.setInt(3, CatalogId);
           // pstmt.setString(3, m);

            pstmt.executeUpdate();
            output =  "updateItem succeed";
        } catch (SQLException e) {
            output =  "updateItem failed";
        }
        return output;
    }



    public List<Item> select_Item(int supplierId, int catalogId ) {
        String sql = "SELECT * FROM Items WHERE catalogId=" +catalogId+ " AND supplierId =" + supplierId;

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
            //System.out.println("faild select DataSection.Item");
        }
        return output;
    }


    public List<Item> select_ALL_Item() {
        String sql = "SELECT * FROM Items ";

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
            //System.out.println("faild select DataSection.Item");
        }
        return output;
    }





    //*********************************end item::


    ////////////////////////////order:

    public String add_order(Order order) {
        String output = "";
        try (Statement stmt = connection.createStatement()) {
            // loop through the result set
            String sql = "INSERT INTO Orders VALUES ("+order.orderId + ","  + order.supplierId + ",\"" +
                    order.orderDate + "\"," + order.recived + ",\"" + order.arrivalDate + "\")";
            stmt.executeUpdate(sql);
            output = "Add Order succeeded";
        } catch (SQLException e) {
            output = "Add Order failed";
        }

        return output;
    }

    public String updateOrder(int catalogId ,String filed, String value){
        String output;
        String sql = "UPDATE Oredrs SET " +filed+ "= ?  where catalogId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(2, catalogId);
            if(filed =="quanttity"||filed =="recived" ) {
                pstmt.setInt(1,  Integer.parseInt(value));
            }
            else if(filed =="orderDate" || filed =="arrivalDate") {
                pstmt.setString(1,value);
            }
            pstmt.executeUpdate();
            output = "update order succeed";
        } catch (SQLException e) {
            output =  "update order failed";
        }
        return output;
    }

    public List<Order> select_Order(int supplierId) {
        String sql = "SELECT * FROM Orders WHERE supplierId=" +supplierId  ;
        List<Order> newOrder = new ArrayList<Order>();
        try (Statement stmt  = connection.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                Order tmpO = new Order();
                tmpO.supplierId = rs.getInt("supplierId");
                tmpO.orderId = rs.getInt("orderId");
                tmpO.arrivalDate = rs.getString("arrivalDate");
                tmpO.orderDate = rs.getString("orderDate");
                tmpO.recived=rs.getInt("recived");
                newOrder.add(tmpO);

            }
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            //System.out.println("faild select DataSection.Order");

        }
        return newOrder;
    }



    public List<Order> select_Not_Recived_Orders(int supplierId) {
        String sql = "SELECT * FROM Orders WHERE supplierId=" +supplierId +" AND recived = 0"  ;
        List<Order> newOrder = new ArrayList<Order>();
        try (Statement stmt  = connection.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {

                Order tmpO = new Order();
                tmpO.supplierId = rs.getInt("supplierId");
                tmpO.orderId = rs.getInt("orderId");
                tmpO.arrivalDate = rs.getString("arrivalDate");
                tmpO.orderDate = rs.getString("orderDate");
                tmpO.recived=rs.getInt("recived");
                newOrder.add(tmpO);

            }
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            //System.out.println("faild select DataSection.Order");

        }
        return newOrder;
    }





    ///////************************************



    ////discount:_______________________------------



    public String add_Discount(Discount discount) {
        String output ;
        try (Statement stmt  = connection.createStatement()){
            // loop through the result set
            stmt.executeUpdate("INSERT INTO Discounts VALUES (" +discount.catalogId +"," +
                    discount.quanttity +"," + discount.discount +")" );
            output = "Add discount succeeded";
        } catch (SQLException e) {
            output = "Add discount failed" ;
        }
        return  output;
    }

    public String updateDiscount(int catalogId ,int quanttity ,int newDiscount){
        String output;
        String sql = "UPDATE Discounts SET discount= ?  where catalogId = ? AND quanttity = ? ";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, newDiscount);
            pstmt.setInt(2, catalogId);
            pstmt.setInt(3, quanttity);

            pstmt.executeUpdate();
            output = "update discount succeed";
        } catch (SQLException e) {
            output = "update discount failed";
        }
        return output;
    }

    public List<Discount> select_Discount(int catalogId) {
        String sql = "SELECT * FROM Discounts WHERE catalogId=" +catalogId ;
        List<Discount> discounts = new LinkedList<>();
        try (Statement stmt  = connection.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                Discount d = new Discount();
                d.catalogId = rs.getInt("catalogId");
                d.quanttity = rs.getInt("quanttity");
                d.discount = rs.getInt("discount");
                discounts.add(d);
            }
        } catch (SQLException e) {

        }
        return discounts;
    }



    ////////////**********************************

    ////contact:_______________________------------



    public String add_Contact(Contact con) {
        String output;
        try (Statement stmt = connection.createStatement()) {
            // loop through the result set
            stmt.executeUpdate("INSERT INTO Contacts " + "VALUES (" + con.supplierId + ",\"" +
                    con.firstName + "\",\"" + con.lastName + "\",\"" + con.phoneNumber + "\",\"" + con.email + "\")");
            output = "Add Contact succeeded";
        } catch (SQLException e) {
            output = "Add Contact failed";
        }


        return output;

    }

    //not god!!!!!!!!!!!!!!!!!!!!!!
    public String updateContact(int supplierId ,String filed ,String newVal){
        String output ;
        String sql = "UPDATE Discounts SET discount= ?  where catalogId = ? AND quanttity = ? ";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(2, supplierId);
            pstmt.setString(1, newVal);
            pstmt.executeUpdate();
            output = "update contact succeed";
        } catch (SQLException e) {
            output= "update contact failed";
        }
        return output;
    }

    public List<Contact> select_All_Contact_of_supplier(int supplierId) {
        String sql = "SELECT * FROM Contacts WHERE supplierId=" +supplierId  ;
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
        }
        return newOrder;
    }

    public String delete(String table,String filed,String value) {
        return delete(table,filed,value,"","");
    }

    public String delete(String table,String filed,String value, String filed2,String value2){
        String output ;
        String sql = "delete from " + table + " where " + filed  + "="  + value;
        if(!filed2.equals(""))
            sql += " and " + filed2 + "=" + value2;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.executeUpdate();
            output = "delete succeed";
        }
        catch (Exception e){
            output = "delete failed " +e ;
        }
        return output;
    }

    ////////////**********************************





}



package DataSection;

import DataSection.*;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class testDatabase {

    Database databaseConn = new Database();

    @Test
    public void testAdd_Supplier() {
        Supplier supplier = new Supplier(234567, 234123122, "check", "Payments");
        String output ="Add supplier succeeded";
        assertEquals(output,databaseConn.add_Supplier(supplier));
        //should fail adding
        assertNotEquals(output,databaseConn.add_Supplier(supplier));
        databaseConn.delete("Suppliers","supplierId","234567");
    }

    @Test
    public void add_order(){
        int supplierId = 111111;
        int orderId = 215684;
        int catalogId =231564;
        String orderDate ="12/01/2018";
        int recived =0;
        String arrivalDate = "";
        Order order = new Order(orderId,supplierId,orderDate,recived,arrivalDate);
        String output = "Add Order succeeded";
        assertEquals(output,databaseConn.add_order(order));
        databaseConn.delete("Orders","orderId",Integer.toString(orderId),"catalogId",Integer.toString(catalogId));
    }

    @Test
    public void testAdd_Contact() {
        int supplierId = 111111; //need to be in db
        String firstName = "eli";
        String lastName = "perel";
        String phoneNumber = "05342314232";
        String email = "eliper@post.barIlan.ac.il";
        Contact contact = new Contact(supplierId,firstName,lastName,phoneNumber,email);
        String output ="Add Contact succeeded";
        assertEquals(output,databaseConn.add_Contact(contact));
        //should fail adding
        assertNotEquals(output,databaseConn.add_Contact(contact));
        databaseConn.delete("Contacts","supplierId","111111" , "phoneNumber","\"05342314232\"");
    }

    @Test
    public void testAdd_Discount() {
        int catalogId=211111; //need to be in db
        int quanttity=10;
        int discount=10;
        Discount newDiscount =new Discount(catalogId,quanttity,discount);
        String output ="Add discount succeeded";
        assertEquals(output, databaseConn.add_Discount(newDiscount));
        //should fail adding
        assertNotEquals(output, databaseConn.add_Discount(newDiscount));
        databaseConn.delete("Discounts","catalogId" , "211111","quanttity","10");
    }


    @Test
    public void selectSupplier() {
        int id=77565633;
        Supplier supplier = new Supplier(id, 234123122, "check", "Payments");
        databaseConn.add_Supplier(supplier);
        assertEquals(id, databaseConn.select_supplier((id)).supplierId);
        databaseConn.delete("Suppliers","supplierId",Integer.toString(77565633));
        assertNotEquals(id,databaseConn.select_supplier((id)).supplierId);
    }

    @Test
    public void selectOrder() {
        int supplierId = 111111;
        int orderId = 215684;
        String orderDate ="12/01/2018";
        int recived =0;
        String arrivalDate = "";
        Order order = new Order(orderId,supplierId,orderDate,recived,arrivalDate);
        databaseConn.add_order(order);
        List<Order> orders= databaseConn.select_Order((orderId));
        if(orders.size() >0) {
            assertEquals(supplierId, databaseConn.select_Order((orderId)).get(0));
            System.out.println("d");
        }
        databaseConn.delete("Orders","orderId",Integer.toString(orderId));
    }

}
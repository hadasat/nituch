import org.junit.*;
import static org.junit.Assert.*;
import junit.framework.Assert.*;

public class testDatabase {


    Database databaseConn = new Database();

    @Test
    public void testAdd_Supplier() {
        Supplier supplier = new Supplier(77565633, 234123122, "check", "Payments");
        String output ="Add supplier succeeded";
        assertEquals(output,databaseConn.add_Supplier(supplier));
        //should fail adding
        assertNotEquals(output,databaseConn.add_Supplier(supplier));
        databaseConn.delete("Supplier","supplierId","77565633");
    }

    @Test
    public void add_order(){
        int supplierId = 1234567;
        int orderId = 215684;
        int catalogId =231564;
        int quanttity  =154;
        String orderDate ="12/01/2018";
        int recived =0;
        String arrivalDate = "";
        Order order = new Order(supplierId,orderId,catalogId,quanttity,orderDate,recived,arrivalDate);
        String output = "Add Order succeeded";
        assertEquals(output,databaseConn.add_order(order));
    }

    @Test
    public void testAdd_Contact() {
        int supplierId = 12345678; //need to be in db
        String firstName = "eli";
        String lastName = "perel";
        String phoneNumber = "05342314232";
        String email = "eliper@post.barIlan.ac.il";
        Contact contact = new Contact(supplierId,firstName,lastName,phoneNumber,email);
        String output ="Add Contact succeeded";
        assertEquals(output,databaseConn.add_Contact(contact));
        //should fail adding
        assertNotEquals(output,databaseConn.add_Contact(contact));
        databaseConn.delete("Contacts","supplierId","12345678","phoneNumber","05342314232");
    }

    @Test
    public void testAdd_Discount() {
        int catalogId=208666623; //need to be in db
        int quanttity=1;
        int discount=1;
        Discount newDiscount =new Discount(catalogId,quanttity,discount);
        String output = "Add Discount succeeded";
        assertEquals(output, databaseConn.add_Discount(newDiscount));
        //should fail adding
        assertEquals(output, databaseConn.add_Discount(newDiscount));
        databaseConn.delete("Discount","catalogId" , "208666623","quanttity","1");
    }


    @Test
    public void selectSupplier() {
        Supplier supplier = new Supplier(77565633, 234123122, "check", "Payments");
        databaseConn.add_Supplier(supplier);
        int s=77565633;
        assertEquals(s, databaseConn.select_supplier((s)).supplierId);
        assertNull(databaseConn.select_supplier((1)));
        databaseConn.delete("Supplier","supplierId","77565633");
    }

    @Test
    public void selectOrder() {
        int s=208666623; //need to be in db
        assertEquals("208666623", databaseConn.select_Order((s)).get(0).supplierId);

    }

    @Test
    public void selectOrderFailed() {
        int s =555555555; //not    /need to be in db


        try {

            assertEquals("", databaseConn.select_Order((s)).get(0).supplierId);




        } catch (Exception e) {

        }
        // Suppli


    }





}
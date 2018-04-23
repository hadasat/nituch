import org.junit.*;
import static org.junit.Assert.*;
import junit.framework.Assert.*;

public class testDatabase {


    Database databaseConn = new Database();

    @Test
    public void testAdd_SupplierSucssed() {
        try {
            Supplier supplier = new Supplier(12745634, 234123122, "check", "Payments");
            String output = databaseConn.add_Supplier(supplier);
            System.out.println(output);
            String expected ="Add supplier succeeded";
            assertTrue(expected.equals(output));
        } catch (Exception e) {

        }
        // Supplier supplier = new Supplier(supplierId,bankAccount,payment,supplyForm);

    }

    @Test
    public void testAdd_SupplierFailed() {
        try {
            assertEquals("Add item failed", databaseConn.add_Supplier(new Supplier(12341234, 234123122, "ttgy", "9")));

        } catch (Exception e) {

        }
        // Supplier supplier = new Supplier(supplierId,bankAccount,payment,supplyForm);

    }

    @Test
    public void testAdd_ContactSucssed() {
        int supplierId = 12345678; //need to be in db
        String firstName = "eli";
        String lastName = "perel";
        String phoneNumber = "05342314232";
        String email = "eliper@post.barIlan.ac.il";

        try {

            assertEquals("Add Contact succeeded", databaseConn.add_Contact(new Contact(supplierId, firstName, lastName, phoneNumber, email)));


        } catch (Exception e) {

        }
        // Supplier supplier = new Supplier(supplierId,bankAccount,payment,supplyForm);

    }

    @Test
    public void testAdd_ContactFailed() {
        int supplierId = 444444444; //notttttt need to be in db
        String firstName = "eli";
        String lastName = "perel";
        String phoneNumber = "05342314232";
        String email = "eliper@post.barIlan.ac.il";

        try {

            assertEquals("Add Contact failed no such supplier", databaseConn.add_Contact(new Contact(supplierId, firstName, lastName, phoneNumber, email)));


        } catch (Exception e) {

        }
        // Supplier supplier = new Supplier(supplierId,bankAccount,payment,supplyForm);

    }


    @Test
    public void testAdd_DiscountSucssed() {
        int catalogId=208666623; //need to be in db
        int quanttity=1;
        int discount=1;


        try {

            assertEquals("Add Discount succeeded", databaseConn.add_Discount(new Discount(catalogId,quanttity,discount)));




        } catch (Exception e) {

        }

    }

    @Test
    public void testAdd_DiscountFailed() {
        int catalogId=12312312; //need to not be in db
        int quanttity=1;
        int discount=2;

        try {

            assertEquals("Add Discount failed", databaseConn.add_Discount(new Discount(catalogId,quanttity,discount)));


        } catch (Exception e) {

        }


    }


    @Test
    public void selectSupplierSusseced() {
        int s=208666623; //need to be in db


        try {

            assertEquals("208666623", databaseConn.select_supplier((s)).supplierId);

        } catch (Exception e) {

        }
        // Supplier supplier = new Supplier(supplierId,bankAccount,payment,supplyForm);

    }

    @Test
    public void selectSupplierFailed() {
        int s =555555555; //not    /need to be in db


        try {

            assertEquals("", databaseConn.select_supplier((s)).supplierId);

        } catch (Exception e) {

        }
        // Suppli


    }






    @Test
    public void selectOrderSusseced() {
        int s=208666623; //need to be in db


        try {

            assertEquals("208666623", databaseConn.select_Order((s)).get(0).supplierId);

        } catch (Exception e) {

        }
        // Supplier supplier = new Supplier(supplierId,bankAccount,payment,supplyForm);

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
import org.junit.*;
import static org.junit.Assert.*;

public class DatabaseTest {

    @Test
    void testAdd_Supplier() {
        int supplierId = 1235
        int bankAccount = 1234
        String payment = check
        String supplyForm = "Mon"
        Supplier supplier = new Supplier(supplierId,bankAccount,payment,supplyForm)
        AssertEquale("Add supplier succeeded",Add_Supplier(supplier));
    }

    void testUpdateSupplier() {

    }

    void testSelect_supplier() {
    }

    void testAdd_item() {
    }

    void testUpdateItem() {
    }

    void testSelect_Item() {
    }

    void testAdd_order() {
    }

    void testUpdateOrder() {
    }

    void testSelect_Order() {
    }

    void testSelect_Not_Recived_Orders() {
    }

    void testAdd_Discount() {
    }

    void testUpdateDiscount() {
    }

    void testSelect_Discount() {
    }

    void testAdd_Contact() {
    }

    void testUpdateContact() {
    }

    void testSelect_All_Contact_of_supplier() {
    }
}

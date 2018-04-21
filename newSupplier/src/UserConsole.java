import java.util.List;
import java.util.Scanner;

/**
 * Created by מחשב on 20/04/2018.
 */
public class UserConsole {

    private static Scanner in = new Scanner(System.in);
    private static Database databaseConn;

    public static void main(String[] args) {
        databaseConn = new Database();
        System.out.println("please select an action by choosing the index: \n" +
                "1.add new supplier \n" +
                "2.add new contact for supplier \n" +
                "3.show discounts \n" +
                "4.order report by supplier\n" +
                "5.change supplier detailes" +
                "6.add new discount");

        String s = in.nextLine();
        chooseAcction(s);
    }

    private static void addNewSupplier() {
        System.out.println("please enter supplierId");
        String supplierId = in.nextLine();
        System.out.println("please enter bankAccount");
        String bankAccount = in.nextLine();
        System.out.println("please choose payment form payment: 1.check, 2.cash, 3.Payments");
        String paymentIndex = in.nextLine();
       while (!(paymentIndex != "1" || paymentIndex != "2" || paymentIndex != "3")) {
           System.out.println("please enter payment form between 1-3");
           paymentIndex = in.nextLine();
       }
        String paymentForm[] = {"check", "cash", "Payments"};
        String payment = paymentForm[Integer.parseInt(paymentIndex) - 1];
        System.out.println("please enter supplyForm : 1.Independent 2.Regular days 3.personal invitation");
        String supplyFormIn = in.nextLine();
        while (!(supplyFormIn != "1" || supplyFormIn != "2" || supplyFormIn != "3")) {
            System.out.println("please enter payment form between 1-3");
            supplyFormIn = in.nextLine();
        }
        String supplyFormOptions [] = {"Independent","Regular days","personal invitation"};
        String supplyForm = supplyFormOptions[Integer.parseInt(supplyFormIn)-1];
        Supplier supplier = new Supplier(supplierId, Integer.parseInt(bankAccount), payment, supplyForm);
        databaseConn.add_Supplier(supplier);
    }

    private static void addNewContact() {
        System.out.println("please enter supplierId");
        String supplierId = in.nextLine();
        System.out.println("please enter first Name");
        String firstName = in.nextLine();
        System.out.println("please enter last Name");
        String lastName = in.nextLine();
        System.out.println("please enter phone Number");
        String phoneNumber = in.nextLine();
        System.out.println("please enter email");
        String email = in.nextLine();
        Contact newContact = new Contact(supplierId, firstName, lastName, phoneNumber, email);
        databaseConn.add_Contact(newContact);
    }

    private static void showDiscounts(){
        System.out.println("please enter catalogId");
        String catalogId = in.nextLine();
        System.out.println("please enter quantity");
        String quantity = in.nextLine();
        databaseConn.select_Discount(catalogId,quantity);
    }
    private static void orderReportBySupplier(){
        System.out.println("please enter supplierId");
        String supplierId = in.nextLine();
        List<Order> orders = databaseConn.select_Order(supplierId);
        int index = 0;
        for(Order order:orders){
            String output = index + ". order id: " + order.orderId +", quantity: " + order.quanttity +
                    ", arrival date: " + order.arrivalDate + ", ordered Date: "+order.orderDate +
                    ", supplier id: " + order.supplierId + ", catalog id: "+order.catalogId + ", recived: ";
            if(order.recived ==1)
                output += "yes";
            else
                output += "no";
            index++;
            System.out.println(output);
        }
    }

    private static void changeSupplierDetailes(){
        System.out.println("please enter supplier id:");
        String supplierId = in.nextLine();
        System.out.println("please enter a field to update: 1.bankAccount 2.payment 3.supplyForm");
        String filedIndex = in.nextLine();
        while (!(filedIndex != "1" || filedIndex != "2" || filedIndex != "3")) {
            System.out.println("please enter payment form between 1-3");
            filedIndex = in.nextLine();
        }
        String fileds [] = {"bankAccount","payment","supplyForm"};
        String filed = fileds[Integer.parseInt(filedIndex)-1];
        System.out.println("please enter new value for "+filed+": ");
        String value = in.nextLine();
        databaseConn.updateSupplier(supplierId,filed,value);
    }


    private static void addNewDiscount(){
        Discount discount = new Discount();
        databaseConn.add_Discount();
    }
    /*
        public List<Order> select_Last_Order(String supplierId) {
            List<Order> newOrder = new ArrayList<Order>();
            newOrder = databaseConn.select_Order(supplierId);
            Date maxDate = newOrder.stream().map(u -> u.orderDate).max(Date::compareTo).get();
            List<Order> anser = new ArrayList<Order>();
            for (Order o: newOrder
                    ) {
                if(o.orderDate.equals(maxDate)){
                    anser.add(o);
                }

            }

            return  anser;
        }
        }
        */
    private static void chooseAcction(String action) {
        switch (action) {
            case ("1"):
                addNewSupplier();
                break;

            case ("2"):
                addNewContact();
                break;
            case ("3"):
                showDiscounts();
                break;
            case ("4"):
                orderReportBySupplier();
                break;
            case ("5"):
                changeSupplierDetailes();
                break;
            case ("6"):
                addNewDiscount();
                break;

        }
    }

}

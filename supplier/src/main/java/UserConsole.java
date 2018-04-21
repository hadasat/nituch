import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by מחשב on 20/04/2018.
 */
public class UserConsole {

    private static Scanner in = new Scanner(System.in);
    private static Database databaseConn;

    public static void main(String[] args){
        databaseConn = new Database();
        System.out.println("please select an action by choosing the index: \n" +
                "1.add new supplier \n" +
                "2.add new contact for supplier \n" +
                "3.show discounts \n" +
                "4.order report by supplier\n" +
                "5.change supplier detailes" +
                "6.add new discount");

        String s = in.nextLine();

    }
    private void addNewSupplier(){
        System.out.println("please enter supplierId\n");
        String supplierId = in.nextLine();
        System.out.println("please enter bankAccount\n");
        int bankAccount = in.nextInt();
        System.out.println("please enter payment \n");
        String payment = in.nextLine();
        System.out.println("please enter supplyForm \n");
        String supplyForm = in.nextLine();

        Supplier supplier = new Supplier(supplierId,bankAccount,payment,supplyForm);
        databaseConn.add_Supplier(supplier);
    }

    private void addNewContact(){
        System.out.println("please enter supplierId\n");
        String supplierId = in.nextLine();
        System.out.println("please enter first Name\n");
        String firstName = in.nextLine();
        System.out.println("please enter last Name\n");
        String lastName = in.nextLine();
        System.out.println("please enter phone Number\n");
        String phoneNumber = in.nextLine();
        System.out.println("please enter email\n");
        String email = in.nextLine();
        Contact newContact = new Contact(supplierId,firstName,lastName,phoneNumber,email);
        //databaseConn.addCintact(newContact);
    }


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
    private  void showDiscounts(){

    }
    private void chooseAcction(String action){
        switch (action){
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

                break;

            case ("5"):

                break;
        }
    }
}

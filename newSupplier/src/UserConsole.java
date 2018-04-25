import DataSection.*;

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
                System.out.println("please select an action by choosing the index or choose to quit by q: \n" +
                "1.add new supplier card \n" +
                "2.add new contact for supplier \n" +
                "3.show discounts of item \n" +
                "4.order report (of supplier)\n" +
                "5.update supplier details\n" +
                "6.add new agreement with supplier\n" +
                "7.print all suppliers in the system\n "+
                "8.show all not-received orders of supplier\n" +
                "9.print the menu again"
        );
        String s = in.nextLine();
        while (!s.equals("q")){
            switch (s) {
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
                case ("7"):
                    showALL_Suppliers();
                    break;
                case ("8"):
                    show_all_not_recived_orders();
                    break;
                case ("9"):
                    System.out.println("please select an action by choosing the index or choose to quit by q: "+
                            "1.add new supplier card \n" +
                            "2.add new contact for supplier \n" +
                            "3.show discounts of item \n" +
                            "4.order report (of supplier)\n" +
                            "5.update supplier details\n" +
                            "6.add new agreement with supplier\n" +
                            "7.print the menu again\n"+
                            "8.show all not-received orders of supplier\n" +
                            "9.print all suppliers in the system"
                    );
                    break;

                default:
                    System.out.println("please enter a valid command");
            }
            System.out.println("please enter q for quit, action number or 7 for menu: ");
            s = in.nextLine();
        }
    }

    public static void add_order(){
        Order orderToAdd  =new Order();
        String supplierId = "";
        String orderId = "";
        String catalogId = "";
        String quanttity = "";
        String orderDate = "";
        String recived = "";
        String arrivalDate = "";
        try {
            while (true) {
                System.out.println("please enter supplierId");
                supplierId = in.nextLine();
                if (isNumeric(supplierId) == true) {
                    break;
                } else {
                    System.out.println("not A NUMBER!! try again");
                }
            }

            while (true) {
                System.out.println("please enter orderId");
                orderId = in.nextLine();
                if (isNumeric(orderId) == true) {
                    break;
                } else {
                    System.out.println("not A NUMBER!! try again");
                }
            }

            while (true) {
                System.out.println("please enter quanttity");
                quanttity = in.nextLine();
                if (isNumeric(quanttity) == true) {

                    break;
                } else {
                    System.out.println("not A NUMBER!! try again");
                }
            }


            System.out.println("please enter orderDate in format dd/mm/yyyy");
            orderDate = in.nextLine();

            while (true) {
                System.out.println("please enter recived : if order recived enetr 1 else enter 0");
                recived = in.nextLine();
                if (recived.equals("1")||recived.equals("0")) {
                    break;
                } else {
                    System.out.println("not  1 or 0!! try again");
                }
            }

            System.out.println("please enter arrivalDate in format dd/mm/yyyy yuo can leave this filed empty and press Enter");
            arrivalDate = in.nextLine();
            orderToAdd= new Order(Integer.parseInt(orderId),Integer.parseInt(supplierId),orderDate,Integer.parseInt(recived),arrivalDate);
            databaseConn.add_order(orderToAdd);
        }catch (Exception e){
            System.out.println("failed to add Order");
        }

    }
    public static void print_Suppliers_List(List<Supplier> l){

        for (Supplier i: l
                ) {
            System.out.println("\nsupplierId: "+i.supplierId);
            System.out.println("bankAccount: "+i.bankAccount);
            System.out.println("payment: "+i.payment);
            System.out.println("supplyForm:"+i.supplyForm);
        }
    }
    public static void showALL_Suppliers(){

        try {
            List<Supplier> all_s = databaseConn.select_ALL_supplier();
            print_Suppliers_List(all_s);
        }catch (Exception e){
            System.out.println("failed! ");
        }

    }

    public static void printItemList(List<Item> l){
        for (Item i: l
                ) {
            System.out.println("\ncatalogId: "+i.catalogId);
            System.out.println("supplierId: "+i.supplierId);
            System.out.println("price: "+i.price);
            System.out.println("manufacturer:"+i.manufacturer);
        }
    }
    public static void  showALL_items(){


        try {
            List<Item> all_items = databaseConn.select_ALL_Item();
            printItemList(all_items);
        }catch (Exception e){
            System.out.println("failed to select item.");
        }

    }

    public static void printOrderList(List<Order> l){
        for (Order i: l
                ) {

            System.out.println("\norderId: "+i.orderId);
            System.out.println("supplierId: "+i.supplierId);
            System.out.println("orderDate: "+i.orderDate);
            System.out.println("is recived? : "+(i.recived==1));
            System.out.println("arrivalDate:"+i.arrivalDate);
        }
    }
    public static void show_all_not_recived_orders(){

        String supplierId = "";

        while (true) {
            System.out.println("please enter supplierId");
            supplierId = in.nextLine();
            if (isNumeric(supplierId) == true) {

                break;
            } else {
                System.out.println("not A NUMBER!! try again");
            }
        }
        try {
            List<Order> allOrd = databaseConn.select_Not_Recived_Orders(Integer.parseInt(supplierId));
           printOrderList(allOrd);
        }catch (Exception e){
            System.out.println("failed to select DataSection.Order probably this supplier dos not have unreceived orders.");
        }

    }

    public static void addNewSupplier() {
        boolean a = true;

        String supplierId = "";
        String bankAccount = "";

        while (a) {
            System.out.println("please enter supplierId");
            supplierId = in.nextLine();
            if (isNumeric(supplierId) == true) {
                a = false;
            } else {
                System.out.println("not A NUMBER!! try again");
            }
        }
        while (true) {
            System.out.println("please enter bankAccount");
            bankAccount = in.nextLine();
            if (isNumeric(bankAccount) == true) {

                break;
            } else {
                System.out.println("not A NUMBER!! try again");
            }
        }


        System.out.println("please choose payment form payment: 1.check, 2.cash, 3.payments");
        String paymentIndex = in.nextLine();

        while (!paymentIndex.equals("1") && !paymentIndex.equals("2") && !paymentIndex.equals("3")) {
            System.out.println("please enter payment form between 1-3");
            paymentIndex = in.nextLine();

        }
        String paymentForm[] = {"check", "cash", "payments"};
        String payment = paymentForm[Integer.parseInt(paymentIndex) - 1];

        System.out.println("please enter supplyForm : 1.Independent 2.Fixed days 3.Arrive by order");
        String supplyFormIn = in.nextLine();
        while (!paymentIndex.equals("1") && !paymentIndex.equals("2") && !paymentIndex.equals("3")) {
            System.out.println("please enter payment form between 1-3");
            supplyFormIn = in.nextLine();
        }
        String supplyForm="";
        if (supplyFormIn.equals("2")) {
            System.out.println("please enter days of delivery and \",\" between : 1. Sunday, 2. Monday 3. Tuesday 4. Wednesday 5. Thursday 6. Friday\n ");
            String daysIndex = in.nextLine();
            // remove spaces if there is
            daysIndex.replaceAll("\\s+", "");
            //split by days
            String days[] = daysIndex.split(",");
            //if the user did not enter properly ask him again
            if (days.length == 1 && daysIndex.length() > 1) {
                System.out.println("you did not enter days in the right format,enter yes to skip , otherwise enter the days:");
                daysIndex = in.nextLine();
                daysIndex.replaceAll("\\s+", ""); // remove spaces if there is
                days = daysIndex.split(",");
            }
            //if the user want to enter days manually
            if (!daysIndex.contains("yes")) {
                String week[] = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri"};
                supplyForm = "";
                for (String index : days) {
                    try {
                        int ind = Integer.parseInt(index);
                        if (ind <= 6 && ind >= 1) {
                            supplyForm += week[ind - 1];
                        }
                    } catch (NumberFormatException e) {
                    }
                }
            }
            //check the supply form is not empty
            if (supplyForm.length() == 0)
                supplyForm = "Fixed days";
        }
        else if(supplyForm.equals("1"))
            supplyForm = "Independent";
        else
            supplyForm = "Arrive by order";
        Supplier supplier = new Supplier(Integer.parseInt(supplierId), Integer.parseInt(bankAccount), payment, supplyForm);
        try {
            databaseConn.add_Supplier(supplier);
            System.out.println("sucssed to add new supplier supplierId: "+supplier.supplierId);
        }catch (Exception e){
            System.out.println("failed to add new supplier");
        }

    }


    public static boolean addNewContact() {
        String supplierId="";
        while (true){
            System.out.println("please enter supplierId");
            supplierId = in.nextLine();
            if(isNumeric(supplierId)==true){

                break;
            }else{
                System.out.println("not A NUMBER!! try again");
            }
        }
        System.out.println("please enter first Name");
        String firstName = in.nextLine();
        System.out.println("please enter last Name");
        String lastName = in.nextLine();
        System.out.println("please enter phone Number");
        String phoneNumber = in.nextLine();
        System.out.println("please enter email");
        String email = in.nextLine();
        Contact newContact = new Contact(Integer.parseInt(supplierId), firstName, lastName, phoneNumber, email);
        try {
            databaseConn.add_Contact(newContact);
            System.out.println("sucssed to add DataSection.Contact for supplier: "+ supplierId);
            return  true;
        }catch (Exception e){
            System.out.println("failed to add DataSection.Contact");
            return  false;
        }

    }

    public static boolean showDiscounts(){
        String catalogId="";
        while (true){
            System.out.println("please enter catalogId");
            catalogId= in.nextLine();
            if(isNumeric(catalogId)==true){

                break;
            }else{
                System.out.println("not A NUMBER!! try again");
            }
        }

        try {
            List<Discount> discounts = databaseConn.select_Discount(Integer.parseInt(catalogId));
            printDiscount(discounts);
            return  true;
        }catch (Exception e){
            System.out.println("failed to select DataSection.Discount probably this supplier dos not have agreements yet.");
            return  false;
        }


    }

    private static void printDiscount(List<Discount> discounts){
    for(Discount d : discounts) {
            System.out.println("catalogId: " + d.catalogId);
            System.out.println("quanttity: " + d.quanttity);
            System.out.println ("discount: " + d.discount + "\n");

        }

    }


    public static boolean orderReportBySupplier(){
        String supplierId ="";

        while (true){
            System.out.println("please enter supplierId");
            supplierId = in.nextLine();
            if(isNumeric(supplierId)==true){

                break;
            }else{
                System.out.println("not A NUMBER!! try again");
            }
        }

        List<Order> orders;
        try {
            orders = databaseConn.select_Order(Integer.parseInt(supplierId));


        }catch (Exception e){
            System.out.println("failed to select DataSection.Order probably this supplier dos not have orders yet .");
            return  false;
        }


        int index = 0;
        for(Order order:orders){
            String output = index + ". order id: " + order.orderId +
                    ", arrival date: " + order.arrivalDate + ", ordered Date: "+order.orderDate +
                    ", supplier id: " + order.supplierId  + ", recived: ";
            if(order.recived ==1)
                output += "yes";
            else
                output += "no";
            index++;
            System.out.println(output);
        }
        return true;
    }

    public static boolean changeSupplierDetailes(){
        String supplierId ="";

        while (true){
            System.out.println("please enter supplierId");
            supplierId = in.nextLine();
            if(isNumeric(supplierId)==true){

                break;
            }else{
                System.out.println("not A NUMBER!! try again");
            }
        }

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

        try {
            databaseConn.updateSupplier(Integer.parseInt(supplierId),filed,value);
            System.out.println("sucssed to update DataSection.Supplier");
            return  true;
        }catch (Exception e){
            System.out.println("failed to update DataSection.Supplier");
            return  false;
        }

    }


    public static boolean addNewDiscount() {
        boolean invalid = true;
        String catalogId="";
        String quanttity="";
        String discountValue="";
        while (invalid) {
            try {
                while (true){
                    System.out.println("please enter catalogId:");
                    catalogId = in.nextLine();
                    if(isNumeric(catalogId)==true){

                        break;
                    }else{
                        System.out.println("not A NUMBER!! try again");
                    }
                }

                while (true){
                    System.out.println("please enter quanttity:");
                    quanttity = in.nextLine();
                    if(isNumeric(quanttity)==true){

                        break;
                    }else{
                        System.out.println("not A NUMBER!! try again");
                    }
                }

                while (true){
                    System.out.println("please enter discount:");
                    discountValue = in.nextLine();
                    if(isNumeric(discountValue)==true){

                        break;
                    }else{
                        System.out.println("not A NUMBER!! try again");
                    }
                }




                invalid = false;
                Discount discount = new Discount(Integer.parseInt(catalogId),Integer.parseInt(quanttity),Integer.parseInt(discountValue));

                try {
                    databaseConn.add_Discount(discount);

                    System.out.println("sucssed to add DataSection.Discount");
                }catch (Exception e){
                    System.out.println("failed to add DataSection.Discount");
                    return  false;
                }

            } catch (NumberFormatException e) {
                System.out.println("your inputs are illegals");
            }

        }
        return  true;
    }
    /*
        public List<DataSection.Order> select_Last_Order(String supplierId) {
            List<DataSection.Order> newOrder = new ArrayList<DataSection.Order>();
            newOrder = databaseConn.select_Order(supplierId);
            Date maxDate = newOrder.stream().map(u -> u.orderDate).max(Date::compareTo).get();
            List<DataSection.Order> anser = new ArrayList<DataSection.Order>();
            for (DataSection.Order o: newOrder
                    ) {
                if(o.orderDate.equals(maxDate)){
                    anser.add(o);
                }

            }

            return  anser;
        }
        }
        */

    public static boolean isNumeric(String str)
    {
        try
        {
            int d = Integer.parseInt(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

}

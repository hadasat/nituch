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
                "1.add new supplier \n" +
                "2.add new contact for supplier \n" +
                "3.show discounts \n" +
                "4.order report by supplier\n" +
                "5.change supplier detailes\n" +
                "6.add new discount\n" +
                "7.print the menu again");
        String s = in.nextLine();
        while (s != "q"){
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
                    System.out.println("please select an action by choosing the index: \n" +
                            "1.add new supplier\n" +
                            "2.add new contact for supplier\n" +
                            "3.show discounts \n" +
                            "4.order report by supplier\n" +
                            "5.change supplier detailes\n" +
                            "6.add new discount\n" +
                            "7.print the menu again");
                    break;
                default:
                    System.out.println("please enter a valid command");
            }
            System.out.println("please enter q for quit, action number or ");
            s = in.nextLine();
        }
    }

    private static void addNewSupplier() {
        boolean a = true;

        String supplierId="";
        String bankAccount="";

        while (a){
            System.out.println("please enter supplierId");
            supplierId = in.nextLine();
            if(isNumeric(supplierId)==true){
               a=false;
            }else{
                System.out.println("not A NUMBER!! try again");
            }
        }
        while (true){
            System.out.println("please enter bankAccount");
            bankAccount = in.nextLine();
            if(isNumeric(bankAccount)==true){

                break;
            }else{
                System.out.println("not A NUMBER!! try again");
            }
        }


            System.out.println("please choose payment form payment: 1.check, 2.cash, 3.Payments");
            String paymentIndex = in.nextLine();

            while (!paymentIndex.equals("1")&&!paymentIndex.equals("2")&&!paymentIndex.equals("3")) {
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
            String supplyForm;
            if(supplyFormIn == "2"){
                System.out.println("please enter days of delivery and \",\" between : 1. Sunday, 2. Monday 3. Tuesday 4. Wednesday 5. Thursday 6. Friday\n ");
                String daysIndex = in.nextLine();
                // remove spaces if there is
                daysIndex.replaceAll("\\s+","");
                //split by days
                String days []= daysIndex.split(",");
                //if the user did not enter properly ask him again
                if(days.length == 1 && daysIndex.length() >1){
                    System.out.println("you did not enter any days, are you sure? please enter yes, otherwise enter the days:");
                    daysIndex =  in.nextLine();
                    daysIndex.replaceAll("\\s+",""); // remove spaces if there is
                }
                //initial the supply form with the options

                //if the user want to enter days manually
                if(!daysIndex.contains("yes"))
                    days= daysIndex.split(",");
                    String week[] = {"Sun","Mon","Tue","Wed","Thu","Fri"};

                    for(String index: days){
                        try {
                            int ind = Integer.parseInt(index);
                            if(ind<=6 && ind>=1){

                            }
                        }
                        catch (NumberFormatException e){}
                    }

            }
            else{
                String supplyFormOptions [] = {"Independent","Regular days","personal invitation"};
                 supplyForm= supplyFormOptions[Integer.parseInt(supplyFormIn)-1];
            }

            Supplier supplier = new Supplier(Integer.parseInt(supplierId), Integer.parseInt(bankAccount), payment, supplyForm);
            databaseConn.add_Supplier(supplier);


    }

    private static boolean addNewContact() {
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
            return  true;
        }catch (Exception e){
            return  false;
        }

    }

    private static boolean showDiscounts(){
        String catalogId="";
        String quantity="";
        while (true){
            System.out.println("please enter catalogId");
            catalogId= in.nextLine();
            if(isNumeric(catalogId)==true){

                break;
            }else{
                System.out.println("not A NUMBER!! try again");
            }
        }

        while (true){
            System.out.println("please enter quantity");
            quantity = in.nextLine();
            if(isNumeric(quantity)==true){

                break;
            }else{
                System.out.println("not A NUMBER!! try again");
            }
        }



        try {
            databaseConn.select_Discount(Integer.parseInt(catalogId),Integer.parseInt(quantity));
            return  true;
        }catch (Exception e){
            return  false;
        }


    }


    private static boolean orderReportBySupplier(){
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
            return  false;
        }


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
        return true;
    }

    private static boolean changeSupplierDetailes(){
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
            return  true;
        }catch (Exception e){
            return  false;
        }

    }


    private static boolean addNewDiscount() {
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

                }catch (Exception e){
                    return  false;
                }

            } catch (NumberFormatException e) {
                System.out.println("your inputs are illegals");
            }

        }
        return  true;
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
    private static boolean chooseAcction(String action) {

        return  true;
    }

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

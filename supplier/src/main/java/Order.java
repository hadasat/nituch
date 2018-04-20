import java.sql.Date;

/**
 * Created by מחשב on 17/04/2018.
 */
///
public class Order {

        String item;
        String quanttity;
        Date orderDate;
        Boolean recived;
        Date arrivalDate;


        Order(){}

        Order(String item,String quanttity , Date orderDate,boolean recived, Date arrivalDate){
                this.item=item;
                this.quanttity=quanttity;
                this.orderDate=orderDate;
                this.recived=recived;
                this.arrivalDate=arrivalDate;
        }

    }


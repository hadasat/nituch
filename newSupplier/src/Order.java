import java.sql.Date;

/**
 * Created by מחשב on 17/04/2018.
 */
///
public class Order {

        int supplierId;
        int orderId;
        int catalogId;
        int quanttity;
        Date orderDate;
        int recived;
        Date arrivalDate;


        Order(){}

        Order( int supplierId,int orderId,int catalogId,int quanttity , Date orderDate,int recived, Date arrivalDate){
                this.supplierId=supplierId;
                this.orderId=orderId;
                this.catalogId=catalogId;
                this.quanttity=quanttity;
                this.orderDate=orderDate;
                this.recived=recived;
                this.arrivalDate=arrivalDate;
        }

    }


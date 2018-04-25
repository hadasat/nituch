package DataSection;

import java.sql.Date;

/**
 * Created by מחשב on 17/04/2018.
 */
///
public class Order {
        public int orderId;
        public int supplierId;
        public String orderDate;
        public int recived;
        public String arrivalDate;


        public Order(){}

        public Order(int orderId,int supplierId, String orderDate,int recived, String arrivalDate){
                this.supplierId=supplierId;
                this.orderId=orderId;
                this.orderDate=orderDate;
                this.recived=recived;
                this.arrivalDate=arrivalDate;
        }

    }


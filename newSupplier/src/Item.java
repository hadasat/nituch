/**
 * Created by מחשב on 17/04/2018.
 */
public class Item {
    int catalogId;
    int supplierId;
    int price;
    String manufacturer;

    Item(){}

    Item(int catalogId,int supplierId , int price,String manufacturer){
        this.catalogId=catalogId;
        this.price=price;
        this.supplierId=supplierId;
        this.manufacturer=manufacturer;


    }

}

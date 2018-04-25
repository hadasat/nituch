package DataSection;

/**
 * Created by מחשב on 17/04/2018.
 */
public class Item {
    public int catalogId;
    public int supplierId;
    public int price;
    public String manufacturer;

    public Item(){}

    public Item(int catalogId,int supplierId , int price,String manufacturer){
        this.catalogId=catalogId;
        this.price=price;
        this.supplierId=supplierId;
        this.manufacturer=manufacturer;


    }

}

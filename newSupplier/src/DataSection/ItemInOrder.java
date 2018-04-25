package DataSection;

public class ItemInOrder {

    public int orderId;
    public int catalogId;
    public int quanttity;

    public ItemInOrder(){};

    public ItemInOrder(int orderId,int catalogId, int quanttity){
        this.orderId = orderId;
        this.catalogId = catalogId;
        this.quanttity = quanttity;
    }


}

package DataSection;

/**
 * Created by מחשב on 20/04/2018.
 */
public class Discount {

    public int catalogId;
    public int quanttity;
    public int discount;

    public Discount(){

    }

    public Discount(int catalogId, int quanttity, int discount){
        this.catalogId=catalogId;
        this.quanttity=quanttity;
        this.discount=discount;
    }

}

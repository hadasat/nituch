package DataSection;

/**
 * Created by מחשב on 17/04/2018.
 */

public class Supplier {

    public int supplierId;
    public int bankAccount;
    public String payment;
    public String supplyForm;

    public Supplier(){}

    public Supplier(int supplierId,	int bankAccount,String payment,String supplyForm){
        this.supplierId = supplierId;
        this.bankAccount = bankAccount;
        this.payment = payment;
        this.supplyForm=supplyForm;
    }




}
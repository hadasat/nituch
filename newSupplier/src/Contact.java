/**
 * Created by מחשב on 17/04/2018.
 */
public class Contact {
    String supplierId;
    String firstName;
    String lastName;
    String phoneNumber;
    String email;


    Contact(){

    }

    Contact(String supplierId, String firstName, String lastName, String phoneNumber, String email){
        this.supplierId=supplierId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.phoneNumber=phoneNumber;
        this.email=email;

    }


}
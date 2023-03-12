package runner.delete;

public class UserDetails {


    public String firstname;
    public String firstName;
    public String lastName;
    int age;
    float accountBalance;
    public boolean eligibleForCreditCard;

    public void testEligibility(){

        if(age>25 && accountBalance >= 20000){

            eligibleForCreditCard=true;
        }
    }


}


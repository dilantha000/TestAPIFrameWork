package runner.delete;

public class AccountHolderObjects {
    public static void main(String[] args) {
        UserDetails tom = new UserDetails();
        UserDetails henry = new UserDetails();
        UserDetails inch = new UserDetails();

        tom.firstname = "dilan";
        tom.lastName = "aria";
        tom.age = 21;
        tom.accountBalance=1000;
        tom.testEligibility();
    }

}

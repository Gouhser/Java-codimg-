Program:-
package bloodbank;

import java.util.Scanner;

class BloodDonor {
    String donorName;
    String bloodGroup;
    int age;

    void getDonorDetails() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Donor Name: ");
        donorName = sc.nextLine();

        System.out.print("Enter Blood Group: ");
        bloodGroup = sc.nextLine();

        System.out.print("Enter Age: ");
        age = sc.nextInt();
    }

    void displayDonorDetails() {
        System.out.println("\n--- Donor Details ---");
        System.out.println("Name        : " + donorName);
        System.out.println("Blood Group : " + bloodGroup);
        System.out.println("Age         : " + age);
    }
}

public class BloodBankMain {
    public static void main(String[] args) {

        BloodDonor donor = new BloodDonor(); // Object creation
        donor.getDonorDetails();             // Method call
        donor.displayDonorDetails();          // Method call
    }
}
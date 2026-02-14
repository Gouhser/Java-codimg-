class BloodBank {
    private int bloodUnits = 10; // Initial stock

    public synchronized void donateBlood(String donorName, int units) {
        bloodUnits += units;
        System.out.println(donorName + " donated " + units + " units.");
        System.out.println("Updated Blood Stock: " + bloodUnits);
    }

    public synchronized void requestBlood(String patientName, int units) {
        if (units <= bloodUnits) {
            bloodUnits -= units;
            System.out.println(patientName + " received " + units + " units.");
        } else {
            System.out.println("Not enough blood units for " + patientName);
        }
        System.out.println("Updated Blood Stock: " + bloodUnits);
    }
}

class Donor extends Thread {
    BloodBank bank;
    String name;
    int units;

    Donor(BloodBank bank, String name, int units) {
        this.bank = bank;
        this.name = name;
        this.units = units;
    }

    public void run() {
        bank.donateBlood(name, units);
    }
}

class Patient extends Thread {
    BloodBank bank;
    String name;
    int units;

    Patient(BloodBank bank, String name, int units) {
        this.bank = bank;
        this.name = name;
        this.units = units;
    }

    public void run() {
        bank.requestBlood(name, units);
    }
}

public class Task5_Threading {
    public static void main(String[] args) {
        BloodBank bank = new BloodBank();

        Donor d1 = new Donor(bank, "Rahul", 5);
        Patient p1 = new Patient(bank, "Anita", 3);
        Patient p2 = new Patient(bank, "Kiran", 4);

        d1.start();
        p1.start();
        p2.start();
    }
}
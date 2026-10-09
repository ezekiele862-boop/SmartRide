
import java.util.Scanner;
import java.security.SecureRandom;
public class SmartRide {

    static SecureRandom randomNumber = new SecureRandom();
    static Scanner scanner = new Scanner(System.in);

    static double walletBalance =0;
    static String name;

    static int successfulRides = 0;
    static int economyRides = 0;
    static int standardRides = 0;
    static int premiumRides = 0;

    static double totalDistance = 0;
    static double totalAmountSpent = 0;
    static double totalDiscounts = 0;
    static double totalPeakCharges = 0;
    public static void getCustomerInformation() {
        System.out.println("Enter your name: ");
        name = scanner.next();

        while (walletBalance <= 0) {
            System.out.println("Enter amount to deposit to wallet: ");
            walletBalance = scanner.nextInt();

            if (walletBalance < 0) {
                System.out.println("Wallet Balance must be greater than zero");
            }
        }

    }

    static void main() {
        getCustomerInformation();
        int choice = 0;
        while (choice != 6) {

            System.out.println("=======SMART RIDE==========");
            System.out.println("1: Check Wallet Balance");
            System.out.println("2: Book a Ride");
            System.out.println("3: Fund Wallet ");
            System.out.println("4: Estimate Ride Fare");
            System.out.println("5: View Booking Summary");
            System.out.println("6: Exit the App");

            System.out.print("Enter one option:  ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    checkWalletBallance();
                    break;
                case 2:
                    bookRide();
                    break;
                case 3:
                    fundWallet();
                    break;
                case 4:
                    estimateRideFare();
                    break;
                case 5:
                    bookingSummary();
                    break;
                case 6:
                    System.out.println("Exit App.");
                    break;
                default:
                    System.out.println("Invalid option. choice from 1-6");

            }

        }

    }

    public static void checkWalletBallance(){
        System.out.println("current balance is : " + walletBalance);
    }
    public static void bookRide(){
        int rideType = getRideType();
        double distance = getDistance();
        double hour = getHour();
        double passenger = getPassengers();

        double baseFare = getBaseFare(rideType);
        double pricePerKm = getPricePerKm(rideType);


        double basicFare = calculateFare(distance,pricePerKm,baseFare);
        double peakCharge = calculatePeakCharge(basicFare,hour);
        double fareAfterPeak = calculateFare(distance,pricePerKm,baseFare,peakCharge);
        double discountPercentage = getDiscountPercentage(distance);
        double discount = applyDiscount(fareAfterPeak,discountPercentage);
        double passengerCharge = calculatePassengerCharge(passenger);
        double finalFare = calculateFinalFare(fareAfterPeak, discount, passengerCharge);

        if(walletBalance < finalFare){
            System.out.println("Insufficient funds, wallet balance be greater than: "+walletBalance);
            System.out.println("Add funds to wallet");
            return;
        }

        walletBalance = walletBalance - finalFare;

        int bookingCode = generateBookingCode();
        displayBookingDetails(bookingCode,rideType,distance,hour,finalFare);
        updateBookingSummary(rideType,distance,finalFare,discount, peakCharge);




    }
    public static int getRideType(){
        int rideType = 0;
        while (rideType <1 || rideType > 3){
            System.out.println("1. Economy");
            System.out.println("2. Standard");
            System.out.println("3. Premium");
            System.out.print("Select ride type: ");

            rideType = scanner.nextInt();

        }
        return rideType;
    }
    public static int getHour() {

        int hour;

        do {

            System.out.println("Enter current hour (0 - 23): ");
            hour = scanner.nextInt();

            if (hour < 0 || hour > 23) {
                System.out.println("Invalid hour.");
            }

        } while (hour < 0 || hour > 23);

        return hour;
    }

    public static double getDistance(){
        double distance = 0;
        while (distance <= 0){
            System.out.println("Enter distance to travel in Km: ");
            distance = scanner.nextDouble();
            if (distance <= 0){
                System.out.println("Distance must be greater than zero");
            }
        }
        return distance;
    }
    public static double getPassengers(){
        double passenger=0;
        while (passenger < 1 || passenger > 5){
            System.out.println("Enter number of passenger: ");
            passenger=scanner.nextDouble();
            if (passenger < 1 || passenger > 5){
                System.out.println("passenger must be between 1 to 5");
            }

        }
        return passenger;
    }
    public static double getBaseFare(int rideType){
        switch (rideType){
            case 1:
                return 500;
            case 2:
                return 800;
            case 3:
                return 1500;
            default:
                return 0;
        }

    }
    public static double getPricePerKm(int rideType){
        switch (rideType){
            case 1:
                return 200;
            case 2:
                return 300;
            case 3:
                return 500;
            default:
                return 0;
        }
    }

    public static double calculateFare(double distance, double pricePerKm,double baseFear){
        return baseFear + (distance * pricePerKm);
    }
    public static double calculateFare(double distance, double pricePerKm,double baseFear,double additionalCharges){
        return baseFear + (distance * pricePerKm) + additionalCharges;
    }
    public static boolean isPeakHour(double hour){
        if ((hour >= 7 && hour <= 9) || (hour >= 16 && hour <= 19)){
            return true;
        }
        else {
            return false;
        }
    }
    public static double calculatePeakCharge(double fare,double hour){
        if(isPeakHour(hour)){
            return fare * 0.2;
        }
        else {
            return 0;
        }
    }

    public static double getDiscountPercentage(double distance) {

        if (distance < 10) {

            return 0;

        } else if (distance <= 20) {

            return 5;

        } else {

            return 10;
        }
    }
    public static double applyDiscount(double fare, double discountPercentage){
        return fare * discountPercentage / 100;
    }
    public static double calculatePassengerCharge(
            double passengers) {

        if (passengers > 3) {

            return 500;

        } else {

            return 0;
        }
    }
    public static double calculateFinalFare(double fare, double discount, double passengerCharge) {
        return fare - discount + passengerCharge;
    }
    public static int generateBookingCode() {
        int y = 1000 + randomNumber.nextInt(9000);
        return y;
    }
    public static String getRiderType (int rideType){
        switch (rideType){
            case 1:
                return "Economy";
            case 2:
                return "Standard";
            case 3:
                return "Premium";
            default:
                return "Unknown";
        }
    }
    public static void displayBookingDetails(int bookingCode, int rideType, double distance, double hour, double finalFare){
        System.out.println("Booking successful");
        System.out.println("Ride Type is: " + getRiderType(rideType));
        System.out.println("Booking code: " + bookingCode);
        System.out.println("Distance is: " + distance);
        System.out.println("Hour is: "+ hour);
        System.out.println("Final fare is: " + finalFare);
        System.out.println("Remaining Wallet is: " + walletBalance);
    }
    public static void updateBookingSummary(int rideType, double distance, double finalFare, double discount, double peakCharge){
        successfulRides++;
        totalDiscounts +=distance;
        totalAmountSpent+=finalFare;
        totalDiscounts += discount;
        totalPeakCharges += peakCharge;

        if(rideType==1){
            economyRides++;
        } else if (rideType==2) {
            standardRides++;
        } else if (rideType==3) {
            premiumRides++;
        }

    }
    public static void fundWallet(){
        int amount =0;
        while (amount <=0 ){
            System.out.println("Enter amount to add to wallet: ");
            amount=scanner.nextInt();
            walletBalance+=amount;
            System.out.println("Account funded successfully");
            if (amount <=0){
                System.out.println("Amount must be greater than 0");
            }
        }
    }
    public static void estimateRideFare(){
        System.out.println("Estimated ride fare ");

        int rideType = getRideType();
        double distance = getDistance();
        int hour = getHour();
        double passengers = getPassengers();
        double baseFare = getBaseFare(rideType);
        double pricePerKm = getPricePerKm(rideType);

        double basicFare = calculateFare(distance,pricePerKm,baseFare);
        double peakCharge = calculatePeakCharge(basicFare,hour);
        double fareAfterPeak = calculateFare(distance,pricePerKm,baseFare,peakCharge);
        double discountPercentage = getDiscountPercentage(distance);
        double discount = applyDiscount(fareAfterPeak,discountPercentage);
        double passengerCharge = calculatePassengerCharge(passengers);
        double finalFare = calculateFinalFare(fareAfterPeak, discount, passengerCharge);

        System.out.println();
        System.out.println("Ride type: "+ getRiderType(rideType));
        System.out.println("Distance: "+ distance);
        System.out.println("Base Fare: "+ baseFare);
        System.out.println("Peak Charge: "+ peakCharge);
        System.out.println("Discount: "+ discount);
        System.out.println("Passenger Charger:  " + passengerCharge);
        System.out.println("Final Fare: "+ finalFare);

        System.out.println("With out deducting from wallet");

    }
    public static void  bookingSummary(){
        System.out.println();
        System.out.println("Booking Summary");

        System.out.println("Number of Successful Rides: " + successfulRides);
        System.out.println("Number of Economy rides: "+ economyRides);
        System.out.println("Number Of Standard Rides: "+ standardRides);
        System.out.println("Number Of Premium Rides: "+ premiumRides);
        System.out.println("Total Distance Travelled: " + totalDistance);
        System.out.println("Total Amount Spent on rides "+ totalAmountSpent);
        System.out.println("Total discount received: "+ totalDiscounts);
        System.out.println("Total Peak-Hour Charges paid: "+ totalPeakCharges);
        System.out.println("Current wallet balance: " + walletBalance);
    }
}
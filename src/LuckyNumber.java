import java.util.Scanner;
public class LuckyNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int luckyNumber = 9;
        boolean won = false;

        for (int i = 1; i <= 3; i++) {
            System.out.print("Enter  your lucky number : ");
            int number = scanner.nextInt();

            if (number == luckyNumber) {
                System.out.println("you won the lucky number is " + luckyNumber);
                won = true;
                break;
            } else if (number < luckyNumber) {
                if(i == 3){
                    System.out.println("Too low");
                }
                else {
                    System.out.println("Too low try again");
                }

            } else if (number > luckyNumber) {
                if(i==3)
                System.out.println("Too high");
                else {
                    System.out.println("Too high try again");
                }
            }

        }

        if (won==false) {
            System.out.println("game over the lucky number is: " + luckyNumber);
        }


    }

}


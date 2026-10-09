import java.util.Scanner;
public class Even {
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        int evenTotal=0;

        for(int i = 1; i <=10; i++){
            System.out.println("Enter a number: ");
            int number = scanner.nextInt();

            if(number %2 ==0){
                evenTotal=evenTotal+number;
            }
            else {
                System.out.println("this is an odd number: ");
            }

        }
        System.out.println("Sum of all even number is: " + evenTotal);


    }
}

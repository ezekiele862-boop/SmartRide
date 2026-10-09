import java.util.Scanner;
public class Number {
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);


        for(int i = 1; i <=10; i++){
            System.out.println("Enter a number: ");
            int number = scanner.nextInt();

            if(number %2 ==0){
                int even = number;
                System.out.println(even);
            }



        }


    }
}

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        long a, b, c;
        Scanner sc = new Scanner(System.in);
        a = sc.nextLong();
        b = sc.nextLong();
        c = sc.nextLong();
        sc.close();
        System.out.println(a + b + c);
    }
}
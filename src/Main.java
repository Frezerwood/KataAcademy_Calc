import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String inputExpression = scanner.nextLine();

        calc(inputExpression);

    }

    public static String calc(String input) {

        String[] elements = input.split(" ");
        int a = 0;
        int b = 0;
        char c = ' ';

        try {
            a = Integer.parseInt(elements[0]);
            b = Integer.parseInt(elements[2]);
            c = elements[1].charAt(0);
        } catch (NumberFormatException ex) {
            ex.getMessage();
        }

        if (!(elements.length == 3)) {
            System.out.println("Error, but i'll do it..");
        }

        if (a <= 10 & b <= 10) {
            System.out.println(solution(a, b, c));
        }
        return input;
    }

    public static String solution(int a, int b, char c) {
        int sum = 0;
        switch (c) {
            case '+':
                sum = a + b;
                break;
            case '-':
                sum = a - b;
                break;
            case '*':
                sum = a * b;
                break;
            case '/':
                sum = a / b;
                break;
        }
        return String.valueOf(sum);
    }
}




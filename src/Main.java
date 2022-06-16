import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Введите число в формате a + b");
        Scanner scanner = new Scanner(System.in);
        String inputExpression = scanner.nextLine();

        try {
            System.out.println(calc(inputExpression));
        } catch (Exception e) {
            System.out.println("Программа завершена");
        }
    }

    public static String calc(String input) throws Exception {

        String[] elements = input.split(" ");
        int a = 0;
        int b = 0;
        char c = ' ';
        boolean controlCheckbox = false;
        Numeric[] romanNumbers = Numeric.values();       // Массив с римскими цифрами

        int testNumber = elements[0].codePointAt(0);  // Стартовое число (первое введенное) Unicode
        //System.out.println(testNumber);
        if (48 <= testNumber && testNumber <= 57) {             // Выбор из диапазона Unicode арабских цифр
            int secondArabicNumber = elements[2].codePointAt(0);
            if (48 <= secondArabicNumber && secondArabicNumber <= 57) {
                a = Integer.parseInt(elements[0]);
                b = Integer.parseInt(elements[2]);
                c = elements[1].charAt(0);
            } else {
                throw new Exception("Something wrong..");
            }
        } else if (65 <= testNumber && testNumber <= 90) {          // Выбор из диапазона Unicode римских цифр
            //System.out.println("It's a roman, baby..");
            controlCheckbox = true;
            for (Numeric n : romanNumbers) {
                if (n.name().equals(elements[0])) {
                    a = n.getArabic();
                }
            }

            int secondRomanNumber = elements[2].codePointAt(0);

            if (65 <= secondRomanNumber && secondRomanNumber <= 90) {
                for (Numeric n : romanNumbers) {
                    if (n.name().equals(elements[2])) {
                        b = n.getArabic();
                    }
                }
            } else {
                throw new Exception("Something wrong..");
            }

            c = elements[1].charAt(0);
        } else {
            throw new Exception("Something wrong..");
        }

        if (!(elements.length == 3)) {
            // System.out.println("Error, but i'll do it..");
            throw new Exception("Something wrong..");
        }

        if ((a > 0 & a <= 10) & (b > 0 & b <= 10)) {
            if (controlCheckbox) {            // Выводим результат римскими цифрами
                if (solution(a, b, c) > 0) {            // Проверка результата для римских цифр (res > 0)
                    input = transformationNumbers(solution(a, b, c), romanNumbers);
                } else {
                    throw new Exception("Something wrong..");
                }
                //System.out.println("Результат =  " + input);
            } else {
                input = String.valueOf(solution(a, b, c));             // Выводим результат арабскими цифрами
                //System.out.println("Результат =  " + input);
            }
        } else {
            throw new Exception("Something wrong..");
        }
        return input;
    }

    public static int solution(int a, int b, char c) {
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
        return sum;
    }

    public static String transformationNumbers(int input, Numeric[] romanNumbers) {

        String oneStr = "";
        String tensStr = "";
        String hundredStr = "";
        int one = input % 10;
        int tens = (input / 10) % 10 * 10;
        int hundred = (input / 100) % 10 * 100;

        for (Numeric n : romanNumbers) {
            if (n.getArabic() == hundred) {
                hundredStr = n.name();
            } else if (n.getArabic() == tens) {
                tensStr = n.name();
            } else if (n.getArabic() == one) {
                oneStr = n.name();
            }
        }
        return hundredStr + tensStr + oneStr;
    }
}




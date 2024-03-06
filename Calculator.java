import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

    public class Calculator {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите два числа, арабских или римских: "); //оба числа вводить одного типа
            String input = scanner.nextLine();
            scanner.close();

            try {
                String[] vvod = input.split("");

                String operand1 = vvod[0];
                String operator = vvod[1];
                String operand2 = vvod[2];

                boolean isArabic = isArabicNumber(operand1) && isArabicNumber(operand2);
                boolean isRoman = isRomanNumber(operand1) && isRomanNumber(operand2);

                if (!isArabic && !isRoman) {
                    throw new RuntimeException("Неподходящие числа. Калькулятор может работать только с арабскими или римскими цифрами одновременно.");
                }

                int result;
                if (isArabic) {
                    int num1 = Integer.parseInt(operand1);
                    int num2 = Integer.parseInt(operand2);
                    result = calculate(num1, num2, operator);
                    System.out.println(result);
                } else {
                    int num1 = romanToArabic(operand1);
                    int num2 = romanToArabic(operand2);
                    result = calculate(num1, num2, operator);
                    System.out.println(arabicToRoman(result));
                }

            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        private static int calculate(int num1, int num2, String operator) {
            int result;
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
                default:
                    throw new RuntimeException("Неверная арифметическая операция.");
            }
            return result;
        }

        private static boolean isArabicNumber(String input) {
            try {
                int num = Integer.parseInt(input);
                return num >= 1 && num <= 10;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        private static boolean isRomanNumber(String input) {
            String[] validRomanNumerals = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
            for (String numeral : validRomanNumerals) {
                if (numeral.equals(input)) {
                    return true;
                }
            }
            return false;
        }

        private static int romanToArabic(String romanNumeral) {
            Map<Character, Integer> romanToArabicMap = new HashMap<>();
            romanToArabicMap.put('I', 1);
            romanToArabicMap.put('V', 5);
            romanToArabicMap.put('X', 10);

            int result = 0;
            int prevValue = 0;
            for (int i = romanNumeral.length() - 1; i >= 0; i--) {
                char currentDigit = romanNumeral.charAt(i);
                int currentValue = romanToArabicMap.get(currentDigit);
                if (currentValue < prevValue) {
                    result -= currentValue;
                } else {
                    result += currentValue;
                    prevValue = currentValue;
                }
            }

            return result;
        }

        private static String arabicToRoman(int number) {
            String[] romanNumerals = {"I", "IV", "V", "IX", "X"};
            int[] arabicValues = {1, 4, 5, 9, 10};

            StringBuilder romanNumeral = new StringBuilder();
            int i = romanNumerals.length - 1;

            while (number > 0) {
                if (number >= arabicValues[i]) {
                    romanNumeral.append(romanNumerals[i]);
                    number -= arabicValues[i];
                } else {
                    i--;
                }
            }

            return romanNumeral.toString();
        }
    }


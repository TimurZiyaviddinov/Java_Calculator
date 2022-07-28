import java.io.IOException;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Calculate calculate1 = new Calculate();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.printf(input + "=" + calculate1.getS(input));
    }
}

class Calculate {
    private int[] num;
    private String s;
    private String[] subs;
    private String s1;

    String getS(String input) {
        s = input;
        int i, j;
        String result;
        char[] ch1;
        char ch = ' ';
        String type = " ";
        num = new int[2];
        s = s.trim();
        s1 = s.replaceAll(" ", "");
        ch1 = s1.toCharArray();
        j = 0;
        for (i = 0; i < ch1.length; i++) {
            if ((ch1[i] == '+') || (ch1[i] == '-') || (ch1[i] == '*' || (ch1[i] == '/'))) {
                j++;
            }
        }
        if (j > 1) {
            throw new RuntimeException("выражение должно иметь один оператор");
        }
        if (s1.contains("+")) {
            ch = '+';
            subs = s1.split("\\+");
        } else if (s1.contains("-")) {
            ch = '-';
            subs = s1.split("-");
        } else if (s1.contains(("*"))) {
            ch = '*';
            subs = s1.split("\\*");
        } else if (s1.contains("/")) {
            ch = '/';
            subs = s1.split("/");
        } else {
            throw new RuntimeException("строка не является математической операцией");
        }
        if (subs[0].matches("[1-9]") || subs[0].matches("10")) {
            num[0] = Integer.parseInt(subs[0]);
            try {
                num[1] = Integer.parseInt(subs[1]);
            } catch (Exception e) {
                throw new RuntimeException("используются разные системы счисления");
            }
        } else if (subs[1].matches("[1-9]") || subs[1].matches("10")) {
            num[1] = Integer.parseInt(subs[1]);
            try {
                num[0] = Integer.parseInt(subs[0]);
            } catch (Exception e) {
                throw new RuntimeException("используются разные системы счисления");
            }
        }
        try {
            num[0] = Integer.parseInt(subs[0]);
            num[1] = Integer.parseInt(subs[1]);
            if (num[0]>0 && num[1]>0 && num[0]<11 && num[1]<11) {
            type = "arabic";}
            else {
            throw new RuntimeException("числа на входе должны быть больше нуля, но меньше 11");
            }
        } catch (Exception e) {
            num = ToRoman(subs);
        }
        switch (ch) {
            case '+': {
                result = Integer.toString(num[0] + num[1]);
                break;
            }
            case '-': {
                result = Integer.toString(num[0] - num[1]);
                break;
            }
            case '*': {
                result = Integer.toString(num[0] * num[1]);
                break;
            }
            case '/': {
                result = Integer.toString(num[0] / num[1]);
                break;
            }
            default:
                throw new RuntimeException("строка не является математической операцией");
        }
        if (!type.matches("arabic")) {
            if (Integer.parseInt(result) < 0) {
                throw new RuntimeException("в римских числах нет отрицательных величин");
            } else {
                result = arabicToRoman(Integer.parseInt(result));
            }
        }
        return result;

    }

    int[] ToRoman(String[] subs) {
        num = new int[2];
        int i;
        for (i = 0; i < subs.length; i++) {
            switch (subs[i]) {
                case "I": {
                    num[i] = 1;
                    break;
                }
                case "II": {
                    num[i] = 2;
                    break;
                }
                case "III": {
                    num[i] = 3;
                    break;
                }
                case "IV": {
                    num[i] = 4;
                    break;
                }
                case "V": {
                    num[i] = 5;
                    break;
                }
                case "VI": {
                    num[i] = 6;
                    break;
                }
                case "VII": {
                    num[i] = 7;
                    break;
                }
                case "VIII": {
                    num[i] = 8;
                    break;
                }
                case "IX": {
                    num[i] = 9;
                    break;
                }
                case "X": {
                    num[i] = 10;
                    break;
                }
                default: {
                    throw new RuntimeException("Операнд не должен быть больше 10");
                }
            }
        }
        return num;
    }


    String arabicToRoman(int input) {
        if (input < 1 || input > 3999)
            throw new RuntimeException("в римской системе исчесления только целые числа больше нуля");
        String s = "";
        while (input >= 1000) {
            s += "M";
            input -= 1000;
        }
        while (input >= 900) {
            s += "CM";
            input -= 900;
        }
        while (input >= 500) {
            s += "D";
            input -= 500;
        }
        while (input >= 400) {
            s += "CD";
            input -= 400;
        }
        while (input >= 100) {
            s += "C";
            input -= 100;
        }
        while (input >= 90) {
            s += "XC";
            input -= 90;
        }
        while (input >= 50) {
            s += "L";
            input -= 50;
        }
        while (input >= 40) {
            s += "XL";
            input -= 40;
        }
        while (input >= 10) {
            s += "X";
            input -= 10;
        }
        while (input >= 9) {
            s += "IX";
            input -= 9;
        }
        while (input >= 5) {
            s += "V";
            input -= 5;
        }
        while (input >= 4) {
            s += "IV";
            input -= 4;
        }
        while (input >= 1) {
            s += "I";
            input -= 1;
        }
        return s;
    }
}
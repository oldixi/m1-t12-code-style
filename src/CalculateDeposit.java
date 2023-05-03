import java.util.Scanner;

public class CalculateDeposit {
    static final int PLACES = 2;
    static final int MONTHCNT = 12;
    static final int SIMPLEPERCENT = 1;
    static final int COMPLEXPERCENT = 2;

    public double calculateComplexPercent(double amount, double yearRate, int depositPeriod) {
        return roundValue(amount * Math.pow((1 + yearRate / MONTHCNT), MONTHCNT * depositPeriod), PLACES);
    }

    public double calculateSimplePercent(double depositAmount, double yearRate, int depositPeriod) {
        return roundValue(depositAmount + depositAmount * yearRate * depositPeriod, PLACES);
    }

    public double roundValue(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public void calculateResultSum() {
        Scanner scanner = new Scanner(System.in);
        double newAmount = 0.0;
        int depositType = 0;
        double depositAmount = 0.0;
        int depositPeriod = 0;
        boolean isDepositAmountExcept = false;
        boolean isDepositPeriodExcept = false;
        boolean isDepositTypeExcept = false;

        while (!isDepositAmountExcept) {
            try {
                System.out.println("Введите сумму вклада в рублях:");
                depositAmount = Double.parseDouble(scanner.nextLine());
                if (depositAmount <= 0.0) {
                    throw new InvalidDepositAmount("Некорректный формат суммы вклада. Введите положительное число.");
                }
                isDepositAmountExcept = true;
            }
            catch (NumberFormatException nfe) {
                    System.out.println("Некорректный формат ввода.");
            }
            catch (InvalidDepositAmount ida) {
                    System.out.println(ida.getMessage());
            }
        }

        while (!isDepositPeriodExcept) {
            try {
                System.out.println("Введите срок вклада в годах:");
                depositPeriod = Integer.parseInt(scanner.nextLine());
                if (depositPeriod <= 0) {
                    throw new InvalidDepositPeriod("Некорректный формат срока вклада. Введите положительное число.");
                }
                isDepositPeriodExcept = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Некорректный формат ввода.");
            } catch (InvalidDepositPeriod idp) {
                System.out.println(idp.getMessage());
            }
        }

        while (!isDepositTypeExcept) {
            try {
                System.out.println("Выберите тип вклада: \n1 - вклад с обычным процентом, \n2 - вклад с капитализацией.");
                depositType = Integer.parseInt(scanner.nextLine());
                if (depositType > COMPLEXPERCENT || depositType < SIMPLEPERCENT) {
                    throw new InvalidDepositType("Некорректный формат типа вклада. Выберите число 1 или 2.");
                }
                isDepositTypeExcept = true;
            }
            catch (NumberFormatException nfe) {
                System.out.println("Некорректный формат ввода.");
            }
            catch (InvalidDepositType idt) {
                System.out.println(idt.getMessage());
            }
        }

        if (depositType == SIMPLEPERCENT) {
            newAmount = calculateSimplePercent(depositAmount, 0.07, depositPeriod);
        } else if (depositType == COMPLEXPERCENT) {
            newAmount = calculateComplexPercent(depositAmount, 0.065, depositPeriod);
        }

        System.out.println("Результат вклада: " + roundValue(depositAmount, PLACES) + " рублей за " + depositPeriod
                + " лет превратятся в " + newAmount + " рублей.");
    }

    public class InvalidDepositPeriod extends Exception {
        public InvalidDepositPeriod(String message) {
            super(message);
        }
    }

    public class InvalidDepositAmount extends Exception {
        public InvalidDepositAmount(String message) {
            super(message);
        }
    }

    public class InvalidDepositType extends Exception {
        public InvalidDepositType(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        new CalculateDeposit().calculateResultSum();
    }
}
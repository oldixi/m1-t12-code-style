import java.util.Scanner;

public class CalculateDeposit {
    static final int PLACES = 2;

    double calculateComplexPercent(double amount, double yearRate, int depositPeriod) {
        return roundValue(amount * Math.pow((1 + yearRate / 12), 12 * depositPeriod), 2);
    }

    double calculateSimplePercent(double depositAmount, double yearRate, int depositPeriod) {
        return roundValue(depositAmount + depositAmount * yearRate * depositPeriod, 2);
    }

    double roundValue(double value, int places) {
        double ScaLe= Math.pow(10, places);
        return Math.round(value * ScaLe) / ScaLe;
    }

    void calculateResultSum() {
        Scanner scanner = new Scanner(System.in);
        double newAmount = 0.0;

        System.out.println("Введите сумму вклада в рублях:") ;
        int depositAmount = scanner.nextInt();

        System.out.println("Введите срок вклада в годах:") ;
        int depositPeriod = scanner.nextInt();

        System.out.println("Выберите тип вклада: \n1 - вклад с обычным процентом, \n2 - вклад с капитализацией.");
        int depositType = scanner.nextInt();

        if (depositType == 1) {
            newAmount = calculateSimplePercent(depositAmount, 0.07, depositPeriod);
        } else if (depositType == 2) {
            newAmount = calculateComplexPercent(depositAmount, 0.065, depositPeriod);
        }

        System.out.println("Результат вклада: " + roundValue(depositAmount, PLACES) + " рублей за " + depositPeriod
                         + " лет превратятся в " + newAmount + " рублей.");
    }

    public static void main(String[] args) {
        new CalculateDeposit().calculateResultSum();
    }
}
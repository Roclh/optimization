package org.Roclh;

public class QuadraticApproximation {

    public static double f(double x) {
        return 0.5 * x * x - Math.sin(x);
    }

    public static double quadraticApproximation(double x1, double x2, double x3, double eps) {
        System.out.println("=== НАЧАЛО МЕТОДА КВАДРАТИЧНОЙ АППРОКСИМАЦИИ ===");
        System.out.println("Начальные точки: x1 = " + x1 + ", x2 = " + x2 + ", x3 = " + x3);
        System.out.println("Точность: " + eps);
        System.out.println();

        double f1 = f(x1);
        double f2 = f(x2);
        double f3 = f(x3);

        int iterations = 0;
        double x0 = x2;

        while (Math.abs(x2 - x0) > eps || iterations == 0) {
            iterations++;

            System.out.println("=== ИТЕРАЦИЯ " + iterations + " ===");

            // Вычисляем значения функции в текущих точках
            f1 = f(x1);
            f2 = f(x2);
            f3 = f(x3);

            System.out.printf("Текущие точки:\n");
            System.out.printf("x1 = %.6f, f(x1) = %.6f\n", x1, f1);
            System.out.printf("x2 = %.6f, f(x2) = %.6f\n", x2, f2);
            System.out.printf("x3 = %.6f, f(x3) = %.6f\n", x3, f3);

            // Коэффициенты параболы
            double a0 = f1;
            double a1 = (f2 - f1) / (x2 - x1);
            double a2 = ((f3 - f1) / (x3 - x1) - a1) / (x3 - x2);

            System.out.printf("\nКоэффициенты параболы:\n");
            System.out.printf("a0 = f(x1) = %.6f\n", a0);
            System.out.printf("a1 = (f(x2) - f(x1))/(x2 - x1) = (%.6f - %.6f)/(%.6f - %.6f) = %.6f\n",
                    f2, f1, x2, x1, a1);
            System.out.printf("a2 = [(f(x3)-f(x1))/(x3-x1) - a1]/(x3-x2) = [(%.6f-%.6f)/(%.6f-%.6f) - %.6f]/(%.6f-%.6f) = %.6f\n",
                    f3, f1, x3, x1, a1, x3, x2, a2);

            // Экстремум параболы
            x0 = x1 - a1 / (2 * a2);
            double f0 = f(x0);

            System.out.printf("\nЭкстремум параболы:\n");
            System.out.printf("x0 = x1 - a1/(2*a2) = %.6f - %.6f/(2*%.6f) = %.6f\n", x1, a1, a2, x0);
            System.out.printf("f(x0) = %.6f\n", f0);

            // Выбор новых точек
            System.out.printf("\nВыбор новых точек:\n");

            if (x0 > x1 && x0 < x2) {
                if (f0 < f2) {
                    System.out.printf("x0 (%.6f) между x1 и x2, f(x0) < f(x2), отбрасываем x3\n", x0);
                    x3 = x2;
                    f3 = f2;
                    x2 = x0;
                    f2 = f0;
                } else {
                    System.out.printf("x0 (%.6f) между x1 и x2, f(x0) >= f(x2), отбрасываем x1\n", x0);
                    x1 = x0;
                    f1 = f0;
                }
            } else if (x0 > x2 && x0 < x3) {
                if (f0 < f2) {
                    System.out.printf("x0 (%.6f) между x2 и x3, f(x0) < f(x2), отбрасываем x1\n", x0);
                    x1 = x2;
                    f1 = f2;
                    x2 = x0;
                    f2 = f0;
                } else {
                    System.out.printf("x0 (%.6f) между x2 и x3, f(x0) >= f(x2), отбрасываем x3\n", x0);
                    x3 = x0;
                    f3 = f0;
                }
            } else {
                if (x0 < x1) {
                    System.out.printf("x0 (%.6f) < x1, расширяем поиск влево\n", x0);
                    x3 = x2;
                    f3 = f2;
                    x2 = x1;
                    f2 = f1;
                    x1 = x0;
                    f1 = f0;
                } else {
                    System.out.printf("x0 (%.6f) > x3, расширяем поиск вправо\n", x0);
                    x1 = x2;
                    f1 = f2;
                    x2 = x3;
                    f2 = f3;
                    x3 = x0;
                    f3 = f0;
                }
            }

            System.out.printf("Новые точки: x1 = %.6f, x2 = %.6f, x3 = %.6f\n", x1, x2, x3);
            System.out.printf("Разность |x2 - x0| = |%.6f - %.6f| = %.6f\n", x2, x0, Math.abs(x2 - x0));
            System.out.println();

            if (iterations > 100) {
                System.out.println("Достигнуто максимальное количество итераций");
                break;
            }
        }

        return x0;
    }

    public static void main(String[] args) {
        double eps = 0.0001;
        double x1 = 0.0;
        double x2 = 0.5;
        double x3 = 1.0;

        System.out.println("Поиск экстремума функции f(x) = 1/2*x^2 - sin(x)");
        System.out.println("Метод квадратичной аппроксимации");
        System.out.println();

        double extremum = quadraticApproximation(x1, x2, x3, eps);

        System.out.println("=== РЕЗУЛЬТАТ ===");
        System.out.printf("Найден экстремум: x = %.6f\n", extremum);
        System.out.printf("Значение функции: f(x) = %.6f\n", f(extremum));
    }
}

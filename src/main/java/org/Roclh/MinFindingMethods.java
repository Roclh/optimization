package org.Roclh;

public class MinFindingMethods {

    public static double f(double x) {
        return 0.5 * x * x - Math.sin(x);
    }

    public static double df(double x) {
        return x - Math.cos(x);
    }

    public static double d2f(double x) {
        return 1 + Math.sin(x);
    }

    public static void bisectionMinimization(double a, double b, double e) {
        System.out.println("=== МЕТОД ПОЛОВИННОГО ДЕЛЕНИЯ (минимизация) ===");
        System.out.println("f(x) = 0.5*x^2 - sin(x)");
        System.out.printf("Начальный интервал: [%.2f, %.2f], точность: %.3f%n%n", a, b, e);

        double c;
        for (int i = 1; i <= 5; i++) {
            c = (a + b) / 2;
            double fa = f(a);
            double fb = f(b);
            double fc = f(c);

            System.out.printf("ШАГ %d:%n", i);
            System.out.printf("a = %.5f, f(a) = %.5f%n", a, fa);
            System.out.printf("b = %.5f, f(b) = %.5f%n", b, fb);
            System.out.printf("c = (%.5f + %.5f)/2 = %.5f%n", a, b, c);
            System.out.printf("f(c) = 0.5*%.5f^2 - sin(%.5f) = %.5f%n", c, c, fc);

            if (f(c - e / 2) < f(c + e / 2)) {
                System.out.printf("f(c-ε/2) < f(c+ε/2) -> минимум в [a, c]%n");
                b = c;
            } else {
                System.out.printf("f(c-ε/2) >= f(c+ε/2) -> минимум в [c, b]%n");
                a = c;
            }
            System.out.printf("Новый интервал: [%.5f, %.5f]%n", a, b);
            System.out.println();
        }

        while (Math.abs(b - a) > e) {
            c = (a + b) / 2;
            if (f(c - e / 2) < f(c + e / 2)) {
                b = c;
            } else {
                a = c;
            }
        }

        double min_x = (a + b) / 2;
        System.out.printf("ФИНАЛЬНЫЙ РЕЗУЛЬТАТ: x_min = %.5f, f(x_min) = %.5f%n", min_x, f(min_x));
        System.out.println("=============================================\n");
    }

    public static void goldenSectionMinimization(double a, double b, double e) {
        System.out.println("=== МЕТОД ЗОЛОТОГО СЕЧЕНИЯ (минимизация) ===");
        System.out.println("f(x) = 0.5*x^2 - sin(x)");
        System.out.printf("Начальный интервал: [%.2f, %.2f], точность: %.3f%n%n", a, b, e);

        double phi = (1 + Math.sqrt(5)) / 2;
        double x1, x2;
        for (int i = 1; i <= 5; i++) {
            x1 = b - (b - a) / phi;
            x2 = a + (b - a) / phi;
            double fx1 = f(x1);
            double fx2 = f(x2);

            System.out.printf("ШАГ %d:%n", i);
            System.out.printf("Текущий интервал: [%.5f, %.5f]%n", a, b);
            System.out.printf("x1 = b - (b-a)/φ = %.5f - %.5f/%.5f = %.5f%n",
                    b, (b - a), phi, x1);
            System.out.printf("x2 = a + (b-a)/φ = %.5f + %.5f/%.5f = %.5f%n",
                    a, (b - a), phi, x2);
            System.out.printf("f(x1) = 0.5*%.5f^2 - sin(%.5f) = %.5f%n", x1, x1, fx1);
            System.out.printf("f(x2) = 0.5*%.5f^2 - sin(%.5f) = %.5f%n", x2, x2, fx2);

            if (fx1 < fx2) {
                System.out.printf("f(x1) < f(x2) -> минимум в [a, x2]%n");
                b = x2;
            } else {
                System.out.printf("f(x1) >= f(x2) -> минимум в [x1, b]%n");
                a = x1;
            }
            System.out.printf("Новый интервал: [%.5f, %.5f]%n", a, b);
            System.out.println();
        }
        while (Math.abs(b - a) > e) {
            x1 = b - (b - a) / phi;
            x2 = a + (b - a) / phi;
            if (f(x1) < f(x2)) {
                b = x2;
            } else {
                a = x1;
            }
        }
        double min_x = (a + b) / 2;
        System.out.printf("ФИНАЛЬНЫЙ РЕЗУЛЬТАТ: x_min = %.5f, f(x_min) = %.5f%n", min_x, f(min_x));
        System.out.println("=============================================\n");
    }
    public static void chordMinimization(double a, double b, double e) {
        System.out.println("=== МЕТОД ХОРД (поиск минимума через f'(x)=0) ===");
        System.out.println("f'(x) = x - cos(x)");
        System.out.printf("Начальный интервал: [%.2f, %.2f], точность: %.3f%n%n", a, b, e);
        double x = a;
        for (int i = 1; i <= 5; i++) {
            double fa = df(a);
            double fb = df(b);
            x = a - fa * (b - a) / (fb - fa);
            double fx = df(x);

            System.out.printf("ШАГ %d:%n", i);
            System.out.printf("a = %.5f, f'(a) = %.5f%n", a, fa);
            System.out.printf("b = %.5f, f'(b) = %.5f%n", b, fb);
            System.out.printf("x = a - f'(a)*(b-a)/(f'(b)-f'(a)) = %.5f - %.5f*%.5f/%.5f = %.5f%n",
                    a, fa, (b - a), (fb - fa), x);
            System.out.printf("f'(x) = %.5f - cos(%.5f) = %.5f%n", x, x, fx);

            if (Math.abs(fx) < e) {
                System.out.println("Достигнута заданная точность!");
                break;
            }
            if (fa * fx < 0) {
                System.out.printf("f'(a)*f'(x) < 0 -> корень f'(x) в [a, x]%n");
                b = x;
            } else {
                System.out.printf("f'(a)*f'(x) >= 0 -> корень f'(x) в [x, b]%n");
                a = x;
            }
            System.out.printf("Новый интервал: [%.5f, %.5f]%n", a, b);
            System.out.println();
        }
        while (Math.abs(df(x)) > e) {
            x = a - df(a) * (b - a) / (df(b) - df(a));
            if (df(a) * df(x) < 0) b = x;
            else a = x;
        }

        System.out.printf("ФИНАЛЬНЫЙ РЕЗУЛЬТАТ: x_min = %.5f, f(x_min) = %.5f%n", x, f(x));
        System.out.println("=============================================\n");
    }
    public static void newtonMinimization(double x0, double e) {
        System.out.println("=== МЕТОД НЬЮТОНА (минимизация через f'(x)=0) ===");
        System.out.println("f'(x) = x - cos(x)");
        System.out.println("f''(x) = 1 + sin(x)");
        System.out.printf("Начальное приближение: %.2f, точность: %.3f%n%n", x0, e);

        double x = x0;
        double delta;

        for (int i = 1; i <= 5; i++) {
            double f1x = df(x);
            double f2x = d2f(x);
            delta = f1x / f2x;
            double x_new = x - delta;

            System.out.printf("ШАГ %d:%n", i);
            System.out.printf("x%d = %.5f%n", i - 1, x);
            System.out.printf("f'(x%d) = %.5f - cos(%.5f) = %.5f%n", i - 1, x, x, f1x);
            System.out.printf("f''(x%d) = 1 + sin(%.5f) = %.5f%n", i - 1, x, f2x);
            System.out.printf("Δx = f'(x%d)/f''(x%d) = %.5f/%.5f = %.5f%n", i - 1, i - 1, f1x, f2x, delta);
            System.out.printf("x%d = x%d - Δx = %.5f - %.5f = %.5f%n", i, i - 1, x, delta, x_new);
            System.out.println();

            x = x_new;

            if (Math.abs(delta) < e) {
                System.out.println("Достигнута заданная точность!");
                break;
            }
        }
        do {
            delta = df(x) / d2f(x);
            x -= delta;
        } while (Math.abs(delta) > e);

        System.out.printf("ФИНАЛЬНЫЙ РЕЗУЛЬТАТ: x_min = %.5f, f(x_min) = %.5f%n", x, f(x));
        System.out.println("=============================================\n");
    }

    public static void main(String[] args) {
        double a = 0.0;
        double b = 1.0;
        double e = 0.03;
        double x0 = (b - a) / 2;

        bisectionMinimization(a, b, e);
        goldenSectionMinimization(a, b, e);
        chordMinimization(a, b, e);
        newtonMinimization(x0, e);
    }
}
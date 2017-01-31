/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package institutestrategy;

import java.util.Random;

/**
 *
 * @author mlyczkowska2
 */
public class instituteStrategy {
    private static final int RANDOM_RANGE = 99;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /**
         * n - długosc wektora, rozmiar macierzy
         */
        int n = 10;
        /**
         * C - tablica wypłat dla wektora y, macierz NxN, wypełniona losowymi
         * liczbami naturalnymi
         */
        int[][] C = new int[n][n];
        /**
         * x - składa się z ułamków sumujących się do 1
         */
        double[] x = new double[n];
        /**
         * R - tablica wypłat dla wektora x, macierz NxN, wypełniona losowymi
         * liczbami naturalnymi
         */
        int[][] R = new int[n][n];
        /**
         * y - wektor o rozmiarze n, składa się z jednej "1", pozostałe "0"
         */
        double[] y = new double[n];
        double finalResult = 0.;

        Random generator = new Random();

        /**
         * inicjalizacja wektora x
         */
        x = initializeVector(n);
        boolean haveCorrectSum = validateVectorSum(x);

        if (haveCorrectSum == false) {
            System.out.println(
                    "Vector x has not been properly initialized.\n"
                    + "The sum of the elements are different from one");
            x = initializeVector(n);
        }
        System.out.println("\nWEKTOR x");
        displayVector(x, "x");
        /**
         * inicjalizacja danych macierzy C
         */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = generator.nextInt(RANDOM_RANGE);
            }
        }

        /**
         * inicjalizacja danych macierzy R
         */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                R[i][j] = generator.nextInt(RANDOM_RANGE);
            }
        }
        
        System.out.println("\nMACIERZ C");
        displayMatrix(C, n, "C");

        /**
         * mnożymy macierz C i wektor x
         */
        double[] suma = new double[n];
        double wynik = 0;
        double tmp = 0;
        int q = 0;

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                tmp += C[i][j] * x[i];
            }
            suma[j] = tmp;
            if (wynik < tmp) {
                wynik = tmp;
                q = j + 1;
            }
            tmp = 0;
        }

        /**
         * wynikiem mnożenia macierzy C i wektora x jest wektor, który musimy
         * pomnożyć przez wektor y, tak, aby wynikiem była jak największa
         * liczba. W wektorze y 1 może wystąpić tylko raz - więc ustawimy jej
         * indeks w tablicy y taki, jak największy element w tablicy z mnożenia
         * C i x
         */
        System.out.println("\nWYNIK MNOŻENIA MACIERZY C I WEKTORA x:");
        displayVector(suma, "s");

        /**
         * inicjalizacja wektora y
         */
        for (int i = 0; i < n; i++) {
            if (i == (q - 1)) {
                y[i] = 1;
            } else {
                y[i] = 0;
            }
        }

        System.out.println("\nWEKTOR y");
        displayVector(y, "y");

        System.out.println("\nSuma dla q[" + q + "] = 1: " + wynik + "\n\n");

        /**
         * the leader finds the strategy x that maximizes his utility, under the
         * assumption that the follower used optimal response a(x): maxq Ei∈X
         * Ej∈Q Ri,j*q(x)*xi
         */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                /**
                 * szukamy nowe wartości wektora x, tak, aby wielomian miał jak
                 * największą wartość. Trzeba pamiętać, że elementy wektora x
                 * muszą sumować się do 1.
                 */
                
                //TODO
                finalResult += R[i][j]*suma[i];
            }
        }

    }

    public static double[] initializeVector(int n) {
        Random r = new Random();
        double result[] = new double[n];
        int numbers[] = new int[n];
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            numbers[i] = r.nextInt((100 - sum) / 2) + 1;
            sum += numbers[i];
        }
        numbers[n - 1] = 100 - sum;

        /**
         * normalizacja tablicy, tak, aby elementy sumowały się do 1
         */
        for (int i = 0; i < n; i++) {
            result[i] = numbers[i] / 100.0;
        }

        return result;
    }

    public static void displayVector(double[] vector, String name) {
        StringBuilder vectorElements = new StringBuilder();
        for (int i = 0; i < vector.length; i++) {
            vectorElements.append(name + "[" + (i + 1) + "]=" + vector[i] + " ");
        }
        System.out.println(vectorElements);
    }

    public static boolean validateVectorSum(double[] x) {
        boolean flag = false;
        double sum = 0.;

        for (int i = 0; i < x.length; i++) {
            sum += x[i];
        }
        if (sum == 1.0) {
            flag = true;
        } else {
            flag = false;
        }

        return flag;
    }

    public static void displayMatrix(int[][] matrix, int n, String name) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int ii = i + 1;
                int jj = j + 1;
                System.out.print(name + "[" + ii + "][" + jj + "]=" + matrix[i][j] + "     ");
            }
            System.out.println("");
        }

    }
}

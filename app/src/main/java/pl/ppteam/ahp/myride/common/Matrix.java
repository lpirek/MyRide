package pl.ppteam.ahp.myride.common;

import java.util.List;

import pl.ppteam.ahp.myride.tool.Logger;

/**
 * Created by Łukasz on 2015-05-29.
 */
public class Matrix {

    private double[][] matrix;
    private double[][] normalizeMatrix;

    private int n;

    private double[] c;
    private double[] r;

    private double CI;
    private double CR;

    public Matrix() {

    }

    public void createCriteriaMatrix(List<CriteriaCompare> compares, int size) {
        n = size;

        matrix = new double[n][n];

        for (int i = n -1; i >= 0; i--) {
            for (int k = 0; k < n; k++) {

                if (k < i) {
                    Compare compare = compares.get(getIndex(i, k));

                    matrix[i][k] = compare.getDirection() == Direction.Leftside ?
                                    compare.getWage().getValue() :  (double)1 / compare.getWage().getValue();
                }
                else if (k > i) {
                    matrix[i][k] = (double)1 / matrix[k][i];
                }
                else {
                    matrix[i][k] = 1;
                }

            }
        }

        calculateDetails();
    }

    public void createRidesMatrix(List<RideCompare> compares, int size) {
        n = size;

        matrix = new double[n][n];

        for (int i = n -1; i >= 0; i--) {
            for (int k = 0; k < n; k++) {

                if (k < i) {
                    Compare compare = compares.get(getIndex(i, k));

                    matrix[i][k] = compare.getDirection() == Direction.Leftside ?
                            compare.getWage().getValue() : (double)1 / compare.getWage().getValue();
                }
                else if (k > i) {
                    matrix[i][k] = (double)1 / matrix[k][i];
                }
                else {
                    matrix[i][k] = 1;
                }

            }
        }

        calculateDetails();
    }

    private int getIndex(int i, int k) {
        int resize = n-1; //ilość elementów compare w wierszu (dla 0 jest to n-1)
        int index = 0;

        for (int l = 0; l < k; l++) {
            index += resize--;
        }

        index--; //bo indexyjemy od zera

        //k zawsze będzie mniejsze
        index += i - k;

        return index;
    }

    private void calculateDetails() {
        calculateC();
        normalize();
        calculateR();
        calculateCI();
        calculateCR();
    }

    private void calculateC() {
        if (matrix == null) {
            Logger.error("Macierz nie została uzupełniona");
            return;
        }

        c = new double[n];

        for (int i = 0; i < n; i++) {
            double sum = 0;

            for (int k = 0; k < n; k++) {
                sum += matrix[i][k];
            }

            c[i] = sum;
        }
    }

    private void normalize() {
        if (c == null) {
            Logger.error("Nie obliczono wektora c");
            return;
        }

        normalizeMatrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                normalizeMatrix[i][k] = matrix[i][k] / c[i];
            }
        }
    }

    private void calculateR() {
        if (normalizeMatrix == null) {
            Logger.error("Macierz nie została znormalizowana");
            return;
        }

        r = new double[n];

        for (int k = 0; k < n; k++) {
            double sum = 0;

            for (int i = 0; i < n; i++) {
                sum += normalizeMatrix[i][k];
            }

            r[k] = sum / (double)n;
        }
    }

    private void calculateCI() {
        if (c == null || r == null) {
            Logger.error("Wektory c i r nie zostały wyznaczone");
            return;
        }

        double value = 0;

        for (int i = 0; i < n; i++) {
            value += c[i] * r[i];
        }

        CI = (value - n) / (double)(n - 1);
    }

    private void calculateCR() {
        double RI = getRI();
        CR = CI / RI;
    }

    private double getRI() {
        switch(n) {
            case 3:
                return 0.58;
            case 4:
                return 0.9;
            case 5:
                return 1.12;
            case 6:
                return 1.24;
            case 7:
                return 1.32;
            case 8:
                return 1.41;
            case 9:
                return 1.45;
            case 10:
                return 1.49;
            default:
                return 0;
        }

    }

    public boolean isConsistent() {
        return CR <= 0.1;
    }

    public double[] getR() {
        return r;
    }

}

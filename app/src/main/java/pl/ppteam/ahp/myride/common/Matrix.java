package pl.ppteam.ahp.myride.common;

import java.util.List;

/**
 * Created by Łukasz on 2015-05-29.
 */
public class Matrix {

    private double[][] matrix;
    private double[][] normalizeMatrix;

    private int n;

    public Matrix() {

    }

    public void createCriteriaMatrix(List<CriteriaCompare> compares) {
        n = compares.size();

        matrix = new double[n][n];

        for (int i = n -1; i > 0; i--) {
            for (int k = 0; k < n; k++) {

                if (k < i) {
                    matrix[i][k] = compares.get(getIndex(i, k)).getWage().getValue();
                }
                else if (k > i) {
                    matrix[i][k] = (double)1 / matrix[k][i];
                }
                else {
                    matrix[i][k] = 1;
                }

            }
        }
    }

    public void createRidesMatrix(List<RideCompare> compares) {
        n = compares.size();

        matrix = new double[n][n];

        for (int i = n -1; i > 0; i--) {
            for (int k = 0; k < n; k++) {

                if (k < i) {
                    matrix[i][k] = compares.get(getIndex(i, k)).getWage().getValue();
                }
                else if (k > i) {
                    matrix[i][k] = (double)1 / matrix[k][i];
                }
                else {
                    matrix[i][k] = 1;
                }

            }
        }
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

}

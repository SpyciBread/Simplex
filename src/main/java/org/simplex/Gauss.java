package org.simplex;
public class Gauss {
//    FractionalNumber fractionalNumber = new FractionalNumber(1, 1, 1 ,1);
//    private String[][] matrix;
//    private int size;
//
//    public Gauss(String[][] matrix, int size) {
//        this.matrix = matrix;
//        this.size = size;
//    }
//
//    public boolean isSolvable() {
////        for (int k = 0; k < size; k++) {
//            int maxRow = k;
//            for (int i = k + 1; i < size; i++) {
//                if (Math.abs(matrix[i][k]) > Math.abs(matrix[maxRow][k])) {
//                    maxRow = i;
//                }
//            }
//            double[] temp = matrix[k];
//            matrix[k] = matrix[maxRow];
//            matrix[maxRow] = temp;
//
//            if (matrix[k][k] == 0.0) {
//                return false;
//            }
//
//            for (int i = k + 1; i < size; i++) {
//                double factor = matrix[i][k] / matrix[k][k];
//                for (int j = k + 1; j < size + 1; j++) {
//                    matrix[i][j] -= factor * matrix[k][j];
//                }
//                matrix[i][k] = 0.0;
//            }
//        }
//
//        return true;
//    }
//
//    public double[] solve() {
//        if (!isSolvable()) {
//            throw new RuntimeException("Система уравнений не разрешима");
//        }
//
//        String[] solution = new String[size];
//
//        for (int i = size - 1; i >= 0; i--) {
//            double sum = 0;
//            for (int j = i + 1; j < size; j++) {
//                sum += matrix[i][j] * solution[j];
//            }
//            solution[i] = (matrix[i][size] - sum) / matrix[i][i];
//        }
//
//        return solution;
//    }
}

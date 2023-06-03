//import java.util.ArrayList;
//import java.util.Arrays;
//
//public class PlateSingleThread {
//    final static int plateLength = 50;
//    static int maxIterTime = 100;
//    static int alpha = 2;
//    static int deltaX = 1;
//    double deltaT = (deltaX * deltaX) / (4 * alpha);
//    double gamma = (alpha * deltaT) / (deltaX * deltaX);
//    double sum = 0;
//    double[][] arr = new double[plateLength][plateLength];
//    double currentValue = 0;
//    double lastValue = 0;
//    ArrayList<Double> arrList = new ArrayList<>();
//
//    public static void main(String[] args) {
//        double start = System.currentTimeMillis();
//        double[][][] u = new double[maxIterTime][plateLength][plateLength];
//        double[][] arr = new double[plateLength][plateLength];
//
//        for (int t = 0; t < maxIterTime; t++) {
//            u[t] = arr;
//            PlateSingleThread plateST = new PlateSingleThread();
//            plateST.calculate(u);
//            if (t == maxIterTime - 1) {
//                plateST.info();
//            }
//        }
//        double end = System.currentTimeMillis();
//        System.out.print("Elapsed Time: " + (end - start) + " milliseconds");
//    }
//
//    public void calculate(double[][][] u) {
//        arr[0][0] = 22.5;               //topL
//        arr[0][plateLength - 1] = 51;                 //topR
//        arr[plateLength - 1][0] = 45;                 //botL
//        arr[plateLength - 1][plateLength - 1] = 73.5;               //botR
//        for (int k = 0; k < maxIterTime - 1; k++) {
//            for (int i = 1; i < plateLength - 1; i++) {
//                deltaX++;
//                for (int j = 1; j < plateLength - 1; j++) {
//                    deltaX++;
//                    for (int row = 0; row < plateLength; row++) {
//                        arr[0][row] = 30;
//                        arr[plateLength - 1][row] = 75;
//                    }
//                    for (int column = 0; column < plateLength; column++) {
//                        arr[column][0] = 15;
//                        arr[column][plateLength - 1] = 72;
//                    }
//
//                    u[k + 1][i][j] = gamma * (u[k][i + 1][j] + u[k][i - 1][j] + u[k][i][j + 1] + u[k][i][j - 1] - 4 * u[k][i][j]) + u[k][i][j];
//                    arr = u[k];
//                }
//            }
//        }
//       // System.out.println(Arrays.deepToString(tD));
//    }
//
//    public void info() {
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[i].length; j++) {
//                sum += arr[i][j];
//                arrList.add(i, (sum / plateLength * plateLength));
//            }
//        }
//        currentValue = arrList.get(plateLength);
//        lastValue = arrList.get(plateLength - 1);
//        currentValue = currentValue / (plateLength * plateLength);
//        lastValue = lastValue / (plateLength * plateLength);
//
//        System.out.println("Type = Single Thread");
//        System.out.println("Size = " + plateLength + " x " + plateLength + " (" + plateLength * plateLength + ")");
//        System.out.println("Average Grid Value = " + sum / (plateLength * plateLength));
//        System.out.println("Total Error= " + Math.abs(lastValue - currentValue));
//    }
//}
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Aria Ali
 *
 * This program takes a 2d array and transforms it based on a heat equation that
 * is approximated by the finite difference method, and returns statistics
 * such as the size of the grid, the average value of the grid, the total error,
 * and if its single-threaded or multi-threaded.
 */
public class PlateMultiThread {
    final static int plateAmount = 500;                                               //Quantity of rows / columns
    public static int iterNum = 400;                                                  //Number of iterations
    static double[][] twoD = new double[plateAmount][plateAmount];                    //Spacial 2d array

    static int alpha = 2;                                                             //Value for finite difference / heat equation (surface area of surface emitting heat)
    static int deltaX = 1;                                                            //Value for finite difference / heat equation (thickness of material conducting heat)
    double deltaT = (deltaX * deltaX) / (4 * alpha);                                  //Formula for difference in temperature between hot and cold regions
    double gamma = (alpha) * ((deltaT) / (deltaX * deltaX));                          //Formula for temperature gradient

    static int flag = 0;                                                              //Value used to determine single-threaded or multi-threaded
    double average = 0;                                                               //Value for avg grid value
    double currentValue = 0;                                                          //Value for current avg grid value
    double lastValue = 0;                                                             //Value for previous avg grid value
    ArrayList<Double> avgList = new ArrayList<>();                                    //Used to store / compare averages


    /**
     * Main method, initializes arrays, single-threaded implementation, and multi-threaded implementation
     * @param args
     */
    public static void main(String[] args) {
        double[][][] spacialIter = new double[iterNum][plateAmount][plateAmount];     //Represents the 2d array and has a 3rd dimension for each iteration
        double[][] spacial = new double[plateAmount][plateAmount];                    //Represents just the 2d array

        ExecutorService threadPool = Executors.newFixedThreadPool(1);         //Initialize 4 threads
        flag++;
        plateRun plateR = new plateRun(spacialIter,spacial, iterNum);                 //New instance of class PlateRun
    //    synchronized (plateR) {                                                       //Syncronizes on plateR
            threadPool.submit(plateR);                                                //Submits threadPool to plateR
    //    }


//        double start = System.currentTimeMillis();                  //Starts timer
//            for (int t = 0; t < iterNum; t++) {                                     //For loop, used for single threaded version
//
//                spacialIter[t] = spacial;
//                PlateMultiThread plateST = new PlateMultiThread();
//                plateST.matrixGenerator(spacialIter);
//                if (t == iterNum - 1) {
//                    plateST.info();
//                }
//            }
//        double end = System.currentTimeMillis();                    //Timer stop
//        System.out.print("Elapsed Time: " + (end - start) + " milliseconds");
    }

    /**
     * matrixGenerator method, generates matrices based
     * on heat equations, takes 3d matrix param
     * @param spacialIter
     */
    public void matrixGenerator(double[][][] spacialIter) {
        for (int k = 0; k < iterNum - 1; k++) {                                  //Triple for loop iteratres through 3d matrix
            for (int i = 1; i < plateAmount - 1; i++) {
                deltaX++;                                                        //deltaX increment for heat equation
                for (int j = 1; j < plateAmount - 1; j++) {
                    twoD[0][i] = 30;                                             //top,   these all maintain the boundaries of each matrix
                    twoD[plateAmount - 1][i] = 75;                               //bottom
                    twoD[j][0] = 15;                                             //left
                    twoD[j][plateAmount - 1] = 72;                               //right
                    twoD[0][0] = 22.5;                                           //topL
                    twoD[0][plateAmount - 1] = 51;                               //topR
                    twoD[plateAmount - 1][0] = 45;                               //botL
                    twoD[plateAmount - 1][plateAmount - 1] = 73.5;               //botR
                    deltaX++;                                                    //deltaX increment for heat equation
                    spacialIter[k + 1][i][j] = gamma *                           //Matrix transformation for heat equation
                            (spacialIter[k][i + 1][j]
                                    + spacialIter[k][i - 1][j]
                                    + spacialIter[k][i][j + 1]
                                    + spacialIter[k][i][j - 1]
                                    - 4 * spacialIter[k][i][j])
                            + spacialIter[k][i][j];
                    twoD = spacialIter[k];                                      //Stores the spacial elements into 2d array
                }
            }
        }
    //    System.out.println(Arrays.deepToString(twoD));
    }

    /**
     * info method outputs information based on the information
     * gathered from the transformation of matrices
     */
    public void info() {
        for (int i = 0; i < twoD.length; i++) {                                 //Double for loop, traverses 2d array
            for (int j = 0; j < twoD[i].length; j++) {
                average += twoD[i][j];                                          //Stores value at index into average
                avgList.add(i, (average / plateAmount * plateAmount));          //Average is stored into arrayList
            }
        }
        currentValue = avgList.get(plateAmount) / (plateAmount * plateAmount);  //Gets current index in avgList and averages it
        lastValue = avgList.get(plateAmount - 1) / (plateAmount * plateAmount); //Gets previous index in avgList and averages it

        if((average / (plateAmount * plateAmount)) > 42){                      //Multi-threaded implementation of println, checks to see is average is within an acceptable range
            System.out.println("Type = Multi Thread");
            System.out.println("Size = " + plateAmount + " x " + plateAmount + " (" + plateAmount * plateAmount + ")");
            System.out.println("Average Grid Value = " + average / (plateAmount * plateAmount));
            System.out.println("Total Error= " + Math.abs(lastValue - currentValue));
            Thread.currentThread().getThreadGroup().interrupt();
            return;
        }

        if(flag==0){                                                         //Single-threaded implementation of println, checks flag variable
            System.out.println("Type = Single Thread");
            System.out.println("Size = " + plateAmount + " x " + plateAmount + " (" + plateAmount * plateAmount + ")");
            System.out.println("Average Grid Value = " + average / (plateAmount * plateAmount));
            System.out.println("Total Error= " + Math.abs(lastValue - currentValue));
            return;
        }
        else{
            System.out.println("Type = Multi Thread");                      //Edge case for if there arent enough iterations to each an acceptable average
            System.out.println("Size = " + plateAmount + " x " + plateAmount + " (" + plateAmount * plateAmount + ")");
            System.out.println("Average Grid Value = " + average / (plateAmount * plateAmount));
            System.out.println("Total Error= " + Math.abs(lastValue - currentValue));
            return;
        }
    }
}

/**
 * plateRun record, takes 3d array, 2d array, and double value
 * 3d array represents 2 spacial and 1 temporal, 2d array
 * represents 2 spacial, double value represents iteration number.
 * Used for multi-threaded implementation
 */
record plateRun(double[][][] spacialIter, double[][] twoD, double iterNum) implements Runnable {
    /**
     * run method for threads
     */
    @Override
    public void run() {
                double start = System.currentTimeMillis();                  //Starts timer
        for (int t = 0; t < iterNum; t++) {                     //For loop, used for each iteration
            spacialIter[t] = twoD;                              //Assigns spacial values from 3d array to 2d array
            PlateMultiThread plateMT = new PlateMultiThread();  //New instance of PlateMultiThread class
            plateMT.matrixGenerator(spacialIter);               //plateMT instance of matrixGenerator, using spacialIter as parameters
            if (t == iterNum - 1) {                                 //If on the last iteration, call info method for final calculations
                plateMT.info();
            }
        }
        double end = System.currentTimeMillis();                    //Timer stop
        System.out.print("Elapsed Time: " + (end - start) + " milliseconds");
    }
}
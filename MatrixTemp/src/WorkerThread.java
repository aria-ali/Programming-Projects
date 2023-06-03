public class WorkerThread extends Thread {
    private final int row;
    private final int col;

    private final int[][] A;
    private final int[][] B;
    private final int[][] C;

    /**
     * Instantiates a new worker thread.
     *
     * @param row the row
     * @param col the col
     * @param A   the a
     * @param B   the b
     * @param C   the c
     */

    public WorkerThread(int row, int col, int[][] A, int[][] B, int[][] C) {
        this.row = row;
        this.col = col;
        this.A = A;
        this.B = B;
        this.C = C;
    }
    public void run() {
        C[row][col] = (A[row][0] * B[0][col]) + (A[row][1] * B[1][col]);
    }
}
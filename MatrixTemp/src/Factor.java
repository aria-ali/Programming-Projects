//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//
//public class Factor {
//    public static void main(String[] args) {
//        double start = System.currentTimeMillis();
//        int[] range = new int[100000];
//        ExecutorService executor = Executors.newFixedThreadPool(2);
//        for (int i = 0; i < range.length; i++) {
//            range[i] = i + 1;
//            int ranged = range[i];
//            executor.execute(new factorRun(ranged));
//        }
//        executor.shutdown();
//        try {
//            if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
//                executor.shutdownNow();
//            }
//        } catch (InterruptedException e) {
//            executor.shutdownNow();
//        }
//        double end = System.currentTimeMillis();
//        System.out.print("Elapsed Time: " + (end - start) + " milliseconds");
//    }
//}
//
//record factorRun(int num) implements Runnable {
//    @Override
//    public void run() {
//        for (int i = 2; i <= num / 2; i++) {
//            if (num % i == 0) {
//                return;
//            }
//        }
//        System.out.println(num);
//    }
//}
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Lesson4
 */
public class test {

    public static void main(String[] args) {
        Queue<Integer> queuex = new LinkedList<Integer>();
        Queue<Integer> queuey = new LinkedList<Integer>();
        // ArrayList<Pair<Integer,Integer>> queue = new ArrayList<>();
        // queue.add(new Pair<Integer,Integer>(x,y));
        // Pair<Integer,Integer> cur = queue.remove(queue.size() - 1);

        int n = 10; // количество строк
        int m = 10; // количество столбцов
        int[][] arr = new int[n][m];
        int startpoint = 1;
        int[] blocks = new int[] { 21, 30, 39, 48, 22, 23, 24 }; // Номера ячеек по порядку

        int[][] newarea = arrOneByOneFilling(arr, n, m);
        print(arr, n, m);
        arr = arreapreparatio(newarea, n, m);
        System.out.println();

        print(arr, n, m);
        System.out.println();

        int[][] arrWithBlocks = blocksprep(arr, n, m, blocks);
        print(arrWithBlocks, n, m);
        System.out.println();

        int[][] newarrWithBlocks = blockspreptozero(arrWithBlocks, n, m);
        print(newarrWithBlocks, n, m);
        System.out.println();
        // newarrWithBlocks[5][2] = startpoint;

        int i = 5;
        int j = 2;
        newarrWithBlocks[i][j] = startpoint;

        // queue.add(newarrWithBlocks[5][2]);
        queuey.add(i);
        queuex.add(j);
        print(newarrWithBlocks, n, m);
        System.out.println();
        System.out.println(queuey);
        System.out.println(queuex);

        while (queuex.size() != 0) {
            i = queuey.element();
            j = queuex.element();
            if (newarrWithBlocks[i - 1][j] == 0) {
                newarrWithBlocks[i - 1][j] = newarrWithBlocks[i][j] + 1;
                queuey.add(i-1);
                queuex.add(j);
                System.out.println(i);
                System.out.println(j);
                System.out.println(newarrWithBlocks[i][j]);
                
                // System.out.println(queuey);
                // System.out.println(queuex);
            }
            if (newarrWithBlocks[i][j + 1] ==0) {
                newarrWithBlocks[i][j + 1] = newarrWithBlocks[i][j] + 1;
                queuey.add(i);
                queuex.add(j+1);
            }
            if (newarrWithBlocks[i + 1][j] ==0) {
                newarrWithBlocks[i + 1][j] = newarrWithBlocks[i][j] + 1;
                queuey.add(i+1);
                queuex.add(j);
            }
            if (newarrWithBlocks[i][j - 1] ==0) {
                newarrWithBlocks[i][j - 1] = newarrWithBlocks[i][j] + 1;
                queuey.add(i);
                queuex.add(j-1);
            }
            queuex.remove();
            queuey.remove();
            System.out.println(queuey);
            System.out.println(queuex);
            // System.out.println(queuex.element());
            // System.out.println(queuey.element());

            // System.out.println(newarrWithBlocks[i][j]);
            // queue.remove();

            // print(newarrWithBlocks, n, m);
            // System.out.println(queue);
        }
        print(newarrWithBlocks, n, m);
        System.out.println(queuey);
        System.out.println(queuex);
        // while (queuex.size() < 10 ){
        // if (newarrWithBlocks[i-1][j] != -1 ){
        // newarrWithBlocks[i-1][j] = newarrWithBlocks[i][j]+1;
        // queue.add(newarrWithBlocks[i-1][j]);
        // }
        // if (newarrWithBlocks[i][j+1] != -1 ){
        // newarrWithBlocks[i][j+1] = newarrWithBlocks[i][j]+1;
        // queue.add(newarrWithBlocks[i][j+1]);
        // }
        // if (newarrWithBlocks[i+1][j] != -1 ){
        // newarrWithBlocks[i+1][j] = newarrWithBlocks[i][j]+1;
        // queue.add(newarrWithBlocks[i+1][j]);
        // }
        // if (newarrWithBlocks[i][j-1] != -1 ){
        // newarrWithBlocks[i][j-1] = newarrWithBlocks[i][j]+1;
        // queue.add(newarrWithBlocks[i][j-1]);
        // }
        // // i = i-1;
        // // i = i+1;
        // // j = j+1;
        // System.out.println(newarrWithBlocks[i][j]);
        // // queue.remove();

        // // print(newarrWithBlocks, n, m);
        // // System.out.println(queue);
        // }print(newarrWithBlocks, n, m);
        // System.out.println(queue);

    }

    public static void print(int arr[][], int n, int m) {
        for (int i = 1; i < n; i++) {
            System.out.println();
            for (int j = 1; j < m; j++) {
                System.out.print(arr[i][j] + "\t");
            }

        }
    }

    public static int[][] arreapreparatio(int arr[][], int n, int m) {
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (i == 1 || i == n - 1 || j == 1 || j == m - 1) {
                    arr[i][j] = -1;
                }
            }
        }
        return arr;
    }

    public static int[][] blocksprep(int arr[][], int n, int m, int arr2[]) {
        int count = 0;
        while (count < arr2.length) {
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    if (arr[i][j] == arr2[count]) {
                        arr[i][j] = -1;
                    }

                }
            }
            count += 1;
        }
        return arr;
    }

    public static int[][] blockspreptozero(int arr[][], int n, int m) {
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (arr[i][j] != -1) {
                    arr[i][j] = 0;
                }

            }
        }
        return arr;
    }

    public static int[][] arrOneByOneFilling(int arr[][], int n, int m) {
        int num = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                num += 1;
                arr[i][j] = num;
            }
        }
        return arr;
    }

    // public static int[][] algorithm(int arr[][], int n, int m,) {
    // for (int i = 1; i < n; i++) {
    // for (int j = 1; j < m; j++) {
    // if (arr[i-1][j] != -1){
    // queue.add = arr[i-1][j];
    // }
    // }
    // }
    // return arr;
    // }
}
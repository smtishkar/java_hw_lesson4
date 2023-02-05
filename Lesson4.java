import java.util.LinkedList;
import java.util.Queue;

/**
 * Lesson4
 */
public class Lesson4 {

    public static void main(String[] args) {
        // Queue<Integer> queue = new LinkedList<Integer>();
        
        int n = 10; // количество строк
        int m = 10; // количество столбцов
        int[][] arr = new int[n][m];
        // int startpoint = arr [3][3] = 1;
        arr = arreapreparatio(arr, n, m);
        print(arr, n, m);

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
                if (i == 1 || i == n-1 || j == 1 || j == m-1){
                    arr[i][j] = -1;
                }
            }
        }
        return arr;
    }

}
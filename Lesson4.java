import java.util.LinkedList;
import java.util.Queue;


/**
 * Lesson4
 */
public class Lesson4 {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<Integer>();
        
        int n = 10; // количество строк
        int m = 10; // количество столбцов
        int[][] arr = new int[n][m];
        int startpoint = arr [3][3] = 1;
        int[] blocks = new int[] {21, 30, 39, 48}; // 19, 35, 28, 30 // Номера ячеек по порядку


        int[][] newarea = arrOneByOneFilling(arr, n, m);
        print(arr, n, m);
        arr = arreapreparatio(newarea, n, m);
        System.out.println();

        print(arr, n, m);
        System.out.println();

        int [][] arrWithBlocks = blocksprep(arr,n,m,blocks);
        print(arrWithBlocks, n, m);
        System.out.println();
        int [][] newarrWithBlocks = blockspreptozero(arrWithBlocks,n,m);
        print(newarrWithBlocks, n, m);

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

}
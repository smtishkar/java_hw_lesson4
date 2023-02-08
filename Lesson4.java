import java.util.LinkedList;
import java.util.Queue;

/**
 * Реализация волнового алгоритма (алгоритм Ли)
 */
public class Lesson4 {

    public static void main(String[] args) {
        Queue<Integer> queuex = new LinkedList<Integer>();
        Queue<Integer> queuey = new LinkedList<Integer>();
        int n = 20; // количество строк
        int m = 10; // количество столбцов
        int[][] arr = new int[n][m];
        int startpoint = 1;
        int endpoint = 99;
        int[] blocks = new int[] { 21, 30, 39, 48, 57,
                66, 75, 84, 85, 94, 103,
                104, 113, 122, 22, 23, 24 }; // Номера ячеек по порядку где будут блоки
        int i = 5; // Это номер строки где будет находиться входная точка
        int j = 2; // Это номер столбца где будет находиться входная точка
        int o = 14; // Это номер строки где будет находиться выходная точка
        int p = 7; // Это номер столбца где будет находиться выходная точка

        int[][] newarea = arrOneByOneFilling(arr, n, m);
        print(arr, n, m);
        // arr = arreapreparation(newarea, n, m);
        // System.out.println();

        // print(arr, n, m);
        // System.out.println();

        // int[][] arrWithBlocks = blocksprep(arr, n, m, blocks);
        // print(arrWithBlocks, n, m);
        // System.out.println();

        // int[][] newarrWithBlocks = blockspreptozero(arrWithBlocks, n, m);
        // print(newarrWithBlocks, n, m);
        // System.out.println();
        // int[][] findway = algorithLI(newarrWithBlocks, i, j, o, p, queuex, queuey, startpoint);
        // print(findway, n, m);

        // System.out.println();
        // int[][] showarr = showshortestway(findway, i, j, o, p, queuex, queuey, startpoint, endpoint);
        // print(showarr, n, m);

    }

    public static int[][] arrOneByOneFilling(int arr[][], int n, int m) { // Метод заполняет массива цифрами по порядку,
                                                                          // чтобы потом можно было указать где будут
                                                                          // стоять блоки
        int num = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[0].length; j++) {
                num += 1;
                arr[i][j] = num;
            }
        }
        return arr;
    }

    public static void print(int arr[][], int n, int m) { // Метода который распечатывает массив
        for (int i = 1; i < n; i++) {
            System.out.println();
            for (int j = 1; j < m; j++) {
                System.out.print(arr[i][j] + "\t");
            }

        }
    }

    public static int[][] arreapreparation(int arr[][], int n, int m) { // Метод который ограничевает поле и ставит по
                                                                       // периметру -1
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (i == 1 || i == n - 1 || j == 1 || j == m - 1) {
                    arr[i][j] = -1;
                }
            }
        }
        return arr;
    }

    public static int[][] blocksprep(int arr[][], int n, int m, int arr2[]) { // Метод устанавливает блоки
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

    public static int[][] blockspreptozero(int arr[][], int n, int m) { // Метод после установки блоков заменяет все
                                                                        // числа на 0, кроме -1, чтобы начать волновой
                                                                        // алгоритм
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (arr[i][j] != -1) {
                    arr[i][j] = 0;
                }

            }
        }
        return arr;
    }

    public static int[][] algorithLI(int arr[][], int i, int j, // метод находит кратчайший путь до выхода
            int o, int p, Queue<Integer> queuex,
            Queue<Integer> queuey, int startpoint) {
        arr[i][j] = startpoint;
        queuey.add(i);
        queuex.add(j);

        while (i != o || j != p) {
            i = queuey.element();
            j = queuex.element();

            if (arr[i - 1][j] == 0) {
                arr[i - 1][j] = arr[i][j] + 1;
                queuey.add(i - 1);
                queuex.add(j);
            }
            if (arr[i][j + 1] == 0) {
                arr[i][j + 1] = arr[i][j] + 1;
                queuey.add(i);
                queuex.add(j + 1);
            }
            if (arr[i + 1][j] == 0) {
                arr[i + 1][j] = arr[i][j] + 1;
                queuey.add(i + 1);
                queuex.add(j);
            }
            if (arr[i][j - 1] == 0) {
                arr[i][j - 1] = arr[i][j] + 1;
                queuey.add(i);
                queuex.add(j - 1);
            }
            queuex.remove();
            queuey.remove();
        }
        return arr;
    }

    public static int[][] showshortestway(int arr[][], int i, int j, // метод закрашивает кратчайший путь до выхода
                                                                     // цифрами 99
            int o, int p, Queue<Integer> queuex,
            Queue<Integer> queuey, int startpoint, int endpoint) {
        i = o;
        j = p;
        queuex.clear();
        queuey.clear();
        queuey.add(i);
        queuex.add(j);
        System.out.println();

        while (queuex.size() > 0) {

            i = queuey.element();
            j = queuex.element();

            if (arr[i][j - 1] == arr[i][j] - 1) {
                arr[i][j - 1] = arr[i][j] - 1;
                queuey.add(i);
                queuex.add(j - 1);
                arr[i][j] = 99;

            }
            if (arr[i + 1][j] == arr[i][j] - 1) {
                arr[i + 1][j] = arr[i][j] - 1;
                queuey.add(i + 1);
                queuex.add(j);
                arr[i][j] = 99;

            }
            if (arr[i][j + 1] == arr[i][j] - 1) {
                arr[i][j + 1] = arr[i][j] - 1;
                queuey.add(i);
                queuex.add(j + 1);
                arr[i][j] = 99;

            }
            if (arr[i - 1][j] == arr[i][j] - 1) {
                arr[i - 1][j] = arr[i][j] - 1;
                queuey.add(i - 1);
                queuex.add(j);
                arr[i][j] = 99;

            }
            queuex.remove();
            queuey.remove();

        }
        arr[o][p] = endpoint;
        arr[i][j] = 99;

        return arr;

    }
}
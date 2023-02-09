import java.util.LinkedList;
import java.util.Queue;

/**
 * Реализация волнового алгоритма (алгоритм Ли)
 */
public class test {

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
        int enterY = 5; // Это номер строки где будет находиться входная точка
        int enterX = 2; // Это номер столбца где будет находиться входная точка
        int exit1Y = 5; // Это номер строки где будет находиться выходная точка
        int exit1X = 7; // Это номер столбца где будет находиться выходная точка
        int exit2Y = 8;
        int exit2X = 4;

        int[][] fillednynumarr = arrOneByOneFilling(arr);
        print(fillednynumarr);
        System.out.println();

        int[][] fencedarea = arreapreparation(fillednynumarr);
        // print(fencedarea);
        System.out.println();

        int[][] arreaWithBlocks = blocksprep(fencedarea, blocks);
        // print(arreaWithBlocks);
        System.out.println();

        int[][] preparedarrWithBlocks = blockspreptozero(arreaWithBlocks);
        print(preparedarrWithBlocks);
        System.out.println();

        int[][] findway = algorithLI(preparedarrWithBlocks, enterY, enterX, exit1Y, exit1X, exit2Y, exit2X, queuex,
                queuey, startpoint);
        print(findway);
        System.out.println();

        int[][] showarr = showshortestway(findway, enterY, enterX, exit1Y, exit1X, exit2Y, exit2X, queuex, queuey,
                startpoint, endpoint);
        print(showarr);
        System.out.println();

        print(onlyshortestway(showarr));

    }

    /**
     * // Метод заполняет массива цифрами по порядку, чтобы удобней было указать где
     * будут стоять блоки.
     * 
     * @param arr - двумерный массив
     * @return
     */
    public static int[][] arrOneByOneFilling(int arr[][]) {
        int num = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[0].length; j++) {
                num += 1;
                arr[i][j] = num;
            }
        }
        return arr;
    }

    /**
     * Метода который распечатывает массив
     * 
     * @param arr - двумерный массив
     */
    public static void print(int arr[][]) {
        for (int i = 1; i < arr.length; i++) {
            System.out.println();
            for (int j = 1; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }

        }
    }

    /**
     * Метод который ограничевает поле и ставит по периметру -1
     * 
     * @param arr - двумерный массив
     * @return
     */
    public static int[][] arreapreparation(int arr[][]) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[0].length; j++) {
                if (i == 1 || i == arr.length - 1 || j == 1 || j == arr[0].length - 1) {
                    arr[i][j] = -1;
                }
            }
        }
        return arr;
    }

    /**
     * Метод устанавливает блоки на поле
     * 
     * @param arr  - двумерный массив - поле
     * @param arr2 - массив где мы заранее "руками" указываем положение блоков
     * @return
     */
    public static int[][] blocksprep(int arr[][], int arr2[]) {
        int count = 0;
        while (count < arr2.length) {
            for (int i = 1; i < arr.length; i++) {
                for (int j = 1; j < arr[0].length; j++) {
                    if (arr[i][j] == arr2[count]) {
                        arr[i][j] = -1;
                    }

                }
            }
            count += 1;
        }
        return arr;
    }

    /**
     * Метод после установки блоков заменяет все числа на 0, кроме -1, чтобы начать
     * волновой алгоритм
     * 
     * @param arr - двумерный массив
     * @return
     */
    public static int[][] blockspreptozero(int arr[][]) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[0].length; j++) {
                if (arr[i][j] != -1) {
                    arr[i][j] = 0;
                }

            }
        }
        return arr;
    }

    /**
     * Метод находит кратчайший путь до одной из двух точек выхода. Метод
     * останавливается когда кратчайший путь найден
     * 
     * @param arr        - двумерный массив
     * @param enterY     - координата y, входа
     * @param enterX     - координата x, входа
     * @param exit1Y     - координата y, первого выхода
     * @param exit1X     - координата x, первого выхода
     * @param exit2Y     - координата y, второго выхода
     * @param exit2X     - координата х, второго выхода
     * @param queuex     - очередь оси х
     * @param queuey     - - очередь оси y
     * @param startpoint - обозначение входа в массиве. По умолчанию это 1
     * @return
     */
    public static int[][] algorithLI(int arr[][], int enterY, int enterX,
            int exit1Y, int exit1X, int exit2Y, int exit2X, Queue<Integer> queuex,
            Queue<Integer> queuey, int startpoint) {
        arr[enterY][enterX] = startpoint;
        queuey.add(enterY);
        queuex.add(enterX);

        while ((enterY != exit1Y || enterX != exit1X) && (enterY != exit2Y || enterX != exit2X)) {
            enterY = queuey.element();
            enterX = queuex.element();

            if (arr[enterY - 1][enterX] == 0) {
                arr[enterY - 1][enterX] = arr[enterY][enterX] + 1;
                queuey.add(enterY - 1);
                queuex.add(enterX);
            }
            if (arr[enterY][enterX + 1] == 0) {
                arr[enterY][enterX + 1] = arr[enterY][enterX] + 1;
                queuey.add(enterY);
                queuex.add(enterX + 1);
            }
            if (arr[enterY + 1][enterX] == 0) {
                arr[enterY + 1][enterX] = arr[enterY][enterX] + 1;
                queuey.add(enterY + 1);
                queuex.add(enterX);
            }
            if (arr[enterY][enterX - 1] == 0) {
                arr[enterY][enterX - 1] = arr[enterY][enterX] + 1;
                queuey.add(enterY);
                queuex.add(enterX - 1);
            }
            queuex.remove();
            queuey.remove();
        }
        return arr;
    }

    /**
     * Метод показывает 2е точки выхода, обозначая их цифрой 99 и закрашивает путь
     * до ближайшего выхода. Закраска происходит цифрами 99
     * 
     * @param arr        - двумерный массив
     * @param enterY     - координата y, входа
     * @param enterX     - координата x, входа
     * @param exit1Y     - координата y, первого выхода
     * @param exit1X     - координата x, первого выхода
     * @param exit2Y     - координата y, второго выхода
     * @param exit2X     - координата х, второго выхода
     * @param queuex     - очередь оси х
     * @param queuey     - - очередь оси y
     * @param startpoint - обозначение входа в массиве. По умолчанию это 1
     * @param endpoint   - обозначение входа в массиве. По умолчанию это 99.
     * @return
     */
    public static int[][] showshortestway(int arr[][], int enterY, int enterX,
            int exit1Y, int exit1X, int exit2Y, int exit2X, Queue<Integer> queuex,
            Queue<Integer> queuey, int startpoint, int endpoint) {

        if (arr[exit1Y][exit1X] == 0) {
            enterY = exit2Y;
            enterX = exit2X;
        } else {
            enterY = exit1Y;
            enterX = exit1X;
        }

        queuex.clear();
        queuey.clear();
        queuey.add(enterY);
        queuex.add(enterX);
        System.out.println();

        while (queuex.size() > 0) {

            enterY = queuey.element();
            enterX = queuex.element();

            if (arr[enterY][enterX - 1] == arr[enterY][enterX] - 1) {
                arr[enterY][enterX - 1] = arr[enterY][enterX] - 1;
                queuey.add(enterY);
                queuex.add(enterX - 1);
                arr[enterY][enterX] = 99;

            }
            if (arr[enterY + 1][enterX] == arr[enterY][enterX] - 1) {
                arr[enterY + 1][enterX] = arr[enterY][enterX] - 1;
                queuey.add(enterY + 1);
                queuex.add(enterX);
                arr[enterY][enterX] = 99;

            }
            if (arr[enterY][enterX + 1] == arr[enterY][enterX] - 1) {
                arr[enterY][enterX + 1] = arr[enterY][enterX] - 1;
                queuey.add(enterY);
                queuex.add(enterX + 1);
                arr[enterY][enterX] = 99;

            }
            if (arr[enterY - 1][enterX] == arr[enterY][enterX] - 1) {
                arr[enterY - 1][enterX] = arr[enterY][enterX] - 1;
                queuey.add(enterY - 1);
                queuex.add(enterX);
                arr[enterY][enterX] = 99;

            }
            queuex.remove();
            queuey.remove();

        }
        arr[exit1Y][exit1X] = endpoint;
        arr[exit2Y][exit2X] = endpoint;
        arr[enterY][enterX] = 99;

        return arr;

    }

    /**
     * Метод убирает все числа и оставляет только путь
     * 
     * @param arr - принимает двумерный массив
     * @return - возвращает двумерный массив
     */
    public static int[][] onlyshortestway(int arr[][]) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[0].length; j++) {
                if (arr[i][j] != 99 && arr[i][j] != -1) {
                    arr[i][j] = 0;
                }

            }
        }
        return arr;
    }
}
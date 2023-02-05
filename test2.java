public class test2 {
    public void findPath(int x,int y) {
        ArrayList<Pair<Integer,Integer>> queue = new ArrayList<>();
        queue.add(new Pair<Integer,Integer>(x,y));
        mas[x][y] = 1;
        while (queue.size() > 0)  {
            Pair<Integer,Integer> cur = queue.remove(queue.size() - 1);
            int x = cur.x;
            int y = cur.y;
    //
            if (x < width - 1 && mas[x + 1][y] == 0) {
                    queue.add(new Pair<Integer,Integer>(x+1, y);
                    mas[x+1][y] = 1;
            }
            if (x > 0 && mas[x - 1][y] == 0) {
                    queue.add(new Pair<Integer,Integer>(x-1,y);
                    mas[x-1][y] = 1;
            }
            if (y < height - 1 && mas[x][y+1] == 0) {
                    queue.add(new Pair<Integer,Integer>(x, y + 1);
                    mas[x][y+1] = 1;
            }
            if (y > 0 && mas[x][y-1] == 0) {
                    queue.add(new Pair<Integer,Integer>(x, y - 1);
                    mas[x][y-1] = 1;
            }
    //
        }
    }
}

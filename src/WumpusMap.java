import java.awt.Point;

public class WumpusMap
{
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;
    public static final int NUM_PITS = 10;
    private WumpusSquare[][] map;
    private int ladderC;
    private int ladderR;
    private static DS8_Stack<Point> stack;
    private static boolean[][] visited;
    private static char[][] maze;

    public WumpusMap()
    {
        createMap();
    }
    public void createMap()
    {
        map = new WumpusSquare[NUM_ROWS][NUM_COLS];
        for (int i = 0; i < NUM_ROWS; i++)
        {
            for (int j = 0; j < NUM_COLS; j++)
            {
                map[i][j] = new WumpusSquare();
            }
        }
        int wumpusC = (int)(Math.random() * 10);
        int wumpusR = (int)(Math.random() * 10);
        map[wumpusR][wumpusC].setWumpus(true);
        if (wumpusR > 0)
        {
            map[wumpusR - 1][wumpusC].setStench(true);
        }
        if (wumpusR < 10 - 1)
        {
            map[wumpusR + 1][wumpusC].setStench(true);
        }
        if (wumpusC > 0)
        {
            map[wumpusR][wumpusC - 1].setStench(true);
        }
        if (wumpusC < 10 - 1)
        {
            map[wumpusR][wumpusC + 1].setStench(true);
        }
        int goldC = (int)(Math.random() * 10);
        int goldR = (int)(Math.random() * 10);
        map[goldR][goldC].setGold(true);
        while (true)
        {
            ladderC = (int)(Math.random() * 10);
            ladderR = (int)(Math.random() * 10);
            if (!map[ladderR][ladderC].getGold() && !map[ladderR][ladderC].getWumpus())
            {
                map[ladderR][ladderC].setLadder(true);
                break;
            }
        }
        int numPits = 0;
        while (numPits < NUM_PITS)
        {
            int r = (int)(Math.random() * 10);
            int c = (int)(Math.random() * 10);
            if (!map[r][c].getPit() && !map[r][c].getLadder() && !map[r][c].getGold() && !map[r][c].getWumpus())
            {
                map[r][c].setPit(true);
                numPits++;
                addBreeze(r, c);
            }
        }
    }
    public boolean isSolvable(WumpusSquare[][] map)
    {
        DS8_Stack<Point> stack = new DS8_Stack<Point>();
        boolean[][] visited = new boolean[10][10];
        char [][] maze = new char[10][10];
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (map[i][j].getPit())
                {
                    maze[i][j] = 'X';
                }
                else if (map[i][j].getGold())
                {
                    maze[i][j] = 'G';
                }
                else if (map[i][j].getLadder())
                {
                    stack.push(new Point(i, j));
                    maze[i][j] = 'L';
                    visited[i][j] = true;
                }
                else
                {
                    maze[i][j] = ' ';
                }
            }
        }
        while(!stack.isEmpty())
        {
            Point location = stack.pop();
            visited[location.x][location.y] = true;
            if (maze[location.x][location.y] == 'L')
            {
                return true;
            }
            addToStack(maze, new Point(location.x + 1, location.y));
            addToStack(maze, new Point(location.x - 1, location.y));
            addToStack(maze, new Point(location.x, location.y + 1));
            addToStack(maze, new Point(location.x, location.y - 1));
        }
        return false;
    }
    public static void addToStack(char[][] maze, Point cur)
    {
        try
        {
            if (maze[cur.x][cur.y] != 'X' && !visited[cur.x][cur.y])
            {
                stack.push(cur);
                System.out.println("Pushing: " + cur.x + ", " + cur.y);
                visited[cur.x][cur.y] = true;
            }
        }
        catch (Exception e)
        {
            return;
        }
    }
    public WumpusSquare getSquare(int row, int col)
    {
        return map[row][col];
    }
    public int getLadderC()
    {
        return ladderC;
    }
    public int getLadderR()
    {
        return ladderR;
    }
    public void addBreeze(int row, int col)
    {
        if (row > 0)
        {
            map[row - 1][col].setBreeze(true);
        }
        if (row < 10 - 1)
        {
            map[row + 1][col].setBreeze(true);
        }
        if (col > 0)
        {
            map[row][col - 1].setBreeze(true);
        }
        if (col < 10 - 1)
        {
            map[row][col + 1].setBreeze(true);
        }
    }
    public String toString()
    {
        String s = "";
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                s += map[i][j].toString();
            }
            s += "\n";
        }
        return s;
    }
}
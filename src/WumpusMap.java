public class WumpusMap
{
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;
    public static final int NUM_PITS = 10;
    private WumpusSquare[][] map;
    private int ladderC;
    private int ladderR;

    public WumpusMap()
    {
        createMap();
    }
    public void createMap()
    {
        map = new WumpusSquare[10][10];
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                map[i][j] = new WumpusSquare();
            }
        }
        ladderC = (int)(Math.random() * 10);
        ladderR = (int)(Math.random() * 10);
        map[ladderR][ladderC].setLadder(true);
        int numPits = 0;
        while (numPits < NUM_PITS)
        {
            int r = (int)(Math.random() * 10);
            int c = (int)(Math.random() * 10);
            if (!map[r][c].getPit() && !map[r][c].getLadder())
            {
                map[r][c].setPit(true);
                addBreeze(r, c);
                numPits++;
            }
        }
        int wumpusR = (int)(Math.random() * 10);
        int wumpusC = (int)(Math.random() * 10);
        if (!map[wumpusR][wumpusC].getPit() && !map[wumpusR][wumpusC].getLadder())
        {
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
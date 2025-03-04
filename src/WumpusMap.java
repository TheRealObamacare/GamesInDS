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
        map = new WumpusSquare[NUM_ROWS][NUM_COLS];
        for (int i = 0; i < NUM_ROWS; i++)
        {
            for (int j = 0; j < NUM_COLS; j++)
            {
                map[i][j] = new WumpusSquare();
            }
        }
        ladderC = (int)(Math.random() * NUM_COLS);
        ladderR = (int)(Math.random() * NUM_ROWS);
        map[ladderR][ladderC].setLadder(true);
        int numPits = 0;
        while (numPits < NUM_PITS)
        {
            int r = (int)(Math.random() * NUM_ROWS);
            int c = (int)(Math.random() * NUM_COLS);
            if (!map[r][c].getPit() && !map[r][c].getLadder())
            {
                map[r][c].setPit(true);
                addBreeze(r, c);
                numPits++;
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
        if (row < NUM_ROWS - 1)
        {
            map[row + 1][col].setBreeze(true);
        }
        if (col > 0)
        {
            map[row][col - 1].setBreeze(true);
        }
        if (col < NUM_COLS - 1)
        {
            map[row][col + 1].setBreeze(true);
        }
    }
    public String toString()
    {
        String s = "";
        for (int i = 0; i < NUM_ROWS; i++)
        {
            for (int j = 0; j < NUM_COLS; j++)
            {
                s += map[i][j].toString();
            }
            s += "\n";
        }
        return s;
    }
}
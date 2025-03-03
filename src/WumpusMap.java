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
}
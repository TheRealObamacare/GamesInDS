public class WumpusPlayer
{
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    private int direction;
    private boolean arrow;
    private boolean gold;
    private int colPosition;
    private int rowPosition;
    public WumpusPlayer()
    {
        direction = NORTH;
        arrow = true;
        gold = false;
        colPosition = 0;
        rowPosition = 0;
    }

    public int getDirection()
    {
        return direction;
    }
    public int setPlayerDirection(int direction)
    {
        this.direction = direction;
        return direction;
    }
    public boolean getArrow()
    {
        return arrow;
    }
    public boolean setArrow(boolean arrow)
    {
        this.arrow = arrow;
        return arrow;
    }
    public boolean getGold()
    {
        return gold;
    }
    public boolean setGold(boolean gold)
    {
        this.gold = gold;
        return gold;
    }
    public int getColPosition()
    {
        return colPosition;
    }
    public int setColPosition(int colPosition)
    {
        this.colPosition = colPosition;
        return colPosition;
    }
    public int getRowPosition()
    {
        return rowPosition;
    }
    public int setRowPosition(int rowPosition)
    {
        this.rowPosition = rowPosition;
        return rowPosition;
    }
}
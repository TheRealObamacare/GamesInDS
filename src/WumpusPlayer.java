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
    public void setPlayerDirection(int direction)
    {
        this.direction = direction;
    }
    public boolean getArrow()
    {
        return arrow;
    }
    public void setArrow(boolean arrow)
    {
        this.arrow = arrow;
    }
    public boolean getGold()
    {
        return gold;
    }
    public void setGold(boolean gold)
    {
        this.gold = gold;
    }
    public int getColPosition()
    {
        return colPosition;
    }
    public void setColPosition(int colPosition)
    {
        this.colPosition = colPosition;
    }
    public int getRowPosition()
    {
        return rowPosition;
    }
    public void setRowPosition(int rowPosition)
    {
        this.rowPosition = rowPosition;
    }
}
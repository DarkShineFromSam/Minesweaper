package sweeper;

public class Game
{
    private final Bomb bomb;
    private final Flag flag;
    private GameState state;

    public GameState getState()
    {
        return state;
    }

    public Game (int cols, int rows, int bombs)
    {
        Ranges.setSize(new Coords(cols,rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    public void start()

    {
        bomb.start();
        flag.start();
        state = GameState.PLAYED;
    }
    public Box getBox (Coords cord)
    {
        if (flag.get(cord) == Box.OPENED)
            return bomb.get(cord);
        else
            return flag.get(cord);
    }

    public void pressLeftButton(Coords cord)
    {
        if (gameOver()) return;
        openBox(cord);
        checkWinner();
    }

    private void checkWinner ()
    {
        if (state == GameState.PLAYED)
            if (flag.getCountOfClosedBodes () == bomb.getTotalBombs())
                state = GameState.WINNER;
    }

    private void openBox(Coords cord)
    {
        switch (flag.get(cord))
        {
            case OPENED: setOpenedToClosedBoxesAroundNumber(cord); return;
            case FLAGED: return;
            case CLOSED:
                switch (bomb.get(cord)) {
                    case ZERO -> openBoxesAround(cord);
                    case BOMB -> openBombs(cord);
                    default -> flag.setOpenedToBox(cord);
                }
            //default:
        }
    }

    private void openBombs(Coords bombed)
    {
        state = GameState.BOMBED;
        flag.setBombedToBox (bombed);
        for (Coords cord : Ranges.getAllCords())
            if (bomb.get(cord) == Box.BOMB)
                flag.setOpenedToClosedBombBox (cord);
            else
                flag.setNoBombToFlagSafeBox(cord);
    }

    private boolean gameOver()
    {
        if (state == GameState.PLAYED)
            return false;
        start();
        return true;
    }

    private void openBoxesAround(Coords cord)
    {
        flag.setOpenedToBox(cord);
        for (Coords around: Ranges.getCordsAround(cord))
            openBox(around);
    }

    private void setOpenedToClosedBoxesAroundNumber(Coords cord)
    {
        if (bomb.get(cord) != Box.BOMB)
            if (flag.getCountOfFlagBoxesAround(cord) == bomb.get(cord).getNumber())
                for (Coords around: Ranges.getCordsAround(cord))
                    if (flag.get(around) == Box.CLOSED)
                        openBox(around);


    }

    public void pressRightButton(Coords cord)
    {
        if (gameOver()) return;
        flag.toggleFlagToBox(cord);
    }
}

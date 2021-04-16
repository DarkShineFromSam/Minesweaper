package sweeper;

import java.util.ArrayList;
import java.util.Random;

public class Ranges
{
    private static Coords size;
    private static ArrayList <Coords> allCords;
    private static final Random random = new Random();

    public static void setSize (Coords _size)
    {
        size = _size;
        allCords = new ArrayList<>();
        for (int y = 0; y < size.y; y++)
            for (int x = 0; x < size.x; x++)
                allCords.add(new Coords(x,y));
    }

    public static Coords getSize()
    {
        return size;
    }

    public static ArrayList<Coords> getAllCords ()
    {
        return allCords;
    }

    static boolean inRange (Coords cord)
    {
        return cord.x >=0 && cord.x < size.x &&
                cord.y >= 0 && cord.y < size.y;
    }

    static Coords getRandomCord()
    {
        return new Coords(random.nextInt(size.x),
                         random.nextInt(size.y));
    }

    static ArrayList <Coords> getCordsAround(Coords cord)
    {
        Coords around;
        ArrayList<Coords> list = new ArrayList<>();
        for (int x = cord.x - 1; x <= cord.x + 1; x++){
            for (int y = cord.y - 1; y <= cord.y + 1; y++) {
                if (inRange(around = new Coords(x, y)))
                    if (!around.equals(cord))
                        list.add(around);
            }
        }
        return list;
    }
}

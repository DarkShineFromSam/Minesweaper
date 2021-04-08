import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

import javax.swing.*;
import java.awt.*;

public class Javasweeper extends JFrame {

    private Game game;


    private JPanel panel;
    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;
    private final int imagesize = 50;

    public static void main(String[] args)
    {
        new Javasweeper();
    }

    private Javasweeper ()
    {
        game = new Game(COLS,ROWS,BOMBS);
        game.start();
        setImages();
        initPanel();
        initFrame();

    }

    private void initPanel()
    {
        panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                for (Coord coord: Ranges.getAllCords())
                {
                    g.drawImage((Image)game.getBox(coord).image,
                            coord.x * imagesize,coord.y * imagesize,this);
                }
            }
        };
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x*imagesize,
                Ranges.getSize().y*imagesize));
        add(panel);
    }

    private void initFrame ()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Sweeper");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setIconImage(getImage("icon"));
        pack();
    }

    public void setImages()
    {
        for (Box box : Box.values())
            box.image = getImage(box.name().toLowerCase());
    }

    private Image getImage(String name)
    {
        String fileName = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon (getClass().getResource(fileName));
        return icon.getImage();
    }
}

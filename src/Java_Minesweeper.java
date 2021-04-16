import sweeper.Box;
import sweeper.Coords;
import sweeper.Game;
import sweeper.Ranges;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Java_Minesweeper extends JFrame {

    private final Game game;

    private JPanel panel;
    private JLabel label;

    private final int IMAGE_SIZE = 50;

    public static void main(String[] args)
    {
        new Java_Minesweeper();
    }

    private Java_Minesweeper()
    {
        int COLS = 9;
        int ROWS = 9;
        int BOMBS = 10;
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }

    private void initLabel()
    {
        label = new JLabel("Welcome");
        add(label, BorderLayout.SOUTH);
    }

    private void initPanel()
    {
        panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                for (Coords cord : Ranges.getAllCords())
                {
                    g.drawImage((Image) game.getBox(cord).image,
                            cord.x * IMAGE_SIZE, cord.y * IMAGE_SIZE,IMAGE_SIZE, IMAGE_SIZE,this);
                }
            }
        };

        panel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                int x  = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coords cord = new Coords(x,y);
                if (e.getButton() == MouseEvent.BUTTON1)
                    game.pressLeftButton(cord);
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton(cord);
                if (e.getButton() == MouseEvent.BUTTON2)
                    game.start();
                label.setText(getMessage ());
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x* IMAGE_SIZE,
                Ranges.getSize().y* IMAGE_SIZE));
        add(panel);
    }

    private String getMessage()
    {
        return switch (game.getState()) {
            case PLAYED -> "Think twice";
            case BOMBED -> "You loose!";
            case WINNER -> "Congratulations";
        };
    }

    private void initFrame ()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Sweeper");
        setResizable(false);
        setVisible(true);
        pack();
        setIconImage(getImage("icon"));
        setLocationRelativeTo(null);
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

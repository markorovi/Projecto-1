package com.gmail.markorovi24.HUDCards;
import com.gmail.markorovi24.Mediator.MediadorCartas;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.nio.file.Paths;


public class MyCards {
    JLabel Card = new JLabel();
    boolean moving = false;
    MediadorCartas Control;
    boolean isUp;
    boolean acceptable;

    public JLabel getCard(){
        return Card;
    }

    public void setImage(String name) {
        String path = Paths.get("").toAbsolutePath().toString();
        path = path + "\\src\\main\\resources\\images\\";
        Card.setIcon(new ImageIcon(path + name));
    }

    private void animate(JComponent component, Point newPoint, int frames, int interval) {
        Rectangle compBounds = component.getBounds();

        Point oldPoint = new Point(compBounds.x, compBounds.y),
                animFrame = new Point((newPoint.x - oldPoint.x) / frames,
                        (newPoint.y - oldPoint.y) / frames);

        new Timer(interval, new ActionListener() {
            int currentFrame = 0;
            public void actionPerformed(ActionEvent e) {
                component.setBounds(oldPoint.x + (animFrame.x * currentFrame),
                        oldPoint.y + (animFrame.y * currentFrame),
                        compBounds.width,
                        compBounds.height);

                if (currentFrame != frames){
                    currentFrame++;
                } else {
                    ((Timer)e.getSource()).stop();
                    moving = false;
                }
            }
        }).start();
    }


    public void builder(int x, int y, int width, int height, MediadorCartas mediador){
        this.Control = mediador;
        Card.setOpaque(true);
        setImage("Interrogacion.jpg");
        Card.setBounds(x, y, width, height);
        Card.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!Control.getCardsState() && !moving) {
                    moving = true;
                    Control.setCardUp(true);
                    isUp = true;
                    animate(Card, new Point(Card.getX(), Card.getY() - 45), 20, 30);
                } else if (Control.getCardsState() && !moving && isUp) {
                    moving = true;
                    Control.setCardUp(false);
                    isUp = false;
                    animate(Card, new Point(Card.getX(), Card.getY() + 45), 20, 30);
                }
            }



            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

    }
}

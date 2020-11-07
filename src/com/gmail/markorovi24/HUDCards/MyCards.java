package com.gmail.markorovi24.HUDCards;
import com.gmail.markorovi24.Cartas.Cartas;
import com.gmail.markorovi24.Mediator.MediadorCartasHUD;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.nio.file.Paths;

/**
 * Clase que hace referencia a todas los label que funcionan como la mano de cartas del usuario
 *
 * @author Marco Rodríguez
 * @version 1.0
 * @since 0.4.5
 */
public class MyCards {
    private JLabel Card = new JLabel();
    private boolean moving = false;
    private MediadorCartasHUD Control;
    private boolean isUp;

    /**
     * Get para el JLabel que el JFrame necesita para agregarlo a la ventana
     * @return el Jlabel
     */
    public JLabel getCard(){
        return Card;
    }

    /**
     * Get de si la carta esta seleccionada o no
     * @return boolean value
     */
    public boolean getIsUp(){
        return isUp;
    }

    /**
     * Set para la imagen que muestra el JLabel
     * @param name Nombre de la imagen a asignar
     */
    public void setImage(String name) {
        String path = Paths.get("").toAbsolutePath().toString();
        path = path + "\\src\\main\\resources\\images\\";
        Card.setIcon(new ImageIcon(path + name));
    }

    /**
     * Método que se encarga de dar la animación a los JLabels para hacerlos pareces como si fueran seleccionados
     * @param component JLabel a animar
     * @param newPoint Punto al donde llevar el JLabel
     * @param frames Framerate en el que animarlo
     * @param interval Tiempo en el que se realice la animación
     */
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

    /**
     * Método que se encarga de animar solo las cartas con ciertas restricciones.
     */
    public void publicAnimate(){
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

    /**
     * Constructor para cada uno de los JLabels, además de que incluye el detector de eventos para cuando se clickean los mismos, los párametros son auto-explicatorios
     * @param x
     * @param y
     * @param width
     * @param height
     * @param mediador
     */
    public void builder(int x, int y, int width, int height, MediadorCartasHUD mediador){
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
                    isUp = true;
                    Control.setCardUp(true);
                    animate(Card, new Point(Card.getX(), Card.getY() - 45), 20, 30);
                } else if (Control.getCardsState() && !moving && isUp) {
                    moving = true;
                    isUp = false;
                    Control.setCardUp(false);
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

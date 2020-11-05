package com.gmail.markorovi24.GUI;
import com.github.monstertecg.listasEnlazadas.ListaDoble;
import com.gmail.markorovi24.Cartas.Cartas;
import com.gmail.markorovi24.GUI.Widgets.Button;
import com.gmail.markorovi24.GUI.Widgets.Label;
import com.gmail.markorovi24.HUDCards.Deck;
import com.gmail.markorovi24.HUDCards.MyCards;
import com.gmail.markorovi24.HUDCards.RivalCards;
import com.gmail.markorovi24.Mediator.MediadorCartasHUD;
import com.gmail.markorovi24.GUI.Widgets.*;
import com.gmail.markorovi24.Mediator.MediadorGeneradorCartas;
import com.gmail.markorovi24.Mediator.MediadorMyCards;
import com.gmail.markorovi24.Mediator.MediadorVidaMana;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VentanaJuego extends Ventana{
    private final MyCards MyCard1 = new MyCards();
    private final MyCards MyCard2 = new MyCards();
    private final MyCards MyCard3 = new MyCards();
    private final MyCards MyCard4 = new MyCards();
    private final MyCards MyCard5 = new MyCards();
    private final MyCards MyCard6 = new MyCards();
    private final MyCards MyCard7 = new MyCards();
    private final MyCards MyCard8 = new MyCards();
    private final MyCards MyCard9 = new MyCards();
    private final MyCards MyCard10 = new MyCards();

    private final ListaDoble<MyCards> Hand = new ListaDoble<MyCards>();

    private final Deck GameDeck = new Deck();
    private final Deck CardsPlayed = new Deck();

    private final RivalCards RivalCard1 = new RivalCards();
    private final RivalCards RivalCard2 = new RivalCards();
    private final RivalCards RivalCard3 = new RivalCards();
    private final RivalCards RivalCard4 = new RivalCards();
    private final RivalCards RivalCard5 = new RivalCards();

    private final Text ActiveCards = new Text();
    private final Text SelectedCardEffects = new Text();
    private final Text ListCardEffect = new Text();

    private final Label Texto1 = new Label();
    private final Label Texto2 = new Label();
    private final Label Texto3 = new Label();
    private final Label Texto4 = new Label();
    private final Label Texto5 = new Label();
    private final Label Texto6 = new Label();

    private final Button Boton1 = new Button();
    private final Button Boton2 = new Button();
    private final Button Boton3 = new Button();
    private final Button Boton4 = new Button();

    private MediadorCartasHUD ControlCartas;
    private MediadorVidaMana ControlVidaMana;
    private MediadorMyCards ControlDecks;


    public void configurarMenu(MediadorCartasHUD ControlCartasParametro, MediadorVidaMana ControlVidaManaParametro, MediadorMyCards ControlDecksParametro){
        this.ControlCartas = ControlCartasParametro;
        this.ControlVidaMana = ControlVidaManaParametro;
        this.ControlDecks = ControlDecksParametro;

        this.MyCard1.builder(180, 490, 120, 160, this.ControlCartas);
        this.MyCard2.builder(330, 490, 120, 160, this.ControlCartas);
        this.MyCard3.builder(480, 490, 120, 160, this.ControlCartas);
        this.MyCard4.builder(630, 490, 120, 160, this.ControlCartas);
        this.MyCard5.builder(780, 490, 120, 160, this.ControlCartas);
        this.MyCard6.builder(180, 700, 120, 160, this.ControlCartas);
        this.MyCard7.builder(330, 700, 120, 160, this.ControlCartas);
        this.MyCard8.builder(480, 700, 120, 160, this.ControlCartas);
        this.MyCard9.builder(630, 700, 120, 160, this.ControlCartas);
        this. MyCard10.builder(780, 700, 120, 160, this.ControlCartas);

        this.Hand.Agregar(MyCard1);
        this.Hand.Agregar(MyCard2);
        this.Hand.Agregar(MyCard3);
        this.Hand.Agregar(MyCard4);
        this.Hand.Agregar(MyCard5);
        this.Hand.Agregar(MyCard6);
        this.Hand.Agregar(MyCard7);
        this.Hand.Agregar(MyCard8);
        this.Hand.Agregar(MyCard9);
        this.Hand.Agregar(MyCard10);

        this.RivalCard1.builder(180, 30, 120, 160);
        this.RivalCard2.builder(330, 30, 120, 160);
        this.RivalCard3.builder(480, 30, 120, 160);
        this.RivalCard4.builder(630, 30, 120, 160);
        this.RivalCard5.builder(780, 30, 120, 160);

        this.GameDeck.builderWithActions(330, 250, 120, 160, this.ControlDecks);
        this.CardsPlayed.builderWithOutActions(630, 250, 120, 160);

        this.ActiveCards.builder(15, 750, 150, 100, Color.white);

        this.SelectedCardEffects.builder(15, 600, 150, 100, Color.white);

        this.ListCardEffect.builder(925, 600, 150, 100, Color.white);

        this.Texto1.builder(15, 710, 150, 30, Color.white, "Efectos activos");

        this.Texto2.builder(15, 560, 150, 30, Color.white, "Carta Seleccionada");

        this.Texto3.builder(925, 560, 150, 30, Color.white, "Carta del Deck");

        this.Texto4.builder(480, 250, 120, 30, Color.white, "Vida rival: ");
        this.Texto4.setValue(this.ControlVidaMana.getGuestHP());

        this.Texto5.builder(15, 500, 150, 30, Color.white, "Vida: ");
        this.Texto5.setValue(this.ControlVidaMana.getHostHP());

        this.Texto6.builder(925, 500, 150, 30, Color.white, "Maná: ");
        this.Texto6.setValue(this.ControlVidaMana.getHostMana());

        this.Boton1.builder(950, 800, 100,50, "Lanzar", Color.white);
        this.Boton1.getBoton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < ControlDecks.getHandCards(); i++){
                    if (Hand.Obtener(i).getIsUp()){
                        Hand.Obtener(i).publicAnimate();
                        ControlDecks.agregarHistorial(ControlDecks.getHand().Obtener(i));
                        ControlDecks.eliminarHandCard(i);

                        CardsPlayed.setImage(ControlDecks.getHistorial().Obtener(ControlDecks.getContadorHistorial()).getNombre() + ".jpg");
                        ControlDecks.setIndex(ControlDecks.getContadorHistorial());
                        actualizarCartaHistorial();

                        ControlDecks.setContadorHistorial(ControlDecks.getContadorHistorial() + 1);
                        for (int k = 0; k < ControlDecks.getHandCards(); k++){
                            Hand.Obtener(k).setImage(ControlDecks.getHand().Obtener(k).getNombre() + ".jpg");
                        }
                        Hand.Obtener(ControlDecks.getHandCards()).setImage("Interrogacion.jpg");
                        actualizarCartaSeleccionada();
                        break;
                    }
                }
            }
        });

        this.Boton2.builder(630, 412, 50,35, "←", Color.white);
        this.Boton2.getBoton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ControlDecks.getIndex() - 1 <= -1){
                } else {
                    ControlDecks.setIndex(ControlDecks.getIndex() - 1);
                    CardsPlayed.setImage(ControlDecks.getHistorial().Obtener(ControlDecks.getIndex()).getNombre() + ".jpg");
                    actualizarCartaHistorial();
                }
            }
        });

        this.Boton3.builder(700, 412, 50,35, "→", Color.white);
        this.Boton3.getBoton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ControlDecks.getIndex() + 1 >= ControlDecks.getContadorHistorial()){
                } else {
                    ControlDecks.setIndex(ControlDecks.getIndex() + 1);
                    CardsPlayed.setImage(ControlDecks.getHistorial().Obtener(ControlDecks.getIndex()).getNombre() + ".jpg");
                    actualizarCartaHistorial();
                }
            }
        });

        this.Boton4.builder(950, 730, 100,50, "Saltar", Color.white);
        this.Boton4.getBoton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        for (int i = 0; i < 4; i++){
            this.Hand.Obtener(i).setImage(this.ControlDecks.getHand().Obtener(i).getNombre() + ".jpg");
        }

        Ventana.add(this.MyCard1.getCard());
        Ventana.add(this.MyCard2.getCard());
        Ventana.add(this.MyCard3.getCard());
        Ventana.add(this.MyCard4.getCard());
        Ventana.add(this.MyCard5.getCard());
        Ventana.add(this.MyCard6.getCard());
        Ventana.add(this.MyCard7.getCard());
        Ventana.add(this.MyCard8.getCard());
        Ventana.add(this.MyCard9.getCard());
        Ventana.add(this.MyCard10.getCard());
        Ventana.add(this.RivalCard1.getCard());
        Ventana.add(this.RivalCard2.getCard());
        Ventana.add(this.RivalCard3.getCard());
        Ventana.add(this.RivalCard4.getCard());
        Ventana.add(this.RivalCard5.getCard());
        Ventana.add(this.GameDeck.getDeck());
        Ventana.add(this.CardsPlayed.getDeck());
        Ventana.add(this.Boton1.getBoton());
        Ventana.add(this.Boton2.getBoton());
        Ventana.add(this.Boton3.getBoton());
        Ventana.add(this.Boton4.getBoton());
        Ventana.add(this.ActiveCards.getText());
        Ventana.add(this.SelectedCardEffects.getText());
        Ventana.add(this.ListCardEffect.getText());
        Ventana.add(this.Texto1.getLabel());
        Ventana.add(this.Texto2.getLabel());
        Ventana.add(this.Texto3.getLabel());
        Ventana.add(this.Texto4.getLabel());
        Ventana.add(this.Texto5.getLabel());
        Ventana.add(this.Texto6.getLabel());

        crearVentana("test", 1110,910);
        habilitarVentana();
    }

    public void actualizarHand(){
        for (int k = 0; k < ControlDecks.getHandCards(); k++){
            Hand.Obtener(k).setImage(ControlDecks.getHand().Obtener(k).getNombre() + ".jpg");
        }
    }

    public void actualizarCartaSeleccionada(){
        for (int i = 0; i < ControlDecks.getHandCards(); i++){
            if (Hand.Obtener(i).getIsUp()){
                String tipo = ControlDecks.getHand().Obtener(i).getTipo();
                if (tipo.equals("esbirros")) {
                    String nombre = ControlDecks.getHand().Obtener(i).getNombre();;
                    String descripcion = ControlDecks.getHand().Obtener(i).getDescripcion();
                    String frase = ControlDecks.getHand().Obtener(i).getFrase();
                    int dano = ControlDecks.getHand().Obtener(i).getDano();
                    int mana = ControlDecks.getHand().Obtener(i).getMana();
                    SelectedCardEffects.setText(nombre + "\n" + descripcion + "\n" +frase +"\nDaño: " +dano +"\nManá: " + mana);
                    break;
                } else if (tipo.equals("hechizos")){
                    String nombre = ControlDecks.getHand().Obtener(i).getNombre();;
                    String descripcion = ControlDecks.getHand().Obtener(i).getDescripcion();
                    int mana = ControlDecks.getHand().Obtener(i).getMana();

                    SelectedCardEffects.setText(nombre + "\n" +descripcion + "\nManá: " +mana);
                    break;
                } else if (tipo.equals("secretos")){
                    String nombre = ControlDecks.getHand().Obtener(i).getNombre();;
                    String descripcion = ControlDecks.getHand().Obtener(i).getDescripcion();
                    String frase = ControlDecks.getHand().Obtener(i).getFrase();
                    int mana = ControlDecks.getHand().Obtener(i).getMana();

                    SelectedCardEffects.setText(nombre + "\n" +descripcion + "\n" +frase +"\nManá: " + mana);
                    break;
                }
            } else {
                SelectedCardEffects.setText("");
            }
        }
    }

    public void actualizarCartaHistorial(){
        int i = ControlDecks.getIndex();

        String tipo = ControlDecks.getHistorial().Obtener(i).getTipo();
        if (tipo.equals("esbirros")) {
            String nombre = ControlDecks.getHistorial().Obtener(i).getNombre();;
            String descripcion = ControlDecks.getHistorial().Obtener(i).getDescripcion();
            String frase = ControlDecks.getHistorial().Obtener(i).getFrase();
            int dano = ControlDecks.getHistorial().Obtener(i).getDano();
            int mana = ControlDecks.getHistorial().Obtener(i).getMana();
            ListCardEffect.setText(nombre + "\n" + descripcion + "\n" +frase +"\nDaño: " +dano +"\nManá: " + mana);
        } else if (tipo.equals("hechizos")){
            String nombre = ControlDecks.getHistorial().Obtener(i).getNombre();;
            String descripcion = ControlDecks.getHistorial().Obtener(i).getDescripcion();
            int mana = ControlDecks.getHistorial().Obtener(i).getMana();

            ListCardEffect.setText(nombre + "\n" +descripcion + "\nManá: " +mana);
        } else if (tipo.equals("secretos")){
            String nombre = ControlDecks.getHistorial().Obtener(i).getNombre();;
            String descripcion = ControlDecks.getHistorial().Obtener(i).getDescripcion();
            String frase = ControlDecks.getHistorial().Obtener(i).getFrase();
            int mana = ControlDecks.getHistorial().Obtener(i).getMana();

            ListCardEffect.setText(nombre + "\n" +descripcion + "\n" +frase +"\nManá: " + mana);

        }
    }
}

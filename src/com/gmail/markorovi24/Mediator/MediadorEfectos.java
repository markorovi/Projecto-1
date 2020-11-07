package com.gmail.markorovi24.Mediator;

import com.github.monstertecg.Reproductor.Reproductor;
import com.github.monstertecg.json.Json;
import com.github.monstertecg.listasEnlazadas.ListaDoble;
import com.github.monstertecg.sockets.Conectividad;
import com.gmail.markorovi24.Cartas.Esbirros;
import com.gmail.markorovi24.HUDCards.MyCards;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class MediadorEfectos {
    static MediadorEfectos Mediador;
    public static synchronized MediadorEfectos obtenerInstancia(){
        if (Mediador == null){
            Mediador = new MediadorEfectos();
        } return Mediador;
    }

    ListaDoble<Boolean> Efectos = new ListaDoble<>();

    public ListaDoble<Boolean> getEfectos() {
        return Efectos;
    }

    public void setEfectos(ListaDoble<Boolean> efectos) {
        Efectos = efectos;
    }

    public void setEfectosEn(int i, boolean value){
        Efectos.CambiarValor(i, value);
    }

    public void generarLista(){
        for (int i = 0; i < 10; i++){
            Efectos.Agregar(false);
        }
    }

    public void verificarEfectos(){
        for(int i = 0; i < 10; i++) {
            if(Efectos.Obtener(i)){
                Random rand = new Random();
                switch (i){
                    case 0:
                        if (MediadorMyCards.obtenerInstancia().getHandCards() == 0){
                            MediadorMyCards.obtenerInstancia().agregarHand(new Esbirros("10", "La mosca", "Una mosca a estrado al campo de batalla.", "bzzz", 1, 10));
                            MediadorMyCards.obtenerInstancia().setHandCards(MediadorMyCards.obtenerInstancia().getHandCards() + 1);
                        }
                        break;

                    case 1:
                        if (MediadorMyCards.obtenerInstancia().getHandCards() > 7 && MediadorVidaMana.obtenerInstancia().getRivalHP() > 500){
                            setEfectosEn(1, false);
                            Reproductor.ObtenerInstancia().Safaera();
                        }
                        break;

                    case 2:
                        int azar = rand.nextInt(50);
                        if(azar == 0){
                            ListaDoble<MyCards> lista = MediadorCartasHUD.obtenerInstancia().getVentana().obtenerCartas();
                            setEfectosEn(1, false);
                            for(int k = 0; k < 10; k++){
                                lista.Obtener(k).setImage("se cae.jpg");
                            }
                        }
                        break;

                    case 3:
                        int azar2 = rand.nextInt(999);
                        if (azar2 == 0){
                            Conectividad.obtenerInstancia().EnviarMensaje(Json.VarToString("dañar", "20000",MediadorVidaMana.obtenerInstancia().getMyHP(), MediadorVidaMana.obtenerInstancia().getMyMana(),false,"HEADSHOOOOT"));
                            MediadorVidaMana.obtenerInstancia().setRivalHP(MediadorVidaMana.obtenerInstancia().getRivalHP() - 20000);
                        }
                        break;

                    case 4:
                        if(MediadorVidaMana.obtenerInstancia().getMyHP() < 200){
                            setEfectosEn(4, false);
                            int restantes = 10 - MediadorMyCards.obtenerInstancia().getHandCards();
                                for(int j = 0; j < restantes; j++){
                                    MediadorMyCards.obtenerInstancia().agregarHand(new Esbirros("10", "La mosca", "Una mosca a entrado al cambo de batalla", "bzzz", 1, 10));
                                    MediadorMyCards.obtenerInstancia().setHandCards(10);
                                }
                            MediadorCartasHUD.obtenerInstancia().getVentana().actualizarHand();
                        }
                        break;

                    case 5:
                        System.out.println(MediadorMyCards.obtenerInstancia().getContadorHistorial());

                        if (MediadorMyCards.obtenerInstancia().getContadorHistorial() > 0 && MediadorMyCards.obtenerInstancia().getHistorial().Obtener(MediadorMyCards.obtenerInstancia().getContadorHistorial() -1).getNombre().equals("La mosca")){
                            setEfectosEn(5, false);
                            Conectividad.obtenerInstancia().EnviarMensaje(Json.VarToString("dañar", "1",MediadorVidaMana.obtenerInstancia().getMyHP(), MediadorVidaMana.obtenerInstancia().getMyMana(),false,"HEADSHOOOOT"));
                        }
                        break;

                    case 6:
                        if (MediadorVidaMana.obtenerInstancia().getRivalHP() < 0) {
                            setEfectosEn(6, false);
                            Reproductor.ObtenerInstancia().Chayanne();
                        }
                        break;

                    case 7:
                        if (MediadorMyCards.obtenerInstancia().getContadorHistorial() > 0 && MediadorMyCards.obtenerInstancia().getHistorial().Obtener(MediadorMyCards.obtenerInstancia().getContadorHistorial() -1 ).getTipo().equals("hechizos")) {
                            setEfectosEn(7, false);
                            MediadorVidaMana.obtenerInstancia().setMyMana(500);
                            MediadorCartasHUD.obtenerInstancia().getVentana().actualizarMana();
                        }
                        break;

                    case 8:
                        if (MediadorMyCards.obtenerInstancia().getContadorHistorial() > 0 && MediadorMyCards.obtenerInstancia().getHistorial().Obtener(MediadorMyCards.obtenerInstancia().getContadorHistorial() - 1).getTipo().equals("esbirros")) {
                            setEfectosEn(8, false);
                            MediadorVidaMana.obtenerInstancia().setMyMana(1000);
                            MediadorCartasHUD.obtenerInstancia().getVentana().actualizarMana();
                        }
                        break;

                    case 9:
                        if (MediadorVidaMana.obtenerInstancia().getMyMana() < 200) {
                            setEfectosEn(9, false);
                            MediadorVidaMana.obtenerInstancia().setMyMana(800);
                            MediadorCartasHUD.obtenerInstancia().getVentana().actualizarMana();
                        }
                        break;

                }
            }
        }
        MediadorVidaMana.obtenerInstancia().checkWinCondition();
    }

}

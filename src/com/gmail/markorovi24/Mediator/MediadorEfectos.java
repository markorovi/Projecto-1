package com.gmail.markorovi24.Mediator;

import com.github.monstertecg.listasEnlazadas.ListaDoble;

import java.util.Random;

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
            Efectos.Agregar(true);
        }
    }

    public void verificarEfectos(){
        for(int i = 0; i < 10; i++) {
            if(Efectos.Obtener(i)){
                Random rand = new Random();
                switch (i){
                    case 0:
                        if (MediadorMyCards.obtenerInstancia().getHandCards() == 0){

                        }
                        break;

                    case 1:
                        if (MediadorMyCards.obtenerInstancia().getHandCards() > 7 && MediadorVidaMana.obtenerInstancia().getRivalHP() > 500){
                            setEfectosEn(1, false);

                        }
                        break;

                    case 2:
                        int azar = rand.nextInt(50);
                        if(azar == 0){

                        }
                        break;

                    case 3:
                        int azar2 = rand.nextInt(2);
                        if (azar2 == 0){

                        }
                        break;

                    case 4:
                        if(MediadorVidaMana.obtenerInstancia().getMyHP() < 50){
                            setEfectosEn(4, false);

                        }
                        break;

                    case 5:
                        System.out.println(MediadorMyCards.obtenerInstancia().getContadorHistorial());
                        if (MediadorMyCards.obtenerInstancia().getHistorial().Obtener(MediadorMyCards.obtenerInstancia().getContadorHistorial() -1).getNombre().equals("La mosca")){
                            setEfectosEn(5, false);

                        }
                        break;

                    case 6:
                        if (MediadorVidaMana.obtenerInstancia().getRivalHP() < 0) {
                            setEfectosEn(6, false);

                        }
                        break;

                    case 7:
                        if (MediadorMyCards.obtenerInstancia().getHistorial().Obtener(MediadorMyCards.obtenerInstancia().getContadorHistorial() -1 ).getTipo().equals("hechizos")) {
                            setEfectosEn(7, false);

                        }
                        break;

                    case 8:
                        if (MediadorMyCards.obtenerInstancia().getHistorial().Obtener(MediadorMyCards.obtenerInstancia().getContadorHistorial() - 1).getTipo().equals("esbirros")) {
                            setEfectosEn(8, false);

                        }
                        break;

                    case 9:
                        if (MediadorVidaMana.obtenerInstancia().getMyMana() < 100) {
                            setEfectosEn(9, false);

                        }
                        break;

                }
            }
        }
    }

}

package com.gmail.markorovi24.Mediator;

import com.github.monstertecg.listasEnlazadas.ListaDoble;

public class MediadorHechizos {
    static MediadorHechizos Mediador;

    public static synchronized MediadorHechizos obtenerInstancia() {
        if (Mediador == null) {
            Mediador = new MediadorHechizos();
        }
        return Mediador;
    }

    ListaDoble<Boolean> Hechizos = new ListaDoble<>();

    public ListaDoble<Boolean> getHechizos() {
        return Hechizos;
    }

    public void setHechizos(ListaDoble<Boolean> hechizos) {
        Hechizos = hechizos;
    }

    public void setHechizosEn(int i, boolean value) {
        Hechizos.CambiarValor(i, value);
    }

    public void generarLista() {
        for (int i = 0; i < 10; i++) {
            Hechizos.Agregar(false);
        }
    }

    public void verificarHechizos() {
        for (int i = 0; i < 10; i++) {
            if (Hechizos.Obtener(i)) {
                switch (i) {
                    case 0:
                        MediadorServidor.obtenerInstancia().congelado();
                        MediadorServidor.obtenerInstancia().setMyTurn(false);
                        MediadorMyCards.obtenerInstancia().setRemainingCards(0);
                        //MediadorServidor.obtenerInstancia().congelado();
                        System.out.println("Esta vara esta entrando");
                        break;

                    case 1:
                        System.out.println(i);
                        break;

                    case 2:
                        System.out.println(i);
                        break;

                    case 3:
                        System.out.println(i);
                        break;

                    case 4:
                        System.out.println(i);
                        break;

                    case 5:
                        System.out.println(i);
                        break;

                    case 6:
                        System.out.println(i);
                        break;

                    case 7:
                        System.out.println(i);
                        break;

                    case 8:
                        System.out.println(i);
                        break;

                    case 9:
                        System.out.println(i);
                        break;

                }
            }
        }
    }
}



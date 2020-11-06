package com.gmail.markorovi24.Mediator;

import com.github.monstertecg.listasEnlazadas.ListaDoble;

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
                switch (i){
                    case 0:
                        System.out.println(i);
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

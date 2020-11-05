package com.gmail.markorovi24.Mediator;

public class MediadorVidaMana {
    int MyHP = 1000;
    int MyMana = 200;
    int RivalHP = 1000;
    int RivalMana = 200;

    static MediadorVidaMana Mediador;
    public static synchronized MediadorVidaMana obtenerInstancia(){
        if (Mediador == null){
            Mediador = new MediadorVidaMana();
        } return Mediador;
    }

    public int getMyHP() {
        return MyHP;
    }

    public void setMyHP(int myHP) {
        MyHP = myHP;
    }

    public int getMyMana() {
        return MyMana;
    }

    public void setMyMana(int myMana) {
        MyMana = myMana;
        if (MyMana > 1000){
            MyMana = 1000;
        }
    }

    public int getRivalHP() {
        return RivalHP;
    }

    public void setRivalHP(int rivalHP) {
        RivalHP = rivalHP;
    }

    public int getRivalMana() {
        return RivalMana;
    }

    public void setRivalMana(int rivalMana) {
        RivalMana = rivalMana;
    }

    public void checkWinCondition(){
        if (this.RivalHP <= 0){

        } else if (this.MyHP <= 0){

        }
    }

    public void recibirDano(int dano){
        this.MyHP -= dano;
    }

    public void actualizarMana(int mana){
        this.MyMana -= mana;
    }

    public void producirDano(int dano){
        this.RivalHP -= dano;
    }
}

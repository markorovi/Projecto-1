package com.gmail.markorovi24.Mediator;

public class MediadorVidaMana {
    int HostHP = 1000;
    int HostMana= 200;
    int GuestHP = 1000;
    int GuestMana = 200;

    public int getHostHP() {
        return HostHP;
    }

    public void setHostHP(int hostHP) {
        HostHP = hostHP;
    }

    public int getHostMana() {
        return HostMana;
    }

    public void setHostMana(int hostMana) {
        HostMana = hostMana;
    }

    public int getGuestHP() {
        return GuestHP;
    }

    public void setGuestHP(int guestHP) {
        GuestHP = guestHP;
    }

    public int getGuestMana() {
        return GuestMana;
    }

    public void setGuestMana(int guestMana) {
        GuestMana = guestMana;
    }

    public void checkWinCondition(){
        if (this.GuestHP <= 0){

        } else if (this.HostHP <= 0){

        }
    }
}

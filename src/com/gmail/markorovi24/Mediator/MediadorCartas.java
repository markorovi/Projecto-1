package com.gmail.markorovi24.Mediator;

public class MediadorCartas {
    private boolean isACardUp = false;



    public void setCardUp(boolean ACardUp) {
        isACardUp = ACardUp;
    }

    public boolean getCardsState() {
        return isACardUp;
    }

}

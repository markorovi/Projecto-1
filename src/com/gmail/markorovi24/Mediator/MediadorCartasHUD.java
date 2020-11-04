package com.gmail.markorovi24.Mediator;

public class MediadorCartasHUD {
    private boolean isACardUp = false;



    public void setCardUp(boolean ACardUp) {
        isACardUp = ACardUp;
    }

    public boolean getCardsState() {
        return isACardUp;
    }

}

package Thmod.Orbs.ElementOrb.KenjiaOrb;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

import Thmod.Cards.ElementCards.AbstractElementCards;

public abstract class AbstractKenjiaOrb extends AbstractOrb {
    public AbstractElementCards.ElementType elementType;

    public void updateAnimation() {
        super.updateAnimation();
        this.angle += Gdx.graphics.getDeltaTime() * 10.0f;
    }

    public void playChannelSFX()
    {
    }

    public void onEvoke() {
    }
}

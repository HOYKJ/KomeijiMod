package Thmod.Orbs.ElementOrb;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

import Thmod.Cards.ElementCards.AbstractElementCards;

public abstract class AbstractElementOrb extends AbstractOrb {
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

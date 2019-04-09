package Thmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.Iterator;

public class RemovePowerEffect extends AbstractGameEffect {
    private String powerToRemove;
    private AbstractPower powerInstance;
    private static final float DURATION = 0.1F;
    private AbstractCreature target, source;

    public RemovePowerEffect(AbstractCreature target, AbstractCreature source, String powerToRemove) {
        this.target = target;
        this.source = source;
        this.duration = 0.1F;
        this.powerToRemove = powerToRemove;
    }

    public RemovePowerEffect(AbstractCreature target, AbstractCreature source, AbstractPower powerInstance) {
        this.target = target;
        this.source = source;
        this.duration = 0.1F;
        this.powerInstance = powerInstance;
    }

    public void update() {
        if (this.duration == 0.1F) {
            if (this.target.isDeadOrEscaped()) {
                this.isDone = true;
                return;
            }

            AbstractPower removeMe = null;
            if (this.powerToRemove != null) {
                removeMe = this.target.getPower(this.powerToRemove);
            } else if (this.powerInstance != null && this.target.powers.contains(this.powerInstance)) {
                removeMe = this.powerInstance;
            }

            if (removeMe != null) {
                //AbstractDungeon.effectList.add(new PowerExpireTextEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, removeMe.name, removeMe.region128));
                removeMe.onRemove();
                this.target.powers.remove(removeMe);
                AbstractDungeon.onModifyPower();
                Iterator var2 = AbstractDungeon.player.orbs.iterator();

                while(var2.hasNext()) {
                    AbstractOrb o = (AbstractOrb)var2.next();
                    o.updateDescription();
                }
            } else {
                this.duration = 0.0F;
            }
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }
}

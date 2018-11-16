package Thmod.vfx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BlockImpactLineEffect;
import com.megacrit.cardcrawl.vfx.combat.BlockedWordEffect;
import com.megacrit.cardcrawl.vfx.combat.DamageImpactCurvyEffect;
import com.megacrit.cardcrawl.vfx.combat.DamageImpactLineEffect;
import com.megacrit.cardcrawl.vfx.combat.DamageNumberEffect;

public class NewStrikeEffect extends AbstractGameEffect {
    public NewStrikeEffect(AbstractCreature target, float x, float y, int number) {
        AbstractDungeon.effectsQueue.add(new DamageNumberEffect(target, x, y, number));

        int i;
//        for(i = 0; i < 18; ++i) {
//            AbstractDungeon.effectsQueue.add(new DamageImpactLineEffect(x, y));
//        }
//
        for(i = 0; i < 5; ++i) {
            AbstractDungeon.effectsQueue.add(new DamageImpactCurvyEffect(x, y));
        }

        if (number < 5) {
            CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, false);
        } else if (number < 20) {
            CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
        } else {
            CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.SHORT, false);
        }

    }

    public NewStrikeEffect(AbstractCreature target, float x, float y, String msg) {
        AbstractDungeon.effectsQueue.add(new BlockedWordEffect(target, x, y, msg));

        for(int i = 0; i < 18; ++i) {
            AbstractDungeon.effectsQueue.add(new BlockImpactLineEffect(x, y));
        }

        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
    }

    public void update() {
        this.isDone = true;
    }

    public void render(SpriteBatch sb) {
    }
}

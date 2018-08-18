package Thmod.Relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class KyoryuNoHagoromo extends AbstractThRelic {
    public static final String ID = "KyoryuNoHagoromo";

    public KyoryuNoHagoromo()
    {
        super("KyoryuNoHagoromo",  RelicTier.RARE, LandingSound.FLAT);
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        AbstractPlayer p = AbstractDungeon.player;
        if(damageAmount <= 0){
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new PlatedArmorPower(p, 1), 1));
        }
        return damageAmount;
    }

    protected  void onRightClick(){
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new KyoryuNoHagoromo();
    }
}

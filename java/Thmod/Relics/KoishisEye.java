package Thmod.Relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class KoishisEye extends AbstractThRelic {
    public static final String ID = "KoishisEye";

    public KoishisEye()
    {
        super("KoishisEye",  RelicTier.SPECIAL, LandingSound.FLAT);
    }

    public void onEquip()
    {
        Settings.hideCombatElements = true;
    }

    public void onUnequip(){
        Settings.hideCombatElements = false;
    }

    public void atBattleStart() {
        if(!(Settings.hideCombatElements))
            Settings.hideCombatElements = true;
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new StrengthPower(p,3),3));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new DexterityPower(p,3),3));
    }

    protected  void onRightClick(){
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new KoishisEye();
    }
}

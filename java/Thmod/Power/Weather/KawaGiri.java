package Thmod.Power.Weather;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class KawaGiri extends AbstractPower {
    public static final String POWER_ID = "KaiSei";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("KawaGiri");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;
    private int StrgengthCounter;
    private int DexterityCounter;

    public KawaGiri(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "KawaGiri";
        this.owner = owner;
        this.amount = 3;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Weather/KawaGiri.png");
        this.type = PowerType.BUFF;
        this.StrgengthCounter = 0;
        this.DexterityCounter = 0;
    }

    public void atStartOfTurnPostDraw() {
        int roll = MathUtils.random(5);
        if(roll >= 2){
            this.StrgengthCounter = roll - 2;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.StrgengthCounter), this.StrgengthCounter));
            flash();
        }
        else {
            this.DexterityCounter = roll;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, this.DexterityCounter), this.DexterityCounter));
            flash();
        }
    }

    public void atEndOfRound() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, -this.StrgengthCounter), -this.StrgengthCounter));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, -this.DexterityCounter), -this.DexterityCounter));
        flash();
        if (this.amount <= 1)
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "KawaGiri"));
        else
            this.amount -= 1;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}

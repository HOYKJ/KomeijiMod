package Thmod.Power;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class UndeadPower extends AbstractPower {
    public static final String POWER_ID = "UndeadPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("UndeadPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;

    public UndeadPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "UndeadPower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/UndeadPower.png");
        this.type = PowerType.BUFF;
    }

    @Override
    public int onLoseHp(int damageAmount) {
        int outPut = damageAmount;
        if(this.owner.currentHealth <= damageAmount){
            outPut = this.owner.currentHealth - 1;
        }
        return super.onLoseHp(outPut);
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}

package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

import Thmod.Orbs.TateNingyou;

public class RoundTable extends AbstractPower {
    public static final String POWER_ID = "RoundTable";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("RoundTable");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;
    private int Innate;

    public RoundTable(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "RoundTable";
        this.owner = owner;
        this.amount = amount;
        this.Innate += amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/RoundTable.png");
        this.type = PowerType.BUFF;
    }

    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer){
            if(p.hasPower("Dexterity")){
                this.amount = (this.Innate + p.getPower("Dexterity").amount);
            }
            int TateNum = 0;
            for (int i = 0; i < p.orbs.size(); i++) {
                if (p.orbs.get(i) instanceof TateNingyou)
                    TateNum += 1;
            }
            AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, (this.amount * TateNum)));
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}

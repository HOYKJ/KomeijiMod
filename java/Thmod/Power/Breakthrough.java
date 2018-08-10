package Thmod.Power;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Breakthrough extends AbstractPower {
    public static final String POWER_ID = "Breakthrough";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Breakthrough");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;

    public Breakthrough(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "Breakthrough";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/Breakthrough.png");
        this.type = PowerType.BUFF;
    }

    public void atStartOfTurn() {
        p.currentBlock = 0;
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner,this.owner,this));
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}

package Thmod.Power;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class VampirePower extends AbstractPower {
    public static final String POWER_ID = "VampirePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("VampirePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;

    public VampirePower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "VampirePower";
        this.owner = owner;
        this.amount = -1;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/VampirePower.png");
        this.type = PowerType.BUFF;
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        p.heal((damageAmount/4));
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}

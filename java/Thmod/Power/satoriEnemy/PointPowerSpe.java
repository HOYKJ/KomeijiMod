package Thmod.Power.satoriEnemy;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class PointPowerSpe extends AbstractPower {
    public static final String POWER_ID = "PointPowerSpe";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("PointPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;
    private static ArrayList<AbstractCard> cardid = new ArrayList<>();

    public PointPowerSpe(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "PointPowerSpe";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/PointPower.png");
        this.type = PowerType.BUFF;
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}

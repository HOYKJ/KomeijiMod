package Thmod.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SelenePower extends AbstractPower {
    private static final Logger logger = LogManager.getLogger(FreezePower.class.getName());
    public static final String POWER_ID = "SelenePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("SelenePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;
    private DamageInfo thornsInfo;

    public SelenePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "SelenePower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/SelenePower.png");
        this.type = PowerType.BUFF;
        this.thornsInfo = new DamageInfo(owner, this.amount, DamageInfo.DamageType.THORNS);
    }

    public void stackPower(int stackAmount) {
        if (this.amount == -1) {
            logger.info(this.name + " does not stack");
            return;
        }
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        this.thornsInfo = new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS);
        updateDescription();
    }

    public int onAttacked(DamageInfo info, int damageAmount)
    {
        if ((info.owner != null) && (info.type != DamageInfo.DamageType.THORNS) && (info.type != DamageInfo.DamageType.HP_LOSS) && (info.owner != this.owner))
        {
            flash();
            AbstractDungeon.actionManager.addToTop(new DamageAction(info.owner, this.thornsInfo, AbstractGameAction.AttackEffect.NONE));
        }

        return damageAmount;
    }

    public void atStartOfTurn()
    {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "SelenePower"));
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}

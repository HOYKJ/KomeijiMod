package Thmod.Potion;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import Thmod.Power.DeepSleep;

public class RedTea extends AbstractPotion
{
    public static final String POTION_ID = "RedTea";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("RedTea");
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public RedTea()
    {
        super(NAME, "RedTea", PotionRarity.RARE, PotionSize.BOTTLE, PotionColor.ATTACK);
        this.description = DESCRIPTIONS[0];
        this.isThrown = true;
        this.targetRequired = true;
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target)
    {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, AbstractDungeon.player, new DeepSleep(target)));
    }

    public AbstractPotion makeCopy()
    {
        return new PpointPotion();
    }

    public int getPotency(int ascensionLevel)
    {
        return 3;
    }
}

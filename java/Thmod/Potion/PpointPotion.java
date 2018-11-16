package Thmod.Potion;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Thmod.Power.PointPower;

public class PpointPotion extends AbstractPotion
{
    public static final String POTION_ID = "PpointPotion";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("PpointPotion");
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public PpointPotion()
    {
        super(NAME, "PpointPotion", AbstractPotion.PotionRarity.COMMON, PotionSize.BOLT, AbstractPotion.PotionColor.ATTACK);
        this.potency = getPotency();
        this.description = (DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1]);
        this.isThrown = false;
        this.tips.add(new PowerTip(this.name, this.description));
//        this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.DEXTERITY.NAMES[0]), GameDictionary.keywords.get(GameDictionary.DEXTERITY.NAMES[0])));
    }

    public void use(AbstractCreature target)
    {
        target = AbstractDungeon.player;
        label:
        {
            for (AbstractPower p : target.powers) {
                if (p instanceof PointPower) {
                    if (p.amount > 2) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, target, new PointPower(target, (5 - p.amount)), (5 - p.amount)));
                        break label;
                    }
                }
            }
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, target, new PointPower(target, 3), 3));
        }
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

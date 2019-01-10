package Thmod.Potion;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import Thmod.Power.JyouchiRei;

public class JyouchiReiPotion extends AbstractPotion
{
    public static final String POTION_ID = "JyouchiReiPotion";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("JyouchiReiPotion");
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public JyouchiReiPotion()
    {
        super(NAME, "JyouchiReiPotion", PotionRarity.RARE, PotionSize.GHOST, PotionColor.ATTACK);
        this.description = DESCRIPTIONS[0];
        this.isThrown = true;
        this.targetRequired = true;
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(TipHelper.capitalize("紧缚灵"), GameDictionary.keywords.get("紧缚灵")));
    }

    public void use(AbstractCreature target)
    {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, AbstractDungeon.player, new JyouchiRei(target, 3), 3));
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

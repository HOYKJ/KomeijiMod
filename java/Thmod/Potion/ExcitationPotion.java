package Thmod.Potion;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.DexterityPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ExcitationPotion extends AbstractPotion
{
    public static final String POTION_ID = "ExcitationPotion";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("ExcitationPotion");
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public ExcitationPotion()
    {
        super(NAME, "ExcitationPotion", PotionRarity.RARE, PotionSize.T, PotionColor.ATTACK);
        this.potency = getPotency();
        this.description = (DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1]);
        this.isThrown = false;
        this.tips.add(new PowerTip(this.name, this.description));
//        this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.DEXTERITY.NAMES[0]), GameDictionary.keywords.get(GameDictionary.DEXTERITY.NAMES[0])));
    }

    public void use(AbstractCreature target)
    {
        target = AbstractDungeon.player;
        for(AbstractPower p:target.powers){
            if(p.amount >= 1)
                p.amount += this.potency;
        }
    }

    public AbstractPotion makeCopy()
    {
        return new ExcitationPotion();
    }

    public int getPotency(int ascensionLevel)
    {
        return 2;
    }
}

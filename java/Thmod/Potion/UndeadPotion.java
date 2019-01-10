package Thmod.Potion;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import Thmod.Power.UndeadPower;

public class UndeadPotion extends AbstractPotion
{
    public static final String POTION_ID = "UndeadPotion";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("UndeadPotion");
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public UndeadPotion()
    {
        super(NAME, "UndeadPotion", PotionRarity.RARE, PotionSize.HEART, PotionColor.ATTACK);
        this.description = DESCRIPTIONS[0];
        this.isThrown = false;
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target)
    {
        target = AbstractDungeon.player;
        target.maxHealth -= 10;
        if(target.currentHealth > target.maxHealth)
            target.currentHealth = target.maxHealth;
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, AbstractDungeon.player, new UndeadPower(target)));
    }

    public boolean canUse()
    {
        if (super.canUse())
        {
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters)
            {
                if (m.type == AbstractMonster.EnemyType.BOSS)
                {
                    return false;
                }
            }
        }
        return true;
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

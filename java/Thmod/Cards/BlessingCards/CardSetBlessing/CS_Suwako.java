package Thmod.Cards.BlessingCards.CardSetBlessing;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.vfx.combat.RoomTintEffect;

import Thmod.Cards.BlessingCards.AbstractBlessingCard;
import Thmod.Power.Abnormal.BlueAbnormity;
import Thmod.Power.Abnormal.GreenAbnormity;
import Thmod.Power.Abnormal.RedAbnormity;
import Thmod.vfx.AgararetaCoverEffect;
import Thmod.vfx.BorderVeryLongFlashEffect;

public class CS_Suwako extends AbstractBlessingCard
{
    public static final String ID = "CS_Suwako";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public CS_Suwako()
    {
        super("CS_Suwako", CS_Suwako.NAME, CS_Suwako.DESCRIPTION);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
    }

    public void triggerWhenDrawn()
    {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(p, 1));
        AbstractDungeon.effectsQueue.add(new RoomTintEffect(Color.BLACK.cpy(), 0.8F,4F,true));
        for (int i = 0; i < 10; ++i)
            AbstractDungeon.topLevelEffects.add(new AgararetaCoverEffect());
        AbstractDungeon.topLevelEffects.add(new BorderVeryLongFlashEffect(Color.PURPLE.cpy()));
        AbstractMonster m = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
        int roll = MathUtils.random(2);
        switch (roll) {
            case 0:
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(m, p, new RedAbnormity(m, 3), 3));
                break;
            case 1:
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(m, p, new GreenAbnormity(m, 3), 3));
                break;
            case 2:
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(m, p, new BlueAbnormity(m, 3), 3));
                break;
            default:
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(m, p, new RedAbnormity(m, 3), 3));
        }
        AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(this, p.hand));
    }

    public AbstractCard makeCopy()
    {
        return new CS_Suwako();
    }

    public void upgrade() {}

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("CS_Suwako");
        NAME = CS_Suwako.cardStrings.NAME;
        DESCRIPTION = CS_Suwako.cardStrings.DESCRIPTION;
    }
}

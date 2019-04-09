package Thmod.Cards.BlessingCards.CardSetBlessing;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

import Thmod.Cards.BlessingCards.AbstractBlessingCard;

public class CS_Meirin extends AbstractBlessingCard
{
    public static final String ID = "CS_Meirin";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;

    public CS_Meirin()
    {
        super("CS_Meirin", CS_Meirin.NAME, CS_Meirin.DESCRIPTION);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
    }

    public void triggerWhenDrawn()
    {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(p, 1));
        ArrayList<AbstractPower> powers = new ArrayList<>();
        for(AbstractPower power : p.powers){
            if(power.type == AbstractPower.PowerType.DEBUFF){
                powers.add(power);
            }
        }
        if(powers.size() >= 1) {
            int i = powers.size() - 1;
            int roll = MathUtils.random(i);
            p.powers.remove(powers.get(roll));
        }
        AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(this, p.hand));
    }

    public AbstractCard makeCopy()
    {
        return new CS_Meirin();
    }

    public void upgrade() {}

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("CS_Meirin");
        NAME = CS_Meirin.cardStrings.NAME;
        DESCRIPTION = CS_Meirin.cardStrings.DESCRIPTION;
    }
}

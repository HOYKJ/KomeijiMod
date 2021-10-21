package Thmod.Cards.UncommonCards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Power.GasuOrimonoPower;
import Thmod.Power.StrengthEndPower;
import Thmod.vfx.BorderVeryLongFlashEffect;
import Thmod.vfx.animation.PoisonSmokeEffect;

public class GasuOrimono extends AbstractKomeijiCards {
    public static final String ID = "GasuOrimono";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int STR_AMOUNT = 3;

    public GasuOrimono() {
        super("GasuOrimono", GasuOrimono.NAME,  1, GasuOrimono.DESCRIPTION, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF, CardSet_k.REISEN);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.effectList.add(new BorderVeryLongFlashEffect(new
            Color(MathUtils.random(100.0F, 110.0F) / 255, MathUtils.random(210.0F, 220.0F) / 255, MathUtils.random(10.0F, 20.0F) / 255, MathUtils.random(0.8F, 0.9F))));
        for(int i = 0; i < 12; i ++){
            AbstractDungeon.effectList.add(new PoisonSmokeEffect());
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new GasuOrimonoPower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new GasuOrimono();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("GasuOrimono");
        NAME = GasuOrimono.cardStrings.NAME;
        DESCRIPTION = GasuOrimono.cardStrings.DESCRIPTION;
    }
}

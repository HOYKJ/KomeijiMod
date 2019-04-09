package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Cards.NingyouShinki;
import Thmod.Power.MakuraSekiPower;
import basemod.helpers.TooltipInfo;

public class MakuraSeki extends AbstractKomeijiCards {
    public static final String ID = "MakuraSeki";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public MakuraSeki() {
        super("MakuraSeki", MakuraSeki.NAME,  1, MakuraSeki.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, CardSet_k.YUKARI);
        this.baseBlock = 7;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new MakuraSekiPower(p,this.upgraded)));
    }

    @Override
    public List<TooltipInfo> getCustomTooltips()
    {
        List<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
        return tips;
    }

    public AbstractCard makeCopy() {
        return new MakuraSeki();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("MakuraSeki");
        NAME = MakuraSeki.cardStrings.NAME;
        DESCRIPTION = MakuraSeki.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = MakuraSeki.cardStrings.EXTENDED_DESCRIPTION;
    }
}

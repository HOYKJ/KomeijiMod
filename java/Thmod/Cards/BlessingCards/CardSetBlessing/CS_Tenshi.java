package Thmod.Cards.BlessingCards.CardSetBlessing;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.unique.ChooseAction;
import Thmod.Cards.BlessingCards.AbstractBlessingCard;
import Thmod.Power.Weather.DaiyamondoDasuto;
import Thmod.Power.Weather.DonTen;
import Thmod.Power.Weather.Fuuu;
import Thmod.Power.Weather.Haku;
import Thmod.Power.Weather.HanaGumo;
import Thmod.Power.Weather.KaiSei;
import Thmod.Power.Weather.KawaGiri;
import Thmod.Power.Weather.KiriSame;
import Thmod.Power.Weather.Kousa;
import Thmod.Power.Weather.KyoKkou;
import Thmod.Power.Weather.Nagi;
import Thmod.Power.Weather.NouMu;
import Thmod.Power.Weather.RetsuJitsu;
import Thmod.Power.Weather.SeiRan;
import Thmod.Power.Weather.SouTen;
import Thmod.Power.Weather.Soyuki;
import Thmod.Power.Weather.TaiFuu;
import Thmod.Power.Weather.TenkiYume;
import Thmod.Power.Weather.Tsume;
import Thmod.Power.Weather.Yuki;

public class CS_Tenshi extends AbstractBlessingCard
{
    public static final String ID = "CS_Tenshi";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public CS_Tenshi()
    {
        super("CS_Tenshi", CS_Tenshi.NAME, CS_Tenshi.DESCRIPTION);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
    }

    public void triggerWhenDrawn() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(p, 1));
        final ChooseAction choice = new ChooseAction(this, null, CS_Tenshi.EXTENDED_DESCRIPTION[0], true, 1);

        choice.add(CS_Tenshi.EXTENDED_DESCRIPTION[1], "", () -> {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KaiSei(p)));
        });
        choice.add(CS_Tenshi.EXTENDED_DESCRIPTION[2], "", () -> {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KiriSame(p)));
        });
        choice.add(CS_Tenshi.EXTENDED_DESCRIPTION[3], "", () -> {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DonTen(p)));
        });
        choice.add(CS_Tenshi.EXTENDED_DESCRIPTION[4], "", () -> {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SouTen(p)));
        });
        choice.add(CS_Tenshi.EXTENDED_DESCRIPTION[5], "", () -> {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Haku(p)));
        });
        choice.add(CS_Tenshi.EXTENDED_DESCRIPTION[6], "", () -> {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new HanaGumo(p)));
        });
        choice.add(CS_Tenshi.EXTENDED_DESCRIPTION[7], "", () -> {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new NouMu(p)));
        });
        choice.add(CS_Tenshi.EXTENDED_DESCRIPTION[8], "", () -> {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Yuki(p)));
        });
        choice.add(CS_Tenshi.EXTENDED_DESCRIPTION[9], "", () -> {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new TenkiYume(p)));
        });
        choice.add(CS_Tenshi.EXTENDED_DESCRIPTION[10], "", () -> {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Soyuki(p)));
        });
        choice.add(CS_Tenshi.EXTENDED_DESCRIPTION[11], "", () -> {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Fuuu(p)));
        });
        choice.add(CS_Tenshi.EXTENDED_DESCRIPTION[12], "", () -> {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SeiRan(p)));
        });
        choice.add(CS_Tenshi.EXTENDED_DESCRIPTION[13], "", () -> {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KawaGiri(p)));
        });
        choice.add(CS_Tenshi.EXTENDED_DESCRIPTION[14], "", () -> {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new TaiFuu(p)));
        });
        choice.add(CS_Tenshi.EXTENDED_DESCRIPTION[15], "", () -> {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Nagi(p)));
        });
        choice.add(CS_Tenshi.EXTENDED_DESCRIPTION[16], "", () -> {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DaiyamondoDasuto(p)));
        });
        choice.add(CS_Tenshi.EXTENDED_DESCRIPTION[17], "", () -> {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Kousa(p)));
        });
        choice.add(CS_Tenshi.EXTENDED_DESCRIPTION[18], "", () -> {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RetsuJitsu(p)));
        });
        choice.add(CS_Tenshi.EXTENDED_DESCRIPTION[19], "", () -> {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Tsume(p)));
        });
        choice.add(CS_Tenshi.EXTENDED_DESCRIPTION[20], "", () -> {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KyoKkou(p)));
        });

        AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(this, p.hand));
        AbstractDungeon.actionManager.addToBottom(choice);
    }

    public AbstractCard makeCopy()
    {
        return new CS_Tenshi();
    }

    public void upgrade() {}

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("CS_Tenshi");
        NAME = CS_Tenshi.cardStrings.NAME;
        DESCRIPTION = CS_Tenshi.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = CS_Tenshi.cardStrings.EXTENDED_DESCRIPTION;
    }
}

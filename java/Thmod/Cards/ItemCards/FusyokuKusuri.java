package Thmod.Cards.ItemCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Power.PointPower;
import Thmod.Power.WocchiPower;

public class FusyokuKusuri extends AbstractItemCards {
    public static final String ID = "FusyokuKusuri";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 0;

    public FusyokuKusuri() {
        super("FusyokuKusuri", FusyokuKusuri.NAME,  0, FusyokuKusuri.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= 1) {
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new AbstractGameAction() {
                    public void update() {
                        m.rollMove();
                        AbstractDungeon.getMonsters().showIntent();
                        this.isDone = true;
                    }
                });
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p,p,"PointPower",1));
            }
        }
    }

    public AbstractCard makeCopy() {
        return new FusyokuKusuri();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("FusyokuKusuri");
        NAME = FusyokuKusuri.cardStrings.NAME;
        DESCRIPTION = FusyokuKusuri.cardStrings.DESCRIPTION;
    }
}

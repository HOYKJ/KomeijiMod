package Thmod.Cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.ArrayList;

import Thmod.vfx.KinbakuCrushEffect;

public class KeiseiJin extends AbstractSweepCards{
    public static final String ID = "KeiseiJin";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int BLOCK_AMT = 5;

    public KeiseiJin() {
        super("KeiseiJin", KeiseiJin.NAME,  1, KeiseiJin.DESCRIPTION, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);
        this.baseBlock = 5;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber,false), this.magicNumber));
        AbstractDungeon.effectList.add(new KinbakuCrushEffect(m.hb.cX, m.hb.cY, Color.SKY.cpy(), true));
    }

    @Override
    public ArrayList<AbstractSweepCards> getOpposite() {
        final ArrayList<AbstractSweepCards> opposite = new ArrayList<>();
        opposite.add(new KinbakuJin());
        opposite.add(new JyouchiJin());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new KeiseiJin();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("KeiseiJin");
        NAME = KeiseiJin.cardStrings.NAME;
        DESCRIPTION = KeiseiJin.cardStrings.DESCRIPTION;
    }
}

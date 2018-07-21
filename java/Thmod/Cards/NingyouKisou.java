package Thmod.Cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

import java.util.ArrayList;

import Thmod.Orbs.NingyouOrb;

public class NingyouKisou extends AbstractSweepCards {
    public static final String ID = "NingyouKisou";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int BLOCK_AMT = 5;

    public NingyouKisou() {
        super("NingyouKisou", NingyouKisou.NAME,  1, NingyouKisou.DESCRIPTION, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 5;
        this.isMultiDamage = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractOrb orb = new NingyouOrb();
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    @Override
    public ArrayList<AbstractSweepCards> getOpposite() {
        final ArrayList<AbstractSweepCards> opposite = new ArrayList<>();
        opposite.add(new NingyouSousou());
        opposite.add(new NingyouFukuhei());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new NingyouKisou();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("NingyouKisou");
        NAME = NingyouKisou.cardStrings.NAME;
        DESCRIPTION = NingyouKisou.cardStrings.DESCRIPTION;
    }
}

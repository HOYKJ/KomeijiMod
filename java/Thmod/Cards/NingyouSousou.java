package Thmod.Cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;

import java.util.ArrayList;

import Thmod.Orbs.NingyouOrb;

public class NingyouSousou extends AbstractSweepCards {
    public static final String ID = "NingyouSousou";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;

    public NingyouSousou() {
        super("NingyouSousou", NingyouSousou.NAME,  1, NingyouSousou.DESCRIPTION, CardType.ATTACK, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);
        this.baseBlock = 5;
        this.baseDamage = 5;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        int EmptyNum = 0;
        for(int i = 0;i < p.orbs.size();i++){
            if(p.orbs.get(i) instanceof EmptyOrbSlot)
                EmptyNum += 1;
        }
        if(EmptyNum > 0) {
            AbstractOrb orb = new NingyouOrb();
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
        }
        AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
    }

    @Override
    public ArrayList<AbstractSweepCards> getOpposite() {
        final ArrayList<AbstractSweepCards> opposite = new ArrayList<>();
        opposite.add(new NingyouKisou());
        opposite.add(new NingyouFukuhei());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new NingyouSousou();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("NingyouSousou");
        NAME = NingyouSousou.cardStrings.NAME;
        DESCRIPTION = NingyouSousou.cardStrings.DESCRIPTION;
    }
}

package Thmod.Cards.SpellCards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.RoomTintEffect;

import Thmod.Power.Abnormal.BlueAbnormity;
import Thmod.Power.Abnormal.GreenAbnormity;
import Thmod.Power.Abnormal.RedAbnormity;
import Thmod.vfx.AgararetaCoverEffect;
import Thmod.vfx.BorderVeryLongFlashEffect;

public class TatariKami extends AbstractSpellCards {
    public static final String ID = "TatariKami";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private int pointcost;

    public TatariKami() {
        super("TatariKami", TatariKami.NAME,  1, TatariKami.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.pointcost = 5;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                AbstractDungeon.effectsQueue.add(new RoomTintEffect(Color.BLACK.cpy(), 0.8F,4F,true));
                for (int i = 0; i < 10; ++i)
                    AbstractDungeon.topLevelEffects.add(new AgararetaCoverEffect());
                AbstractDungeon.topLevelEffects.add(new BorderVeryLongFlashEffect(Color.PURPLE.cpy()));
                for (int i = (AbstractDungeon.getCurrRoom().monsters.monsters.size() - 1); i >= 0; i--) {
                    AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                    if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, p, new RedAbnormity(target, 3), 3));
                        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, p, new GreenAbnormity(target, 3), 3));
                        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, p, new BlueAbnormity(target, 3), 3));
                    }
                }
            }
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        super.canUse(p,m);
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                return true;
            }
        }
        this.cantUseMessage = AbstractSpellCards.EXTENDED_DESCRIPTION[4];
        return false;
    }

    public AbstractCard makeCopy() {
        return new TatariKami();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("TatariKami");
        NAME = TatariKami.cardStrings.NAME;
        DESCRIPTION = TatariKami.cardStrings.DESCRIPTION;
    }
}

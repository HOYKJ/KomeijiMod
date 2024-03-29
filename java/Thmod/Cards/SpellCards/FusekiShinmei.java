package Thmod.Cards.SpellCards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.EmpowerEffect;
import com.megacrit.cardcrawl.vfx.combat.RoomTintEffect;

import Thmod.Actions.common.LatterAction;
import Thmod.vfx.SunderLineHandler;
import Thmod.vfx.SunderSoulEffect;
import basemod.DevConsole;

public class FusekiShinmei extends AbstractSpellCards {
    public static final String ID = "FusekiShinmei";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 3;
    private int pointcost;

    public FusekiShinmei() {
        super("FusekiShinmei", FusekiShinmei.NAME,  3, FusekiShinmei.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.pointcost = 5;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                AbstractDungeon.effectsQueue.add(new RoomTintEffect(Color.BLACK.cpy(), 0.8F));
                AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(new Color(1.0F, 0.0F, 1.0F, 0.7F)));
                AbstractDungeon.actionManager.addToBottom(new LatterAction(() -> {
                    AbstractDungeon.effectsQueue.add(new SunderLineHandler(p, m));
                }, 0.2f));
                AbstractDungeon.actionManager.addToBottom(new LatterAction(() -> {
                    //CardCrawlGame.sound.play("ATTACK_FLAME_BARRIER", 0.05F);
                    CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, false);
                    AbstractDungeon.effectsQueue.add(new SunderSoulEffect(p, m, 0.8F));
                    AbstractDungeon.effectsQueue.add(new EmpowerEffect(p.hb.cX, p.hb.cY));
                    AbstractDungeon.effectsQueue.add(new EmpowerEffect(m.hb.cX, m.hb.cY));
                    float moper = ((float) m.currentHealth / (float) m.maxHealth);
                    float plper = ((float) p.currentHealth / (float) p.maxHealth);
                    p.currentHealth = (int) (p.maxHealth * moper);
                    p.healthBarUpdatedEvent();
                    int moNewHealth = (int) (m.maxHealth * plper);
                    if (moNewHealth <= 0) {
                        moNewHealth = 1;
                    }
                    m.currentHealth = moNewHealth;
                    m.healthBarUpdatedEvent();
                }, 0.6f));

                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p, p, "PointPower", this.pointcost));
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
        return new FusekiShinmei();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("FusekiShinmei");
        NAME = FusekiShinmei.cardStrings.NAME;
        DESCRIPTION = FusekiShinmei.cardStrings.DESCRIPTION;
    }
}

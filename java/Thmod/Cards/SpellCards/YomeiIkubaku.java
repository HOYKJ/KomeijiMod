package Thmod.Cards.SpellCards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.RoomTintEffect;

import Thmod.Actions.common.LatterAction;
import Thmod.vfx.SoulEffect;
import Thmod.vfx.StopAnimEffect;
import Thmod.vfx.YomeiCutEffect;

public class YomeiIkubaku extends AbstractSpellCards {
    public static final String ID = "YomeiIkubaku";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 3;
    private int pointcost;

    public YomeiIkubaku() {
        super("YomeiIkubaku", YomeiIkubaku.NAME,  3, YomeiIkubaku.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.pointcost = 5;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                AbstractDungeon.effectsQueue.add(new RoomTintEffect(Color.BLACK.cpy(), 0.8F));
                AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(new Color(1.0F, 0.0F, 1.0F, 0.7F)));
                AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
                    m.tint.color = Color.LIGHT_GRAY.cpy();
                    m.tint.changeColor(Color.WHITE.cpy(), 0.5f);
                }, 0.5f));
                AbstractDungeon.actionManager.addToBottom(new LatterAction(()->{
                    CardCrawlGame.sound.play("ATTACK_HEAVY", 0);
                    CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.SHORT, false);
                    AbstractDungeon.effectsQueue.add(new YomeiCutEffect(m));
                    AbstractDungeon.effectsQueue.add(new SoulEffect(m, 0.4F));
                    m.currentHealth = (m.currentHealth/2);
                    m.healthBarUpdatedEvent();
                }, 0.3f));
                AbstractDungeon.effectsQueue.add(new StopAnimEffect(m, 1.2F));
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"PointPower",this.pointcost));
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
        return new YomeiIkubaku();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("YomeiIkubaku");
        NAME = YomeiIkubaku.cardStrings.NAME;
        DESCRIPTION = YomeiIkubaku.cardStrings.DESCRIPTION;
    }
}

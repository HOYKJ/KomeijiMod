package Thmod.Cards.RewardCards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import Thmod.Cards.AbstractKomeijiCards;

public class RemiliaStretch extends AbstractKomeijiCards {
    public static final String ID = "RemiliaStretch";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 3;

    public RemiliaStretch() {
        super("RemiliaStretch", RemiliaStretch.NAME,  3, RemiliaStretch.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseDamage = 50;
        this.isMultiDamage = true;
        this.exhaust = true;
        this.timesUpgraded = 1;
        this.upgraded = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new WeightyImpactEffect(target.hb.cX, target.hb.cY, new Color(1.0F, 0.1F, 0.1F, 0.0F))));
            }
        }
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, DamageInfo.DamageType.HP_LOSS, AbstractGameAction.AttackEffect.NONE));
    }

    public boolean canUpgrade()
    {
        return false;
    }

    public AbstractCard makeCopy() {
        return new RemiliaStretch();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("RemiliaStretch");
        NAME = RemiliaStretch.cardStrings.NAME;
        DESCRIPTION = RemiliaStretch.cardStrings.DESCRIPTION;
    }
}

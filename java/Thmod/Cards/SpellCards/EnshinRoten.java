package Thmod.Cards.SpellCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Cards.DemonLordCradle;
import Thmod.Cards.UncommonCards.SeishiRoten;
import Thmod.ThMod;

public class EnshinRoten extends AbstractSpellCards {
    public static final String ID = "EnshinRoten";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 4;
    private int pointcost;
    public boolean next;

    public EnshinRoten(boolean next) {
        super("EnshinRoten", EnshinRoten.NAME,  1, EnshinRoten.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 4;
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.pointcost = 3;
        this.next = next;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if(!next) {
            if (p.hasPower("PointPower")) {
                if (p.getPower("PointPower").amount >= this.pointcost) {
                    if (this.magicNumber > 0) {
                        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                        if(this.magicNumber > 1) {
                            AbstractCard tmp = new EnshinRoten(true);
                            AbstractDungeon.player.limbo.addToBottom(tmp);
                            tmp.target = this.target;
                            tmp.current_x = this.current_x;
                            tmp.current_y = this.current_y;
                            tmp.target_x = (Settings.WIDTH / 2.0F - 300.0F * Settings.scale);
                            tmp.target_y = (Settings.HEIGHT / 2.0F);
                            tmp.magicNumber = this.magicNumber - 1;
                            tmp.freeToPlayOnce = true;
                            if (m != null) {
                                tmp.calculateCardDamage(m);
                            }
                            tmp.purgeOnUse = true;
                            AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(tmp, m, this.energyOnUse));
                        }
                    }
                    AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p, p, "PointPower", this.pointcost));
                }
            }
        }
        else {
            if (this.magicNumber > 0) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                if(this.magicNumber > 1) {
                    AbstractCard tmp = new EnshinRoten(true);
                    AbstractDungeon.player.limbo.addToBottom(tmp);
                    tmp.target = this.target;
                    tmp.current_x = this.current_x;
                    tmp.current_y = this.current_y;
                    tmp.target_x = (Settings.WIDTH / 2.0F - 300.0F * Settings.scale);
                    tmp.target_y = (Settings.HEIGHT / 2.0F);
                    tmp.magicNumber = this.magicNumber - 1;
                    tmp.freeToPlayOnce = true;
                    if (m != null) {
                        tmp.calculateCardDamage(m);
                    }
                    tmp.purgeOnUse = true;
                    AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(tmp, m, this.energyOnUse));
                }
            }
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        super.canUse(p,m);
        if(!next) {
            if (p.hasPower("PointPower")) {
                if (p.getPower("PointPower").amount >= this.pointcost) {
                    return true;
                }
            }
            this.cantUseMessage = AbstractSpellCards.EXTENDED_DESCRIPTION[4];
            return false;
        }
        return true;
    }

    public AbstractCard makeCopy() {
        return new EnshinRoten(false);
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("EnshinRoten");
        NAME = EnshinRoten.cardStrings.NAME;
        DESCRIPTION = EnshinRoten.cardStrings.DESCRIPTION;
    }
}

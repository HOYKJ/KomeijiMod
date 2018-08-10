package Thmod.Cards.SpellCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import Thmod.Relics.SpellCardsRule;

public class HangonChyou extends AbstractSpellCards {
    public static final String ID = "HangonChyou";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private int pointcost;

    public HangonChyou(int Times) {
        super("HangonChyou", HangonChyou.NAME,  1, HangonChyou.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseDamage = (8 + 3*Times);
        this.isMultiDamage = true;
        this.pointcost = 3;
        switch (Times){
            case 0:
                this.name = "回忆「反魂蝶 -一分咲-」";
                break;
            case 1:
                this.name = "回忆「反魂蝶  -贰分咲-」";
                break;
            case 2:
                this.name = "回忆「反魂蝶  -参分咲-」";
                break;
            case 3:
                this.name = "回忆「反魂蝶  -肆分咲-」";
                break;
            case 4:
                this.name = "回忆「反魂蝶  -伍分咲-」";
                break;
            case 5:
                this.name = "回忆「反魂蝶  -陆分咲-」";
                break;
            case 6:
                this.name = "回忆「反魂蝶  -柒分咲-」";
                break;
            case 7:
                this.name = "回忆「反魂蝶  -八分咲-」";
                break;
            case 8:
                this.name = "回忆「西行寺无余涅槃」";
                break;
            default:
                this.name = "「反魂蝶 -一分咲-」";
                break;
        }
        initializeTitle();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"PointPower",this.pointcost));
                if (SpellCardsRule.Hangongnum < 8)
                    SpellCardsRule.Hangongnum += 1;
                SpellCardsRule.HangongUsed = true;
                UnlockTracker.unlockCard("MuyoNehan");
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
        this.cantUseMessage = "我没有足够的P点";
        return false;
    }

    public AbstractCard makeCopy() {
        return new HangonChyou(0);
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("HangonChyou");
        NAME = HangonChyou.cardStrings.NAME;
        DESCRIPTION = HangonChyou.cardStrings.DESCRIPTION;
    }
}

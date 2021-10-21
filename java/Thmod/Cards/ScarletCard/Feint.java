package Thmod.Cards.ScarletCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.Remiria.PlusCardAction;
import Thmod.Actions.common.DrawSpeCardAction;
import Thmod.Actions.unique.ChooseAction;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class Feint extends AbstractRemiriaCards {
    public static final String ID = "Feint";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public Feint() {
        this(false);
    }

    public Feint(boolean isPlus) {
        super("Feint", Feint.NAME,  0, Feint.DESCRIPTION, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, isPlus);
        this.baseDamage = 3;
        this.addTips();
        this.attackType = AttackType.CHAIN;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        if(this.isPlus){
            if(p.hand.getAttacks().group.size() <= 1){
                final ChooseAction choice = new ChooseAction(null, null, Absorbed.EXTENDED_DESCRIPTION[2], true, 1);
                for (AbstractCard card : p.drawPile.group) {
                    if(card.type == CardType.ATTACK) {
                        choice.add(card, () -> {
                            AbstractDungeon.actionManager.addToBottom(new DrawSpeCardAction(card));
                        });
                    }
                }
                AbstractDungeon.actionManager.addToBottom(choice);
            }
        }
        int tmp = 0;
        for(AbstractCard card : AbstractDungeon.actionManager.cardsPlayedThisTurn){
            if(card.type == CardType.ATTACK){
                tmp++;
            }
        }
        if(tmp <= 1){
            final ChooseAction choice = new ChooseAction(null, null, Absorbed.EXTENDED_DESCRIPTION[2], true, 1);
            for (AbstractCard card : p.drawPile.group) {
                if(card.type == CardType.ATTACK) {
                    choice.add(card, () -> {
                        AbstractDungeon.actionManager.addToBottom(new DrawSpeCardAction(card));
                    });
                }
            }
            AbstractDungeon.actionManager.addToBottom(choice);
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new Feint(true);
            }
        }
        return new Feint();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Feint");
        NAME = Feint.cardStrings.NAME;
        DESCRIPTION = Feint.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Feint.cardStrings.EXTENDED_DESCRIPTION;
    }
}

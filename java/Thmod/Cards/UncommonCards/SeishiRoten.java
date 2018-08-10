package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import Thmod.Actions.unique.PlayerTalkAction;
import Thmod.Cards.AbstractSweepCards;
import Thmod.Relics.SpellCardsRule;
import basemod.DevConsole;

import static Thmod.Relics.SpellCardsRule.SeishiRotenNum;

public class SeishiRoten extends AbstractSweepCards {
    public static final String ID = "SeishiRoten";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int DAMAGE_AMT = 5;

    public SeishiRoten() {
        super("SeishiRoten", SeishiRoten.NAME,  1, SeishiRoten.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 7;
        this.exhaust = true;
    }

    public void calculateCardDamage(AbstractMonster mo){
        super.calculateCardDamage(mo);
        this.damage += SpellCardsRule.SeishiRotenNum;
        this.isDamageModified = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        SpellCardsRule.SeishiRotenNum += 2;
    }

    public void triggerOnExhaust(){
        if(!(this.purgeOnUse)) {
            if(AbstractDungeon.player.hand.size() >= 10)
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new SeishiRoten(), 1));
            else
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new SeishiRoten(), 1));
        }
    }

    @Override
    public ArrayList<AbstractSweepCards> getOpposite() {
        final ArrayList<AbstractSweepCards> opposite = new ArrayList<>();
        opposite.add(new KokorosuKi());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new SeishiRoten();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("SeishiRoten");
        NAME = SeishiRoten.cardStrings.NAME;
        DESCRIPTION = SeishiRoten.cardStrings.DESCRIPTION;
    }
}

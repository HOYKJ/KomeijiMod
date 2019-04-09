package Thmod.Cards.ElementCards.UncommonCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import Thmod.Cards.ElementCards.AbstractElementSweepCards;

public class EmeraldCity extends AbstractElementSweepCards {
    public static final String ID = "EmeraldCity";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;

    public EmeraldCity() {
        super("EmeraldCity", EmeraldCity.NAME,  2, EmeraldCity.DESCRIPTION, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.SELF_AND_ENEMY,ElementType.Earth);
        this.baseBlock = 8;
        this.baseDamage = 0;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        super.use(p,m);
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));

        this.baseDamage = (p.currentBlock + this.block);
        calculateCardDamage(m);
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));

        this.rawDescription = DESCRIPTION;
        initializeDescription();
    }

    public void applyPowers()
    {
        this.baseDamage = (AbstractDungeon.player.currentBlock + this.block);
        super.applyPowers();

        this.rawDescription = DESCRIPTION;
        this.rawDescription += UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    public void onMoveToDiscard()
    {
        this.rawDescription = DESCRIPTION;
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo)
    {
        super.calculateCardDamage(mo);
        this.rawDescription = DESCRIPTION;
        this.rawDescription += UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    @Override
    public ArrayList<AbstractElementSweepCards> getOpposite() {
        final ArrayList<AbstractElementSweepCards> opposite = new ArrayList<>();
        opposite.add(new MidautumnSpear(0));
        opposite.add(new DiamondHardness());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new EmeraldCity();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("EmeraldCity");
        NAME = EmeraldCity.cardStrings.NAME;
        DESCRIPTION = EmeraldCity.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = EmeraldCity.cardStrings.UPGRADE_DESCRIPTION;
    }
}

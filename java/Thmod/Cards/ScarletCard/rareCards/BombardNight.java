package Thmod.Cards.ScarletCard.rareCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.common.RandomAttackAction;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class BombardNight extends AbstractRemiriaCards {
    public static final String ID = "BombardNight";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public BombardNight() {
        this(false);
    }

    public BombardNight(boolean isPlus) {
        super("BombardNight", BombardNight.NAME,  2, BombardNight.DESCRIPTION, CardType.ATTACK, CardRarity.RARE, CardTarget.NONE, isPlus);
        this.baseDamage = 3;
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.addTips();
        this.attackType = null;
    }

    public void calculateCardDamage(AbstractMonster mo){
        super.calculateCardDamage(mo);
        AbstractPlayer p = AbstractDungeon.player;
        this.baseMagicNumber = 0;
        this.baseMagicNumber = p.exhaustPile.group.size();
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        AbstractPlayer p = AbstractDungeon.player;
        this.baseMagicNumber = 0;
        this.baseMagicNumber = p.exhaustPile.group.size();
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new RandomAttackAction(AbstractDungeon.getMonsters().getRandomMonster(true),
                new DamageInfo(p, this.damage, DamageInfo.DamageType.THORNS), this.magicNumber, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        if(!this.isPlus){
            p.exhaustPile.group.clear();
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new BombardNight(true);
            }
        }
        return new BombardNight();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeDamage(2);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("BombardNight");
        NAME = BombardNight.cardStrings.NAME;
        DESCRIPTION = BombardNight.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = BombardNight.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = BombardNight.cardStrings.EXTENDED_DESCRIPTION;
    }
}

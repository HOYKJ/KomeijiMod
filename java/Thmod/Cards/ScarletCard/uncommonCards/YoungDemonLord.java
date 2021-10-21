package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Thmod.Actions.Remiria.IncreaseMagicAction;
import Thmod.Actions.Remiria.RemoveDebuffsActionChange;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.abstracts.CustomSavable;
import basemod.helpers.TooltipInfo;

public class YoungDemonLord extends AbstractRemiriaCards implements CustomSavable<int[]> {
    public static final String ID = "YoungDemonLord";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    public int[] mis2 = {1};
    private boolean upgradedMis = false;

    public YoungDemonLord() {
        this(false);
    }

    public YoungDemonLord(boolean isPlus) {
        super("YoungDemonLord", YoungDemonLord.NAME,  2, YoungDemonLord.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, isPlus);
        this.misc = 6;
        this.baseBlock = this.misc;
        this.mis2[0] = 1;
        this.baseMagicNumber = this.mis2[0];
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.baseMagicNumber = this.mis2[0];
        this.magicNumber = this.baseMagicNumber;
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new IncreaseMiscAction(this.uuid, 0, 2));
        if(this.isPlus){
            AbstractDungeon.actionManager.addToBottom(new IncreaseMagicAction(this.uuid, 1));
        }
        super.use(p, m);
    }

    @Override
    public void applyPowers() {
        this.baseBlock = this.misc;
        this.baseMagicNumber = this.mis2[0];
        this.magicNumber = this.baseMagicNumber;
        super.applyPowers();
        initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.baseMagicNumber = this.mis2[0];
        this.magicNumber = this.baseMagicNumber;
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                AbstractCard card = new YoungDemonLord(true);
                ((YoungDemonLord)card).mis2 = this.mis2.clone();
                return card;
            }
        }
        AbstractCard card = new YoungDemonLord();
        ((YoungDemonLord)card).mis2 = this.mis2.clone();
        return card;
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.mis2[0] += 1;
            this.upgradedMis = true;
            this.baseMagicNumber = this.mis2[0];
            this.magicNumber = this.baseMagicNumber;
            this.isMagicNumberModified = true;
        }
    }

    @Override
    public void displayUpgrades() {
        super.displayUpgrades();
        if(this.upgradedMis){
            this.mis2[0] -= 1;
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    @Override
    public int[] onSave() {
        return this.mis2;
    }

    @Override
    public void onLoad(int[] arg0)
    {
        if (arg0 == null) {
            return;
        }
        this.mis2 = arg0.clone();
        this.addTips();
        this.applyPowers();
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard card = super.makeStatEquivalentCopy();
        ((YoungDemonLord)card).mis2 = this.mis2.clone();
        return card;
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("YoungDemonLord");
        NAME = YoungDemonLord.cardStrings.NAME;
        DESCRIPTION = YoungDemonLord.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = YoungDemonLord.cardStrings.EXTENDED_DESCRIPTION;
    }
}

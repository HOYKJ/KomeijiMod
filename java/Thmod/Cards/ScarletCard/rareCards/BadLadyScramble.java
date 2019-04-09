package Thmod.Cards.ScarletCard.rareCards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class BadLadyScramble extends AbstractRemiriaCards {
    public static final String ID = "BadLadyScramble";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public BadLadyScramble() {
        this(false);
    }

    public BadLadyScramble(boolean isPlus) {
        super("BadLadyScramble", BadLadyScramble.NAME,  -1, BadLadyScramble.DESCRIPTION, CardType.SKILL, CardRarity.RARE, CardTarget.NONE, isPlus);
        this.exhaust = true;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (this.energyOnUse < EnergyPanel.totalCount) {
            this.energyOnUse = EnergyPanel.totalCount;
        }
        if(!this.upgraded){
            this.energyOnUse -= 1;
        }
        if (p.hasRelic("Chemical X")) {
            this.energyOnUse += 2;
            p.getRelic("Chemical X").flash();
        }
        for(;this.energyOnUse > 0; this.energyOnUse --){
            AbstractCard card = AbstractDungeon.returnTrulyRandomCard().makeCopy();
            card.freeToPlayOnce = true;
            if(this.isPlus){
                if(card instanceof AbstractRemiriaCards){
                    ((AbstractRemiriaCards) card).plusCard();
                }
            }
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(card));
        }
        if(!this.freeToPlayOnce) {
            p.energy.use(EnergyPanel.totalCount);
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new BadLadyScramble(true);
            }
        }
        return new BadLadyScramble();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("BadLadyScramble");
        NAME = BadLadyScramble.cardStrings.NAME;
        DESCRIPTION = BadLadyScramble.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = BadLadyScramble.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = BadLadyScramble.cardStrings.EXTENDED_DESCRIPTION;
    }
}

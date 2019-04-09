package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.Remiria.MakeCardInHandAction;
import Thmod.Actions.unique.ChooseAction;
import Thmod.Cards.ScarletCard.Absorbed;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Cards.ScarletCard.rareCards.BadLadyScramble;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class Paradox extends AbstractRemiriaCards {
    public static final String ID = "Paradox";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public Paradox() {
        this(false);
    }

    public Paradox(boolean isPlus) {
        super("Paradox", Paradox.NAME,  0, Paradox.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE, isPlus);
        this.isEthereal = true;
        this.exhaust = true;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        final ChooseAction choice = new ChooseAction(null, null, Absorbed.EXTENDED_DESCRIPTION[2], true, 1);
        for (AbstractCard card : p.hand.group) {
            if(!card.cardID.equals(this.cardID)) {
                choice.add(card, () -> {
                    AbstractCard card1 = card.makeStatEquivalentCopy();
                    final ChooseAction choice1 = new ChooseAction(card1, null, Absorbed.EXTENDED_DESCRIPTION[2], false, 1);
                    choice1.add(card1.name, EXTENDED_DESCRIPTION[2], () -> {
                        card1.type = CardType.ATTACK;
                        card1.rawDescription += EXTENDED_DESCRIPTION[2];
                        card1.initializeDescription();
                        AbstractDungeon.actionManager.addToBottom(new MakeCardInHandAction(card1));
                    });
                    choice1.add(card1.name, EXTENDED_DESCRIPTION[3], () -> {
                        card1.type = CardType.SKILL;
                        card1.rawDescription += EXTENDED_DESCRIPTION[3];
                        card1.initializeDescription();
                        AbstractDungeon.actionManager.addToBottom(new MakeCardInHandAction(card1));
                    });
                    choice1.add(card1.name, EXTENDED_DESCRIPTION[4], () -> {
                        card1.type = CardType.POWER;
                        card1.rawDescription += EXTENDED_DESCRIPTION[4];
                        card1.initializeDescription();
                        AbstractDungeon.actionManager.addToBottom(new MakeCardInHandAction(card1));
                    });

                    choice1.add(card1.name, EXTENDED_DESCRIPTION[5], () -> {
                        card1.type = CardType.STATUS;
                        card1.rawDescription += EXTENDED_DESCRIPTION[5];
                        card1.initializeDescription();
                        AbstractDungeon.actionManager.addToBottom(new MakeCardInHandAction(card1));
                    });
                    choice1.add(card1.name, EXTENDED_DESCRIPTION[6], () -> {
                        card1.type = CardType.CURSE;
                        card1.rawDescription += EXTENDED_DESCRIPTION[6];
                        card1.initializeDescription();
                        AbstractDungeon.actionManager.addToBottom(new MakeCardInHandAction(card1));
                    });

                    AbstractDungeon.actionManager.addToBottom(choice1);
                });
            }
        }
        AbstractDungeon.actionManager.addToBottom(choice);
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new Paradox(true);
            }
        }
        return new Paradox();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.isEthereal = false;
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void plusCard() {
        super.plusCard();
        this.retain = true;
    }

    @Override
    public void normalCard() {
        super.normalCard();
        this.retain = false;
    }

    @Override
    public void update() {
        super.update();
        if((this.isPlus) && (!this.retain)){
            this.retain = true;
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Paradox");
        NAME = Paradox.cardStrings.NAME;
        DESCRIPTION = Paradox.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = Paradox.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = Paradox.cardStrings.EXTENDED_DESCRIPTION;
    }
}

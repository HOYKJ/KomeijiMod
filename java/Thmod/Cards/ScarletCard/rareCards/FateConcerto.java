package Thmod.Cards.ScarletCard.rareCards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoDrawPower;

import java.util.concurrent.atomic.AtomicInteger;

import Thmod.Actions.common.DrawSpeCardAction;
import Thmod.Actions.common.LatterAction;
import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class FateConcerto extends AbstractRemiriaCards {
    public static final String ID = "FateConcerto";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public FateConcerto() {
        this(false);
    }

    public FateConcerto(boolean isPlus) {
        super("FateConcerto", FateConcerto.NAME,  1, FateConcerto.DESCRIPTION, CardType.SKILL, CardRarity.RARE, CardTarget.NONE, isPlus);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if(p instanceof RemiriaScarlet) {
            if (this.isPlus) {
                this.magicNumber += 2;
            }

            for (int i = 0; i < this.magicNumber; i++) {
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
                if (!(p.hasPower(NoDrawPower.POWER_ID))) {
                    AbstractDungeon.actionManager.addToBottom(new LatterAction(() -> {
                        AbstractCard card = p.hand.getTopCard();
                        if (!((RemiriaScarlet) p).masterGroupCopy.contains(card)) {
                            AbstractDungeon.actionManager.addToTop(new DrawSpeCardAction(card));
                            AbstractMonster monster = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
                            if (monster != null) {
                                AbstractCard tmp = card.makeCopy();
                                AbstractDungeon.player.limbo.addToBottom(tmp);
                                tmp.target_x = (Settings.WIDTH / 2.0F - 300.0F * Settings.scale);
                                tmp.target_y = (Settings.HEIGHT / 2.0F);
                                tmp.freeToPlayOnce = true;
                                if (m != null) {
                                    tmp.calculateCardDamage(m);
                                }
                                tmp.purgeOnUse = true;
                                AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(tmp, monster, tmp.energyOnUse));
                            }
                            AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(card, p.hand));
                        }
                    }, 0f));
                }
            }

//            AtomicInteger did = new AtomicInteger();
//            if (!(p.hasPower(NoDrawPower.POWER_ID))) {
//                for (int i = 0; i < Math.min(this.magicNumber, p.drawPile.size()); i++) {
//                    int finalI1 = i;
//                    AbstractDungeon.actionManager.addToBottom(new LatterAction(() -> {
//                        if (p.drawPile.group.size() == 0) {
//                            did.set(finalI1);
//                            AbstractDungeon.actionManager.addToBottom(new EmptyDeckShuffleAction());
//                        }
//                        int finalI = finalI1 - did.get();
//                        AbstractDungeon.actionManager.addToBottom(new LatterAction(() -> {
//                            AbstractCard card = AbstractDungeon.player.drawPile.group.get(p.drawPile.size() - finalI - 1);
//                            if (((RemiriaScarlet) p).masterGroupCopy.contains(card)) {
//                                AbstractDungeon.actionManager.addToTop(new DrawSpeCardAction(card));
//                            } else {
//                                AbstractDungeon.actionManager.addToTop(new DrawSpeCardAction(card));
//                                AbstractMonster monster = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
//                                if (monster != null) {
//                                    AbstractCard tmp = card.makeCopy();
//                                    AbstractDungeon.player.limbo.addToBottom(tmp);
//                                    tmp.target_x = (Settings.WIDTH / 2.0F - 300.0F * Settings.scale);
//                                    tmp.target_y = (Settings.HEIGHT / 2.0F);
//                                    tmp.freeToPlayOnce = true;
//                                    if (m != null) {
//                                        tmp.calculateCardDamage(m);
//                                    }
//                                    tmp.purgeOnUse = true;
//                                    AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(tmp, monster, tmp.energyOnUse));
//                                }
//                                AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(card, p.hand));
//                            }
//                        }, 0f));
//                    }, 0f));
//                }
//            }
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new FateConcerto(true);
            }
        }
        return new FateConcerto();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("FateConcerto");
        NAME = FateConcerto.cardStrings.NAME;
        DESCRIPTION = FateConcerto.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = FateConcerto.cardStrings.EXTENDED_DESCRIPTION;
    }
}

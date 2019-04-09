package Thmod.Cards.ScarletCard;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class TricksterDevil extends AbstractRemiriaFate {
    public static final String ID = "TricksterDevil";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public TricksterDevil() {
        this(false);
    }

    public TricksterDevil(boolean isPlus) {
        super("TricksterDevil", TricksterDevil.NAME,  -2, TricksterDevil.DESCRIPTION, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE, isPlus);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractMonster monster = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
        if (monster != null) {
            AbstractCard tmp;
            do {
                tmp = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
            }
            while (tmp.cost == -2);
            if(this.isPlus){
                if(tmp instanceof AbstractRemiriaCards){
                    ((AbstractRemiriaCards) tmp).plusCard();
                }
            }
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
        if(this.upgraded){
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
        }
        super.use(p, m);
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        this.use(AbstractDungeon.player, null);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new TricksterDevil(true);
            }
        }
        return new TricksterDevil();
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
        cardStrings = CardCrawlGame.languagePack.getCardStrings("TricksterDevil");
        NAME = TricksterDevil.cardStrings.NAME;
        DESCRIPTION = TricksterDevil.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = TricksterDevil.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = TricksterDevil.cardStrings.EXTENDED_DESCRIPTION;
    }
}

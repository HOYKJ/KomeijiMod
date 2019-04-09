package Thmod.Cards.ScarletCard.uncommonCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import Thmod.Cards.ScarletCard.AbstractRemiriaCards;
import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.CurseofVladTepesPower;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.TooltipInfo;

public class CurseofVladTepes extends AbstractRemiriaCards {
    public static final String ID = "CurseofVladTepes";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public CurseofVladTepes() {
        this(false);
    }

    public CurseofVladTepes(boolean isPlus) {
        super("CurseofVladTepes", CurseofVladTepes.NAME,  2, CurseofVladTepes.DESCRIPTION, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF, isPlus);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.addTips();
        this.attackType = AttackType.LIGHT;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CurseofVladTepesPower(p, this.magicNumber), this.magicNumber));
        if(this.isPlus){
            ArrayList<AbstractCard> groupCopy = new ArrayList<>();
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if ((c.cost > 0) && (c.costForTurn > 0) && (!c.freeToPlayOnce)) {
                    groupCopy.add(c);
                }
            }
            groupCopy.remove(this);
            AbstractCard c = null;
            if (!groupCopy.isEmpty())
            {
                c = groupCopy.get(AbstractDungeon.cardRandomRng.random(0, groupCopy.size() - 1));
            }
            if (c != null)
            {
                c.setCostForTurn(0);
            }
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new CurseofVladTepes(true);
            }
        }
        return new CurseofVladTepes();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("CurseofVladTepes");
        NAME = CurseofVladTepes.cardStrings.NAME;
        DESCRIPTION = CurseofVladTepes.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = CurseofVladTepes.cardStrings.EXTENDED_DESCRIPTION;
    }
}

package Thmod.Cards.ScarletCard;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;

import Thmod.Characters.RemiriaScarlet;
import Thmod.Power.remiria.ScarletLordPower;
import basemod.helpers.BaseModCardTags;
import basemod.helpers.TooltipInfo;

public class Defend_Remirira extends AbstractRemiriaCards {
    public static final String ID = "Defend_Remirira";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    public Defend_Remirira() {
        this(false);
    }

    public Defend_Remirira(boolean isPlus) {
        super("Defend_Remirira", Defend_Remirira.NAME,  1, Defend_Remirira.DESCRIPTION, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF, isPlus);
        this.baseBlock = 5;
        tags.add(CardTags.STARTER_DEFEND);
        this.addTips();
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
        if(this.isPlus){
            AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
        }
        super.use(p, m);
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null){
            if((AbstractDungeon.player instanceof RemiriaScarlet) && (AbstractDungeon.player.hasPower(ScarletLordPower.POWER_ID))){
                return new Defend_Remirira(true);
            }
        }
        return new Defend_Remirira();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }

    @Override
    public void addTips() {
        this.tips.clear();
        this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Defend_Remirira");
        NAME = Defend_Remirira.cardStrings.NAME;
        DESCRIPTION = Defend_Remirira.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Defend_Remirira.cardStrings.EXTENDED_DESCRIPTION;
    }
}

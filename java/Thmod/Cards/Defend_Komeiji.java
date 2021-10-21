package Thmod.Cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Relics.KoishisEye;
import basemod.helpers.BaseModCardTags;

public class Defend_Komeiji extends AbstractKomeijiCards{
    public static final String ID = "Defend_Komeiji";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int BLOCK_AMT = 5;

    public Defend_Komeiji() {
        super("Defend_Komeiji", Defend_Komeiji.NAME,  1, Defend_Komeiji.DESCRIPTION, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF, CardSet_k.SATORI);
        this.baseBlock = 5;
        tags.add(CardTags.STARTER_DEFEND);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null) {
            if (AbstractDungeon.player.hasRelic(KoishisEye.ID)) {
                return new Defend_Koishi();
            }
        }
        return new Defend_Komeiji();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Defend_Komeiji");
        NAME = Defend_Komeiji.cardStrings.NAME;
        DESCRIPTION = Defend_Komeiji.cardStrings.DESCRIPTION;
    }
}

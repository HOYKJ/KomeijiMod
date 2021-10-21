package Thmod.Cards.VictoryCards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;

import Thmod.Relics.Clue;
import basemod.DevConsole;

public class Searching extends AbstractVictoryCards{
    public static final String ID = "Searching";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private RewardItem clue = new RewardItem(new Clue());

    public Searching() {
        super("Searching", Searching.NAME, Searching.DESCRIPTION, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        this.baseMagicNumber = 10;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
    }

    public AbstractCard makeCopy() {
        return new Searching();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeMagicNumber(5);
        }
    }

    @Override
    public void onVictory() {
        int roll = (MathUtils.random(99) + 1);
        DevConsole.logger.info(roll);
        if(roll < this.magicNumber) {
            roll = (MathUtils.random(99) + 1);
            if(roll < 10)
                AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.RARE);
            else if(roll < 40)
                AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.UNCOMMON);
            else
                AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.COMMON);
        }

        roll = (MathUtils.random(99) + 1);
        if(roll < (this.magicNumber + 5)){
            AbstractDungeon.getCurrRoom().addPotionToRewards();
        }

        roll = (MathUtils.random(99) + 1);
        if(roll < (this.magicNumber + 10)){
            AbstractDungeon.getCurrRoom().addCardToRewards();
        }

        roll = (MathUtils.random(99) + 1);
        if(roll < (this.magicNumber + 15)){
            roll = (MathUtils.random(99) + 1);
            AbstractDungeon.getCurrRoom().addGoldToRewards(roll);
        }

        if(this.clue.relic.counter != 3) {
            roll = (MathUtils.random(99) + 1);
            if (roll < this.magicNumber) {
                AbstractDungeon.getCurrRoom().rewards.add(this.clue);
            }
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Searching");
        NAME = Searching.cardStrings.NAME;
        DESCRIPTION = Searching.cardStrings.DESCRIPTION;
    }
}

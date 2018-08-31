package Thmod.Events;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

import java.util.ArrayList;
import java.util.Collections;

import Thmod.Relics.BookofPenglai;
import Thmod.ThMod;

public class Kourindou extends AbstractImageEvent {
    public static final String ID = "Kourindou";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Kourindou");
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String INTRO_MSG = DESCRIPTIONS[0];
    private AbstractRelic choice;
    private boolean upcard;
    private AbstractCard upCard;
    private AbstractRelic getRelic;
    private boolean noRelic = true;
    private int getCoin;
    private float x = 400.0F * Settings.scale;
    private float y = 200.0F * Settings.scale;
    private CurScreen screen = CurScreen.INTRO;

    private static enum CurScreen
    {
        INTRO,TAKED,LEAVE;

        private CurScreen() {}
    }

    public Kourindou() {
        super(NAME, INTRO_MSG, "images/events/Kourindou.png");
        AbstractPlayer p = AbstractDungeon.player;

        ArrayList<AbstractRelic> relics = new ArrayList<>();
        relics.addAll(AbstractDungeon.player.relics);
        Collections.shuffle(relics, new java.util.Random(AbstractDungeon.miscRng.randomLong()));

        for (int i = 0; i < relics.size(); i++) {
            this.choice = (relics.get(i));
            if (this.choice.tier != AbstractRelic.RelicTier.STARTER)
                break;
            this.noRelic = false;
        }

        if (p.gold > 100)
            this.imageEventText.setDialogOption(OPTIONS[0]);
        else
            this.imageEventText.setDialogOption(OPTIONS[1], true);

        this.upcard = CardHelper.hasUpgradedCard();
        if (this.upcard) {
            this.upCard = CardHelper.returnUpgradedCard();
            switch (this.upCard.rarity) {
                case BASIC:
                    this.getRelic = AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.COMMON);
                    break;
                case COMMON:
                    this.getRelic = AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.COMMON);
                    break;
                case UNCOMMON:
                    this.getRelic = AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.UNCOMMON);
                    break;
                case RARE:
                    this.getRelic = AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.RARE);
                    break;
                default:
                    this.getRelic = AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.COMMON);
                    break;
            }
            this.imageEventText.setDialogOption(OPTIONS[2] + FontHelper.colorString(this.upCard.name, "r") + OPTIONS[3] + FontHelper.colorString(this.getRelic.name, "g") + OPTIONS[4],this.upCard.makeStatEquivalentCopy());
        } else
            this.imageEventText.setDialogOption(OPTIONS[5], true);

        switch (this.choice.tier) {
            case STARTER:
            case COMMON:
                this.getCoin = MathUtils.round(150 * AbstractDungeon.merchantRng.random(1.05F, 1.15F));
                break;
            case UNCOMMON:
                this.getCoin = MathUtils.round(250 * AbstractDungeon.merchantRng.random(1.05F, 1.15F));
                break;
            case RARE:
                this.getCoin = MathUtils.round(300 * AbstractDungeon.merchantRng.random(1.05F, 1.15F));
                break;
            case SHOP:
                this.getCoin = MathUtils.round(200 * AbstractDungeon.merchantRng.random(1.05F, 1.15F));
                break;
            case SPECIAL:
                if (this.choice.relicId.equals("Spirit Poop")) {
                    this.getCoin = 1;
                    break;
                }
                this.getCoin = MathUtils.round(400 * AbstractDungeon.merchantRng.random(1.05F, 1.15F));
                break;
            case BOSS:
                this.getCoin = MathUtils.round(600 * AbstractDungeon.merchantRng.random(1.05F, 1.15F));
                break;
        }

        if(this.noRelic)
            this.imageEventText.setDialogOption(OPTIONS[9], true);
        else
            this.imageEventText.setDialogOption(OPTIONS[6] + FontHelper.colorString(this.choice.name, "r") + OPTIONS[7] + this.getCoin + OPTIONS[8]);

        this.imageEventText.setDialogOption(OPTIONS[13]);

        if (ThMod.blessingOfDetermination == 1) {
            if(p.hasRelic("FamiliarSpoon"))
                this.imageEventText.setDialogOption(OPTIONS[11]);
            else
                this.imageEventText.setDialogOption(OPTIONS[10], true);
        }
    }

    protected void buttonEffect(int buttonPressed) {
        AbstractPlayer p = AbstractDungeon.player;
        switch (this.screen) {
            case INTRO:
                switch (buttonPressed) {
                    case 0:
                        this.screen = CurScreen.TAKED;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[12]);
                        this.imageEventText.clearRemainingOptions();
                        p.loseGold(100);
                        AbstractDungeon.getCurrRoom().rewards.clear();
                        AbstractDungeon.getCurrRoom().rewards.add(new RewardItem(PotionHelper.getRandomPotion()));
                        AbstractDungeon.getCurrRoom().rewards.add(new RewardItem(PotionHelper.getRandomPotion()));
                        AbstractDungeon.getCurrRoom().addCardToRewards();
                        AbstractDungeon.getCurrRoom().addCardToRewards();
                        break;
                    case 1:
                        this.screen = CurScreen.TAKED;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[12]);
                        this.imageEventText.clearRemainingOptions();
                        AbstractDungeon.effectList.add(new PurgeCardEffect(this.upCard));
                        AbstractDungeon.player.masterDeck.removeCard(this.upCard);
                        AbstractDungeon.getCurrRoom().rewards.clear();
                        AbstractDungeon.getCurrRoom().addRelicToRewards(this.getRelic);
                        break;
                    case 2:
                        this.screen = CurScreen.TAKED;

                        switch (this.choice.tier) {
                            case STARTER:
                            case COMMON:
                                this.imageEventText.updateBodyText(DESCRIPTIONS[3] + DESCRIPTIONS[5]);
                                break;
                            case UNCOMMON:
                                this.imageEventText.updateBodyText(DESCRIPTIONS[3] + DESCRIPTIONS[6]);
                                break;
                            case RARE:
                                this.imageEventText.updateBodyText(DESCRIPTIONS[3] + DESCRIPTIONS[7]);
                                break;
                            case SHOP:
                                this.imageEventText.updateBodyText(DESCRIPTIONS[3] + DESCRIPTIONS[6]);
                                break;
                            case SPECIAL:
                                if (this.choice.relicId.equals("Spirit Poop")) {
                                    this.imageEventText.updateBodyText(DESCRIPTIONS[3] + DESCRIPTIONS[4]);
                                    break;
                                }
                                this.imageEventText.updateBodyText(DESCRIPTIONS[3] + DESCRIPTIONS[8]);
                                break;
                            case BOSS:
                                this.imageEventText.updateBodyText(DESCRIPTIONS[3] + DESCRIPTIONS[9]);
                                break;
                        }

                        this.imageEventText.updateDialogOption(0, OPTIONS[12]);
                        this.imageEventText.clearRemainingOptions();
                        p.loseRelic(this.choice.relicId);
                        AbstractDungeon.getCurrRoom().rewards.clear();
                        AbstractDungeon.getCurrRoom().addGoldToRewards(this.getCoin);
                        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
                        break;
                    case 3:
                        this.screen = CurScreen.LEAVE;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[13]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[12]);
                        this.imageEventText.clearRemainingOptions();
                        break;
                    case 4:
                        this.screen = CurScreen.LEAVE;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[12]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[13]);
                        this.imageEventText.clearRemainingOptions();
                        ThMod.blessingOfDetermination += 1;
                        p.loseRelic("FamiliarSpoon");
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, new BookofPenglai());
                        break;
                }
                break;
            case TAKED:
                this.screen = CurScreen.LEAVE;
                this.imageEventText.updateBodyText(DESCRIPTIONS[11]);
                this.imageEventText.updateDialogOption(0, OPTIONS[13]);
                AbstractDungeon.combatRewardScreen.open();
                break;
            case LEAVE:
                openMap();
                break;
        }
    }
}

package Thmod.Power;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.ArrayList;
import java.util.Iterator;

import Thmod.Actions.unique.CardSelectAction;
import Thmod.Actions.unique.PlayerTalkAction;
import Thmod.Actions.unique.isSeedAction;
import Thmod.Cards.ElementCards.SpellCards.ElementalHarvester;
import Thmod.Cards.ElementCards.SpellCards.EmeraldMegalopolis;
import Thmod.Cards.ElementCards.SpellCards.ForestBlaze;
import Thmod.Cards.ElementCards.SpellCards.HydrogenousProminence;
import Thmod.Cards.ElementCards.SpellCards.KenjiaNoSeki;
import Thmod.Cards.ElementCards.SpellCards.LavaCromlech;
import Thmod.Cards.ElementCards.SpellCards.MercuryPoison;
import Thmod.Cards.ElementCards.SpellCards.NoachianDeluge;
import Thmod.Cards.ElementCards.SpellCards.PhlogisticPillar;
import Thmod.Cards.ElementCards.SpellCards.Photosynthesis;
import Thmod.Cards.ElementCards.SpellCards.RoyalDiamondRing;
import Thmod.Cards.ElementCards.SpellCards.SatelliteHimawari;
import Thmod.Cards.ElementCards.SpellCards.StElmoPillar;
import Thmod.Cards.ElementCards.SpellCards.SunshineReflector;
import Thmod.Cards.ElementCards.SpellCards.WaterElf;
import Thmod.Cards.SpellCards.CuteOchiyari;
import Thmod.Cards.SpellCards.DollofRoundTable;
import Thmod.Cards.SpellCards.DollsWar;
import Thmod.Cards.SpellCards.LemmingsParade;
import Thmod.Cards.SpellCards.TripWire;
import Thmod.Relics.BookofPenglai;
import Thmod.Relics.FamiliarSpoon;
import Thmod.ThMod;
import basemod.DevConsole;

public class PointPower extends AbstractPower {
    public static final String POWER_ID = "PointPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("PointPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;
    private static ArrayList<AbstractCard> cardid = new ArrayList<>();

    public PointPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "PointPower";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/PointPower.png");
        this.type = PowerType.BUFF;
    }

    public void atStartOfTurn() {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount > 5)
                this.amount = 5;
        }
        if(ThMod.StartSelectOpen)
            Start();

        if (AbstractDungeon.player.hasRelic("Strange Spoon")) {
            AbstractDungeon.player.loseRelic("Strange Spoon");
            AbstractRelic relic = new FamiliarSpoon();
            UnlockTracker.markRelicAsSeen(relic.relicId);
            relic.obtain();
            relic.isObtained = true;
            relic.isAnimating = false;
            relic.isDone = false;
        }
        if (ThMod.blessingOfDetermination == 2){
            boolean giveBook = true;
            for (AbstractRelic r : p.relics){
                if(r instanceof BookofPenglai) {
                    giveBook = false;
                    break;
                }
            }
            if(giveBook) {
                AbstractRelic relic = new BookofPenglai();
                relic.obtain();
                relic.isObtained = true;
                relic.isAnimating = false;
                relic.isDone = false;
            }
        }
    }

    public static void Start() {
        if(AbstractDungeon.player.hand.size() >= 10)
            AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(AbstractDungeon.player,DESCRIPTIONS[1]));
        else {
            cardid.clear();
            AbstractDungeon.actionManager.addToBottom(new isSeedAction());
        }
    }

    public void cardSelect(){
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount > 0) {
                int powercount = p.getPower("PointPower").amount;
                if (powercount > 5)
                    powercount = 5;
                DevConsole.logger.info("beforeSelect"+ powercount);
                if (powercount == 1)
                    AbstractDungeon.actionManager.addToBottom( new CardSelectAction(1, true, true, 1, cardid));
                else
                    AbstractDungeon.actionManager.addToBottom( new CardSelectAction(1, false, true, powercount, cardid));
            }
        }
    }

    public void isSeed(final AbstractCard c) {
        for (Iterator Iterator = ThMod.fightids.iterator(); Iterator.hasNext(); ) {
            String cardid1 = (String) Iterator.next();
            if (c.cardID.equals(cardid1)) {
                cardid.add(c);
            }
        }
    }

    public void addNingyou(int NingyouNum,int YariNum,int TateNum,int YumiNum){
        DevConsole.logger.info("NingyouNum"+NingyouNum);
        DevConsole.logger.info("YariNum"+YariNum);
        DevConsole.logger.info("TateNum"+TateNum);
        DevConsole.logger.info("YumiNum"+YumiNum);
        if(NingyouNum > 2){
            cardid.add(new TripWire());
            cardid.add(new LemmingsParade());
        }
        if(YariNum > 2)
            cardid.add(new CuteOchiyari());
        if((TateNum > 2) && ((!p.hasPower("RoundTable"))))
            cardid.add(new DollofRoundTable());
        if(YumiNum > 2)
            cardid.add(new DollsWar());
    }

    public void elementMix(boolean earth,boolean fire,boolean luna,boolean metal,boolean sun,boolean water,boolean wood,boolean isMix){
        if(isMix){
            cardid.clear();
        }
        if((metal) && (wood))
            cardid.add(new ElementalHarvester());
        if((metal) && (water))
            cardid.add(new MercuryPoison());
        if((metal) && (fire))
            cardid.add(new StElmoPillar());
        if((metal) && (earth))
            cardid.add(new EmeraldMegalopolis());
        if((wood) && (water))
            cardid.add(new WaterElf());
        if((wood) && (fire))
            cardid.add(new ForestBlaze());
        if((water) && (fire))
            cardid.add(new PhlogisticPillar());
        if((water) && (earth))
            cardid.add(new NoachianDeluge());
        if((fire) && (earth))
            cardid.add(new LavaCromlech());
        if((luna) && (metal))
            cardid.add(new SunshineReflector());
        if((luna) && (wood))
            cardid.add(new SatelliteHimawari());
        if((sun) && (wood))
            cardid.add(new Photosynthesis());
        if((sun) && (water))
            cardid.add(new HydrogenousProminence());
        if((sun) && (luna))
            cardid.add(new RoyalDiamondRing());
        if((metal) && (wood) && (water) && (fire) && (earth) && (!(p.hasPower("KenjiaPower"))))
            cardid.add(new KenjiaNoSeki());
        if(isMix){
            if(cardid.size() > 0) {
                AbstractCard c = cardid.get(0);
                AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(c));
            }
            else {
                AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, DESCRIPTIONS[2]));
            }
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }

}

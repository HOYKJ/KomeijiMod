package Thmod.Actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.ArrayList;
import java.util.Iterator;

import Thmod.Cards.DeriveCards.AbstractDeriveCards;
import Thmod.Cards.DeriveCards.TaikoAoe;
import Thmod.Cards.DeriveCards.TaikoDefend;
import Thmod.Cards.DeriveCards.TaikoStrike;
import Thmod.Cards.ElementCards.SpellCards.ElementExtend;
import Thmod.Cards.ElementCards.SpellCards.ElementInflux;
import Thmod.Cards.ElementCards.SpellCards.JellyfishPrincess;
import Thmod.Cards.ItemCards.ByoukiHeiyu;
import Thmod.Cards.ItemCards.FusyokuKusuri;
import Thmod.Cards.ItemCards.HisouNoKenItem;
import Thmod.Cards.ItemCards.IbukiHisyaku;
import Thmod.Cards.ItemCards.MajikkuPosyun;
import Thmod.Cards.ItemCards.Namazu;
import Thmod.Cards.ItemCards.ReiGeki;
import Thmod.Cards.ItemCards.RyuuSei;
import Thmod.Cards.ItemCards.SaSen;
import Thmod.Cards.ItemCards.SanbutsuTenmizu;
import Thmod.Cards.ItemCards.SeigyoBou;
import Thmod.Cards.ItemCards.SutoppuWocchi;
import Thmod.Cards.SpellCards.BurariHaieki;
import Thmod.Cards.SpellCards.CuteOchiyari;
import Thmod.Cards.SpellCards.DaiesanShikkaiKoroshi;
import Thmod.Cards.SpellCards.DeepEcologicalBomb;
import Thmod.Cards.SpellCards.DollofRoundTable;
import Thmod.Cards.SpellCards.DollsWar;
import Thmod.Cards.SpellCards.DraculaCradle;
import Thmod.Cards.SpellCards.EasyMasterSpark;
import Thmod.Cards.SpellCards.EnshinRoten;
import Thmod.Cards.SpellCards.FinalSpark;
import Thmod.Cards.SpellCards.FocusManipulate;
import Thmod.Cards.SpellCards.FusekiShinmei;
import Thmod.Cards.SpellCards.FuumaJin;
import Thmod.Cards.SpellCards.GensouFuubi;
import Thmod.Cards.SpellCards.HagoromoToki;
import Thmod.Cards.SpellCards.HangonChyou;
import Thmod.Cards.SpellCards.HappouKibaku;
import Thmod.Cards.SpellCards.HeartBreak;
import Thmod.Cards.SpellCards.Kamaitachi;
import Thmod.Cards.SpellCards.KokushiMusou;
import Thmod.Cards.SpellCards.KyoufuSaimin;
import Thmod.Cards.SpellCards.LemmingsParade;
import Thmod.Cards.SpellCards.MirenKamai;
import Thmod.Cards.SpellCards.Mireniamu;
import Thmod.Cards.SpellCards.MissingPurplePower;
import Thmod.Cards.SpellCards.MoozeNoKiseki;
import Thmod.Cards.SpellCards.MoukoNaikei;
import Thmod.Cards.SpellCards.MusouTensei;
import Thmod.Cards.SpellCards.MusuNoTegata;
import Thmod.Cards.SpellCards.PenglaiNingyou;
import Thmod.Cards.SpellCards.RapurasuNoMa;
import Thmod.Cards.SpellCards.ReturnInanimateness;
import Thmod.Cards.SpellCards.RokkonShyoujyou;
import Thmod.Cards.SpellCards.SakuraHirame;
import Thmod.Cards.SpellCards.SakuyaNoSekai;
import Thmod.Cards.SpellCards.SeikonRyuuri;
import Thmod.Cards.SpellCards.SenseofCherryBlossom;
import Thmod.Cards.SpellCards.SpeartheGungnir;
import Thmod.Cards.SpellCards.TaihouKen;
import Thmod.Cards.SpellCards.TaihouTsuigeki;
import Thmod.Cards.SpellCards.TatariKami;
import Thmod.Cards.SpellCards.TenkeiKisyou;
import Thmod.Cards.SpellCards.TripWire;
import Thmod.Cards.SpellCards.YomeiIkubaku;
import Thmod.Cards.UncommonCards.SenseofElegance;
import Thmod.Power.PointPower;
import Thmod.Relics.SpellCardsRule;
import Thmod.ThMod;
import Thmod.Utils;
import basemod.DevConsole;

import static Thmod.ThMod.masterSpellCard;
import static Thmod.ThMod.masterSpellCardFor2;
import static Thmod.ThMod.masterSpellCardFor3;
import static Thmod.ThMod.masterSpellCardFor5;
import static Thmod.ThMod.soulSpellCard;
import static Thmod.ThMod.soulSpellCardFor2;
import static Thmod.ThMod.soulSpellCardFor3;
import static Thmod.ThMod.soulSpellCardFor5;

public class CardSelectAction extends AbstractGameAction
{
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CardSelectAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private boolean random;
    private boolean cardOffset;
    private ArrayList<AbstractCard> cardsToShuffle;
    private static CardGroup SpellCards;
    private static CardGroup allUpgradedSpellCards;
    private ArrayList<AbstractCard> cardid;
    private String testid;
    private String testid2;
    int powercount;
    private AbstractCard deletecard;
    private int zh;

    public CardSelectAction(final int amount, final boolean random, final boolean cardOffset , int powercount,ArrayList<AbstractCard> cardid) {
        this.powercount = powercount;
        this.cardid = cardid;
        this.cardsToShuffle = new ArrayList<>();
        if (this.powercount >= 1){
            CardSelectAction.SpellCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for(AbstractCard card : masterSpellCard.group) {
                CardSelectAction.SpellCards.addToTop(card.makeCopy());
            }
        }
        if (this.powercount >= 2){
            for(AbstractCard card : masterSpellCardFor2.group) {
                CardSelectAction.SpellCards.addToTop(card.makeCopy());
            }
        }
        if (this.powercount >= 3){
            for(AbstractCard card : masterSpellCardFor3.group) {
                if(card instanceof HangonChyou) {
                    if (!(SpellCardsRule.HangongUsed)) {
                        CardSelectAction.SpellCards.addToTop(new HangonChyou(SpellCardsRule.Hangongnum));
                    }
                }
                else {
                    CardSelectAction.SpellCards.addToTop(card.makeCopy());
                }
            }
        }
        if (this.powercount >= 5){
            for(AbstractCard card : masterSpellCardFor5.group) {
                CardSelectAction.SpellCards.addToTop(card.makeCopy());
            }
        }
        if ((this.powercount >= 1) && (this.cardid.size() > 0)){
            for (Iterator localIterator = this.cardid.iterator(); localIterator.hasNext();) {
                AbstractCard Cardid = (AbstractCard) localIterator.next();
                if (Cardid.cardID.equals("HagoromoMizu")) {
                    CardSelectAction.SpellCards.addToTop(new HagoromoToki());
                }
            }
        }
        if ((this.powercount >= 2) && (this.cardid.size() > 0)){
            for (Iterator localIterator = this.cardid.iterator(); localIterator.hasNext();) {
                AbstractCard Cardid = (AbstractCard) localIterator.next();
                if ((Cardid.cardID.equals("KeiseiJin")) || (Cardid.cardID.equals("KinbakuJin")) || (Cardid.cardID.equals("JyouchiJin"))) {
                    CardSelectAction.SpellCards.addToTop(new FuumaJin());
                    CardSelectAction.SpellCards.addToTop(new FuumaJin());
                }
                if (Cardid.cardID.equals("DemonsDinnerFork")) {
                    CardSelectAction.SpellCards.addToTop(new HeartBreak());
                    CardSelectAction.SpellCards.addToTop(new HeartBreak());
                }
            }
        }
        if ((this.powercount >= 3) && (this.cardid.size() > 0)){
            for (Iterator localIterator = this.cardid.iterator(); localIterator.hasNext();) {
                AbstractCard Cardid = (AbstractCard) localIterator.next();
                if (Cardid.cardID.equals("Sabishigari")) {
                    CardSelectAction.SpellCards.addToTop(new MirenKamai());
                    CardSelectAction.SpellCards.addToTop(new MirenKamai());
                }
                if (Cardid.cardID.equals("NarrowSpark")) {
                    CardSelectAction.SpellCards.addToTop(new FinalSpark());
                    CardSelectAction.SpellCards.addToTop(new FinalSpark());
                }
                if (Cardid.cardID.equals("SeishiRoten")) {
                    CardSelectAction.SpellCards.addToTop(new EnshinRoten(false));
                    CardSelectAction.SpellCards.addToTop(new EnshinRoten(false));
                }
                if (Cardid.cardID.equals("KokorosuKi")) {
                    CardSelectAction.SpellCards.addToTop(new SakuraHirame());
                    CardSelectAction.SpellCards.addToTop(new SakuraHirame());
                }
                if (Cardid.cardID.equals("KouPou")) {
                    CardSelectAction.SpellCards.addToTop(new TaihouKen());
                    CardSelectAction.SpellCards.addToTop(new TaihouKen());
                }
            }
        }
        if ((this.powercount >= 4) && (this.cardid.size() > 0)){
            for (Iterator localIterator = this.cardid.iterator(); localIterator.hasNext();) {
                AbstractCard Cardid = (AbstractCard) localIterator.next();
                if ((Cardid.cardID.equals("KeiseiJin")) || (Cardid.cardID.equals("KinbakuJin")) || (Cardid.cardID.equals("JyouchiJin"))) {
                    CardSelectAction.SpellCards.addToTop(new HappouKibaku());
                    CardSelectAction.SpellCards.addToTop(new HappouKibaku());
                }
                if (Cardid.cardID.equals("RikonNoKama")) {
                    CardSelectAction.SpellCards.addToTop(new SeikonRyuuri());
                    CardSelectAction.SpellCards.addToTop(new SeikonRyuuri());
                }
                if (Cardid.cardID.equals("VampireKiss")) {
                    CardSelectAction.SpellCards.addToTop(new Mireniamu());
                    CardSelectAction.SpellCards.addToTop(new Mireniamu());
                }
                if (Cardid.cardID.equals("MusuNoYume")) {
                    CardSelectAction.SpellCards.addToTop(new MusuNoTegata());
                    CardSelectAction.SpellCards.addToTop(new MusuNoTegata());
                }
                if (Cardid.cardID.equals("HenyouMirume")) {
                    CardSelectAction.SpellCards.addToTop(new RapurasuNoMa());
                    CardSelectAction.SpellCards.addToTop(new RapurasuNoMa());
                }
                if (Cardid.cardID.equals("MissingPower")) {
                    CardSelectAction.SpellCards.addToTop(new MissingPurplePower());
                    CardSelectAction.SpellCards.addToTop(new MissingPurplePower());
                }
                if (Cardid.cardID.equals("KoKei")) {
                    CardSelectAction.SpellCards.addToTop(new MoukoNaikei());
                    CardSelectAction.SpellCards.addToTop(new MoukoNaikei());
                }
                if (Cardid.cardID.equals("MajikuruSanhai")) {
                    CardSelectAction.SpellCards.addToTop(new DeepEcologicalBomb());
                    CardSelectAction.SpellCards.addToTop(new DeepEcologicalBomb());
                }
                if (Cardid.cardID.equals("DemonsDinnerFork")) {
                    CardSelectAction.SpellCards.addToTop(new SpeartheGungnir());
                    CardSelectAction.SpellCards.addToTop(new SpeartheGungnir());
                }
                if (Cardid.cardID.equals("HisouTensoku")) {
                    CardSelectAction.SpellCards.addToTop(new TenkeiKisyou());
                    CardSelectAction.SpellCards.addToTop(new TenkeiKisyou());
                }
            }
        }
        if ((this.powercount >= 5) && (this.cardid.size() > 0)){
            for (Iterator localIterator = this.cardid.iterator(); localIterator.hasNext();) {
                AbstractCard Cardid = (AbstractCard) localIterator.next();
                if (Cardid.cardID.equals("DamonLordCradle")) {
                    CardSelectAction.SpellCards.addToTop(new DraculaCradle());
                    CardSelectAction.SpellCards.addToTop(new DraculaCradle());
                }
                if (Cardid.cardID.equals("NarrowSpark")) {
                    CardSelectAction.SpellCards.addToTop(new EasyMasterSpark());
                    CardSelectAction.SpellCards.addToTop(new EasyMasterSpark());
                }
                if (Cardid.cardID.equals("KouPou")) {
                    CardSelectAction.SpellCards.addToTop(new TaihouTsuigeki());
                    CardSelectAction.SpellCards.addToTop(new TaihouTsuigeki());
                }
                if (Cardid.cardID.equals("SenseofElegance")) {
                    CardSelectAction.SpellCards.addToTop(new SenseofCherryBlossom(Cardid.timesUpgraded, ((SenseofElegance)Cardid).extraEffect, ((SenseofElegance)Cardid).remainEffect));
                    CardSelectAction.SpellCards.addToTop(new SenseofCherryBlossom(Cardid.timesUpgraded, ((SenseofElegance)Cardid).extraEffect, ((SenseofElegance)Cardid).remainEffect));
                }
                if (Cardid.cardID.equals("MusouMyousyu")) {
                    CardSelectAction.SpellCards.addToTop(new MusouTensei());
                    CardSelectAction.SpellCards.addToTop(new MusouTensei());
                }
                if (Cardid.cardID.equals("LunaDial")) {
                    CardSelectAction.SpellCards.addToTop(new SakuyaNoSekai());
                    CardSelectAction.SpellCards.addToTop(new SakuyaNoSekai());
                }
                if (Cardid.cardID.equals("WumiGaWareru")) {
                    CardSelectAction.SpellCards.addToTop(new MoozeNoKiseki());
                    CardSelectAction.SpellCards.addToTop(new MoozeNoKiseki());
                }
                if (Cardid.cardID.equals("TerribleSouvenir")) {
                    CardSelectAction.SpellCards.addToTop(new KyoufuSaimin());
                    CardSelectAction.SpellCards.addToTop(new KyoufuSaimin());
                }
                if ((Cardid.cardID.equals("Agarareta")) || (Cardid.cardID.equals("DochyakuKami"))) {
                    CardSelectAction.SpellCards.addToTop(new TatariKami());
                    CardSelectAction.SpellCards.addToTop(new TatariKami());
                }
            }
        }


        if ((this.powercount == -1) && (this.cardid.size() > 0)){
            if(!(this.cardid.get(0).upgraded)){
                (CardSelectAction.SpellCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED)).addToTop(new TaikoDefend());
                CardSelectAction.SpellCards.addToTop(new TaikoStrike());
                CardSelectAction.SpellCards.addToTop(new TaikoAoe());
                this.zh = 3;
            }
            if(this.cardid.get(0).upgraded) {
                int i = 0;
                switch (i) {
                    case 0: {
                        AbstractDeriveCards c = new TaikoDefend();
                        c.upgrade();
                        (CardSelectAction.SpellCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED)).addToTop(c);
                    }
                    case 1: {
                        AbstractDeriveCards c = new TaikoStrike();
                        c.upgrade();
                        CardSelectAction.SpellCards.addToTop(c);
                    }
                    case 2: {
                        AbstractDeriveCards c = new TaikoAoe();
                        c.upgrade();
                        CardSelectAction.SpellCards.addToTop(c);
                        break;
                    }
                }
                this.zh = 3;
            }
        }


        if(ThMod.AliceOpen){
            if ((this.powercount >= 2) && (this.cardid.size() > 0)) {
                for (Iterator localIterator = this.cardid.iterator(); localIterator.hasNext(); ) {
                    AbstractCard Cardid = (AbstractCard) localIterator.next();
                    if (Cardid.cardID.equals("TripWire")) {
                        CardSelectAction.SpellCards.addToTop(new TripWire());
                    }
                }
                if(AbstractDungeon.player.orbs.size() >= 2)
                    CardSelectAction.SpellCards.addToTop(new FocusManipulate());
            }
            if ((this.powercount >= 3) && (this.cardid.size() > 0)) {
                for (Iterator localIterator = this.cardid.iterator(); localIterator.hasNext(); ) {
                    AbstractCard Cardid = (AbstractCard) localIterator.next();
                    if ((Cardid.cardID.equals("NingyouKasou")) || (Cardid.cardID.equals("OoedoNingyou"))) {
                        CardSelectAction.SpellCards.addToTop(new ReturnInanimateness());
                    }
                    if (Cardid.cardID.equals("CuteOchiyari")) {
                            CardSelectAction.SpellCards.addToTop(new CuteOchiyari());
                    }
                    if (Cardid.cardID.equals("DollofRoundTable")) {
                        CardSelectAction.SpellCards.addToTop(new DollofRoundTable());
                    }
                }
            }
            if ((this.powercount >= 4) && (this.cardid.size() > 0)) {
                for (Iterator localIterator = this.cardid.iterator(); localIterator.hasNext(); ) {
                    AbstractCard Cardid = (AbstractCard) localIterator.next();
                    if (Cardid.cardID.equals("ShanghaiNingyou")) {
                        CardSelectAction.SpellCards.addToTop(new PenglaiNingyou());
                    }
                    if (Cardid.cardID.equals("DollsWar")) {
                        CardSelectAction.SpellCards.addToTop(new DollsWar());
                    }
                }
            }
            if ((this.powercount >= 5) && (this.cardid.size() > 0)) {
                for (Iterator localIterator = this.cardid.iterator(); localIterator.hasNext(); ) {
                    AbstractCard Cardid = (AbstractCard) localIterator.next();
                    if (Cardid.cardID.equals("LemmingsParade")) {
                        CardSelectAction.SpellCards.addToTop(new LemmingsParade());
                    }
                }
            }
        }

        else{
            if ((this.powercount >= 2)) {
                CardSelectAction.SpellCards.addToTop(new ElementExtend());
                if(AbstractDungeon.player.orbs.size() >= 4)
                    CardSelectAction.SpellCards.addToTop(new ElementInflux());
            }
            if ((this.powercount >= 3) && (this.cardid.size() > 0)) {
                for (Iterator localIterator = this.cardid.iterator(); localIterator.hasNext(); ) {
                    AbstractCard Cardid = (AbstractCard) localIterator.next();
                    if (Cardid.cardID.equals("CondensedBubble")) {
                        CardSelectAction.SpellCards.addToTop(new JellyfishPrincess());
                    }
                }
            }
            if(this.cardid.size() > 0){
                for (int i = 0;i < this.cardid.size();i++) {
                    AbstractCard Cardid = this.cardid.get(i);
                    DevConsole.logger.info("Cardids"+Cardid.cardID);
                    if (Cardid.cardID.equals("ElementalHarvester") || (Cardid.cardID.equals("MercuryPoison")) || (Cardid.cardID.equals("StElmoPillar")) || (Cardid.cardID.equals("EmeraldMegalopolis"))
                            || (Cardid.cardID.equals("WaterElf")) || (Cardid.cardID.equals("ForestBlaze")) || (Cardid.cardID.equals("PhlogisticPillar")) || (Cardid.cardID.equals("NoachianDeluge"))
                            || (Cardid.cardID.equals("LavaCromlech")) || (Cardid.cardID.equals("SunshineReflector")) || (Cardid.cardID.equals("SatelliteHimawari")) || (Cardid.cardID.equals("Photosynthesis"))
                            || (Cardid.cardID.equals("HydrogenousProminence")) || (Cardid.cardID.equals("RoyalDiamondRing")) || (Cardid.cardID.equals("KenjiaNoSeki"))) {
                        CardSelectAction.SpellCards.addToTop(this.cardid.get(i));
                    }
                }
            }
        }

        CardSelectAction.SpellCards.sortAlphabetically(false);
        CardSelectAction.allUpgradedSpellCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (final AbstractCard c : CardSelectAction.SpellCards.group) {
            final AbstractCard copy = c.makeCopy();
            copy.upgrade();
            CardSelectAction.allUpgradedSpellCards.addToTop(copy);
        }
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = 0.5f;
        this.amount = amount;
        this.random = random;
        this.cardOffset = cardOffset;
    }

    public void update() {
        if (this.duration == 0.5f) {
            if (this.random) {
                this.cardsToShuffle = getRandomSpellCards(this.amount, false);
                SpellCardsRule.selected = true;
                this.cardSelect();
            }
            else {
                if (AbstractDungeon.cardRewardScreen.codexCard != null) {
                    final AbstractCard c = AbstractDungeon.cardRewardScreen.codexCard.makeStatEquivalentCopy();
                    this.cardsToShuffle.add(c);
                    AbstractDungeon.cardRewardScreen.codexCard = null;
                }
                if (this.amount > 0) {
                    --this.amount;
                    if(this.powercount >= 1) {
                        if (SpellCardsRule.newCards) {
                            if(AbstractDungeon.player.hasRelic("SpellExtend")){
                                SpellCardsRule.cardsToSelect = getRandomSpellCards(5, false);
                            }
                            else
                                SpellCardsRule.cardsToSelect = getRandomSpellCards(this.powercount, false);
                            SpellCardsRule.newCards = false;
                        }
                    }
                    else if(this.powercount == -1)
                        Utils.openCardRewardsScreen(getRandomSpellCards(this.zh, false), false,1);
                    if (this.powercount >= 2)
                        Utils.openCardRewardsScreen(SpellCardsRule.cardsToSelect, true,0);
//                    else if (this.powercount == -1)
                    return;
                }
                SpellCardsRule.clicked = false;
                this.cardSelect();
            }
        }
        this.tickDuration();
    }

    private void cardSelect() {
        final boolean randomSpot = true;
        boolean canRep = false;
        boolean given = false;
        if(AbstractDungeon.player.hand.size() >= 10)
            AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(AbstractDungeon.player, PointPower.DESCRIPTIONS[1]));
        else {
            for (final AbstractCard c : this.cardsToShuffle) {
                c.unhover();
                ThMod.removedcardids.clear();
                if (this.powercount >= 1) {
                    label1:
                    for (Iterator Iterator = ThMod.upcardids.iterator(); Iterator.hasNext(); ) {
                        this.testid = (String) Iterator.next();
                        if (c.cardID.equals(this.testid)) {
                            label2:
                            for (Iterator Iterator2 = ThMod.fightids.iterator(); Iterator2.hasNext(); ) {
                                this.testid2 = (String) Iterator2.next();
                                for (final AbstractCard cd : AbstractDungeon.player.hand.group) {
                                    if (cd.cardID.equals((this.testid2))) {
                                        DevConsole.logger.info("remove" + ThMod.removemap.get(cd.cardID));
                                        DevConsole.logger.info("up" + ThMod.upcardmap.get(c.cardID));
                                        if (ThMod.removemap.get(cd.cardID).equals(ThMod.upcardmap.get(c.cardID))) {
                                            ThMod.removedcardids.clear();
                                            ThMod.removedcardids.put(c.cardID, cd);
                                            this.deletecard = cd;
                                            given = true;
                                            canRep = true;
                                            break label2;
                                        }
                                    }
                                }
                            }
                            if (!(canRep)) {
                                AbstractDungeon.actionManager.addToBottom(new PlayerTalkAction(AbstractDungeon.player, TEXT[0]));
                                given = true;
                                break label1;
                            } else {
                                AbstractDungeon.player.hand.removeCard(this.deletecard);
                                AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(c));
                                SpellCardsRule.selected = true;
                                given = true;
                                break label1;
                            }
                        }
                    }
                    if (!(given)) {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(c));
                        SpellCardsRule.selected = true;
                    }
                } else if (this.powercount == -1)
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, false));
            }
        }
        this.cardsToShuffle.clear();
    }

    private static CardGroup allSpellCardsToUse() {
        //if (AbstractDungeon.player.hasRelic("PolishingWheel")) {
        //return CardSelectAction.allUpgradedUrns;
        //}
        return CardSelectAction.SpellCards;
    }

    private static AbstractCard getRandomSpellCards() {
        return allSpellCardsToUse().getRandomCard(false);
    }

    private static ArrayList<AbstractCard> getRandomSpellCards(final int amount, final boolean allowDuplicates) {
        final ArrayList<AbstractCard> cards = new ArrayList<>();
        while (cards.size() < amount) {
            int tries = 0;
            final AbstractCard card = getRandomSpellCards();
            if (allowDuplicates || !cards.contains(card) || tries++ > 10) {
                cards.add(card);
            }
        }
        return cards;
    }
}

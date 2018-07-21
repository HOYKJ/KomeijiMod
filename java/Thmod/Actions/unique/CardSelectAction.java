package Thmod.Actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.ArrayList;
import java.util.Iterator;

import Thmod.Actions.common.PlayerTalkAction;
import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Cards.AbstractSweepCards;
import Thmod.Cards.DeriveCards.AbstractDeriveCards;
import Thmod.Cards.DeriveCards.TaikoAoe;
import Thmod.Cards.DeriveCards.TaikoDefend;
import Thmod.Cards.DeriveCards.TaikoStrike;
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
import Thmod.Cards.SpellCards.AbstractSpellCards;
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
import Thmod.Cards.SpellCards.TenkeiKisyou;
import Thmod.Cards.SpellCards.TripWire;
import Thmod.Cards.SpellCards.YomeiIkubaku;
import Thmod.Relics.SpellCardsRule;
import Thmod.ThMod;
import Thmod.Utils;

public class CardSelectAction extends AbstractGameAction
{
    private static final float startingDuration = 0.5f;
    private boolean random;
    private static final boolean ALLOW_DUPLICATES = false;
    private static final boolean CHOOSE_FROM_ALL = false;
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
            (CardSelectAction.SpellCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED)).addToTop(new ReiGeki());
            CardSelectAction.SpellCards.addToTop(new MajikkuPosyun());
            CardSelectAction.SpellCards.addToTop(new SutoppuWocchi());
            CardSelectAction.SpellCards.addToTop(new SaSen());
            CardSelectAction.SpellCards.addToTop(new ByoukiHeiyu());
//            CardSelectAction.SpellCards.addToTop(new FusyokuKusuri());
            CardSelectAction.SpellCards.addToTop(new HisouNoKenItem());
            CardSelectAction.SpellCards.addToTop(new IbukiHisyaku());
            CardSelectAction.SpellCards.addToTop(new Namazu());
            CardSelectAction.SpellCards.addToTop(new RyuuSei());
            CardSelectAction.SpellCards.addToTop(new SanbutsuTenmizu());
            CardSelectAction.SpellCards.addToTop(new SeigyoBou());
        }
        if (this.powercount >= 2){
            CardSelectAction.SpellCards.addToTop(new Kamaitachi());
        }
        if (this.powercount >= 3){
            CardSelectAction.SpellCards.addToTop(new KokushiMusou());
            CardSelectAction.SpellCards.addToTop(new HangonChyou(SpellCardsRule.Hangongnum));
        }
        if (this.powercount >= 5){
            CardSelectAction.SpellCards.addToTop(new YomeiIkubaku());
            CardSelectAction.SpellCards.addToTop(new FusekiShinmei());
            CardSelectAction.SpellCards.addToTop(new RokkonShyoujyou());
            CardSelectAction.SpellCards.addToTop(new BurariHaieki());
            CardSelectAction.SpellCards.addToTop(new DaiesanShikkaiKoroshi());
            CardSelectAction.SpellCards.addToTop(new GensouFuubi());
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
                }
                if (Cardid.cardID.equals("DemonsDinnerFork")) {
                    CardSelectAction.SpellCards.addToTop(new HeartBreak());
                }
            }
        }
        if ((this.powercount >= 3) && (this.cardid.size() > 0)){
            for (Iterator localIterator = this.cardid.iterator(); localIterator.hasNext();) {
                AbstractCard Cardid = (AbstractCard) localIterator.next();
                if (Cardid.cardID.equals("Sabishigari")) {
                    CardSelectAction.SpellCards.addToTop(new MirenKamai());
                }
                if (Cardid.cardID.equals("NarrowSpark")) {
                    CardSelectAction.SpellCards.addToTop(new FinalSpark());
                }
                if (Cardid.cardID.equals("SeishiRoten")) {
                    CardSelectAction.SpellCards.addToTop(new EnshinRoten());
                }
                if (Cardid.cardID.equals("KokorosuKi")) {
                    CardSelectAction.SpellCards.addToTop(new SakuraHirame());
                }
                if (Cardid.cardID.equals("KouPou")) {
                    CardSelectAction.SpellCards.addToTop(new TaihouKen());
                }
            }
        }
        if ((this.powercount >= 4) && (this.cardid.size() > 0)){
            for (Iterator localIterator = this.cardid.iterator(); localIterator.hasNext();) {
                AbstractCard Cardid = (AbstractCard) localIterator.next();
                if ((Cardid.cardID.equals("KeiseiJin")) || (Cardid.cardID.equals("KinbakuJin")) || (Cardid.cardID.equals("JyouchiJin"))) {
                    CardSelectAction.SpellCards.addToTop(new HappouKibaku());
                }
                if (Cardid.cardID.equals("RikonNoKama")) {
                    CardSelectAction.SpellCards.addToTop(new SeikonRyuuri());
                }
                if (Cardid.cardID.equals("VampireKiss")) {
                    CardSelectAction.SpellCards.addToTop(new Mireniamu());
                }
                if (Cardid.cardID.equals("MusuNoYume")) {
                    CardSelectAction.SpellCards.addToTop(new MusuNoTegata());
                }
                if (Cardid.cardID.equals("HenyouMirume")) {
                    CardSelectAction.SpellCards.addToTop(new RapurasuNoMa());
                }
                if (Cardid.cardID.equals("MissingPower")) {
                    CardSelectAction.SpellCards.addToTop(new MissingPurplePower());
                }
                if (Cardid.cardID.equals("KoKei")) {
                    CardSelectAction.SpellCards.addToTop(new MoukoNaikei());
                }
                if (Cardid.cardID.equals("MajikuruSanhai")) {
                    CardSelectAction.SpellCards.addToTop(new DeepEcologicalBomb());
                }
                if (Cardid.cardID.equals("DemonsDinnerFork")) {
                    CardSelectAction.SpellCards.addToTop(new SpeartheGungnir());
                }
                if (Cardid.cardID.equals("HisouTensoku")) {
                    CardSelectAction.SpellCards.addToTop(new TenkeiKisyou());
                }
            }
        }
        if ((this.powercount >= 5) && (this.cardid.size() > 0)){
            for (Iterator localIterator = this.cardid.iterator(); localIterator.hasNext();) {
                AbstractCard Cardid = (AbstractCard) localIterator.next();
                if (Cardid.cardID.equals("DamonLordCradle")) {
                    CardSelectAction.SpellCards.addToTop(new DraculaCradle());
                }
                if (Cardid.cardID.equals("NarrowSpark")) {
                    CardSelectAction.SpellCards.addToTop(new EasyMasterSpark());
                }
                if (Cardid.cardID.equals("KouPou")) {
                    CardSelectAction.SpellCards.addToTop(new TaihouTsuigeki());
                }
                if (Cardid.cardID.equals("SenceofElegance")) {
                    CardSelectAction.SpellCards.addToTop(new SenseofCherryBlossom(Cardid.timesUpgraded));
                }
                if (Cardid.cardID.equals("MusouMyousyu")) {
                    CardSelectAction.SpellCards.addToTop(new MusouTensei());
                }
                if (Cardid.cardID.equals("LunaDial")) {
                    CardSelectAction.SpellCards.addToTop(new SakuyaNoSekai());
                }
                if (Cardid.cardID.equals("WumiGaWareru")) {
                    CardSelectAction.SpellCards.addToTop(new MoozeNoKiseki());
                }
                if (Cardid.cardID.equals("TerribleSouvenir")) {
                    CardSelectAction.SpellCards.addToTop(new KyoufuSaimin());
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
                this.cardselect();
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
                this.cardselect();
            }
        }
        this.tickDuration();
    }

    private void cardselect() {
        final boolean randomSpot = true;
        boolean canRep = false;
        boolean given = false;
        for (final AbstractCard c : this.cardsToShuffle) {
            c.unhover();
            ThMod.removedcardids.clear();
            if (this.powercount >= 1) {
                label1:
                for (Iterator Iterator = ThMod.upcardids.iterator(); Iterator.hasNext(); ) {
                    this.testid = (String) Iterator.next();
                    if (c.cardID.equals(this.testid)){
                        label2:
                        for (Iterator Iterator2 = ThMod.fightids.iterator(); Iterator2.hasNext(); ) {
                            this.testid2 = (String) Iterator2.next();
                            for (final AbstractCard cd : AbstractDungeon.player.hand.group) {
                                if (cd.cardID.equals((this.testid2))) {
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
                            AbstractDungeon.actionManager.addToBottom(new PlayerTalkAction(AbstractDungeon.player, "我手中没有可以替换的牌"));
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
                if(!(given)) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(c));
                    SpellCardsRule.selected = true;
                }
            }
            else if(this.powercount == -1)
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, false));
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

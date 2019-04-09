package Thmod.vfx.campfire;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.relics.Omamori;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.ui.DialogWord;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.TextAboveCreatureEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Thmod.Cards.DeriveCards.EasterEgg.SingleWing;
import Thmod.Relics.GoodDreamPillow;
import Thmod.ui.Buttons.DreamButton;
import Thmod.vfx.DreamFogCoverEffect;
import basemod.DevConsole;


public class DreamEventEffect extends AbstractGameEffect
{
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("GoodDreamOption");
    public static final String[] TEXT = uiStrings.TEXT;
    private Color screenColor = AbstractDungeon.fadeColor.cpy();
    private static int selectedOption = -1;
    private float curLineWidth = 0.0F;
    private int curLine = 0;
    private int Screen;
    private float animateTimer = 0.5F;
    private float animateTimer2 = 1F;
    private boolean addButton = false;
    private float wordTimer = 0.5F;
    private ArrayList<DialogWord> words = new ArrayList<>();
    public static ArrayList<DreamButton> optionList = new ArrayList<>();
    private boolean textDone = true;
    public static boolean waitForInput = true;
    private Scanner s;
    private GlyphLayout gl = new GlyphLayout();
    private DialogWord.AppearEffect a_effect;
    private static final float CHAR_SPACING = 8.0F * Settings.scale;
    private static final float LINE_SPACING = 30.0F * Settings.scale;
    private static final float DIALOG_MSG_X = Settings.WIDTH * 0.5F;
    private static final float DIALOG_MSG_Y = 250.0F * Settings.scale;
    private static final float DIALOG_MSG_W = Settings.WIDTH * 0.8F;
    private Color panelColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
    private Color titleColor = new Color(1.0F, 0.835F, 0.39F, 0.0F);
    private Color borderColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
    private Color imgColor = new Color(1.0F, 1.0F, 1.0F, 1.0F);
    private Texture img;
    private ArrayList<Boolean> choose = new ArrayList<>();

    public DreamEventEffect()
    {
        this.duration = 2.5F;
        this.screenColor.a = 0F;
//        AbstractDungeon.overlayMenu.proceedButton.hide();
        this.img = ImageMaster.loadImage("images/events/GoodDream.png" );
        float tmp = 1.0F;
        this.color = new Color();
        this.color.r = tmp;
        this.color.g = (tmp - 0.03F);
        this.color.b = (tmp - 0.07F);
//        updateDreamText(TEXT[0]);
        this.Screen = 1;
        this.choose.clear();
        optionList.clear();
    }

    public void update()
    {
        animateIn();
        if((this.animateTimer2 <= 0.5F) && (this.addButton)){
            this.addDreamOptiom(TEXT[2],TEXT[2]);
            this.addButton = false;
        }
        int i;
        if  (this.animateTimer == 0.0F) {
            for (i = 0; i < optionList.size(); i++)
            {
                (optionList.get(i)).update(optionList.size());
                if (((optionList.get(i)).pressed) && (waitForInput))
                {
                    selectedOption = i;
                    (optionList.get(i)).pressed = false;
                    waitForInput = false;
                }
            }
            if (Settings.lineBreakViaCharacter) {
                bodyTextEffectCN();
            } else {
                bodyTextEffect();
            }
            for (DialogWord w : this.words) {
                w.update();
            }
        }

        if (!waitForInput) {
            buttonEffect(this.getSelectedOption());
        }

        this.color.a = Interpolation.exp5In.apply(0.0F, 1.0F, 1.0F - this.animateTimer / 0.5F);

        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    private void buttonEffect(int buttonPressed){
        boolean canDream = true;
        switch (Screen){
            case 1:
                updateDreamText(TEXT[12]);
                updateDreamOption(0,TEXT[3]);
                updateDreamOption(1,TEXT[4]);
                changeImage(1);
                Screen += 1;
                ((RestRoom)AbstractDungeon.getCurrRoom()).cutFireSound();
                AbstractDungeon.getCurrRoom().playBgmInstantly("兔子.mp3");
                break;
            case 2:
                switch (buttonPressed){
                    case 0:
                        updateDreamText(TEXT[13]);
                        updateDreamOption(0,TEXT[2]);
                        updateDreamOption(1,TEXT[2]);
                        AbstractDungeon.player.maxHealth += 10;
                        AbstractDungeon.effectsQueue.add(new TextAboveCreatureEffect(AbstractDungeon.player.hb.cX - AbstractDungeon.player.animX, AbstractDungeon.player.hb.cY, "+" + Integer.toString(10), Settings.GREEN_TEXT_COLOR));
                        this.Screen = 88;
                        break;
                    case 1:
                        updateDreamText(TEXT[14]);
                        updateDreamOption(0,TEXT[5]);
                        updateDreamOption(1,TEXT[6]);
                        changeImage(5);
                        Screen += 1;
                        break;
                }
                break;
            case 3:
                switch (buttonPressed){
                    case 0:
                        this.choose.add(true);
                        updateDreamText(TEXT[15]);
                        Screen += 1;
                        break;
                    case 1:
                        this.choose.add(false);
                        updateDreamText(TEXT[16]);
                        Screen += 1;
                        break;
                }
                break;
            case 4:
                switch (buttonPressed){
                    case 0:
                        this.choose.add(true);
                        updateDreamText(TEXT[15]);
                        Screen += 1;
                        break;
                    case 1:
                        this.choose.add(false);
                        updateDreamText(TEXT[16]);
                        Screen += 1;
                        break;
                }
                break;
            case 5:
                switch (buttonPressed){
                    case 0:
                        this.choose.add(true);
                        for(int i = 0;i < GoodDreamPillow.dreamCode.size();i++){
                            if(!(this.choose.get(i) == GoodDreamPillow.dreamCode.get(i))){
                                canDream = false;
                                break;
                            }
                        }
                        if(canDream){
                            updateDreamText(TEXT[19]);
                            updateDreamOption(0,TEXT[2]);
                            updateDreamOption(1,TEXT[2]);
                            changeImage(2);
                            CardCrawlGame.music.silenceTempBgmInstantly();
                            AbstractDungeon.getCurrRoom().playBgmInstantly("月人.mp3");
                            UnlockTracker.unlockCard(SingleWing.ID);
                            this.Screen += 1;
                        }
                        else{
                            updateDreamText(TEXT[17]);
                            updateDreamOption(0,TEXT[3]);
                            updateDreamOption(1,TEXT[7]);
                            changeImage(4);
                            this.Screen = 20;
                        }
                        break;
                    case 1:
                        this.choose.add(false);
                        for(int i = 0;i < GoodDreamPillow.dreamCode.size();i++){
                            if(!(this.choose.get(i) == GoodDreamPillow.dreamCode.get(i))){
                                canDream = false;
                                break;
                            }
                        }
                        if(canDream){
                            updateDreamText(TEXT[19]);
                            updateDreamOption(0,TEXT[2]);
                            updateDreamOption(1,TEXT[2]);
                            changeImage(2);
                            CardCrawlGame.music.silenceTempBgmInstantly();
                            AbstractDungeon.getCurrRoom().playBgmInstantly("月人.mp3");
                            UnlockTracker.unlockCard(SingleWing.ID);
                            this.Screen += 1;
                        }
                        else{
                            updateDreamText(TEXT[17]);
                            updateDreamOption(0,TEXT[3]);
                            updateDreamOption(1,TEXT[7]);
                            changeImage(4);
                            this.Screen = 20;
                        }
                        break;
                }
                break;
            case 6:
                updateDreamText(TEXT[20]);
                updateDreamOption(0,TEXT[8]);
                updateDreamOption(1,TEXT[8]);
                this.Screen += 1;
                break;
            case 7:
                updateDreamText(TEXT[21]);
                this.Screen += 1;
                break;
            case 8:
                updateDreamText(TEXT[22]);
                this.Screen += 1;
                break;
            case 9:
                updateDreamText(TEXT[23]);
                updateDreamOption(0,TEXT[9]);
                updateDreamOption(1,TEXT[10]);
                changeImage(3);
                this.Screen += 1;
                break;
            case 10:
                switch (buttonPressed){
                    case 0:
                        boolean hasCurse = false;
                        updateDreamOption(0,TEXT[2]);
                        updateDreamOption(1,TEXT[2]);
                        for(AbstractCard card : AbstractDungeon.player.masterDeck.group){
                            if(card.type == AbstractCard.CardType.CURSE){
                                hasCurse = true;
                                break;
                            }
                        }
                        int roll;
                        if(hasCurse){
                            roll = MathUtils.random(3) + 1;
                        }
                        else {
                            roll = MathUtils.random(2) + 1;
                        }
                        switch (roll){
                            case 1:
                                updateDreamText(TEXT[25]);
                                AbstractDungeon.player.maxHealth += 10;
                                AbstractDungeon.player.heal(AbstractDungeon.player.maxHealth);
                                break;
                            case 2:
                                updateDreamText(TEXT[26]);
                                AbstractDungeon.player.gainGold(321);
                                break;
                            case 3:
                                updateDreamText(TEXT[27]);
                                for(AbstractCard card : AbstractDungeon.player.masterDeck.group){
                                    card.upgrade();
                                }
                                break;
                            case 4:
                                updateDreamText(TEXT[28]);
                                ArrayList<AbstractCard> moving = new ArrayList<>();
                                for(AbstractCard card : AbstractDungeon.player.masterDeck.group){
                                    if(card.type == AbstractCard.CardType.CURSE){
                                        moving.add(card);
                                    }
                                }
                                for(AbstractCard card : moving){
                                    AbstractDungeon.player.masterDeck.removeCard(card);
                                }
                                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, RelicLibrary.getRelic(Omamori.ID).makeCopy());
                                break;
                        }
                        this.Screen = 88;
                        break;
                    case 1:
                        updateDreamText(TEXT[24]);
                        updateDreamOption(0,TEXT[2]);
                        updateDreamOption(1,TEXT[2]);
                        this.Screen = 88;
                        break;
                }
                break;
            case 20:
                switch (buttonPressed){
                    case 0:
                        updateDreamText(TEXT[13]);
                        updateDreamOption(0,TEXT[2]);
                        updateDreamOption(1,TEXT[2]);
                        AbstractDungeon.player.maxHealth += 10;
                        AbstractDungeon.effectsQueue.add(new TextAboveCreatureEffect(AbstractDungeon.player.hb.cX - AbstractDungeon.player.animX, AbstractDungeon.player.hb.cY, "+" + Integer.toString(10), Settings.GREEN_TEXT_COLOR));
                        this.Screen = 88;
                        break;
                    case 1:
                        updateDreamText(TEXT[18]);
                        updateDreamOption(0,TEXT[2]);
                        updateDreamOption(1,TEXT[2]);
                        upgradeCards();
                        this.Screen = 88;
                        break;
                }
                break;
            case 88:
                updateDreamText(TEXT[29]);
                changeImage(5);
                CardCrawlGame.music.silenceTempBgmInstantly();
                this.Screen = 99;
                break;
            case 99:
                for(int i = 0;i < AbstractDungeon.effectList.size();i++){
                    if(AbstractDungeon.effectList.get(i) instanceof DreamFogCoverEffect){
                        DreamFogCoverEffect effect = (DreamFogCoverEffect)AbstractDungeon.effectList.get(i);
                        effect.doneEffect();
                    }
                }
                this.isDone = true;
                if (CampfireUI.hidden) {
                    com.megacrit.cardcrawl.rooms.AbstractRoom.waitTimer = 0F;
                    AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
                    ((RestRoom)AbstractDungeon.getCurrRoom()).cutFireSound();
                }
                Screen += 1;
                break;
        }
    }

    private int getSelectedOption()
    {
        waitForInput = true;
        return selectedOption;
    }

    public void updateDreamText(String text)
    {
        updateDreamText(text, DialogWord.AppearEffect.BUMP_IN);
    }

    public void updateDreamText(String text, DialogWord.AppearEffect ae)
    {
        this.s = new Scanner(text);
        this.words.clear();
        this.textDone = false;
        this.a_effect = ae;
        this.curLineWidth = 0.0F;
        this.curLine = 0;
    }

    public void canAddButton(){
        this.addButton = true;
    }

    public void addDreamOptiom(String text,String text2){
        optionList.add(new DreamButton(optionList.size(), text));
        optionList.add(new DreamButton(optionList.size(), text2));
    }

    public void updateDreamOption(int slot,String text){
        optionList.set(slot,new DreamButton(slot,text));
    }

    public void changeImage(int num){
        switch (num){
            case 1:
                this.img = ImageMaster.loadImage("images/events/GoodDream2.png" );
                break;
            case 2:
                this.img = ImageMaster.loadImage("images/events/GoodDream3.png" );
                break;
            case 3:
                this.img = ImageMaster.loadImage("images/events/GoodDream4.png" );
                break;
            case 4:
                this.img = ImageMaster.loadImage("images/events/GoodDream5.png" );
                break;
            case 5:
                this.img = ImageMaster.loadImage("images/events/GoodDream.png" );
                break;
        }
        this.animateTimer2 = 0.5F;
    }

    private void bodyTextEffectCN()
    {
        this.wordTimer -= Gdx.graphics.getDeltaTime();
        if ((this.wordTimer < 0.0F) && (!this.textDone))
        {
            if (Settings.FAST_MODE) {
                this.wordTimer = 0.005F;
            } else {
                this.wordTimer = 0.02F;
            }
            if (this.s.hasNext())
            {
//                DevConsole.logger.info(s.next());
                String word = this.s.next();
                if (word.equals("NL"))
                {
                    this.curLine += 1;
                    this.curLineWidth = 0.0F;
                    return;
                }
                DialogWord.WordColor color = DialogWord.identifyWordColor(word);
                if (color != DialogWord.WordColor.DEFAULT) {
                    word = word.substring(2, word.length());
                }
                DialogWord.WordEffect effect = DialogWord.identifyWordEffect(word);
                if (effect != DialogWord.WordEffect.NONE) {
                    word = word.substring(1, word.length() - 1);
                }
                for (int i = 0; i < word.length(); i++)
                {
                    String tmp = Character.toString(word.charAt(i));

                    this.gl.setText(FontHelper.eventBodyText, tmp);
                    if (this.curLineWidth + this.gl.width > DIALOG_MSG_W)
                    {
                        this.curLine += 1;
                        this.curLineWidth = this.gl.width;
                    }
                    else
                    {
                        this.curLineWidth += this.gl.width;
                    }
                    this.words.add(new DialogWord(FontHelper.eventBodyText, tmp, this.a_effect, effect, color, DIALOG_MSG_X + this.curLineWidth - this.gl.width, DIALOG_MSG_Y - LINE_SPACING * this.curLine, this.curLine));
//                    (this.words.get(this.words.size() - 1)).dialogFadeOut();
                }
            }
            else
            {
                this.textDone = true;
                this.s.close();
            }
        }
    }

    private void bodyTextEffect()
    {
        this.wordTimer -= Gdx.graphics.getDeltaTime();
        if ((this.wordTimer < 0.0F) && (!this.textDone))
        {
            if (Settings.FAST_MODE) {
                this.wordTimer = 0.005F;
            } else {
                this.wordTimer = 0.02F;
            }
            if (this.s.hasNext())
            {
                String word = this.s.next();
                if (word.equals("NL"))
                {
                    this.curLine += 1;
                    this.curLineWidth = 0.0F;
                    return;
                }
                DialogWord.WordColor color = DialogWord.identifyWordColor(word);
                if (color != DialogWord.WordColor.DEFAULT) {
                    word = word.substring(2, word.length());
                }
                DialogWord.WordEffect effect = DialogWord.identifyWordEffect(word);
                if (effect != DialogWord.WordEffect.NONE) {
                    word = word.substring(1, word.length() - 1);
                }
                this.gl.setText(FontHelper.textOnlyEventBody, word);
                if (this.curLineWidth + this.gl.width > DIALOG_MSG_W)
                {
                    this.curLine += 1;
                    this.curLineWidth = (this.gl.width + CHAR_SPACING);
                }
                else
                {
                    this.curLineWidth += this.gl.width + CHAR_SPACING;
                }
                this.words.add(new DialogWord(FontHelper.textOnlyEventBody, word, this.a_effect, effect, color, DIALOG_MSG_X + this.curLineWidth - this.gl.width, DIALOG_MSG_Y - LINE_SPACING * this.curLine, this.curLine));
//                (this.words.get(this.words.size() - 1)).dialogFadeOut();
            }
            else
            {
                this.textDone = true;
                this.s.close();
            }
        }
    }

    private void upgradeCards() {
        AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
        ArrayList<AbstractCard> upgradableCards = new ArrayList<>();
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.canUpgrade()) {
                upgradableCards.add(c);
            }
        }
        Collections.shuffle(upgradableCards, new java.util.Random(AbstractDungeon.miscRng.randomLong()));
        if (!upgradableCards.isEmpty()) {
            if (upgradableCards.size() == 1) {
                (upgradableCards.get(0)).upgrade();
                AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(0));
                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect((upgradableCards.get(0)).makeStatEquivalentCopy()));
            }
            else if(upgradableCards.size() == 2) {
                (upgradableCards.get(0)).upgrade();
                (upgradableCards.get(1)).upgrade();
                AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(0));
                AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(1));
                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect((upgradableCards.get(0)).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F - 190.0F * Settings.scale, Settings.HEIGHT / 2.0F));

                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect((upgradableCards.get(1)).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F + 190.0F * Settings.scale, Settings.HEIGHT / 2.0F));
            }
            else {
                (upgradableCards.get(0)).upgrade();
                (upgradableCards.get(1)).upgrade();
                (upgradableCards.get(2)).upgrade();
                AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(0));
                AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(1));
                AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(2));
                AbstractDungeon.effectsQueue.add(new ShowCardBrieflyEffect((upgradableCards.get(0)).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F - 380.0F * Settings.scale, Settings.HEIGHT / 2.0F));

                AbstractDungeon.effectsQueue.add(new ShowCardBrieflyEffect((upgradableCards.get(1)).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));

                AbstractDungeon.effectsQueue.add(new ShowCardBrieflyEffect((upgradableCards.get(2)).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F + 380.0F * Settings.scale, Settings.HEIGHT / 2.0F));
            }
        }
    }

    private void animateIn()
    {
            this.animateTimer2 -= Gdx.graphics.getDeltaTime();
            if(this.animateTimer2 > 0.5F)
                this.animateTimer = 0.5F;
            else
                this.animateTimer = this.animateTimer2;
            if (this.animateTimer < 0.0F) {
                this.animateTimer = 0.0F;
            }
            this.panelColor.a = MathHelper.slowColorLerpSnap(this.panelColor.a, 1.0F);
            if (this.panelColor.a > 0.8F)
            {
                this.titleColor.a = MathHelper.slowColorLerpSnap(this.titleColor.a, 1.0F);
                this.borderColor.a = this.titleColor.a;
                if (this.borderColor.a > 0.8F) {
                    this.imgColor.a = MathHelper.slowColorLerpSnap(this.imgColor.a, 1.0F);
                }
            }
    }

    public void render(SpriteBatch sb)
    {
        if (this.img != null)
        {
            sb.setColor(this.color);
            sb.draw(this.img, 460.0F * Settings.scale - 300.0F, Settings.EVENT_Y - 300.0F + 166.0F * Settings.scale, 300.0F, 300.0F, 600.0F, 600.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 600, 600, false, false);

//            sb.draw(ImageMaster.EVENT_IMG_FRAME, 460.0F * Settings.scale - 305.0F, Settings.EVENT_Y - 305.0F + 16.0F * Settings.scale, 305.0F, 305.0F, 610.0F, 610.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 610, 610, false, false);
        }
        for (DialogWord w : this.words) {
            w.render(sb, Settings.HEIGHT - 525.0F * Settings.scale);
//            w.render(sb);
        }
        for (DreamButton b : optionList) {
            b.render(sb);
        }
    }

    public void dispose(){}
}

package Thmod.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Bone;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.Keyword;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import Thmod.Cards.Curses.Lonely;
import Thmod.Cards.Dash_Komeiji;
import Thmod.Cards.DeriveCards.EasterEgg.Scarlet;
import Thmod.Cards.ScarletCard.Absorbed;
import Thmod.Cards.ScarletCard.AbstractRemiriaFate;
import Thmod.Cards.ScarletCard.BloodCleaner;
import Thmod.Cards.ScarletCard.BloodCollect;
import Thmod.Cards.ScarletCard.BloodySuit;
import Thmod.Cards.ScarletCard.BodyStrengthen;
import Thmod.Cards.ScarletCard.Cripple;
import Thmod.Cards.ScarletCard.DamonLordCradle_Remiria;
import Thmod.Cards.ScarletCard.Defend_Remirira;
import Thmod.Cards.ScarletCard.DemonsDinnerFork_Remiria;
import Thmod.Cards.ScarletCard.DoubleEdge;
import Thmod.Cards.ScarletCard.FastHealing;
import Thmod.Cards.ScarletCard.Feint;
import Thmod.Cards.ScarletCard.FierceSweep;
import Thmod.Cards.ScarletCard.RocketKickup;
import Thmod.Cards.ScarletCard.Scratch;
import Thmod.Cards.ScarletCard.ServantFlier;
import Thmod.Cards.ScarletCard.StigmaNizer;
import Thmod.Cards.ScarletCard.Strike_Remiria;
import Thmod.Cards.ScarletCard.TricksterDevil;
import Thmod.Cards.ScarletCard.Warpath;
import Thmod.Cards.ScarletCard.rareCards.AllTheWorldInNightmare;
import Thmod.Cards.ScarletCard.rareCards.BadLadyScramble;
import Thmod.Cards.ScarletCard.rareCards.BloodCoagulation;
import Thmod.Cards.ScarletCard.rareCards.BombardNight;
import Thmod.Cards.ScarletCard.rareCards.Coercion;
import Thmod.Cards.ScarletCard.rareCards.FateConcerto;
import Thmod.Cards.ScarletCard.rareCards.FateSonata;
import Thmod.Cards.ScarletCard.rareCards.FitfulNightmare;
import Thmod.Cards.ScarletCard.rareCards.HeartBreak_Remiria;
import Thmod.Cards.ScarletCard.rareCards.MillenniumVampire;
import Thmod.Cards.ScarletCard.rareCards.MiserableFate_Remiria;
import Thmod.Cards.ScarletCard.rareCards.QueenOfMidnight;
import Thmod.Cards.ScarletCard.rareCards.Reaper_Remiria;
import Thmod.Cards.ScarletCard.rareCards.RedMagic;
import Thmod.Cards.ScarletCard.rareCards.RedtheNightlessCastle;
import Thmod.Cards.ScarletCard.rareCards.RemiliaStalker;
import Thmod.Cards.ScarletCard.rareCards.RemiliaStretch_Remiria;
import Thmod.Cards.ScarletCard.rareCards.ScarletDestiny;
import Thmod.Cards.ScarletCard.rareCards.Septette;
import Thmod.Cards.ScarletCard.rareCards.StarOfDavid;
import Thmod.Cards.ScarletCard.rewardCards.BloodyCatastrophe;
import Thmod.Cards.ScarletCard.rewardCards.BloodyLaserofSeventeenArticles;
import Thmod.Cards.ScarletCard.rewardCards.SuperhumanBloodyKnife;
import Thmod.Cards.ScarletCard.uncommonCards.BathedInBlood;
import Thmod.Cards.ScarletCard.uncommonCards.BloodPact;
import Thmod.Cards.ScarletCard.uncommonCards.BloodSurge;
import Thmod.Cards.ScarletCard.uncommonCards.BloodyMagicSquare;
import Thmod.Cards.ScarletCard.uncommonCards.ChainGang;
import Thmod.Cards.ScarletCard.uncommonCards.CombatMeter;
import Thmod.Cards.ScarletCard.uncommonCards.CruelSlaughter;
import Thmod.Cards.ScarletCard.uncommonCards.Culling;
import Thmod.Cards.ScarletCard.uncommonCards.CurseofVladTepes;
import Thmod.Cards.ScarletCard.uncommonCards.DemonLordWalk;
import Thmod.Cards.ScarletCard.uncommonCards.Exsanguinate;
import Thmod.Cards.ScarletCard.uncommonCards.FateCollect;
import Thmod.Cards.ScarletCard.uncommonCards.FateEnsemble;
import Thmod.Cards.ScarletCard.uncommonCards.FateMeister;
import Thmod.Cards.ScarletCard.uncommonCards.Impregnable;
import Thmod.Cards.ScarletCard.uncommonCards.LifeBreak;
import Thmod.Cards.ScarletCard.uncommonCards.NaturallyHealing;
import Thmod.Cards.ScarletCard.uncommonCards.NightDance;
import Thmod.Cards.ScarletCard.uncommonCards.Offensive;
import Thmod.Cards.ScarletCard.uncommonCards.PainBurst;
import Thmod.Cards.ScarletCard.uncommonCards.Paradox;
import Thmod.Cards.ScarletCard.uncommonCards.PartStrengthen;
import Thmod.Cards.ScarletCard.uncommonCards.Rewrite;
import Thmod.Cards.ScarletCard.uncommonCards.ScarletNetherworld;
import Thmod.Cards.ScarletCard.uncommonCards.SealingFear;
import Thmod.Cards.ScarletCard.uncommonCards.SeparateFleshBlood;
import Thmod.Cards.ScarletCard.uncommonCards.Tearing;
import Thmod.Cards.ScarletCard.uncommonCards.Tepes;
import Thmod.Cards.ScarletCard.uncommonCards.ThePrice;
import Thmod.Cards.ScarletCard.uncommonCards.ThirstForBlood;
import Thmod.Cards.ScarletCard.uncommonCards.TrickFate;
import Thmod.Cards.ScarletCard.uncommonCards.VampireClaw;
import Thmod.Cards.ScarletCard.uncommonCards.VampireKiss_Remiria;
import Thmod.Cards.ScarletCard.uncommonCards.VampirishNight;
import Thmod.Cards.ScarletCard.uncommonCards.YoungDemonLord;
import Thmod.Monsters.Remiria;
import Thmod.Patches.AbstractCardEnum;
import Thmod.Patches.CharacterEnum;
import Thmod.Power.remiria.StarOfDavidPower;
import Thmod.Relics.remiria.RedTeaWithBlood;
import Thmod.Relics.remiria.SpecialStopwatch;
import Thmod.Relics.remiria.SpellCardsRuleRemi;
import Thmod.Relics.remiria.TepesBloodVial;
import Thmod.Relics.remiria.ThirstyCrossSpe;
import Thmod.Relics.remiria.UnsealingCross;
import Thmod.ThMod;
import Thmod.vfx.RemiriaFireParticle;
import Thmod.vfx.StartPetalEffect;
import basemod.BaseMod;
import basemod.abstracts.CustomPlayer;

public class RemiriaScarlet extends CustomPlayer {
    public static final int ENERGY_PER_TURN = 3;
    public static final String[] orbTextures;
    public static final String orbVfx;
    public boolean isShopAnimation;
    public boolean isLordAnimation;
    private ArrayList<Bone> fires = new ArrayList<>();
    private float fireTimer = 0.0F;
    public ArrayList<AbstractCard> soulGroupCopy = new ArrayList<>();
    public ArrayList<AbstractCard> masterGroupCopy = new ArrayList<>();
    public int bloodCounter;

    public RemiriaScarlet(final String name) {
        super(name, CharacterEnum.RemiriaScarlet, orbTextures, orbVfx, (String) null, null);

        this.initializeClass(null, "images/characters/remiria/shoulder.png", "images/characters/remiria/shoulder2.png", "images/characters/remiria/corpse.png", getLoadout(), 0.0F, 0.0F, 450.0F, 550.0F, new EnergyManager(ENERGY_PER_TURN));
        loadAnimation("images/characters/remiria/normal/normal.atlas", "images/characters/remiria/normal/normal.json", 1.1F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "normal", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.stateData.setMix("hit", "normal", 0.2F);
        this.state.setTimeScale(1.0F);
        //this.flipHorizontal = true;

        //this.drawY += 250.0f * Settings.scale;
        //this.animY += 330.0f * Settings.scale;

        this.dialogX = this.drawX + 0.0f * Settings.scale;
        this.dialogY = this.drawY + 240.0f * Settings.scale;
        //this.changedPosition = true;
    }

    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Strike_Remiria.ID);
        retVal.add(Strike_Remiria.ID);
        retVal.add(Strike_Remiria.ID);
        retVal.add(Strike_Remiria.ID);
        retVal.add(Defend_Remirira.ID);
        retVal.add(Defend_Remirira.ID);
        retVal.add(Defend_Remirira.ID);
        retVal.add(Defend_Remirira.ID);
        retVal.add(Absorbed.ID);
        retVal.add(Scratch.ID);
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(ThirstyCrossSpe.ID);
        UnlockTracker.markRelicAsSeen(ThirstyCrossSpe.ID);
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout(){
        String title;
        String flavor;

        if (ThMod.AllengOpen)
        {
            title = "Remiria";
            flavor = "...";
        }
        else
        {
            title = "蕾米莉亚";
            flavor = "...";
        }
        return new CharSelectInfo(title, flavor,
                100, 100, 0, 160, 5,
                this, getStartingRelics(), getStartingDeck(), false);
    }

    public String getLocalizedCharacterName()
    {
        String title;
        if (ThMod.AllengOpen)
        {
            title = "Remiria";
        }
        else
        {
            title = "蕾米莉亚";
        }
        return title;
    }

    public AbstractCard.CardColor getCardColor()
    {
        return AbstractCardEnum.Remiria;
    }

    public Color getCardTrailColor()
    {
        return ThMod.Remiria;
    }


    public AbstractPlayer newInstance()
    {
        return new RemiriaScarlet(this.name);
    }

    public int getAscensionMaxHPLoss()
    {
        return 0;
    }

    public AbstractCard getStartCardForEvent()
    {
        return new Scratch();
    }

    public String getTitle(AbstractPlayer.PlayerClass playerClass)
    {
        String title;
        if (ThMod.AllengOpen)
        {
            title = "Remiria";
        }
        else
        {
            title = "蕾米莉亚";
        }
        return title;
    }

    public void doCharSelectScreenSelectEffect()
    {
        CardCrawlGame.sound.playA("select00", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
    }

    public String getCustomModeCharacterButtonSoundKey()
    {
        return "select00";
    }

    public BitmapFont getEnergyNumFont()
    {
        return FontHelper.energyNumFontBlue;
    }

    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect()
    {
        return new AbstractGameAction.AttackEffect[] { AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL };
    }

    public String getVampireText()
    {
        return com.megacrit.cardcrawl.events.city.Vampires.DESCRIPTIONS[1];
    }

    public Color getCardRenderColor()
    {
        return ThMod.Remiria;
    }

    public void updateOrb(int orbCount)
    {
        this.energyOrb.updateOrb(orbCount);
    }

//    public TextureAtlas.AtlasRegion getOrb()
//    {
//        return ThMod.orbAtlas.findRegion("komeiji");
//    }

    public Color getSlashAttackColor()
    {
        return ThMod.Remiria;
    }

    public String getSpireHeartText()
    {
        String Description;
        if(Settings.language == Settings.GameLanguage.ENG)
            Description = "……";
        else
            Description = "……";
        return Description;
    }

    public void enterRoom(){
        if(AbstractDungeon.getCurrRoom() instanceof ShopRoom){
            this.isShopAnimation = true;
            this.fires.clear();
            loadAnimation("images/characters/remiria/start/start.atlas", "images/characters/remiria/start/start.json", 1.1F);
            AnimationState.TrackEntry e = this.state.setAnimation(0, "normal", true);
            e.setTime(e.getEndTime() * MathUtils.random());
            this.state.setTimeScale(1.0F);
        }
        else if((this.isShopAnimation) || (this.isLordAnimation)) {
            this.isShopAnimation = false;
            this.isLordAnimation = false;
            this.fires.clear();
            loadAnimation("images/characters/remiria/normal/normal.atlas", "images/characters/remiria/normal/normal.json", 1.1F);
            AnimationState.TrackEntry e = this.state.setAnimation(0, "normal", true);
            e.setTime(e.getEndTime() * MathUtils.random());
            this.stateData.setMix("hit", "normal", 0.2F);
            this.state.setTimeScale(1.0F);
        }
    }

    @Override
    public void applyStartOfCombatLogic() {
        super.applyStartOfCombatLogic();
        for(AbstractCard card : AbstractDungeon.player.drawPile.group) {
            if(!(card instanceof TrickFate)) {
                this.soulGroupCopy.add(card);
            }
        }
        for(AbstractCard card : AbstractDungeon.player.hand.group) {
            if(!(card instanceof TrickFate)) {
                this.soulGroupCopy.add(card);
            }
        }
        for(AbstractCard card : AbstractDungeon.player.discardPile.group) {
            if(!(card instanceof TrickFate)) {
                this.soulGroupCopy.add(card);
            }
        }
        this.bloodCounter = 0;
        this.addGroupCopy();
    }

    public void addGroupCopy(){
        this.masterGroupCopy.addAll(this.soulGroupCopy);
    }

    public void clearGroupCopy(){
        this.masterGroupCopy.clear();
    }

    @Override
    public void applyStartOfTurnPowers() {
        super.applyStartOfTurnPowers();
        if(this.masterGroupCopy.size() == 0){
            addGroupCopy();
        }
    }

    @Override
    public void onVictory() {
        super.onVictory();
//        if(this.bloodCounter > 0){
//            AbstractDungeon.player.heal(this.bloodCounter / 2);
//            this.bloodCounter = 0;
//        }
    }

    public void changeState(String key){
        this.isShopAnimation = false;
        this.isLordAnimation = false;
        switch (key) {
            case "NORMAL":
                this.fires.clear();
                loadAnimation("images/characters/remiria/normal/normal.atlas", "images/characters/remiria/normal/normal.json", 1.1F);
                AnimationState.TrackEntry e = this.state.setAnimation(0, "normal", true);
                e.setTime(e.getEndTime() * MathUtils.random());
                this.stateData.setMix("hit", "normal", 0.2F);
                this.healthBarUpdatedEvent();
                break;
            case "GUNGNIR":
                this.isLordAnimation = true;
                loadAnimation("images/characters/remiria/gungnir/gungnir.atlas", "images/characters/remiria/gungnir/gungnir.json", 1.1F);
                AnimationState.TrackEntry e2 = this.state.setAnimation(0, "normal", true);
                e2.setTime(e2.getEndTime() * MathUtils.random());
                this.stateData.setMix("hit", "normal", 0.2F);
                this.stateData.setMix("normal", "normalAttack", 0.2F);
                this.stateData.setMix("normal", "chainAttack", 0.2F);
                this.stateData.setMix("normal", "heavyAttack", 0.2F);
                this.healthBarUpdatedEvent();
                this.fires.add(this.skeleton.findBone("fire"));
                this.fires.add(this.skeleton.findBone("fire2"));
                this.fires.add(this.skeleton.findBone("fire3"));
                this.fires.add(this.skeleton.findBone("fire4"));
                this.fires.add(this.skeleton.findBone("fire5"));
                this.fires.add(this.skeleton.findBone("fire6"));
                this.fires.add(this.skeleton.findBone("fire7"));
                this.fires.add(this.skeleton.findBone("fire8"));
                this.fires.add(this.skeleton.findBone("fire9"));
                this.fires.add(this.skeleton.findBone("fire10"));
                this.fires.add(this.skeleton.findBone("fire11"));
                this.fires.add(this.skeleton.findBone("fire12"));
                break;
            case "N_ATTACK":
                this.state.setAnimation(0, "normalAttack", false);
                this.state.setTimeScale(1F);
                this.state.addAnimation(0, "normal", true, 0.0F);
                break;
            case "C_ATTACK":
                if(this.fires.size() != 0) {
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(new ClawEffect(this.skeleton
                            .getX() + this.fires.get(0).getWorldX(), this.skeleton
                            .getY() + this.fires.get(0).getWorldY(), Color.SCARLET, Color.ORANGE), 0.1F));
                }
                this.state.setAnimation(0, "chainAttack", false);
                this.state.setTimeScale(1F);
                this.state.addAnimation(0, "normal", true, 0.0F);
                break;
            case "H_ATTACK":
                this.state.setAnimation(0, "heavyAttack", false);
                this.state.setTimeScale(1F);
                this.state.addAnimation(0, "normal", true, 0.0F);
                break;
            case "SHOP":
                this.isShopAnimation = true;
                this.fires.clear();
                loadAnimation("images/characters/remiria/start/start.atlas", "images/characters/remiria/start/start.json", 1.1F);
                AnimationState.TrackEntry e3 = this.state.setAnimation(0, "normal", true);
                e3.setTime(e3.getEndTime() * MathUtils.random());
                this.stateData.setMix("hit", "normal", 0.2F);
                this.state.setTimeScale(1.0F);
                break;
        }
    }

    public static void addCards(){
        BaseMod.addCard(new Absorbed());
        BaseMod.addCard(new BloodCleaner());
        BaseMod.addCard(new BloodCollect());
        BaseMod.addCard(new BloodySuit());
        BaseMod.addCard(new BodyStrengthen());
        BaseMod.addCard(new Cripple());
        BaseMod.addCard(new DamonLordCradle_Remiria());
        BaseMod.addCard(new Defend_Remirira());
        BaseMod.addCard(new DemonsDinnerFork_Remiria());
        BaseMod.addCard(new DoubleEdge());
        BaseMod.addCard(new FastHealing());
        BaseMod.addCard(new Feint());
        BaseMod.addCard(new FierceSweep());
        BaseMod.addCard(new RocketKickup());
        BaseMod.addCard(new Scratch());
        BaseMod.addCard(new ServantFlier());
        BaseMod.addCard(new StigmaNizer());
        BaseMod.addCard(new Strike_Remiria());
        BaseMod.addCard(new TricksterDevil());
        BaseMod.addCard(new Warpath());

        BaseMod.addCard(new BathedInBlood());
        BaseMod.addCard(new BloodPact());
        BaseMod.addCard(new BloodSurge());
        BaseMod.addCard(new BloodyMagicSquare());
        BaseMod.addCard(new ChainGang());
        BaseMod.addCard(new CombatMeter());
        BaseMod.addCard(new CruelSlaughter());
        BaseMod.addCard(new Culling());
        BaseMod.addCard(new CurseofVladTepes());
        BaseMod.addCard(new DemonLordWalk());
        BaseMod.addCard(new Exsanguinate());
        BaseMod.addCard(new FateCollect());
        BaseMod.addCard(new FateEnsemble());
        BaseMod.addCard(new FateMeister());
        BaseMod.addCard(new Impregnable());
        BaseMod.addCard(new LifeBreak());
        BaseMod.addCard(new NaturallyHealing());
        BaseMod.addCard(new NightDance());
        BaseMod.addCard(new Offensive());
        BaseMod.addCard(new PainBurst());
        BaseMod.addCard(new Paradox());
        BaseMod.addCard(new PartStrengthen());
        BaseMod.addCard(new Rewrite());
        BaseMod.addCard(new ScarletNetherworld());
        BaseMod.addCard(new SealingFear());
        BaseMod.addCard(new SeparateFleshBlood());
        BaseMod.addCard(new Tearing());
        BaseMod.addCard(new Tepes());
        BaseMod.addCard(new ThePrice());
        BaseMod.addCard(new ThirstForBlood());
        BaseMod.addCard(new TrickFate());
        BaseMod.addCard(new VampireClaw());
        BaseMod.addCard(new VampireKiss_Remiria());
        BaseMod.addCard(new VampirishNight());
        BaseMod.addCard(new YoungDemonLord());

        BaseMod.addCard(new AllTheWorldInNightmare());
        BaseMod.addCard(new BadLadyScramble());
        BaseMod.addCard(new BloodCoagulation());
        BaseMod.addCard(new BombardNight());
        BaseMod.addCard(new Coercion());
        BaseMod.addCard(new FateConcerto());
        BaseMod.addCard(new FateSonata());
        BaseMod.addCard(new FitfulNightmare());
        BaseMod.addCard(new HeartBreak_Remiria());
        BaseMod.addCard(new MillenniumVampire());
        BaseMod.addCard(new MiserableFate_Remiria());
        BaseMod.addCard(new QueenOfMidnight());
        BaseMod.addCard(new Reaper_Remiria());
        BaseMod.addCard(new RedMagic());
        BaseMod.addCard(new RedtheNightlessCastle());
        BaseMod.addCard(new RemiliaStalker());
        BaseMod.addCard(new RemiliaStretch_Remiria());
        BaseMod.addCard(new ScarletDestiny());
        BaseMod.addCard(new Septette());
        BaseMod.addCard(new StarOfDavid());
        BaseMod.addCard(new Warpath());

        BaseMod.addCard(new Lonely());

        BaseMod.addCard(new BloodyCatastrophe());
        BaseMod.addCard(new SuperhumanBloodyKnife());
        BaseMod.addCard(new BloodyLaserofSeventeenArticles());
    }

    public static void addRelics(){
        BaseMod.addRelicToCustomPool(new ThirstyCrossSpe(), AbstractCardEnum.Remiria);
        BaseMod.addRelicToCustomPool(new TepesBloodVial(), AbstractCardEnum.Remiria);
        BaseMod.addRelicToCustomPool(new SpellCardsRuleRemi(), AbstractCardEnum.Remiria);
        BaseMod.addRelicToCustomPool(new RedTeaWithBlood(), AbstractCardEnum.Remiria);
        BaseMod.addRelicToCustomPool(new UnsealingCross(), AbstractCardEnum.Remiria);
    }

    public void update()
    {
        super.update();
        if ((!this.isDying) && (this.fires.size() > 0)) {
            this.fireTimer -= Gdx.graphics.getDeltaTime();
            if (this.fireTimer < 0.0F) {
                this.fireTimer = 0.05F;
                for (Bone fire : this.fires) {
                    AbstractDungeon.effectList.add(new RemiriaFireParticle(this.skeleton
                            .getX() + fire.getWorldX(), this.skeleton.getY() + fire.getWorldY()));
                }
            }
        }
    }

    @Override
    public void draw(int numCards) {
        super.draw(numCards);
        for(AbstractCard card : this.hand.group){
            if(card instanceof Lonely){
                ((Lonely) card).drawCard();
            }
        }
    }

    @Override
    public void updateInput() {
        super.updateInput();
        if ((!AbstractDungeon.actionManager.turnHasEnded))
        {
            updateRightClickCard();
        }
    }

    private void  updateRightClickCard(){
        if (((InputHelper.justClickedRight) || (InputActionSet.confirm.isJustPressed()) || (CInputActionSet.select.isJustPressed())) && (this.hoveredCard != null)
                && (AbstractDungeon.player.hasPower(StarOfDavidPower.POWER_ID)) && (this.hoveredCard instanceof AbstractRemiriaFate)) {
            InputHelper.justClickedRight = false;
            ((AbstractRemiriaFate) this.hoveredCard).rightUse();
        }
    }

    public static String remiriaCardImage(final String id) {
        return "images/cards/remiria/" + id + ".png";
    }

    public static String remiriaRelicImage(final String id) {
        return "images/relics/remiria/" + id + ".png";
    }

    public static String remiriaRelicOutlineImage(final String id) {
        return "images/relics/remiria/outline/" + id + ".png";
    }

    static {
        orbTextures = new String[] {
                "images/ui/topPanel/Remiria/layer1.png","images/ui/topPanel/Remiria/layer2.png","images/ui/topPanel/Remiria/layer3.png","images/ui/topPanel/Remiria/layer4.png","images/ui/topPanel/Remiria/layer5.png","images/ui/topPanel/Remiria/layer6.png",
                "images/ui/topPanel/Remiria/layer1d.png","images/ui/topPanel/Remiria/layer2d.png","images/ui/topPanel/Remiria/layer3d.png","images/ui/topPanel/Remiria/layer4d.png","images/ui/topPanel/Remiria/layer5d.png",
        };
        orbVfx = "images/ui/topPanel/energyRemiriaVFX.png";
    }
}

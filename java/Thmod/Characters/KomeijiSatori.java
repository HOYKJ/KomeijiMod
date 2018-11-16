package Thmod.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;

import Thmod.Cards.BlessingCards.BlessingOfTime;
import Thmod.Cards.Dash_Komeiji;
import Thmod.Cards.Defend_Komeiji;
import Thmod.Cards.Strike_Komeiji;
import Thmod.Patches.AbstractCardEnum;
import Thmod.Patches.CharacterEnum;
import Thmod.Relics.BookofPenglai;
import Thmod.ThMod;
import basemod.abstracts.CustomPlayer;

import static Thmod.ThMod.blessingOfTime;
import static Thmod.ThMod.古明地觉;

public class KomeijiSatori extends CustomPlayer {
    public static final int ENERGY_PER_TURN = 3;
    public static final String[] orbTextures;
    public static final String orbVfx;
    public static TextureAtlas.AtlasRegion orb_Komeiji;

    public KomeijiSatori(final String name) {
        super(name, CharacterEnum.KomeijiSatori, orbTextures, orbVfx, (String) null, null);

        this.dialogX = this.drawX + 0.0f * Settings.scale;
        this.dialogY = this.drawY + 240.0f * Settings.scale;
        ThMod.orbAtlas = new TextureAtlas(Gdx.files.internal("images/orbs/Komeiji/orb.atlas"));
        AbstractCard.orb_red = ThMod.orbAtlas.findRegion("komeiji");
        ImageMaster.RED_ORB = ThMod.orbAtlas.findRegion("komeiji");

        this.initializeClass(null, "images/characters/komeiji/shoulder.png", "images/characters/komeiji/shoulder2.png", "images/characters/komeiji/corpse.png", getLoadout(), 0.0F, 5.0F, 240.0F, 300.0F, new EnergyManager(ENERGY_PER_TURN));
        loadAnimation("images/characters/komeiji/Komeiji.atlas", "images/characters/komeiji/Komeiji.json", 1.0F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "Normal", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        e.setTimeScale(1.0F);
    }

    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Strike_Komeiji.ID);
        retVal.add(Strike_Komeiji.ID);
        retVal.add(Strike_Komeiji.ID);
        retVal.add(Strike_Komeiji.ID);
        retVal.add(Defend_Komeiji.ID);
        retVal.add(Defend_Komeiji.ID);
        retVal.add(Defend_Komeiji.ID);
        retVal.add(Defend_Komeiji.ID);
        retVal.add(Dash_Komeiji.ID);
        if (blessingOfTime > 0) {
            if (blessingOfTime < 3) {
                retVal.add(BlessingOfTime.ID);
            }
        }
//        retVal.add(NingyouFukuhei.ID);
//        retVal.add(NingyouShinki.ID);
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("KomeijisEye");
        UnlockTracker.markRelicAsSeen("KomeijisEye");
        retVal.add("SpellCardsRule");
        UnlockTracker.markRelicAsSeen("SpellCardsRule");
        if (ThMod.blessingOfDetermination == 2){
            retVal.add(BookofPenglai.ID);
        }
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout(){
        int maxOrbs;
        String title;
        String flavor;
        if(ThMod.AliceOpen)
            maxOrbs = 5;
        else
            maxOrbs = 4;

        if (ThMod.AllengOpen)
        {
            title = "Komeiji Satori";
            flavor = "When Satori wake from a coma, she find she is in a unbeknown spire. NL what she own, it's only a Spell Card Rule Manual, and some scattered spell card in mind...";
        }
        else
        {
            title = "古明地 觉";
            flavor = "觉从一次昏迷中醒来后,发现自己处在一个不知名的高塔中, NL 她所有的,只有一本符卡规则,和记忆中零散的符卡...";
        }
        return new CharSelectInfo(title, flavor,
                50, 50, maxOrbs, 99, 4,
                this, getStartingRelics(), getStartingDeck(), false);
    }

    public String getLocalizedCharacterName()
    {
        String title;
        if (ThMod.AllengOpen)
        {
            title = "Komeiji Satori";
        }
        else
        {
            title = "古明地 觉";
        }
        return title;
    }

    public AbstractCard.CardColor getCardColor()
    {
        return AbstractCardEnum.古明地觉;
    }

    public Color getCardTrailColor()
    {
        return 古明地觉;
    }


    public AbstractPlayer newInstance()
    {
        return new KomeijiSatori(this.name);
    }

    public int getAscensionMaxHPLoss()
    {
        return 0;
    }

    public AbstractCard getStartCardForEvent()
    {
        return new Dash_Komeiji();
    }

    public String getTitle(AbstractPlayer.PlayerClass playerClass)
    {
        String title;
        if (ThMod.AllengOpen)
        {
            title = "Komeiji Satori";
        }
        else
        {
            title = "古明地 觉";
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
        return ThMod.古明地觉;
    }

    public void updateOrb(int orbCount)
    {
        this.energyOrb.updateOrb(orbCount);
    }

    public TextureAtlas.AtlasRegion getOrb()
    {
        return AbstractCard.orb_red;
    }

    public Color getSlashAttackColor()
    {
        return ThMod.古明地觉;
    }

    public String getSpireHeartText()
    {
        String Description;
        if(Settings.language == Settings.GameLanguage.ENG)
            Description = "";
        else
            Description = "NL 你将你的精神紧绷到极限……";
        return Description;
    }

    static {
        orbTextures = new String[] {
                "images/ui/topPanel/Komeiji/layer1.png","images/ui/topPanel/Komeiji/layer2.png","images/ui/topPanel/Komeiji/layer3.png","images/ui/topPanel/Komeiji/layer4.png","images/ui/topPanel/Komeiji/layer5.png","images/ui/topPanel/Komeiji/layer6.png",
                "images/ui/topPanel/Komeiji/layer1d.png","images/ui/topPanel/Komeiji/layer2d.png","images/ui/topPanel/Komeiji/layer3d.png","images/ui/topPanel/Komeiji/layer4d.png","images/ui/topPanel/Komeiji/layer5d.png",
        };
        orbVfx = "images/ui/topPanel/energyKomeijiVFX.png";
    }
}


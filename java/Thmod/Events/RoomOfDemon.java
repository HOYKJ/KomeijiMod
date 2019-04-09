package Thmod.Events;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.scene.LightFlareMEffect;

import java.util.ArrayList;
import java.util.Iterator;

import Thmod.Characters.RemiriaScarlet;
import Thmod.Events.Remiria.RoomOfDemonRemi;
import Thmod.Relics.SpellCardsRule;
import Thmod.Relics.ThirstyCross;
import Thmod.ThMod;
import Thmod.vfx.RedFogCoverEffect;
import Thmod.vfx.scene.CeremonialTorchEffect;
import Thmod.vfx.scene.TorchUnactivated;

public class RoomOfDemon extends AbstractEvent {
    public static final String ID = "RoomOfDemon";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("RoomOfDemon");
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String INTRO_MSG = DESCRIPTIONS[0];
    private float x = 800.0F * Settings.scale;
    private float y = AbstractDungeon.floorY;
    public static boolean enemyAppear = false;
    private CurScreen screen = CurScreen.INTRO;
    private ArrayList<CeremonialTorchEffect> torches = new ArrayList<>();
    private boolean battleStart = false;
    private boolean raedDone = false;
    private boolean extractDone = false;
    public static boolean torchDone = true;
    public static int torchNum = 0;
    private Texture adventurerImg;
    private Texture adventurerImg2;
    private Texture adventurerImg3;


    private enum CurScreen
    {
        INTRO,  READ,  CROSS,ESCAPE;

        CurScreen() {}
    }

    @Override
    public void onEnterRoom() {
        super.onEnterRoom();
        if((AbstractDungeon.player != null) && (AbstractDungeon.player instanceof RemiriaScarlet)){
            RoomEventDialog.optionList.clear();
            ThMod.logger.info("Replace Event");
            AbstractDungeon.getCurrRoom().event = new RoomOfDemonRemi();
            AbstractDungeon.getCurrRoom().event.onEnterRoom();
        }
    }

    public void update() {
        super.update();
        updateTorches();

        if (!RoomEventDialog.waitForInput) {
            buttonEffect(this.roomEventText.getSelectedOption());
        }

        if(enemyAppear){
            AbstractDungeon.getCurrRoom().monsters = MonsterHelper.getEncounter("Remiria");
            AbstractDungeon.lastCombatMetricKey = "Remiria";
            enterCombat();
            enemyAppear = false;
        }

        if(!(torchDone)){
            endTorch(torchNum);
            torchDone = true;
        }
    }

    public RoomOfDemon(){
//        this.imageEventText.setDialogOption("My Dialog Option");
        this.body = INTRO_MSG;
        this.roomEventText.addDialogOption(OPTIONS[0]);
        this.roomEventText.addDialogOption(OPTIONS[1]);

        this.hasDialog = true;
        this.hasFocus = true;
//        AbstractDungeon.getCurrRoom().monsters = MonsterHelper.getEncounter("Mysterious Sphere");
        this.adventurerImg = ImageMaster.loadImage("images/events/Evil.png");
        this.adventurerImg2 = ImageMaster.loadImage("images/events/Cross.png");
        this.adventurerImg3 = ImageMaster.loadImage("images/events/Cross2.png");
        randomizeTorch();
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (this.screen)
        {
            case INTRO:
                switch (buttonPressed)
                {
                    case 0:
                        this.screen = CurScreen.READ;
                        CardCrawlGame.music.silenceBGM();
                        this.roomEventText.updateBodyText(DESCRIPTIONS[1]);
                        this.roomEventText.updateDialogOption(0, OPTIONS[2]);
                        this.roomEventText.clearRemainingOptions();
                        SpellCardsRule.torchLight.clear();
                        SpellCardsRule.ceremonied = false;
//                        for (CeremonialTorchEffect e : this.torches) {
//                            e.startCeremony();
//                        }
                        AbstractDungeon.topLevelEffects.add(new TorchUnactivated(this.torches));
                        this.raedDone = true;
                        break;
                    case 1:
                        this.screen = CurScreen.CROSS;
                        this.roomEventText.updateBodyText(DESCRIPTIONS[2]);
                        this.roomEventText.updateDialogOption(0, OPTIONS[3]);
                        this.roomEventText.clearRemainingOptions();
                        this.extractDone = true;
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.x, this.y, new ThirstyCross());
                        break;
                }
                break;
            case READ:
                switch (buttonPressed) {
                    case 0:
                        this.raedDone = false;
                        if (SpellCardsRule.ceremonied) {
                            this.battleStart = true;
                            randomizeTorch();
//                            CardCrawlGame.music.unsilenceBGM();
                            for (int i = 0; i < 30; ++i)
                                AbstractDungeon.topLevelEffects.add(new RedFogCoverEffect(i));
//                            AbstractDungeon.topLevelEffects.add(new RedFogEffect());
                            break;
                        } else {
                            this.roomEventText.updateBodyText(DESCRIPTIONS[3]);
                            this.screen = CurScreen.ESCAPE;
                            break;
                        }
                }
                break;
            case CROSS:
                openMap();
                break;
            case ESCAPE:
                openMap();
                break;
        }
    }

    private void updateTorches()
    {
        for (Iterator<CeremonialTorchEffect> e = this.torches.iterator(); e.hasNext();)
        {
            CeremonialTorchEffect effect = e.next();
            effect.update();
            if (effect.isDone) {
                e.remove();
            }
        }
    }

    private void randomizeTorch()
    {
        this.torches.clear();
        if(!(this.battleStart)) {
            for (int i = 0; i < 9; i++) {
                for (int i1 = 0; i1 < 9; i1++) {
                    float x = (1150.0F + ((float) i * 50.0F));
                    float y = (500.0F + ((float) i1 * 50.0F));
                    this.torches.add(new CeremonialTorchEffect((x * Settings.scale), (y * Settings.scale), CeremonialTorchEffect.TorchSize.S, i, i1));
                }
            }
        }
        else{
        for(int i = 0;i < 9;i++) {
            for (int i1 = 0; i1 < 9; i1++) {
                float x = (1150.0F + ((float) i * 50.0F));
                float y = (500.0F + ((float) i1 * 50.0F));
                if (((i == 0) && ((i1 == 2) || (i1 == 6)))
                        || ((i == 1) && ((i1 == 2) || (i1 == 3) || (i1 == 5) || (i1 == 6)))
                        || ((i == 2) && ((i1 == 2) || (i1 == 3) || (i1 == 4) || (i1 == 5) || (i1 == 6)))
                        || ((i == 3) && ((i1 == 1) || (i1 == 2) || (i1 == 6) || (i1 == 7)))
                        || ((i == 4) && ((i1 == 0) || (i1 == 2) || (i1 == 6) || (i1 == 8)))
                        || ((i == 5) && ((i1 == 1) || (i1 == 2) || (i1 == 6) || (i1 == 7)))
                        || ((i == 6) && ((i1 == 2) || (i1 == 3) || (i1 == 4) || (i1 == 5) || (i1 == 6)))
                        || ((i == 7) && ((i1 == 2) || (i1 == 3) || (i1 == 5) || (i1 == 6)))
                        || ((i == 8) && ((i1 == 2) || (i1 == 6))))
                    this.torches.add(new CeremonialTorchEffect((x * Settings.scale), (y * Settings.scale), CeremonialTorchEffect.TorchSize.M, i, i1));
                LightFlareMEffect.renderGreen = true;
                com.megacrit.cardcrawl.vfx.scene.TorchParticleMEffect.renderGreen = true;
            }
        }
        }
//        LightFlareMEffect.renderGreen = MathUtils.randomBoolean();
//        com.megacrit.cardcrawl.vfx.scene.TorchParticleMEffect.renderGreen = LightFlareMEffect.renderGreen;
    }

    private void endTorch(int num){
        switch (num){
            case 0:
                this.torches.get(13).midUnactivated();
                this.torches.get(14).midUnactivated();
                this.torches.get(17).midUnactivated();
                this.torches.get(18).midUnactivated();
                this.torches.get(21).midUnactivated();
                this.torches.get(22).midUnactivated();
                break;
            case 1:
                this.torches.get(1).midUnactivated();
                this.torches.get(4).midUnactivated();
                this.torches.get(5).midUnactivated();
                this.torches.get(9).midUnactivated();
                this.torches.get(10).midUnactivated();
                break;
            case 2:
                this.torches.get(26).midUnactivated();
                this.torches.get(27).midUnactivated();
                this.torches.get(30).midUnactivated();
                this.torches.get(31).midUnactivated();
                this.torches.get(33).midUnactivated();
                break;
            case 3:
                this.torches.get(0).midUnactivated();
                this.torches.get(2).midUnactivated();
                this.torches.get(3).midUnactivated();
                this.torches.get(6).midUnactivated();
                this.torches.get(7).midUnactivated();
                this.torches.get(8).midUnactivated();
                break;
            case 4:
                this.torches.get(23).midUnactivated();
                this.torches.get(24).midUnactivated();
                this.torches.get(25).midUnactivated();
                this.torches.get(28).midUnactivated();
                this.torches.get(29).midUnactivated();
                this.torches.get(32).midUnactivated();
                break;
            case 99:
                this.torches.get(13).midUnactivated();
                this.torches.get(14).midUnactivated();
                this.torches.get(17).midUnactivated();
                this.torches.get(18).midUnactivated();
                this.torches.get(21).midUnactivated();
                this.torches.get(22).midUnactivated();
                this.torches.get(1).midUnactivated();
                this.torches.get(4).midUnactivated();
                this.torches.get(5).midUnactivated();
                this.torches.get(9).midUnactivated();
                this.torches.get(10).midUnactivated();
                this.torches.get(26).midUnactivated();
                this.torches.get(27).midUnactivated();
                this.torches.get(30).midUnactivated();
                this.torches.get(31).midUnactivated();
                this.torches.get(33).midUnactivated();
                this.torches.get(0).midUnactivated();
                this.torches.get(2).midUnactivated();
                this.torches.get(3).midUnactivated();
                this.torches.get(6).midUnactivated();
                this.torches.get(7).midUnactivated();
                this.torches.get(8).midUnactivated();
                this.torches.get(23).midUnactivated();
                this.torches.get(24).midUnactivated();
                this.torches.get(25).midUnactivated();
                this.torches.get(28).midUnactivated();
                this.torches.get(29).midUnactivated();
                this.torches.get(32).midUnactivated();
                this.torches.get(11).midUnactivated();
                this.torches.get(12).midUnactivated();
                this.torches.get(15).midUnactivated();
                this.torches.get(16).midUnactivated();
                this.torches.get(19).midUnactivated();
                this.torches.get(20).midUnactivated();
                break;
        }
    }

    public void render(SpriteBatch sb) {
        super.render(sb);
        sb.setColor(Color.WHITE);
        if (this.raedDone) {
            sb.draw(this.adventurerImg, (800 * Settings.scale) - 128.0F, (550 * Settings.scale) - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);
        }
        if(this.extractDone){
            sb.draw(this.adventurerImg3, this.x - 64.0F, this.y -64.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 128, 128, false, false);
        }
        else if(!(this.battleStart)){
            sb.draw(this.adventurerImg2, this.x - 64.0F, this.y -64.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 128, 128, false, false);
        }
        for (CeremonialTorchEffect e : this.torches) {
            e.render(sb);
        }
    }
}

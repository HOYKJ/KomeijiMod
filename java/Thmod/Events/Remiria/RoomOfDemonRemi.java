package Thmod.Events.Remiria;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.scene.LightFlareMEffect;

import java.util.ArrayList;
import java.util.Iterator;

import Thmod.Actions.common.LatterAction;
import Thmod.Relics.remiria.ThirstyCrossSpe;
import Thmod.vfx.LatterEffect;
import Thmod.vfx.RedFogCoverEffect;
import Thmod.vfx.scene.CeremonialTorchEffect;

public class RoomOfDemonRemi extends AbstractEvent {
    public static final String ID = "RoomOfDemonRemi";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("RoomOfDemonRemi");
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

    private enum CurScreen
    {
        INTRO, NEXT, COMBAT, LEAVE;

        CurScreen() {}
    }

    public void update() {
        super.update();
        updateTorches();

        if (!RoomEventDialog.waitForInput) {
            buttonEffect(this.roomEventText.getSelectedOption());
        }

        if(enemyAppear){
            enemyAppear = false;
        }
    }

    public RoomOfDemonRemi(){
        this.body = INTRO_MSG;
        this.roomEventText.addDialogOption(OPTIONS[2]);

        this.hasDialog = true;
        this.hasFocus = true;
        randomizeTorch();
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (this.screen)
        {
            case INTRO:
                this.screen = CurScreen.NEXT;
                this.roomEventText.updateBodyText(DESCRIPTIONS[1]);
                if(AbstractDungeon.player.hasRelic(ThirstyCrossSpe.ID)) {
                    this.roomEventText.updateDialogOption(0, OPTIONS[0]);
                }
                else {
                    this.roomEventText.removeDialogOption(0);
                    this.roomEventText.addDialogOption(OPTIONS[3], true);
                }
                this.roomEventText.addDialogOption(OPTIONS[1]);
                break;
            case NEXT:
                switch (buttonPressed) {
                    case 0:
                        this.screen = CurScreen.COMBAT;
                        this.roomEventText.updateBodyText(DESCRIPTIONS[2]);
                        this.roomEventText.updateDialogOption(0, OPTIONS[2]);
                        this.roomEventText.clearRemainingOptions();
                        AbstractDungeon.player.loseRelic(ThirstyCrossSpe.ID);
                        break;
                    case 1:
                        this.screen = CurScreen.LEAVE;
                        this.roomEventText.updateBodyText(DESCRIPTIONS[3]);
                        this.roomEventText.updateDialogOption(0, OPTIONS[2]);
                        this.roomEventText.clearRemainingOptions();
                        break;
                }
                break;
            case COMBAT:
                this.battleStart = true;
                randomizeTorch();
                for (int i = 0; i < 30; ++i) {
                    AbstractDungeon.topLevelEffects.add(new RedFogCoverEffect(i));
                }
                AbstractDungeon.effectsQueue.add(new LatterEffect(()->{
                    AbstractDungeon.getCurrRoom().monsters = MonsterHelper.getEncounter("Satori");
                    enterCombat();
                }, 0.5f));
                break;
            case LEAVE:
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
                    CeremonialTorchEffect ceremonialTorchEffect = new CeremonialTorchEffect((x * Settings.scale), (y * Settings.scale), CeremonialTorchEffect.TorchSize.S, i, i1);
                    ceremonialTorchEffect.setActivated(false);
                    this.torches.add(ceremonialTorchEffect);
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
    }

    public void render(SpriteBatch sb) {
        super.render(sb);
        for (CeremonialTorchEffect e : this.torches) {
            e.render(sb);
        }
    }
}

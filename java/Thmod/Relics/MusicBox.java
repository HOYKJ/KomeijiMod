package Thmod.Relics;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;

import Thmod.Events.GateofTheMinistry;
import Thmod.Events.Kourindou;
import Thmod.Events.RoomOfDemon;
import Thmod.Events.RoomOfTime;
import Thmod.Events.SpiritSuffering;
import Thmod.ThMod;

public class MusicBox extends AbstractThLaterRelic {
    public static final String ID = "MusicBox";
    public boolean active;

    public MusicBox()
    {
        super("MusicBox",  RelicTier.STARTER, LandingSound.CLINK);
        this.active = true;
        this.img = ImageMaster.loadImage("images/relics/MusicBox_0.png");
    }

    protected  void onRightClick(){
        this.active = !this.active;
        if(this.active){
            this.img = ImageMaster.loadImage("images/relics/MusicBox_0.png");
        }
        else {
            this.img = ImageMaster.loadImage("images/relics/MusicBox.png");
        }
    }

    @Override
    public void enteredRoom(AbstractRoom room) {
        if(this.active) {
            if (room instanceof ShopRoom) {
                CardCrawlGame.music.silenceTempBgmInstantly();
                CardCrawlGame.music.playTempBGM("商店.mp3");
                this.img = ImageMaster.loadImage("images/relics/MusicBox_1.png");
            }
            else if(room instanceof EventRoom){
                if(room.event instanceof RoomOfTime){
                    if ((ThMod.blessingOfTime == 0) || (ThMod.blessingOfTime == 1) || (ThMod.blessingOfTime == 3)) {
                        CardCrawlGame.music.silenceTempBgmInstantly();
                        CardCrawlGame.music.playTempBGM("时.mp3");
                        this.img = ImageMaster.loadImage("images/relics/MusicBox_1.png");
                    }
                }
                else if(room.event instanceof RoomOfDemon){
                    if (ThMod.blessingOfTime == 2) {
                        CardCrawlGame.music.silenceTempBgmInstantly();
                        CardCrawlGame.music.playTempBGM("时.mp3");
                        this.img = ImageMaster.loadImage("images/relics/MusicBox_1.png");
                    }
                }
                else if(room.event instanceof SpiritSuffering){
                    if ((ThMod.blessingOfDetermination == 0) || (ThMod.blessingOfDetermination == 3)) {
                        CardCrawlGame.music.silenceTempBgmInstantly();
                        CardCrawlGame.music.playTempBGM("觉.mp3");
                        this.img = ImageMaster.loadImage("images/relics/MusicBox_1.png");
                    }
                }
                else if(room.event instanceof Kourindou){
                    if (ThMod.blessingOfDetermination == 1) {
                        CardCrawlGame.music.silenceTempBgmInstantly();
                        CardCrawlGame.music.playTempBGM("觉.mp3");
                        this.img = ImageMaster.loadImage("images/relics/MusicBox_1.png");
                    }
                }
                else if(room.event instanceof GateofTheMinistry){
                    if (ThMod.blessingOfRemission >= 1) {
                        CardCrawlGame.music.silenceTempBgmInstantly();
                        CardCrawlGame.music.playTempBGM("归航.mp3");
                        this.img = ImageMaster.loadImage("images/relics/MusicBox_1.png");
                    }
                }
            }
            else if(AbstractDungeon.player.hasRelic(WordlessDocument.ID)){
                CardCrawlGame.music.silenceTempBgmInstantly();
                CardCrawlGame.music.playTempBGM("归航.mp3");
                this.img = ImageMaster.loadImage("images/relics/MusicBox_1.png");
            }
            else {
                this.img = ImageMaster.loadImage("images/relics/MusicBox_0.png");
            }
        }
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new MusicBox();
    }
}

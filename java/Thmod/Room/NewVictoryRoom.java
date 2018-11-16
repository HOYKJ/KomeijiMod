package Thmod.Room;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.VictoryRoom;
import com.megacrit.cardcrawl.screens.DeathScreen;

public class NewVictoryRoom extends VictoryRoom{
    public NewVictoryRoom(EventType type){
        super(type);
    }

    @Override
    public void onPlayerEntry() {
        super.onPlayerEntry();
        CardCrawlGame.fadeIn(1.5F);
        AbstractDungeon.closeCurrentScreen();
        AbstractDungeon.player.isDying = true;
        AbstractDungeon.player.isDead = true;
        AbstractDungeon.deathScreen = new DeathScreen(null);
    }
}

package Thmod.Potion;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.neow.NeowReward;
import com.megacrit.cardcrawl.neow.NeowRoom;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;

import Thmod.Events.Ferry;
import Thmod.Events.RoomOfTime;
import Thmod.Room.NewVictoryRoom;

public class RecallPotion extends AbstractPotion
{
    public static final String POTION_ID = "RecallPotion";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("RecallPotion");
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public RecallPotion()
    {
        super(NAME, "RecallPotion", PotionRarity.RARE, PotionSize.FAIRY, PotionColor.ATTACK);
        this.potency = getPotency();
        this.description = (DESCRIPTIONS[0]);
        this.isThrown = false;
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(DESCRIPTIONS[1], DESCRIPTIONS[2]));
    }

    public void use(AbstractCreature target)
    {
        AbstractDungeon.currMapNode.room = new MonsterRoom();
        AbstractDungeon.getCurrRoom().monsters = MonsterHelper.getEncounter("Shikieiki");
//        if(AbstractDungeon.getCurrRoom() instanceof EventRoom){
//            ((EventRoom)AbstractDungeon.getCurrRoom()).event = new RoomOfTime();
//            ((EventRoom)AbstractDungeon.getCurrRoom()).event.roomEventText.clear();
//            ((EventRoom)AbstractDungeon.getCurrRoom()).event.hasFocus = false;
//            ((EventRoom)AbstractDungeon.getCurrRoom()).event.roomEventText.hide();
//            ((EventRoom)AbstractDungeon.getCurrRoom()).event.combatTime = true;
//        }
//        if(AbstractDungeon.getCurrRoom() instanceof NeowRoom){
//            ((NeowRoom)AbstractDungeon.getCurrRoom()).event = new RoomOfTime();
//            ((NeowRoom)AbstractDungeon.getCurrRoom()).event.roomEventText.clear();
//            ((NeowRoom)AbstractDungeon.getCurrRoom()).event.hasFocus = false;
//            ((NeowRoom)AbstractDungeon.getCurrRoom()).event.roomEventText.hide();
//            AbstractDungeon.effectList.clear();
//            ((EventRoom)AbstractDungeon.getCurrRoom()).event.combatTime = true;
//        }
//        AbstractDungeon.getCurrRoom().smoked = false;
//        AbstractDungeon.player.isEscaping = false;
//        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMBAT;
//        AbstractDungeon.getCurrRoom().monsters.init();
//        AbstractRoom.waitTimer = 0.1F;
//        AbstractDungeon.player.preBattlePrep();
//        GenericEventDialog.hide();
//        CardCrawlGame.fadeIn(1.5F);
//        AbstractDungeon.rs = AbstractDungeon.RenderScene.NORMAL;
        AbstractDungeon.getCurrRoom().smoked = false;
        AbstractDungeon.player.isEscaping = false;
        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMBAT;
        AbstractDungeon.getCurrRoom().monsters.init();
        AbstractRoom.waitTimer = 0.1F;
        AbstractDungeon.player.preBattlePrep();
        CardCrawlGame.fadeIn(1.5F);
        AbstractDungeon.rs = AbstractDungeon.RenderScene.NORMAL;
    }

    public boolean canUse()
    {
        return AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMPLETE;
    }

    public AbstractPotion makeCopy()
    {
        return new RecallPotion();
    }

    public int getPotency(int ascensionLevel)
    {
        return 0;
    }
}

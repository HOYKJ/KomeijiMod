package Thmod.Relics.remiria;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;

import Thmod.Cards.ScarletCard.DeriveCards.BloodyCurse;
import Thmod.Cards.ScarletCard.DeriveCards.ExtremelyThirsty;
import Thmod.Cards.ScarletCard.DeriveCards.PowerControl;
import Thmod.Power.remiria.BloodBruisePower;
import Thmod.ThMod;
import Thmod.vfx.action.ChooseEffect;
import basemod.abstracts.CustomSavable;

public class UnsealingCross extends AbstractRemiriaRelic implements CustomSavable<int[]> {
    public static final String ID = "UnsealingCross";
    private AbstractPlayer p;
    public int[] number = {0};
    private boolean canUse;
    private CardGroup choices;
    private ArrayList<Runnable> actions;

    public UnsealingCross()
    {
        super("UnsealingCross",  RelicTier.BOSS, LandingSound.HEAVY);
        this.p = AbstractDungeon.player;
        this.number[0] = 0;
    }

    @Override
    public void onEquip() {
        super.onEquip();

        if (AbstractDungeon.isScreenUp) {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }
        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;

        this.p = AbstractDungeon.player;
        //p.loseRelic(ThirstyCrossSpe.ID);
        //p.loseRelic(ID);

        final ChooseEffect choice = new ChooseEffect(null, null, DESCRIPTIONS[4], false, 1);
        choice.add(new BloodyCurse(), () -> {
            this.number[0] = 1;
            this.counter = 0;
            AbstractDungeon.player.energy.energyMaster += 1;
            reloadDes();
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
        });
        choice.add(new PowerControl(), () -> {
            this.number[0] = 2;
            this.counter = 0;
            reloadDes();
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
        });
        choice.add(new ExtremelyThirsty(), () -> {
            this.number[0] = 3;
            reloadDes();
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
        });
        AbstractDungeon.effectsQueue.add(choice);

    }

    @Override
    public void obtain() {
        for(int i = 0; i < AbstractDungeon.player.relics.size(); i ++){
            if(AbstractDungeon.player.relics.get(i) instanceof ThirstyCrossSpe){
                this.instantObtain(AbstractDungeon.player, i, false);
                break;
            }
        }
    }

    @Override
    public void onUnequip() {
        super.onUnequip();
        if(this.number[0] == 1){
            AbstractDungeon.player.energy.energyMaster -= 1;
        }
    }

    @Override
    public void update() {
        super.update();
        if(this.number[0] == 0){
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (int i1 = 0; i1 < AbstractDungeon.gridSelectScreen.selectedCards.size(); i1++) {
                    final ArrayList<AbstractCard> pick = new ArrayList<>();
                    pick.add(AbstractDungeon.gridSelectScreen.selectedCards.get(i1)) ;
                    final ArrayList<Integer> i = new ArrayList<>();
                    i.add(choices.group.indexOf(pick.get(0))) ;
                    actions.get(i.get(0)).run();
                    pick.clear();
                    i.clear();
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
                return;
            }
            if ((!AbstractDungeon.isScreenUp) && (AbstractDungeon.currMapNode != null)) {
                choices = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                choices.addToTop(new BloodyCurse().makeStatEquivalentCopy());
                choices.addToTop(new PowerControl().makeStatEquivalentCopy());
                choices.addToTop(new ExtremelyThirsty().makeStatEquivalentCopy());
                AbstractDungeon.gridSelectScreen.open(choices, 1, DESCRIPTIONS[4], false, false, true, false);
                actions = new ArrayList<>();
                actions.add(() -> {
                    this.number[0] = 1;
                    this.counter = 0;
                    AbstractDungeon.player.energy.energyMaster += 1;
                    reloadDes();
                });
                actions.add(() -> {
                    this.number[0] = 2;
                    this.counter = 0;
                    reloadDes();
                });
                actions.add(() -> {
                    this.number[0] = 3;
                    reloadDes();
                });
            }
        }
    }

    public void atBattleStart() {
        if(this.number[0] == 1){
            if(this.counter >= 6){
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, new BloodBruisePower(this.p, this.counter), this.counter));
            }
            else {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, new BloodBruisePower(this.p, 6), 6));
                this.counter = 6;
            }
        }
        else if(this.number[0] == 2){
            if(this.counter == 0){
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, new StrengthPower(this.p, 3), 3));
            }
            else {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, new DexterityPower(this.p, 3), 3));
            }
            this.canUse = true;
        }
    }

    public void atTurnStart() {
        if(this.number[0] == 3) {
            beginLongPulse();
            this.pulse = true;
        }
    }

    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if(this.number[0] == 3) {
            if (targetCard.type == AbstractCard.CardType.ATTACK) {
                this.pulse = false;
                stopPulse();
            }
        }
    }

    public void onPlayerEndTurn() {
        if(this.number[0] == 3) {
            if (this.pulse) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, new StrengthPower(this.p, 1), 1));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodBruisePower(p, 3), 3));
                for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                    AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                    if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, this.p, new BloodBruisePower(target, 3), 3));
                    }
                }
            }
        }
    }

    @Override
    public void onVictory() {
        super.onVictory();
        if(this.number[0] == 1) {
            for (AbstractPower power : AbstractDungeon.player.powers) {
                if(power instanceof BloodBruisePower){
                    this.counter = power.amount;
                }
            }
        }
    }

    protected  void onRightClick(){
        if(this.number[0] == 2){
            if(this.canUse) {
                this.canUse = false;
                if (this.counter == 0) {
                    this.counter = 1;
                    int tmp = 0;
                    if (this.p.hasPower(ArtifactPower.POWER_ID)) {
                        tmp = this.p.getPower(ArtifactPower.POWER_ID).amount;
                        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.p, this.p, ArtifactPower.POWER_ID));
                    }
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, new StrengthPower(this.p, -3), -3));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, new DexterityPower(this.p, 3), 3));
                    if (tmp > 0) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, new ArtifactPower(this.p, tmp), tmp));
                    }
                } else {
                    this.counter = 0;
                    int tmp = 0;
                    if (this.p.hasPower(ArtifactPower.POWER_ID)) {
                        tmp = this.p.getPower(ArtifactPower.POWER_ID).amount;
                        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.p, this.p, ArtifactPower.POWER_ID));
                    }
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, new DexterityPower(this.p, -3), -3));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, new StrengthPower(this.p, 3), 3));
                    if (tmp > 0) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, new ArtifactPower(this.p, tmp), tmp));
                    }
                }
            }
        }
    }

    public boolean canSpawn()
    {
        return AbstractDungeon.player.hasRelic(ThirstyCrossSpe.ID);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public String getUpdatedDescription(int num) {
        return this.DESCRIPTIONS[num];
    }

    private void reloadDes(){
        this.description = getUpdatedDescription(this.number[0]);
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        initializeTips();
    }

    public AbstractRelic makeCopy() {
        return new UnsealingCross();
    }

    @Override
    public int[] onSave() {
        return this.number;
    }

    @Override
    public void onLoad(int[] arg0)
    {
        if (arg0 == null) {
            return;
        }
        this.number = arg0.clone();
        reloadDes();
    }
}

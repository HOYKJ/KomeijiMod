package Thmod.Actions.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

import Thmod.Actions.unique.ChooseAction;
import Thmod.Cards.NingyouShinki;
import Thmod.Orbs.Helan;
import Thmod.Orbs.NingyouOrb;
import Thmod.Orbs.Penglai;
import Thmod.Orbs.Shanghai;
import Thmod.Orbs.TateNingyou;
import Thmod.Orbs.YariNingyou;
import Thmod.Orbs.YumiNingyou;

public class AddNewOrbAction extends AbstractGameAction {
    private AbstractCard card;
    private String message;
    private int ningyouNum;

    public AddNewOrbAction(AbstractCard card,String message,int ningyouNum) {
        this.card = card;
        this.message = message;
        this.ningyouNum = ningyouNum;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        final ChooseAction choice = new ChooseAction(this.card, AbstractDungeon.getCurrRoom().monsters.monsters.get(0), this.message, true, 1);
        for (int i = (AbstractDungeon.player.orbs.size() - 1); i >= 0; i--) {
            final ArrayList<Integer> orbnum = new ArrayList<>();
            orbnum.clear();
            orbnum.add(i);
            if (AbstractDungeon.player.orbs.get(i) instanceof NingyouOrb) {
                choice.add(NingyouShinki.EXTENDED_DESCRIPTION[1], NingyouShinki.EXTENDED_DESCRIPTION[2], () -> {
                    AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0),this.ningyouNum));
                });
            }
            if (AbstractDungeon.player.orbs.get(i) instanceof YariNingyou) {
                choice.add(NingyouShinki.EXTENDED_DESCRIPTION[3], NingyouShinki.EXTENDED_DESCRIPTION[4], () -> {
                    AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0),this.ningyouNum));
                });
            }
            if (AbstractDungeon.player.orbs.get(i) instanceof TateNingyou) {
                choice.add(NingyouShinki.EXTENDED_DESCRIPTION[5], NingyouShinki.EXTENDED_DESCRIPTION[6], () -> {
                    AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0),this.ningyouNum));
                });
            }
            if (AbstractDungeon.player.orbs.get(i) instanceof YumiNingyou) {
                choice.add(NingyouShinki.EXTENDED_DESCRIPTION[7], NingyouShinki.EXTENDED_DESCRIPTION[8], () -> {
                    AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0),this.ningyouNum));
                });
            }
            if (AbstractDungeon.player.orbs.get(i) instanceof Shanghai) {
                choice.add(NingyouShinki.EXTENDED_DESCRIPTION[9], NingyouShinki.EXTENDED_DESCRIPTION[10], () -> {
                    AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0),this.ningyouNum));
                });
            }
            if (AbstractDungeon.player.orbs.get(i) instanceof Penglai) {
                choice.add(NingyouShinki.EXTENDED_DESCRIPTION[11], NingyouShinki.EXTENDED_DESCRIPTION[12], () -> {
                    AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0),this.ningyouNum));
                });
            }
            if (AbstractDungeon.player.orbs.get(i) instanceof Helan) {
                choice.add(NingyouShinki.EXTENDED_DESCRIPTION[13], NingyouShinki.EXTENDED_DESCRIPTION[14], () -> {
                    AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0),this.ningyouNum));
                });
            }
        }
        AbstractDungeon.actionManager.addToBottom(choice);
        this.isDone = true;
    }
}

package Thmod.Cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;

import java.util.ArrayList;

import Thmod.Actions.common.ChangeOrbAction;
import Thmod.Actions.common.ChooseAction;
import Thmod.Orbs.Helan;
import Thmod.Orbs.NingyouOrb;
import Thmod.Orbs.Penglai;
import Thmod.Orbs.Shanghai;
import Thmod.Orbs.TateNingyou;
import Thmod.Orbs.YariNingyou;
import Thmod.Orbs.YumiNingyou;

public class NingyouShinki extends AbstractSweepCards {
    public static final String ID = "NingyouShinki";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private static final int COST = 1;

    public NingyouShinki() {
        super("NingyouShinki", NingyouShinki.NAME,  1, NingyouShinki.DESCRIPTION, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 6;
        this.isMultiDamage = true;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        final ChooseAction choice = new ChooseAction(this, m, NingyouShinki.EXTENDED_DESCRIPTION[0], true, 3);
        for (int i = (AbstractDungeon.player.orbs.size() - 1); i >= 0; i--) {
            final ArrayList<Integer> orbnum = new ArrayList<>();
            orbnum.clear();
            orbnum.add(i);
            if (AbstractDungeon.player.orbs.get(i) instanceof NingyouOrb) {
                choice.add(NingyouShinki.EXTENDED_DESCRIPTION[1], NingyouShinki.EXTENDED_DESCRIPTION[2], () -> {
                    AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0),true));
                    AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                });
            }
            if (AbstractDungeon.player.orbs.get(i) instanceof YariNingyou) {
                choice.add(NingyouShinki.EXTENDED_DESCRIPTION[3], NingyouShinki.EXTENDED_DESCRIPTION[4], () -> {
                    AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0),false));
                    AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                });
            }
            if (AbstractDungeon.player.orbs.get(i) instanceof TateNingyou) {
                choice.add(NingyouShinki.EXTENDED_DESCRIPTION[5], NingyouShinki.EXTENDED_DESCRIPTION[6], () -> {
                    AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0),false));
                    AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                });
            }
            if (AbstractDungeon.player.orbs.get(i) instanceof YumiNingyou) {
                choice.add(NingyouShinki.EXTENDED_DESCRIPTION[7], NingyouShinki.EXTENDED_DESCRIPTION[8], () -> {
                    AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0),false));
                    AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                });
            }
            if (AbstractDungeon.player.orbs.get(i) instanceof Shanghai) {
                choice.add(NingyouShinki.EXTENDED_DESCRIPTION[9], NingyouShinki.EXTENDED_DESCRIPTION[10], () -> {
                    AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0),true));
                    AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                });
            }
            if (AbstractDungeon.player.orbs.get(i) instanceof Penglai) {
                choice.add(NingyouShinki.EXTENDED_DESCRIPTION[11], NingyouShinki.EXTENDED_DESCRIPTION[12], () -> {
                    AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0),true));
                    AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                });
            }
            if (AbstractDungeon.player.orbs.get(i) instanceof Helan) {
                choice.add(NingyouShinki.EXTENDED_DESCRIPTION[13], NingyouShinki.EXTENDED_DESCRIPTION[14], () -> {
                    AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(orbnum.get(0),true));
                    AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                });
            }
        }
        AbstractDungeon.actionManager.addToBottom(choice);
    }

    @Override
    public ArrayList<AbstractSweepCards> getOpposite() {
        final ArrayList<AbstractSweepCards> opposite = new ArrayList<>();
        opposite.add(new NingyouSP());
        opposite.add(new SeekerWire());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new NingyouShinki();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("NingyouShinki");
        NAME = NingyouShinki.cardStrings.NAME;
        DESCRIPTION = NingyouShinki.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = NingyouShinki.cardStrings.EXTENDED_DESCRIPTION;
    }
}

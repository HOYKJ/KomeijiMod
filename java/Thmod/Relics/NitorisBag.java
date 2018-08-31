package Thmod.Relics;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import Thmod.Actions.unique.CardMixAction;
import Thmod.Actions.unique.PlayerTalkAction;
import Thmod.Cards.DeriveCards.MixedItem;
import basemod.DevConsole;

public class NitorisBag extends AbstractThRelic {
    public static final String ID = "NitorisBag";
    public static int costForMix;
    private boolean playerturn;
    private boolean clicked;
    private boolean selected;

    public NitorisBag()
    {
        super("NitorisBag",  RelicTier.SHOP, LandingSound.HEAVY);
        this.playerturn = false;
        this.selected = false;
    }

    protected  void onRightClick(){
        AbstractPlayer p = AbstractDungeon.player;
        if ((AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)) {
            if (!(this.selected)) {
                if (this.playerturn) {
                    if (!(this.clicked)) {
                        this.clicked = true;
                        final CardMixAction choice = new CardMixAction(new MixedItem(null,null,null), this.DESCRIPTIONS[1]);
                        choice.add(this.DESCRIPTIONS[2], this.DESCRIPTIONS[3], () -> {
                            costForMix = 1;
                            DevConsole.logger.info("run_1");
                        });
                        choice.add(this.DESCRIPTIONS[4], this.DESCRIPTIONS[5], () -> {
                            costForMix = 2;
                            DevConsole.logger.info("run_2");
                        });
                        choice.add(this.DESCRIPTIONS[6], this.DESCRIPTIONS[7], () -> {
                            costForMix = 3;
                            DevConsole.logger.info("run_3");
                        });



                        choice.add(this.DESCRIPTIONS[8], this.DESCRIPTIONS[9], () -> {
                            AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(true);
                            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, 6, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                            DevConsole.logger.info("run_4");
                        });
                        choice.add(this.DESCRIPTIONS[10], this.DESCRIPTIONS[11], () -> {
                            for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                                AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                                if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                                    AbstractDungeon.actionManager.addToBottom(new DamageAction(target, new DamageInfo(p, 5, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                                }
                            }
                            DevConsole.logger.info("run_5");
                        });
                        choice.add(this.DESCRIPTIONS[12], this.DESCRIPTIONS[13], () -> {
                            AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, 5));
                            DevConsole.logger.info("run_6");
                        });
                        choice.add(this.DESCRIPTIONS[14], this.DESCRIPTIONS[15], () -> {
                            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
                            DevConsole.logger.info("run_7");
                        });
                        choice.add(this.DESCRIPTIONS[16], this.DESCRIPTIONS[17], () -> {
                            AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(true);
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,p,new WeakPower(m,3,false),3));
                            DevConsole.logger.info("run_8");
                        });
                        choice.add(this.DESCRIPTIONS[18], this.DESCRIPTIONS[19], () -> {
                            AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(true);
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,p,new VulnerablePower(m,3,false),3));
                            DevConsole.logger.info("run_9");
                        });

                        choice.add(this.DESCRIPTIONS[20], this.DESCRIPTIONS[21], () -> {
                            AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(true);
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,p,new StrengthPower(m,-1),-1));
                            DevConsole.logger.info("run_10");
                        });
                        choice.add(this.DESCRIPTIONS[22], this.DESCRIPTIONS[23], () -> {
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new StrengthPower(p,1),1));
                            DevConsole.logger.info("run_11");
                        });
                        choice.add(this.DESCRIPTIONS[24], this.DESCRIPTIONS[25], () -> {
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new DexterityPower(p,1),1));
                            DevConsole.logger.info("run_12");
                        });
                        choice.add(this.DESCRIPTIONS[26], this.DESCRIPTIONS[27], () -> {
                            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, 3));
                            DevConsole.logger.info("run_13");
                        });



                        choice.add(this.DESCRIPTIONS[28], this.DESCRIPTIONS[29], () -> {
                            AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(true);
                            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, 12, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                            DevConsole.logger.info("run_14");
                        });
                        choice.add(this.DESCRIPTIONS[30], this.DESCRIPTIONS[31], () -> {
                            for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                                AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                                if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                                    AbstractDungeon.actionManager.addToBottom(new DamageAction(target, new DamageInfo(p, 10, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                                }
                            }
                            DevConsole.logger.info("run_15");
                        });
                        choice.add(this.DESCRIPTIONS[32], this.DESCRIPTIONS[33], () -> {
                            AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, 10));
                            DevConsole.logger.info("run_16");
                        });
                        choice.add(this.DESCRIPTIONS[34], this.DESCRIPTIONS[35], () -> {
                            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 2));
                            DevConsole.logger.info("run_17");
                        });
                        choice.add(this.DESCRIPTIONS[36], this.DESCRIPTIONS[37], () -> {
                            AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(true);
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,p,new WeakPower(m,3,false),3));
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,p,new VulnerablePower(m,3,false),3));
                            DevConsole.logger.info("run_18");
                        });
                        choice.add(this.DESCRIPTIONS[38], this.DESCRIPTIONS[39], () -> {
                            for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                                AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                                if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target,p,new WeakPower(target,3,false),3));
                                }
                            }
                            DevConsole.logger.info("run_19");
                        });
                        choice.add(this.DESCRIPTIONS[40], this.DESCRIPTIONS[41], () -> {
                            for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                                AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                                if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target,p,new VulnerablePower(target,3,false),3));
                                }
                            }
                            DevConsole.logger.info("run_20");
                        });
                        choice.add(this.DESCRIPTIONS[42], this.DESCRIPTIONS[43], () -> {
                            for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                                AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                                if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target,p,new StrengthPower(target,-1),-1));
                                }
                            }
                            DevConsole.logger.info("run_21");
                        });
                        choice.add(this.DESCRIPTIONS[44], this.DESCRIPTIONS[45], () -> {
                            AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(true);
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,p,new StrengthPower(m,-2),-2));
                            DevConsole.logger.info("run_22");
                        });
                        choice.add(this.DESCRIPTIONS[46], this.DESCRIPTIONS[47], () -> {
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new StrengthPower(p,2),2));
                            DevConsole.logger.info("run_23");
                        });
                        choice.add(this.DESCRIPTIONS[48], this.DESCRIPTIONS[49], () -> {
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new DexterityPower(p,2),2));
                            DevConsole.logger.info("run_24");
                        });
                        choice.add(this.DESCRIPTIONS[50], this.DESCRIPTIONS[51], () -> {
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new StrengthPower(p,1),1));
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new DexterityPower(p,1),1));
                            DevConsole.logger.info("run_25");
                        });
                        choice.add(this.DESCRIPTIONS[52], this.DESCRIPTIONS[53], () -> {
                            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
                            DevConsole.logger.info("run_26");
                        });
                        choice.add(this.DESCRIPTIONS[54], this.DESCRIPTIONS[55], () -> {
                            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, 3));
                            DevConsole.logger.info("run_27");
                        });



                        choice.add(this.DESCRIPTIONS[56], this.DESCRIPTIONS[57], () -> {
                            AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(true);
                            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, 18, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                            DevConsole.logger.info("run_28");
                        });
                        choice.add(this.DESCRIPTIONS[58], this.DESCRIPTIONS[59], () -> {
                            for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                                AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                                if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                                    AbstractDungeon.actionManager.addToBottom(new DamageAction(target, new DamageInfo(p, 15, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                                }
                            }
                            DevConsole.logger.info("run_29");
                        });
                        choice.add(this.DESCRIPTIONS[60], this.DESCRIPTIONS[61], () -> {
                            AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, 15));
                            DevConsole.logger.info("run_30");
                        });
                        choice.add(this.DESCRIPTIONS[62], this.DESCRIPTIONS[63], () -> {
                            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 3));
                            DevConsole.logger.info("run_31");
                        });
                        choice.add(this.DESCRIPTIONS[64], this.DESCRIPTIONS[65], () -> {
                            for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                                AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                                if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target,p,new WeakPower(target,3,false),3));
                                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target,p,new VulnerablePower(target,3,false),3));
                                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target,p,new FrailPower(target,3,false),3));
                                }
                            }
                            DevConsole.logger.info("run_32");
                        });
                        choice.add(this.DESCRIPTIONS[66], this.DESCRIPTIONS[67], () -> {
                            for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                                AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                                if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target,p,new StrengthPower(target,-2),-2));
                                }
                            }
                            DevConsole.logger.info("run_33");
                        });
                        choice.add(this.DESCRIPTIONS[68], this.DESCRIPTIONS[69], () -> {
                            AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(true);
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,p,new StrengthPower(m,-3),-3));
                            DevConsole.logger.info("run_34");
                        });
                        choice.add(this.DESCRIPTIONS[70], this.DESCRIPTIONS[71], () -> {
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new StrengthPower(p,3),3));
                            DevConsole.logger.info("run_35");
                        });
                        choice.add(this.DESCRIPTIONS[72], this.DESCRIPTIONS[73], () -> {
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new DexterityPower(p,3),3));
                            DevConsole.logger.info("run_36");
                        });
                        choice.add(this.DESCRIPTIONS[74], this.DESCRIPTIONS[75], () -> {
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new StrengthPower(p,2),2));
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new DexterityPower(p,1),1));
                            DevConsole.logger.info("run_37");
                        });
                        choice.add(this.DESCRIPTIONS[76], this.DESCRIPTIONS[77], () -> {
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new StrengthPower(p,1),1));
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new DexterityPower(p,2),2));
                            DevConsole.logger.info("run_38");
                        });
                        choice.add(this.DESCRIPTIONS[78], this.DESCRIPTIONS[79], () -> {
                            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(2));
                            DevConsole.logger.info("run_39");
                        });
                        choice.add(this.DESCRIPTIONS[80], this.DESCRIPTIONS[81], () -> {
                            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, 3));
                            DevConsole.logger.info("run_40");
                        });



                        AbstractDungeon.actionManager.addToBottom(choice);
                        this.pulse = false;
                        this.selected = true;
                    } else {
                        AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, this.DESCRIPTIONS[82]));
                    }
                } else {
                    AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, this.DESCRIPTIONS[83]));
                }
            } else {
                AbstractDungeon.actionManager.addToTop(new PlayerTalkAction(p, this.DESCRIPTIONS[84]));
            }
        }
    }

    public void atTurnStart() {
        this.playerturn = true;
        this.clicked = false;
        if (!this.selected) {
            beginPulse();
            this.pulse = true;
        }
    }

    public void onPlayerEndTurn() {
        this.playerturn = false;
        this.pulse = false;
    }

    public void onVictory() {
        this.selected = false;
        this.pulse = false;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new NitorisBag();
    }
}

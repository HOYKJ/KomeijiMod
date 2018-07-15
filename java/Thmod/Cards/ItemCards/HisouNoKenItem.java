package Thmod.Cards.ItemCards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Power.PointPower;
import Thmod.Power.Weather.DaiyamondoDasuto;
import Thmod.Power.Weather.DonTen;
import Thmod.Power.Weather.Fuuu;
import Thmod.Power.Weather.Haku;
import Thmod.Power.Weather.HanaGumo;
import Thmod.Power.Weather.KaiSei;
import Thmod.Power.Weather.KawaGiri;
import Thmod.Power.Weather.KiriSame;
import Thmod.Power.Weather.Kousa;
import Thmod.Power.Weather.KyoKkou;
import Thmod.Power.Weather.Nagi;
import Thmod.Power.Weather.NouMu;
import Thmod.Power.Weather.RetsuJitsu;
import Thmod.Power.Weather.SeiRan;
import Thmod.Power.Weather.SouTen;
import Thmod.Power.Weather.Soyuki;
import Thmod.Power.Weather.TaiFuu;
import Thmod.Power.Weather.TenkiYume;
import Thmod.Power.Weather.Tsume;
import Thmod.Power.Weather.Yuki;
import Thmod.ThMod;

public class HisouNoKenItem extends AbstractItemCards {
    public static final String ID = "HisouNoKenItem";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 0;

    public HisouNoKenItem() {
        super("HisouNoKenItem", HisouNoKenItem.NAME,  0, HisouNoKenItem.DESCRIPTION, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= 1) {
                for(int i = 0;i < 20;i++){
                    String weatherid = ThMod.weathers.get(i);
                    if(p.hasPower(weatherid)) {
                        AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p, p, weatherid));
                        break;
                    }
                }
                int roll = MathUtils.random(20);
                switch (roll){
                    case 0:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KaiSei(p)));
                        break;
                    case 1:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KiriSame(p)));
                        break;
                    case 2:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DonTen(p)));
                        break;
                    case 3:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SouTen(p)));
                        break;
                    case 4:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Haku(p)));
                        break;
                    case 5:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new HanaGumo(p)));
                        break;
                    case 6:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new NouMu(p)));
                        break;
                    case 7:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Yuki(p)));
                        break;
                    case 8:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new TenkiYume(p)));
                        break;
                    case 9:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Soyuki(p)));
                        break;
                    case 10:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Fuuu(p)));
                        break;
                    case 11:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SeiRan(p)));
                        break;
                    case 12:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KawaGiri(p)));
                        break;
                    case 13:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new TaiFuu(p)));
                        break;
                    case 14:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Nagi(p)));
                        break;
                    case 15:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DaiyamondoDasuto(p)));
                        break;
                    case 16:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Kousa(p)));
                        break;
                    case 17:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RetsuJitsu(p)));
                        break;
                    case 18:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Tsume(p)));
                        break;
                    case 19:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KyoKkou(p)));
                        break;
                    default:
                        break;
                }
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p,p,"PointPower",1));
            }
        }
    }

    public AbstractCard makeCopy() {
        return new HisouNoKenItem();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("HisouNoKenItem");
        NAME = HisouNoKenItem.cardStrings.NAME;
        DESCRIPTION = HisouNoKenItem.cardStrings.DESCRIPTION;
    }
}

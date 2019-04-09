package Thmod.Cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Relics.KoishisEye;
import basemod.helpers.BaseModCardTags;

public class Strike_Komeiji extends AbstractKomeijiCards{
    public static final String ID = "Strike_Komeiji";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 6;

    public Strike_Komeiji() {
        super("Strike_Komeiji", Strike_Komeiji.NAME,  1, Strike_Komeiji.DESCRIPTION, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY, CardSet_k.SATORI);
        this.baseDamage = 6;
        tags.add(BaseModCardTags.BASIC_STRIKE);
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public AbstractCard makeCopy() {
        if(AbstractDungeon.player != null) {
            if (AbstractDungeon.player.hasRelic(KoishisEye.ID)) {
                return new Strike_Koishi();
            }
        }
        return new Strike_Komeiji();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Strike_Komeiji");
        NAME = Strike_Komeiji.cardStrings.NAME;
        DESCRIPTION = Strike_Komeiji.cardStrings.DESCRIPTION;
    }
}

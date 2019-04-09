package Thmod.Cards;

import basemod.abstracts.CustomCard;

public abstract class AbstractSetCards extends CustomCard {
    public enum CardSet_k{
        OTHER, SATORI, KOISHI, REIMU, MARISA, AYA, KOMACHI, MEIRIN, SAKUYA, YOUMU, REMIRIA, IKU, TENSHI, UTSUHO, YUYUKO, YUKARI, SUIKA, REISEN, CIRNO, SUWAKO, ALICE, PATCHOULI, SANAE
    }
    public CardSet_k cardSetK;

    public AbstractSetCards(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target, CardSet_k cardSet_k) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.cardSetK = cardSet_k;
    }
}

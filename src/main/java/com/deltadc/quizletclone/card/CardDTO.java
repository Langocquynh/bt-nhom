package com.deltadc.quizletclone.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
    private Long card_id;

    private Long set_id;

    private String front_text;

    private String back_text;

    private String created_at;

    private String updated_at;

    private boolean is_known;
    public CardDTO(Card card) {
        this.card_id = card.getCard_id();
        this.set_id = card.getSet().getSet_id();
        this.front_text = card.getFront_text();
        this.back_text = card.getBack_text();
        this.created_at = card.getCreated_at();
        this.updated_at = card.getUpdated_at();
        this.is_known = card.is_known();
    }
}

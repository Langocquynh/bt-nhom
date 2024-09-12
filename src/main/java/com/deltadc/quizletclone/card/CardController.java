package com.deltadc.quizletclone.card;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/card")
@CrossOrigin(origins = "http://localhost:3000")
public class CardController {
    private final CardService cardService;

    private boolean isEmptyCardInput(Card card) {
        return card.getFront_text().isEmpty() || card.getBack_text().isEmpty();
    }

    // Lấy ra thông tin của tất cả card
    @GetMapping("/cards")
    public ResponseEntity<?> getAllCards() {
        return cardService.getAllCards();
    }

    // Lấy ra thông tin của 1 card theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getCardById(@PathVariable Long id) {
        return cardService.getCardById(id);
    }

    // Lấy tất cả cards trong 1 set
    @GetMapping("/{set_id}/cards")
    public ResponseEntity<?> getCardsInSet(@PathVariable("set_id") Long set_id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "30") int size) {
        return cardService.getCardsInSet(set_id, page, size);
    }

    // Tạo card mới
    @PostMapping("/{set_id}/create_card")
    public ResponseEntity<?> createCard(@PathVariable("set_id") Long set_id, @RequestBody Card card) {
        if(isEmptyCardInput(card)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("khong duoc de trong");
        }

        return cardService.createCard(card, set_id);
    }

    // Chỉnh sửa card theo id
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateCard(@PathVariable Long id, @RequestBody Card updatedCard) {
        if(isEmptyCardInput(updatedCard)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("khong duoc de trong");
        }

        return cardService.updateCard(id, updatedCard);
    }

    // Xóa card theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable Long id) {
        return cardService.deleteCard(id);
    }

    // tao mot list cac card theo set id
    @PostMapping("/{set_id}/create-cards")
    public ResponseEntity<?> createCards(@PathVariable("set_id") Long setId, @RequestBody List<Card> cardList) {
        boolean allCardsValid = cardList.stream().allMatch(this::isEmptyCardInput);

        if(!allCardsValid) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("khong duoc de trong");
        }

        return cardService.createCards(setId, cardList);
    }
}

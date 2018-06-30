package com.example.niemawidaha.practical_deckofcards.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SelectedCardsJsonResponse {

    // private members:
    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("deck_id")
    @Expose
    private String deckId;

    @SerializedName("remaining")
    @Expose
    private Integer remaining;

    @SerializedName("cards")
    @Expose
    private List<Card> cardList = null;

    // constructors:
    public SelectedCardsJsonResponse(){

    }

    public SelectedCardsJsonResponse( Integer remaining, List<Card> cardList) {
        this.remaining = remaining;
        this.cardList = cardList;
    }

    // getters:

    public Boolean getSuccess() {
        return success;
    }

    public String getDeckId() {
        return deckId;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    // setters:

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    // toString():
    @Override
    public String
    toString() {
        return "SelectedCardsJsonResponse{" +
                "success=" + success +
                ", deckId='" + deckId + '\'' +
                ", remaining=" + remaining +
                ", cardList=" + cardList +
                '}';
    }
}

package com.example.niemawidaha.practical_deckofcards.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShuffledDeckJsonResponse {

    // private members:

    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("deck_id")
    @Expose
    private String deckId;

    @SerializedName("shuffled")
    @Expose
    private Boolean shuffled;

    @SerializedName("remaining")
    @Expose
    private Integer remaining;

    // constructors:
    // default:
    public ShuffledDeckJsonResponse() {
    }

    // construct a deck with ids and remaining cards:

    public ShuffledDeckJsonResponse(String deckId, Integer remaining) {
        this.deckId = deckId;
        this.remaining = remaining;
    }

    public ShuffledDeckJsonResponse(Boolean success, String deckId, Boolean shuffled, Integer remaining) {
        this.success = success;
        this.deckId = deckId;
        this.shuffled = shuffled;
        this.remaining = remaining;
    }

    // getters + setters:

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public Boolean getShuffled() {
        return shuffled;
    }

    public void setShuffled(Boolean shuffled) {
        this.shuffled = shuffled;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    // toString():


    @Override
    public String toString() {
        return "ShuffledDeckJsonResponse{" +
                "success=" + success +
                ", deckId='" + deckId + '\'' +
                ", shuffled=" + shuffled +
                ", remaining=" + remaining +
                '}';
    }
}

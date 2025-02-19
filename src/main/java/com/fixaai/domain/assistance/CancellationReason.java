package com.fixaai.domain.assistance;

public enum CancellationReason {

    CLIENTE_DESISTIU("Client Changed his Mind"),
    OUTRO("Other");

    private final String translationToEnglish;

    CancellationReason(String translationToEnglish){
        this.translationToEnglish = translationToEnglish;
    }

    public String getTranslationToEnglish() {
        return translationToEnglish;
    }

    @Override
    public String toString() {
        return this.getTranslationToEnglish() + " (" + this.name() + ")";
    }
}

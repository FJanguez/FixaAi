package com.fixaai.domain.professional;

public enum Specialty {

    ELETRICISTA("Electrician"),
    HIDRAULICO("Hydraulic"),
    JARDINEIRO("Gardener"),
    PEDREIRO("Mason"),
    MONTADOR_DE_MOVEIS("Furniture Assembler");

    final String translationToEnglish;

    Specialty(String translationToEnglish) {
        this.translationToEnglish = translationToEnglish;
    }

    public String getTranslationToEnglish() {
        return translationToEnglish;
    }

    @Override
    public String toString() {
        return translationToEnglish;
    }
}
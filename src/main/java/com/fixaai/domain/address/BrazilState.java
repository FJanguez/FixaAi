package com.fixaai.domain.address;

public enum BrazilState {
    AC("Acre"),
    AL("Alagoas"),
    AP("Amapá"),
    AM("Amazonas"),
    BA("Bahia"),
    CE("Ceará"),
    ES("Espirito Santo"),
    GO("Goiás"),
    MA("Maranhão"),
    MT("Mato Grosso"),
    MS("Mato Grosso do Sul"),
    MG("Minas Gerais"),
    PA("Pará"),
    PB("Paraíba"),
    PR("Paraná"),
    PE("Pernambuco"),
    PI("Piauí"),
    RJ("Rio de Janeiro"),
    RN("Rio Grande do Norte"),
    RS("Rio Grande do Sul"),
    RO("Rondônia"),
    RR("Roraima"),
    SC("Santa Catarina"),
    SP("São Paulo"),
    SE("Sergipe"),
    TO("Tocantins");

    private final String name;

    BrazilState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static BrazilState fromAbbreviation(String abbreviation) {
        for (BrazilState state : BrazilState.values()) {
            if (state.name().equalsIgnoreCase(abbreviation)) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid abbreviation: " + abbreviation);
    }

    @Override
    public String toString() {
        return this.getName() + " (" + this.name() + ")";
    }
}
package com.fixaai.domain.assistance;

public enum AssistanceStatus {
    PENDENTE ("Pending"),
    EM_PROGRESSO ("In Progress"),
    CONCLUIDO ("Completed"),
    CANCELADO ("Cancelled");

    private final String status;

    AssistanceStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "AssistanceStatus{" +
                "status='" + status + '\'' +
                '}';
    }
}
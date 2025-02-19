package com.fixaai.domain.assistance;

import com.fixaai.domain.condominium.Condominium;
import com.fixaai.domain.professional.Professional;
import com.fixaai.domain.professional.Specialty;
import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "assistances")
public class Assistance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "condominium_id", nullable = false)
    private Condominium condominium;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professional professional;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "scheduled_date")
    private LocalDateTime scheduledDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssistanceStatus status;

    @Column(name = "completion_date")
    private LocalDateTime completionDate;

    @Column(name = "cancellation_reason", columnDefinition = "TEXT")
    @Enumerated(EnumType.STRING)
    private CancellationReason cancellationReason;

    @Column(name = "cancellation_date")
    private LocalDateTime cancellationDate;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    public Assistance() {
    }

    public Assistance(Long id, Condominium condominium, Professional professional, String description, LocalDateTime scheduledDate, AssistanceStatus status, LocalDateTime completionDate, CancellationReason cancellationReason, LocalDateTime cancellationDate, Specialty specialty) {
        this.id = id;
        this.condominium = condominium;
        this.professional = professional;
        this.description = description;
        this.scheduledDate = scheduledDate;
        this.status = status;
        this.completionDate = completionDate;
        this.cancellationReason = cancellationReason;
        this.cancellationDate = cancellationDate;
        this.specialty = specialty;
    }

    public Assistance(Long id, Condominium condominium, Professional professional, String description, LocalDateTime scheduledDate, Specialty specialty) {
        this.id = id;
        this.condominium = condominium;
        this.professional = professional;
        this.description = description;
        this.scheduledDate = scheduledDate;
        this.status = AssistanceStatus.PENDENTE;
        this.specialty = specialty;
        this.completionDate = null;
        this.cancellationReason = null;
        this.cancellationDate = null;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Assistance that = (Assistance) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Long getId() {
        return id;
    }

    public Condominium getCondominium() {
        return condominium;
    }

    public Professional getProfessional() {
        return professional;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public AssistanceStatus getStatus() {
        return status;
    }

    public CancellationReason getCancellationReason() {
        return cancellationReason;
    }

    public LocalDateTime getCancellationDate() {
        return cancellationDate;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void complete(){
        this.status = AssistanceStatus.CONCLUIDO;
        this.completionDate = LocalDateTime.now();
    }

    public void cancel(CancellationReason cancellationReason) {
        this.cancellationReason = cancellationReason;
        this.cancellationDate = LocalDateTime.now();
        this.status = AssistanceStatus.CANCELADO;
    }

    public void updateInformation(@Valid AssistanceUpdateDTO assistanceUpdateData) {
        if(assistanceUpdateData.description()!=null){
            this.description = assistanceUpdateData.description();
        }
        if(assistanceUpdateData.scheduledDate()!=null){
            this.scheduledDate = assistanceUpdateData.scheduledDate();
        }
    }
}


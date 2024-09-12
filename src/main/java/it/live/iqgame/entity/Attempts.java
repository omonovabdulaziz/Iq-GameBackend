package it.live.iqgame.entity;

import it.live.iqgame.entity.tmp.AbsUUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Attempts extends AbsUUID {
    @ManyToOne
    @JoinColumn(nullable = false)
    private Question question;
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
    @Column(nullable = false)
    private Boolean isFinished;
}
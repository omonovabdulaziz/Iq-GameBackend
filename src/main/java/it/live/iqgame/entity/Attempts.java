package it.live.iqgame.entity;

import it.live.iqgame.entity.enums.QuestionType;
import it.live.iqgame.entity.tmp.AbsUUID;
import jakarta.persistence.*;
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
    private Subject subject;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
    @Column(nullable = false)
    private Boolean isFinished;
}
package it.live.iqgame.entity;

import it.live.iqgame.entity.enums.QuestionType;
import it.live.iqgame.entity.tmp.AbsLong;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Question extends AbsLong {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    private String imgUrl;

    @Column(columnDefinition = "TEXT")
    private String questionValue;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Level level;

    @Column(nullable = false)
    private String correctAnswer;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(
            columnDefinition = "text[]"
    )
    private List<String> additiveAnswers;
}
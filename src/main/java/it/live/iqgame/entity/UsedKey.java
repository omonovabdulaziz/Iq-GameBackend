package it.live.iqgame.entity;

import it.live.iqgame.entity.tmp.AbsUUID;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "subject_id"}))
public class UsedKey extends AbsUUID {
    @ManyToOne
    private User user;
    @ManyToOne
    private Subject subject;
    private Long count;
}

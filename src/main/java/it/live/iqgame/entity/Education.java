package it.live.iqgame.entity;

import it.live.iqgame.entity.tmp.AbsLong;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Education extends AbsLong {
    private String name;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "education", cascade = CascadeType.ALL)
    private List<Subject> subjects;
}
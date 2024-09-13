package it.live.iqgame.entity;

import it.live.iqgame.entity.tmp.AbsLong;
import jakarta.persistence.*;
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
public class Level extends AbsLong {
    @Column(nullable = false)
    private String title;
    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Collection collection;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "level", cascade = CascadeType.ALL)
    private List<Question> questions;
}
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
public class Collection extends AbsLong {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subject;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL)
    private List<Level> levels;

}
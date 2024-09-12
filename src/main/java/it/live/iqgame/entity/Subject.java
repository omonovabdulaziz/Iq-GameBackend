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
public class Subject extends AbsLong {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String imgUrl;
    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Education education;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Collection> collections;
}
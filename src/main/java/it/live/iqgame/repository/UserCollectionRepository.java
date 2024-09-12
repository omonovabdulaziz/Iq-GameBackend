package it.live.iqgame.repository;

import it.live.iqgame.entity.Education;
import it.live.iqgame.entity.UserCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCollectionRepository extends JpaRepository<UserCollection, Long> {

}
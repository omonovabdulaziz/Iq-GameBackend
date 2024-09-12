package it.live.iqgame.repository;

import it.live.iqgame.entity.Attempts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttemptsRepository extends JpaRepository<Attempts, UUID> {

}
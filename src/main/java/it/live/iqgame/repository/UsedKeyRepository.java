package it.live.iqgame.repository;

import it.live.iqgame.entity.UsedKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsedKeyRepository extends JpaRepository<UsedKey, UUID> {
    Long countAllByUserIdAndSubjectId(Long user_id, Long subject_id);
    Optional<UsedKey> findByUserIdAndSubjectId(Long user_id, Long subject_id);
}

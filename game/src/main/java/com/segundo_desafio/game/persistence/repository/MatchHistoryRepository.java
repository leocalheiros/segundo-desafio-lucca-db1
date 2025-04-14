package com.segundo_desafio.game.persistence.repository;

import com.segundo_desafio.game.persistence.model.MatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MatchHistoryRepository extends JpaRepository<MatchHistory, UUID> {
}

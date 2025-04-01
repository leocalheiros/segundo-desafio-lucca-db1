package com.segundo_desafio.api.repository;

import com.segundo_desafio.api.domain.matchHistory.MatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MatchHistoryRepository extends JpaRepository<MatchHistory, UUID> {
}

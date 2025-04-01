package com.segundo_desafio.api.domain.matchHistory;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Table(name = "matchHistory")
@Entity
@Setter
@Getter
@AllArgsConstructor
public class MatchHistory {
    @Id
    @GeneratedValue
    private UUID id;

    private String playerOneMove;

    private String playerOneName;

    private String playerTwoMove;

    private String playerTwoName;

    private LocalDateTime date;

}

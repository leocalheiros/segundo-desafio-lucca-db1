package com.segundo_desafio.api.domain.matchhistory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "matchHistory")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

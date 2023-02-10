package com.team.final8teamproject.owner.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private Long id;

    @Column(nullable = false)
    private String trainername;

    @Column(nullable = false)
    private String storeName;

    @Column
    private String contents;
}

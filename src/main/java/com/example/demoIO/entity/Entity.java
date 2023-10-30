package com.example.demoIO.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@NoArgsConstructor
@ToString
@jakarta.persistence.Entity
public class Entity {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = SEQUENCE)
//    @SequenceGenerator(name = "seq_gen")
    @Column(name = "id", nullable = false)
    private Long id;

    String SR_NO;
    String NTN;
    String NAME;
    String BUSINESS_NAME;

}

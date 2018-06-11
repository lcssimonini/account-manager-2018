package br.com.iftm.model;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
    @SequenceGenerator(name = "account_sequence", sequenceName = "account_sequence", allocationSize = 1)
    private Long id;

    private Double balance;
}

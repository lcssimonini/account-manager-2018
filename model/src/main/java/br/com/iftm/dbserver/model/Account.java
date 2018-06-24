package br.com.iftm.dbserver.model;

import lombok.*;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
public class Account {

    private Integer id;

    private Double balance;
}

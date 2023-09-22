package org.zerock.ajax1.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb1_memo")
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;
    private String memoText;

}

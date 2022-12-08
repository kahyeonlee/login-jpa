package com.example.loginjpa.domain;

import com.example.loginjpa.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Hospital {
    @Id
    private Long id;
    private String roadNameAddress;
    private String hospitalName;

    //지연로딩 -필요한 시점에 연관된 데이터를 불러오는 것(fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY)
    private List<Review> reviews;

    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY)
    private List<Visit> visits;
}
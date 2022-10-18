package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Event")
public class EventEntity {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(columnDefinition =  "TEXT")
    private String content;

    private String startDate;

    private String endDate;

    private Boolean status;

    private int capacity;

    private String createAt;
    @Column(columnDefinition =  "TEXT")
    private String contentMarkdown;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    @OneToMany(mappedBy = "eventEntity")
    private List<EventParticipateEntity> eventParticipateEntities;

    @ManyToOne
    private SchoolEntity schoolEntity;
}

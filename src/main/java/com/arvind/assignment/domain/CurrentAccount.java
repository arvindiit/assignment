package com.arvind.assignment.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class CurrentAccount {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    
    private long balance;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Transaction> transactions = new HashSet<>();
    
    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime createdDate;
    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime modifiedDate;
    
}

package com.eurosoftware.property.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() { return id; }

    @NonNull
    private String name;

    @NonNull
    private String location;

    @NonNull
    private String type;

    private double price;

    @Nullable
    private Long ownerId;

}

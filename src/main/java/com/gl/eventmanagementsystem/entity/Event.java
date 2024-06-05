package com.gl.eventmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/*
    task is to add the required annotation to make this class a JPA entity
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event")
public class Event {
    /*
        task is to add a new column id of type Long.
        It should support the auto-generation of the primary key.
        Use appropriate annotation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /*
        task is to add a new column eventName of type String.
        It should not be null.
        Use appropriate annotation.
     */
    @Column(name = "eventName", nullable = false)
    private String eventName;
    /*
        task is to add a new column eventDate of type String.
        It should not be null.
        Use appropriate annotation.
     */
    @Column(name = "eventDate", nullable = false)
    private String eventDate;
    /*
        task is to add a new column location of type String.
        It should not be null.
        Use appropriate annotation.
     */
    @Column(name = "location", nullable = false)
    private String location;

    /*
        task is to establish the mapping between Event and Registration
        it should be a appropriate mapping as per the diagram
     */
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "event")
    private List<Registration> registrationList= new ArrayList<>();

}

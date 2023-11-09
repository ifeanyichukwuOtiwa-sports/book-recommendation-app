package com.gxstar.bookrecommendation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class User {

    @Id
    @GeneratedValue
    private UUID id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String email;
    @OneToOne
    @JoinColumn(name="access_role_id", referencedColumnName = "id")
    private AccessRole accessRole;


}

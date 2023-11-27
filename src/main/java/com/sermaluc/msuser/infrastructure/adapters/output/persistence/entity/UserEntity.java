package com.sermaluc.msuser.infrastructure.adapters.output.persistence.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "user")
public class UserEntity implements Serializable {

    @Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "userPhone", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserPhoneEntity> phone = new ArrayList<>();

    @Column(name = "created")
    private Date created;

    @Column(name = "modified")
    private Date modified;

    @Column(name = "lastLogin")
    private Date lastLogin;
    
    @Column(name = "token")
    private String token;

    @Column(name = "isActive")
    private boolean isActive;

	@PrePersist
	private void onCreated() {
		Instant instant = Instant.now();
		this.created = Date.from(instant);
		this.isActive = true;
	}

	@PreUpdate
	private void onUpdated() {
		Instant instant = Instant.now();
		this.modified = Date.from(instant);
	}

}

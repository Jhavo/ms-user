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
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity implements Serializable {

    @Id
    @Column(columnDefinition = "uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserPhoneEntity> phones = new ArrayList<>();

    private Date created;

    private Date modified;

    private Date lastLogin;

    private String token;

    private Boolean isActive;

	@PrePersist
	public void onCreated() {
		Instant instant = Instant.now();
		this.created = Date.from(instant);
        this.lastLogin = Date.from(instant);
		this.isActive = true;
	}

	@PreUpdate
    private void onUpdated() {
		Instant instant = Instant.now();
		this.modified = Date.from(instant);
	}

}

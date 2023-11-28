package com.sermaluc.msuser.infrastructure.adapters.output.persistence.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "userPhones")
public class UserPhoneEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private UserEntity user;

    private String number;

    private String cityCode;

    private String countryCode;

    private Date created;

    private Date modified;

    private Boolean isActive;

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

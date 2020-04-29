package com.xworkz.cm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "register_table")
@Setter
@Getter
@ToString
@NamedQueries({ @NamedQuery(name = "fetchUserId", query = "select u from RegisterEntity u where u.userId=:uID"),
		@NamedQuery(name = "fetchEmail", query = "select  e from RegisterEntity e where e.email=:email"),
		@NamedQuery(name = "fetchByEmail", query = "select l from RegisterEntity l where l.email=:email"),
		@NamedQuery(name = "updateLoginCount", query = "update  RegisterEntity set noOfLoginAttempt=:count where regID=:rid"),
		@NamedQuery(name = "resetPasswordAndLoginCount", query = "update  RegisterEntity set noOfLoginAttempt=:count, randomPassword=:pwd where regID=:rid") })
public class RegisterEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "auto", strategy = "increment")
	@GeneratedValue(generator = "auto")
	@Column(name = "REG_ID")
	private Integer regID;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "COURSE")
	private String course;

	@Column(name = "AGREE")
	private Boolean agree;

	@Column(name = "RANDOM_PASSWORD")
	private String randomPassword;

	@Column(name = "NO_OF_LOGIN_ATTEMPT")
	private Integer noOfLoginAttempt;

	public RegisterEntity() {
		super();
		System.out.println("Created\t" + this.getClass().getSimpleName());
	}

}

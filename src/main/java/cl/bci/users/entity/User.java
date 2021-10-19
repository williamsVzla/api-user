package cl.bci.users.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "USER")
@Data
public class User {
	
	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ID", updatable = false, nullable = false)
	String id;
	
	@Column(name = "NAME")
	String name;
	
	@Column(name = "EMAIL")
	String email;
	
	@Column(name = "ENC_PASSW")
	String password;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<Phone> phones;
	
	@Column(name = "CREATED")
	Date created;
	
	@Column(name = "MODIFIED")
	Date modified;
	
	@Column(name = "LAST_LOGIN")
	Date last_login;
	
	@Column(name = "IS_ACTIVE")
	Boolean isactive;
	

}

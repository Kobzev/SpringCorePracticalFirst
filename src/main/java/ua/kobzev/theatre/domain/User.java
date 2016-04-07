package ua.kobzev.theatre.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import ua.kobzev.theatre.adapter.LocalDateTimeAdapter;
import ua.kobzev.theatre.enums.UserRoles;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author kkobziev
 *
 */
@Entity
@Table(name = "users")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://ua.kobzev.theatre/soap")
public class User {
	@Id
	@GeneratedValue
    @XmlElement(required = true)
	private Integer id;

    @XmlElement(required = true)
	private String email;

    @XmlElement(required = true)
	private String name;

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @XmlElement(required = false, type = Date.class)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime birthDay;

	//Security
    @XmlTransient
	private String password;
	@Transient
	@JsonIgnore
    @XmlTransient
	private List<Role> roles;

	public User(String email, String name, LocalDateTime birthDay) {
		this.email = email;
		this.name = name;
		this.birthDay = birthDay;
		this.roles = Arrays.asList(new Role[] {new Role(name, UserRoles.RESGISTERED_USER)});
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", email='" + email + '\'' +
				", name='" + name + '\'' +
				", birthDay=" + birthDay +
				", password='" + password + '\'' +
				", roles=" + roles +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (id != null ? !id.equals(user.id) : user.id != null) return false;
		if (email != null ? !email.equals(user.email) : user.email != null) return false;
		if (name != null ? !name.equals(user.name) : user.name != null) return false;
		if (birthDay != null ? !birthDay.equals(user.birthDay) : user.birthDay != null) return false;
		if (password != null ? !password.equals(user.password) : user.password != null) return false;
		return roles != null ? roles.equals(user.roles) : user.roles == null;

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (birthDay != null ? birthDay.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (roles != null ? roles.hashCode() : 0);
		return result;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDateTime birthDay) {
		this.birthDay = birthDay;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public User() {

	}
}

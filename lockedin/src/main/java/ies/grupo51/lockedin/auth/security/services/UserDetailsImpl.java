package ies.grupo51.lockedin.auth.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ies.grupo51.lockedin.models.Guard;
import ies.grupo51.lockedin.models.Warden;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private String email;
	private long phone;
	private String birthdate;
    private long areaId; 

	public long getAreaId() {
		return areaId;
	}

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(long id, String name, String email, long phone, String birthdate, long areaId, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.birthdate = birthdate;
		this.areaId = areaId;
		this.password = password;
		this.authorities = authorities;

	}

	public UserDetailsImpl(long id, String name, String email, long phone, String birthdate, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.birthdate = birthdate;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(Guard user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getId(), 
				user.getName(),
				user.getEmail(),
				user.getPhone(),
				user.getBirthdate(),
				user.getAreaId(),
				user.getPassword(), 
				authorities);
	}

	public static UserDetailsImpl build(Warden user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getId(), 
				user.getName(),
				user.getEmail(),
				user.getPhone(),
				user.getBirthdate(),
				user.getPassword(), 
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getBirthdate() {
        return birthdate;
    }
    public String getEmail() {
        return email;
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public long getPhone() {
        return phone;
    }

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}
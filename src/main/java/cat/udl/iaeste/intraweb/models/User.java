package cat.udl.iaeste.intraweb.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Date;

/**
 * Created by eduard on 23/03/15.
 */

@Entity
public class User implements UserDetails {

    @Id
    @NotBlank(message = "Username cannot be blank")
    @Size(max = 256, message = "Content maximum length is {max} characters long")
    private String username;

    @Email(message = "Email shoud be valid")
    private String email;

    @URL
    private String imageUrl;

    private String birthdate;

    private String join_date;

    private String dismiss_date;

    @Size(max = 256, message = "Content maximum length is {max} characters long")
    private String name;

    @Size(max = 256, message = "Content maximum length is {max} characters long")
    private String surname;

    private int points;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList("USER");
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    @Override
    public String getUsername() { return username; }

    public String getEncodedUsername() {
        try {
            return  URLEncoder.encode(username, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            return username;
        }
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getBirthdate() {
        return birthdate;
    }

    public String getJoin_date() {
        return join_date;
    }

    public String getDismiss_date() {
        return dismiss_date;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setJoin_date(String join_date) {
        this.join_date = join_date;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setDismiss_date(String dismiss_date) {
        this.dismiss_date = dismiss_date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return getUsername();
    }
}

package com.deltadc.quizletclone.user;


import com.deltadc.quizletclone.auth.authtoken.ConfirmationToken;
import com.deltadc.quizletclone.folder.Folder;
import com.deltadc.quizletclone.review.Review;
import com.deltadc.quizletclone.set.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data // sinh ra getter, setter, toString,... tu dong
@Builder // tao doi tuong linh hoat hon (khong can dung vi tri tham so)
@NoArgsConstructor // tao constructor ko doi tu dong
@AllArgsConstructor
@Entity // danh dau la entity trong database
@Table(name = "user") // table voi ten la "user"
public class User implements UserDetails {

    public User(String username, String email, String password, Role role, Boolean enabled) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    // danh dau la id va auto_increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Set> sets;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Folder> folders;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    private Boolean enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ConfirmationToken> confirmationTokens;

    public String getName() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
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
//        setEnabled(false);
        return enabled;
    }

    @Override
    public String getPassword() {
        return password;
    }
}

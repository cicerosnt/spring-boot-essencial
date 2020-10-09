package br.cicerosnt.project3.service;

import br.cicerosnt.project3.model.Administrator;
import br.cicerosnt.project3.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomAdministratorDetailService implements UserDetailsService {
    private final AdministratorRepository adminRepository;

    @Autowired
    public CustomAdministratorDetailService(AdministratorRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Administrator administrator = Optional.ofNullable(adminRepository.findByuserName(userName)).orElseThrow(
                () -> new UsernameNotFoundException("Administrator not found")
        );
        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList(
                "ROLE_USER", "ROLE_ADMIN"
        );
        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList(
                "ROLE_USER"
        );

        return new User(administrator.getUserName(), administrator.getUserPassword(),
                administrator.isAdmin() ? authorityListAdmin : authorityListUser);
    }
}

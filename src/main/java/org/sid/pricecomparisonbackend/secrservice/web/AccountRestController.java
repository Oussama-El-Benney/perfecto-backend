package org.sid.pricecomparisonbackend.secrservice.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.sid.pricecomparisonbackend.secrservice.JWTUtil;
import org.sid.pricecomparisonbackend.secrservice.dto.LoginDto;
import org.sid.pricecomparisonbackend.secrservice.entities.AppRole;
import org.sid.pricecomparisonbackend.secrservice.entities.AppUser;
import org.sid.pricecomparisonbackend.secrservice.response.MessageResponse;
import org.sid.pricecomparisonbackend.secrservice.service.AccountService;
import org.sid.pricecomparisonbackend.secrservice.service.UserDetailsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class AccountRestController {
  private AccountService accountService;
  private UserDetailsServiceImpl userDetailsService;

  public AccountRestController(AccountService accountService, UserDetailsServiceImpl userDetailsService) {
    this.accountService = accountService;
    this.userDetailsService = userDetailsService;
  }

  @GetMapping(path = "/users")
//  @PostAuthorize("hasAuthority('CLIENT')")
  public List<AppUser> appUsers() {
    return accountService.listUsers();
  }

  @PostMapping(path = "/users")
  public AppUser saveUser(@RequestBody AppUser appUser) {
    return accountService.addNewUser(appUser);
  }

  @PostMapping(path = "/roles")
  public AppRole saveRole(@RequestBody AppRole appRole) {
    return accountService.addNewRole(appRole);
  }

  @PostMapping(path = "/addRoleToUser")
  public void addRoleToUser(@RequestBody RoleUserForm roleUserForm) {
    accountService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRoleName());
  }

  @PostMapping(path = "/login")
  public UserDetails login(@RequestBody LoginDto loginDto) {
//  public UserDetails login(@RequestBody RoleUserForm roleUserForm) {
    return userDetailsService.loadUserByUsername(loginDto.getUsername());
  }

  @PostMapping(path = "/signup")
  public ResponseEntity<?> signup(@RequestBody LoginDto loginDto) {
//    boolean bool =accountService.userExists(loginDto.getUsername());

    if (accountService.userExists(loginDto.getUsername())) {
      return ResponseEntity.ok(new MessageResponse("User Already Exists!"));
    } else {
      AppUser appUser = new AppUser();
      appUser.setUsername(loginDto.getUsername());
      appUser.setPassword(loginDto.getPassword());
      accountService.addNewUser(appUser);
      accountService.addRoleToUser(appUser.getUsername(), "CLIENT");
      return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
  }

  @PostMapping(path = "/logine")
  public HttpServletResponse authenticate(HttpServletRequest request, HttpServletResponse response) {
    return response;
  }

  @GetMapping(path = "/refreshToken")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String auhToken = request.getHeader(JWTUtil.AUTH_HEADER);
    if (auhToken != null && auhToken.startsWith(JWTUtil.PREFIX)) {
      try {
        String jwt = auhToken.substring(JWTUtil.PREFIX.length());
        Algorithm algorithm = Algorithm.HMAC256(JWTUtil.SECRET);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
        String username = decodedJWT.getSubject();
        AppUser appUser = accountService.loadUserByUsername(username);
        String jwtAccessToken = JWT.create()
                .withSubject(appUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWTUtil.EXPIRE_ACCESS_TOKEN))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", appUser.getAppRoles().stream().map(r -> r.getRoleName()).collect(Collectors.toList()))
                .sign(algorithm);
        Map<String, String> idToken = new HashMap<>();
        idToken.put("access-token", jwtAccessToken);
        idToken.put("refresh-token", jwt);
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(), idToken);
      } catch (Exception e) {
        response.setHeader("error-message", e.getMessage());
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
      }
    } else {
      throw new RuntimeException("Refresh Token Required!!!");
    }
  }

  @GetMapping(path = "/profile")
  public AppUser profile(Principal principal) {
    return accountService.loadUserByUsername(principal.getName());
  }
}

@Data
class RoleUserForm {
  private String username;
  private String roleName;

}

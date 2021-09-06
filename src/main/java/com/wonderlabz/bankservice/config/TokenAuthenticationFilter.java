//package com.wonderlabz.bankservice.config;
//
//
//import com.wonderlabz.bankservice.entities.Users;
//import com.wonderlabz.bankservice.repositories.UsersRepository;
//import com.wonderlabz.bankservice.services.AuthUserManagement;
//import com.wonderlabz.bankservice.util.JwtDecoder;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Slf4j
//@Component
//public class TokenAuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtDecoder jwtDecoder;
//
//    @Autowired
//    private AuthUserManagement authUserManagement;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        String authorizationHeader = httpServletRequest.getHeader("Authorization");
//        String token = null;
//        String claims = null;
//        String email = null;
//        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
//            token = authorizationHeader.substring(7);
//            claims = jwtDecoder.decodeJwtToken(token);
//            log.info("extracted claims are : "+claims);
//            //get just the email from this. Bad I know but time...
//
//        }
//
//        if(claims != null && SecurityContextHolder.getContext().getAuthentication() == null){
//            email = claims.substring(7,claims.length()-1);
//            log.info("the final email is : "+email);
//            UserDetails user = authUserManagement.loadUserByUsername(email);
//            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//            usernamePasswordAuthenticationToken
//                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
//            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//        }
//        filterChain.doFilter(httpServletRequest, httpServletResponse);
//    }
//}

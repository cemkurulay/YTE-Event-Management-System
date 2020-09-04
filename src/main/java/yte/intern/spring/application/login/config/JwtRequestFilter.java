package yte.intern.spring.application.login.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import yte.intern.spring.application.usercrud.SystemUser;
import yte.intern.spring.application.login.util.JwtUtil;
import yte.intern.spring.application.usercrud.CustomUserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

	@Value(value = "${security.jwt.secret-key}")
	private String secretKey;

	private final CustomUserDetailsService userDetailsManager;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String authentication = request.getHeader("Authorization");
		if (authentication != null && authentication.startsWith("Bearer")) {
			String jwtToken = authentication.substring(7);
			String username = JwtUtil.extractUsername(jwtToken, secretKey);

			SystemUser systemUserDetails = (SystemUser) userDetailsManager.loadUserByUsername(username);

			if (SecurityContextHolder.getContext().getAuthentication() == null) {
				var token = new UsernamePasswordAuthenticationToken(systemUserDetails, null, systemUserDetails.getAuthorities());
				token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(token);
			}
		}

		filterChain.doFilter(request, response);
	}
}

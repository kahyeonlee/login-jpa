package com.example.loginjpa.configuration;

import com.example.loginjpa.domain.User;
import com.example.loginjpa.service.UserService;
import com.example.loginjpa.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {
    private final String secretKey;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //개찰구 역할
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorizationHeader:{}", authorizationHeader);

        //언제 막아야하는지
        //1.토큰이 없을 경우(null) - bearer
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        //토큰 분리
        String token;
        try {
            token = authorizationHeader.split(" ")[1];
        }catch (Exception e){
            log.error("token 추출에 실패 했습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        //2.만료된 토큰을 가져올때 - 토큰 만료됬는지 확인
        if(JwtTokenUtil.isExpired(token, secretKey)) {
            filterChain.doFilter(request, response);
            return;
        }
        //3.토큰이 유효하지 않을때
        // Token- Claim에서 UserName꺼내기
        String userName = JwtTokenUtil.getUserName(token, secretKey);
        log.info("userName:{}", userName);

        // username으로 User 찾아오기
        User user = userService.getUserByUserName(userName);
        log.info("userRole:{}", user.getRole());

        // 권한을 부여, 막기
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), null, List.of(new SimpleGrantedAuthority(user.getRole().name())));

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken); // 권한 부여
        filterChain.doFilter(request, response);
    }
}

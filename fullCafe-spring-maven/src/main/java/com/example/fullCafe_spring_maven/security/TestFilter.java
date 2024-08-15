package com.example.fullCafe_spring_maven.security;

import com.example.fullCafe_spring_maven.firebase.FirebaseAuthentication;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import java.io.IOException;

public class TestFilter extends AbstractPreAuthenticatedProcessingFilter {
    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return null;
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return null;
    }

    // Bearer 123만 허용
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request; // 다운 캐스팅
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        String idToken = httpRequest.getHeader("Authorization");
        try{
            if(idToken != null && idToken.startsWith("Bearer ")){
                idToken = idToken.substring(7);
                if(!idToken.equalsIgnoreCase("123")){
                    throw new RuntimeException("123이 아님");
                }
                String email = "test@dfsdf"; String uid = "sdfsdf";
                SecurityContextHolder.getContext().setAuthentication(new FirebaseAuthentication(email,uid,true));
            }
        } catch(Exception e){
            SecurityContextHolder.clearContext();
            System.out.println(e.getMessage());
        }

        chain.doFilter(request,response);
    }
}

package com.example.BankMega.Ascore.config;

import java.net.URI;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletContext;

@Component
public class NewCustomValidation implements AuthenticationProvider {
    @Autowired
    private ServletContext context;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            System.out.println(context.getVirtualServerName());
            Boolean cek = post("http://10.14.15.24:8081/api/checkLogin",
                    "{\"userName\":" + authentication.getName() + ",\"passWord\":\"" + authentication.getCredentials()
                            + "\"}");
            // Boolean cek = post("/api/test",
            // authentication.getName());
            // Boolean cek = post("http://localhost:8080/api/test",
            // authentication.getName());
            // Boolean cek = post("http://localhost:8083/api/test",
            // authentication.getName());
            if (cek) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                return new UsernamePasswordAuthenticationToken(authentication.getName(),
                        authentication.getCredentials().toString(), authorities);
            } else {
                throw new BadCredentialsException("Tidak Diizinkan");
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            throw new BadCredentialsException(e.getMessage());
        }

        // System.out.println(authentication.getName());

    }

    private static final String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }

    public Boolean post(String uri, String data) throws Exception {
        System.out.println(data);
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .POST(BodyPublishers.ofString(data))
                .header("Content-type", "application/json")
                .header("Authorization", getBasicAuthenticationHeader("CardSysAscore", "Pass1234!!"))
                .build();

        HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body().toString());
        Boolean hasil = response.body().toString().contains("200") && response.statusCode() == 200;
        return hasil;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}

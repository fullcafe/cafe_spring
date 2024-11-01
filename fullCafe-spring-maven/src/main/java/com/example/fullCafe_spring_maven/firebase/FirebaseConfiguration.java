package com.example.fullCafe_spring_maven.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;

@Configuration
@ConditionalOnProperty(name = "firebase.enabled", havingValue = "true", matchIfMissing = true)
public class FirebaseConfiguration {

    @PostConstruct
    public void init() {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("fullCafe-spring-maven/src/main/resources/serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IllegalStateException exception){
            // 개발용 한정
        } catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }
}

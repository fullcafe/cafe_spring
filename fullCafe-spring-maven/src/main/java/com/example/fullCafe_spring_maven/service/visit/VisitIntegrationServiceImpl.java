package com.example.fullCafe_spring_maven.service.visit;

import com.example.fullCafe_spring_maven.service.cafe.CafeService;
import com.example.fullCafe_spring_maven.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitIntegrationServiceImpl implements VisitIntegrationService{
    private final VisitService visitService;
    private final UserService userService;
    private final CafeService cafeService;

}

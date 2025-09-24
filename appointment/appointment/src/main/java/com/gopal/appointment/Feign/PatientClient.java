package com.gopal.appointment.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("PATIENT-SERVICE")
public interface PatientClient {

    @GetMapping("/auth/getById/{id}")
    boolean verifyUser(@PathVariable String id);

}

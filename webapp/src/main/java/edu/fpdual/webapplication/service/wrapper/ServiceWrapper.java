package edu.fpdual.webapplication.service.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ServiceWrapper<T> {
    private String serviceName;
    private T service;

}

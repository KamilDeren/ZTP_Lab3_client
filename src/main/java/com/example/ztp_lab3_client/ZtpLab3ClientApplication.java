package com.example.ztp_lab3_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ZtpLab3ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZtpLab3ClientApplication.class, args);

        RestTemplate restTemplate = new RestTemplate();

        String baseUrl = "http://127.0.0.1:5000";

        getEmployees(restTemplate, baseUrl);
        getEmployeeById(restTemplate, baseUrl, 1);
        addEmployee(restTemplate, baseUrl, 3);
        getEmployees(restTemplate, baseUrl);
        updateEmployee(restTemplate, baseUrl, 3);
        getEmployees(restTemplate, baseUrl);
    }

    // Metoda do pobierania wszystkich pracowników
    private static void getEmployees(RestTemplate restTemplate, String baseUrl) {
        String url = baseUrl + "/employees";
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);
    }

    // Metoda do pobierania pracownika o podanym id
    private static void getEmployeeById(RestTemplate restTemplate, String baseUrl, int id) {
        String url = baseUrl + "/employees/" + id;
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);
    }

    // Metoda do dodawania pracownika
    private static void addEmployee(RestTemplate restTemplate, String baseUrl, int id) {
        String url = baseUrl + "/employees";
        String requestBody = "[{\"id\":" + id +",\"imie\":\"Anna\",\"nazwisko\":\"Kowalska\",\"stanowisko\":\"Analyst\"}]";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        restTemplate.postForObject(url, requestEntity, String.class);
    }

    // Metoda do aktualizacji danych pracownika
    private static void updateEmployee(RestTemplate restTemplate, String baseUrl, int id) {
        String url = baseUrl + "/employees/" + id;
        String requestBody = "{\"imie\":\"Maria\",\"nazwisko\":\"Nowak\",\"stanowisko\":\"Analyst\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        restTemplate.put(url, requestEntity);
    }
}

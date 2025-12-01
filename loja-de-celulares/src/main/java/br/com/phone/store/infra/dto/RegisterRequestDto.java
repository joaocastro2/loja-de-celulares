package br.com.phone.store.infra.dto;

public record RegisterRequestDto(String userName, String userCpf, String userEmail, String userPassword) {
}

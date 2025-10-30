package br.com.phone.store.infra.dto;

public record RegisterRequestDto(String userName, String userSsn, String userEmail, String userPassword) {
}

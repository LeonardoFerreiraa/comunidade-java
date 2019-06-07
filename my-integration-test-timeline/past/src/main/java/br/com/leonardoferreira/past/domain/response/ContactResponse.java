package br.com.leonardoferreira.past.domain.response;

import lombok.Data;

@Data
public class ContactResponse {

    private Long id;

    private String name;

    private String email;

    private String createdAt;

    private String updatedAt;

}

package br.com.leonardoferreira.contact.controller;

import br.com.leonardoferreira.contact.domain.request.ContactRequest;
import br.com.leonardoferreira.contact.domain.response.ContactResponse;
import br.com.leonardoferreira.contact.service.ContactService;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(final ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<ContactResponse> findAll() {
        return contactService.findAll();
    }

    @GetMapping("/{id}")
    public ContactResponse findById(@PathVariable final Long id) {
        return contactService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody final ContactRequest contactRequest) {
        Long id = contactService.create(contactRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/contacts/{id}")
                .build(id);

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable final Long id,
                                    @Valid @RequestBody final ContactRequest contactRequest) {
        contactService.update(id, contactRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

package org.spring.as.quickstarts.kitchensink.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.as.quickstarts.kitchensink.exception.ResourceNotFoundException;
import org.spring.as.quickstarts.kitchensink.model.request.MemberRequest;
import org.spring.as.quickstarts.kitchensink.model.response.RestApiResponse;
import org.spring.as.quickstarts.kitchensink.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@Validated
@CrossOrigin("*")
public class MemberController {

    private final MemberService memberService;

    /**
     * Create Member.
     */
    @Operation(
            summary = "Create member",
            description = "Create member with basic details",
            tags = {"Member"}
    )
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RestApiResponse> createMember(
            @Valid @RequestBody MemberRequest memberRequest
            ) throws IOException {
        log.info("Received member request with request: {}", memberRequest);

        var memberResponse = memberService.create(memberRequest);
        log.info("Member created successfully: {}", memberResponse);
        var response = new RestApiResponse("Record inserted successfully.", memberResponse, true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Fetch all members.
     */
    @Operation(
            summary = "All members",
            description = "Fetch all members",
            tags = {"Member"}
    )
    @GetMapping(
            path = "/list",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> list() {
        log.info("Received request to fetch all members");
        var membersResponse = memberService.list();
        var response = new RestApiResponse("Record fetch successfully.", membersResponse, true);
        log.info("{} Member(s) fetched successfully", membersResponse.size());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Fetch member by email.
     */
    @Operation(
            summary = "Fetch member by email",
            description = "Fetch member email",
            tags = {"Member"}
    )
    @GetMapping(
            consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public ResponseEntity<RestApiResponse> fetchByEmail(
            @RequestParam(value = "email", required = true) String email
    ) throws ResourceNotFoundException {
        log.info("Received request to find member with email: {}", email);
        var memberResponse = memberService.fetchByEmail(email);
        var response = new RestApiResponse("Record fetch successfully.", memberResponse, true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Deletes a member by its ID.
     */
    @Operation(
            summary = "Delete member by Id",
            description = "Delete member by Id",
            tags = {"Member"}
    )
    @DeleteMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RestApiResponse> delete(
            @PathVariable("id") @Min(1) Long id
    ) throws ResourceNotFoundException {
        log.info("Received request to delete member with id: {}", id);
        var memberResponse = memberService.deleteById(id);
        log.info("Member deleted successfully: {}", memberResponse);
        var response = new RestApiResponse("Record deleted successfully.", memberResponse, true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Fetch member by email.
     */
    @Operation(
            summary = "Fetch member by email",
            description = "Fetch member email",
            tags = {"Member"}
    )
    @GetMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public ResponseEntity<RestApiResponse> fetchById(
            @PathVariable(value = "id", required = true) Long id
    ) throws ResourceNotFoundException {
        log.info("Received request to find member with id: {}", id);
        var memberResponse = memberService.fetchById(id);
        var response = new RestApiResponse("Record fetch successfully.", memberResponse, true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

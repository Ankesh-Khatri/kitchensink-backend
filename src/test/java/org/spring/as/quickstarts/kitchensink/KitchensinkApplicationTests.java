package org.spring.as.quickstarts.kitchensink;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spring.as.quickstarts.kitchensink.controller.MemberController;
import org.spring.as.quickstarts.kitchensink.model.request.MemberRequest;
import org.spring.as.quickstarts.kitchensink.model.response.MemberResponse;
import org.spring.as.quickstarts.kitchensink.model.response.RestApiResponse;
import org.spring.as.quickstarts.kitchensink.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class KitchensinkApplicationTests {

	@Mock
	private MemberService memberService;

	@InjectMocks
	private MemberController memberController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateMember() throws IOException {
		MemberRequest memberRequest = new MemberRequest();
		MemberResponse memberResponse = new MemberResponse();
		lenient().when(memberService.create(any(MemberRequest.class))).thenReturn(memberResponse);

		ResponseEntity<RestApiResponse> responseEntity = memberController.createMember(memberRequest);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Record inserted successfully.", Objects.requireNonNull(responseEntity.getBody()).getMessage());

	}

	@Test
	void testListMembers() {
		List<MemberResponse> mockMembers = List.of(new MemberResponse());
		lenient().when(memberService.list()).thenReturn(mockMembers);

		ResponseEntity<?> responseEntity = memberController.list();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Record fetch successfully.", ((RestApiResponse) Objects.requireNonNull(responseEntity.getBody())).getMessage());
	}

	@Test
	void testFetchByEmail() throws Exception {
		String email = "test@example.com";
		MemberResponse mockMember = new MemberResponse();
		lenient().when(memberService.fetchByEmail(email)).thenReturn(mockMember);

		ResponseEntity<RestApiResponse> responseEntity = memberController.fetchByEmail(email);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Record fetch successfully.", Objects.requireNonNull(responseEntity.getBody()).getMessage());
	}

	@Test
	void testDeleteMember() throws Exception {
		Long memberId = 1L;
		MemberResponse mockMember = new MemberResponse();
		lenient().when(memberService.deleteById(memberId)).thenReturn(mockMember);

		ResponseEntity<RestApiResponse> responseEntity = memberController.delete(memberId);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Record deleted successfully.", Objects.requireNonNull(responseEntity.getBody()).getMessage());
	}

	@Test
	void testFetchById() throws Exception {
		Long memberId = 1L;
		MemberResponse mockMember = new MemberResponse();
		lenient().when(memberService.fetchById(memberId)).thenReturn(mockMember);

		ResponseEntity<RestApiResponse> responseEntity = memberController.fetchById(memberId);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Record fetch successfully.", Objects.requireNonNull(responseEntity.getBody()).getMessage());
	}

}

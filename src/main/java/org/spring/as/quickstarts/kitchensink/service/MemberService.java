package org.spring.as.quickstarts.kitchensink.service;

import org.spring.as.quickstarts.kitchensink.exception.ResourceNotFoundException;
import org.spring.as.quickstarts.kitchensink.model.request.MemberRequest;
import org.spring.as.quickstarts.kitchensink.model.response.MemberResponse;

import java.util.List;

public interface MemberService {
    MemberResponse create(MemberRequest memberRequest);
    MemberResponse fetchByEmail(String email) throws ResourceNotFoundException;
    List<MemberResponse> list();
    MemberResponse deleteById(Long id) throws ResourceNotFoundException;
    MemberResponse fetchById(Long id) throws ResourceNotFoundException;
}

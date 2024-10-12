package org.spring.as.quickstarts.kitchensink.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.spring.as.quickstarts.kitchensink.entity.MemberEntity;
import org.spring.as.quickstarts.kitchensink.exception.ResourceNotFoundException;
import org.spring.as.quickstarts.kitchensink.model.request.MemberRequest;
import org.spring.as.quickstarts.kitchensink.model.response.MemberResponse;
import org.spring.as.quickstarts.kitchensink.repository.MemberRepository;
import org.spring.as.quickstarts.kitchensink.util.CommonUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberResponse create(MemberRequest memberRequest) {
        log.debug("Starting member creation process with request: {}", memberRequest);

        var memberEntity = CommonUtils.objectToPojoConverter(memberRequest, MemberEntity.class);
        memberEntity = memberRepository.save(Objects.requireNonNull(memberEntity));

        log.debug("Saved MemberEntity to the repository: {}", memberEntity);
        return CommonUtils.objectToPojoConverter(memberEntity, MemberResponse.class);
    }

    @Override
    public MemberResponse fetchByEmail(String email) throws ResourceNotFoundException {
        log.debug("Fetching member with email: {}", email);
        var memberEntity = memberRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("Member not found with email: {}", email);
                    return new ResourceNotFoundException("Resource Not Found");
                });
        var memberResponse = CommonUtils.objectToPojoConverter(memberEntity, MemberResponse.class);
        log.debug("User fetched successfully with email: {}", email);
        return memberResponse;
    }

    @Override
    public List<MemberResponse> list() {
        log.debug("Received request to fetch all members");
        var memberEntities = memberRepository.findAll();
        return CommonUtils.objectToPojoConverter(memberEntities, MemberResponse.class);
    }

    @Override
    public MemberResponse deleteById(Long id) throws ResourceNotFoundException {
        log.debug("Attempting to delete member with id: {}", id);
        var memberEntity = memberRepository.findById(id).orElseThrow(() -> {
            log.error("Member with id {} not found", id);
            return new ResourceNotFoundException("Resource Not Found");
        });
        log.debug("Member with id {} found: {}", id, memberEntity);

        memberRepository.delete(memberEntity);
        log.debug("File with id {} deleted", id);

        var memberResponse = CommonUtils.objectToPojoConverter(memberEntity, MemberResponse.class);
        log.debug("Converted member entity to response: {}", memberResponse);
        return memberResponse;
    }

    @Override
    public MemberResponse fetchById(Long id) throws ResourceNotFoundException {
        log.debug("Fetching member with id: {}", id);
        var memberEntity = memberRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Member not found with id: {}", id);
                    return new ResourceNotFoundException("Resource Not Found");
                });
        var memberResponse = CommonUtils.objectToPojoConverter(memberEntity, MemberResponse.class);
        log.debug("User fetched successfully with id: {}", id);
        return memberResponse;
    }
}

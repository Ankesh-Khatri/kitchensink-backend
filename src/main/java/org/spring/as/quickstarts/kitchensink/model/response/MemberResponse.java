package org.spring.as.quickstarts.kitchensink.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Data
public class MemberResponse {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
}

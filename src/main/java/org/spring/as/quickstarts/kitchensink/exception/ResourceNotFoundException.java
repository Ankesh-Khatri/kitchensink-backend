package org.spring.as.quickstarts.kitchensink.exception;

public class ResourceNotFoundException extends KitchensinkException {
    private static final long serialVersionUID = 5272447637711039999L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

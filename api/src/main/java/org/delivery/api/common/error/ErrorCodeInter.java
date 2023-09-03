package org.delivery.api.common.error;

public interface ErrorCodeInter {

    Integer getHttpStatusCode();

    Integer getErrorCode();

    String getDescription();

}

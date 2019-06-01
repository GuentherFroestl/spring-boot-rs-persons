package com.mikrowerk.addressms.entity;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author gfr
 */
@Data
@Builder
public class ErrorMessage {
    
    private String message;
    
}

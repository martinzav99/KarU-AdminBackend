package com.ungspp1.gadminbackend.restResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CachableBodyResponse<T> extends BaseBodyResponse<T> {
    public enum CacheStatus {
        CURRENT, CACHED, UNAVAILABLE
    }
    
    CacheStatus cacheStatus;
    
    public CachableBodyResponse<T> with(CacheStatus cacheStatus) {
    	this.cacheStatus = cacheStatus;
    	return this;
    }
}

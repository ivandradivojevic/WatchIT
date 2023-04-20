package com.rolex.watchit.service;

import com.rolex.watchit.dtos.WatchDto;
import com.rolex.watchit.model.Watch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WatchService {

    void deleteById(Long id);
    Page<WatchDto> getPageOfWatches(Pageable pageable);
    Page<WatchDto> getPageOfWatches(Long userId,Pageable pageable);
    Watch addWatch(Long id,WatchDto watchDto);
}

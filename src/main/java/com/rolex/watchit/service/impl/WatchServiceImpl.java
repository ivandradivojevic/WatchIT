package com.rolex.watchit.service.impl;

import com.rolex.watchit.dtos.WatchDto;
import com.rolex.watchit.mappers.WatchDtoMapper;
import com.rolex.watchit.model.User;
import com.rolex.watchit.model.Watch;
import com.rolex.watchit.repository.UserRepository;
import com.rolex.watchit.repository.WatchRepository;
import com.rolex.watchit.service.WatchService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class WatchServiceImpl implements WatchService {
    private final UserRepository userRepository;
    private final WatchRepository watchRepository;
    private final WatchDtoMapper mapper;


    @Override
    public void deleteById(Long id) {
        watchRepository.deleteById(id);
    }

    @Override
    public Page<WatchDto> getPageOfWatches(Pageable pageable) {
        Page page = watchRepository.findAll(pageable);
        return new PageImpl<>(mapper.watchToDto(page.getContent()),pageable,page.getTotalElements());
    }
    @Override
    public Page<WatchDto> getPageOfWatches(Long userId,Pageable pageable) {
        Page<Watch> page = watchRepository.findAllByUserId(userId,pageable);
        return new PageImpl<>(mapper.watchToDto(page.getContent()),pageable,page.getTotalElements());
    }

    @Override
    public Watch addWatch(Long id, WatchDto watchDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User with ID " + id +"doesn't exist."));
        Watch watch = mapper.dtoToWatch(watchDto);
        watch.setUser(user);
        return watchRepository.save(watch);
    }
}

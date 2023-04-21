package com.rolex.watchit.controller;


import com.rolex.watchit.auth.AuthenticationService;
import com.rolex.watchit.dtos.WatchDto;
import com.rolex.watchit.model.Watch;
import com.rolex.watchit.service.WatchService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/watches")
@AllArgsConstructor
public class WatchController {
    private final WatchService watchService;
    private final AuthenticationService authenticationService;


    @GetMapping("/user")
    public Page<WatchDto> getWatchesOfUser(@RequestParam Long id,@RequestParam(name = "pageNumber",defaultValue = "0") Integer pageNumber,
                                           @RequestParam(name = "pageSize",defaultValue = "1") Integer pageSize){
        return watchService.getPageOfWatches(id,PageRequest.of(pageNumber,pageSize));
    }

    @PostMapping
    public Watch saveNewWatch(HttpServletRequest httpServletRequest, @RequestBody WatchDto watchDto) throws Exception {
        Long id = authenticationService.getIdOfUser(httpServletRequest);
        return watchService.addWatch(id,watchDto);
    }

    @GetMapping
    public Page<WatchDto> getPageOfWatches(@RequestParam(name = "pageNumber",defaultValue = "0") Integer pageNumber,
                                           @RequestParam(name = "pageSize",defaultValue = "1") Integer pageSize){
        return watchService.getPageOfWatches(PageRequest.of(pageNumber,pageSize));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWatch(@PathVariable Long id){
        watchService.deleteById(id);
        return ResponseEntity.ok("The Watch was successfully deleted");
    }


}

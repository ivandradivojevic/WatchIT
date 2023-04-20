package com.rolex.watchit.mappers;


import com.rolex.watchit.dtos.WatchDto;
import com.rolex.watchit.model.Watch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Mapper
public interface WatchDtoMapper {

    @Mappings({
            @Mapping(target = "pathToImage", source = "image", qualifiedByName = "stringToPath")})
    Watch dtoToWatch(WatchDto watchDto);
    List<Watch> dtoToWatch(List<WatchDto> watchDtoList);
    @Mappings({
            @Mapping(target = "image", source = "pathToImage", qualifiedByName = "pathToString")})
    WatchDto watchToDto(Watch watch);
    List<WatchDto> watchToDto(List<Watch> watchList);


    @Named("stringToPath")
    default String stringToPath(String image) {
        if (image == null || image.equals("")) return null;

        byte[] imageBytes;
        imageBytes = Base64.getDecoder().decode(image.substring(23));
        String name = "images/" + System.currentTimeMillis() + ".jpg";
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(name);
            fos.write(imageBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name;
    }

    @Named("pathToString")
    default String pathToString(String pathToImage){
        try {
            return Base64
                    .getEncoder()
                    .encodeToString(Files.readAllBytes(Paths.get(pathToImage)));
            } catch (IOException e) {
                throw new RuntimeException(e);
        }
    }
}

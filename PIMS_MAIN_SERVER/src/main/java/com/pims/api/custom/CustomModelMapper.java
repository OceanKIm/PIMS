package com.pims.api.custom;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomModelMapper{

    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * 기본 Mapper
     *
     * @return  ModelMapper
     */
    public ModelMapper mapper() {
        return modelMapper;
    }

    /**
     * STANDARD : 정확히 일치하는 필드만 맵핑해준다.
     *
     * @return  ModelMapper
     */
    public ModelMapper strictMapper() {
        modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    /**
     * STRICT : 지능적으로 맵핑 해준다.
     *
     * @return  ModelMapper
     */
    public ModelMapper standardMapper() {
        modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        return modelMapper;
    }

    /**
     * LOOSE : 느슨하게 맵핑해준다.
     *
     * @return  ModelMapper
     */
    public ModelMapper looseMapper() {
        modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }

}
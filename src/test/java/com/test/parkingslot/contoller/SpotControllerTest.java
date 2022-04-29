package com.test.parkingslot.contoller;

import com.test.parkingslot.controller.SpotController;
import com.test.parkingslot.dto.SpotsNumberDto;
import com.test.parkingslot.mapper.SpotDtoMapper;
import com.test.parkingslot.model.Spot;
import com.test.parkingslot.repository.SpotRepository;
import com.test.parkingslot.service.SpotService;
import com.test.parkingslot.service.impl.SpotServiceImpl;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = {
        SpotControllerTest.Config.class,
        SpotController.class,
        SpotServiceImpl.class
})
public class SpotControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SpotService spotService;
    @MockBean
    private SpotRepository repository;

    @Test
    void whenGetAllFreeSpotsThenOkTestWithinTask() throws Exception {
        //given
        final String url = "/spots?status=FREE";

        ReflectionTestUtils.setField(spotService, "bookedSpots", Set.of("M123КК"));

        Spot spot1 = new Spot();
        spot1.setNumber("1");
        spot1.setAvailableStatus(true);

        Spot spot2 = new Spot();
        spot2.setNumber("2");
        spot2.setAvailableStatus(true);

        List<Spot> allFreeSpots = Arrays.asList(spot1, spot2);

        SpotsNumberDto spotsNumberDto = new SpotsNumberDto().setNumber("1");
        //when
        when(repository.findAllByAvailableStatusIsTrue()).thenReturn(allFreeSpots);

        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value(spotsNumberDto.getNumber()));
        //then
        verify(repository, times(1)).findAllByAvailableStatusIsTrue();
    }

    static class Config {
        @Bean
        SpotDtoMapper mapper() {
            return Mappers.getMapper(SpotDtoMapper.class);
        }

    }
}

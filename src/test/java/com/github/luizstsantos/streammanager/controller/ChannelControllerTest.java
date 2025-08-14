package com.github.luizstsantos.streammanager.controller;

import com.github.luizstsantos.streammanager.model.Channel;
import com.github.luizstsantos.streammanager.service.ChannelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ChannelControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ChannelService channelService;

    @InjectMocks
    private ChannelController channelController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(channelController).build();
    }

    @Test
    void testGetAllChannels() throws Exception {
        Channel c1 = new Channel();
        c1.setId(1L);
        c1.setName("Canal 1");

        Channel c2 = new Channel();
        c2.setId(2L);
        c2.setName("Canal 2");

        when(channelService.getAllChannels()).thenReturn(Arrays.asList(c1, c2));

        mockMvc.perform(get("/channels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Canal 1"))
                .andExpect(jsonPath("$[1].name").value("Canal 2"));
    }

    @Test
    void testGetChannelById() throws Exception {
        Channel c = new Channel();
        c.setId(1L);
        c.setName("Canal Teste");

        when(channelService.getChannelById(1L)).thenReturn(Optional.of(c));

        mockMvc.perform(get("/channels/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Canal Teste"));
    }

    @Test
    void testCreateChannel() throws Exception {
        Channel savedChannel = new Channel();
        savedChannel.setId(1L);
        savedChannel.setName("Novo Canal");

        when(channelService.createChannel(any(Channel.class))).thenReturn(savedChannel);

        mockMvc.perform(post("/channels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Novo Canal\"}"))
                .andExpect(status().isOk())// 200
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Novo Canal"));
    }

    @Test
    void testUpdateChannel() throws Exception {
        Channel updatedChannel = new Channel();
        updatedChannel.setId(1L);
        updatedChannel.setName("Canal Atualizado");

        when(channelService.updateChannel(eq(1L), any(Channel.class)))
                .thenReturn(Optional.of(updatedChannel));

        mockMvc.perform(put("/channels/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Canal Atualizado\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Canal Atualizado"));
    }

    @Test
    void testDeleteChannel() throws Exception {
        when(channelService.deleteChannel(1L)).thenReturn(true);

        mockMvc.perform(delete("/channels/1"))
                .andExpect(status().isNoContent());

        verify(channelService, times(1)).deleteChannel(1L);
    }

    @Test
    void testDeleteChannelNotFound() throws Exception {
        when(channelService.deleteChannel(999L)).thenReturn(false);

        mockMvc.perform(delete("/channels/999"))
                .andExpect(status().isNotFound());

        verify(channelService, times(1)).deleteChannel(999L);
    }
}
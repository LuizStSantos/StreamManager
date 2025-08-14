package com.github.luizstsantos.streammanager.controller;

import com.github.luizstsantos.streammanager.model.Channel;
import com.github.luizstsantos.streammanager.service.ChannelService;
import jakarta.persistence.Id;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channels")
public class ChannelController {

    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    //GET /channels - Lista todos
    @GetMapping
    public List<Channel> getAllChannels() {
        return channelService.getAllChannels();
    }
    //GET /channels/{id} - busca por id
    @GetMapping("{/id}")
    public ResponseEntity<Channel> getChannelById(@PathVariable Long id) {
        return channelService.getChannelById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    //POST - /channels - criar canal
    @PostMapping
    public ResponseEntity<Channel> createChannel(@RequestBody Channel channel) {
        Channel savedChannel = channelService.createChannel(channel);
        return ResponseEntity.ok(savedChannel);
    }
    //PUT /channels/{id} - atualiza canal
    @PutMapping("/{id}")
    public ResponseEntity<Channel> updateChannel(@PathVariable Long id, @RequestBody Channel channelDetails) {
        return channelService.updateChannel(id, channelDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    //DELETE /channels/{id} - deleta canal
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChannel(@PathVariable Long id) {
        boolean deleted = channelService.deleteChannel(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

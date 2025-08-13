package com.github.luizstsantos.streammanager.service;

import com.github.luizstsantos.streammanager.model.Channel;
import com.github.luizstsantos.streammanager.repository.ChannelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChannelService {

    private ChannelRepository channelRepository;

    private ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }
    //Criar Canal
    public Channel createChannel(Channel channel) {
        return channelRepository.save(channel);
    }
    //Listar todos os canais
    public List<Channel> getAllChannels() {
        return channelRepository.findAll();
    }
    //Buscar canal por id
    public Optional<Channel> getChannelById(Long id) {
        return channelRepository.findById(id);
    }
    //Atualizar canal
    public Optional<Channel> updateChannel(Long id, Channel channelDetails) {
        return channelRepository.findById(id).map(channel -> {
            channel.setName(channelDetails.getName());
            channel.setDescription(channelDetails.getDescription());
            channel.setStreamUrl(channelDetails.getStreamUrl());
            return channelRepository.save(channel);
        });
    }
    //Deletar canal
    public boolean deleteChannel(Long id) {
        return channelRepository.findById(id).map(channel -> {
            channelRepository.delete(channel);
            return true;
        }).orElse(false);
    }
}

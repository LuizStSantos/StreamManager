package com.github.luizstsantos.streammanager.repository;

import com.github.luizstsantos.streammanager.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
}

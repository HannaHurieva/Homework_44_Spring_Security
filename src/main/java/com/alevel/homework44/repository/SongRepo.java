package com.alevel.homework44.repository;

import com.alevel.homework44.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepo extends JpaRepository<Song, Long> {
    List<Song> findBySinger(String singer);
}

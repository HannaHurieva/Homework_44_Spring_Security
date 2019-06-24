package com.alevel.homework44.controller;


import com.alevel.homework44.model.Song;
import com.alevel.homework44.repository.SongRepo;
import com.alevel.homework44.service.SongNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    private final SongRepo songRepo;

    @Autowired
    public MainController(SongRepo songRepo) {
        this.songRepo = songRepo;
    }

    @GetMapping("/")
    public String greeting(@RequestParam(name ="name", required=false, defaultValue="guest") String name,
                            Map<String, Object> model
    ) { model.put("name", name);
        return "greeting";
    }

    @GetMapping("/song")
    public String main(Map<String, Object> model) {
        Iterable<Song> songs = songRepo.findAll();
        model.put("songs", songs);
        return "song";
    }

    @PostMapping("/song")
    public String add(@RequestParam String title,
                      @RequestParam String singer,
                      Map<String, Object> model) {

        Song message = new Song(title, singer);
        songRepo.save(message);

        Iterable<Song> songs = songRepo.findAll();
        model.put("songs", songs);
        return "song";
    }

    @GetMapping("/song/{id}")
    public Song getSongById(@PathVariable Long id) {
        return songRepo.findById(id).
                orElseThrow(() -> new SongNotFoundException(id));
    }

    @PostMapping("filter")
    public String getSongByAuthor (@RequestParam String filter,
                         Map<String, Object> model) {
        Iterable<Song> songs;
        if (filter != null && !filter.isEmpty()) {
            songs = songRepo.findBySinger(filter);
        } else {
            songs = songRepo.findAll();
        }
        model.put("songs", songs);
        return "song";
    }
}
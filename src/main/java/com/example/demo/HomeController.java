package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    ArtistRepository artistRepository;
    SongRepository songRepository;

    @RequestMapping("/")
    public String showArtist(Model model){
        model.addAttribute("artists", artistRepository.findAll());
        return "showartist";
        }

        @RequestMapping("/artist")
    public String addArtist(Model model){
        model.addAttribute("artist", new Artist());
        return "addartist";
        }

        @RequestMapping("/processartist")
        public String processArtist(@Valid Artist artist, BindingResult result) {
            if (result.hasErrors()) {
                return "addartist";
            }
            artistRepository.save(artist);
            return "redirect:/";
        }

        @RequestMapping("/song")
    public String addSong(Model model){
        model.addAttribute("song", new Song());
        return "addsong";
        }

        @RequestMapping("/processsong")
    public String processSong(@Valid Song song, BindingResult result){
        if (result.hasErrors()){
            return "addsong";
        }
        songRepository.save(song);
        return "redirect:/";
        }


}

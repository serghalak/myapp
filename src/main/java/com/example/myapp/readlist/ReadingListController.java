package com.example.myapp.readlist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ReadingListController {

    private final ReadingListRepository readingListRepository;

    public ReadingListController(
            ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }

//    @GetMapping("/")
//    public String test(){
//        System.out.println("Root get");
//        return "readingList";
//    }


    @RequestMapping(value = "/{reader}", method = RequestMethod.GET)
    public String readersBooks(
            @PathVariable(name = "reader") String reader
            ,Model model){
        List<Book> readingList = readingListRepository.findByReader(reader);
        if(readingList != null){
            model.addAttribute("books",readingList);
        }
        System.out.println("reader: " + reader +"  " + readingList);
        return "readingList";
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public String addToReadingList(@PathVariable(name = "reader")String reader
            ,Book book){
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/{reader}";
    }
}

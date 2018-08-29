package pl.akademiakodu.bookstoreexample.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("books")
    public Collection<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        BookMapper mapper = new BookMapper();
        List<BookDto> bookDtos = new ArrayList<>();

        for (Book b: books) {
            BookDto bookDto = mapper.map(b);
            bookDtos.add(bookDto);
        }
        return bookDtos;
    }

    @RequestMapping(value = "book", method = RequestMethod.POST)
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book){
        bookRepository.save(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

//    public ResponseEntity<Book> addBook(Book book) {
//
//    }


}

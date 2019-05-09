package lukaskodaj.planner;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Controller
public class NotesController {

    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private HttpSession session;

    @RequestMapping("/my-notes")
    public String showMyNotes(Model model) {

        if (session.getAttribute("email") != null) {

            List<Notes> notes = (List<Notes>) notesRepository.findNotesByUserId(3);

            Filter filter = new Filter();

            String[] selectedTypes = {"c-new", "c-process", "c-done"};

            model.addAttribute("selectedOrder", "none");

            model.addAttribute("notes", notes);
            model.addAttribute("filter", filter);
            model.addAttribute("selectedTypes", "c-new, c-process, c-done");
            return "my-notes";
        } else {
            return "redirect:/sign-in";
        }

    }

    @RequestMapping(value = "notes-filter", method = RequestMethod.POST)
    public String filterNotes(Filter filter, Model model) {

        if (session.getAttribute("email") != null) {

            List<Notes> notes;
            String[] types = {"none"};

            if (filter.getType() != null) {
                types = filter.getType().split(",");
            }

            if (filter.getOrder() != null && filter.getOrder().equals("edited-asc")) {
                notes = (List<Notes>) notesRepository.findNotesByFilterOrderAsc(3, types);
            } else {
                notes = (List<Notes>) notesRepository.findNotesByFilterOrderDesc(3, types);
            }

            model.addAttribute("selectedOrder", filter.getType());

            model.addAttribute("selectedTypes", filter.getType() != null ? filter.getType() : "none");

            model.addAttribute("notes", notes);

            return "my-notes";

        } else {
            return "redirect:/sign-in";
        }

    }

    @RequestMapping(value = "/note-delete/{id}", method = RequestMethod.GET)
    public String deleteNote(@PathVariable("id") Long noteId, Model model) {

        if (session.getAttribute("email") != null) {

            notesRepository.deleteById(noteId);

            return "redirect:/my-notes";

        } else {
            return "redirect:/sign-in";
        }

    }

    @RequestMapping(value = "/note-detail/{id}", method = RequestMethod.GET)
    public String noteDetail(@PathVariable("id") Long noteId, Model model) {

        if (session.getAttribute("email") != null) {


            Notes note = notesRepository.findNotesById(noteId);

            model.addAttribute("note", note);

//        model.addAttribute("message", "Note was successfully edited");

            return "note-detail";

        } else {
            return "redirect:/sign-in";
        }

    }

    @RequestMapping(value = "/note-add")
    public String addN(Notes note, Model model) {

        if (session.getAttribute("email") != null) {


            model.addAttribute("note", note);


            return "note-add";

        } else {
            return "redirect:/sign-in";
        }
    }

    @RequestMapping(value = "note-add", method = RequestMethod.POST)
    public String addNote(Notes note) {

        if (session.getAttribute("email") != null) {


            note.setEdited(new Timestamp(System.currentTimeMillis()));
            note.setCreated(new Date());
            note.setCleanNote(Jsoup.parse(note.getNote()).text());
            note.setUserId(3);

            notesRepository.save(note);
            return "redirect:/my-notes";

        } else {
            return "redirect:/sign-in";
        }

    }

    @RequestMapping(value = "note-edit", method = RequestMethod.POST)
    public String editNote(Notes note, Model model) {

        if (session.getAttribute("email") != null) {


            Notes foundNote = notesRepository.findById(note.getId()).get();

            foundNote.setTitle(note.getTitle());
            foundNote.setNote(note.getNote());
            foundNote.setCleanNote(Jsoup.parse(note.getNote()).text());
            foundNote.setType(note.getType());
            foundNote.setEdited(new Timestamp(System.currentTimeMillis()));
            notesRepository.save(foundNote);

//        model.addAttribute("message", "Note was successfully edited");

            return "redirect:/note-detail/" + note.getId();

        } else {
            return "redirect:/sign-in";
        }

    }

}

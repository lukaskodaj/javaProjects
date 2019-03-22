package lukaskodaj.studentCrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository repository;


    @RequestMapping("/")
    public String index(Model model)
    {
        List<Student> students = (List<Student>)repository.findAll();

        model.addAttribute("students", students);

        return "index";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Student student)
    {
        repository.save(student);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("id") Long studentId, Model model)
    {
        repository.deleteById(studentId);
        return "redirect:/";
    }

    @RequestMapping(value = "/add")
    public String addStudent(Student student)
    {
        return "add-student";
    }

    @RequestMapping(value = "/deleteall", method = RequestMethod.GET)
    public String deleteStudent(Model model)
    {
        repository.deleteAll();
        return "redirect:/";
    }

}

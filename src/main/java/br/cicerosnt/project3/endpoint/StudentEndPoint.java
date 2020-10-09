package br.cicerosnt.project3.endpoint;

import br.cicerosnt.project3.error.ResourceNotFoundException;
import br.cicerosnt.project3.model.Student;
import br.cicerosnt.project3.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController //adiciona automaticamento o @ResponseBody (dados no corpo do json)
@RequestMapping("v1")
public class StudentEndPoint {

    private StudentRepository studentDAO = null;

    @Autowired
    public StudentEndPoint(StudentRepository studentDAO) {
        this.studentDAO = studentDAO;
    }

    @GetMapping(path = "protected/students")
    public ResponseEntity<?> listAll(Pageable pageable){
        return new ResponseEntity<>(studentDAO.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "protected/students/findById/{id}")
    public ResponseEntity<?> getStudentsById(@PathVariable("id") Long id,
                                             @AuthenticationPrincipal UserDetails userDetails){
        System.out.println(userDetails);
        studentExist(id);
        Optional<Student> student = studentDAO.findById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping(path = "admin/students")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> create(@Valid @RequestBody Student student){
        return new ResponseEntity<>(studentDAO.save(student), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/admin/students/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id){
        studentExist(id);
        studentDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "admin/{id}")
    public ResponseEntity<?> update(@RequestBody Student student){
        studentExist(student.getId());
        studentDAO.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "protected/students/findByName/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){
        return new ResponseEntity<>(studentDAO.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    private void studentExist(Long id) {
        if (studentDAO.findById(id) == null) {
            throw new ResourceNotFoundException("Student not found " + id);
        }
    }
}

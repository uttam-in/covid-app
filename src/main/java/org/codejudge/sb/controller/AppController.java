package org.codejudge.sb.controller;

import io.swagger.annotations.ApiOperation;
import org.codejudge.sb.apimodels.CUser;
import org.codejudge.sb.apimodels.CovidSymptom;
import org.codejudge.sb.apimodels.CovidUser;
import org.codejudge.sb.dtos.CreateCovidSymptomsDTO;
import org.codejudge.sb.dtos.CreateCovidUserDTO;
import org.codejudge.sb.dtos.CreateUserDTO;
import org.codejudge.sb.models.CovidSymptoms;
import org.codejudge.sb.reposotories.CovidSymptomsRepository;
import org.codejudge.sb.usecases.CovidSymptomUsecases;
import org.codejudge.sb.usecases.CovidUserUsecases;
import org.codejudge.sb.usecases.UserUsecases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping
public class AppController {
    @Autowired
    UserUsecases userUsecases;

    @Autowired
    CovidUserUsecases covidUserUsecases;

    @Autowired
    CovidSymptomUsecases covidSymptomsUsecases;
//
//    @Autowired
//    QuestionService questionService;
//
    @ApiOperation("This is the hello world api")
    @GetMapping("/")
    public String hello() {
        return "Hello World!!";
    }
//
//    @ApiOperation("Get a quiz")
//    @GetMapping("/api/quiz/{quiz_id}")
//    public ResponseEntity<?> getquiz(@PathVariable("quiz_id") String quiz_id)
//    {
//        int id = parseInt(quiz_id);
//        return quizService.getQuizById(id);
//    }
//
    @ApiOperation("Post a quiz")
    @RequestMapping(value = "/api/register/", method = RequestMethod.POST)
    public ResponseEntity<?> postquiz(@Valid @RequestBody CUser u) throws Exception {
        {
            CreateUserDTO user = new CreateUserDTO(u.getUsername(),u.getPassword());
            return userUsecases.createUser(user);
        }
    }

    @ApiOperation("Register a covid app user")
    @RequestMapping(value = "/api/covid-register/", method = RequestMethod.POST)
    public ResponseEntity<?> registerCovidUser(@Valid @RequestBody CovidUser u) throws Exception {
        {
            CreateCovidUserDTO user = new CreateCovidUserDTO(u.getName(),u.getPhoneNumber(),u.getPinCode());
            return covidUserUsecases.createUser(user);
        }
    }

    @ApiOperation("Register a covid app user")
    @RequestMapping(value = "/api/symptom-register/", method = RequestMethod.POST)
    public ResponseEntity<?> registerSymptomsUser(@Valid @RequestBody CovidSymptom u) throws Exception {
        {
            CreateCovidSymptomsDTO user = new CreateCovidSymptomsDTO(u.getUserId(),u.getSymptoms(),u.isTravelHistory(),u.isContactWithCovidPatient());
            return covidSymptomsUsecases.updateSymptom(user);
        }
    }

//
//    @ApiOperation("Get a question")
//    @GetMapping("/api/questions/{question_id}")
//    public ResponseEntity<?> getquestion(@PathVariable("question_id") String question_id)
//    {
//        int id = parseInt(question_id);
//        return questionService.getQuestionById(id);
//    }
//
//    @ApiOperation("Post a question")
//    @RequestMapping(value = "/api/questions/", method = RequestMethod.POST)
//    public ResponseEntity<?> postquestion(@Valid @RequestBody Question q) throws Exception {
//        {
//            return questionService.saveOrUpdate(q);
//        }
//    }
//
//    @ApiOperation("Get a quiz questions")
//    @GetMapping("api/quiz-questions/{quiz_id}")
//    public ResponseEntity<?> getquizquestions(@PathVariable("quiz_id") String quiz_id)
//    {
//        int id = parseInt(quiz_id);
//        return quizService.getQuestionsById(id);
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        errors.put("status", "failure");
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            //String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put("reason", errorMessage);
//        });
//        return errors;
//    }

}

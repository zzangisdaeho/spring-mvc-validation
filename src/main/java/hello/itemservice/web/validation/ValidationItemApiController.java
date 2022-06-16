package hello.itemservice.web.validation;

import hello.itemservice.web.validation.form.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

    @Autowired
    private MessageSource messageSource;

    @PostMapping("/add")
    public Object addItem(@RequestBody @Validated ItemSaveForm form,
                          BindingResult bindingResult,
                          Locale locale) {
        log.info("API 컨트롤러 호출");
        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생 errors={}", bindingResult);
            bindingResult.getFieldErrors().forEach(error -> {
                log.info("error Object.Field : {}.{}", error.getObjectName(), error.getField());
                log.info("error default message : {}", error.getDefaultMessage());
                log.info("error message : {} ", messageSource.getMessage(error.getCodes()[3], new Object[]{"야"}, locale));
            });
            return bindingResult.getAllErrors();
        }
        log.info("성공 로직 실행");
        return form;
    }

    @PostMapping("/add2")
    public Object addItem2(@RequestBody @Validated ItemSaveForm form,
                          Locale locale) {
        log.info("API 컨트롤러 호출");
        log.info("성공 로직 실행");
        return form;
    }
}
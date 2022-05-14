package ru.kpfu.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import ru.kpfu.itis.models.dtos.UserDto;

import java.util.regex.Pattern;

public class Validator implements org.springframework.validation.Validator {
    @Autowired
    private UsersService usersService;
    @Override
    public boolean supports(Class<?> clazz) {return UserDto.class.equals(clazz);}
    @Override
    public void validate(Object obj, Errors errors) {
        UserDto registrationDto = (UserDto) obj;
        ValidationUtils.rejectIfEmpty(errors, "firstName", "firstName", "Не указано Имя");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "lastName", "Не указано Фамилия");
        ValidationUtils.rejectIfEmpty(errors, "email", "email", "Не указан Логин");
        ValidationUtils.rejectIfEmpty(errors, "password", "password", "Не указан Пароль");
        ValidationUtils.rejectIfEmpty(errors, "consent", "consent", "Не указано Согласие");

        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);
        if (!(pattern.matcher(registrationDto.getEmail()).matches())) {
            errors.reject("email", "Неправильный email");
        }

//        if (!usersService.findByCookie(re)) {
//            errors.reject("email", "Пользователь с таким логином существует");
//        }

        if (!registrationDto.getPassword().equals(registrationDto.getRepassword())) {
            errors.reject("confirmPassword", "Пароли не совпадают");
        }
    }
}

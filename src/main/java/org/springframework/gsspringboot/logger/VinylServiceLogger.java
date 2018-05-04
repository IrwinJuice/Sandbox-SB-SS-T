package org.springframework.gsspringboot.logger;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
class VinylServiceLogger {
    private final Logger logger =
            LoggerFactory.getLogger(VinylServiceLogger.class);

    @Pointcut("execution(* org.springframework.gsspringboot.service.VinylService.saveVinyl(..))")
    private void createPointcut() {
    }
    @Before("createPointcut()")
    private void beforeCreateContact() {
        logger.info("Вызываем метод create()...");
    }

    @After("createPointcut()")
    private void afterCreateContact() {
        logger.info("Завершаем метод create()...");
    }

    @AfterReturning(value = "createPointcut()", returning = "id")
    private void afterReturningCreate(Object id) {
        logger.info("Id сгенерированый для Vinyl - " + (Long) id);
    }

}

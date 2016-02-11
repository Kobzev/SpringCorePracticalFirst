package ua.kobzev.theatre.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.service.AspectService;
import ua.kobzev.theatre.strategy.DiscountStrategy;

/**
 * 
 * @author kkobziev
 * 
 *         count how many times each discount was given total and for specific
 *         user
 */

@Aspect
@Component
public class DiscountAspect {

    @Autowired
    private AspectService aspectService;

    @Pointcut("execution(* ua.kobzev.theatre..DiscountStrategy.getDiscount(..))")
    private void totalDiscount() {}


    @Pointcut("execution(* ua.kobzev.theatre..DiscountService.getDiscount(..))")
    private void discountForUser() {}

    @AfterReturning(value = "totalDiscount()", returning = "retVal")
    public void saveInfoAboutEachDiscount(JoinPoint joinPoint, Object retVal){
        if (retVal == null) return;
        if (new Double(0.0).compareTo((Double)retVal) == 0) return;

        aspectService.saveTotalDiscount((DiscountStrategy)joinPoint.getThis());
    }

    @AfterReturning(value = "discountForUser()", returning = "retVal")
    public void saveInfoAboutTotalDiscountForUser(JoinPoint joinPoint, Object retVal){
        if (retVal == null) return;
        if (new Double(0.0).compareTo((Double)retVal) == 0) return;

        User user = (User) joinPoint.getArgs()[0];

        aspectService.saveInfoAboutTotalDiscountForUser(user);

    }

}

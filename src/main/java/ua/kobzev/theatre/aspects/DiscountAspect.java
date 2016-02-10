package ua.kobzev.theatre.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

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

    private void totalDiscount() {}

    private void discountForUser() {}

}

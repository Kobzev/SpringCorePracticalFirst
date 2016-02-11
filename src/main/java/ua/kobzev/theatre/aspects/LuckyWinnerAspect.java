package ua.kobzev.theatre.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * 
 * @author kkobziev
 *
 *         every time the bookTicket method is executed perform the checkLucky
 *         method for the user that based on some randomness will return true or
 *         false. If user is lucky, the ticketPrice changes to zero and ticket
 *         is booked, thus user pays nothing. Store the information about this
 *         lucky event into the user object (like some system messages or so) -
 *         OPTIONAL
 */

@Aspect
@Component
public class LuckyWinnerAspect {

    @Pointcut("execution(* *.setPrice(..))")
    private void setPrice() {}

    @Around("setPrice()")
    public void setLuckyWinnerUser(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (!areYouLucky()) {
            proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        } else {
            System.out.println("You are lucky user!!!!! Your ticket price = 0!!!!");
            Object[] args = new Object[1];
            args[0] = new Double(0.0);
            proceedingJoinPoint.proceed(args);
        }
    }

    private boolean areYouLucky(){
        if (new Random().nextInt(1) == 1) return true;
        return false;
    }

}

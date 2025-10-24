package ie.atu.errorhandling;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling
{
    public String showerrorDetaills()
    {
        return "There is an issue";
    }
}

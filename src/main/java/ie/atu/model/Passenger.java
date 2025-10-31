package ie.atu.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Passenger
{
    @NotBlank
    @Size(max = 40)
    private String PassengerId;

    @NotBlank
    @Size(max = 60)
    private String name;

    @NotBlank
    @Email
    private String email;

    // Manually add the getter method
    public String getPassengerId()
    {
        return PassengerId;
    }

    // Add other getters if needed
    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }
}
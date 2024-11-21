package Model;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull; 
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import lombok.Data;

@Entity
@Data
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int guestId;

    @NotNull
    @Size(min = 2 , max = 15)
    private String guestName;

    @Email
    private String guestEmail;
    
    @Size(max = 10)
    private String guestPhone;
    
    @NotNull
    @Size(min = 5 , max = 30)
    private String guestAddress;
}

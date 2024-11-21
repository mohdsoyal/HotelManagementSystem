package Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Room {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;  
    
    private String roomType;
    
    private int roomPrice;
    
    private String roomStatus = "available";  
  
    @ManyToOne  
    @JoinColumn(name = "HotelId")  
    private Hotel hotel;  
}

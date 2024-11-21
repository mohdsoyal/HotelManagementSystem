package Model;

import java.time.LocalDateTime;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	
	private LocalDateTime bookingDate = LocalDateTime.now();
	
	@NotNull
	private String CheckIn;
	@NotNull
	private String CheckOut;
	
	@ManyToOne
	@JoinColumn(name="GuestId")
	private Guest guest;
	
	@ManyToOne
	@JoinColumn(name="roomId")
	private Room room;
	
	@ManyToOne
	@JoinColumn(name="HotelId")
	private Hotel hotel ;

}
         
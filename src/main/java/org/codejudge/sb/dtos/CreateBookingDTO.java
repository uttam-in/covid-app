package org.codejudge.sb.dtos;

import lombok.Data;
import lombok.NonNull;
import org.codejudge.sb.models.Show;
import org.codejudge.sb.models.ShowSeat;

import java.util.List;

@Data
public class CreateBookingDTO {
    @NonNull
    Show show;
    @NonNull
    List<ShowSeat> showSeats;
}

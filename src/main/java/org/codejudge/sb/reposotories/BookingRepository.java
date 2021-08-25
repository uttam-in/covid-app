package org.codejudge.sb.reposotories;

import org.codejudge.sb.models.Booking;
import org.codejudge.sb.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> getBookingsByCustomer(Customer customer);
}

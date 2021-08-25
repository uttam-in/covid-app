package org.codejudge.sb.usecases;

import org.codejudge.sb.dtos.CreateBookingDTO;
import org.codejudge.sb.exceptions.booking.InvalidBookingStateException;
import org.codejudge.sb.exceptions.booking.SeatsNotAvailableException;
import org.codejudge.sb.models.Booking;
import org.codejudge.sb.models.BookingStatus;
import org.codejudge.sb.models.Customer;
import org.codejudge.sb.models.ShowSeat;
import org.codejudge.sb.reposotories.BookingRepository;
import org.codejudge.sb.reposotories.ShowSeatRepository;
import org.codejudge.sb.services.RefundService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingUsecases {
    private final ShowSeatRepository showSeatRepository;
    private final RefundService refundService;
    private final BookingRepository bookingRepository;

    public BookingUsecases(ShowSeatRepository showSeatRepository, RefundService refundService, BookingRepository bookingRepository) {
        this.showSeatRepository = showSeatRepository;
        this.refundService = refundService;
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(Customer customer, CreateBookingDTO details) {
        if (!details.getShow().isShowPending()) {
            throw new SeatsNotAvailableException("The show is no longer accepting bookings");
        }
        Booking booking;
        // todo: acquire some lock
        {
            boolean preOccupied = details
                    .getShowSeats()
                    .stream()
                    .anyMatch(ShowSeat::isOccupied);
            if (preOccupied) {
                throw new SeatsNotAvailableException("Some of the seats are no longer available");
            }
            for (ShowSeat showSeat : details.getShowSeats()) {
                showSeat.setOccupied(true);
                showSeatRepository.save(showSeat);
            }
            booking = new Booking(customer, details.getShow());
            booking.setSeatsBooked(details.getShowSeats());
            bookingRepository.save(booking);
        }
        // todo: release lock
        return booking;
    }

    public Booking cancelBooking(Booking booking) {
        if (!booking.getShow().isShowPending()) {
            throw new InvalidBookingStateException("The show has already started/completed. This booking cannot be cancelled now");
        }
        refundService.refundBooking(booking);
        booking.setStatus(BookingStatus.CANCELLED);
        for (ShowSeat seat : booking.getSeatsBooked()) {
            seat.setOccupied(false);
            showSeatRepository.save(seat);
        }
        bookingRepository.save(booking);
        return booking;
    }

    public List<Booking> listCustomerBookings(Customer customer) {
        return bookingRepository.getBookingsByCustomer(customer);
    }
}

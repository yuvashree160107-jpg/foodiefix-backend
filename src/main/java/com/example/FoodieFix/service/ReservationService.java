package com.example.FoodieFix.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.FoodieFix.dto.ReservationRequestDto;
import com.example.FoodieFix.entity.Reservation;
import com.example.FoodieFix.entity.Reservation.ReservationStatus;
import com.example.FoodieFix.entity.RestaurantTable;
import com.example.FoodieFix.entity.RestaurantTable.TableStatus;
import com.example.FoodieFix.exception.BusinessValidationException;
import com.example.FoodieFix.exception.ReservationConflictException;
import com.example.FoodieFix.exception.ReservationNotFoundException;
import com.example.FoodieFix.exception.ResourceNotFoundException;
import com.example.FoodieFix.repository.ReservationRepository;
import com.example.FoodieFix.repository.RestaurantTableRepository;

@Service
public class ReservationService {

    private final ReservationRepository repo;
    private final RestaurantTableRepository tableRepo;

    public ReservationService(ReservationRepository repo, RestaurantTableRepository tableRepo) {
        this.repo = repo;
        this.tableRepo = tableRepo;
    }

    public Page<Reservation> getReservations(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Reservation createReservation(ReservationRequestDto request) {
        if (request.getGuestCount() == null || request.getGuestCount() <= 0) {
            throw new BusinessValidationException("Guest count must be greater than zero");
        }

        Reservation reservation = new Reservation();
        reservation.setCustomerName(request.getCustomerName());
        reservation.setCustomerPhoneNumber(request.getCustomerPhone());
        reservation.setReservationTime(request.getReservationTime());
        reservation.setGuestCount(request.getGuestCount());
        reservation.setStatus(ReservationStatus.BOOKED);

        if (request.getTableId() != null) {
            if (!tableRepo.existsById(request.getTableId())) {
                throw new ResourceNotFoundException("Table not found with id: " + request.getTableId());
            }
            reservation.setTable_id(request.getTableId());
        }

        return repo.save(reservation);
    }

    @Transactional
    public Reservation seatGuest(Long id) {
        Reservation reservation = repo.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found with id: " + id));

        if (reservation.getStatus() != ReservationStatus.BOOKED) {
            throw new ReservationConflictException("Reservation is not in a seatable state");
        }

        if (reservation.getTable_id() != null) {
            RestaurantTable table = tableRepo.findById(reservation.getTable_id())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Table not found with id: " + reservation.getTable_id()));

            if (table.getStatus() == TableStatus.OCCUPIED) {
                throw new ReservationConflictException("Table is already occupied");
            }
            table.setStatus(TableStatus.OCCUPIED);
            tableRepo.save(table);
        }

        reservation.setStatus(ReservationStatus.SEATED);
        return repo.save(reservation);
    }

    public void cancelReservation(Long id) {
        Reservation reservation = repo.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found with id: " + id));
        reservation.setStatus(ReservationStatus.CANCELLED);
        repo.save(reservation);
    }
}
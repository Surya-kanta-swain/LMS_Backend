package com.elms.controller;

import com.elms.entities.Leave;
import com.elms.entities.User;
import com.elms.repository.LeaveRepository;
import com.elms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

@CrossOrigin("*")
@RequestMapping("/api/leave")
@RestController
public class LeaveController {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new leave request
    @PostMapping("/")
    public Leave createLeave(@RequestBody Leave leave) {
        leaveRepository.save(leave);
        return leave;
    }

    // Get total leave days taken by user (approved leaves)
    @GetMapping("/count/{id}")
    public int leavesTaken(@PathVariable Long id) {
        System.out.println("id"+id);
        int count = 0;
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            System.out.println(user.getDays());
            System.out.println(user);
        }

        List<Leave> allMatchedLeave = leaveRepository.findAll().stream()
                .filter(e -> e.getUser() != null && Objects.equals(e.getUser().getId(), id) && "approve".equals(e.getStatus()))
                .toList();

        System.out.println("All leave match"+allMatchedLeave);

        for (Leave l : allMatchedLeave) {
            LocalDate l1 = LocalDate.from(l.getStartDate().toInstant().atZone(java.time.ZoneId.systemDefault()));
            LocalDate l2 = LocalDate.from(l.getEndDate().toInstant().atZone(java.time.ZoneId.systemDefault()));

            Period period = Period.between(l1, l2);
            int l3 = period.getDays() + 1;
            System.out.println(l3);
            count += l3;

        }
System.out.println("count ="+count);
        return count;
    }

    // Get total sick leave days taken by user
    @GetMapping("/count/sick/{id}")
    public int sickLeavesTaken(@PathVariable Long id) {
        int count = 0;
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            System.out.println(user.getDays());
            System.out.println(user);
        }

        List<Leave> allMatchedLeave = leaveRepository.findAll().stream()
                .filter(e -> e.getUser() != null && Objects.equals(e.getUser().getId(), id) && "approve".equals(e.getStatus()))
                .toList();

        List<Leave> allSickLeave = allMatchedLeave.stream()
                .filter(e -> "sick".equals(e.getLeaveType()))
                .toList();


        System.out.println(allSickLeave);

        for (Leave l : allSickLeave) {
            LocalDate l1 = LocalDate.from(l.getStartDate().toInstant().atZone(java.time.ZoneId.systemDefault()));
            LocalDate l2 = LocalDate.from(l.getEndDate().toInstant().atZone(java.time.ZoneId.systemDefault()));

            Period period = Period.between(l1, l2);
            int l3 = period.getDays() + 1;
            System.out.println(l3);
            count += l3;
        }

        return count;
    }

    // Get total vacation leave days taken by user
    @GetMapping("/count/vacation/{id}")
    public int vacationLeavesTaken(@PathVariable Long id) {
        int count = 0;
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            System.out.println(user.getDays());
            System.out.println(user);
        }

        List<Leave> allMatchedLeave = leaveRepository.findAll().stream()
                .filter(e -> e.getUser() != null && Objects.equals(e.getUser().getId(), id) && "approve".equals(e.getStatus()))
                .toList();

        List<Leave> allVacationLeave = allMatchedLeave.stream()
                .filter(e -> "vacation".equals(e.getLeaveType()))
                .toList();

        System.out.println(allVacationLeave);

        for (Leave l : allVacationLeave) {
            LocalDate l1 = LocalDate.from(l.getStartDate().toInstant().atZone(java.time.ZoneId.systemDefault()));
            LocalDate l2 = LocalDate.from(l.getEndDate().toInstant().atZone(java.time.ZoneId.systemDefault()));

            Period period = Period.between(l1, l2);
            int l3 = period.getDays() + 1;
            System.out.println(l3);
            count += l3;
        }

        return count;
    }

    // Get all leaves
    @GetMapping("/")
    public List<Leave> getLeaves() {
        return leaveRepository.findAll();
    }

    // Get a specific leave by ID
    @GetMapping("/{id}")
    public Leave getLeave(@PathVariable Long id) {
        return leaveRepository.findById(id).orElse(null);
    }

    // Update a leave request by ID
    @PatchMapping("/{id}")
    public Leave updateLeave(@PathVariable Long id, @RequestBody Leave leave) {
        Leave existingLeave = leaveRepository.findById(id).orElse(null);
        if (existingLeave != null) {
            existingLeave.setStartDate(leave.getStartDate());
            existingLeave.setEndDate(leave.getEndDate());
            existingLeave.setLeaveCause(leave.getLeaveCause());
            existingLeave.setLeaveType(leave.getLeaveType());
            leaveRepository.save(existingLeave);
        }
        return existingLeave;
    }

    // Approve a leave request by ID
    @PutMapping("/approve/{id}")
    public Leave approveLeave(@PathVariable Long id, @RequestBody String adminComment) {
        Leave leave = leaveRepository.findById(id).orElse(null);
        if (leave != null) {
            leave.setStatus("approve");
            leave.setAdminComment(adminComment);
            leaveRepository.save(leave);
        }
        return leave;
    }

    // Reject a leave request by ID
    @PutMapping("/reject/{id}")
    public Leave rejectLeave(@PathVariable Long id, @RequestBody String adminComment) {
        Leave leave = leaveRepository.findById(id).orElse(null);
        if (leave != null) {
            leave.setStatus("reject");
            leave.setAdminComment(adminComment);
            leaveRepository.save(leave);
        }
        return leave;
    }

    // Delete a leave by ID
    @DeleteMapping("/{id}")
    public void deleteLeave(@PathVariable Long id) {
        leaveRepository.deleteById(id);
    }

    // Delete all leaves
    @DeleteMapping("/")
    public void deleteAllLeaves() {
        leaveRepository.deleteAll();
    }
}

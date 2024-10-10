package com.elms.repository;

import com.elms.entities.Leave;
import com.elms.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Long> {

    List<Leave> findByUser(User user);
}

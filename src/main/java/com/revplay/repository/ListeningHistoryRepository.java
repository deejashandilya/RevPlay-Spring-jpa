package com.revplay.repository;


import com.revplay.model.ListeningHistory;
import com.revplay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListeningHistoryRepository extends JpaRepository<ListeningHistory, Long> {

    List<ListeningHistory> findByUserOrderByListenedAtDesc(User user);
}

package com.moa.participate.infrastructure;


import com.moa.participate.domain.MeetingLike;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MeetingLikeRepository extends JpaRepository<MeetingLike, Long> {
}

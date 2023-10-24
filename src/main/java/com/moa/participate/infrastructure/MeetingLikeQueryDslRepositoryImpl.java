package com.moa.participate.infrastructure;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.moa.participate.domain.QMeetingLike.meetingLike;


@Repository
@RequiredArgsConstructor
public class MeetingLikeQueryDslRepositoryImpl implements MeetingLikeQueryDslRepository {

	private final JPAQueryFactory jpaQueryFactory;


	private BooleanExpression whereMeetingId(Long meetingId) {
		return meetingLike.meetingId.eq(meetingId);
	}


	private BooleanExpression whereUserUuid(UUID userUuid) {
		return meetingLike.userUuid.eq(userUuid);
	}


	@Override
	@Transactional
	@Modifying(clearAutomatically = true)
	public void deleteByMeetingIdAndUserUuid(Long meetingId, UUID userUuid) {
		jpaQueryFactory
			.delete(meetingLike)
			.where(whereMeetingId(meetingId).and(whereUserUuid(userUuid)))
			.execute();
	}

}

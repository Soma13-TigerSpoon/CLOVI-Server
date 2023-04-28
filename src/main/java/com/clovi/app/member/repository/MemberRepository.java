package com.clovi.app.member.repository;

import java.util.Optional;

import com.clovi.app.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

  Optional<Member> findByMemberId(String memberId);

  Optional<Member> findByIdAndDeletedIsFalse(Long memberId);

  Boolean existsByMemberId(String memberId);
}


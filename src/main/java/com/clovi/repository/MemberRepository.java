package com.clovi.repository;

import com.clovi.domain.user.Member;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

  Optional<Member> findByMemberId(String memberId);

  Optional<Member> findByIdAndIsDeletedIsFalse(Long memberId);

  Boolean existsByMemberId(String memberId);
}


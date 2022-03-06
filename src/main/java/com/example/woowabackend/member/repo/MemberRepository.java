package com.example.woowabackend.member.repo;

import com.example.woowabackend.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

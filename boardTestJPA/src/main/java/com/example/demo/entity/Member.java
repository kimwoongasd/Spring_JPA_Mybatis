package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member {
	@Id
	private String id;
	private String name;
	private String pwd;
	private String role;
	// cascade = CascadeType.REMOVE 부모가 삭제되면 연관된 자식들 삭제
	// fetch = 참조된 자식을 언제 로딩할 것인가 EAGER=즉시로딩
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Board> boards;
}

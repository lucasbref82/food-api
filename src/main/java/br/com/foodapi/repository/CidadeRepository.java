package br.com.foodapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.foodapi.domain.entity.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}

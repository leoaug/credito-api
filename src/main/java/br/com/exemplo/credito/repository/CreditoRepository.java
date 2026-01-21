package br.com.exemplo.credito.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.exemplo.credito.model.Credito;

@Repository
public interface CreditoRepository extends JpaRepository<Credito, Long> {
	
	List<Credito> findByNumeroNfse(String numeroNfse);

	Collection<Credito> findByNumeroCredito(String numero, PageRequest of);
}

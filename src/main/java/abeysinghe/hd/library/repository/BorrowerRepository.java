package abeysinghe.hd.library.repository;

import abeysinghe.hd.library.entity.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerRepository extends JpaRepository<Borrower, Long> { }

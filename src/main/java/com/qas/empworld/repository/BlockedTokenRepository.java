package com.qas.empworld.repository;

import com.qas.empworld.model.BlockedTokenMaster;
import org.springframework.data.repository.CrudRepository;

public interface BlockedTokenRepository extends CrudRepository<BlockedTokenMaster, Long> {
    BlockedTokenMaster findByToken(String token);
}


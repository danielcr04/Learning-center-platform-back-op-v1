package com.intellectsoft.platform.u20221g120tipo1.portfolio.infrastructure.persistence.jpa.repositories;

import com.intellectsoft.platform.u20221g120tipo1.portfolio.domain.model.aggregates.WebApplication;
import com.intellectsoft.platform.u20221g120tipo1.shared.domain.model.valueobjects.WebAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebApplicationRepository extends JpaRepository<WebApplication, Long> {
    boolean existsByFrontendUrl(WebAddress frontendUrl);

    boolean existsByBackendUrl(WebAddress backendUrl);
}

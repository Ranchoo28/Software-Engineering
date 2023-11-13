package it.unical.demacs.FundasticServer.Dashboard;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    private final DashboardRepository dashboardRepository;

    public DashboardService(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    public List<Dashboard> findData() {
        return dashboardRepository.findData(1L);
    }
}

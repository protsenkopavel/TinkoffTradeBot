package net.protsenko.service.impl;

import lombok.AllArgsConstructor;
import net.protsenko.models.entities.Timeframe;
import net.protsenko.repositories.TimeframeRepository;
import net.protsenko.service.TimeFrameService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class TimeFrameServiceImpl implements TimeFrameService {

    private TimeframeRepository periodRepository;

    @Override
    public List<Timeframe> getAll() {
        return periodRepository.findAll();
    }

    @Override
    public Timeframe getById(Long id) {
        return periodRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Timeframe.class, id));
    }

    @Override
    public Timeframe getByCode(String code) {
        return periodRepository.findByCodeIgnoreCase(code);
    }
}

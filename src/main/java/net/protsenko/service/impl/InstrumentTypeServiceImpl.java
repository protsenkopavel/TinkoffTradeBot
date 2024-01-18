package net.protsenko.service.impl;

import lombok.AllArgsConstructor;
import net.protsenko.models.entities.InstrumentType;
import net.protsenko.repositories.InstrumentTypeRepository;
import net.protsenko.service.InstrumentTypeService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class InstrumentTypeServiceImpl implements InstrumentTypeService {

    private InstrumentTypeRepository instrumentTypeRepository;

    @Override
    public List<InstrumentType> getAll() {
        return instrumentTypeRepository.findAll();
    }

    @Override
    public InstrumentType getById(Long id) {
        return instrumentTypeRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(InstrumentType.class, id));
    }

    @Override
    public InstrumentType getByCode(String code) {
        return instrumentTypeRepository.findByCodeIgnoreCase(code);
    }
}

package org.springrest.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springrest.dao.SongsRepository;

@Service
@RequiredArgsConstructor
public class SongsService {
    private final SongsRepository repository;
}

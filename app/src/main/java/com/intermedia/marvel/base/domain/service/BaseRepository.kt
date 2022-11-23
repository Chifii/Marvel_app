package com.intermedia.marvel.base.domain.service

import com.intermedia.marvel.base.domain.service.RepositoryFactory.getRetrofit

open class BaseRepository<T>(
    service: Class<T>,
) {
    var service: T = getRetrofit()
        .create(service)
}
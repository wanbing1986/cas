package org.apereo.cas.services;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * Registry of all RegisteredServices.
 *
 * @author Scott Battaglia
 * @author Dmitriy Kopylenko
 * @since 3.1
 */
public interface ServiceRegistry {

    /**
     * Persist the service in the data store.
     *
     * @param registeredService the service to persist.
     * @return the updated RegisteredService.
     */
    RegisteredService save(RegisteredService registeredService);

    /**
     * Remove the service from the data store.
     *
     * @param registeredService the service to remove.
     * @return true if it was removed, false otherwise.
     */
    boolean delete(RegisteredService registeredService);

    /**
     * Retrieve the services from the data store.
     *
     * @return the collection of services.
     */
    Collection<RegisteredService> load();

    /**
     * Find service by the numeric id.
     *
     * @param id the id
     * @return the registered service
     */
    RegisteredService findServiceById(long id);

    /**
     * Find service by the service id.
     *
     * @param id the id
     * @return the registered service
     */
    RegisteredService findServiceById(String id);

    /**
     * Find a service by an exact match of the service id.
     *
     * @param id the id
     * @return the registered service
     */
    RegisteredService findServiceByExactServiceId(final String id);

    /**
     * Find a service by an exact match of the service name.
     *
     * @param name the name
     * @return the registered service
     */
    RegisteredService findServiceByExactServiceName(final String name);

    /**
     * Find service predicate registered service.
     *
     * @param predicate the predicate
     * @return the registered service
     */
    default RegisteredService findServicePredicate(final Predicate<RegisteredService> predicate) {
        return load()
            .stream()
            .filter(predicate)
            .findFirst()
            .orElse(null);
    }

    /**
     * Return number of records held in this service registry. Provides default implementation so that implementations
     * needed this new functionality could override it and other implementations not caring for it could be left alone.
     *
     * @return number of registered services held by any particular implementation
     * @since 5.0.0
     */
    default long size() {
        return load().size();
    }

    /**
     * Returns the friendly name of this registry.
     *
     * @return the name.
     * @since 5.2.0
     */
    String getName();
}

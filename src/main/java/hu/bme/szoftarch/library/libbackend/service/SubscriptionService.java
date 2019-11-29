package hu.bme.szoftarch.library.libbackend.service;

import hu.bme.szoftarch.library.libbackend.model.Subscription;
import hu.bme.szoftarch.library.libbackend.repository.SubscriptionRepository;
import hu.bme.szoftarch.library.libbackend.utils.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Transactional
    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepository.saveAndFlush(subscription);
    }

    @Transactional
    public Subscription getSubscriptionById(Long id) { return subscriptionRepository.getOne(id); }

    @Transactional
    public List<Subscription> getSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @Transactional
    public Subscription updateSubscription(Long id, Subscription subscription) {
        Subscription existingSubscription = subscriptionRepository.findById(id).orElse(new Subscription());
        NullAwareBeanUtils.copyNonNullProperties(subscription, existingSubscription);
        return subscriptionRepository.saveAndFlush(existingSubscription);
    }

    @Transactional
    public void deleteSubscription(Long id) { subscriptionRepository.deleteById(id); }
}

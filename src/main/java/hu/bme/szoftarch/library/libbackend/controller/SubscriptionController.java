package hu.bme.szoftarch.library.libbackend.controller;

import hu.bme.szoftarch.library.libbackend.model.Subscription;
import hu.bme.szoftarch.library.libbackend.service.SubscriptionService;
import hu.bme.szoftarch.library.libbackend.utils.exceptions.IllegalDeleteRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping
    private List<Subscription> getSubscriptions() {
        return subscriptionService.getSubscriptions();
    }

    @GetMapping("{id}")
    public Subscription getSubscription(@PathVariable Long id) {
        return subscriptionService.getSubscriptionById(id);
    }

    @PostMapping
    public Subscription createSubscription(@RequestBody Subscription subscription) {
        return subscriptionService.createSubscription(subscription);
    }

    @PutMapping("{id}")
    public Subscription updateSubscription(@PathVariable Long id, @RequestBody Subscription subscription) {
        return subscriptionService.updateSubscription(id, subscription);
    }

    @DeleteMapping("{id}")
    public void deleteSubscription(@PathVariable Long id) {
        try {
            subscriptionService.deleteSubscription(id);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new IllegalDeleteRequestException("Can not remove subscription, since there are users referencing it.");
        }
    }
}

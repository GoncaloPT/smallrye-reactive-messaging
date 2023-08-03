package io.smallrye.reactive.messaging.mqtt;

import java.util.concurrent.Flow.Publisher;

import jakarta.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.mutiny.Multi;

@ApplicationScoped
public class NullProducingBean {

    @Incoming("data")
    @Outgoing("sink")
    @Acknowledgment(Acknowledgment.Strategy.MANUAL)
    public Message<Integer> process(Message<Integer> input) {
        return input.withPayload(null);
    }

    @Outgoing("data")
    public Publisher<Integer> source() {
        return Multi.createFrom().range(0, 10);
    }

}

package resource_manager;

import org.junit.jupiter.api.*;
import resource_manager.model.Resource;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

class ResourceManagerImplTest {
    private final int resourceAMaxInstances = 5;
    private final int resourceBMaxInstances = 3;
    ResourceManager resourceManager;


    @BeforeEach
    void init() {
        resourceManager = new ResourceManagerImpl(resourceAMaxInstances, resourceBMaxInstances);
    }

    @Test()
    @Timeout(value = 20, unit = TimeUnit.MILLISECONDS)
    @DisplayName("acquireResourceA. Acquires max number of resources, then releases 1 and acquires one more. Returns OK")
    void acquireResourceA_ok() throws InterruptedException {
        Resource resourceA1 = resourceManager.acquireResourceA();
        for (int i=1; i<resourceAMaxInstances; i++) {
            resourceManager.acquireResourceA();
        }
        resourceA1.releaseResource();
        Resource resourceA2 = resourceManager.acquireResourceA();
        Assertions.assertNotNull(resourceA2);
    }

    @Test()
    @DisplayName("acquireResourceA, Acquires max number of resource+1. Fails after timeout")
    void acquireResourceA_TryToAcquireMoreThanPossible_fail() throws InterruptedException, ExecutionException {
        CompletableFuture.supplyAsync(
                () -> {
                    try {
                        // Acquire more Resources than allowed
                        for (int i=0; i<resourceAMaxInstances+1; i++) {
                            resourceManager.acquireResourceA();
                        }
                    } catch (InterruptedException e) {
                        System.out.println("InterruptedException: " + e);
                    }
                    throw new RuntimeException("Something went wrong");
                })
                .orTimeout(20, TimeUnit.MILLISECONDS)
                .handle((result, throwable) -> {
                    if (!(throwable instanceof TimeoutException)) {
                        Assertions.fail();
                    }
                    return result;})
                .get();
    }
}
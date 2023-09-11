package resource_manager;

import resource_manager.model.Resource;
import resource_manager.model.ResourceA;
import resource_manager.model.ResourceB;

import java.util.concurrent.Semaphore;


public class ResourceManagerImpl implements ResourceManager {
    private final Semaphore resourceASemaphore;
    private final Semaphore resourceBSemaphore;

    public ResourceManagerImpl(int resourceAMaxInstances, int resourceBMaxInstances) {
        this.resourceASemaphore = new Semaphore(resourceAMaxInstances);
        this.resourceBSemaphore = new Semaphore(resourceBMaxInstances);
    }

    @Override
    public Resource acquireResourceA() throws InterruptedException {
        resourceASemaphore.acquire();
        System.out.println("ResourceA acquired");
        return new ResourceA(this);
    }

    @Override
    public Resource acquireResourceB() throws InterruptedException {
        resourceBSemaphore.acquire();
        System.out.println("ResourceB acquired");
        return new ResourceB(this);
    }

    @Override
    public void releaseResourceA() {
        resourceASemaphore.release();
        System.out.println("ResourceA released");
    }

    @Override
    public void releaseResourceB() {
        resourceBSemaphore.release();
        System.out.println("ResourceB released");
    }
}

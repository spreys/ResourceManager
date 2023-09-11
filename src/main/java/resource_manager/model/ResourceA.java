package resource_manager.model;

import resource_manager.ResourceManager;

public class ResourceA implements Resource {
    private final ResourceManager resourceManager;

    public ResourceA(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    @Override
    public void doWork() {
        System.out.println("ResourceA, I am doing something!");
    }

    @Override
    public void releaseResource() {
        resourceManager.releaseResourceA();
    }
}

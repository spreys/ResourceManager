package resource_manager.model;

import resource_manager.ResourceManager;

public class ResourceB implements Resource {
    private final ResourceManager resourceManager;

    public ResourceB(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    @Override
    public void doWork() {
        System.out.println("ResourceB, I am doing something!");
    }

    @Override
    public void releaseResource() {
        resourceManager.releaseResourceB();
    }
}

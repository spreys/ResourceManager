package resource_manager;

import resource_manager.model.Resource;
import resource_manager.model.ResourceA;
import resource_manager.model.ResourceB;

public interface ResourceManager {

    Resource acquireResourceA() throws InterruptedException;

    Resource acquireResourceB() throws InterruptedException;

    void releaseResourceA();

    void releaseResourceB();

}

/*
 * Copyright 2017 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.spawning;

import com.google.common.collect.Lists;
import org.terasology.entitySystem.entity.EntityManager;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.prefab.Prefab;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterMode;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.logic.location.LocationComponent;
import org.terasology.math.geom.Vector3f;
import org.terasology.portals.PortalComponent;
import org.terasology.registry.In;

import java.util.Iterator;

@RegisterSystem(RegisterMode.AUTHORITY)
public class SpawningSystem extends BaseComponentSystem {

    @In
    private EntityManager entityManager;

    private Prefab prefabToSpawn;
    private Vector3f spawnPos;

    /**
     *  Gets the position in the world where Oreon has to be spawned.
     *  If number of Portals in the world is more than 1 selects the Portal which triggered the event
     */
    public void setSpawnPos() {
        Iterable<EntityRef> spawnPortals = entityManager.getEntitiesWith(PortalComponent.class);

        for(EntityRef spawnPortal : spawnPortals) {
            spawnPos = spawnPortal.getComponent(LocationComponent.class).getWorldPosition();
        }
    }

    public Prefab getPrefabToSpawn() {
        return prefabToSpawn;
    }


}
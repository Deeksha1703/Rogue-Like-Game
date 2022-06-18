# designosouls
FIT2099 S2 2021 Assignment

### Requirements Implemented for Creative Mode

## Requirement 4: C4 Bomb

Souls can be traded by the Player to buy a new item called C4 Bomb through the Vendor. 
The price of the C4 Bomb will be 200 souls. The player can place the bomb anywhere on 
the map(as long as the Player can enter that area), but the bomb will only activate if
it is dropped on Dirt ground, otherwise it will just deactivate (do nothing). 

Once the Player drops the bomb, it will take 2 turns before the bomb explodes and causes
damage to actors that are in the surrounding area, including the player himself. The 
damage caused by the bomb will be 50 hit points. If an actor is still conscious after 
receiving damage, he will be stunned for a turn.

The bomb can only be activated once, then it will be removed from the map after it has been used.



|                     Requirements                           |   Features (HOW)/ Your Approach / Your Answer   |
| ---------------------------------------------------------- | ----------------------------------------------- |
| Must use at least two (2) classes from the engine package. |   Classes like Location and Exit from the engine package are being used for this requirement. (there are more classes from engine package being used) |
| Must use/re-use at least one (1) existing feature          |   The C4 bomb can be purchased from the Vendor which we have implemented in assignment 2                                             | 
| Must use existing or create new abstraction/interfaces     |   Purchase of this C4Bomb will be done using PurchaseC4BombAction which extends from the PurchaseAction class which is an abstract class |
| Must use existing or create new capabilities.              |   We have made use of the STUNNED capability.When an actor receives damage by stepping on the bombed ground and he is still conscious, this actor will be added a capability of  STUNNED status. |
| Must explain why it adheres to the SOLID principles.       |   We have created a new class for the C4 bomb. This abides by the Single Responsibility principle as the class has only one responsibility. This one class contains all the functionality needed to support that responsibility. We have also created a PurchaseC4BombAction class which extends the PurchaseAction class so that the Player can purchase the bomb through the Vendor. This abides by Liskov Substitution Principle as the PurchaseC4BombAction inherits its functions and abilities from its parent class                                            |  


## Requirement 5:  Invisibility

Souls can be traded by the Player to buy a new item called invisibility cloak which makes the Player invisible 
to enemies. The price of the invisibility cloak will be 500 Souls. The Player can equip the cloak anytime. If the 
invisible cloak is used, the player will be invisible in the map, enemies will stop attacking the player if they are 
already attacking, and continue their wandering behaviour. If no enemy is attacking player, enemies will ignore the 
presence of the player even if player is nearby.



|                     Requirements                           |   Features (HOW)/ Your Approach / Your Answer   |
| ---------------------------------------------------------- | ----------------------------------------------- |
| Must use at least two (2) classes from the engine package. |  We have created 3 new classes- InvisibleCloak, InvisibleAction and PurchaseInvisibleCloak. The Invisible cloak extends the Item class and the InvisibleAction extends the Action class                                              |
| Must use/re-use at least one (1) existing feature          |  The invisible cloak can be purchased from the Vendor which we have implemented in assignment 2                                              | 
| Must use existing or create new abstraction/interfaces     |  Purchase of this C4Bomb will be done using PurchaseC4BombAction which extends from the PurchaseAction class which is an abstract class.                                              |  
| Must use existing or create new capabilities.              |  We have created a new capability called INVISIBLE. When the player executes InvisibleAction, this INVISIBLE status will be added to the player as a new capability.                                               |
| Must explain why it adheres to the SOLID principles.       |  We have created a new class for the invisible cloak, this abides by the single responsibility principle as the class has only one responsibility. This one class contains all the functionality needed to support that responsibility. We have also created a PurchaseInvisibleCloak class which extends the PurchaseAction class so that the Player can purchase the Invisible Cloak through the Vendor. This abides by Liskov Substitution Principle as the PurchaseInvisibleCloak class inherits its functions and abilities from its parent class
|  





## Gameplay
### Characters
**Player** `@`
- This is you

**Skeleton** 'S'
- Skeleton which is an enemy in Design o' Souls

**Undead** 'U'
- Undead which is an enemy in Design o' Souls

**Aldrich the Devourer** 'A'
- Lord of Cinder in Design o' Souls

**Yhorm the Giant** 'Y'
- Lord of Cinder in Design o' Souls

### Weapons

**Broadsword** `1`
- Weapon that can be purchased from the Vendor for 500 Souls

**Darkmoon Longbow** 'D'
- Weapon held by Aldrich the Devourer

**Giant Axe** 'T'
- Weapon that can be purchased from the Vendor for 100 Souls

**Storm Ruler** '7'
- Weapon that can be found next to Yhorm the Giant in the Profane Capital map

**Yhorm's Giant Machete** 'L'
- Weapon held by Yhorm the Giant

### Grounds

**Bombed Ground** `x`
- Ground when C4 Bomb has been dropped

**Bonfire** 'B'
- Bonfire where player can rest

**Burning Ground** 'v'
- Ground surrounding the area where a C4 Bomb is dropped

**Cemetery** 'C'
- Ground which spawns undead

**Dirt** '.'
- Ground that represents bare dirt

**Floor** '_'
- Ground that represents floor inside a building

**Fog Door** '='
- Stepping on the fog door can transport the player from one map to another

**Valley** '+'
- Kills the Player if they step on it

**Vendor** 'F'
- Player can purchase weapons and items from the Vendor

**Wall** '#'
- Player and objects cannot enter the wall 

### Items

**C4 Bomb** 'X'
- Item which Player can purchase from Vendor for 300 Souls

**Cinders of a Lord** 'L'
- Dropped by Lord of Cinders when they dies

**Estus Flask** 'E'
- Item carried by the Player which the Player can drink to reset hit points

**Token of Souls** '$'
- Souls the player had before dying which spawns at the player's dying location


|                     Requirements                           |   Features (HOW) Your Approach / Your Answer   |
| ---------------------------------------------------------- | ---------------------------------------------- |
| Must use at least two (2) classes from the engine package. |                                                |
| Must use/re-use at least one (1) existing feature          |                                                | 
| Must use existing or create new abstraction/interfaces     |                                                |  
| Must use existing or create new capabilities.              |                                                |  
| Must explain why it adheres to the SOLID principles.       |                                                |  



Run function was missing in gradle build so i added it.
Search strategies not incorporated as choices but tested to work in the test case in Solar system app test.Since in the requirements
it said its ok if they are hard coded.
Tried seperating everything into different
packages found out it wasnt nessecery besdies
not being able to do it in time without visibility
errors through using factory classes but
decided against it.

SolarSystemApp : Main application class managing user interactions
also contains FileManager, Registry, current SolarSystem
File manager: Handles file persistencem through save and load method,has a field the file or datafile name
Registry: Manages SolarSystem instances and contains a list of SolarSystems
Solar system: Contains a Star and multiple Planets also contains planet management operations and
palnet sorting operations
Heavenly Body: Base class for celestial bodies had methods that concerne valid average radius and name that must be implemented by celestila body subclasses
Star: represent star of a SolarSystem,inherits from HeavenlyBody contains planet instances

Planet: Orbital body with moon instances has moon management operations,inherits from heavenly body
Moon:celestial body that orbits a planet inherits from heavenly body.

HeavenlyBodyLimits (Enum) :Defines size constraints for celestial bodies used by celestial bodies for radius validation and is in association or dependency with those classes.

SolarSystem has a Star and multiple Planets. Planet has multiple Moons.

These are strong ownership relations.Since a removal of a solar system removes all other heavenly objects in it in a hierarchical manner same goes for Sun and planet and moon.

Registry has an association with solar system since it manages and operates on solar system instances.

Solar system app uses multiple classes and contains a user interface and menu display methods
that utilize methods from the previous classes in particular Solar system,File manager ,Registry based on user input and as such is in association with those classes

Finally the SearchStrategy interface implement defines a matches method implemented by
NameSearch: which implements it to filter by name

RadiusSearch: implements it to fileter by size

MoonCountSearch: implements it to filter by moon count


and CompositeSearch: which  Combines multiple strategies by having two subclasses for adittional search or alternative search

Finally Search context utilizes the Search strategy interface and executes search using a selected strategy

The the classes in conjuction represent a Strategy pattern.
The pattern is tested in the the test cases below in the system app class utilizing the previous
Solar system app related classes.
''For this requirements it is sufficient that you design and implement some hard coded examples (for example as test cases)''
![alt text](image.png)
![alt text](image.png)
![alt text](image.png)
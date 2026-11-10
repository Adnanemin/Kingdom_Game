# Kingdom Game

A console-based kingdom management and strategy game developed in Java as an Object-Oriented Programming project.

The player manages a kingdom by constructing buildings, training military units, managing resources, and making strategic decisions over 30 game days. The objective is to prepare the kingdom for a final battle against the Dark Empire.

---

## Features

- Kingdom management (Gold, Food, Population, Happiness, Housing)
- Building construction with unique-building and housing rules
- Military unit training with building prerequisites
- Difficulty selection (Easy, Normal, Hard)
- Random event system
- Dynamic rule engine with a pluggable attribute registry
- Final battle simulation with defence and morale modifiers
- Custom exception hierarchy
- Modular manager-based architecture

---

## Requirements & Running

- JDK 16 or newer (the code uses switch expressions, text blocks and pattern matching)

```bash
chmod +x run.sh   # first time only
./run.sh
```

`run.sh` compiles every source file under `src/` into `out/` and starts `game.GameEngine`.

---

## Project Structure

The project is organized into packages with clear responsibilities:

- **game** – Core classes: `GameEngine` (game loop), `GameSession` (day counter and running state), `Kingdom` (state and daily logic), `Army` (unit collection)
- **buildings** – `Building` hierarchy, daily building effects and building hooks (`isUnique`, `getHouseCapacity`, `getDefenceBonus`)
- **units** – `Unit` hierarchy; each unit declares its battle value and required building
- **events** – `RandomEvent` interface and concrete events
- **managers** – Game mechanics: building, training, taxation, rebellion, random events, difficulty and input
- **rules** – Dynamic rule system (`RuleEngine`, `RuleFactory`, `ParsedRule`)
- **rules.attributes** – `KingdomAttribute` interface and one strategy class per rule-able stat
- **battle** – Final battle logic and narration
- **setup** – Kingdom creation (`KingdomFactory`) and the `Difficulty` enum
- **ui** – Menus and status output
- **util** – `Outcome<T>` generic result wrapper and the `Purchasable` interface
- **exceptions** – Custom exception hierarchy rooted at `GameException`

---

## Object-Oriented Concepts

This project demonstrates the following OOP concepts:

- Encapsulation
- Abstraction
- Inheritance
- Polymorphism: subtype (overriding), enum-constant, overloading, parametric (generics), coercion
- Interfaces & Default Methods
- Hook Methods
- Generics
- Exception Handling
- Separation of Concerns
- Information Hiding
- Subtyping & Multityping
- Composition
- Extensibility
- Programming to Interfaces

---

## How to Play

1. Start the game.
2. Choose a difficulty level.
3. Enter the name of your kingdom.
4. Manage your kingdom day by day by:
   - Building structures
   - Training military units
   - Adjusting tax rates
   - Creating custom rules
5. Survive until day 30 and defeat the enemy kingdom in the final battle.

---

## Design Highlights

The project follows a modular architecture where responsibilities are separated into dedicated manager classes. Game state is maintained by the `Kingdom` and `GameSession` classes, while mechanics such as random events, building construction, taxation, and unit training are delegated to specialized managers.

Type-specific rules are expressed through polymorphism instead of `instanceof` checks: buildings report their own housing capacity, defence bonus and uniqueness, and units report the building they require. Difficulty-specific enemy armies are defined through constant-specific methods of the `Difficulty` enum, and custom rules resolve their attributes through a registry of `KingdomAttribute` strategies. Custom exceptions share the abstract `GameException` parent, so player actions are handled through a single catch block.

---

## Author

**Adnan Emin Nalçacı**
University of Messina  
Bachelor's Degree in Data Analysis

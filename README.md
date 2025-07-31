# BeanHighlight

**BeanHighlight** is an IntelliJ IDEA plugin that automatically highlights your Service and Mapper bean variables in Java code. This makes it easier to recognize key Spring and MyBatis components at a glance.

---

## Features

- **Highlights Service and Mapper variables**  
  Automatically applies color highlighting to Java variables whose names end with `Service` or `Mapper` (e.g., `userService`, `orderMapper`).
- **Follows Java bean naming conventions**  
  Only variables with a lowercase first letter (e.g., `userService`) will be highlighted, ensuring consistency.
- **Non-intrusive, lightweight**  
  No impact on code structure or performance—just enhanced visibility in the editor.

---

## Installation

1. Download the latest plugin `.zip` from the [Releases](./releases) page.
2. In IntelliJ IDEA, go to **Settings → Plugins → Install plugin from disk...**
3. Select the downloaded zip and follow the prompts.
4. Restart IDEA to activate highlighting.

---

## Usage

- After installing and restarting IDEA, open or edit any Java file containing variables like:

  ```java
  private UserService userService;
  private UserMapper userMapper;
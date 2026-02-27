# Ragebait User Guide


![Product Screenshot of Ragebait Bot](https://calijacked.github.io/ip/Ui.png)

Ragebait is a command-line interface (CLI) application that helps users manage tasks, deadlines, and contacts efficiently. It allows users to:

- Add, view, find, mark, unmark, and delete tasks
- Track deadlines and priorities
- Maintain a contact list with names, phone numbers, and emails
- Add, view, delete, and find contacts

### Prerequisites
**Java 17 or above** must be installed on your system.  
**Mac users:** Ensure your JDK version matches the app’s requirements.
Check your Java version with: 
```java -version ```

1. Download the latest ragebait.jar from the releases page
2. Choose a folder to serve as your home folder for Ragebait.
3. Copy the ragebait.jar file into this folder.
4. Launch the app by running the jar file: ```java -jar ragebait.jar```

## Commands
Here is the overview of all commands in the CLI application.

### General Commands

| Feature | Format | Example |
|------|------|--------|
| Exit|`bye`|`bye`|
| Add ToDo Task|`task todo <description>`|`task todo Buy milk`|
| Add Deadline Task | `task deadline <description> /by <d/M/YYYY HHMM>` | `task deadline Submit report /by 25/2/2026 2359` |
| Add Event Task| `task event <description> /from <d/M/YYYY HHMM> /to <d/M/YYYY HHMM>` | `task event Team meeting /from 25/2/2026 1400 /to 25/2/2026 1500` |
| List Tasks | `task list` | `task list` |
| Delete Task | `task delete <number>` | `task delete 2` |
| Mark Task | `task mark <number>` | `task mark 1` |
| Unmark Task | `task unmark <number>` | `task unmark 1` |
| Find Task | `task find <keyword>` | `task find homework` |
| Add Contact | `contact add /name <name> /phone <phone> /email <email>` | `contact add /name Jack /phone 91234567 /email jack@example.com` |
| List Contacts | `contact list` | `contact list` |
| Delete Contact | `contact delete <number>` | `contact delete 1` |
| Find Contact | `contact find <keyword>` | `contact find Jack` |

# Features

## 1. Exit Application

**Action:**  
Exits the Ragebait application.

**Format:** `bye`

**Expected Output:**

```
Goodbye. Go touch some grass. Or don’t. Not my problem anymore.
```

**Notes:**
* Extraneous parameters for this command will be ignored. e.g `bye 123` will still terminate the app.
  
## 2. Add ToDo Task

Adds a new ToDo task to your task list.

**Format:** `task todo <description>`

**Example:** `task todo Buy milk`

**Expected Output:**

```
Congrats. Another responsibility you’ll probably ignore:
  [T][ ] Buy milk
That makes 1 task. Collecting them like regrets.
```

**Notes:**
* The ToDo description must not be empty.
* The task category must be present.

## 3. Add Deadline Task

Adds a new Deadline task to your task list.

**Format:** `task deadline <description> /by <d/M/YYYY HHMM>`

**Example:** `task deadline Submit report /by 25/2/2026 2359`

**Expected Output:**

```
Congrats. Another responsibility you’ll probably ignore:
  [D][ ] Submit report (by: 25 Feb 2026 23:59)
That makes 2 tasks. Collecting them like regrets.
```

**Notes:**
* The task category must be present.
* The Deadline description and /by datetime must not be empty.
* The /by date accepts both single- and double-digit day/month values. Examples: `1/1/2021` and `01/01/2021` are both valid.
* The date and time provided must be valid calendar values.
* Time is in 24-hour format (`HHMM`) without a colon.
* `2400` is accepted and treated as midnight of the following day.  
   Example:  
  `task deadline Submit report /by 25/2/2026 2400`  
  is equivalent to  
  `task deadline Submit report /by 26/2/2026 0000`

## 4. Add Event Task

Adds a new Event task to your task list.

**Format:** `task event <description> /from <d/M/YYYY HHMM> /to <d/M/YYYY HHMM>`

**Example:** `task event Team meeting /from 25/2/2026 1400 /to 25/2/2026 1500`

**Expected Output:**

```
Congrats. Another responsibility you’ll probably ignore:
  [E][ ] Team meeting (from: 25 Feb 2026 14:00 to: 25 Feb 2026 15:00)
That makes 3 tasks. Collecting them like regrets.
```

**Notes:**
* The task category must be present.
* The Event description and /from datetime and /to datetime must not be empty.
* The /from and /to date accepts both single- and double-digit day/month values. Examples: `1/1/2021` and `01/01/2021` are both valid.
* The date and time provided must be valid calendar values.
* Time is in 24-hour format (`HHMM`) without a colon.
* `2400` is accepted and treated as midnight of the following day.  
   Example:  
  `task event Team meeting /from 25/2/2026 1400 /to 25/2/2026 2400`  
  is equivalent to  
  `task event Team meeting /from 25/2/2026 1400 /to 26/2/2026 0000`
* The /from date must be strictly before the /to date.

## 5. List Tasks

Shows a list of all tasks.

**Format:** `task list`

**Example:** `task list`

**Expected Output:**

```
Brace yourself. Here’s your disaster lineup:
1. [T][ ] english
2. [D][ ] project (by: 04 Apr 2026 12:34)
3. [T][ ] Buy milk
4. [D][ ] submit report (by: 01 Jan 2021 12:00)
5. [E][ ] Team meeting (from: 25 Feb 2026 15:00 to: 25 Feb 2026 15:00)
```

**Notes:**
* The task category must be present.
* Extraneous parameters for this command will be ignored. e.g `task list` will still show the list of tasks.

## 6. Delete Task

Deletes an existing task from the task list.

**Format:** `task delete <number>`

**Example:** `task delete 2`

**Expected Output:**

```
Deleted. Happy now?
[D][ ] project (by: 04 Apr 2026 12:34)
You’re down to 4 task(s). Try not to mess those up too.
```

**Notes:**
* The task category must be present.
* <number> must be a valid integer corresponding to an existing task in the list.

## 7. Mark Task

Marks an existing task from the task list as complete.

**Format:** `task delete <number>`

**Example:** `task mark 1`

**Expected Output:**

```
Wait… you actually completed something? Miracles happen:
[T][X] english
```

**Notes:**
* The task category must be present.
* <number> must be a valid integer corresponding to an existing task in the list.
* Ragebait will prompt the user if they attempt to mark a task that is already marked.

## 8. Unmark Task

Unmarks an existing task from the task list as incomplete.

**Format:** `task unmark <number>`

**Example:** `task unmark 1`

**Expected Output:**

```
Commitment issues already?
[T][ ] english
```

**Notes:**
* The task category must be present.
* Ragebait will prompt the user if they attempt to unmark a task that is already marked.

## 9. Find Task

Searches the task list for tasks that contain the given keyword in their <description>.

**Format:** `task find <keyword>`

**Example:** `task find eng`

**Expected Output:**

```
Search complete. These are the only things matching your vague input:
1. [T][ ] english
```

**Notes:**
* The task category must be present.
* Ragebait will prompt the user and will not return any results if the task is not found.

## 10. Add contact

Adds a new contact to your contacts list.

**Format:** `contact add /name <name> /phone <phone> /email <email>`

**Example:** `contact add /name Jack /phone 91234567 /email jack@example.com`

**Expected Output:**

```
Wow, a new contact. Someone actually tolerates you:
Name: Jack | Phone: 91234567 | Email: jack@example.com
You now have 1 contact(s). Networking era?
```

**Notes:**
* The contact category must be present.
* The /name, /phone and /email tags must be present.
* <name>, <phone> and <email> must be present.
* The phone number must be strictly 8 digits.
* Email must be in correct syntax
  - Must include exactly one `@` symbol separating the local part and the domain part.
  - The **local part** (before `@`) can contain:
     1. Letters (`a-z`, `A-Z`)
     2. Digits (`0-9`)
     3. 3. Special characters: `+`, `_`, `.`, `-`
  - The **domain part** (after `@`) must:
     1. Contain letters, digits, dots (`.`), and hyphens (`-`)
     2. Include at least one dot (`.`) to separate the domain name and top-level domain (TLD)
     3. End with a valid TLD (e.g., `.com`, `.org`, `.net`, 2–6 letters)
  - No spaces are allowed in the email.
  - Consecutive dots in the local or domain part are not allowed (optional for stricter validation).
  - Examples of valid emails:
     1. `jack@example.com`
     2. `user.name_123@gmail.com`
     3. `first-last+tag@sub.domain.org`
  - Examples of invalid emails:
     1. `user@localhost` (no dot in domain)
     2.  `user@123` (invalid domain)
     3.  `user..name@example.com` (consecutive dots, if enforcing stricter rule)

## 11. List Contacts

Shows a list of all contacts.

**Format:** `contact list`

**Example:** `contact list`

**Expected Output:**

```
Here’s the full list of people who haven’t blocked you yet:
1. Name: Jack | Phone: 91234567 | Email: jack@example.com
2. Name: jack | Phone: 1234 | Email: 123@gmail.com
```

**Notes:**
* The contact category must be present.
* Extraneous parameters for this command will be ignored. e.g `task contact` will still show the list of contacts.

## 12. Delete Contact

Deletes an existing contact from the contact list.

**Format:** `contact delete <number>`

**Example:** `contact delete 2`

**Expected Output:**

```
Contact removed. Another bridge burned?
Name: jack | Phone: 1234 | Email: 123@gmail.com
You’re left with 1 contact(s). Choose wisely next time.
```

**Notes:**
* The contact category must be present.
* <number> must be a valid integer corresponding to an existing contact in the list.

## 13. Find Task

Searches the contact list for contacts whose <name> contain the specified keyword.

**Format:** `contact find <keyword>`

**Example:** `contact find ck`

**Expected Output:**

```
Search done. These are your so-called contacts:
1. Name: Jack | Phone: 91234567 | Email: jack@example.com

```

**Notes:**
* The contact category must be present.
* Ragebait will prompt the user and will not return any results if the contact is not found.

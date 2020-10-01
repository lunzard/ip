# User Guide

## Features 

### Feature 1: Manage tasks
Duke can add, mark, delete different type of tasks (Todo, Deadline, Event) for you!

### Feature 2: Search keyword
Duke can help you search the tasks matching the keyword provided by you!

### Feature 3: Auto-Saving
Duke can save your tasks even if you close it!

## Usage

### `TODO` - add todo task

Add the simplest type of task into your list.

Example of usage: 

`TODO read book`

Expected outcome:

`Got it. I've added this task:
   [T][✘] readbook
 Now you have 1 duke.tasks in the list`
### `DEADLINE` - add Deadline

Add a deadline time on top of todo.

Example of usage: 

`DEADLINE return book /BY 10pm`

Expected outcome:

`Got it. I've added this task:
   [D][✘] return book (by: 10pm)
 Now you have 2 duke.tasks in the list`
### `EVENT` - add Event

Add a time point on top of todo.

Example of usage: 

`EVENT watch movie /at monday`

Expected outcome:

`Got it. I've added this task:
   [E][✘] watch movie (at: monday)
 Now you have 3 duke.tasks in the list`
### `LIST` - List all added tasks

Print all added tasks in a column

Example of usage: 

`LIST`

Expected outcome:

`Here are the duke.tasks in your list:
 1.[T][✘] readbook
 2.[D][✘] return book (by: 10pm)
 3.[E][✘] watch movie (at: monday)`
### `DONE` - Mark selected task as done

Change the sign of the task into tick

Example of usage: 

`DONE 1`

Expected outcome:

`Nice! I've marked this task as done:
   [T][✓] readbook`
### `DELETE` - Remove selected task

Remove task of given index from list.

Example of usage: 

`DELETE 2`

Expected outcome:

`Noted. I've removed this task:
 [D][✘] return book (by: 10pm)`
### `FIND` - Search for matching tasks

Find and print all tasks with match strings.

Example of usage: 

`FIND movie`

Expected outcome:

`Here are the matching tasks in your list:
 2.[E][✘] watch movie (at: monday)`
### `BYE` - Describe action

Describe action and its outcome.

Example of usage: 

`BYE`

Expected outcome:

`Bye. Hope to see you again soon!`
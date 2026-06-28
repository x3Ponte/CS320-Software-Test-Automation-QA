# CS 320: Software Testing and Automation QA

## Summary

This repository contains artifacts from CS 320 at SNHU, showcasing my work in software testing, automation, and quality assurance. The files included are the contact service from Project One and the summary and reflections report from Project Two.

## Reflection

### How can I ensure that my code, program, or software is functional and secure?

I'd say that the biggest thing this course taught me is that writing the code and writing the tests are equally important parts of the job. Throughout Project One I had to make sure every field in each class had validation in both the constructor and the setter so there was no way to pass bad data into the system at any point in its lifecycle. Running the JUnit test suites through Eclipse's Coverage As tool and hitting 100% code coverage across all six classes gave me real confidence that every line and conditional branch was actually being exercised. Input validation is the first line of defense for correctness and security and then the tests are what actually prove it is working.

### How do I interpret user needs and incorporate them into a program?

As for this course the customer requirements were handed to me directly in the assignment specifications, and essentially my job was to just translate those into working code. I approached each requirement as a test case before writing any implementation logic which kept me focused on what the user actually needed rather than what was easiest to build. When the requirements said a phone number had to be exactly 10 digits and not null, I wrote tests like `testPhoneNullThrows` and `testPhoneTooShortThrows` before I even touched the validation code. I found that interpreting user needs means actually reading requirements carefully then questioning edge cases the spec does not spell out and at that point letting those questions drive the design...

### How do I approach designing software?

My approach to designing software definitely started with breaking requirements down into the smallest testable units before writing any logic. And for Project One that meant mapping every field constraint to at least one test and every service operation to its own test method so the design stayed grounded in what the system was supposed to do rather than just what was convenient to build. I also focused on efficiency from the start by using a HashMap for constant time lookup in the service classes and creating shared helper methods like `getContactOrThrow` to avoid repeating the same logic across multiple update methods. I also came to realize that design decisions that seem small upfront like validating in the setter as well as the constructor are what turn out to matter a lot when a codebase grows and other developers start making assumptions about how your code behaves.

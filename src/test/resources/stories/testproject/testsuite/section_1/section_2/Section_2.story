Section_2

As a user
I want to reproduce steps
In order I want so


Scenario: Testcase_1

Meta:
@categories priority_4 testproject testsuite section_1 section_2 Section_2

Given Step 1
When Step 1
Then Expected 1
Then Expected Common

Examples:
root_login      |root_password |name       |password |role             
admin           |admin         |admintest1 |QwErTy   |root: Full rights


Scenario: Testcase_2

Meta:
@categories priority_4 testproject testsuite section_1 section_2 Section_2

Given Preconditions common
When Step 1
Then Expected 1
When Step 2
When Step 3
Then Expected 3
Then Expected Common

Examples:
root_login      |root_password |name       |password |role             
admin           |admin         |admintest1 |QwErTy   |root: Full rights

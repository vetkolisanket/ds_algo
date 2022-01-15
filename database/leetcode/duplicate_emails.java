/*
182. Duplicate Emails

Table: Person

+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| email       | varchar |
+-------------+---------+
id is the primary key column for this table.
Each row of this table contains an email. The emails will not contain uppercase letters.
 

Write an SQL query to report all the duplicate emails.

Return the result table in any order.

The query result format is in the following example.

 

Example 1:

Input: 
Person table:
+----+---------+
| id | email   |
+----+---------+
| 1  | a@b.com |
| 2  | c@d.com |
| 3  | a@b.com |
+----+---------+
Output: 
+---------+
| Email   |
+---------+
| a@b.com |
+---------+
Explanation: a@b.com is repeated two times.
*/

//Using Join
# Write your MySQL query statement below
SELECT DISTINCT a.Email FROM Person a JOIN Person b ON (a.Email = b.Email) WHERE a.id <> b.id

//My soln
# Write your MySQL query statement below
SELECT DISTINCT p1.Email FROM Person p1, Person p2 WHERE p1.email = p2.email AND p1.id <> p2.id

//Another way
# Write your MySQL query statement below
SELECT Email FROM Person GROUP BY Email HAVING COUNT(*) > 1

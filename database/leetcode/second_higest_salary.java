/*
176. Second Highest Salary

Table: Employee

+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| salary      | int  |
+-------------+------+
id is the primary key column for this table.
Each row of this table contains information about the salary of an employee.
 

Write an SQL query to report the second highest salary from the Employee table. If there is no second highest salary, the query should report null.

The query result format is in the following example.

 

Example 1:

Input: 
Employee table:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
Output: 
+---------------------+
| SecondHighestSalary |
+---------------------+
| 200                 |
+---------------------+
Example 2:

Input: 
Employee table:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
+----+--------+
Output: 
+---------------------+
| SecondHighestSalary |
+---------------------+
| null                |
+---------------------+
*/

# Write your MySQL query statement below
SELECT MAX(salary) as SecondHighestSalary FROM Employee WHERE salary < (SELECT MAX(salary) FROM Employee)

//Select nth salary
SELECT Salary AS NthHighest
FROM Employee
ORDER BY Salary DESC
LIMIT N-1,1

to retrieve Nth highest

SELECT MAX(Salary) AS NthHighestSalary
FROM (SELECT Salary, DENSE_RANK() OVER (ORDER BY SALARY DESC) row_rank
FROM Employee) AS s WHERE row_rank = N;

select max(salary) from employee
where salary not in (
select salary from (
select DISTINCT salary from employee
order by salary desc )
where rownum<=2);

/*
184. Department Highest Salary

Table: Employee

+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| id           | int     |
| name         | varchar |
| salary       | int     |
| departmentId | int     |
+--------------+---------+
id is the primary key column for this table.
departmentId is a foreign key of the ID from the Department table.
Each row of this table indicates the ID, name, and salary of an employee. It also contains the ID of their department.
 

Table: Department

+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
+-------------+---------+
id is the primary key column for this table.
Each row of this table indicates the ID of a department and its name.
 

Write an SQL query to find employees who have the highest salary in each of the departments.

Return the result table in any order.

The query result format is in the following example.

 

Example 1:

Input: 
Employee table:
+----+-------+--------+--------------+
| id | name  | salary | departmentId |
+----+-------+--------+--------------+
| 1  | Joe   | 70000  | 1            |
| 2  | Jim   | 90000  | 1            |
| 3  | Henry | 80000  | 2            |
| 4  | Sam   | 60000  | 2            |
| 5  | Max   | 90000  | 1            |
+----+-------+--------+--------------+
Department table:
+----+-------+
| id | name  |
+----+-------+
| 1  | IT    |
| 2  | Sales |
+----+-------+
Output: 
+------------+----------+--------+
| Department | Employee | Salary |
+------------+----------+--------+
| IT         | Jim      | 90000  |
| Sales      | Henry    | 80000  |
| IT         | Max      | 90000  |
+------------+----------+--------+
Explanation: Max and Jim both have the highest salary in the IT department and Henry has the highest salary in the Sales department.
*/

//Another way
# Write your MySQL query statement below
SELECT d.name AS Department, e.name AS Employee, Salary FROM Employee e, Department d WHERE e.departmentId = d.id AND (departmentId, salary) IN (SELECT departmentId, MAX(salary) FROM Employee GROUP BY departmentId) 

# Write your MySQL query statement below
SELECT d.name AS Department, e1.name AS Employee, salary AS Salary FROM Employee e1, Department d WHERE e1.departmentId = d.id AND NOT EXISTS (SELECT 1 FROM Employee e2 WHERE e2.salary > e1.salary AND e2.departmentId = e1.departmentId)

//Another way
# Write your MySQL query statement below
SELECT d.name as Department, e.name as Employee, salary as Salary FROM Employee e, (SELECT departmentId, MAX(salary) as maxSalary FROM Employee GROUP BY departmentId) t, Department d WHERE e.departmentId = t.departmentId AND e.departmentId = d.id AND e.salary = t.maxSalary

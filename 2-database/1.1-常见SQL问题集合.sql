
Q：写SQL：
1、有一个表Employee，查一个公司里所有超过平均工资的员工。
select m.name, m.salary
from EMPLOYEE m, (select avg(t.salary) salavg from EMPLOYEE t) n
where m.salary > n.salavg;

2、女性员工数大于五个人的部门。
select n.dep from (
	select count(*)  numb,t.dep  from EMPLOYEE t where t.sex = 'f'  group by t.dep
) n where n.numb>5;

3、分页语句。
SELECT * FROM (
	SELECT ROWNUM AS rowno, t.*
	FROM Employee t
	WHERE 1 = 1 AND ROWNUM <= 10
) table_alias
WHERE table_alias.rowno >= 5;

SELECT * FROM (
	SELECT a.*, ROWNUM rn
	FROM ( SELECT * FROM Employee ) a
	WHERE ROWNUM <= 10
)
WHERE rn >= 5

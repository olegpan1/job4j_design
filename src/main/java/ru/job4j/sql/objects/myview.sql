create view most_popular as
select b.name as book_name, count(a.name) as count
from students as s
         join orders o on s.id = o.student_id
         join books b on o.book_id = b.id
         join authors a on b.author_id = a.id
group by (b.name)
order by Count desc, book_name;

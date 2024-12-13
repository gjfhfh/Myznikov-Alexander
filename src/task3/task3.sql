select post.post_id
from post
         left join comment c on post.post_id = c.post_id
where c.comment_id is null
union
select sub.post_id
from (select p.post_id, count(c.comment_id)
      from post p
               inner join comment c on p.post_id = c.post_id
      group by p.post_id
     ) sub
where sub.count = 1
order by post_id
limit 10;

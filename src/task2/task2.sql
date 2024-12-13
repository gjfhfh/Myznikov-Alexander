select p.post_id
from post p
where length(p.content) > 20
  and p.title ~ '^[0-9]'
  and p.post_id in (
    select p.post_id
    from post p
             left join comment c on p.post_id = c.post_id
    group by p.post_id
    having count(c.comment_id) = 2
)
order by p.post_id
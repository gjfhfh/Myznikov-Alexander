SELECT post_id
FROM post
WHERE post_id NOT IN (SELECT post_id FROM comment)
OR post_id IN (SELECT post_id FROM comment GROUP BY post_id HAVING COUNT(*) = 1)
ORDER BY post_id
LIMIT 10;

SELECT post_id
FROM post
WHERE post_id IN (SELECT post_id FROM comment GROUP BY post_id HAVING COUNT(*) = 2)
AND title ~ '^[0-9]'
AND LENGTH(content) > 20
ORDER BY post_id;

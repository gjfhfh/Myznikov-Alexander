SELECT COUNT(*)
FROM profile
WHERE profile_id NOT IN (SELECT DISTINCT profile_id FROM post);

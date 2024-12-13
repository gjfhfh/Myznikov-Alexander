select count(*)
from profile
         left join post p on profile.profile_id = p.profile_id
where p.content is null;
-- 5

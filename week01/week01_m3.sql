-- 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리(페이징 포함) --

-- 진행중
SELECT
    um.user_mission_id,
    um.user_mission_status,
    um.created_at,
    m.mission_id,
    m.mission_title,
    m.mission_point,
    m.mission_cost,
    s.store_id,
    s.store_name,
    m.mission_due - DATEDIFF(CURDATE(), DATE(um.created_at)) AS left_days
FROM user_mission um
         JOIN mission m ON m.mission_id = um.mission_id
         LEFT JOIN store s ON s.store_id = m.store_id
WHERE um.user_id = :user_id
  AND um.user_mission_status = 'IN_PROGRESS'
  AND DATEDIFF(CURDATE(), DATE(um.created_at)) < m.mission_due
ORDER BY um.created_at DESC, um.user_mission_id DESC
    LIMIT :page_size OFFSET :offset;

-- 진행 완료
SELECT
    um.user_mission_id,
    um.user_mission_status,
    um.created_at,
    m.mission_id,
    m.mission_title,
    m.mission_point,
    m.mission_cost,
    s.store_id,
    s.store_name
FROM user_mission um
         JOIN mission m ON m.mission_id = um.mission_id
         LEFT JOIN store s ON s.store_id = m.store_id
WHERE um.user_id = :user_id
  AND um.user_mission_status = 'SUCCESS'
ORDER BY um.created_at DESC, um.user_mission_id DESC
    LIMIT :page_size OFFSET :offset;
-- 홈 화면 쿼리 (현재 선택 된 지역에서 도전이 가능한 미션 목록, 페이징 포함)--

-- 지역 별 미션 카운팅 부분
SELECT
    r.region_id,
    r.region_name,
    (
        SELECT COUNT(*)
        FROM store s
                 JOIN mission m ON m.store_id = s.store_id
                 JOIN user_mission um
                      ON um.mission_id = m.mission_id
                          AND um.user_id = :user_id
        WHERE s.region_id = r.region_id
          AND um.user_mission_status = 'SUCCESS'
    ) AS success_count
FROM region r
WHERE r.region_id = :region_id;

-- 지역별 도전 가능 미션 목록 부분
SELECT
    m.mission_id,
    m.mission_title,
    m.mission_point,
    m.mission_cost,
    s.store_id,
    s.store_name,
    s.store_type,
    m.mission_due
FROM user_mission um
         JOIN mission m ON m.mission_id = um.mission_id
         JOIN store s ON s.store_id = m.store_id
WHERE um.user_id = :user_id
  AND s.region_id = :region_id
  AND um.user_mission_status = 'NOT_YET'
ORDER BY m.created_at DESC, m.mission_id DESC
    LIMIT :page_size OFFSET :offset;
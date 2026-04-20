(defn problem-compress-a-sequence [a]
  (map first (partition-by identity a)))
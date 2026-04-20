(defn hex_key [num]
  (count (filter #{\2 \3 \5 \7 \B \D} num)))
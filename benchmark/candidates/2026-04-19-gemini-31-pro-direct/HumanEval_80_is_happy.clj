(defn is_happy [s]
  (if (< (count s) 3)
    false
    (every? #(= 3 (count (set %))) (partition 3 1 s))))
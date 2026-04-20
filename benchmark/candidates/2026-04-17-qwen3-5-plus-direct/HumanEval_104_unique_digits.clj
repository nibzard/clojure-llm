(defn unique_digits [x]
  (vec (sort (filter (complement #(some #{\0 \2 \4 \6 \8} (str %))) x))))
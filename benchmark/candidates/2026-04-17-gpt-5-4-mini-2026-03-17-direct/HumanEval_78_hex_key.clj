(defn hex_key
  [num]
  (let [prime-digits #{\2 \3 \5 \7 \B \D}]
    (count (filter prime-digits num))))
(defn multiple_to_single
  "	Write a function to join a list of multiple integers into a single integer."
  [L]
  (let [nums (map #(Math/abs (long %)) L)]
    (if (empty? nums)
      0
      (let [sign (if (neg? (first L)) -1 1)
            digits (apply str nums)]
        (* sign (Long/parseLong digits))))))
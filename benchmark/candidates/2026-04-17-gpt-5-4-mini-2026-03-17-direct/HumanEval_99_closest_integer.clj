(defn closest_integer
  [value]
  (let [n (bigdec value)
        i (bigint n)
        frac (- n (bigdec i))]
    (cond
      (zero? frac) i
      (pos? frac) (if (>= frac 1/2) (inc i) i)
      :else (if (<= frac -1/2) (dec i) i))))
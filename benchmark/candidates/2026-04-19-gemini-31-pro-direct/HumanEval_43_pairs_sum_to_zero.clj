(defn pairs_sum_to_zero [l]
  (loop [seen #{}
         [x & xs] l]
    (if (nil? x)
      false
      (if (contains? seen (- x))
        true
        (recur (conj seen x) xs)))))
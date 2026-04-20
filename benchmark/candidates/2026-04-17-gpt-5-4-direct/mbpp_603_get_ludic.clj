(defn get_ludic
  "	Write a function to get all lucid numbers smaller than or equal to a given integer."
  [n]
  (let [limit (long (or n 0))]
    (if (< limit 1)
      []
      (loop [survivors (vec (range 1 (inc limit)))
             ludics []]
        (if (empty? survivors)
          ludics
          (let [l (first survivors)
                remaining (subvec survivors 1)]
            (recur
             (if (<= l 1)
               remaining
               (->> remaining
                    (keep-indexed (fn [idx x]
                                    (when (not (zero? (mod (inc idx) l)))
                                      x)))
                    vec))
             (conj ludics l))))))))
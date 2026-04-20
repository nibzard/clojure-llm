(defn get_ludic
  "	Write a function to get all lucid numbers smaller than or equal to a given integer."
  [n]
  (let [limit (long (or n 0))]
    (if (< limit 1)
      []
      (loop [candidates (vec (range 1 (inc limit)))
             ludics []]
        (if (empty? candidates)
          ludics
          (let [l (first candidates)
                remaining (subvec candidates 1)
                filtered (vec
                          (keep-indexed
                           (fn [idx x]
                             (when (not (zero? (mod (inc idx) l)))
                               x))
                           remaining))]
            (recur filtered (conj ludics l))))))))
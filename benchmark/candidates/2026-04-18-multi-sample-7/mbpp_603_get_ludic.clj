(defn get_ludic
  "	Write a function to get all lucid numbers smaller than or equal to a given integer."
  [n]
  (let [limit (long (or n 0))]
    (if (< limit 1)
      []
      (loop [remaining (vec (range 2 (inc limit)))
             ludics    [1]]
        (if (empty? remaining)
          ludics
          (let [l (first remaining)
                filtered (->> (rest remaining)
                              (keep-indexed (fn [idx x]
                                              (when (not (zero? (mod (inc idx) l)))
                                                x)))
                              vec)]
            (recur filtered (conj ludics l)))))))
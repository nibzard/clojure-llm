(defn get_ludic
  "	Write a function to get all lucid numbers smaller than or equal to a given integer."
  [n]
  (let [limit (long (or n 0))]
    (if (< limit 1)
      []
      (loop [ludics []
             candidates (vec (range 1 (inc limit)))]
        (if (empty? candidates)
          ludics
          (let [l (first candidates)
                remaining (subvec candidates 1)
                filtered (if (empty? remaining)
                           []
                           (->> remaining
                                (keep-indexed (fn [idx x]
                                                (when (not= (mod (inc idx) l) 0)
                                                  x)))
                                vec))]
            (recur (conj ludics l) filtered))))))
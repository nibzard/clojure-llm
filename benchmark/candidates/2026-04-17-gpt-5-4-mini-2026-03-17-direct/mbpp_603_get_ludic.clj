(defn get_ludic
  "Write a function to get all lucid numbers smaller than or equal to a given integer."
  [n]
  (let [limit (long (or n 0))]
    (if (< limit 1)
      []
      (loop [candidates (vec (range 2 (inc limit)))
             step 2]
        (if (or (empty? candidates) (> step (count candidates)))
          (vec (cons 1 candidates))
          (let [idx (dec step)
                survivors (vec (keep-indexed (fn [i x] (when (not= 0 (mod (inc i) step)) x))
                                             candidates))]
            (recur survivors (inc step))))))))
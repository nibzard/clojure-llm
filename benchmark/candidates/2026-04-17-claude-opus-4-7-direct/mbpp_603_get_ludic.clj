(defn get_ludic
  "Write a function to get all lucid numbers smaller than or equal to a given integer."
  [n]
  (if (< n 1)
    []
    (loop [ludics (range 1 (inc n))
           step 1]
      (let [current-count (count ludics)]
        (if (> step current-count)
          ludics
          (let [next-ludics (keep-indexed #(if (zero? (mod (inc %1) step)) %2) ludics)]
            (recur next-ludics (inc step))))))))
(defn get_ludic
  "	Write a function to get all lucid numbers smaller than or equal to a given integer."
  [n]
  (let [limit (if (number? n) (long n) 0)]
    (if (< limit 1)
      []
      (loop [remaining (vec (range 2 (inc limit)))
             ludics [1]]
        (if (empty? remaining)
          ludics
          (let [l (first remaining)
                filtered (->> remaining
                              (map-indexed vector)
                              (remove (fn [[idx _]] (zero? (mod (inc idx) l))))
                              (mapv second))]
            (recur filtered (conj ludics l))))))))
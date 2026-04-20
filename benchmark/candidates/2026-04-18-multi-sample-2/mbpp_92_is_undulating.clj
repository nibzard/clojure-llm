(defn is_undulating
  "	Write a function to check whether the given number is undulating or not."
  [n]
  (let [s (str n)
        digits (seq s)]
    (and (>= (count digits) 3)
         (= 2 (count (distinct digits)))
         (every? true?
                 (map-indexed (fn [i ch]
                                (= ch (nth digits (mod i 2))))
                              digits)))))
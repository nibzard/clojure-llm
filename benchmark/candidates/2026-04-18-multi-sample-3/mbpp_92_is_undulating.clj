(defn is_undulating
  "	Write a function to check whether the given number is undulating or not."
  [n]
  (let [s (str n)]
    (and (>= (count s) 3)
         (let [chars (seq s)
               a (first chars)
               b (second chars)]
           (and (not= a b)
                (every? true?
                        (map-indexed (fn [i ch]
                                       (= ch (if (even? i) a b)))
                                     chars)))))))
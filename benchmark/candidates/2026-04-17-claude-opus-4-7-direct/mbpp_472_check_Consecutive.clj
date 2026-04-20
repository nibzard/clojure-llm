(defn check-Consecutive
  "Write a cljthon function to check whether the given list contains consecutive numbers or not."
  [l]
  (if (empty? l)
    true
    (let [sorted-l (sort l)
          min-val (first sorted-l)
          max-val (last sorted-l)
          n (count l)]
      (and (= (- max-val min-val) (dec n))
           (= (count (set l)) n)))))
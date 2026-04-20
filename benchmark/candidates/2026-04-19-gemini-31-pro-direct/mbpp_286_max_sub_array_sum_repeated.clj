(defn max_sub_array_sum_repeated
  "Write a function to find the largest sum of a contiguous vector in the modified vector which is formed by repeating the given vector k times."
  [a n k]
  (if (or (empty? a) (zero? k))
    0
    (let [kadane (fn [arr]
                   (second
                    (reduce (fn [[curr-max global-max] x]
                              (let [curr-max (max x (+ curr-max x))]
                                [curr-max (max global-max curr-max)]))
                            [(first arr) (first arr)]
                            (rest arr))))
          sum-a (reduce + a)]
      (if (= k 1)
        (kadane a)
        (let [a2 (concat a a)
              max-2 (kadane a2)]
          (if (> sum-a 0)
            (+ max-2 (* (- k 2) sum-a))
            max-2))))))
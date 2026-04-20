(defn max_sub_array_sum_repeated
  "	Write a function to find the largest sum of a contiguous vector in the modified vector which is formed by repeating the given vector k times."
  [a n k]
  (let [v (vec (or a []))
        n' (min (or n 0) (count v))
        xs (subvec v 0 n')
        kadane (fn [coll]
                 (when (seq coll)
                   (reduce (fn [[best curr] x]
                             (let [curr' (max x (+ curr x))
                                   best' (max best curr')]
                               [best' curr']))
                           [(first coll) (first coll)]
                           (rest coll))))
        total (reduce + 0 xs)]
    (cond
      (or (zero? n') (<= (or k 0) 0)) 0
      (= k 1) (first (kadane xs))
      :else (let [[best-two _]
                  (kadane (concat xs xs))]
              (if (pos? total)
                (+ best-two (* (- k 2) total))
                best-two)))))
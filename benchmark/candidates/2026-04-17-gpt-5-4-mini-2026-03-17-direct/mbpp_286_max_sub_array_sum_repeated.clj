(defn max_sub_array_sum_repeated
  "Write a function to find the largest sum of a contiguous vector in the modified vector which is formed by repeating the given vector k times."
  [a n k]
  (let [arr (vec a)
        total (reduce + 0 arr)
        max-subarray
        (fn [v]
          (reduce (fn [[best curr] x]
                    (let [curr' (max x (+ curr x))
                          best' (max best curr')]
                      [best' curr']))
                  [Long/MIN_VALUE 0]
                  v))
        max-prefix
        (fn [v]
          (second
           (reduce (fn [[sum best] x]
                     (let [sum' (+ sum x)]
                       [sum' (max best sum')]))
                   [0 Long/MIN_VALUE]
                   v)))
        max-suffix
        (fn [v]
          (second
           (reduce (fn [[sum best] x]
                     (let [sum' (+ sum x)]
                       [sum' (max best sum')]))
                   [0 Long/MIN_VALUE]
                   (rseq (vec v)))))]
    (cond
      (or (nil? arr) (zero? n) (zero? k)) 0
      (= k 1) (first (max-subarray arr))
      :else (let [single-max (first (max-subarray arr))
                  prefix (max-prefix arr)
                  suffix (max-suffix arr)
                  cross (+ suffix prefix)]
              (if (pos? total)
                (max single-max cross (+ cross (* (- k 2) total)))
                (max single-max cross))))))
(defn sum-negative-indexed
  "Return the sum of values at negative indexes in a vector.
  Negative indexes count from the end, like Python: -1 is the last element,
  -2 is the second-to-last, etc.

  Examples:
  (sum-negative-indexed [10 20 30 40] [-1 -3]) => 70
  (sum-negative-indexed [5 6 7] [-1 -1 -2]) => 20
  (sum-negative-indexed [] [-1]) => 0"
  [v idxs]
  (let [n (count v)]
    (reduce
      (fn [acc i]
        (let [j (if (neg? i) (+ n i) i)]
          (+ acc (if (and (<= 0 j) (< j n)) (nth v j) 0))))
      0
      idxs)))
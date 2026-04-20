(defn max-pair-sum
  "Given a vector of integers `v`, return the largest sum obtainable from any
  pair of elements mirrored around the center of the vector.

  A mirrored pair consists of `v[i]` and `v[(count v) - 1 - i]` for some valid `i`.
  If the vector has an odd length, the middle element is ignored.

  If `v` has fewer than 2 elements, return 0.

  Examples:
  >>> (max-pair-sum [1 2 3 4])
  5
  ;; mirrored pairs are [1 4] => 5, [2 3] => 5

  >>> (max-pair-sum [7 1 9 2 5])
  12
  ;; mirrored pairs are [7 5] => 12, [1 2] => 3

  >>> (max-pair-sum [10])
  0"
  [v]
  (let [n (count v)
        half (quot n 2)]
    (if (zero? half)
      0
      (apply max
             (map #(+ (nth v %) (nth v (- (dec n) %)))
                  (range half))))))
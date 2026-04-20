(defn pair-xor-sum
  "Return the sum of bitwise XOR for all unordered pairs in `coll`.

  Ignores nil values. Works with any sequence, including lazy/infinite ones
  when the input is finite after `take`.

  Examples:
  (pair-xor-sum [1 2 3])
  ;; => 6
  ;; pairs: 1^2=3, 1^3=2, 2^3=1

  (pair-xor-sum [1 nil 2 4])
  ;; => 11"
  [coll]
  (let [nums (remove nil? coll)]
    (reduce
     +
     (for [i (range (count nums))
           j (range (inc i) (count nums))]
       (bit-xor (nth nums i) (nth nums j))))))